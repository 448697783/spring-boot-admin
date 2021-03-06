package admin.modules.databaseinfo.vo;
import admin.common.baseenum.BaseEnum.ReturnMsgEnum;
import admin.modules.databaseinfo.entity.DatabaseInfoEntity;
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
 * 数据库信息表
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2018-06-15 17:49:11
 */
@ApiModel("修改数据库信息表参数")
public class DatabaseInfoEditVO {

	@Size(min=0,max=50,message="别名长度为0-50个字符")
	@NotNull(message="别名不能为空")
	@ApiModelProperty(value="别名",example="填写例子")
	@ApiParam(value="别名",defaultValue="填写例子")
	private String aliase;
	
	@Size(min=0,max=100,message="数据库url长度为0-100个字符")
	@NotNull(message="数据库url不能为空")
	@ApiModelProperty(value="数据库url",example="填写例子")
	@ApiParam(value="数据库url",defaultValue="填写例子")
	private String url;
	
	@Size(min=0,max=100,message="密码长度为0-100个字符")
	@NotNull(message="密码不能为空")
	@ApiModelProperty(value="密码",example="填写例子")
	@ApiParam(value="密码",defaultValue="填写例子")
	private String passWord;
	
	@Size(min=0,max=100,message="用户名长度为0-100个字符")
	@NotNull(message="用户名不能为空")
	@ApiModelProperty(value="用户名",example="填写例子")
	@ApiParam(value="用户名",defaultValue="填写例子")
	private String userName;
	
	@Size(min=0,max=100,message="数据驱动长度为0-100个字符")
	@NotNull(message="数据驱动不能为空")
	@ApiModelProperty(value="数据驱动",example="填写例子")
	@ApiParam(value="数据驱动",defaultValue="填写例子")
	private String driverClassName;
	


	/**
	 * 设置：别名
	 */
	public void setAliase(String aliase) {
		this.aliase = aliase;
	}
	
	/**
	 * 获取：别名
	 */
	public String getAliase() {
		return aliase;
	}

	/**
	 * 设置：数据库url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 获取：数据库url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置：密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	/**
	 * 获取：密码
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 获取：用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置：数据驱动
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	
	/**
	 * 获取：数据驱动
	 */
	public String getDriverClassName() {
		return driverClassName;
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.entity.DatabaseInfoEntity [")
		.append(",aliase=").append(aliase)
		.append(",url=").append(url)
		.append(",passWord=").append(passWord)
		.append(",userName=").append(userName)
		.append(",driverClassName=").append(driverClassName)
		.append("]");
		return builder.toString();
	}
	
	public static enum DatabaseInfoEnum{
		aliase("aliase","别名"),
		url("url","数据库url"),
		pass_word("passWord","密码"),
		user_name("userName","用户名"),
		driver_class_name("driverClassName","数据驱动");
		private String fieldName;
		private String remark;
		DatabaseInfoEnum(String fieldName,String remark){
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
	
	@ApiModel("修改数据库信息表信息,返回执行结果")
	public static class DatabaseInfoEResultVO {

		private static DatabaseInfoEResultVO success = new DatabaseInfoEResultVO(ReturnMsgEnum.SUCCESS.getCode(),ReturnMsgEnum.SUCCESS.getMsg());
		private static DatabaseInfoEResultVO fail = new DatabaseInfoEResultVO(ReturnMsgEnum.FAIL.getCode(),ReturnMsgEnum.FAIL.getMsg());
		@ApiModelProperty(value="状态码",allowableValues="200:成功,其他表示失败")
		private String code;
		@ApiModelProperty(value="返回修改结果信息")
		private String message;
		
		public DatabaseInfoEResultVO(String code,String message){
			this.code = code;
			this.message = message;
		}
		
		public DatabaseInfoEResultVO(){
		}
		
		public String getCode() {
			return code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
		public static DatabaseInfoEResultVO ok() {
			return success;
		}
	
		public static DatabaseInfoEResultVO fail() {
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
