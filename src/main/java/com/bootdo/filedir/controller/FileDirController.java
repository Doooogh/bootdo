package com.bootdo.filedir.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.filedir.domain.FileDirDO;
import com.bootdo.filedir.service.FileDirService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 15:27:35
 */
 
@Controller
@RequestMapping("/filedir/fileDir")
public class FileDirController {
	@Autowired
	private FileDirService fileDirService;
	
	@GetMapping()
	@RequiresPermissions("filedir:fileDir:fileDir")
	String FileDir(){
	    return "filedir/fileDir/fileDir";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("filedir:fileDir:fileDir")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		query.put("createBy",ShiroUtils.getUserId());
		List<FileDirDO> fileDirList = fileDirService.list(query);
		int total = fileDirService.count(query);
		PageUtils pageUtils = new PageUtils(fileDirList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/allList")
	@RequiresPermissions("filedir:fileDir:fileDir")
	public Map allList(@RequestParam Map<String, Object> params){
		Map<String,Object> map=new HashMap<>();
		String rc="0";
		try{
			//查询列表数据
			params.put("createBy",ShiroUtils.getUserId());
			List<FileDirDO> fileDirList = fileDirService.list(params);
			map.put("list",fileDirList);
		}catch (Exception e){
			e.printStackTrace();
			rc="-1";
		}
		map.put("rc",rc);
		return map;
	}

	
	@GetMapping("/add")
	@RequiresPermissions("filedir:fileDir:add")
	String add(){
	    return "filedir/fileDir/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("filedir:fileDir:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		FileDirDO fileDir = fileDirService.get(id);
		model.addAttribute("fileDir", fileDir);
	    return "filedir/fileDir/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("filedir:fileDir:add")
	public R save( FileDirDO fileDir){
		fileDir.setCreateBy((int)(long)ShiroUtils.getUserId());
		fileDir.setCreateDate(new Date());
		if(fileDirService.save(fileDir)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("filedir:fileDir:edit")
	public R update( FileDirDO fileDir){
		fileDirService.update(fileDir);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("filedir:fileDir:remove")
	public R remove( Integer id){
		if(fileDirService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("filedir:fileDir:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		fileDirService.batchRemove(ids);
		return R.ok();
	}
	
}
