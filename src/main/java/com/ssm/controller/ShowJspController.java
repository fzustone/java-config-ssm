package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * JSP输出测试
 *
 * @author chenly
 * @create 2020-07-04 16:29
 */
@Controller
public class ShowJspController {

	@GetMapping("/showJsp")
	public ModelAndView showJsp() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("showJsp");
		modelAndView.addObject("message","bilibili 干杯");
		return modelAndView;
	}

	@GetMapping("/redirectJsp")
	public String redirectJsp() {
		return "redirect:/showJsp";
	}

	@GetMapping("/redirect-handler")
	public String testredirect(Model model, RedirectAttributes attr) {
		attr.addFlashAttribute("test", "小陈真好");
		attr.addFlashAttribute("u2", "GOOD GOOD STUDY");
		return "redirect:/showJsp";
	}
}
