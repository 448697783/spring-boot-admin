package admin.modules.test.vo;
import admin.common.baseenum.BaseEnum.ReturnMessageEnum;
import admin.modules.test.entity.TestEntity;
import java.io.Serializable;
import java.util.ArrayList;
import admin.common.utils.BeanCopierUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.util.Date;
import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.math.BigDecimal;
/**
 * 测试用例
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-06-15 20:06:11
 */
@ApiModel("查询测试用例参数")
public class TestQueryVO {
	@Size(min=0,max=20,message="controller名称长度为0-20个字符")
//	@NotNull(message="controller名称不能为空")
	@ApiModelProperty(value="controller名称",example="填写例子")
	@ApiParam(value="controller名称",defaultValue="填写例子")
	private String controllerName;
	
//	@NotNull(message="主键不能为空")
	@Min(value=0,message="主键值应大于0")
	@Max(value=999999999999999999L,message="主键值应小于999999999999999999L")
	@ApiModelProperty(value="主键",example="填写例子")
	@ApiParam(value="主键",defaultValue="填写例子")
	private Long testCaseId;
	
//	@NotNull(message="创建时间不能为空")
	@ApiModelProperty(value="创建时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
	@ApiParam(value="创建时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子") 
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdTime;
	
//	@NotNull(message="更新时间不能为空")
	@ApiModelProperty(value="更新时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
	@ApiParam(value="更新时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子") 
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatedTime;
	
//	@NotNull(message="上次执行时间不能为空")
	@ApiModelProperty(value="上次执行时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
	@ApiParam(value="上次执行时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子") 
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date execLastTime;
	
//	@NotNull(message="状态0:成功1:失败2:未执行不能为空")
	@Min(value=0,message="状态0:成功1:失败2:未执行值应大于0")
	@Max(value=9999999999L,message="状态0:成功1:失败2:未执行值应小于9999999999L")
	@ApiModelProperty(value="状态0:成功1:失败2:未执行",example="填写例子")
	@ApiParam(value="状态0:成功1:失败2:未执行",defaultValue="填写例子")
	private Integer status;
	


	/**
	 * 设置：controller名称
	 */
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}
	
	/**
	 * 获取：controller名称
	 */
	public String getControllerName() {
		return controllerName;
	}

	/**
	 * 设置：主键
	 */
	public void setTestCaseId(Long testCaseId) {
		this.testCaseId = testCaseId;
	}
	
	/**
	 * 获取：主键
	 */
	public Long getTestCaseId() {
		return testCaseId;
	}

	/**
	 * 设置：创建时间
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	/**
	 * 获取：创建时间
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * 设置：更新时间
	 */
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	/**
	 * 获取：更新时间
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * 设置：上次执行时间
	 */
	public void setExecLastTime(Date execLastTime) {
		this.execLastTime = execLastTime;
	}
	
	/**
	 * 获取：上次执行时间
	 */
	public Date getExecLastTime() {
		return execLastTime;
	}

	/**
	 * 设置：状态0:成功1:失败2:未执行
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 获取：状态0:成功1:失败2:未执行
	 */
	public Integer getStatus() {
		return status;
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.entity.TestEntity [")
		.append(",controllerName=").append(controllerName)
		.append(",testCaseId=").append(testCaseId)
		.append(",createdTime=").append(createdTime)
		.append(",updatedTime=").append(updatedTime)
		.append(",execLastTime=").append(execLastTime)
		.append(",status=").append(status)
		.append("]");
		return builder.toString();
	}
	
	public static enum TestEnum{
		controller_name("controllerName","controller名称"),
		test_case_id("testCaseId","主键"),
		created_time("createdTime","创建时间"),
		updated_time("updatedTime","更新时间"),
		exec_last_time("execLastTime","上次执行时间"),
		status("status","状态0:成功1:失败2:未执行");
		private String fieldName;
		private String remark;
		TestEnum(String fieldName,String remark){
			this.fieldName = fieldName;
			this.remark = remark;
		}
		
		public String get(){
			return this.fieldName;
		}
		
		public String getRemark(){
			return this.remark;
		}
	}
	
	@ApiModel("查询测试用例结果集")
	public static class TestQResultVO {

		@ApiModelProperty(value="状态码",allowableValues="200:成功,其他表示失败")
		private String code;
		@ApiModelProperty(value="返回信息描述")
		private String message;
		
		public TestQResultVO(String code,String message){
			this.code = code;
			this.message = message;
		}
		public TestQResultVO(){
		}
		
		@ApiModelProperty(value="查询列表")
		private List<TestResult> resultList;
		public TestQResultVO(List<TestEntity> TestEntityList){
			this.code = ReturnMessageEnum.SUCCESS.getCode();
			this.message =ReturnMessageEnum.SUCCESS.getMessage();
			if(TestEntityList==null||TestEntityList.size()==0){
				return;
			}
			TestResult result = null;
			resultList = new ArrayList<TestResult>(TestEntityList.size());
			for (TestEntity tempEntity : TestEntityList) {
				result = new TestResult();
				BeanCopierUtils.copyProperties( tempEntity,result);
				resultList.add(result);
			}
		}
		
		public List<TestResult> getResultList() {
			return resultList;
		}
		
		public void setResultList(List<TestResult> resultList) {
			this.resultList = resultList;
		}
		
		public String getCode() {
			return code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
		public static TestQResultVO ok(List<TestEntity> TestEntityList) {
			return new TestQResultVO(TestEntityList);
		}
	
		public static TestQResultVO  fail() {
			 TestQResultVO vo = new TestQResultVO(null);
			 vo.setCode(ReturnMessageEnum.FAIL.getCode());
			 vo.setMessage(ReturnMessageEnum.FAIL.getMessage());
			 return vo;
		}
		
		public String getMessage() {
			return message;
		}
	
		public void setMessage(String message) {
			this.message = message;
		}
	}
		/**
	 * 回执测试用例说明
	 * 
	 */
	@ApiModel("返回测试用例字段说明")
	public static class TestResult {
		
		@ApiModelProperty(value="controller名称",example="填写例子")
		@ApiParam(value="controller名称",defaultValue="填写例子")
		private String controllerName;
		@ApiModelProperty(value="主键",example="填写例子")
		@ApiParam(value="主键",defaultValue="填写例子")
		private Long testCaseId;
		@ApiModelProperty(value="创建时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
		@ApiParam(value="创建时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子") 
		@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
		private Date createdTime;
		@ApiModelProperty(value="更新时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
		@ApiParam(value="更新时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子") 
		@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
		private Date updatedTime;
		@ApiModelProperty(value="上次执行时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
		@ApiParam(value="上次执行时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子") 
		@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
		private Date execLastTime;
		@ApiModelProperty(value="状态0:成功1:失败2:未执行",example="填写例子")
		@ApiParam(value="状态0:成功1:失败2:未执行",defaultValue="填写例子")
		private Integer status;
	
		
		/**
		 * 设置：controller名称
		 */
		public void setControllerName(String controllerName) {
			this.controllerName = controllerName;
		}
		
		/**
		 * 获取：controller名称
		 */
		public String getControllerName() {
			return controllerName;
		}
		
		/**
		 * 设置：主键
		 */
		public void setTestCaseId(Long testCaseId) {
			this.testCaseId = testCaseId;
		}
		
		/**
		 * 获取：主键
		 */
		public Long getTestCaseId() {
			return testCaseId;
		}
		
		/**
		 * 设置：创建时间
		 */
		public void setCreatedTime(Date createdTime) {
			this.createdTime = createdTime;
		}
		
		/**
		 * 获取：创建时间
		 */
		public Date getCreatedTime() {
			return createdTime;
		}
		
		/**
		 * 设置：更新时间
		 */
		public void setUpdatedTime(Date updatedTime) {
			this.updatedTime = updatedTime;
		}
		
		/**
		 * 获取：更新时间
		 */
		public Date getUpdatedTime() {
			return updatedTime;
		}
		
		/**
		 * 设置：上次执行时间
		 */
		public void setExecLastTime(Date execLastTime) {
			this.execLastTime = execLastTime;
		}
		
		/**
		 * 获取：上次执行时间
		 */
		public Date getExecLastTime() {
			return execLastTime;
		}
		
		/**
		 * 设置：状态0:成功1:失败2:未执行
		 */
		public void setStatus(Integer status) {
			this.status = status;
		}
		
		/**
		 * 获取：状态0:成功1:失败2:未执行
		 */
		public Integer getStatus() {
			return status;
		}
			@Override
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("admin.entity.TestEntity [")
					.append(",controllerName=").append(controllerName)
						.append(",testCaseId=").append(testCaseId)
						.append(",createdTime=").append(createdTime)
						.append(",updatedTime=").append(updatedTime)
						.append(",execLastTime=").append(execLastTime)
						.append(",status=").append(status)
					.append("]");
			return builder.toString();
		}
	}
}
