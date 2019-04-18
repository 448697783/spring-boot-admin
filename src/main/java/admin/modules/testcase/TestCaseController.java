package admin.modules.testcase;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import admin.common.annotation.CtrlLog;
import admin.common.utils.ReturnInfo;
import admin.modules.sys.entity.SysUserEntity;
import admin.modules.testcase.entity.ParamterEntity;
import admin.modules.testcase.entity.TestCaseEntity;
import admin.modules.testcase.entity.TestCaseVO;
import admin.modules.testcase.service.impl.TestCaseService;
import admin.modules.testcase.service.impl.impl.ParamterServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;




/**
 * ${comments}
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2018-04-26 15:10:19
 */
//https://howtodoinjava.com/
@RestController
@Api(value="测试用例管理",tags={"测试用例管理"})
public class TestCaseController {
	
	@Autowired
	private TestCaseService testCaseService;
	
	@Autowired
	private ParamterServiceImpl paramterServiceImpl;
	
	
	
	@PostMapping("/testcase/save")
	@CtrlLog("保存测试用例")
    @ApiOperation(value="保存测试用例",notes="注意问题点")
	@ResponseBody
	@RequiresPermissions("testcase:save")
	public ReturnInfo save(@ApiParam("测试用例json参数")@RequestBody(required=false)TestCaseVO vo) throws IllegalAccessException, InvocationTargetException{
		TestCaseEntity entity = new TestCaseEntity();
		BeanUtils.copyProperties(entity, vo);
		testCaseService.save(entity);
		for (ParamterEntity paramterEntity : entity.getParamterEntity()) {
			paramterEntity.setId(Long.valueOf(entity.getTestCaseId()));
		}
		paramterServiceImpl.insertBath(entity.getParamterEntity());
		return ReturnInfo.ok();
	}
	
	@GetMapping("/testcase/getList")
	@CtrlLog("获取测试用例列表")
    @ApiOperation(value="获取测试用例列表",notes="注意问题点")
	@ResponseBody
	@RequiresPermissions("testcase:info")
	public List<TestCaseEntity> getList(@ApiParam("测试用例controller名称")@RequestParam() String controllerName,@RequestParam(required=false) String describe){
       
		List<TestCaseEntity> testCaseEntityList = testCaseService.getTestCaseEntity(controllerName,describe);
		for (TestCaseEntity testCaseEntity : testCaseEntityList) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", testCaseEntity.getTestCaseId());
			List<ParamterEntity> queryList = paramterServiceImpl.queryList(map);
			testCaseEntity.setParamterEntity(queryList);
		}
		return  testCaseEntityList;
	}
	
	@GetMapping("/testcase/delete")
	@CtrlLog("删除用例")
    @ApiOperation(value="删除用例",notes="注意问题点")
	@ResponseBody
	@RequiresPermissions("testcase:delete")
	@Transactional
	public ReturnInfo getDelete(@ApiParam("删除用例")@RequestParam(value="ids",required = false) String ids){
		for (String id : ids.split(",")) {
			testCaseService.delete(Long.parseLong(id));
			paramterServiceImpl.delete(Long.parseLong(id));
		}
		return  ReturnInfo.ok();
	}
	@ApiModel(parent=ReturnInfo.class)
	static class TestReturnInfo extends ReturnInfo {

		
		public TestReturnInfo(String code, String message) {
			super(code, message);
		}
		
		@ApiModelProperty(value="状态码",allowableValues="200:成功,999:参数错误")
		@Override
		public String getCode() {
			// TODO Auto-generated method stub
			return super.getCode();
		}
		
	}
}
