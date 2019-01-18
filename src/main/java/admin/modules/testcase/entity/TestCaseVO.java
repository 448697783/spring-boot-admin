package admin.modules.testcase.entity;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("保存测试用例实体")
public class TestCaseVO  {
	
	@Size(max=4,min=2)
	private List<ParamterEntity> paramterEntity;
	
	@Min(3)@Max(10)
	@NotNull
	private int max;
	@Size(max=10,min=1)
	@NotNull
	private String stringMax;
	
	@ApiModelProperty("测试用例描述")
	private String describe;
	@ApiModelProperty("测试用例描述")
	private String method;
	
	@ApiModelProperty("测试用例controller名称")
	private String controllerName;
	public List<ParamterEntity> getParamterEntity() {
		return paramterEntity;
	}
	public void setParamterEntity(List<ParamterEntity> paramterEntity) {
		this.paramterEntity = paramterEntity;
	}
	public String getControllerName() {
		return controllerName;
	}
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}
	public String getStringMax() {
		return stringMax;
	}
	public void setStringMax(String stringMax) {
		this.stringMax = stringMax;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
}
