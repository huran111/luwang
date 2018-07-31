package com.hr;

import com.hr.demo.IDemoServer;
import com.hr.demo.UserService;
import com.hr.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboxConsumerApplicationTests {
	@Test
	public void contextLoads() {
		final String port = "8888";

		//测试Rest服务
		getUser("http://192.168.10.30:" + port + "/services/users/1");
		//getUser("http://localhost:" + port + "/users/1.xml");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring/spring-consumer.xml");
		UserService userService = context.getBean(UserService.class);
		User user=userService.getUser(1L);
		System.out.println("***********:"+user.toString());
		context.start();
	}

	private static void getUser(String url) {
		System.out.println("getting user :" + url);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		Response response = target.request().get();
		try {
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + response.getStatus());
			}
			System.out.println("Successfully got result: " + response.readEntity(String.class));

		} finally {
			response.close();
			client.close();
		}
	}

}
