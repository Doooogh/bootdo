package com.bootdo.classinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.classinfo.dao.ClassinfoDao;
import com.bootdo.classinfo.domain.ClassinfoDO;
import com.bootdo.classinfo.service.ClassinfoService;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ClassinfoServiceImpl implements ClassinfoService {
	@Autowired
	private ClassinfoDao classinfoDao;
	
	@Override
	public ClassinfoDO get(Long id){
		return classinfoDao.get(id);
	}
	
	@Override
	public List<ClassinfoDO> list(Map<String, Object> map){
		return classinfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return classinfoDao.count(map);
	}
	
	@Override
	public int save(ClassinfoDO classinfo){
		classinfoDao.updateIsNew(classinfo.getDeptId());
		return classinfoDao.save(classinfo);
	}
	
	@Override
	public int update(ClassinfoDO classinfo){
		return classinfoDao.update(classinfo);
	}
	
	@Override
	public int remove(Long id){
		return classinfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return classinfoDao.batchRemove(ids);
	}

	@Override
	public int updateIsNew(Long deptId) {
		return classinfoDao.updateIsNew(deptId);
	}

}
