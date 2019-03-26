package com.spirngcloud.spirngcloud;

import com.spirngcloud.spirngcloud.controller.HelloController;
import com.spirngcloud.spirngcloud.security.Permission;
import com.spirngcloud.spirngcloud.service.BusinessService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpirngcloudApplicationTests {

	@Autowired
	BusinessService businessService;

	MockMvc mvc;
	@Test
	public void contextLoads() {
		Permission.setUser("shuaiGe");
		businessService.delete(1);
	}

	@Test
	public void admin(){
		Permission.setUser("admin");
		businessService.delete(1);
	}

	@Before
	public void setUp(){
		mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
	}
	@Test
	public void hello () throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("hello")));
	}
}
