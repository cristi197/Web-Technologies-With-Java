package webtechnologies.lab1.ex3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet("/headers")
public class HeadersDemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name =  request.getParameter("name");
        Locale locale = request.getLocale();
       String languageTag = locale.toLanguageTag().substring(0,2);
       String greeting;

       switch (languageTag)
       {
           case "en":
               greeting = "Hello";
               break;
           case "fr":
               greeting = "Bonjour";
               break;
           default:
               greeting = "Salut";
       }
        response.setHeader("Content-language",languageTag);
        response.getWriter().println(String.format(greeting + " "+name + "!"));
    }
}
