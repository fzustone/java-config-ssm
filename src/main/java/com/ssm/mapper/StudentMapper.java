package com.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssm.model.Student;
import org.springframework.stereotype.Repository;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenly1
 * @since 2019-11-19
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {

	Student selectByPrimaryKey(int id);
}
