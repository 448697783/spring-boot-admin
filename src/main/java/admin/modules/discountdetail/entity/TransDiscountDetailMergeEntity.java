package admin.modules.discountdetail.entity;


import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-07 17:00:23
 */
public class TransDiscountDetailMergeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//活动编码
	private String activeNumber;
	//优惠类型(1:折扣 2:满减)
	private Long discountType;
	//创建日期
	private Date createdOn;
	//总金额
	private Float totalAmt;
	//折扣金额
	private Float discountAmt;
	//没有描述
	private String moneyType;
	//没有描述
	private Float amt;
	//没有描述
	private String userName;
	//没有描述
	private String orderId;
	//没有描述
	private String transId;
	//没有描述
	private Date notityFinishTime;
	//没有描述
	private String merchantName;

	/**
	 * 设置：活动编码
	 */
	public void setActiveNumber(String activeNumber) {
		this.activeNumber = activeNumber;
	}
	/**
	 * 获取：活动编码
	 */
	public String getActiveNumber() {
		return activeNumber;
	}
	/**
	 * 设置：优惠类型(1:折扣 2:满减)
	 */
	public void setDiscountType(Long discountType) {
		this.discountType = discountType;
	}
	/**
	 * 获取：优惠类型(1:折扣 2:满减)
	 */
	public Long getDiscountType() {
		return discountType;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreatedOn() {
		return createdOn;
	}
	/**
	 * 设置：总金额
	 */
	public void setTotalAmt(Float totalAmt) {
		this.totalAmt = totalAmt;
	}
	/**
	 * 获取：总金额
	 */
	public Float getTotalAmt() {
		return totalAmt;
	}
	/**
	 * 设置：折扣金额
	 */
	public void setDiscountAmt(Float discountAmt) {
		this.discountAmt = discountAmt;
	}
	/**
	 * 获取：折扣金额
	 */
	public Float getDiscountAmt() {
		return discountAmt;
	}
	/**
	 * 设置：没有描述
	 */
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
	/**
	 * 获取：没有描述
	 */
	public String getMoneyType() {
		return moneyType;
	}
	/**
	 * 设置：没有描述
	 */
	public void setAmt(Float amt) {
		this.amt = amt;
	}
	/**
	 * 获取：没有描述
	 */
	public Float getAmt() {
		return amt;
	}
	/**
	 * 设置：没有描述
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：没有描述
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：没有描述
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：没有描述
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * 设置：没有描述
	 */
	public void setTransId(String transId) {
		this.transId = transId;
	}
	/**
	 * 获取：没有描述
	 */
	public String getTransId() {
		return transId;
	}
	/**
	 * 设置：没有描述
	 */
	public void setNotityFinishTime(Date notityFinishTime) {
		this.notityFinishTime = notityFinishTime;
	}
	/**
	 * 获取：没有描述
	 */
	public Date getNotityFinishTime() {
		return notityFinishTime;
	}
	/**
	 * 设置：没有描述
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	/**
	 * 获取：没有描述
	 */
	public String getMerchantName() {
		return merchantName;
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.entity.TransDiscountDetailMergeEntity [")
		.append(",activeNumber=").append(activeNumber)
		.append(",discountType=").append(discountType)
		.append(",createdOn=").append(createdOn)
		.append(",totalAmt=").append(totalAmt)
		.append(",discountAmt=").append(discountAmt)
		.append(",moneyType=").append(moneyType)
		.append(",amt=").append(amt)
		.append(",userName=").append(userName)
		.append(",orderId=").append(orderId)
		.append(",transId=").append(transId)
		.append(",notityFinishTime=").append(notityFinishTime)
		.append(",merchantName=").append(merchantName)
		.append("]");
		return builder.toString();
	}
	
	public static enum TransDiscountDetailMergeEnum{
		ACTIVE_NUMBER("activeNumber","活动编码"),
		DISCOUNT_TYPE("discountType","优惠类型(1:折扣 2:满减)"),
		CREATED_ON("createdOn","创建日期"),
		TOTAL_AMT("totalAmt","总金额"),
		DISCOUNT_AMT("discountAmt","折扣金额"),
		MONEY_TYPE("moneyType","没有描述"),
		AMT("amt","没有描述"),
		USER_NAME("userName","没有描述"),
		ORDER_ID("orderId","没有描述"),
		TRANS_ID("transId","没有描述"),
		NOTITY_FINISH_TIME("notityFinishTime","没有描述"),
		MERCHANT_NAME("merchantName","没有描述");
		private String fieldName;
		private String remark;
		TransDiscountDetailMergeEnum(String fieldName,String remark){
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
