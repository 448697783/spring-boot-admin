package admin.modules.testcase.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("参数项实体")
public class ParamterEntity {

	@ApiModelProperty("参数名")
	@Size(max=10,min=1)
    private String parameterName;
	@ApiModelProperty("参数项父id")
	private long id;
	@ApiModelProperty("参数类型")
    private String parameterType;
	@ApiModelProperty("参数内容")
	private String context;
	@ApiModelProperty("参数描述")
	private String parameterDescribe;
    private String parameterDeprecated;
	@ApiModelProperty(value="参数协议类型",allowableValues="body,query")
    private String parameterIn;
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getParameterType() {
		return parameterType;
	}
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	public String getParameterDeprecated() {
		return parameterDeprecated;
	}
	public void setParameterDeprecated(String parameterDeprecated) {
		this.parameterDeprecated = parameterDeprecated;
	}
	public String getParameterIn() {
		return parameterIn;
	}
	public void setParameterIn(String parameterIn) {
		this.parameterIn = parameterIn;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getParameterDescribe() {
		return parameterDescribe;
	}
	public void setParameterDescribe(String parameterDescribe) {
		this.parameterDescribe = parameterDescribe;
	}
    
    
}
