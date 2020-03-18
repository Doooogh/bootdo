package com.bootdo.system.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.domain.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:28:36
 */
public interface DeptService {
	
	DeptDO get(Long deptId);
	
	List<DeptDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptDO sysDept);
	
	int update(DeptDO sysDept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);

	Tree<DeptDO> getTree();
	
	boolean checkDeptHasUser(Long deptId);

	List<Long> listChildrenIds(Long parentId);

	/**
	 * 根据deptId 查询所有的子类
	 * @param deptId
	 * @return
	 */
	List<DeptDO>listAllChildDept(String deptId);

	/**
	 * 查询所有的班级 (就是没子集的)
	 * @return
	 */
	List<DeptDO> listNoChildDept();
}
