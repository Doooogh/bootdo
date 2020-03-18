package com.bootdo.transaction.dao;

import com.bootdo.transaction.domain.TransactionDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 班级事务
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 23:44:13
 */
@Mapper
public interface TransactionDao {

	TransactionDO get(Long id);
	
	List<TransactionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TransactionDO transaction);
	
	int update(TransactionDO transaction);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<TransactionDO> listByDeptId(Long deptId);
}
