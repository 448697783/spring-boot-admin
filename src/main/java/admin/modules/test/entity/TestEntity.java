package admin.modules.test.entity;


import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 测试用例
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2018-06-15 20:06:11
 */
public class TestEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//controller名称
	private String controllerName;
	//主键
	private Long testCaseId;
	//创建时间
	private Date createdTime;
	//更新时间
	private Date updatedTime;
	//上次执行时间
	private Date execLastTime;
	//状态0:成功1:失败2:未执行
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
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.entity.TestEntity [")
		.append(",controllerName=").append(controllerName)
		.append(",testCaseId=").append(testCaseId)
		.append(",createdTime=").append(createdTime)
		.append(",updatedTime=").append(updatedTime)
		.append(",execLastTime=").append(execLastTime)
		.append(",status=").append(status)
		.append("]");
		return builder.toString();
	}
	
	public static enum TestEnum{
		controller_name("controllerName","controller名称"),
		test_case_id("testCaseId","主键"),
		created_time("createdTime","创建时间"),
		updated_time("updatedTime","更新时间"),
		exec_last_time("execLastTime","上次执行时间"),
		status("status","状态0:成功1:失败2:未执行");
		private String fieldName;
		private String remark;
		TestEnum(String fieldName,String remark){
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
	
}
