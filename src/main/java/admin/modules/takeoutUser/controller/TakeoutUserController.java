package admin.modules.takeoutUser.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import admin.common.validator.ValidatorUtils;
import admin.common.utils.BeanCopierUtils;
import admin.common.annotation.CtrlLog;
import admin.modules.takeoutUser.entity.TakeoutUserEntity;
import admin.modules.takeoutUser.vo.TakeoutUserQueryVO;
import admin.modules.takeoutUser.vo.TakeoutUserQueryVO.*;
import admin.modules.takeoutUser.vo.TakeoutUserSaveVO;
import admin.modules.takeoutUser.vo.TakeoutUserSaveVO.*;
import admin.modules.takeoutUser.vo.TakeoutUserDeleteVO;
import admin.modules.takeoutUser.vo.TakeoutUserDeleteVO.*;
import admin.modules.takeoutUser.vo.TakeoutUserEditVO;
import admin.modules.takeoutUser.vo.TakeoutUserEditVO.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import admin.modules.takeoutUser.service.ITakeoutUserService;


//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '', 'modules/takeoutUser/takeoutUser.html', NULL, 1, 'fa fa-file-code-o', 1);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '查看', NULL, 'takeoutUser:queryTakeoutUserList', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '新增', NULL, 'takeoutUser:saveTakeoutUser,takeoutUser:saveTakeoutUserList', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '修改', NULL, 'takeoutUser:editTakeoutUser,takeoutUser:editTakeoutUserList', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '删除', NULL, 'takeoutUser:deleteTakeoutUser,takeoutUser:deleteTakeoutUserList', 2, NULL, 0);


/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-07-01 19:19:04
 */
@RestController
@Api(value="",tags={""})
@RequestMapping("takeoutUser/")
public class TakeoutUserController {
	@Autowired
	private ITakeoutUserService takeoutUserService;

	/**
	 * 查询列表
	 */
	@RequestMapping(value="/queryTakeoutUserList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("查询")
	@RequiresPermissions("takeoutUser:queryTakeoutUserList")
	@ApiOperation(value="查询",notes="注意问题点")
	@ResponseBody
	public TakeoutUserQResultVO queryTakeoutUserList(@ApiParam("查询参数")TakeoutUserQueryVO  takeoutUserQueryVO){
		ValidatorUtils.validateEntity(takeoutUserQueryVO);
		TakeoutUserEntity takeoutUserEntity = new TakeoutUserEntity();
		BeanCopierUtils.copyProperties(takeoutUserQueryVO, takeoutUserEntity);
		List<TakeoutUserEntity> takeoutUserList = takeoutUserService.queryTakeoutUserEntityListForPage(takeoutUserEntity,takeoutUserQueryVO.getStartRow(),takeoutUserQueryVO.getRows());
		//统计行数,如果数据量比较大可以才线程同时处理两个语句
		Long count= takeoutUserService.queryTakeoutUserEntityListForPageCount(takeoutUserEntity);
		TakeoutUserQResultVO takeoutUserQResultVO = takeoutUserQueryVO.ok(takeoutUserList);
		takeoutUserQResultVO.setCount(count);
		return takeoutUserQResultVO;
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value="/saveTakeoutUser",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存")
	@RequiresPermissions("takeoutUser:saveTakeoutUser")
	@ApiOperation(value="保存",notes="注意问题点")
	@ResponseBody
	public TakeoutUserSResultVO saveTakeoutUser(@ApiParam("保存参数")@RequestBody(required=false) TakeoutUserSaveVO  takeoutUserSaveVO){
		ValidatorUtils.validateEntity(takeoutUserSaveVO);
		TakeoutUserEntity takeoutUserEntity = new TakeoutUserEntity();
		BeanCopierUtils.copyProperties(takeoutUserSaveVO, takeoutUserEntity);
	
		takeoutUserService.saveTakeoutUserEntity(takeoutUserEntity);
		
		return TakeoutUserSResultVO.ok();
	}
	
	/**
	 * 批量保存
	 */
	@RequestMapping(value="/saveTakeoutUserList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存")
	@RequiresPermissions("takeoutUser:saveTakeoutUserList")
	@ApiOperation(value="保存",notes="注意问题点")
	@ResponseBody
	public TakeoutUserSResultVO saveTakeoutUserList(@ApiParam("保存参数")@RequestBody(required=false) List<TakeoutUserSaveVO>  takeoutUserSaveVOList){
		ValidatorUtils.validateEntity(takeoutUserSaveVOList);
		
		TakeoutUserEntity entity = null;
		List<TakeoutUserEntity> entitys = new ArrayList<TakeoutUserEntity>(takeoutUserSaveVOList.size());
		for (TakeoutUserSaveVO tempVO : takeoutUserSaveVOList) {
			entity = new TakeoutUserEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		takeoutUserService.saveTakeoutUserEntity(entitys);
		
		return TakeoutUserSResultVO.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/editTakeoutUser",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新")
	@RequiresPermissions("takeoutUser:editTakeoutUser")
	@ApiOperation(value="更新",notes="注意问题点")
	@ResponseBody
	public TakeoutUserEResultVO editTakeoutUser(@ApiParam("更新参数")@RequestBody(required=false)TakeoutUserEditVO takeoutUserEditVO){
		ValidatorUtils.validateEntity(takeoutUserEditVO);
		TakeoutUserEntity takeoutUserEntity = new TakeoutUserEntity();
		BeanCopierUtils.copyProperties(takeoutUserEditVO, takeoutUserEntity);
	
		takeoutUserService.editTakeoutUserEntity(takeoutUserEntity);
		
		return  TakeoutUserEResultVO.ok();
	}
	
	/**
	 * 批量修改
	 */
	@RequestMapping(value="/updateTakeoutUserList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新")
	@RequiresPermissions("takeoutUser:editTakeoutUserList")
	@ApiOperation(value="更新",notes="注意问题点")
	@ResponseBody
	public TakeoutUserEResultVO editTakeoutUserList(@ApiParam("更新参数")@RequestBody(required=false) List<TakeoutUserEditVO> takeoutUserEditVOList){
		ValidatorUtils.validateEntity(takeoutUserEditVOList);
		TakeoutUserEntity entity = null;
		List<TakeoutUserEntity> entitys = new ArrayList<TakeoutUserEntity>(takeoutUserEditVOList.size());
		for (TakeoutUserEditVO tempVO : takeoutUserEditVOList) {
			entity = new TakeoutUserEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		takeoutUserService.editTakeoutUserEntity(entitys);
		
		return  TakeoutUserEResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteTakeoutUser",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除")
	@RequiresPermissions("takeoutUser:deleteTakeoutUser")
	@ApiOperation(value="删除",notes="注意问题点")
	@ResponseBody
	public TakeoutUserDResultVO deleteTakeoutUser(@ApiParam("更新参数") TakeoutUserDeleteVO takeoutUserDeleteVO){
		ValidatorUtils.validateEntity(takeoutUserDeleteVO);
		TakeoutUserEntity takeoutUserEntity = new TakeoutUserEntity();
		BeanCopierUtils.copyProperties( takeoutUserDeleteVO,takeoutUserEntity);
	
		takeoutUserService.removeTakeoutUserEntity(takeoutUserEntity);
		
		return TakeoutUserDResultVO.ok();
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteTakeoutUserList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除")
	@RequiresPermissions("takeoutUser:deleteTakeoutUserList")
	@ApiOperation(value="删除",notes="注意问题点")
	@ResponseBody
	public TakeoutUserDResultVO deleteTakeoutUserList(@ApiParam("更新参数") @RequestBody List<TakeoutUserDeleteVO> takeoutUserDeleteVOList){
		ValidatorUtils.validateEntity(takeoutUserDeleteVOList);
		TakeoutUserEntity entity = null;
		List<TakeoutUserEntity> entitys = new ArrayList<TakeoutUserEntity>(takeoutUserDeleteVOList.size());
		for (TakeoutUserDeleteVO tempVO : takeoutUserDeleteVOList) {
			entity = new TakeoutUserEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
		
		takeoutUserService.removeTakeoutUserEntity(entitys);
		
		return TakeoutUserDResultVO.ok();
	}
}
