package com.ssm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author chenly
 * @create 2020-07-04 20:26
 */
@Controller
@SessionAttributes(names = { "name", "attr2" }, types = String.class)
public class SessionAttributesController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionAttributesController.class);

	@GetMapping(value = "/session")
	public String model(Model model) {
		model.addAttribute("name", "小陈小陈心想事成");
		return "sessionAtt";
	}

	@GetMapping(value = "/session2")
	public String model2(Model model) {
		model.addAttribute("attr2", "一马平川");
		return "sessionAtt";
	}

	@GetMapping(value = "/get-session-attributes")
	public void getSessionAttributes(@ModelAttribute("name") String name) {
		LOGGER.info("name = {}", name);

	}

	@GetMapping(value = "/get-session-attributes-2")
	public void getSessionAttributes2(@SessionAttribute("name") String name) {
		LOGGER.info("name = {}", name);

	}

}
