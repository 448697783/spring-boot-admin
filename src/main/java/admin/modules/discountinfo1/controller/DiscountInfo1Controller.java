package admin.modules.discountinfo1.controller;

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
import admin.common.annotation.DataSource;
import admin.common.baseenum.DataSourceEnum;
import admin.modules.discountinfo1.entity.DiscountInfo1Entity;
import admin.modules.discountinfo1.vo.DiscountInfo1QueryVO;
import admin.modules.discountinfo1.vo.DiscountInfo1QueryVO.*;
import admin.modules.discountinfo1.vo.DiscountInfo1SaveVO;
import admin.modules.discountinfo1.vo.DiscountInfo1SaveVO.*;
import admin.modules.discountinfo1.vo.DiscountInfo1DeleteVO;
import admin.modules.discountinfo1.vo.DiscountInfo1DeleteVO.*;
import admin.modules.discountinfo1.vo.DiscountInfo1EditVO;
import admin.modules.discountinfo1.vo.DiscountInfo1EditVO.*;
import admin.modules.discountinfo1.service.IDiscountInfo1Service;


//INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (31, 1, '折扣活动信息表', 'modules/discountinfo1/discountInfo1.html', NULL, 1, 'fa fa-file-code-o', 1);
//INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (32, 31, '查看', NULL, 'discountinfo1:queryDiscountInfo1List', 2, NULL, 0);
//INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (33, 31, '新增', NULL, 'discountinfo1:saveDiscountInfo1', 2, NULL, 0);
//INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (34, 31, '修改', NULL, 'discountinfo1:editDiscountInfo1', 2, NULL, 0);
//INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (35, 31, '删除', NULL, 'discountinfo1:deleteDiscountInfo1', 2, NULL, 0);
/**
 * 折扣活动信息表
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-12 16:21:22
 */
@RestController
@Api(value="折扣活动信息表",tags={"折扣活动信息表"})
@RequestMapping("discountinfo1/")
public class DiscountInfo1Controller {
	@Autowired
	private IDiscountInfo1Service discountInfo1Service;

	/**
	 * 查询列表
	 */
	@RequestMapping(value="/queryDiscountInfo1List",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("查询折扣活动信息表")
	@ApiOperation(value="查询折扣活动信息表",notes="注意问题点")
	@ResponseBody
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	public DiscountInfo1QResultVO queryDiscountInfo1List(@ApiParam("查询折扣活动信息表参数")DiscountInfo1QueryVO  discountInfo1QueryVO){
		ValidatorUtils.validateEntity(discountInfo1QueryVO);
		DiscountInfo1Entity discountInfo1Entity = new DiscountInfo1Entity();
		BeanCopierUtils.copyProperties(discountInfo1QueryVO, discountInfo1Entity);
		List<DiscountInfo1Entity> discountInfo1List = discountInfo1Service.queryDiscountInfo1EntityListForPage(discountInfo1Entity,discountInfo1QueryVO.getStartRow(),discountInfo1QueryVO.getEndRow());
		//统计行数,如果数据量比较大可以才线程同时处理两个语句
		Long count= discountInfo1Service.queryDiscountInfo1EntityListForPageCount(discountInfo1Entity);
		DiscountInfo1QResultVO discountInfo1QResultVO = discountInfo1QueryVO.ok(discountInfo1List);
		discountInfo1QResultVO.setCount(count);
		return discountInfo1QResultVO;
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value="/saveDiscountInfo1",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存折扣活动信息表")
	@ApiOperation(value="保存折扣活动信息表",notes="注意问题点")
	@ResponseBody
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	public DiscountInfo1SResultVO saveDiscountInfo1(@ApiParam("保存折扣活动信息表参数")@RequestBody(required=false) DiscountInfo1SaveVO  discountInfo1SaveVO){
		ValidatorUtils.validateEntity(discountInfo1SaveVO);
		DiscountInfo1Entity discountInfo1Entity = new DiscountInfo1Entity();
		BeanCopierUtils.copyProperties(discountInfo1SaveVO, discountInfo1Entity);
	
		discountInfo1Service.saveDiscountInfo1Entity(discountInfo1Entity);
		
		return DiscountInfo1SResultVO.ok();
	}
	
	/**
	 * 批量保存
	 */
	@RequestMapping(value="/saveDiscountInfo1List",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存折扣活动信息表")
	@ApiOperation(value="保存折扣活动信息表",notes="注意问题点")
	@ResponseBody
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	public DiscountInfo1SResultVO saveDiscountInfo1List(@ApiParam("保存折扣活动信息表参数")@RequestBody(required=false) List<DiscountInfo1SaveVO>  discountInfo1SaveVOList){
		ValidatorUtils.validateEntity(discountInfo1SaveVOList);
		
		DiscountInfo1Entity entity = null;
		List<DiscountInfo1Entity> entitys = new ArrayList<DiscountInfo1Entity>(discountInfo1SaveVOList.size());
		for (DiscountInfo1SaveVO tempVO : discountInfo1SaveVOList) {
			entity = new DiscountInfo1Entity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		discountInfo1Service.saveDiscountInfo1Entity(entitys);
		
		return DiscountInfo1SResultVO.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/editDiscountInfo1",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新折扣活动信息表")
	@ApiOperation(value="更新折扣活动信息表",notes="注意问题点")
	@ResponseBody
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	public DiscountInfo1EResultVO editDiscountInfo1(@ApiParam("更新折扣活动信息表参数")@RequestBody(required=false)DiscountInfo1EditVO discountInfo1EditVO){
		ValidatorUtils.validateEntity(discountInfo1EditVO);
		DiscountInfo1Entity discountInfo1Entity = new DiscountInfo1Entity();
		BeanCopierUtils.copyProperties(discountInfo1EditVO, discountInfo1Entity);
	
		discountInfo1Service.editDiscountInfo1Entity(discountInfo1Entity);
		
		return  DiscountInfo1EResultVO.ok();
	}
	
	/**
	 * 批量修改
	 */
	@RequestMapping(value="/updateDiscountInfo1List",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新折扣活动信息表")
	@ApiOperation(value="更新折扣活动信息表",notes="注意问题点")
	@ResponseBody
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	public DiscountInfo1EResultVO editDiscountInfo1List(@ApiParam("更新折扣活动信息表参数")@RequestBody(required=false) List<DiscountInfo1EditVO> discountInfo1EditVOList){
		ValidatorUtils.validateEntity(discountInfo1EditVOList);
		DiscountInfo1Entity entity = null;
		List<DiscountInfo1Entity> entitys = new ArrayList<DiscountInfo1Entity>(discountInfo1EditVOList.size());
		for (DiscountInfo1EditVO tempVO : discountInfo1EditVOList) {
			entity = new DiscountInfo1Entity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		discountInfo1Service.editDiscountInfo1Entity(entitys);
		
		return  DiscountInfo1EResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteDiscountInfo1",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除折扣活动信息表")
	@ApiOperation(value="删除折扣活动信息表",notes="注意问题点")
	@ResponseBody
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	public DiscountInfo1DResultVO deleteDiscountInfo1(@ApiParam("更新折扣活动信息表参数")DiscountInfo1DeleteVO discountInfo1DeleteVO){
		ValidatorUtils.validateEntity(discountInfo1DeleteVO);
		DiscountInfo1Entity discountInfo1Entity = new DiscountInfo1Entity();
		BeanCopierUtils.copyProperties( discountInfo1DeleteVO,discountInfo1Entity);
	
		discountInfo1Service.removeDiscountInfo1Entity(discountInfo1Entity);
		
		return DiscountInfo1DResultVO.ok();
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteDiscountInfo1List",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除折扣活动信息表")
	@ApiOperation(value="删除折扣活动信息表",notes="注意问题点")
	@ResponseBody
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	public DiscountInfo1DResultVO deleteDiscountInfo1List(@ApiParam("更新折扣活动信息表参数")List<DiscountInfo1DeleteVO> discountInfo1DeleteVOList){
		ValidatorUtils.validateEntity(discountInfo1DeleteVOList);
		DiscountInfo1Entity entity = null;
		List<DiscountInfo1Entity> entitys = new ArrayList<DiscountInfo1Entity>(discountInfo1DeleteVOList.size());
		for (DiscountInfo1DeleteVO tempVO : discountInfo1DeleteVOList) {
			entity = new DiscountInfo1Entity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
		
		discountInfo1Service.removeDiscountInfo1Entity(entitys);
		
		return DiscountInfo1DResultVO.ok();
	}
}
