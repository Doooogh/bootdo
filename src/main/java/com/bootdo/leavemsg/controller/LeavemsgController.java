package com.bootdo.leavemsg.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
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

import com.bootdo.leavemsg.domain.LeavemsgDO;
import com.bootdo.leavemsg.service.LeavemsgService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 留言
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 22:46:28
 */
 
@Controller
@RequestMapping("/leavemsg/leavemsg")
public class LeavemsgController {
	@Autowired
	private LeavemsgService leavemsgService;
	
	@GetMapping()
	@RequiresPermissions("leavemsg:leavemsg:leavemsg")
	String Leavemsg(){
	    return "leavemsg/leavemsg/leavemsg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("leavemsg:leavemsg:leavemsg")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        if(null==query.get("touserId")||StringUtils.isBlank((String)query.get("touserId"))){
        	query.put("toUserId",ShiroUtils.getUserId());
		}
		List<LeavemsgDO> leavemsgList = leavemsgService.list(query);
		int total = leavemsgService.count(query);
		PageUtils pageUtils = new PageUtils(leavemsgList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("leavemsg:leavemsg:add")
	String add(){
	    return "leavemsg/leavemsg/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("leavemsg:leavemsg:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		LeavemsgDO leavemsg = leavemsgService.get(id);
		model.addAttribute("leavemsg", leavemsg);
	    return "leavemsg/leavemsg/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("leavemsg:leavemsg:add")
	public R save( LeavemsgDO leavemsg){
		if(null==leavemsg.getTouserId()){
			return R.error("没有指定为谁留言!");
		}
		boolean b = leavemsgService.hasZZ(leavemsg.getContent());
		if(b){
			leavemsg.setIsPass(0);
		}else{
			leavemsg.setIsPass(1);
		}
		leavemsg.setFromuserId((int)(long)ShiroUtils.getUserId());
		leavemsg.setCreateDate(new Date());
		if(leavemsgService.save(leavemsg)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("leavemsg:leavemsg:edit")
	public R update( LeavemsgDO leavemsg){
		leavemsgService.update(leavemsg);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("leavemsg:leavemsg:remove")
	public R remove( Integer id){
		if(leavemsgService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("leavemsg:leavemsg:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		leavemsgService.batchRemove(ids);
		return R.ok();
	}
	
}
