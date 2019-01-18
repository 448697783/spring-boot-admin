package admin.modules.testcase.entity;

import java.sql.Timestamp;
import java.util.List;

import admin.common.utils.ReturnInfo;
import admin.modules.sys.entity.SysRoleDeptEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("测试用例实体")
public class TestCaseEntity extends ReturnInfo {
	
	public TestCaseEntity(String code, String message) {
		super(code, message);
	}
	public TestCaseEntity() {
	}
	/**
	 * 上次执行时间
	 */
	@ApiModelProperty(value="上次执行时间",hidden=true)
	private Timestamp execLastTime;
	@ApiModelProperty(value="上次执行状态",hidden=true)
	private int status;
	@ApiModelProperty("测试用例描述")
	private String method;
	
	private List<ParamterEntity> paramterEntity;
	@ApiModelProperty("测试用例controller名称")
	private String controllerName;
	@ApiModelProperty(value="参数id",allowEmptyValue=true)
    private String testCaseId;
	@ApiModelProperty(value="部门id",allowEmptyValue=true)
	private long deptId;
	private List<SysRoleDeptEntity> sysRoleDeptEntity;
	@ApiModelProperty(value="用例描述",allowEmptyValue=true)
	private String describe;
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
	public Timestamp getExecLastTime() {
		return execLastTime;
	}
	public void setExecLastTime(Timestamp execLastTime) {
		this.execLastTime = execLastTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	public List<SysRoleDeptEntity> getSysRoleDeptEntity() {
		return sysRoleDeptEntity;
	}
	public void setSysRoleDeptEntity(List<SysRoleDeptEntity> sysRoleDeptEntity) {
		this.sysRoleDeptEntity = sysRoleDeptEntity;
	}
	public long getDeptId() {
		return deptId;
	}
	public void setDeptId(long deptId) {
		this.deptId = deptId;
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
