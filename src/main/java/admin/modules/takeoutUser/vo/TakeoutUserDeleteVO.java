package admin.modules.takeoutUser.vo;
import admin.common.baseenum.BaseEnum.ReturnMsgEnum;
import admin.modules.takeoutUser.entity.TakeoutUserEntity;
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
 * @date 2019-07-01 19:19:04
 */
@ApiModel("删除参数")
public class TakeoutUserDeleteVO {

	@Min(value=0,message="机器码值应大于0")
	@Max(value=9999999999L,message="机器码值应小于9999999999L")
	@ApiModelProperty(value="机器码",example="填写例子")
	@ApiParam(value="机器码",defaultValue="填写例子")
	private Integer fid;
	
	@Size(min=0,max=50,message="姓名长度为0-50个字符")
	@ApiModelProperty(value="姓名",example="填写例子")
	@ApiParam(value="姓名",defaultValue="填写例子")
	private String fname;
	
	@Size(min=0,max=20,message="电话长度为0-20个字符")
	@ApiModelProperty(value="电话",example="填写例子")
	@ApiParam(value="电话",defaultValue="填写例子")
	private String fphonenumber;
	
	@Size(min=0,max=200,message="地址长度为0-200个字符")
	@ApiModelProperty(value="地址",example="填写例子")
	@ApiParam(value="地址",defaultValue="填写例子")
	private String faddress;
	


	/**
	 * 设置：机器码
	 */
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	
	/**
	 * 获取：机器码
	 */
	public Integer getFid() {
		return fid;
	}

	/**
	 * 设置：姓名
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * 设置：电话
	 */
	public void setFphonenumber(String fphonenumber) {
		this.fphonenumber = fphonenumber;
	}
	
	/**
	 * 获取：电话
	 */
	public String getFphonenumber() {
		return fphonenumber;
	}

	/**
	 * 设置：地址
	 */
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	
	/**
	 * 获取：地址
	 */
	public String getFaddress() {
		return faddress;
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.modules.takeoutUser.vo.TakeoutUserDeleteVO ={")
		.append("fid=").append(fid)
		.append(",fname=").append(fname)
		.append(",fphonenumber=").append(fphonenumber)
		.append(",faddress=").append(faddress)
		.append("}");
		return builder.toString();
	}
	
	public static enum TakeoutUserEnum{
		fID("fid","机器码"),
		fName("fname","姓名"),
		fPhoneNumber("fphonenumber","电话"),
		fAddress("faddress","地址");
		private String fieldName;
		private String remark;
		TakeoutUserEnum(String fieldName,String remark){
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
	public static class TakeoutUserDResultVO {

		private static TakeoutUserDResultVO success = new TakeoutUserDResultVO(ReturnMsgEnum.SUCCESS.getCode(),ReturnMsgEnum.SUCCESS.getMsg());
		private static TakeoutUserDResultVO fail = new TakeoutUserDResultVO(ReturnMsgEnum.FAIL.getCode(),ReturnMsgEnum.FAIL.getMsg());
		@ApiModelProperty(value="状态码",allowableValues="200:成功,其他表示失败")
		private String code;
		@ApiModelProperty(value="返回修改结果信息")
		private String msg;
		
		public TakeoutUserDResultVO(String code,String msg){
			this.code = code;
			this.msg = msg;
		}
		
		@Override
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("admin.modules.takeoutUser.vo.TakeoutUserDResultVO =")
			.append("code=").append(code)
			.append(",msg=").append(msg)
			.append("}");
			return builder.toString();
		}
		
		public TakeoutUserDResultVO(){
		}
		
		public String getCode() {
			return code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
		public static TakeoutUserDResultVO ok() {
			return success;
		}
	
		public static TakeoutUserDResultVO  fail() {
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
