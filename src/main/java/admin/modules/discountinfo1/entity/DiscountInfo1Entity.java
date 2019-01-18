package admin.modules.discountinfo1.entity;


import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 折扣活动信息表
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-12 16:21:22
 */
public class DiscountInfo1Entity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//是否启用(1:启用2:不启用)
	private Long enabled;
	//活动编码
	private String activeNumber;
	//更新日期
	private Date updatedOn;
	//活动CRON表达式
	private String validTime;
	//优惠类型(1:折扣 2:满减)
	private Long discountType;
	//版本号
	private Long versionOptimizedLock;
	//优惠形式JSO格式
	private String rang;
	//创建日期
	private Date createdOn;
	//优先级(越大优先级越高)
	private Long priority;
	//活动名称
	private String activeName;
	//业务线ID
	private String merchantId;
	//活动结束时间
	private Date endTime;
	//活动开始时间
	private Date startTime;
	//产品ID
	private Long productId;

	/**
	 * 设置：主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：是否启用(1:启用2:不启用)
	 */
	public void setEnabled(Long enabled) {
		this.enabled = enabled;
	}
	/**
	 * 获取：是否启用(1:启用2:不启用)
	 */
	public Long getEnabled() {
		return enabled;
	}
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
	 * 设置：更新日期
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	/**
	 * 获取：更新日期
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}
	/**
	 * 设置：活动CRON表达式
	 */
	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}
	/**
	 * 获取：活动CRON表达式
	 */
	public String getValidTime() {
		return validTime;
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
	 * 设置：版本号
	 */
	public void setVersionOptimizedLock(Long versionOptimizedLock) {
		this.versionOptimizedLock = versionOptimizedLock;
	}
	/**
	 * 获取：版本号
	 */
	public Long getVersionOptimizedLock() {
		return versionOptimizedLock;
	}
	/**
	 * 设置：优惠形式JSO格式
	 */
	public void setRang(String rang) {
		this.rang = rang;
	}
	/**
	 * 获取：优惠形式JSO格式
	 */
	public String getRang() {
		return rang;
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
	 * 设置：优先级(越大优先级越高)
	 */
	public void setPriority(Long priority) {
		this.priority = priority;
	}
	/**
	 * 获取：优先级(越大优先级越高)
	 */
	public Long getPriority() {
		return priority;
	}
	/**
	 * 设置：活动名称
	 */
	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}
	/**
	 * 获取：活动名称
	 */
	public String getActiveName() {
		return activeName;
	}
	/**
	 * 设置：业务线ID
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	/**
	 * 获取：业务线ID
	 */
	public String getMerchantId() {
		return merchantId;
	}
	/**
	 * 设置：活动结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：活动结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：活动开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：活动开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：产品ID
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品ID
	 */
	public Long getProductId() {
		return productId;
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.entity.DiscountInfo1Entity [")
		.append(",id=").append(id)
		.append(",enabled=").append(enabled)
		.append(",activeNumber=").append(activeNumber)
		.append(",updatedOn=").append(updatedOn)
		.append(",validTime=").append(validTime)
		.append(",discountType=").append(discountType)
		.append(",versionOptimizedLock=").append(versionOptimizedLock)
		.append(",rang=").append(rang)
		.append(",createdOn=").append(createdOn)
		.append(",priority=").append(priority)
		.append(",activeName=").append(activeName)
		.append(",merchantId=").append(merchantId)
		.append(",endTime=").append(endTime)
		.append(",startTime=").append(startTime)
		.append(",productId=").append(productId)
		.append("]");
		return builder.toString();
	}
	
	public static enum DiscountInfo1Enum{
		ID("id","主键"),
		ENABLED("enabled","是否启用(1:启用2:不启用)"),
		ACTIVE_NUMBER("activeNumber","活动编码"),
		UPDATED_ON("updatedOn","更新日期"),
		VALID_TIME("validTime","活动CRON表达式"),
		DISCOUNT_TYPE("discountType","优惠类型(1:折扣 2:满减)"),
		VERSION_OPTIMIZED_LOCK("versionOptimizedLock","版本号"),
		RANG("rang","优惠形式JSO格式"),
		CREATED_ON("createdOn","创建日期"),
		PRIORITY("priority","优先级(越大优先级越高)"),
		ACTIVE_NAME("activeName","活动名称"),
		MERCHANT_ID("merchantId","业务线ID"),
		END_TIME("endTime","活动结束时间"),
		START_TIME("startTime","活动开始时间"),
		PRODUCT_ID("productId","产品ID");
		private String fieldName;
		private String remark;
		DiscountInfo1Enum(String fieldName,String remark){
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
