package com.ssm.service;

import com.ssm.mapper.StudentMapper;
import com.ssm.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cly
 * @create 2019-11-20 22:22
 */
@Service
public class StudentService {

	@Autowired
	private StudentMapper studentMapper;

	public Student getStudent(int id) {
		return studentMapper.selectByPrimaryKey(id);

	}
}
