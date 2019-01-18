package admin.modules.discountinfo1.vo;
import admin.common.baseenum.BaseEnum.ReturnMessageEnum;
import admin.modules.discountinfo1.entity.DiscountInfo1Entity;
import java.io.Serializable;
import java.util.ArrayList;
import admin.common.utils.BeanCopierUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.Timestamp;
import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.math.BigDecimal;
/**
 * 折扣活动信息表
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-12 16:21:22
 */
@ApiModel("修改折扣活动信息表参数")
public class DiscountInfo1EditVO {

	@NotNull(message="主键不能为空")
	@Min(value=0,message="主键值应大于0")
	@Max(value=999999999999999999L,message="主键值应小于999999999999999999L")
	@ApiModelProperty(value="主键",example="填写例子")
	@ApiParam(value="主键",defaultValue="填写例子")
	private Long id;
	
	
	@Min(value=0,message="是否启用(1:启用2:不启用)值应大于0")
	@Max(value=9,message="是否启用(1:启用2:不启用)值应小于9")
	@ApiModelProperty(value="是否启用(1:启用2:不启用)",example="填写例子")
	@ApiParam(value="是否启用(1:启用2:不启用)",defaultValue="填写例子")
	private Long enabled;
	
	@Size(min=0,max=32,message="活动编码长度为0-32个字符")
	@NotNull(message="活动编码不能为空")
	@ApiModelProperty(value="活动编码",example="填写例子")
	@ApiParam(value="活动编码",defaultValue="填写例子")
	private String activeNumber;
	
	@NotNull(message="更新日期不能为空")
	@ApiModelProperty(value="更新日期 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
	@ApiParam(value="更新日期 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子")  
	private Date updatedOn;
	
	@Size(min=0,max=200,message="活动CRON表达式长度为0-200个字符")
	@NotNull(message="活动CRON表达式不能为空")
	@ApiModelProperty(value="活动CRON表达式",example="填写例子")
	@ApiParam(value="活动CRON表达式",defaultValue="填写例子")
	private String validTime;
	
	@NotNull(message="优惠类型(1:折扣 2:满减)不能为空")
	@Min(value=0,message="优惠类型(1:折扣 2:满减)值应大于0")
	@Max(value=9,message="优惠类型(1:折扣 2:满减)值应小于9")
	@ApiModelProperty(value="优惠类型(1:折扣 2:满减)",example="填写例子")
	@ApiParam(value="优惠类型(1:折扣 2:满减)",defaultValue="填写例子")
	private Long discountType;
	
	@NotNull(message="版本号不能为空")
	@Min(value=0,message="版本号值应大于0")
	@Max(value=99999999999L,message="版本号值应小于99999999999L")
	@ApiModelProperty(value="版本号",example="填写例子")
	@ApiParam(value="版本号",defaultValue="填写例子")
	private Long versionOptimizedLock;
	
	@Size(min=0,max=100,message="优惠形式JSO格式长度为0-100个字符")
	
	@ApiModelProperty(value="优惠形式JSO格式",example="填写例子")
	@ApiParam(value="优惠形式JSO格式",defaultValue="填写例子")
	private String rang;
	
	@NotNull(message="创建日期不能为空")
	@ApiModelProperty(value="创建日期 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
	@ApiParam(value="创建日期 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子")  
	private Date createdOn;
	
	
	@Min(value=0,message="优先级(越大优先级越高)值应大于0")
	@Max(value=9999999999L,message="优先级(越大优先级越高)值应小于9999999999L")
	@ApiModelProperty(value="优先级(越大优先级越高)",example="填写例子")
	@ApiParam(value="优先级(越大优先级越高)",defaultValue="填写例子")
	private Long priority;
	
	@Size(min=0,max=64,message="活动名称长度为0-64个字符")
	@NotNull(message="活动名称不能为空")
	@ApiModelProperty(value="活动名称",example="填写例子")
	@ApiParam(value="活动名称",defaultValue="填写例子")
	private String activeName;
	
	@Size(min=0,max=50,message="业务线ID长度为0-50个字符")
	@NotNull(message="业务线ID不能为空")
	@ApiModelProperty(value="业务线ID",example="填写例子")
	@ApiParam(value="业务线ID",defaultValue="填写例子")
	private String merchantId;
	
	@NotNull(message="活动结束时间不能为空")
	@ApiModelProperty(value="活动结束时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
	@ApiParam(value="活动结束时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子")  
	private Date endTime;
	
	@NotNull(message="活动开始时间不能为空")
	@ApiModelProperty(value="活动开始时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
	@ApiParam(value="活动开始时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子")  
	private Date startTime;
	
	@NotNull(message="产品ID不能为空")
	@Min(value=0,message="产品ID值应大于0")
	@Max(value=9999,message="产品ID值应小于9999")
	@ApiModelProperty(value="产品ID",example="填写例子")
	@ApiParam(value="产品ID",defaultValue="填写例子")
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
	
	@ApiModel("修改折扣活动信息表信息,返回执行结果")
	public static class DiscountInfo1EResultVO {

		private static DiscountInfo1EResultVO success = new DiscountInfo1EResultVO(ReturnMessageEnum.SUCCESS.getCode(),ReturnMessageEnum.SUCCESS.getMessage());
		private static DiscountInfo1EResultVO fail = new DiscountInfo1EResultVO(ReturnMessageEnum.FAIL.getCode(),ReturnMessageEnum.FAIL.getMessage());
		@ApiModelProperty(value="状态码",allowableValues="200:成功,其他表示失败")
		private String code;
		@ApiModelProperty(value="返回修改结果信息")
		private String message;
		
		public DiscountInfo1EResultVO(String code,String message){
			this.code = code;
			this.message = message;
		}
		
		public DiscountInfo1EResultVO(){
		}
		
		public String getCode() {
			return code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
		public static DiscountInfo1EResultVO ok() {
			return success;
		}
	
		public static DiscountInfo1EResultVO fail() {
			return fail;
		}
		
		public String getMessage() {
			return message;
		}
	
		public void setMessage(String message) {
			this.message = message;
		}
	}
}
