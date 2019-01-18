package admin.modules.generator.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 菜单管理
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2017-09-07 00:18:45
 */
public class ModuleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long menuId;
	//父菜单ID，一级菜单为0
	private Long parentId;
	//菜单名称
	private String name;
	//菜单URL
	private String url;
	//授权(多个用逗号分隔，如：user:list,user:create)
	private String perms;
	//类型   0：目录   1：菜单   2：按钮
	private Integer type;
	//菜单图标
	private String icon;
	//排序	 
	private Integer orderNum;
	
	private Boolean open;


	/**
	 * 设置：
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	/**
	 * 获取：
	 */
	public Long getMenuId() {
		return menuId;
	}
	/**
	 * 设置：父菜单ID，一级菜单为0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父菜单ID，一级菜单为0
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：菜单名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：菜单名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：菜单URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：菜单URL
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：授权(多个用逗号分隔，如：user:list,user:create)
	 */
	public void setPerms(String perms) {
		this.perms = perms;
	}
	/**
	 * 获取：授权(多个用逗号分隔，如：user:list,user:create)
	 */
	public String getPerms() {
		return perms;
	}
	/**
	 * 设置：类型   0：目录   1：菜单   2：按钮
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型   0：目录   1：菜单   2：按钮
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：菜单图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取：菜单图标
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrderNum() {
		return orderNum;
	}
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("admin.modules.generator.entity.ModuleEntity [")
		.append(",menuId=").append(menuId)
		.append(",parentId=").append(parentId)
		.append(",name=").append(name)
		.append(",url=").append(url)
		.append(",perms=").append(perms)
		.append(",type=").append(type)
		.append(",icon=").append(icon)
		.append(",orderNum=").append(orderNum)
		.append("]");
		return builder.toString();
	}
	
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}

	public static enum ModuleEnum{
		menuId("menu_id",""),
		parentId("parent_id","父菜单ID，一级菜单为0"),
		name("name","菜单名称"),
		url("url","菜单URL"),
		perms("perms","授权(多个用逗号分隔，如：user:list,user:create)"),
		type("type","类型   0：目录   1：菜单   2：按钮"),
		icon("icon","菜单图标"),
		orderNum("order_num","排序");
		private String fieldName;
		private String remark;
		ModuleEnum(String fieldName,String remark){
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
