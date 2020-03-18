package com.bootdo.filedetail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.filedetail.dao.FileDetailDao;
import com.bootdo.filedetail.domain.FileDetailDO;
import com.bootdo.filedetail.service.FileDetailService;



@Service
public class FileDetailServiceImpl implements FileDetailService {
	@Autowired
	private FileDetailDao fileDetailDao;
	
	@Override
	public FileDetailDO get(Integer id){
		return fileDetailDao.get(id);
	}
	
	@Override
	public List<FileDetailDO> list(Map<String, Object> map){
		return fileDetailDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return fileDetailDao.count(map);
	}
	
	@Override
	public int save(FileDetailDO fileDetail){
		return fileDetailDao.save(fileDetail);
	}
	
	@Override
	public int update(FileDetailDO fileDetail){
		return fileDetailDao.update(fileDetail);
	}
	
	@Override
	public int remove(Integer id){
		return fileDetailDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return fileDetailDao.batchRemove(ids);
	}
	
}
