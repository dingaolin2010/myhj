package com.luo.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

	/**
	 * @PathVariable 注解
	 * @param id
	 * @return
	 */
	@GetMapping(value = { "/say/{id}" })
	public String say(@PathVariable("id") Integer id) {
		return "id:" + id;
	}

	/**
	 * @RequestParam 注解
	 * @param id
	 * @return
	 */
	@GetMapping("/say2")
	public String say2(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
		return "id:" + id;
	}

}