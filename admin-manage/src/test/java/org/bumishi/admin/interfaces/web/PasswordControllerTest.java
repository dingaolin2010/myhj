package org.bumishi.admin.interfaces.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@WebAppConfiguration
public class PasswordControllerTest {

	// mock api 模拟http请求
	private MockMvc mockMvc;

	// 初始化工作
	@Before
	public void setUp() {
		// 独立安装测试
		mockMvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
		// 集成Web环境测试（此种方式并不会集成真正的web环境，而是通过相应的Mock API进行模拟测试，无须启动服务器）
		// mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
/*
	@Test
	public void modifyPwd() {
		mockMvc.perform(MockMvcRequestBuilders.get("/index")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(equalTo("index")));
	}*/
}
