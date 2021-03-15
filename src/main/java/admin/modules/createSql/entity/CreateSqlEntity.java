package admin.modules.createSql.entity;
import admin.modules.sys.entity.SysRoleDeptEntity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 快速构建sql
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2020-06-04 00:01:06
 */
public class CreateSqlEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String ordernum;
	private String outTradeNo;
	//
	private String customernum;

	//当需要根据时间范围查询时使用,不适用时请删除
	private Timestamp startTime;
	
	//当需要根据时间范围查询时使用,不适用时请删除
	private Timestamp endTime;
	
	/**
	 * 设置：开始时间
	 */
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 获取：开始时间
	 */
	public Timestamp getStartTime() {
		return startTime;
	}
	
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 获取：结束时间
	 */
	public Timestamp getEndTime() {
		return endTime;
	}

	/**
	 * 设置：
	 */
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	/**
	 * 获取：
	 */
	public String getOrdernum() {
		return ordernum;
	}
	/**
	 * 设置：
	 */
	public void setCustomernum(String customernum) {
		this.customernum = customernum;
	}
	/**
	 * 获取：
	 */
	public String getCustomernum() {
		return customernum;
	}
	
	public String getOutTradeNo() {
		return outTradeNo;
	}
	
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.entity.CreateSqlEntity [")
		.append(",ordernum=").append(ordernum)
		.append(",customernum=").append(customernum)
		.append(",startTime=").append(startTime)
		.append(",endTime=").append(endTime)
		.append("]");
		return builder.toString();
	}
	
	public static enum CreateSqlEnum{
		orderNum("ordernum",""),
		customerNum("customernum","");
		private String fieldName;
		private String remark;
		CreateSqlEnum(String fieldName,String remark){
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
