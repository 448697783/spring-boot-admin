package admin.modules.discountinfo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import admin.common.annotation.CtrlLog;
import admin.common.annotation.DataSource;
import admin.common.baseenum.DataSourceEnum;
import admin.common.utils.BeanCopierUtils;
import admin.common.validator.ValidatorUtils;
import admin.modules.discountinfo.entity.DiscountActiveInfoEntity;
import admin.modules.discountinfo.service.IDiscountActiveInfoService;
import admin.modules.discountinfo.vo.DiscountActiveInfoDeleteVO;
import admin.modules.discountinfo.vo.DiscountActiveInfoDeleteVO.DiscountActiveInfoDResultVO;
import admin.modules.discountinfo.vo.DiscountActiveInfoEditVO;
import admin.modules.discountinfo.vo.DiscountActiveInfoEditVO.DiscountActiveInfoEResultVO;
import admin.modules.discountinfo.vo.DiscountActiveInfoQueryVO;
import admin.modules.discountinfo.vo.DiscountActiveInfoQueryVO.DiscountActiveInfoQResultVO;
import admin.modules.discountinfo.vo.DiscountActiveInfoSaveVO;
import admin.modules.discountinfo.vo.DiscountActiveInfoSaveVO.DiscountActiveInfoSResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;




/**
 * 折扣活动信息表
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 15:41:29
 */
@RestController
@Api(value="折扣活动信息表",tags={"折扣活动信息表"})
@RequestMapping("discountinfo/")
public class DiscountActiveInfoController {
	@Autowired
	private IDiscountActiveInfoService discountActiveInfoService;
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value="/queryDiscountActiveInfoList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("查询折扣活动信息表")
	@ApiOperation(value="查询折扣活动信息表",notes="注意问题点")
	@ResponseBody
	public DiscountActiveInfoQResultVO queryDiscountActiveInfoList(@ApiParam("查询折扣活动信息表参数")DiscountActiveInfoQueryVO  discountActiveInfoQueryVO){
		ValidatorUtils.validateEntity(discountActiveInfoQueryVO);
		DiscountActiveInfoEntity discountActiveInfoEntity = new DiscountActiveInfoEntity();
		BeanCopierUtils.copyProperties(discountActiveInfoQueryVO, discountActiveInfoEntity);
		List<DiscountActiveInfoEntity> discountActiveInfoList = discountActiveInfoService.queryDiscountActiveInfoEntityListAll(discountActiveInfoEntity);
		
		DiscountActiveInfoQResultVO discountActiveInfoQResultVO = new DiscountActiveInfoQResultVO(discountActiveInfoList);
		
		return discountActiveInfoQResultVO;
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value="/saveDiscountActiveInfo",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存折扣活动信息表")
	@ApiOperation(value="保存折扣活动信息表",notes="注意问题点")
	@ResponseBody
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	public DiscountActiveInfoSResultVO saveDiscountActiveInfo(@ApiParam("保存折扣活动信息表参数")@RequestBody(required=false) DiscountActiveInfoSaveVO  discountActiveInfoSaveVO){
		ValidatorUtils.validateEntity(discountActiveInfoSaveVO);
		DiscountActiveInfoEntity discountActiveInfoEntity = new DiscountActiveInfoEntity();
		BeanCopierUtils.copyProperties(discountActiveInfoSaveVO, discountActiveInfoEntity);
	
		discountActiveInfoService.saveDiscountActiveInfoEntity(discountActiveInfoEntity);
		
		return DiscountActiveInfoSResultVO.ok();
	}
	
	/**
	 * 批量保存
	 */
	@RequestMapping(value="/saveDiscountActiveInfoList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存折扣活动信息表")
	@ApiOperation(value="保存折扣活动信息表",notes="注意问题点")
	@ResponseBody
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	public DiscountActiveInfoSResultVO saveDiscountActiveInfoList(@ApiParam("保存折扣活动信息表参数")@RequestBody(required=false) List<DiscountActiveInfoSaveVO>  discountActiveInfoSaveVOList){
		ValidatorUtils.validateEntity(discountActiveInfoSaveVOList);
		
		DiscountActiveInfoEntity entity = null;
		List<DiscountActiveInfoEntity> entitys = new ArrayList<DiscountActiveInfoEntity>(discountActiveInfoSaveVOList.size());
		for (DiscountActiveInfoSaveVO tempVO : discountActiveInfoSaveVOList) {
			entity = new DiscountActiveInfoEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		discountActiveInfoService.saveDiscountActiveInfoEntity(entitys);
		
		return DiscountActiveInfoSResultVO.ok();
	}
	
	/**
	 * 修改
	 */
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	@RequestMapping(value="/editDiscountActiveInfo",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新折扣活动信息表")
	@ApiOperation(value="更新折扣活动信息表",notes="注意问题点")
	@ResponseBody
	public DiscountActiveInfoEResultVO editDiscountActiveInfo(@ApiParam("更新折扣活动信息表参数")@RequestBody(required=false)DiscountActiveInfoEditVO discountActiveInfoEditVO){
		ValidatorUtils.validateEntity(discountActiveInfoEditVO);
		DiscountActiveInfoEntity discountActiveInfoEntity = new DiscountActiveInfoEntity();
		BeanCopierUtils.copyProperties(discountActiveInfoEditVO, discountActiveInfoEntity);
	
		discountActiveInfoService.editDiscountActiveInfoEntity(discountActiveInfoEntity);
		
		return  DiscountActiveInfoEResultVO.ok();
	}
	
	/**
	 * 批量修改
	 */
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	@RequestMapping(value="/updateDiscountActiveInfoList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新折扣活动信息表")
	@ApiOperation(value="更新折扣活动信息表",notes="注意问题点")
	@ResponseBody
	public DiscountActiveInfoEResultVO editDiscountActiveInfoList(@ApiParam("更新折扣活动信息表参数")@RequestBody(required=false) List<DiscountActiveInfoEditVO> discountActiveInfoEditVOList){
		ValidatorUtils.validateEntity(discountActiveInfoEditVOList);
		DiscountActiveInfoEntity entity = null;
		List<DiscountActiveInfoEntity> entitys = new ArrayList<DiscountActiveInfoEntity>(discountActiveInfoEditVOList.size());
		for (DiscountActiveInfoEditVO tempVO : discountActiveInfoEditVOList) {
			entity = new DiscountActiveInfoEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		discountActiveInfoService.editDiscountActiveInfoEntity(entitys);
		
		return  DiscountActiveInfoEResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteDiscountActiveInfo",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除折扣活动信息表")
	@ApiOperation(value="删除折扣活动信息表",notes="注意问题点")
	@ResponseBody
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	public DiscountActiveInfoDResultVO deleteDiscountActiveInfo(@ApiParam("更新折扣活动信息表参数")DiscountActiveInfoDeleteVO discountActiveInfoDeleteVO){
		ValidatorUtils.validateEntity(discountActiveInfoDeleteVO);
		DiscountActiveInfoEntity discountActiveInfoEntity = new DiscountActiveInfoEntity();
		BeanCopierUtils.copyProperties( discountActiveInfoDeleteVO,discountActiveInfoEntity);
	
		discountActiveInfoService.removeDiscountActiveInfoEntity(discountActiveInfoEntity);
		
		return DiscountActiveInfoDResultVO.ok();
	}
	
	/**
	 * 批量删除
	 */
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	@RequestMapping(value="/deleteDiscountActiveInfoList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除折扣活动信息表")
	@ApiOperation(value="删除折扣活动信息表",notes="注意问题点")
	@ResponseBody
	public DiscountActiveInfoDResultVO deleteDiscountActiveInfoList(@ApiParam("更新折扣活动信息表参数")List<DiscountActiveInfoDeleteVO> discountActiveInfoDeleteVOList){
		ValidatorUtils.validateEntity(discountActiveInfoDeleteVOList);
		DiscountActiveInfoEntity entity = null;
		List<DiscountActiveInfoEntity> entitys = new ArrayList<DiscountActiveInfoEntity>(discountActiveInfoDeleteVOList.size());
		for (DiscountActiveInfoDeleteVO tempVO : discountActiveInfoDeleteVOList) {
			entity = new DiscountActiveInfoEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
		
		discountActiveInfoService.removeDiscountActiveInfoEntity(entitys);
		
		return DiscountActiveInfoDResultVO.ok();
	}
	
	
}
