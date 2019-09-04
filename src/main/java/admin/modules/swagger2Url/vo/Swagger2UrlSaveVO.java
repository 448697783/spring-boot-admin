package admin.modules.swagger2Url.vo;
import admin.common.baseenum.BaseEnum.ReturnMsgEnum;
import admin.modules.swagger2Url.entity.Swagger2UrlEntity;
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
 * @date 2019-09-02 18:13:02
 */
@ApiModel("保存参数")
public class Swagger2UrlSaveVO {

	@Size(min=0,max=50,message="别名长度为0-50个字符")
	@NotNull(message="别名不能为空")
	@ApiModelProperty(value="别名",example="填写例子")
	@ApiParam(value="别名",defaultValue="填写例子")
	private String name;
	
	@Min(value=0,message="值应大于0")
	@Max(value=9999999999L,message="值应小于9999999999L")
	@ApiModelProperty(value="",example="填写例子")
	@ApiParam(value="",defaultValue="填写例子")
	private Integer id;
	
	@Size(min=0,max=256,message="数据库url长度为0-256个字符")
	@NotNull(message="数据库url不能为空")
	@ApiModelProperty(value="数据库url",example="填写例子")
	@ApiParam(value="数据库url",defaultValue="填写例子")
	private String url;
	
	
	@Min(value=0,message="乐观锁值应大于0")
	@Max(value=9999999999L,message="乐观锁值应小于9999999999L")
	@ApiModelProperty(value="乐观锁",example="填写例子")
	@ApiParam(value="乐观锁",defaultValue="填写例子")
	private Integer versionOptimizedLock;
	


	/**
	 * 设置：别名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取：别名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
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
	 * 设置：乐观锁
	 */
	public void setVersionOptimizedLock(Integer versionOptimizedLock) {
		this.versionOptimizedLock = versionOptimizedLock;
	}
	
	/**
	 * 获取：乐观锁
	 */
	public Integer getVersionOptimizedLock() {
		return versionOptimizedLock;
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.modules.swagger2Url.vo.Swagger2UrlSaveVO ={")
		.append("name=").append(name)
		.append(",id=").append(id)
		.append(",url=").append(url)
		.append(",versionOptimizedLock=").append(versionOptimizedLock)
		.append("}");
		return builder.toString();
	}
	
	public static enum Swagger2UrlEnum{
		name("name","别名"),
		id("id",""),
		url("url","数据库url"),
		version_optimized_lock("versionOptimizedLock","乐观锁");
		private String fieldName;
		private String remark;
		Swagger2UrlEnum(String fieldName,String remark){
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
	
	@ApiModel("保存信息,返回执行结果")
	public static class Swagger2UrlSResultVO {
		private static Swagger2UrlSResultVO fail = new Swagger2UrlSResultVO(ReturnMsgEnum.FAIL.getCode(),ReturnMsgEnum.FAIL.getMsg());

		private static Swagger2UrlSResultVO success= new Swagger2UrlSResultVO(ReturnMsgEnum.SUCCESS.getCode(),ReturnMsgEnum.SUCCESS.getMsg());

		@ApiModelProperty(value="状态码",allowableValues="200:成功,其他表示失败")
		private String code;
		@ApiModelProperty(value="返回保存结果信息")
		private String msg;
		
		public Swagger2UrlSResultVO(String code,String msg){
			this.code = code;
			this.msg = msg;
		}
		
		@Override
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("admin.modules.swagger2Url.vo.Swagger2UrlSResultVO ={")
			.append("code=").append(code)
			.append(",msg=").append(msg)
			.append("}");
			return builder.toString();
		}
		public Swagger2UrlSResultVO(){
		}
		
		public String getCode() {
			return code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
		public static Swagger2UrlSResultVO ok() {
			return success;
		}
	
		public static Swagger2UrlSResultVO  fail() {
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
