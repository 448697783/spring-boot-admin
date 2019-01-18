package admin.modules.automatic.vo;

import java.util.List;

public class CoderGeneratorVO {
	private List<String> tables;
	private String projectName;
	private String packageName;
	private String modeName;
	private String tableAliase;
	private String aliase;
	private boolean queryVo;
	private boolean editVo;
	private boolean entity;
	private boolean controller;
	private boolean service;
	private boolean iservice;
	private boolean dao;
	private boolean xml;
	private boolean html;
	private boolean menu;
	private String sql;
	
	public boolean isEntity() {
		return entity;
	}
	public void setEntity(boolean entity) {
		this.entity = entity;
	}
	public boolean isController() {
		return controller;
	}
	public void setController(boolean controller) {
		this.controller = controller;
	}
	public boolean isService() {
		return service;
	}
	public void setService(boolean service) {
		this.service = service;
	}
	public boolean isIservice() {
		return iservice;
	}
	public void setIservice(boolean iservice) {
		this.iservice = iservice;
	}
	public boolean isDao() {
		return dao;
	}
	public void setDao(boolean dao) {
		this.dao = dao;
	}
	public boolean isXml() {
		return xml;
	}
	public void setXml(boolean xml) {
		this.xml = xml;
	}
	public boolean isHtml() {
		return html;
	}
	public void setHtml(boolean html) {
		this.html = html;
	}
	public boolean isMenu() {
		return menu;
	}
	public void setMenu(boolean menu) {
		this.menu = menu;
	}
	public List<String> getTables() {
		return tables;
	}
	public void setTables(List<String> tables) {
		this.tables = tables;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public boolean isEditVo() {
		return editVo;
	}
	public void setEditVo(boolean editVo) {
		this.editVo = editVo;
	}
	public boolean isQueryVo() {
		return queryVo;
	}
	public void setQueryVo(boolean queryVo) {
		this.queryVo = queryVo;
	}
	public String getAliase() {
		return aliase;
	}
	public void setAliase(String aliase) {
		this.aliase = aliase;
	}
	public String getModeName() {
		return modeName;
	}
	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
	public String getTableAliase() {
		return tableAliase;
	}
	public void setTableAliase(String tableAliase) {
		this.tableAliase = tableAliase;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
}
