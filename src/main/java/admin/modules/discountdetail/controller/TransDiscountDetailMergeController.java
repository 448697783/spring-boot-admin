package admin.modules.discountdetail.controller;

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
import admin.modules.discountdetail.entity.TransDiscountDetailMergeEntity;
import admin.modules.discountdetail.vo.TransDiscountDetailMergeQueryVO;
import admin.modules.discountdetail.vo.TransDiscountDetailMergeQueryVO.*;
import admin.modules.discountdetail.service.ITransDiscountDetailMergeService;



/**
 * 
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-07 17:00:23
 */
@RestController
@Api(value="",tags={""})
@RequestMapping("discountdetail/")
public class TransDiscountDetailMergeController {
	@Autowired
	private ITransDiscountDetailMergeService transDiscountDetailMergeService;

	/**
	 * 查询列表(可分页)
	 */
	@RequestMapping(value="/queryTransDiscountDetailMergeList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("查询")
	@ApiOperation(value="查询",notes="注意问题点")
	@ResponseBody
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	public TransDiscountDetailMergeQResultVO queryTransDiscountDetailMergeList(@ApiParam("查询参数")TransDiscountDetailMergeQueryVO  transDiscountDetailMergeQueryVO){
		ValidatorUtils.validateEntity(transDiscountDetailMergeQueryVO);
		TransDiscountDetailMergeEntity transDiscountDetailMergeEntity = new TransDiscountDetailMergeEntity();
		BeanCopierUtils.copyProperties(transDiscountDetailMergeQueryVO, transDiscountDetailMergeEntity);
		List<TransDiscountDetailMergeEntity> transDiscountDetailMergeList = transDiscountDetailMergeService.queryTransDiscountDetailMergeEntityListForPage(transDiscountDetailMergeEntity,transDiscountDetailMergeQueryVO.getStartRow(),transDiscountDetailMergeQueryVO.getEndRow());
		//统计行数,如果数据量比较大可以才线程同时处理两个语句
		Long count= transDiscountDetailMergeService.queryTransDiscountDetailMergeEntityListForPageCount(transDiscountDetailMergeEntity);
		TransDiscountDetailMergeQResultVO transDiscountDetailMergeQResultVO = transDiscountDetailMergeQueryVO.ok(transDiscountDetailMergeList);
		transDiscountDetailMergeQResultVO.setCount(count);
		return transDiscountDetailMergeQResultVO;
	}
}
