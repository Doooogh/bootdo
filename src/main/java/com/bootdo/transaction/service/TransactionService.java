package com.bootdo.transaction.service;

import com.bootdo.transaction.domain.TransactionDO;

import java.util.List;
import java.util.Map;

/**
 * 班级事务
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 23:44:13
 */
public interface TransactionService {
	
	TransactionDO get(Long id);
	
	List<TransactionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TransactionDO transaction);
	
	int update(TransactionDO transaction);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	/**
	 * 查询一个班级的所有事物 根据时间来排序
	 * @param deptId
	 * @return
	 */
	List<TransactionDO> listByDeptId(Long deptId);
}
