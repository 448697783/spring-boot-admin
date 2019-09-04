package admin.modules.swagger2Url.controller;

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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import admin.common.validator.ValidatorUtils;
import admin.common.utils.BeanCopierUtils;
import admin.common.annotation.CtrlLog;
import admin.modules.swagger2Url.entity.Swagger2UrlEntity;
import admin.modules.swagger2Url.vo.Swagger2UrlQueryVO;
import admin.modules.swagger2Url.vo.Swagger2UrlQueryVO.*;
import admin.modules.swagger2Url.vo.Swagger2UrlSaveVO;
import admin.modules.swagger2Url.vo.Swagger2UrlSaveVO.*;
import admin.modules.swagger2Url.vo.Swagger2UrlDeleteVO;
import admin.modules.swagger2Url.vo.Swagger2UrlDeleteVO.*;
import admin.modules.swagger2Url.vo.Swagger2UrlEditVO;
import admin.modules.swagger2Url.vo.Swagger2UrlEditVO.*;
import admin.modules.swagger2Url.service.ISwagger2UrlService;


//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '', 'modules/swagger2Url/swagger2Url.html', NULL, 1, 'fa fa-file-code-o', 1);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '查看', NULL, 'swagger2Url:querySwagger2UrlList', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '新增', NULL, 'swagger2Url:saveSwagger2Url,swagger2Url:saveSwagger2UrlList', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '修改', NULL, 'swagger2Url:editSwagger2Url,swagger2Url:editSwagger2UrlList', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '删除', NULL, 'swagger2Url:deleteSwagger2Url,swagger2Url:deleteSwagger2UrlList', 2, NULL, 0);


/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-09-02 18:13:02
 */
@RestController
@Api(value="",tags={""})
@RequestMapping("swagger2Url/")
public class Swagger2UrlController {
	@Autowired
	private ISwagger2UrlService swagger2UrlService;

	/**
	 * 查询列表
	 */
	@RequestMapping(value="/querySwagger2UrlList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("查询")
	@RequiresPermissions("swagger2Url:querySwagger2UrlList")
	@ApiOperation(value="查询",notes="注意问题点")
	@ResponseBody
	public Swagger2UrlQResultVO querySwagger2UrlList(@ApiParam("查询参数")Swagger2UrlQueryVO  swagger2UrlQueryVO){
		ValidatorUtils.validateEntity(swagger2UrlQueryVO);
		Swagger2UrlEntity swagger2UrlEntity = new Swagger2UrlEntity();
		BeanCopierUtils.copyProperties(swagger2UrlQueryVO, swagger2UrlEntity);
		List<Swagger2UrlEntity> swagger2UrlList = swagger2UrlService.querySwagger2UrlEntityListForPage(swagger2UrlEntity,swagger2UrlQueryVO.getStartRow(),swagger2UrlQueryVO.getRows());
		//统计行数,如果数据量比较大可以才线程同时处理两个语句
		Long count= swagger2UrlService.querySwagger2UrlEntityListForPageCount(swagger2UrlEntity);
		Swagger2UrlQResultVO swagger2UrlQResultVO = swagger2UrlQueryVO.ok(swagger2UrlList);
		swagger2UrlQResultVO.setCount(count);
		return swagger2UrlQResultVO;
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value="/saveSwagger2Url",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存")
	@RequiresPermissions("swagger2Url:saveSwagger2Url")
	@ApiOperation(value="保存",notes="注意问题点")
	@ResponseBody
	public Swagger2UrlSResultVO saveSwagger2Url(@ApiParam("保存参数")@RequestBody(required=false) Swagger2UrlSaveVO  swagger2UrlSaveVO){
		ValidatorUtils.validateEntity(swagger2UrlSaveVO);
		Swagger2UrlEntity swagger2UrlEntity = new Swagger2UrlEntity();
		BeanCopierUtils.copyProperties(swagger2UrlSaveVO, swagger2UrlEntity);
	
		swagger2UrlService.saveSwagger2UrlEntity(swagger2UrlEntity);
		
		return Swagger2UrlSResultVO.ok();
	}
	
	/**
	 * 批量保存
	 */
	@RequestMapping(value="/saveSwagger2UrlList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存")
	@RequiresPermissions("swagger2Url:saveSwagger2UrlList")
	@ApiOperation(value="保存",notes="注意问题点")
	@ResponseBody
	public Swagger2UrlSResultVO saveSwagger2UrlList(@ApiParam("保存参数")@RequestBody(required=false) List<Swagger2UrlSaveVO>  swagger2UrlSaveVOList){
		ValidatorUtils.validateEntity(swagger2UrlSaveVOList);
		
		Swagger2UrlEntity entity = null;
		List<Swagger2UrlEntity> entitys = new ArrayList<Swagger2UrlEntity>(swagger2UrlSaveVOList.size());
		for (Swagger2UrlSaveVO tempVO : swagger2UrlSaveVOList) {
			entity = new Swagger2UrlEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		swagger2UrlService.saveSwagger2UrlEntity(entitys);
		
		return Swagger2UrlSResultVO.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/editSwagger2Url",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新")
	@RequiresPermissions("swagger2Url:editSwagger2Url")
	@ApiOperation(value="更新",notes="注意问题点")
	@ResponseBody
	public Swagger2UrlEResultVO editSwagger2Url(@ApiParam("更新参数")@RequestBody(required=false)Swagger2UrlEditVO swagger2UrlEditVO){
		ValidatorUtils.validateEntity(swagger2UrlEditVO);
		Swagger2UrlEntity swagger2UrlEntity = new Swagger2UrlEntity();
		BeanCopierUtils.copyProperties(swagger2UrlEditVO, swagger2UrlEntity);
	
		swagger2UrlService.editSwagger2UrlEntity(swagger2UrlEntity);
		
		return  Swagger2UrlEResultVO.ok();
	}
	
	/**
	 * 批量修改
	 */
	@RequestMapping(value="/updateSwagger2UrlList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新")
	@RequiresPermissions("swagger2Url:editSwagger2UrlList")
	@ApiOperation(value="更新",notes="注意问题点")
	@ResponseBody
	public Swagger2UrlEResultVO editSwagger2UrlList(@ApiParam("更新参数")@RequestBody(required=false) List<Swagger2UrlEditVO> swagger2UrlEditVOList){
		ValidatorUtils.validateEntity(swagger2UrlEditVOList);
		Swagger2UrlEntity entity = null;
		List<Swagger2UrlEntity> entitys = new ArrayList<Swagger2UrlEntity>(swagger2UrlEditVOList.size());
		for (Swagger2UrlEditVO tempVO : swagger2UrlEditVOList) {
			entity = new Swagger2UrlEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		swagger2UrlService.editSwagger2UrlEntity(entitys);
		
		return  Swagger2UrlEResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteSwagger2Url",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除")
	@RequiresPermissions("swagger2Url:deleteSwagger2Url")
	@ApiOperation(value="删除",notes="注意问题点")
	@ResponseBody
	public Swagger2UrlDResultVO deleteSwagger2Url(@ApiParam("更新参数") Swagger2UrlDeleteVO swagger2UrlDeleteVO){
		ValidatorUtils.validateEntity(swagger2UrlDeleteVO);
		Swagger2UrlEntity swagger2UrlEntity = new Swagger2UrlEntity();
		BeanCopierUtils.copyProperties( swagger2UrlDeleteVO,swagger2UrlEntity);
	
		swagger2UrlService.removeSwagger2UrlEntity(swagger2UrlEntity);
		
		return Swagger2UrlDResultVO.ok();
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteSwagger2UrlList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除")
	@RequiresPermissions("swagger2Url:deleteSwagger2UrlList")
	@ApiOperation(value="删除",notes="注意问题点")
	@ResponseBody
	public Swagger2UrlDResultVO deleteSwagger2UrlList(@ApiParam("更新参数") @RequestBody List<Swagger2UrlDeleteVO> swagger2UrlDeleteVOList){
		ValidatorUtils.validateEntity(swagger2UrlDeleteVOList);
		Swagger2UrlEntity entity = null;
		List<Swagger2UrlEntity> entitys = new ArrayList<Swagger2UrlEntity>(swagger2UrlDeleteVOList.size());
		for (Swagger2UrlDeleteVO tempVO : swagger2UrlDeleteVOList) {
			entity = new Swagger2UrlEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
		
		swagger2UrlService.removeSwagger2UrlEntity(entitys);
		
		return Swagger2UrlDResultVO.ok();
	}
}
