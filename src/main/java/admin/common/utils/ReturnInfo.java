package admin.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("回执信息实体")
public class ReturnInfo {

	
	@ApiModelProperty(value="状态码",allowableValues="200:成功,其他表示失败")
	private String code;
	@ApiModelProperty(value="返回信息")
	private String msg;
	public ReturnInfo(String code,String msg) {
		this.code=code;
		this.msg=msg;
	}
	
	public ReturnInfo() {
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public static ReturnInfo ok() {
		return new ReturnInfo("0","成功");
	}

	public static ReturnInfo error() {
		return new ReturnInfo("9","失败");
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
