package com.ssm.controller;

import com.ssm.model.Student;
import com.ssm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cly
 * @create 2019-11-20 22:22
 */
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping(value = "/get-student")
	public Student getStudent(@RequestParam(name = "id") int id){
		return studentService.getStudent(id);

	}
}
