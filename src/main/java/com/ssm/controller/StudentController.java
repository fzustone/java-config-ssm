package com.ssm.controller;

import com.ssm.model.Student;
import com.ssm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author cly
 * @create 2019-11-20 22:22
 */
@Controller
@RequestMapping
public class StudentController implements EnvironmentAware {

	@Autowired
	private StudentService studentService;

	@ResponseBody
	@GetMapping(value = "/student/get-student")
	public Student getStudent(@RequestParam(name = "id") int id) {
		return studentService.getStudent(id);

	}

	@ResponseBody
	@GetMapping(value = "/student/${id}")
	public Student index(@PathVariable(value = "id") int id) {
		return studentService.getStudent(id);

	}
	@GetMapping(value = "/index")
	public String index() {
		return "forward:index.html";

	}
	private Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
