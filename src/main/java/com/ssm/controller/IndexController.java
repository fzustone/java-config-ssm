package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenly
 * @create 2020-06-30 21:42
 */
@Controller
@RequestMapping
public class IndexController {

	@GetMapping(value = "/index")
	public String index() {
		return "forward:index.html";

	}
}
