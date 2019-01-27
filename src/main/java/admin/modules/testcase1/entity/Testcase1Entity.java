package admin.modules.testcase1.entity;


import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 测试用例
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2019-01-27 22:30:03
 */
public class Testcase1Entity implements Serializable {
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
	//a
	private BigDecimal a;
	//b
	private BigDecimal b;
	//c
	private BigDecimal c;
	//d
	private String d;

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
		.append(",startTime=").append(startTime)
		.append(",endTime=").append(endTime)
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
	
}
