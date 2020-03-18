package com.bootdo.student.dao;

import com.bootdo.student.domain.StudentDO;

import java.util.List;
import java.util.Map;

import com.bootdo.system.domain.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-18 22:05:52
 */
@Mapper
public interface StudentDao {

	StudentDO get(Integer id);
	
	List<StudentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentDO student);
	
	int update(StudentDO student);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	int batchSave(List<StudentDO> list);
}
