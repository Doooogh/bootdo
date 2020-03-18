package com.bootdo.student.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.student.domain.StudentDO;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-18 22:05:52
 */
public interface StudentService {
	
	StudentDO get(Integer id);
	
	List<StudentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentDO student);
	
	int update(StudentDO student);

	int remove(Integer id);
	
	int batchRemove(Integer[] ids);


	/**
	 * 根据部门id查询所有该部门下的人 除了自己
	 * @param
	 * @return
	 */
	Tree<UserDO> getTreeByDeptId();


	Tree<UserDO> getTreeByUserId();




}
