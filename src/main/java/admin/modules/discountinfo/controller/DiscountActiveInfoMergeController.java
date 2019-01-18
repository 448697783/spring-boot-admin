package admin.modules.discountinfo.controller;

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
import admin.modules.discountinfo.entity.DiscountActiveInfoMergeEntity;
import admin.modules.discountinfo.vo.DiscountActiveInfoMergeQueryVO;
import admin.modules.discountinfo.vo.DiscountActiveInfoMergeQueryVO.*;
import admin.modules.discountinfo.service.IDiscountActiveInfoMergeService;



/**
 * 
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-07 15:19:14
 */
@RestController
@Api(value="",tags={""})
@RequestMapping("discountinfo/")
public class DiscountActiveInfoMergeController {
	@Autowired
	private IDiscountActiveInfoMergeService discountActiveInfoMergeService;

	/**
	 * 查询列表(可分页)
	 */
	@RequestMapping(value="/queryDiscountActiveInfoMergeList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("查询")
	@ApiOperation(value="查询",notes="注意问题点")
	@ResponseBody
	@DataSource(DataSourceEnum.DATASOURCE_SLAVE)
	public DiscountActiveInfoMergeQResultVO queryDiscountActiveInfoMergeList(@ApiParam("查询参数")DiscountActiveInfoMergeQueryVO  discountActiveInfoMergeQueryVO){
		ValidatorUtils.validateEntity(discountActiveInfoMergeQueryVO);
		DiscountActiveInfoMergeEntity discountActiveInfoMergeEntity = new DiscountActiveInfoMergeEntity();
		BeanCopierUtils.copyProperties(discountActiveInfoMergeQueryVO, discountActiveInfoMergeEntity);
		List<DiscountActiveInfoMergeEntity> discountActiveInfoMergeList = discountActiveInfoMergeService.queryDiscountActiveInfoMergeEntityListForPage(discountActiveInfoMergeEntity,discountActiveInfoMergeQueryVO.getStartRow(),discountActiveInfoMergeQueryVO.getEndRow());
		//统计行数,如果数据量比较大可以才线程同时处理两个语句
		Long count= discountActiveInfoMergeService.queryDiscountActiveInfoMergeEntityListForPageCount(discountActiveInfoMergeEntity);
		DiscountActiveInfoMergeQResultVO discountActiveInfoMergeQResultVO = discountActiveInfoMergeQueryVO.ok(discountActiveInfoMergeList);
		discountActiveInfoMergeQResultVO.setCount(count);
		return discountActiveInfoMergeQResultVO;
	}
}
