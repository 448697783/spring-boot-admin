package admin.modules.testcase1.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import admin.common.annotation.CtrlLog;
import admin.common.utils.BeanCopierUtils;
import admin.common.validator.ValidatorUtils;
import admin.modules.testcase1.entity.Testcase1Entity;
import admin.modules.testcase1.service.ITestcase1Service;
import admin.modules.testcase1.vo.Testcase1DeleteVO;
import admin.modules.testcase1.vo.Testcase1DeleteVO.*;
import admin.modules.testcase1.vo.Testcase1EditVO;
import admin.modules.testcase1.vo.Testcase1EditVO.*;
import admin.modules.testcase1.vo.Testcase1QueryVO;
import admin.modules.testcase1.vo.Testcase1QueryVO.Testcase1QResultVO;
import admin.modules.testcase1.vo.Testcase1SaveVO;
import admin.modules.testcase1.vo.Testcase1SaveVO.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '测试用例', 'modules/testcase1/testcase1.html', NULL, 1, 'fa fa-file-code-o', 1);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '查看', NULL, 'testcase1:queryTestcase1List', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '新增', NULL, 'testcase1:saveTestcase1,testcase1:saveTestcase1List', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '修改', NULL, 'testcase1:editTestcase1,testcase1:editTestcase1List', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '删除', NULL, 'testcase1:deleteTestcase1,testcase1:deleteTestcase1List', 2, NULL, 0);


/**
 * 测试用例
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-05-15 07:07:51
 */
@RestController
@Api(value="测试用例",tags={"测试用例"})
@RequestMapping("testcase1/")
public class Testcase1Controller {
	@Autowired
	private ITestcase1Service testcase1Service;

	/**
	 * 查询列表
	 */
	@RequestMapping(value="/queryTestcase1List",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("查询测试用例")
	@RequiresPermissions("testcase1:queryTestcase1List")
	@ApiOperation(value="查询测试用例",notes="注意问题点")
	@ResponseBody
	public Testcase1QResultVO queryTestcase1List(@ApiParam("查询测试用例参数")Testcase1QueryVO  testcase1QueryVO){
		ValidatorUtils.validateEntity(testcase1QueryVO);
		Testcase1Entity testcase1Entity = new Testcase1Entity();
		BeanCopierUtils.copyProperties(testcase1QueryVO, testcase1Entity);
		List<Testcase1Entity> testcase1List = testcase1Service.queryTestcase1EntityListForPage(testcase1Entity,testcase1QueryVO.getStartRow(),testcase1QueryVO.getEndRow());
		//统计行数,如果数据量比较大可以才线程同时处理两个语句
		Long count= testcase1Service.queryTestcase1EntityListForPageCount(testcase1Entity);
		Testcase1QResultVO testcase1QResultVO = testcase1QueryVO.ok(testcase1List);
		testcase1QResultVO.setCount(count);
		return testcase1QResultVO;
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value="/saveTestcase1",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存测试用例")
	@RequiresPermissions("testcase1:saveTestcase1")
	@ApiOperation(value="保存测试用例",notes="注意问题点")
	@ResponseBody
	public Testcase1SResultVO saveTestcase1(@ApiParam("保存测试用例参数")@RequestBody(required=false) Testcase1SaveVO  testcase1SaveVO){
		ValidatorUtils.validateEntity(testcase1SaveVO);
		Testcase1Entity testcase1Entity = new Testcase1Entity();
		BeanCopierUtils.copyProperties(testcase1SaveVO, testcase1Entity);
	
		testcase1Service.saveTestcase1Entity(testcase1Entity);
		
		return Testcase1SResultVO.ok();
	}
	
	/**
	 * 批量保存
	 */
	@RequestMapping(value="/saveTestcase1List",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存测试用例")
	@RequiresPermissions("testcase1:saveTestcase1List")
	@ApiOperation(value="保存测试用例",notes="注意问题点")
	@ResponseBody
	public Testcase1SResultVO saveTestcase1List(@ApiParam("保存测试用例参数")@RequestBody(required=false) List<Testcase1SaveVO>  testcase1SaveVOList){
		ValidatorUtils.validateEntity(testcase1SaveVOList);
		
		Testcase1Entity entity = null;
		List<Testcase1Entity> entitys = new ArrayList<Testcase1Entity>(testcase1SaveVOList.size());
		for (Testcase1SaveVO tempVO : testcase1SaveVOList) {
			entity = new Testcase1Entity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		testcase1Service.saveTestcase1Entity(entitys);
		
		return Testcase1SResultVO.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/editTestcase1",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新测试用例")
	@RequiresPermissions("testcase1:editTestcase1")
	@ApiOperation(value="更新测试用例",notes="注意问题点")
	@ResponseBody
	public Testcase1EResultVO editTestcase1(@ApiParam("更新测试用例参数")@RequestBody(required=false)Testcase1EditVO testcase1EditVO){
		ValidatorUtils.validateEntity(testcase1EditVO);
		Testcase1Entity testcase1Entity = new Testcase1Entity();
		BeanCopierUtils.copyProperties(testcase1EditVO, testcase1Entity);
	
		testcase1Service.editTestcase1Entity(testcase1Entity);
		
		return  Testcase1EResultVO.ok();
	}
	
	/**
	 * 批量修改
	 */
	@RequestMapping(value="/updateTestcase1List",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新测试用例")
	@RequiresPermissions("testcase1:editTestcase1List")
	@ApiOperation(value="更新测试用例",notes="注意问题点")
	@ResponseBody
	public Testcase1EResultVO editTestcase1List(@ApiParam("更新测试用例参数")@RequestBody(required=false) List<Testcase1EditVO> testcase1EditVOList){
		ValidatorUtils.validateEntity(testcase1EditVOList);
		Testcase1Entity entity = null;
		List<Testcase1Entity> entitys = new ArrayList<Testcase1Entity>(testcase1EditVOList.size());
		for (Testcase1EditVO tempVO : testcase1EditVOList) {
			entity = new Testcase1Entity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		testcase1Service.editTestcase1Entity(entitys);
		
		return  Testcase1EResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteTestcase1",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除测试用例")
	@RequiresPermissions("testcase1:deleteTestcase1")
	@ApiOperation(value="删除测试用例",notes="注意问题点")
	@ResponseBody
	public Testcase1DResultVO deleteTestcase1(@ApiParam("更新测试用例参数") Testcase1DeleteVO testcase1DeleteVO){
		ValidatorUtils.validateEntity(testcase1DeleteVO);
		Testcase1Entity testcase1Entity = new Testcase1Entity();
		BeanCopierUtils.copyProperties( testcase1DeleteVO,testcase1Entity);
	
		testcase1Service.removeTestcase1Entity(testcase1Entity);
		
		return Testcase1DResultVO.ok();
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteTestcase1List",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除测试用例")
	@RequiresPermissions("testcase1:deleteTestcase1List")
	@ApiOperation(value="删除测试用例",notes="注意问题点")
	@ResponseBody
	public Testcase1DResultVO deleteTestcase1List(@ApiParam("更新测试用例参数") @RequestBody List<Testcase1DeleteVO> testcase1DeleteVOList){
		ValidatorUtils.validateEntity(testcase1DeleteVOList);
		Testcase1Entity entity = null;
		List<Testcase1Entity> entitys = new ArrayList<Testcase1Entity>(testcase1DeleteVOList.size());
		for (Testcase1DeleteVO tempVO : testcase1DeleteVOList) {
			entity = new Testcase1Entity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
		
		testcase1Service.removeTestcase1Entity(entitys);
		
		return Testcase1DResultVO.ok();
	}
}
