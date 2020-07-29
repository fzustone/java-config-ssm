package com.ssm.controller;

import com.ssm.core.editor.CustomJsonEditor;
import com.ssm.model.Student;
import com.ssm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cly
 * @create 2019-11-20 22:22
 */
@RestController
@RequestMapping("/student")
public class StudentController implements EnvironmentAware {
	@Autowired
	private StudentService studentService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(List.class, "extendStr", new CustomJsonEditor(List.class));
	}

	@GetMapping(value = "/get-student")
	public Student getStudent(@RequestParam(name = "id") int id) {
		return studentService.getStudent(id);

	}

	@GetMapping(value = "/${id}")
	public Student index(@PathVariable(value = "id") int id) {
		return studentService.getStudent(id);

	}

	/**
	 * x-www-form-urlencoded 表单提交 与 @InitBinder联动进行绑定
	 */
	@PostMapping(value = "/save")
	public void getStudent(Student student) {
		studentService.save(student);

	}

	@GetMapping(value = "/delete")
	public void delete(Student student) {
		//do nothing

	}

	@PostMapping(value = "/post-save")
	public void postStudent(@RequestBody Student student) {
		studentService.save(student);

	}

	private Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
