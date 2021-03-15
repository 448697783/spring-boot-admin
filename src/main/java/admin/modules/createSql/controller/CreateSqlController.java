package admin.modules.createSql.controller;

import java.io.UnsupportedEncodingException;
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
import admin.modules.createSql.entity.CreateSqlEntity;
import admin.modules.createSql.vo.CreateSqlQueryVO;
import admin.modules.createSql.vo.CreateSqlQueryVO.*;
import admin.modules.createSql.service.ICreateSqlService;


//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '快速构建sql', 'modules/createSql/createSql.html', NULL, 1, 'fa fa-file-code-o', 1);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '查看', NULL, 'createSql:queryCreateSqlList', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '新增', NULL, 'createSql:saveCreateSql,createSql:saveCreateSqlList', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '修改', NULL, 'createSql:editCreateSql,createSql:editCreateSqlList', 2, NULL, 0);
//INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ( 1, '删除', NULL, 'createSql:deleteCreateSql,createSql:deleteCreateSqlList', 2, NULL, 0);


/**
 * 快速构建sql
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2020-06-04 00:01:06
 */
@RestController
@Api(value="快速构建sql",tags={"快速构建sql"})
@RequestMapping("createSql/")
public class CreateSqlController {
	@Autowired
	private ICreateSqlService createSqlService;

	/**
	 * 查询列表
	 */
	@RequestMapping(value="/queryCreateSqlList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("查询快速构建sql")
	@RequiresPermissions("createSql:queryCreateSqlList")
	@ApiOperation(value="查询快速构建sql",notes="注意问题点")
	@ResponseBody
	public CreateSqlQResultVO queryCreateSqlList(@ApiParam("查询快速构建sql参数")@RequestBody(required=false) CreateSqlQueryVO  createSqlQueryVO) throws UnsupportedEncodingException {
		ValidatorUtils.validateEntity(createSqlQueryVO);
		CreateSqlEntity createSqlEntity = new CreateSqlEntity();
		BeanCopierUtils.copyProperties(createSqlQueryVO, createSqlEntity);

		String sql = createSqlService.createSql(createSqlEntity);
		CreateSqlQResultVO createSqlQResultVO = new CreateSqlQResultVO("200","成功");
		createSqlQResultVO.setSql(sql);
		createSqlQResultVO.setCount(1L);
		return createSqlQResultVO;
	}
}
