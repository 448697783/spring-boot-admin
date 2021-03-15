package admin.modules.createSql.vo;
import admin.common.baseenum.BaseEnum.ReturnMsgEnum;
import admin.modules.createSql.entity.CreateSqlEntity;
import java.io.Serializable;
import java.util.ArrayList;
import admin.common.utils.BeanCopierUtils;
import admin.common.utils.DateUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.sql.Timestamp;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.math.BigDecimal;
/**
 * 快速构建sql
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2020-06-04 00:01:06
 */
@ApiModel("查询快速构建sql参数")
public class CreateSqlQueryVO {
	@Size(min=0,max=128,message="长度为0-128个字符")
	@ApiModelProperty(value="",example="填写例子")
	@ApiParam(value="",defaultValue="填写例子")
	private String ordernum;
	
	@Size(min=0,max=128,message="长度为0-128个字符")
	@ApiModelProperty(value="",example="填写例子")
	@ApiParam(value="",defaultValue="填写例子")
	private String customernum;
	
	@Size(min=0,max=128,message="长度为0-128个字符")
	@ApiModelProperty(value="",example="填写例子")
	@ApiParam(value="",defaultValue="填写例子")
	private String outTradeNo;
	/**
	* 默认第一页
	**/
	@ApiModelProperty(value="第几页",example="1")
	@ApiParam(value="第几页",defaultValue="1")
	private Long page=1L;
	
	/**
	* 默认100行数据
	**/
	@ApiModelProperty(value="一页展示多少条数据",example="100")
	@ApiParam(value="一页展示多少条数据",defaultValue="100")
	private Long rows=100L;
	
	//当需要根据时间范围查询时使用,不适用时请删除
	@ApiModelProperty(value="开始时间 格式yyyy-MM-dd HH:mm:ss",example="2018-10-18 18:18:18")
	@ApiParam(value="开始时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "2018-10-18 18:18:18") 
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp startTime;
	
	//当需要根据时间范围查询时使用,不适用时请删除
	@ApiModelProperty(value="结束时间 格式yyyy-MM-dd HH:mm:ss",example="2018-10-18 18:18:18")
	@ApiParam(value="结束时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "2018-10-18 18:18:18") 
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
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
	public CreateSqlQResultVO ok(List<CreateSqlEntity> createSqlEntityList) {
		return CreateSqlQResultVO.ok(createSqlEntityList,this);
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.modules.createSql.vo.CreateSqlQueryVO ={")
		.append("ordernum=").append(ordernum)
		.append(",customernum=").append(customernum)
		.append(",startTime=").append(DateUtils.format(startTime, DateUtils.DATE_TIME_PATTERN))
		.append(",endTime=").append(DateUtils.format(endTime, DateUtils.DATE_TIME_PATTERN))
		.append(",page=").append(page)
		.append(",rows=").append(rows)
		.append("}");
		return builder.toString();
	}
	
	public String getOutTradeNo() {
		return outTradeNo;
	}
	
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
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
	
	@ApiModel("查询快速构建sql结果集")
	public static class CreateSqlQResultVO {

		@ApiModelProperty(value="状态码",allowableValues="200:成功,其他表示失败")
		private String code;
		@ApiModelProperty(value="返回信息描述")
		private String msg;

		@ApiModelProperty(value="返回信息描述")
		private String sql;
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
		
		public CreateSqlQResultVO(String code,String msg){
			this.code = code;
			this.msg = msg;
		}
		public CreateSqlQResultVO(){
		}

		public String getSql() {
			return sql;
		}

		public void setSql(String sql) {
			this.sql = sql;
		}

		@ApiModelProperty(value="查询列表")
		private List<CreateSqlResult> resultList;
		public CreateSqlQResultVO(List<CreateSqlEntity> createSqlEntityList){
			this.code = ReturnMsgEnum.SUCCESS.getCode();
			this.msg =ReturnMsgEnum.SUCCESS.getMsg();
			if(createSqlEntityList==null||createSqlEntityList.size()==0){
				return;
			}
			CreateSqlResult result = null;
			resultList = new ArrayList<CreateSqlResult>(createSqlEntityList.size());
			for (CreateSqlEntity tempEntity : createSqlEntityList) {
				result = new CreateSqlResult();
				BeanCopierUtils.copyProperties( tempEntity,result);
				resultList.add(result);
			}
		}
		
		@Override
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("admin.modules.createSql.vo.CreateSqlQResultVO ={")
			.append("code=").append(code)
			.append(",msg=").append(msg)
			.append(",page=").append(page)
			.append(",rows=").append(rows)
			.append(",resultList ={")
			.append(resultList)
			.append("}")
			.append("}");
			return builder.toString();
		}
		
		public List<CreateSqlResult> getResultList() {
			return resultList;
		}
		
		public void setResultList(List<CreateSqlResult> resultList) {
			this.resultList = resultList;
		}
		
		public String getCode() {
			return code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
		public static CreateSqlQResultVO ok(List<CreateSqlEntity> createSqlEntityList,CreateSqlQueryVO createSqlQueryVO) {
			CreateSqlQResultVO createSqlQResultVO = new CreateSqlQResultVO(createSqlEntityList);
			createSqlQResultVO.setPage(createSqlQueryVO.getPage());
			createSqlQResultVO.setRows(createSqlQueryVO.getRows());
			return createSqlQResultVO;
		}
	
		public static CreateSqlQResultVO  fail() {
			 CreateSqlQResultVO vo = new CreateSqlQResultVO(null);
			 vo.setCode(ReturnMsgEnum.FAIL.getCode());
			 vo.setMsg(ReturnMsgEnum.FAIL.getMsg());
			 return vo;
		}
		
		public String getMsg() {
			return msg;
		}
	
		public void setMsg(String msg) {
			this.msg = msg;
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
		public Long getTotalPage() {
			return (count+rows-1)/rows;
		}
		public void setCount(Long count) {
			this.count = count;
		}
	}
		/**
	 * 回执快速构建sql说明
	 * 
	 */
	@ApiModel("返回快速构建sql字段说明")
	public static class CreateSqlResult {
		
		@ApiModelProperty(value="",example="填写例子")
		@ApiParam(value="",defaultValue="填写例子")
		private String ordernum;
		@ApiModelProperty(value="",example="填写例子")
		@ApiParam(value="",defaultValue="填写例子")
		private String customernum;
	
		
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
		@Override
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("admin.modules.createSql.vo.CreateSqlResult ={")
			.append("ordernum=").append(ordernum)
			.append(",customernum=").append(customernum)
			.append("}");
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
		return (page-1)*rows+rows;
	}
}
