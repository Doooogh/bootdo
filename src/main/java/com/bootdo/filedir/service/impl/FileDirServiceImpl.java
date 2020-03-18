package com.bootdo.filedir.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.filedir.dao.FileDirDao;
import com.bootdo.filedir.domain.FileDirDO;
import com.bootdo.filedir.service.FileDirService;



@Service
public class FileDirServiceImpl implements FileDirService {
	@Autowired
	private FileDirDao fileDirDao;
	
	@Override
	public FileDirDO get(Integer id){
		return fileDirDao.get(id);
	}
	
	@Override
	public List<FileDirDO> list(Map<String, Object> map){
		return fileDirDao.list(map);
	}

	@Override
	public List<FileDirDO> allList(Map<String, Object> map) {
		return fileDirDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return fileDirDao.count(map);
	}
	
	@Override
	public int save(FileDirDO fileDir){
		return fileDirDao.save(fileDir);
	}
	
	@Override
	public int update(FileDirDO fileDir){
		return fileDirDao.update(fileDir);
	}
	
	@Override
	public int remove(Integer id){
		return fileDirDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return fileDirDao.batchRemove(ids);
	}

	@Override
	public int listCount(Map<String, Object> map) {
		return fileDirDao.listCount(map);
	}

}
