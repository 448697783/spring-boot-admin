package admin.modules.takeoutUser.entity;
import admin.modules.sys.entity.SysRoleDeptEntity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-07-01 19:19:04
 */
public class TakeoutUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//机器码
	private Integer fid;
	//姓名
	private String fname;
	//电话
	private String fphonenumber;
	//地址
	private String faddress;

	//  数据权限
	private List<SysRoleDeptEntity> sysRoleDeptEntity;
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
		builder.append("admin.entity.TakeoutUserEntity [")
		.append(",fid=").append(fid)
		.append(",fname=").append(fname)
		.append(",fphonenumber=").append(fphonenumber)
		.append(",faddress=").append(faddress)
		.append(",startTime=").append(startTime)
		.append(",endTime=").append(endTime)
		.append("]");
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
	public List<SysRoleDeptEntity> getSysRoleDeptEntity() {
		return sysRoleDeptEntity;
	}

	public void setSysRoleDeptEntity(List<SysRoleDeptEntity> sysRoleDeptEntity) {
		this.sysRoleDeptEntity = sysRoleDeptEntity;
	}
}
