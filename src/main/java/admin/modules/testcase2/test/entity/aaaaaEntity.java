package admin.modules.testcase2.test.entity;
import admin.modules.sys.entity.SysRoleDeptEntity;


import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-04-18 20:12:20
 */
public class aaaaaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//controller名称
	private String controllerName;
	//
	private String describe;
	//
	private String method;
	//主键
	private Long testCaseId;
	//
	private Long deptId;
	//创建时间
	private Date createdTime;
	//更新时间
	private Date updatedTime;
	//上次执行时间
	private Date execLastTime;
	//状态0:成功1:失败2:未执行
	private Integer status;

	//  数据权限
	private List<SysRoleDeptEntity> sysRoleDeptEntity;
	//当需要根据时间范围查询时使用,不适用时请删除
	private Date startTime;
	
	//当需要根据时间范围查询时使用,不适用时请删除
	private Date endTime;
	
	/**
	 * 设置：开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 获取：开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 获取：结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}

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
		builder.append("admin.entity.aaaaaEntity [")
		.append(",controllerName=").append(controllerName)
		.append(",describe=").append(describe)
		.append(",method=").append(method)
		.append(",testCaseId=").append(testCaseId)
		.append(",deptId=").append(deptId)
		.append(",createdTime=").append(createdTime)
		.append(",updatedTime=").append(updatedTime)
		.append(",execLastTime=").append(execLastTime)
		.append(",status=").append(status)
		.append(",startTime=").append(startTime)
		.append(",endTime=").append(endTime)
		.append("]");
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
	public List<SysRoleDeptEntity> getSysRoleDeptEntity() {
		return sysRoleDeptEntity;
	}

	public void setSysRoleDeptEntity(List<SysRoleDeptEntity> sysRoleDeptEntity) {
		this.sysRoleDeptEntity = sysRoleDeptEntity;
	}
}
