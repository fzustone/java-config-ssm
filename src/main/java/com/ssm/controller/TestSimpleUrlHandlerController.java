package com.ssm.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenly
 * @create 2020-06-20 22:28
 */
public class TestSimpleUrlHandlerController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws
			Exception {

		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "HelloGuestController");

		return model;
	}

}
