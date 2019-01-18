package admin.modules.databaseinfo.entity;


import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 数据库信息表
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-06-15 17:49:11
 */
public class DatabaseInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//别名
	private String aliase;
	//数据库url
	private String url;
	//密码
	private String passWord;
	//用户名
	private String userName;
	//数据驱动
	private String driverClassName;

	/**
	 * 设置：别名
	 */
	public void setAliase(String aliase) {
		this.aliase = aliase;
	}
	/**
	 * 获取：别名
	 */
	public String getAliase() {
		return aliase;
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
	 * 设置：密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * 获取：密码
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：数据驱动
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	/**
	 * 获取：数据驱动
	 */
	public String getDriverClassName() {
		return driverClassName;
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.entity.DatabaseInfoEntity [")
		.append(",aliase=").append(aliase)
		.append(",url=").append(url)
		.append(",passWord=").append(passWord)
		.append(",userName=").append(userName)
		.append(",driverClassName=").append(driverClassName)
		.append("]");
		return builder.toString();
	}
	
	public static enum DatabaseInfoEnum{
		aliase("aliase","别名"),
		url("url","数据库url"),
		pass_word("passWord","密码"),
		user_name("userName","用户名"),
		driver_class_name("driverClassName","数据驱动");
		private String fieldName;
		private String remark;
		DatabaseInfoEnum(String fieldName,String remark){
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
