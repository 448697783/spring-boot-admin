package admin.modules.swagger2Url.vo;
import admin.common.baseenum.BaseEnum.ReturnMsgEnum;
import admin.modules.swagger2Url.entity.Swagger2UrlEntity;
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
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-09-02 18:13:02
 */
@ApiModel("查询参数")
public class Swagger2UrlQueryVO {
	@Size(min=0,max=50,message="别名长度为0-50个字符")
	@ApiModelProperty(value="别名",example="填写例子")
	@ApiParam(value="别名",defaultValue="填写例子")
	private String name;
	
	@Min(value=0,message="值应大于0")
	@Max(value=9999999999L,message="值应小于9999999999L")
	@ApiModelProperty(value="",example="填写例子")
	@ApiParam(value="",defaultValue="填写例子")
	private Integer id;
	
	@Size(min=0,max=256,message="数据库url长度为0-256个字符")
	@ApiModelProperty(value="数据库url",example="填写例子")
	@ApiParam(value="数据库url",defaultValue="填写例子")
	private String url;
	
	@Min(value=0,message="乐观锁值应大于0")
	@Max(value=9999999999L,message="乐观锁值应小于9999999999L")
	@ApiModelProperty(value="乐观锁",example="填写例子")
	@ApiParam(value="乐观锁",defaultValue="填写例子")
	private Integer versionOptimizedLock;
	
	
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
	 * 设置：别名
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取：别名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置：数据库url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 获取：数据库url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置：乐观锁
	 */
	public void setVersionOptimizedLock(Integer versionOptimizedLock) {
		this.versionOptimizedLock = versionOptimizedLock;
	}
	
	/**
	 * 获取：乐观锁
	 */
	public Integer getVersionOptimizedLock() {
		return versionOptimizedLock;
	}
	public Swagger2UrlQResultVO ok(List<Swagger2UrlEntity> swagger2UrlEntityList) {
		return Swagger2UrlQResultVO.ok(swagger2UrlEntityList,this);
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.modules.swagger2Url.vo.Swagger2UrlQueryVO ={")
		.append("name=").append(name)
		.append(",id=").append(id)
		.append(",url=").append(url)
		.append(",versionOptimizedLock=").append(versionOptimizedLock)
		.append(",startTime=").append(DateUtils.format(startTime, DateUtils.DATE_TIME_PATTERN))
		.append(",endTime=").append(DateUtils.format(endTime, DateUtils.DATE_TIME_PATTERN))
		.append(",page=").append(page)
		.append(",rows=").append(rows)
		.append("}");
		return builder.toString();
	}
	
	public static enum Swagger2UrlEnum{
		name("name","别名"),
		id("id",""),
		url("url","数据库url"),
		version_optimized_lock("versionOptimizedLock","乐观锁");
		private String fieldName;
		private String remark;
		Swagger2UrlEnum(String fieldName,String remark){
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
	public static class Swagger2UrlQResultVO {

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
		
		public Swagger2UrlQResultVO(String code,String msg){
			this.code = code;
			this.msg = msg;
		}
		public Swagger2UrlQResultVO(){
		}
		
		@ApiModelProperty(value="查询列表")
		private List<Swagger2UrlResult> resultList;
		public Swagger2UrlQResultVO(List<Swagger2UrlEntity> swagger2UrlEntityList){
			this.code = ReturnMsgEnum.SUCCESS.getCode();
			this.msg =ReturnMsgEnum.SUCCESS.getMsg();
			if(swagger2UrlEntityList==null||swagger2UrlEntityList.size()==0){
				return;
			}
			Swagger2UrlResult result = null;
			resultList = new ArrayList<Swagger2UrlResult>(swagger2UrlEntityList.size());
			for (Swagger2UrlEntity tempEntity : swagger2UrlEntityList) {
				result = new Swagger2UrlResult();
				BeanCopierUtils.copyProperties( tempEntity,result);
				resultList.add(result);
			}
		}
		
		@Override
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("admin.modules.swagger2Url.vo.Swagger2UrlQResultVO ={")
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
		
		public List<Swagger2UrlResult> getResultList() {
			return resultList;
		}
		
		public void setResultList(List<Swagger2UrlResult> resultList) {
			this.resultList = resultList;
		}
		
		public String getCode() {
			return code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
		public static Swagger2UrlQResultVO ok(List<Swagger2UrlEntity> swagger2UrlEntityList,Swagger2UrlQueryVO swagger2UrlQueryVO) {
			Swagger2UrlQResultVO swagger2UrlQResultVO = new Swagger2UrlQResultVO(swagger2UrlEntityList);
			swagger2UrlQResultVO.setPage(swagger2UrlQueryVO.getPage());
			swagger2UrlQResultVO.setRows(swagger2UrlQueryVO.getRows());
			return swagger2UrlQResultVO;
		}
	
		public static Swagger2UrlQResultVO  fail() {
			 Swagger2UrlQResultVO vo = new Swagger2UrlQResultVO(null);
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
	 * 回执说明
	 * 
	 */
	@ApiModel("返回字段说明")
	public static class Swagger2UrlResult {
		
		@ApiModelProperty(value="别名",example="填写例子")
		@ApiParam(value="别名",defaultValue="填写例子")
		private String name;
		@ApiModelProperty(value="",example="填写例子")
		@ApiParam(value="",defaultValue="填写例子")
		private Integer id;
		@ApiModelProperty(value="数据库url",example="填写例子")
		@ApiParam(value="数据库url",defaultValue="填写例子")
		private String url;
		@ApiModelProperty(value="乐观锁",example="填写例子")
		@ApiParam(value="乐观锁",defaultValue="填写例子")
		private Integer versionOptimizedLock;
	
		
		/**
		 * 设置：别名
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * 获取：别名
		 */
		public String getName() {
			return name;
		}
	
		/**
		 * 设置：
		 */
		public void setId(Integer id) {
			this.id = id;
		}
		
		/**
		 * 获取：
		 */
		public Integer getId() {
			return id;
		}
	
		/**
		 * 设置：数据库url
		 */
		public void setUrl(String url) {
			this.url = url;
		}
		
		/**
		 * 获取：数据库url
		 */
		public String getUrl() {
			return url;
		}
	
		/**
		 * 设置：乐观锁
		 */
		public void setVersionOptimizedLock(Integer versionOptimizedLock) {
			this.versionOptimizedLock = versionOptimizedLock;
		}
		
		/**
		 * 获取：乐观锁
		 */
		public Integer getVersionOptimizedLock() {
			return versionOptimizedLock;
		}
		@Override
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("admin.modules.swagger2Url.vo.Swagger2UrlResult ={")
			.append("name=").append(name)
			.append(",id=").append(id)
			.append(",url=").append(url)
			.append(",versionOptimizedLock=").append(versionOptimizedLock)
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
