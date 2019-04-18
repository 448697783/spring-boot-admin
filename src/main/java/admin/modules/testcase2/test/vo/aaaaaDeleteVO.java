package admin.modules.testcase2.test.vo;
import admin.common.baseenum.BaseEnum.ReturnMsgEnum;
import admin.modules.testcase2.test.entity.aaaaaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import admin.common.utils.BeanCopierUtils;
import admin.common.utils.DateUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.math.BigDecimal;
/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-04-18 20:12:20
 */
@ApiModel("删除参数")
public class aaaaaDeleteVO {

	@Size(min=0,max=500,message="controller名称长度为0-500个字符")
	@ApiModelProperty(value="controller名称",example="填写例子")
	@ApiParam(value="controller名称",defaultValue="填写例子")
	private String controllerName;
	
	@Size(min=0,max=500,message="长度为0-500个字符")
	@ApiModelProperty(value="",example="填写例子")
	@ApiParam(value="",defaultValue="填写例子")
	private String describe;
	
	@Size(min=0,max=500,message="长度为0-500个字符")
	@ApiModelProperty(value="",example="填写例子")
	@ApiParam(value="",defaultValue="填写例子")
	private String method;
	
	@DecimalMin(value="0",message="主键值应大于0")
	@DecimalMax(value="9999999999999999999",message="主键值应小于9999999999999999999")
	@ApiModelProperty(value="主键",example="填写例子")
	@ApiParam(value="主键",defaultValue="填写例子")
	private Long testCaseId;
	
	@DecimalMin(value="0",message="值应大于0")
	@DecimalMax(value="9999999999999999999",message="值应小于9999999999999999999")
	@ApiModelProperty(value="",example="填写例子")
	@ApiParam(value="",defaultValue="填写例子")
	private Long deptId;
	
	@ApiModelProperty(value="创建时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
	@ApiParam(value="创建时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子")  
	private Date createdTime;
	
	@ApiModelProperty(value="更新时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
	@ApiParam(value="更新时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子")  
	private Date updatedTime;
	
	@ApiModelProperty(value="上次执行时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
	@ApiParam(value="上次执行时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子")  
	private Date execLastTime;
	
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
	 * 设置：
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	/**
	 * 获取：
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * 设置：
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	
	/**
	 * 获取：
	 */
	public String getMethod() {
		return method;
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
	 * 设置：
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	
	/**
	 * 获取：
	 */
	public Long getDeptId() {
		return deptId;
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
		builder.append("admin.modules.testcase2.test.vo.aaaaaDeleteVO ={")
		.append("controllerName=").append(controllerName)
		.append(",describe=").append(describe)
		.append(",method=").append(method)
		.append(",testCaseId=").append(testCaseId)
		.append(",deptId=").append(deptId)
		.append(",createdTime=").append(DateUtils.format(createdTime, DateUtils.DATE_TIME_PATTERN))
		.append(",updatedTime=").append(DateUtils.format(updatedTime, DateUtils.DATE_TIME_PATTERN))
		.append(",execLastTime=").append(DateUtils.format(execLastTime, DateUtils.DATE_TIME_PATTERN))
		.append(",status=").append(status)
		.append("}");
		return builder.toString();
	}
	
	public static enum aaaaaEnum{
		controller_name("controllerName","controller名称"),
		describe("describe",""),
		method("method",""),
		test_case_id("testCaseId","主键"),
		dept_id("deptId",""),
		created_time("createdTime","创建时间"),
		updated_time("updatedTime","更新时间"),
		exec_last_time("execLastTime","上次执行时间"),
		status("status","状态0:成功1:失败2:未执行");
		private String fieldName;
		private String remark;
		aaaaaEnum(String fieldName,String remark){
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
	
	@ApiModel("修改信息,返回执行结果")
	public static class aaaaaDResultVO {

		private static aaaaaDResultVO success = new aaaaaDResultVO(ReturnMsgEnum.SUCCESS.getCode(),ReturnMsgEnum.SUCCESS.getMsg());
		private static aaaaaDResultVO fail = new aaaaaDResultVO(ReturnMsgEnum.FAIL.getCode(),ReturnMsgEnum.FAIL.getMsg());
		@ApiModelProperty(value="状态码",allowableValues="200:成功,其他表示失败")
		private String code;
		@ApiModelProperty(value="返回修改结果信息")
		private String msg;
		
		public aaaaaDResultVO(String code,String msg){
			this.code = code;
			this.msg = msg;
		}
		
		@Override
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("admin.modules.testcase2.test.vo.aaaaaDResultVO =")
			.append("code=").append(code)
			.append(",msg=").append(msg)
			.append("}");
			return builder.toString();
		}
		
		public aaaaaDResultVO(){
		}
		
		public String getCode() {
			return code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
		public static aaaaaDResultVO ok() {
			return success;
		}
	
		public static aaaaaDResultVO  fail() {
			return fail;
		}
		
		public String getMsg() {
			return msg;
		}
	
		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
}
