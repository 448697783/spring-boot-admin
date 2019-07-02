package admin.modules.takeoutUser.vo;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import admin.common.baseenum.BaseEnum.ReturnMsgEnum;
import admin.common.utils.BeanCopierUtils;
import admin.common.utils.DateUtils;
import admin.modules.takeoutUser.entity.TakeoutUserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-07-01 19:19:04
 */
@ApiModel("查询参数")
public class TakeoutUserQueryVO {
	@Min(value=0,message="机器码值应大于0")
	@Max(value=9999999999L,message="机器码值应小于9999999999L")
	@ApiModelProperty(value="机器码",example="填写例子")
	@ApiParam(value="机器码",defaultValue="填写例子")
	private Integer fid;
	
	@Size(min=0,max=50,message="姓名长度为0-50个字符")
	@ApiModelProperty(value="姓名",example="填写例子")
	@ApiParam(value="姓名",defaultValue="填写例子")
	private String fname;
	
	@Size(min=0,max=20,message="电话长度为0-20个字符")
	@ApiModelProperty(value="电话",example="填写例子")
	@ApiParam(value="电话",defaultValue="填写例子")
	private String fphonenumber;
	
	@Size(min=0,max=200,message="地址长度为0-200个字符")
	@ApiModelProperty(value="地址",example="填写例子")
	@ApiParam(value="地址",defaultValue="填写例子")
	private String faddress;
	
	
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
	 * 设置：机器码
	 */
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	
	/**
	 * 获取：机器码
	 */
	public Integer getFid() {
		return fid;
	}

	/**
	 * 设置：姓名
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * 设置：电话
	 */
	public void setFphonenumber(String fphonenumber) {
		this.fphonenumber = fphonenumber;
	}
	
	/**
	 * 获取：电话
	 */
	public String getFphonenumber() {
		return fphonenumber;
	}

	/**
	 * 设置：地址
	 */
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	
	/**
	 * 获取：地址
	 */
	public String getFaddress() {
		return faddress;
	}
	public TakeoutUserQResultVO ok(List<TakeoutUserEntity> takeoutUserEntityList) {
		return TakeoutUserQResultVO.ok(takeoutUserEntityList,this);
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.modules.takeoutUser.vo.TakeoutUserQueryVO ={")
		.append("fid=").append(fid)
		.append(",fname=").append(fname)
		.append(",fphonenumber=").append(fphonenumber)
		.append(",faddress=").append(faddress)
		.append(",startTime=").append(DateUtils.format(startTime, DateUtils.DATE_TIME_PATTERN))
		.append(",endTime=").append(DateUtils.format(endTime, DateUtils.DATE_TIME_PATTERN))
		.append(",page=").append(page)
		.append(",rows=").append(rows)
		.append("}");
		return builder.toString();
	}
	
	public static enum TakeoutUserEnum{
		fID("fid","机器码"),
		fName("fname","姓名"),
		fPhoneNumber("fphonenumber","电话"),
		fAddress("faddress","地址");
		private String fieldName;
		private String remark;
		TakeoutUserEnum(String fieldName,String remark){
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
	public static class TakeoutUserQResultVO {

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
		
		public TakeoutUserQResultVO(String code,String msg){
			this.code = code;
			this.msg = msg;
		}
		public TakeoutUserQResultVO(){
		}
		
		@ApiModelProperty(value="查询列表")
		private List<TakeoutUserResult> resultList;
		public TakeoutUserQResultVO(List<TakeoutUserEntity> takeoutUserEntityList){
			this.code = ReturnMsgEnum.SUCCESS.getCode();
			this.msg =ReturnMsgEnum.SUCCESS.getMsg();
			if(takeoutUserEntityList==null||takeoutUserEntityList.size()==0){
				return;
			}
			TakeoutUserResult result = null;
			resultList = new ArrayList<TakeoutUserResult>(takeoutUserEntityList.size());
			for (TakeoutUserEntity tempEntity : takeoutUserEntityList) {
				result = new TakeoutUserResult();
				BeanCopierUtils.copyProperties( tempEntity,result);
				resultList.add(result);
			}
		}
		
		@Override
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("admin.modules.takeoutUser.vo.TakeoutUserQResultVO ={")
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
		
		public List<TakeoutUserResult> getResultList() {
			return resultList;
		}
		
		public void setResultList(List<TakeoutUserResult> resultList) {
			this.resultList = resultList;
		}
		
		public String getCode() {
			return code;
		}
	
		public void setCode(String code) {
			this.code = code;
		}
		public static TakeoutUserQResultVO ok(List<TakeoutUserEntity> takeoutUserEntityList,TakeoutUserQueryVO takeoutUserQueryVO) {
			TakeoutUserQResultVO takeoutUserQResultVO = new TakeoutUserQResultVO(takeoutUserEntityList);
			takeoutUserQResultVO.setPage(takeoutUserQueryVO.getPage());
			takeoutUserQResultVO.setRows(takeoutUserQueryVO.getRows());
			return takeoutUserQResultVO;
		}
	
		public static TakeoutUserQResultVO  fail() {
			 TakeoutUserQResultVO vo = new TakeoutUserQResultVO(null);
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
	public static class TakeoutUserResult {
		
		@ApiModelProperty(value="机器码",example="填写例子")
		@ApiParam(value="机器码",defaultValue="填写例子")
		private Integer fid;
		@ApiModelProperty(value="姓名",example="填写例子")
		@ApiParam(value="姓名",defaultValue="填写例子")
		private String fname;
		@ApiModelProperty(value="电话",example="填写例子")
		@ApiParam(value="电话",defaultValue="填写例子")
		private String fphonenumber;
		@ApiModelProperty(value="地址",example="填写例子")
		@ApiParam(value="地址",defaultValue="填写例子")
		private String faddress;
	
		
		/**
		 * 设置：机器码
		 */
		public void setFid(Integer fid) {
			this.fid = fid;
		}
		
		/**
		 * 获取：机器码
		 */
		public Integer getFid() {
			return fid;
		}
	
		/**
		 * 设置：姓名
		 */
		public void setFname(String fname) {
			this.fname = fname;
		}
		
		/**
		 * 获取：姓名
		 */
		public String getFname() {
			return fname;
		}
	
		/**
		 * 设置：电话
		 */
		public void setFphonenumber(String fphonenumber) {
			this.fphonenumber = fphonenumber;
		}
		
		/**
		 * 获取：电话
		 */
		public String getFphonenumber() {
			return fphonenumber;
		}
	
		/**
		 * 设置：地址
		 */
		public void setFaddress(String faddress) {
			this.faddress = faddress;
		}
		
		/**
		 * 获取：地址
		 */
		public String getFaddress() {
			return faddress;
		}
		@Override
		public String toString(){
			StringBuilder builder = new StringBuilder();
			builder.append("admin.modules.takeoutUser.vo.TakeoutUserResult ={")
			.append("fid=").append(fid)
			.append(",fname=").append(fname)
			.append(",fphonenumber=").append(fphonenumber)
			.append(",faddress=").append(faddress)
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
