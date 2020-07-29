package com.ssm.controller;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author chenly
 * @create 2020-06-30 21:46
 */
@RestController
@SessionAttributes(names = { "top" }, types = Map.class)
public class GoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GoController.class);

/*	@ModelAttribute("className")
	public String setModel() {
		return this.getClass()
				.getName();
	}*/

	@ModelAttribute("top")
	public Map top(){
		return ImmutableMap.of("123","hello");
	}

/*	@ModelAttribute
	public void setModel1(Model model) throws IOException {
		model.addAttribute("teacher", "孔老夫子");
	}*/

	/*//执行处理前给Model设置("string", "excelib")
	@ModelAttribute
	public String setModel2() throws IOException {
		return "excelib";
	}*/

	@GetMapping("/model-attribute")
	public void test(HttpServletRequest httpRequest, Model model,@ModelAttribute(value = "top",binding = true) Map top) {
		LOGGER.info("map value:{}",top );
		Map<String, Object> map=model.asMap();
		LOGGER.info("className ->{}", map.get("className"));
		LOGGER.info("teacher ->{}", map.get("teacher"));
		LOGGER.info("string ->{}", map.get("string"));
	}

}
