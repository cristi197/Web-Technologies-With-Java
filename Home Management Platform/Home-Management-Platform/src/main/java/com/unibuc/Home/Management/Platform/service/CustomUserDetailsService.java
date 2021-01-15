package com.unibuc.Home.Management.Platform.service;

import com.unibuc.Home.Management.Platform.domain.*;
import com.unibuc.Home.Management.Platform.repository.PersonRepository;
import com.unibuc.Home.Management.Platform.repository.RoleRepository;
import com.unibuc.Home.Management.Platform.repository.UserRepository;
import com.unibuc.Home.Management.Platform.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger log = Logger.getLogger(CustomUserDetailsService.class.getName());

    @Override
    public UserDetails loadUserByUsername(String username) throws ResponseStatusException {
        log.info("Loading user by username...");
        System.out.println("adfd");
        Optional<User> client = userRepository.findByUsername(username);
        System.out.println(client);


        if (client == null) {
            log.info("User not found...");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username: " + username);
        }
        log.info("Loading...");
        if (client.get().getUsername() != null) {
            return new org.springframework.security.core.userdetails.User(client.get().getUsername(), client.get().getPassword(),
                    getAuthority(client));
        } else {
            return new org.springframework.security.core.userdetails.User(client.get().getUsername(), client.get().getPassword(),
                    getAuthority(client));
        }
    }

    private List<SimpleGrantedAuthority> getAuthority(Optional<User> client) {
        log.info("Fetching authority...");
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Optional<Role> role = roleRepository.findById(client.get().getRoleId());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.get().getName()));
        return authorities;
    }

    public User save(User user) {
        log.info("Saving new client account...");
        System.out.println("username:"+user.getUsername());
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));

        Optional<Role> role = roleRepository.findByName("USER");
        newUser.setRoleId(role.get().getId());
        System.out.println(user);
        Person person = new Person();
        person.setRoleId(role.get().getId());
        //person.setFirstName("First");
        //person.setLastName("Last");
        //person.setAge(1);
        //createPerson(person);

        log.info("New client saved...");
        return userRepository.save(newUser, createPerson(person));
    }

    public Person createPerson(Person person){
        String sql = "insert into person values (?,?,?,?,?)";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setInt(4, person.getAge());
            preparedStatement.setObject(5, person.getRoleId());

            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        generatedKeyHolder.getKey();
        person.setId(generatedKeyHolder.getKey().longValue());
        return person;
    }

    public JwtResponse handleLogin(JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            log.info("User disabled...");
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            log.info("Invalid credentials...");
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
