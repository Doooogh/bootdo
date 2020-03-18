package com.bootdo.classinfo.dao;

import com.bootdo.classinfo.domain.ClassinfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 班级介绍
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 23:56:21
 */
@Mapper
public interface ClassinfoDao {

	ClassinfoDO get(Long id);
	
	List<ClassinfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ClassinfoDO classinfo);
	
	int update(ClassinfoDO classinfo);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int updateIsNew(Long deptId);
}
