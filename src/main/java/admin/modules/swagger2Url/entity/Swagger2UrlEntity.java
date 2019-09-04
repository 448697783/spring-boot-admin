package admin.modules.swagger2Url.entity;
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
 * @date 2019-09-02 18:13:02
 */
public class Swagger2UrlEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//别名
	private String name;
	//
	private Integer id;
	//数据库url
	private String url;
	//乐观锁
	private Integer versionOptimizedLock;

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
		builder.append("admin.entity.Swagger2UrlEntity [")
		.append(",name=").append(name)
		.append(",id=").append(id)
		.append(",url=").append(url)
		.append(",versionOptimizedLock=").append(versionOptimizedLock)
		.append(",startTime=").append(startTime)
		.append(",endTime=").append(endTime)
		.append("]");
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
}
