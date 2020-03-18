package com.bootdo.basicinfo.controller;

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

import com.bootdo.basicinfo.domain.BasicInfoDO;
import com.bootdo.basicinfo.service.BasicInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-08 17:53:30
 */
 
@Controller
@RequestMapping("/basicinfo/basicInfo")
public class BasicInfoController {
	@Autowired
	private BasicInfoService basicInfoService;
	
	@GetMapping()
	@RequiresPermissions("basicinfo:basicInfo:basicInfo")
	String BasicInfo(){
	    return "basicinfo/basicInfo/file";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("basicinfo:basicInfo:basicInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BasicInfoDO> basicInfoList = basicInfoService.list(query);
		int total = basicInfoService.count(query);
		PageUtils pageUtils = new PageUtils(basicInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("basicinfo:basicInfo:add")
	String add(Model model){
		if(ShiroUtils.getSubjct().isPermitted("common:generator")){
			model.addAttribute("isAdmin",true);
		}
		Long userId = ShiroUtils.getUserId();
		model.addAttribute("userId",userId);
		return "basicinfo/basicInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("basicinfo:basicInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		BasicInfoDO basicInfo = basicInfoService.get(id);
		model.addAttribute("basicInfo", basicInfo);
	    return "basicinfo/basicInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("basicinfo:basicInfo:add")
	public R save( BasicInfoDO basicInfo){
		if(basicInfoService.save(basicInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("basicinfo:basicInfo:edit")
	public R update( BasicInfoDO basicInfo){
		basicInfoService.update(basicInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("basicinfo:basicInfo:remove")
	public R remove( Integer id){
		if(basicInfoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("basicinfo:basicInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		basicInfoService.batchRemove(ids);
		return R.ok();
	}
	
}
