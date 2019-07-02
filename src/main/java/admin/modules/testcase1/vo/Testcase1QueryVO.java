package admin.modules.testcase1.vo;
import admin.common.baseenum.BaseEnum.ReturnMsgEnum;
import admin.modules.testcase1.entity.Testcase1Entity;
import java.io.Serializable;
import java.util.ArrayList;
import admin.common.utils.BeanCopierUtils;
import admin.common.utils.DateUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;
import java.sql.Timestamp;
import java.util.Date;
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
 * 测试用例
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-05-15 07:12:47
 */
@ApiModel("查询测试用例参数")
public class Testcase1QueryVO {
	@Size(min=0,max=500,message="controller名称长度为0-500个字符")
	@ApiModelProperty(value="controller名称",example="填写例子")
	@ApiParam(value="controller名称",defaultValue="填写例子")
	private String controllerName;
	
	@Size(min=0,max=500,message="长度为0-500个字符")
	@ApiModelProperty(value="",example="填写例子")
	@ApiParam(value="",defaultValue="填写例子")
	private String describe;
	
	@Size(min=0,max=500,message="长度为0-500个字符")
	@ApiModelProperty(value="",example="填写例子")
	@ApiParam(value="",defaultValue="填写例子")
	private String method;
	
	@DecimalMin(value="0",message="主键值应大于0")
	@DecimalMax(value="9999999999999999999",message="主键值应小于9999999999999999999")
	@ApiModelProperty(value="主键",example="填写例子")
	@ApiParam(value="主键",defaultValue="填写例子")
	private Long testCaseId;
	
	@DecimalMin(value="0",message="值应大于0")
	@DecimalMax(value="9999999999999999999",message="值应小于9999999999999999999")
	@ApiModelProperty(value="",example="填写例子")
	@ApiParam(value="",defaultValue="填写例子")
	private Long deptId;
	
	@ApiModelProperty(value="创建时间 格式yyyy-MM-dd HH:mm:ss",example="2018-10-18 18:18:18")
	@ApiParam(value="创建时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "2018-10-18 18:18:18") 
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdTime;
	
	@ApiModelProperty(value="更新时间 格式yyyy-MM-dd HH:mm:ss",example="2018-10-18 18:18:18")
	@ApiParam(value="更新时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "2018-10-18 18:18:18") 
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatedTime;
	
	@ApiModelProperty(value="上次执行时间 格式yyyy-MM-dd HH:mm:ss",example="2018-10-18 18:18:18")
	@ApiParam(value="上次执行时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "2018-10-18 18:18:18") 
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date execLastTime;
	
	@Min(value=0,message="状态0:成功1:失败2:未执行值应大于0")
	@Max(value=9999999999L,message="状态0:成功1:失败2:未执行值应小于9999999999L")
	@ApiModelProperty(value="状态0:成功1:失败2:未执行",example="填写例子")
	@ApiParam(value="状态0:成功1:失败2:未执行",defaultValue="填写例子")
	private Integer status;
	
	
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
	
	//当需要根据时间范围查询时使用,不适用时请删除
	@ApiModelProperty(value="开始时间 格式yyyy-MM-dd HH:mm:ss",example="2018-10-18 18:18:18")
	@ApiParam(value="开始时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "2018-10-18 18:18:18") 
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	//当需要根据时间范围查询时使用,不适用时请删除
	@ApiModelProperty(value="结束时间 格式yyyy-MM-dd HH:mm:ss",example="2018-10-18 18:18:18")
	@ApiParam(value="结束时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "2018-10-18 18:18:18") 
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
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
	public Testcase1QResultVO ok(List<Testcase1Entity> testcase1EntityList) {
		return Testcase1QResultVO.ok(testcase1EntityList,this);
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.modules.testcase1.vo.Testcase1QueryVO ={")
		.append("controllerName=").append(controllerName)
		.append(",describe=").append(describe)
		.append(",method=").append(method)
		.append(",testCaseId=").append(testCaseId)
		.append(",deptId=").append(deptId)
		.append(",createdTime=").append(DateUtils.format(createdTime, DateUtils.DATE_TIME_PATTERN))
		.append(",updatedTime=").append(DateUtils.format(updatedTime, DateUtils.DATE_TIME_PATTERN))
		.append(",execLastTime=").append(DateUtils.format(execLastTime, DateUtils.DATE_TIME_PATTERN))
		.append(",status=").append(status)
		.append(",startTime=").append(DateUtils.format(startTime, DateUtils.DATE_TIME_PATTERN))
		.append(",endTime=").append(DateUtils.format(endTime, DateUtils.DATE_TIME_PATTERN))
		.append(",page=").append(page)
		.append(",rows=").append(rows)
		.append("}");
		return builder.toString();
	}
	
	public static enum Testcase1Enum{
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
	
	@ApiModel("查询测试用例结果集")
	public static class Testcase1QResultVO {

		@ApiModelProperty(value="状态码",allowableValues="200:成功,其他表示失败")
		private String code;
		@ApiModelProperty(value="返回信息描述")
		private String msg;
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
		
		public Testcase1QResultVO(String code,String msg){
			this.code = code;
			this.msg = msg;
		}
		public Testcase1QResultVO(){
		}
		
		@ApiModelProperty(value="查询列表")
		private List<Testcase1Result> resultList;
		public Testcase1QResultVO(List<Testcase1Entity> testcase1EntityList){
			this.code = ReturnMsgEnum.SUCCESS.getCode();
			this.msg =ReturnMsgEnum.SUCCESS.getMsg();
			if(testcase1EntityList==null||testcase1EntityList.size()==0){
				return;
			}
			Testcase1Result result = null;
			resultList = new ArrayList<Testcase1Result>(testcase1EntityList.size());
			for (Testcase1Entity tempEntity : testcase1EntityList) {
				result = new Testcase1Result();
				BeanCopierUtils.copyProperties( tempEntity,result);
				resultList.add(result);
			}
		}
		
		@Override
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("admin.modules.testcase1.vo.Testcase1QResultVO ={")
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
		
		public List<Testcase1Result> getResultList() {
			return resultList;
		}
		
		public void setResultList(List<Testcase1Result> resultList) {
			this.resultList = resultList;
		}
		
		public String getCode() {
			return code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
		public static Testcase1QResultVO ok(List<Testcase1Entity> testcase1EntityList,Testcase1QueryVO testcase1QueryVO) {
			Testcase1QResultVO testcase1QResultVO = new Testcase1QResultVO(testcase1EntityList);
			testcase1QResultVO.setPage(testcase1QueryVO.getPage());
			testcase1QResultVO.setRows(testcase1QueryVO.getRows());
			return testcase1QResultVO;
		}
	
		public static Testcase1QResultVO  fail() {
			 Testcase1QResultVO vo = new Testcase1QResultVO(null);
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
	 * 回执测试用例说明
	 * 
	 */
	@ApiModel("返回测试用例字段说明")
	public static class Testcase1Result {
		
		@ApiModelProperty(value="controller名称",example="填写例子")
		@ApiParam(value="controller名称",defaultValue="填写例子")
		private String controllerName;
		@ApiModelProperty(value="",example="填写例子")
		@ApiParam(value="",defaultValue="填写例子")
		private String describe;
		@ApiModelProperty(value="",example="填写例子")
		@ApiParam(value="",defaultValue="填写例子")
		private String method;
		@ApiModelProperty(value="主键",example="填写例子")
		@ApiParam(value="主键",defaultValue="填写例子")
		private Long testCaseId;
		@ApiModelProperty(value="",example="填写例子")
		@ApiParam(value="",defaultValue="填写例子")
		private Long deptId;
		@ApiModelProperty(value="创建时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
		@ApiParam(value="创建时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子") 
		@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
		private Date createdTime;
		@ApiModelProperty(value="更新时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
		@ApiParam(value="更新时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子") 
		@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
		private Date updatedTime;
		@ApiModelProperty(value="上次执行时间 格式yyyy-MM-dd HH:mm:ss",example="填写例子")
		@ApiParam(value="上次执行时间 格式yyyy-MM-dd HH:mm:ss",defaultValue = "填写例子") 
		@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
		private Date execLastTime;
		@ApiModelProperty(value="状态0:成功1:失败2:未执行",example="填写例子")
		@ApiParam(value="状态0:成功1:失败2:未执行",defaultValue="填写例子")
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
			builder.append("admin.modules.testcase1.vo.Testcase1Result ={")
			.append("controllerName=").append(controllerName)
			.append(",describe=").append(describe)
			.append(",method=").append(method)
			.append(",testCaseId=").append(testCaseId)
			.append(",deptId=").append(deptId)
			.append(",createdTime=").append(createdTime)
			.append(",updatedTime=").append(updatedTime)
			.append(",execLastTime=").append(execLastTime)
			.append(",status=").append(status)
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
		return page*rows+1;
	}
}
