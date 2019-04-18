package admin.modules.testcase2.test.controller;

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
import admin.modules.testcase2.test.entity.aaaaaEntity;
import admin.modules.testcase2.test.vo.aaaaaQueryVO;
import admin.modules.testcase2.test.vo.aaaaaQueryVO.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import admin.modules.testcase2.test.service.IaaaaaService;

//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '', 'modules/testcase2/test/aaaaa.html', NULL, 1, 'fa fa-file-code-o', 1);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '查看', NULL, 'testcase2:test:queryaaaaaList', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '新增', NULL, 'testcase2:test:saveaaaaa,testcase2:test:saveaaaaaList', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '修改', NULL, 'testcase2:test:editaaaaa,testcase2:test:editaaaaaList', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '删除', NULL, 'testcase2:test:deleteaaaaa,testcase2:test:deleteaaaaaList', 2, NULL, 0);

/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-04-18 20:12:20
 */
@RestController
@Api(value="",tags={""})
@RequestMapping("testcase2/test/")
public class aaaaaController {
	@Autowired
	private IaaaaaService aaaaaService;

	/**
	 * 查询列表(可分页)
	 */
	@RequestMapping(value="/queryaaaaaList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("查询")
	@ApiOperation(value="查询",notes="注意问题点")
	@RequiresPermissions("testcase2:test:queryaaaaaList")
	@ResponseBody
	public aaaaaQResultVO queryaaaaaList(@ApiParam("查询参数")aaaaaQueryVO  aaaaaQueryVO){
		ValidatorUtils.validateEntity(aaaaaQueryVO);
		aaaaaEntity aaaaaEntity = new aaaaaEntity();
		BeanCopierUtils.copyProperties(aaaaaQueryVO, aaaaaEntity);
		List<aaaaaEntity> aaaaaList = aaaaaService.queryaaaaaEntityListForPage(aaaaaEntity,aaaaaQueryVO.getStartRow(),aaaaaQueryVO.getEndRow());
		//统计行数,如果数据量比较大可以才线程同时处理两个语句
		Long count= aaaaaService.queryaaaaaEntityListForPageCount(aaaaaEntity);
		aaaaaQResultVO aaaaaQResultVO = aaaaaQueryVO.ok(aaaaaList);
		aaaaaQResultVO.setCount(count);
		return aaaaaQResultVO;
	}
}
