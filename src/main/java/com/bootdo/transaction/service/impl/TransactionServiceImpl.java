package com.bootdo.transaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.transaction.dao.TransactionDao;
import com.bootdo.transaction.domain.TransactionDO;
import com.bootdo.transaction.service.TransactionService;



@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionDao transactionDao;
	
	@Override
	public TransactionDO get(Long id){
		return transactionDao.get(id);
	}
	
	@Override
	public List<TransactionDO> list(Map<String, Object> map){
		return transactionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return transactionDao.count(map);
	}
	
	@Override
	public int save(TransactionDO transaction){
		return transactionDao.save(transaction);
	}
	
	@Override
	public int update(TransactionDO transaction){
		return transactionDao.update(transaction);
	}
	
	@Override
	public int remove(Long id){
		return transactionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return transactionDao.batchRemove(ids);
	}

	@Override
	public List<TransactionDO> listByDeptId(Long deptId) {
		return null;
	}

}
