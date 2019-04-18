package admin.modules.test.controller;

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
import admin.modules.test.entity.TestEntity;
import admin.modules.test.vo.TestQueryVO;
import admin.modules.test.vo.TestQueryVO.*;
import admin.modules.test.vo.TestSaveVO;
import admin.modules.test.vo.TestSaveVO.*;
import admin.modules.test.vo.TestDeleteVO;
import admin.modules.test.vo.TestDeleteVO.*;
import admin.modules.test.vo.TestEditVO;
import admin.modules.test.vo.TestEditVO.*;
import admin.modules.test.service.ITestService;




/**
 * 测试用例
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2018-06-15 20:06:11
 */
@RestController
@Api(value="测试用例",tags={"测试用例"})
@RequestMapping("test/")
public class TestController {
	@Autowired
	private ITestService TestService;
	
	/**
	 * 查询列表
	 */
	@RequestMapping(value="/queryTestList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("查询测试用例")
	@ApiOperation(value="查询测试用例",notes="注意问题点")
	@ResponseBody
	public TestQResultVO queryTestList(@ApiParam("查询测试用例参数")TestQueryVO  TestQueryVO){
		ValidatorUtils.validateEntity(TestQueryVO);
		TestEntity TestEntity = new TestEntity();
		BeanCopierUtils.copyProperties(TestQueryVO, TestEntity);
		List<TestEntity> TestList = TestService.queryTestEntityListAll(TestEntity);
		
		TestQResultVO TestQResultVO = new TestQResultVO(TestList);
		
		return TestQResultVO;
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping(value="/saveTest",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存测试用例")
	@ApiOperation(value="保存测试用例",notes="注意问题点")
	@ResponseBody
	public TestSResultVO saveTest(@ApiParam("保存测试用例参数")@RequestBody(required=false) TestSaveVO  TestSaveVO){
		ValidatorUtils.validateEntity(TestSaveVO);
		TestEntity TestEntity = new TestEntity();
		BeanCopierUtils.copyProperties(TestSaveVO, TestEntity);
	
		TestService.saveTestEntity(TestEntity);
		
		return TestSResultVO.ok();
	}
	
	/**
	 * 批量保存
	 */
	@RequestMapping(value="/saveTestList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("保存测试用例")
	@ApiOperation(value="保存测试用例",notes="注意问题点")
	@ResponseBody
	public TestSResultVO saveTestList(@ApiParam("保存测试用例参数")@RequestBody(required=false) List<TestSaveVO>  TestSaveVOList){
		ValidatorUtils.validateEntity(TestSaveVOList);
		
		TestEntity entity = null;
		List<TestEntity> entitys = new ArrayList<TestEntity>(TestSaveVOList.size());
		for (TestSaveVO tempVO : TestSaveVOList) {
			entity = new TestEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		TestService.saveTestEntity(entitys);
		
		return TestSResultVO.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/editTest",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新测试用例")
	@ApiOperation(value="更新测试用例",notes="注意问题点")
	@ResponseBody
	public TestEResultVO editTest(@ApiParam("更新测试用例参数")@RequestBody(required=false)TestEditVO TestEditVO){
		ValidatorUtils.validateEntity(TestEditVO);
		TestEntity TestEntity = new TestEntity();
		BeanCopierUtils.copyProperties(TestEditVO, TestEntity);
	
		TestService.editTestEntity(TestEntity);
		
		return  TestEResultVO.ok();
	}
	
	/**
	 * 批量修改
	 */
	@RequestMapping(value="/updateTestList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("更新测试用例")
	@ApiOperation(value="更新测试用例",notes="注意问题点")
	@ResponseBody
	public TestEResultVO editTestList(@ApiParam("更新测试用例参数")@RequestBody(required=false) List<TestEditVO> TestEditVOList){
		ValidatorUtils.validateEntity(TestEditVOList);
		TestEntity entity = null;
		List<TestEntity> entitys = new ArrayList<TestEntity>(TestEditVOList.size());
		for (TestEditVO tempVO : TestEditVOList) {
			entity = new TestEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
	
		TestService.editTestEntity(entitys);
		
		return  TestEResultVO.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteTest",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除测试用例")
	@ApiOperation(value="删除测试用例",notes="注意问题点")
	@ResponseBody
	public TestDResultVO deleteTest(@ApiParam("更新测试用例参数")TestDeleteVO TestDeleteVO){
		ValidatorUtils.validateEntity(TestDeleteVO);
		TestEntity TestEntity = new TestEntity();
		BeanCopierUtils.copyProperties( TestDeleteVO,TestEntity);
	
		TestService.removeTestEntity(TestEntity);
		
		return TestDResultVO.ok();
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteTestList",method= {RequestMethod.POST,RequestMethod.GET})
	@CtrlLog("删除测试用例")
	@ApiOperation(value="删除测试用例",notes="注意问题点")
	@ResponseBody
	public TestDResultVO deleteTestList(@ApiParam("更新测试用例参数")List<TestDeleteVO> TestDeleteVOList){
		ValidatorUtils.validateEntity(TestDeleteVOList);
		TestEntity entity = null;
		List<TestEntity> entitys = new ArrayList<TestEntity>(TestDeleteVOList.size());
		for (TestDeleteVO tempVO : TestDeleteVOList) {
			entity = new TestEntity();
			BeanCopierUtils.copyProperties( tempVO,entity);
			entitys.add(entity);
		}
		
		TestService.removeTestEntity(entitys);
		
		return TestDResultVO.ok();
	}
}
