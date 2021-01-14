package com.unibuc.Home.Management.Platform;

import com.unibuc.Home.Management.Platform.domain.Person;
import com.unibuc.Home.Management.Platform.domain.Priority;
import com.unibuc.Home.Management.Platform.domain.Status;
import com.unibuc.Home.Management.Platform.domain.Task;
import com.unibuc.Home.Management.Platform.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.PersistenceDelegate;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
class HomeManagementPlatformApplicationTests {

	@Mock
	private TaskService taskService;

	Principal principal = new Principal() {
		@Override
		public String getName() {
			return "cristi";
		}
	};

	@Test
	public void getAllTasks(){
		List<Task> tasks = new ArrayList<>();

		Priority priority = new Priority(1, "nameP", 1);
		Person person = new Person(1, "First", "last", 22, 1);

		Task t1 = new Task(1, "name1", "descr1", 1, Status.NEW, new Date(124343454), new Date(124343454), 1);
		Task t2 = new Task(2, "name2", "descr2", 2, Status.DONE, new Date(124343454), new Date(124343454), 1);
		Task t3 = new Task(3, "name3", "descr3", 3, Status.DONE, new Date(124343454), new Date(124343454), 1);

		tasks.add(t1);
		tasks.add(t2);
		tasks.add(t3);

		when(taskService.getAll()).thenReturn(tasks);
		assertEquals(3, tasks.size());
		assertEquals(1, tasks.size());

	}

}
