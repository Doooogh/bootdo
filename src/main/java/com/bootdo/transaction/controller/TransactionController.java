package com.bootdo.transaction.controller;

import java.util.Date;
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

import com.bootdo.transaction.domain.TransactionDO;
import com.bootdo.transaction.service.TransactionService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 班级事务
 * 
 * @author Li
 * @email m13283354149@163.com
 * @date 2020-03-16 23:44:13
 */
 
@Controller
@RequestMapping("/transaction/transaction")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping()
	@RequiresPermissions("transaction:transaction:transaction")
	String Transaction(){
	    return "transaction/transaction/transaction";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("transaction:transaction:transaction")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TransactionDO> transactionList = transactionService.list(query);
		int total = transactionService.count(query);
		PageUtils pageUtils = new PageUtils(transactionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("transaction:transaction:add")
	String add(){
	    return "transaction/transaction/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("transaction:transaction:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TransactionDO transaction = transactionService.get(id);
		model.addAttribute("transaction", transaction);
	    return "transaction/transaction/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("transaction:transaction:add")
	public R save( TransactionDO transaction){
		transaction.setCreateBy(ShiroUtils.getUserId());
		transaction.setCreateDate(new Date());
		if(transactionService.save(transaction)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("transaction:transaction:edit")
	public R update( TransactionDO transaction){
		transactionService.update(transaction);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("transaction:transaction:remove")
	public R remove( Long id){
		if(transactionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("transaction:transaction:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		transactionService.batchRemove(ids);
		return R.ok();
	}
	
}
