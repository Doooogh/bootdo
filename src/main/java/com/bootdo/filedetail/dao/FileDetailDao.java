package com.bootdo.filedetail.dao;

import com.bootdo.filedetail.domain.FileDetailDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 15:00:30
 */
@Mapper
public interface FileDetailDao {

	FileDetailDO get(Integer id);
	
	List<FileDetailDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FileDetailDO fileDetail);
	
	int update(FileDetailDO fileDetail);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
