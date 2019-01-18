package admin.modules.discountdetail.vo;
import admin.common.baseenum.BaseEnum.ReturnMessageEnum;
import admin.modules.discountdetail.entity.TransDiscountDetailMergeEntity;
import java.io.Serializable;
import java.util.ArrayList;
import admin.common.utils.BeanCopierUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.sql.Timestamp;
import java.util.Date;
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
 * 
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-07 17:00:23
 */
@ApiModel("查询参数")
public class TransDiscountDetailMergeQueryVO {
	@Size(min=0,max=32,message="活动编码长度为0-32个字符")
	@ApiModelProperty(value="活动编码",example="填写例子")
	@ApiParam(value="活动编码",defaultValue="填写例子")
	private String activeNumber;
	
	@Min(value=0,message="优惠类型(1:折扣 2:满减)值应大于0")
	@Max(value=9,message="优惠类型(1:折扣 2:满减)值应小于9")
	@ApiModelProperty(value="优惠类型(1:折扣 2:满减)",example="填写例子")
	@ApiParam(value="优惠类型(1:折扣 2:满减)",defaultValue="填写例子")
	private Long discountType;
	
	@ApiModelProperty(value="创建日期 格式yyyy-MM-dd HH:mm:ss",example="2018-10-18 18:18:18")
	@ApiParam(value="创建日期 格式yyyy-MM-dd HH:mm:ss",defaultValue = "2018-10-18 18:18:18") 
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdOn;
	
	@Min(value=0,message="总金额值应大于0")
	@Max(value=999999999999L,message="总金额值应小于999999999999L")
	@ApiModelProperty(value="总金额",example="填写例子")
	@ApiParam(value="总金额",defaultValue="填写例子")
	private Float totalAmt;
	
	@Min(value=0,message="折扣金额值应大于0")
	@Max(value=999999999999L,message="折扣金额值应小于999999999999L")
	@ApiModelProperty(value="折扣金额",example="填写例子")
	@ApiParam(value="折扣金额",defaultValue="填写例子")
	private Float discountAmt;
	
	@Size(min=0,max=32,message="null长度为0-32个字符")
	@ApiModelProperty(value="没有描述",example="填写例子")
	@ApiParam(value="没有描述",defaultValue="填写例子")
	private String moneyType;
	
	@Min(value=0,message="null值应大于0")
	@Max(value=999999999999L,message="null值应小于999999999999L")
	@ApiModelProperty(value="没有描述",example="填写例子")
	@ApiParam(value="没有描述",defaultValue="填写例子")
	private Float amt;
	
	@Size(min=0,max=128,message="null长度为0-128个字符")
	@ApiModelProperty(value="没有描述",example="填写例子")
	@ApiParam(value="没有描述",defaultValue="填写例子")
	private String userName;
	
	@Size(min=0,max=30,message="null长度为0-30个字符")
	@ApiModelProperty(value="没有描述",example="填写例子")
	@ApiParam(value="没有描述",defaultValue="填写例子")
	private String orderId;
	
	@Size(min=0,max=80,message="null长度为0-80个字符")
	@ApiModelProperty(value="没有描述",example="填写例子")
	@ApiParam(value="没有描述",defaultValue="填写例子")
	private String transId;
	
	@ApiModelProperty(value="没有描述 格式yyyy-MM-dd HH:mm:ss",example="2018-10-18 18:18:18")
	@ApiParam(value="没有描述 格式yyyy-MM-dd HH:mm:ss",defaultValue = "2018-10-18 18:18:18") 
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date notityFinishTime;
	
	@Size(min=0,max=100,message="null长度为0-100个字符")
	@ApiModelProperty(value="没有描述",example="填写例子")
	@ApiParam(value="没有描述",defaultValue="填写例子")
	private String merchantName;
	
	/**
	* 默认第一页
	**/
	@ApiModelProperty(value="一页展示数量",example="1")
	@ApiParam(value="一页展示数量",defaultValue="1")
	private Long page=1L;
	
	/**
	* 默认100行数据
	**/
	@ApiModelProperty(value="一页展示多少条数据",example="100")
	@ApiParam(value="一页展示多少条数据",defaultValue="100")
	private Long rows=100L;
	

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
	public TransDiscountDetailMergeQResultVO ok(List<TransDiscountDetailMergeEntity> transDiscountDetailMergeEntityList) {
		return TransDiscountDetailMergeQResultVO.ok(transDiscountDetailMergeEntityList,this);
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
		.append(",page=").append(page)
		.append(",rows=").append(rows)
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
	
	@ApiModel("查询结果集")
	public static class TransDiscountDetailMergeQResultVO {

		@ApiModelProperty(value="状态码",allowableValues="200:成功,其他表示失败")
		private String code;
		@ApiModelProperty(value="返回信息描述")
		private String message;
		/**
		* 默认第一页
		**/
		@ApiModelProperty(value="一页展示数量",example="1")
		@ApiParam(value="一页展示数量",defaultValue="1")
		private Long page=1L;
		
		/**
		* 默认100行数据
		**/
		@ApiModelProperty(value="一页展示多少条数据",example="100")
		@ApiParam(value="一页展示多少条数据",defaultValue="100")
		private Long rows=100L;
		
		/**
		* 总行数
		**/
		@ApiModelProperty(value="总行数",example="100")
		@ApiParam(value="总行数",defaultValue="100")
		private Long count;
		
		public TransDiscountDetailMergeQResultVO(String code,String message){
			this.code = code;
			this.message = message;
		}
		public TransDiscountDetailMergeQResultVO(){
		}
		
		@ApiModelProperty(value="查询列表")
		private List<TransDiscountDetailMergeResult> resultList;
		public TransDiscountDetailMergeQResultVO(List<TransDiscountDetailMergeEntity> transDiscountDetailMergeEntityList){
			this.code = ReturnMessageEnum.SUCCESS.getCode();
			this.message =ReturnMessageEnum.SUCCESS.getMessage();
			if(transDiscountDetailMergeEntityList==null||transDiscountDetailMergeEntityList.size()==0){
				return;
			}
			TransDiscountDetailMergeResult result = null;
			resultList = new ArrayList<TransDiscountDetailMergeResult>(transDiscountDetailMergeEntityList.size());
			for (TransDiscountDetailMergeEntity tempEntity : transDiscountDetailMergeEntityList) {
				result = new TransDiscountDetailMergeResult();
				BeanCopierUtils.copyProperties( tempEntity,result);
				resultList.add(result);
			}
		}
		
		public List<TransDiscountDetailMergeResult> getResultList() {
			return resultList;
		}
		
		public void setResultList(List<TransDiscountDetailMergeResult> resultList) {
			this.resultList = resultList;
		}
		
		public String getCode() {
			return code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
		public static TransDiscountDetailMergeQResultVO ok(List<TransDiscountDetailMergeEntity> transDiscountDetailMergeEntityList,TransDiscountDetailMergeQueryVO transDiscountDetailMergeQueryVO) {
			TransDiscountDetailMergeQResultVO transDiscountDetailMergeQResultVO = new TransDiscountDetailMergeQResultVO(transDiscountDetailMergeEntityList);
			transDiscountDetailMergeQResultVO.setPage(transDiscountDetailMergeQueryVO.getPage());
			transDiscountDetailMergeQResultVO.setRows(transDiscountDetailMergeQueryVO.getRows());
			return transDiscountDetailMergeQResultVO;
		}
	
		public static TransDiscountDetailMergeQResultVO  fail() {
			 TransDiscountDetailMergeQResultVO vo = new TransDiscountDetailMergeQResultVO(null);
			 vo.setCode(ReturnMessageEnum.FAIL.getCode());
			 vo.setMessage(ReturnMessageEnum.FAIL.getMessage());
			 return vo;
		}
		
		public String getMessage() {
			return message;
		}
	
		public void setMessage(String message) {
			this.message = message;
		}
		
		public Long getPage() {
			return page;
		}
		public void setPage(Long page) {
			this.page = page;
		}
		public Long getRows() {
			return rows;
		}
		public void setRows(Long rows) {
			this.rows = rows;
		}
		public Long getCount() {
			return count;
		}
		public void setCount(Long count) {
			this.count = count;
		}
	}
		/**
	 * 回执说明
	 * 
	 */
	@ApiModel("返回字段说明")
	public static class TransDiscountDetailMergeResult {
		
		@ApiModelProperty(value="活动编码",example="填写例子")
		@ApiParam(value="活动编码",defaultValue="填写例子")
		private String activeNumber;
		@ApiModelProperty(value="优惠类型(1:折扣 2:满减)",example="填写例子")
		@ApiParam(value="优惠类型(1:折扣 2:满减)",defaultValue="填写例子")
		private Long discountType;
		@ApiModelProperty(value="创建日期 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
		@ApiParam(value="创建日期 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子") 
		@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
		private Date createdOn;
		@ApiModelProperty(value="总金额",example="填写例子")
		@ApiParam(value="总金额",defaultValue="填写例子")
		private Float totalAmt;
		@ApiModelProperty(value="折扣金额",example="填写例子")
		@ApiParam(value="折扣金额",defaultValue="填写例子")
		private Float discountAmt;
		@ApiModelProperty(value="没有描述",example="填写例子")
		@ApiParam(value="没有描述",defaultValue="填写例子")
		private String moneyType;
		@ApiModelProperty(value="没有描述",example="填写例子")
		@ApiParam(value="没有描述",defaultValue="填写例子")
		private Float amt;
		@ApiModelProperty(value="没有描述",example="填写例子")
		@ApiParam(value="没有描述",defaultValue="填写例子")
		private String userName;
		@ApiModelProperty(value="没有描述",example="填写例子")
		@ApiParam(value="没有描述",defaultValue="填写例子")
		private String orderId;
		@ApiModelProperty(value="没有描述",example="填写例子")
		@ApiParam(value="没有描述",defaultValue="填写例子")
		private String transId;
		@ApiModelProperty(value="没有描述 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
		@ApiParam(value="没有描述 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子") 
		@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
		private Date notityFinishTime;
		@ApiModelProperty(value="没有描述",example="填写例子")
		@ApiParam(value="没有描述",defaultValue="填写例子")
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
	}
	
	public long getPage() {
		// TODO Auto-generated method stub
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getRows() {
		// TODO Auto-generated method stub
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}
	
	public long getStartRow() {
		return (page-1)*rows;
	}
	
	public long getEndRow() {
		return page*rows+1;
	}
}
