package admin.modules.testcase1.vo;
import admin.common.baseenum.BaseEnum.ReturnMessageEnum;
import admin.modules.testcase1.entity.Testcase1Entity;
import java.io.Serializable;
import java.util.ArrayList;
import admin.common.utils.BeanCopierUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.Timestamp;
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
 * 测试用例
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2019-01-27 22:30:03
 */
@ApiModel("修改测试用例参数")
public class Testcase1EditVO {

	@Size(min=0,max=20,message="controller名称长度为0-20个字符")
	
	@ApiModelProperty(value="controller名称",example="填写例子")
	@ApiParam(value="controller名称",defaultValue="填写例子")
	private String controllerName;
	
	@NotNull(message="主键不能为空")
	@Min(value=0,message="主键值应大于0")
	@Max(value=999999999999999999L,message="主键值应小于999999999999999999L")
	@ApiModelProperty(value="主键",example="填写例子")
	@ApiParam(value="主键",defaultValue="填写例子")
	private Long testCaseId;
	
	@NotNull(message="创建时间不能为空")
	@ApiModelProperty(value="创建时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
	@ApiParam(value="创建时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子")  
	private Date createdTime;
	
	@NotNull(message="更新时间不能为空")
	@ApiModelProperty(value="更新时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
	@ApiParam(value="更新时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子")  
	private Date updatedTime;
	
	@NotNull(message="上次执行时间不能为空")
	@ApiModelProperty(value="上次执行时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
	@ApiParam(value="上次执行时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子")  
	private Date execLastTime;
	
	@NotNull(message="状态0:成功1:失败2:未执行不能为空")
	@Min(value=0,message="状态0:成功1:失败2:未执行值应大于0")
	@Max(value=9999999999L,message="状态0:成功1:失败2:未执行值应小于9999999999L")
	@ApiModelProperty(value="状态0:成功1:失败2:未执行",example="填写例子")
	@ApiParam(value="状态0:成功1:失败2:未执行",defaultValue="填写例子")
	private Integer status;
	
	@NotNull(message="a不能为空")
	@DecimalMin(value="0",message="a值应大于0")
	@DecimalMax(value="999999999999",message="a值应小于999999999999")
	@ApiModelProperty(value="a",example="填写例子")
	@ApiParam(value="a",defaultValue="填写例子")
	private BigDecimal a;
	
	@NotNull(message="b不能为空")
	@DecimalMin(value="0",message="b值应大于0")
	@DecimalMax(value="999999999999999999",message="b值应小于999999999999999999")
	@ApiModelProperty(value="b",example="填写例子")
	@ApiParam(value="b",defaultValue="填写例子")
	private BigDecimal b;
	
	@NotNull(message="c不能为空")
	@DecimalMin(value="0",message="c值应大于0")
	@DecimalMax(value="9999999999",message="c值应小于9999999999")
	@ApiModelProperty(value="c",example="填写例子")
	@ApiParam(value="c",defaultValue="填写例子")
	private BigDecimal c;
	
	@Size(min=0,max=65535,message="d长度为0-65535个字符")
	@NotNull(message="d不能为空")
	@ApiModelProperty(value="d",example="填写例子")
	@ApiParam(value="d",defaultValue="填写例子")
	private String d;
	


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

	/**
	 * 设置：a
	 */
	public void setA(BigDecimal a) {
		this.a = a;
	}
	
	/**
	 * 获取：a
	 */
	public BigDecimal getA() {
		return a;
	}

	/**
	 * 设置：b
	 */
	public void setB(BigDecimal b) {
		this.b = b;
	}
	
	/**
	 * 获取：b
	 */
	public BigDecimal getB() {
		return b;
	}

	/**
	 * 设置：c
	 */
	public void setC(BigDecimal c) {
		this.c = c;
	}
	
	/**
	 * 获取：c
	 */
	public BigDecimal getC() {
		return c;
	}

	/**
	 * 设置：d
	 */
	public void setD(String d) {
		this.d = d;
	}
	
	/**
	 * 获取：d
	 */
	public String getD() {
		return d;
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.entity.Testcase1Entity [")
		.append(",controllerName=").append(controllerName)
		.append(",testCaseId=").append(testCaseId)
		.append(",createdTime=").append(createdTime)
		.append(",updatedTime=").append(updatedTime)
		.append(",execLastTime=").append(execLastTime)
		.append(",status=").append(status)
		.append(",a=").append(a)
		.append(",b=").append(b)
		.append(",c=").append(c)
		.append(",d=").append(d)
		.append("]");
		return builder.toString();
	}
	
	public static enum Testcase1Enum{
		controller_name("controllerName","controller名称"),
		test_case_id("testCaseId","主键"),
		created_time("createdTime","创建时间"),
		updated_time("updatedTime","更新时间"),
		exec_last_time("execLastTime","上次执行时间"),
		status("status","状态0:成功1:失败2:未执行"),
		a("a","a"),
		b("b","b"),
		c("c","c"),
		d("d","d");
		private String fieldName;
		private String remark;
		Testcase1Enum(String fieldName,String remark){
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
	
	@ApiModel("修改测试用例信息,返回执行结果")
	public static class Testcase1EResultVO {

		private static Testcase1EResultVO success = new Testcase1EResultVO(ReturnMessageEnum.SUCCESS.getCode(),ReturnMessageEnum.SUCCESS.getMessage());
		private static Testcase1EResultVO fail = new Testcase1EResultVO(ReturnMessageEnum.FAIL.getCode(),ReturnMessageEnum.FAIL.getMessage());
		@ApiModelProperty(value="状态码",allowableValues="200:成功,其他表示失败")
		private String code;
		@ApiModelProperty(value="返回修改结果信息")
		private String message;
		
		public Testcase1EResultVO(String code,String message){
			this.code = code;
			this.message = message;
		}
		
		public Testcase1EResultVO(){
		}
		
		public String getCode() {
			return code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
		public static Testcase1EResultVO ok() {
			return success;
		}
	
		public static Testcase1EResultVO fail() {
			return fail;
		}
		
		public String getMessage() {
			return message;
		}
	
		public void setMessage(String message) {
			this.message = message;
		}
	}
}
