package com.bootdo.filedir.service;

import com.bootdo.filedir.domain.FileDirDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 15:27:35
 */
public interface FileDirService {
	
	FileDirDO get(Integer id);
	
	List<FileDirDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FileDirDO fileDir);
	
	int update(FileDirDO fileDir);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);


}
