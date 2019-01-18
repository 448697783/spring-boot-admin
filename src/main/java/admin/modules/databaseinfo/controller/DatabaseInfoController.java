package admin.modules.databaseinfo.controller;

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
import admin.modules.databaseinfo.entity.DatabaseInfoEntity;
import admin.modules.databaseinfo.vo.DatabaseInfoQueryVO;
import admin.modules.databaseinfo.vo.DatabaseInfoQueryVO.*;
import admin.modules.databaseinfo.vo.DatabaseInfoSaveVO;
import admin.modules.databaseinfo.vo.DatabaseInfoSaveVO.*;
import admin.modules.databaseinfo.vo.DatabaseInfoDeleteVO;
import admin.modules.databaseinfo.vo.DatabaseInfoDeleteVO.*;
import admin.modules.databaseinfo.vo.DatabaseInfoEditVO;
import admin.modules.databaseinfo.vo.DatabaseInfoEditVO.*;
import admin.modules.databaseinfo.service.IDatabaseInfoService;




/**
 * 数据库信息表
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-06-15 17:49:11
 */
@RestController
@Api(value="数据库信息表",tags={"数据库信息表"})
@RequestMapping("databaseinfo/")
public class DatabaseInfoController {
	@Autowired
	private IDatabaseInfoService databaseInfoService;
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value="/queryDatabaseInfoList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("查询数据库信息表")
	@ApiOperation(value="查询数据库信息表",notes="注意问题点")
	@ResponseBody
	public DatabaseInfoQResultVO queryDatabaseInfoList(@ApiParam("查询数据库信息表参数")DatabaseInfoQueryVO  databaseInfoQueryVO){
		ValidatorUtils.validateEntity(databaseInfoQueryVO);
		DatabaseInfoEntity databaseInfoEntity = new DatabaseInfoEntity();
		BeanCopierUtils.copyProperties(databaseInfoQueryVO, databaseInfoEntity);
		List<DatabaseInfoEntity> databaseInfoList = databaseInfoService.queryDatabaseInfoEntityListAll(databaseInfoEntity);
		
		DatabaseInfoQResultVO databaseInfoQResultVO = new DatabaseInfoQResultVO(databaseInfoList);
		
		return databaseInfoQResultVO;
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value="/saveDatabaseInfo",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存数据库信息表")
	@ApiOperation(value="保存数据库信息表",notes="注意问题点")
	@ResponseBody
	public DatabaseInfoSResultVO saveDatabaseInfo(@ApiParam("保存数据库信息表参数")@RequestBody(required=false) DatabaseInfoSaveVO  databaseInfoSaveVO){
		ValidatorUtils.validateEntity(databaseInfoSaveVO);
		DatabaseInfoEntity databaseInfoEntity = new DatabaseInfoEntity();
		BeanCopierUtils.copyProperties(databaseInfoSaveVO, databaseInfoEntity);
	
		databaseInfoService.saveDatabaseInfoEntity(databaseInfoEntity);
		
		return DatabaseInfoSResultVO.ok();
	}
	
	/**
	 * 批量保存
	 */
	@RequestMapping(value="/saveDatabaseInfoList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存数据库信息表")
	@ApiOperation(value="保存数据库信息表",notes="注意问题点")
	@ResponseBody
	public DatabaseInfoSResultVO saveDatabaseInfoList(@ApiParam("保存数据库信息表参数")@RequestBody(required=false) List<DatabaseInfoSaveVO>  databaseInfoSaveVOList){
		ValidatorUtils.validateEntity(databaseInfoSaveVOList);
		
		DatabaseInfoEntity entity = null;
		List<DatabaseInfoEntity> entitys = new ArrayList<DatabaseInfoEntity>(databaseInfoSaveVOList.size());
		for (DatabaseInfoSaveVO tempVO : databaseInfoSaveVOList) {
			entity = new DatabaseInfoEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		databaseInfoService.saveDatabaseInfoEntity(entitys);
		
		return DatabaseInfoSResultVO.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/editDatabaseInfo",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新数据库信息表")
	@ApiOperation(value="更新数据库信息表",notes="注意问题点")
	@ResponseBody
	public DatabaseInfoEResultVO editDatabaseInfo(@ApiParam("更新数据库信息表参数")@RequestBody(required=false)DatabaseInfoEditVO databaseInfoEditVO){
		ValidatorUtils.validateEntity(databaseInfoEditVO);
		DatabaseInfoEntity databaseInfoEntity = new DatabaseInfoEntity();
		BeanCopierUtils.copyProperties(databaseInfoEditVO, databaseInfoEntity);
	
		databaseInfoService.editDatabaseInfoEntity(databaseInfoEntity);
		
		return  DatabaseInfoEResultVO.ok();
	}
	
	/**
	 * 批量修改
	 */
	@RequestMapping(value="/updateDatabaseInfoList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新数据库信息表")
	@ApiOperation(value="更新数据库信息表",notes="注意问题点")
	@ResponseBody
	public DatabaseInfoEResultVO editDatabaseInfoList(@ApiParam("更新数据库信息表参数")@RequestBody(required=false) List<DatabaseInfoEditVO> databaseInfoEditVOList){
		ValidatorUtils.validateEntity(databaseInfoEditVOList);
		DatabaseInfoEntity entity = null;
		List<DatabaseInfoEntity> entitys = new ArrayList<DatabaseInfoEntity>(databaseInfoEditVOList.size());
		for (DatabaseInfoEditVO tempVO : databaseInfoEditVOList) {
			entity = new DatabaseInfoEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		databaseInfoService.editDatabaseInfoEntity(entitys);
		
		return  DatabaseInfoEResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteDatabaseInfo",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除数据库信息表")
	@ApiOperation(value="删除数据库信息表",notes="注意问题点")
	@ResponseBody
	public DatabaseInfoDResultVO deleteDatabaseInfo(@ApiParam("更新数据库信息表参数")DatabaseInfoDeleteVO databaseInfoDeleteVO){
		ValidatorUtils.validateEntity(databaseInfoDeleteVO);
		DatabaseInfoEntity databaseInfoEntity = new DatabaseInfoEntity();
		BeanCopierUtils.copyProperties( databaseInfoDeleteVO,databaseInfoEntity);
	
		databaseInfoService.removeDatabaseInfoEntity(databaseInfoEntity);
		
		return DatabaseInfoDResultVO.ok();
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteDatabaseInfoList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除数据库信息表")
	@ApiOperation(value="删除数据库信息表",notes="注意问题点")
	@ResponseBody
	public DatabaseInfoDResultVO deleteDatabaseInfoList(@ApiParam("更新数据库信息表参数")List<DatabaseInfoDeleteVO> databaseInfoDeleteVOList){
		ValidatorUtils.validateEntity(databaseInfoDeleteVOList);
		DatabaseInfoEntity entity = null;
		List<DatabaseInfoEntity> entitys = new ArrayList<DatabaseInfoEntity>(databaseInfoDeleteVOList.size());
		for (DatabaseInfoDeleteVO tempVO : databaseInfoDeleteVOList) {
			entity = new DatabaseInfoEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
		
		databaseInfoService.removeDatabaseInfoEntity(entitys);
		
		return DatabaseInfoDResultVO.ok();
	}
}
