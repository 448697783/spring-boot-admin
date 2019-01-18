package admin.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.zaxxer.hikari.HikariDataSource;

import admin.common.exception.BaseException;
import admin.datasource.MultiDataSource;
import admin.modules.automatic.entity.ColumnEntity;
import admin.modules.automatic.entity.TableEntity;
import admin.modules.automatic.vo.CoderGeneratorVO;

/**
 * 代码生成器   工具类
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午11:40:24
 */
public class GenUtils {

	public static List<String> getTemplates(CoderGeneratorVO vo){
		MultiDataSource multiDataSource = (MultiDataSource) SpringContextUtils.getBean("datasource");
		HikariDataSource bean = (HikariDataSource) multiDataSource.getDataSource();
		List<String> templates = new ArrayList<String>();
		if (bean.getJdbcUrl().indexOf("oracle")!=-1) {
			if(vo.isDao()){
				templates.add("template/Dao.java.vm");
//				templates.add("template/OracleDao.xml.vm");
			}
		}
		if(bean.getJdbcUrl().indexOf("oracle")==-1){
			if(vo.isDao()){
				templates.add("template/Dao.java.vm");
//				templates.add("template/Dao.xml.vm");
			}
		}
		if(vo.isEntity()){
			templates.add("template/Entity.java.vm");
		}
		if(vo.isService()){
			templates.add("template/Service.java.vm");
		}
		if(vo.isIservice()){
			templates.add("template/ServiceImpl.java.vm");
		}
		if(vo.isController()){
			templates.add("template/Controller.java.vm");
		}
		if(vo.isHtml()){
			templates.add("template/list.html.vm");
			templates.add("template/list.js.vm");
		}
		if(vo.isMenu()){
			templates.add("template/menu.sql.vm");
		}
		if(vo.isQueryVo()){
			templates.add("template/QueryVO.java.vm");
		}
		if(vo.isEditVo()){
			templates.add("template/SaveVO.java.vm");
			templates.add("template/EditVO.java.vm");
			templates.add("template/DeleteVO.java.vm");
		}
		return templates;
	}
	
	/**
	 * 生成代码
	 * @param vo 
	 */
	public static void generatorCode(Map<String, String> table,
			List<Map<String, Object>> columns, ZipOutputStream zip,String projectName,String packageName, CoderGeneratorVO vo){
		//配置信息
		Configuration config = getConfig();
		config.addProperty("package", packageName);
		List<String> importL = new ArrayList<>();
		//表信息
		TableEntity tableEntity = new TableEntity();
		tableEntity.setTableName(table.get("TABLENAME"));
		tableEntity.setComments(table.get("TABLECOMMENT")==null?"":table.get("TABLECOMMENT"));
		//表名转换成Java类名
		String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix"));
		tableEntity.setClassName(!StringUtils.isNotEmpty(vo.getTableAliase())?className:vo.getTableAliase());
		tableEntity.setClassname(!StringUtils.isNotEmpty(vo.getTableAliase())?WordUtils.uncapitalize(className):WordUtils.uncapitalize(vo.getTableAliase()));
		//列信息
		List<ColumnEntity> columsList = new ArrayList<>();
		for(Map<String, Object> column : columns){
			ColumnEntity columnEntity = new ColumnEntity();
			columnEntity.setColumnName(column.get("COLUMNNAME")==null?null:column.get("COLUMNNAME").toString());
			String type = column.get("DATATYPE")==null?null:column.get("DATATYPE").toString();
			String scale = column.get("DATA_SCALE")==null?null:column.get("DATA_SCALE").toString();
			String pre = column.get("DATA_PRECISION")==null?null:column.get("DATA_PRECISION").toString();
			String len = column.get("DATA_LENGTH")==null?null:column.get("DATA_LENGTH").toString();
//			long a = new BigDecimal(1).longValue();
			

			if(column.get("COLUMNNAME").equals("UPDATED_ON")) {
				column.get("COLUMNNAME");
			}
			switch (type) {
			case "NUMBER":
				if(StringUtils.isNotBlank(scale)){
					if(Integer.valueOf(scale)==0){
						if(StringUtils.isNotBlank(pre)&&Integer.valueOf(pre)<=10){
							columnEntity.setDataType(JdbcType.INTEGER.name().replaceAll("\\(.*\\)", ""));
						}else if(StringUtils.isNotBlank(pre)&&Integer.valueOf(pre)>10){
							System.out.println(pre+"11111111111111");
							columnEntity.setDataType(JdbcType.BIGINT.name().replaceAll("\\(.*\\)", ""));
						}else{
							columnEntity.setDataType(JdbcType.BIGINT.name().replaceAll("\\(.*\\)", ""));
						}
					}else if(Integer.valueOf(scale)<7&&Integer.valueOf(scale)>0){
						columnEntity.setDataType(JdbcType.FLOAT.name().replaceAll("\\(.*\\)", ""));
					}else if(Integer.valueOf(scale)>7){
						columnEntity.setDataType(JdbcType.DOUBLE.name().replaceAll("\\(.*\\)", ""));
					}
				}else{
					columnEntity.setDataType(JdbcType.BIGINT.name().replaceAll("\\(.*\\)", ""));
				}
				
				break;
			case "TIMESTAMP":
			case "DATE":
				columnEntity.setDataType(JdbcType.DATE.name().replaceAll("\\(.*\\)", ""));
				break;
			case "VARCHAR2":
			case "CHAR":
				columnEntity.setDataType(JdbcType.VARCHAR.name().replaceAll("\\(.*\\)", ""));
				break;
			default:
				columnEntity.setDataType(column.get("DATATYPE").toString().replaceAll("\\(.*\\)", "").toString());
				break;
			}
//			columnEntity.setDataType(column.get("DATATYPE"));
			columnEntity.setColumnKey(column.get("COLUMNKEY")==null?null:column.get("COLUMNKEY").toString());
			columnEntity.setComments(column.get("COLUMNCOMMENT")==null?"没有描述":column.get("COLUMNCOMMENT")==null?null:column.get("COLUMNCOMMENT").toString());
			columnEntity.setExtra(column.get("EXTRA")==null?null:column.get("EXTRA").toString());
//			if(map.get("CHARACTER_MAXIMUM_LENGTH")!=null&&map.get("CHARACTER_MAXIMUM_LENGTH").toString().length()>0){
//				map.put("CHARACTER_MAXIMUM_LENGTH","@Size(min=0,max="+map.get("CHARACTER_MAXIMUM_LENGTH")+",message=\""+map.get("COLUMNCOMMENT")+"长度为0-"+map.get("CHARACTER_MAXIMUM_LENGTH")+"个字符\")");
//			}
//			if(map.get("NUMERIC_PRECISION")!=null&&map.get("NUMERIC_PRECISION").toString().length()>0){
//				map.put("NUMERIC_PRECISION","@Min(value=0,message=\""+map.get("COLUMNCOMMENT")+"值应大于0)\n@Max(value="+map.get("NUMERIC_PRECISION")+","+map.get("COLUMNCOMMENT")+"值应小于"+map.get("NUMERIC_PRECISION")+"\")");
//			}
			StringBuilder tempLen = new StringBuilder();
			if(column.get("NUMERIC_PRECISION")!=null&&column.get("NUMERIC_PRECISION").toString().length()>0) {
				int len1 = Integer.parseInt(column.get("NUMERIC_PRECISION").toString());
				if(len1>19) {
					column.put("DATATYPE","BigDecimal");
				}
				for(int i=0;i<len1;i++) {
					if(i>17) {
						break;
					}
					tempLen.append("9");
				}
				if(len1>9)
					tempLen.append("L");
			}

			String attrType = config.getString(columnEntity.getDataType(), "unknowType");
			switch (attrType) {
			case "Integer":
			case "Long":
			case "Float":
			case "Double":
				columnEntity.setNumericPrecision(column.get("NUMERIC_PRECISION")==null?null:"@Min(value=0,message=\""+column.get("COLUMNCOMMENT")+"值应大于0\")\n\t@Max(value="+tempLen+",message=\""+column.get("COLUMNCOMMENT")+"值应小于"+tempLen+"\")");
				break;
			case "BigDecimal":
				columnEntity.setNumericPrecision(column.get("NUMERIC_PRECISION")==null?null:"@DecimalMax(value=0,message=\""+column.get("COLUMNCOMMENT")+"值应大于0\")\n\t@DecimalMax(value="+tempLen+",message=\""+column.get("COLUMNCOMMENT")+"值应小于"+tempLen+"\")");
				break;
			case "String":
				columnEntity.setCharacterMaximumLength(column.get("CHARACTER_MAXIMUM_LENGTH")==null?null:"@Size(min=0,max="+column.get("CHARACTER_MAXIMUM_LENGTH")+",message=\""+column.get("COLUMNCOMMENT")+"长度为0-"+column.get("CHARACTER_MAXIMUM_LENGTH")+"个字符\")");
				break;
			case "Date":
				break;
			default:
				break;
			}
			columnEntity.setIsNullable(column.get("IS_NULLABLE")==null?null:("N".equals(column.get("IS_NULLABLE").toString())?"@NotNull(message=\""+column.get("COLUMNCOMMENT")+"不能为空\")":""));

			//列名转换成Java属性名
			String attrName = columnToJava(columnEntity.getColumnName());
			columnEntity.setAttrName(attrName);
			columnEntity.setAttrname(StringUtils.uncapitalize(attrName));
			
			//列的数据类型，转换成Java类型
			columnEntity.setAttrType(attrType);
			
			//是否主键
			if(("PRI".equalsIgnoreCase(column.get("COLUMNKEY")==null?null:column.get("COLUMNKEY").toString())||columnEntity.getColumnName().equalsIgnoreCase(column.get("COLUMNKEY")==null?null:column.get("COLUMNKEY").toString())) && tableEntity.getPk() == null){
				tableEntity.setPk(columnEntity);
			}
			
			columsList.add(columnEntity);
		}
		tableEntity.setColumns(columsList);
		
		//没主键，则第一个字段为主键
		if(tableEntity.getPk() == null){
			tableEntity.setPk(tableEntity.getColumns().get(0));
		}
		
		//设置velocity资源加载器
		Properties prop = new Properties();  
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");  
		Velocity.init(prop);
		
		//封装模板数据
		Map<String, Object> map = new HashMap<>();
		map.put("hasBigDecimal", tableEntity.getTableName());
		map.put("tableName", tableEntity.getTableName());
		map.put("comments", tableEntity.getComments());
		map.put("pk", tableEntity.getPk());
		map.put("className", tableEntity.getClassName());
		map.put("classname", tableEntity.getClassname());
		map.put("pathName", tableEntity.getClassname().toLowerCase());
		map.put("columns", tableEntity.getColumns());
		map.put("package",packageName);
		map.put("isSql",StringUtils.isNotBlank(vo.getSql())&&(vo.getTables()==null||vo.getTables().size()!=0)?true:false);
		map.put("module",(!StringUtils.isNotEmpty(vo.getModeName())?getModules(table.get("TABLENAME")).toLowerCase():vo.getModeName()));

		map.put("author", config.getString("author"));
		map.put("email", config.getString("email"));
		map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        VelocityContext context = new VelocityContext(map);
        
        //获取模板列表
		List<String> templates = getTemplates(vo);
		fromVelocityToZipSteam(zip,projectName, packageName.replace(".", "/")+"/modules/"+map.get("module"), tableEntity, context, templates);
	}
	
	private static String getModules(String tableName){
//		Pattern pattern = Pattern.compile("_(.*?)(_|$)");// 匹配的模式
//		if (StringUtils.isNotBlank(tableName)) {
//			Matcher m = pattern.matcher(tableName);
//			while (m.find()) {
//				if (StringUtils.isNotBlank(m.group(1))) {
//					return m.group(1).toLowerCase();
//				}
//			}
//		}else{
//			return "module";
//		}
		return tableName.replace("_", "");
//		return tableName;
	}

	public static void fromVelocityToZipSteam(ZipOutputStream zip,String projectName, String  pack, TableEntity tableEntity,
			VelocityContext context, List<String> templates) {
		for(String template : templates){
			//渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);
			
			try {
				
			
				//添加到zip
				zip.putNextEntry(new ZipEntry(getFileName(projectName,template, tableEntity, pack,context.get("module").toString())));  
				IOUtils.write(sw.toString(), zip, "UTF-8");
				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				throw new BaseException("渲染模板失败，模版名：" + templates, e);
			}
		}
	}
	
	
	/**
	 * 列名转换成Java属性名
	 */
	public static String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
	}
	
	/**
	 * 表名转换成Java类名
	 */
	public static String tableToJava(String tableName, String tablePrefix) {
		if(StringUtils.isNotBlank(tablePrefix)){
//			String[] split = tablePrefix.split("_");
//			if(split.length){
//				
//			}
			tableName = tableName.replace(tablePrefix, "");
		}
		return columnToJava(tableName);
	}
	
	/**
	 * 获取配置信息
	 */
	public static Configuration getConfig(){
		try {
			return new PropertiesConfiguration("generator.properties");
		} catch (ConfigurationException e) {
			throw new BaseException("获取配置文件失败，", e);
		}
	}
	
	/**
	 * 获取文件名
	 */
	public static String getFileName(String projectName,String template, TableEntity tableEntity, String packageName,String module){
		String packagePath = projectName+"/src/main" + File.separator + "java" + File.separator;
		if(StringUtils.isNotBlank(packageName)){
			packagePath += packageName.replace(".", File.separator) + File.separator;
		}
		
		if(template.contains("Entity.java.vm")){
			return packagePath + "entity" + File.separator + tableEntity.getClassName() + "Entity.java";
		}
		
		if(template.contains("Dao.java.vm")){
			return packagePath + "dao" + File.separator +  tableEntity.getClassName() + "Dao.java";
		}

		if(template.contains("Service.java.vm")){
			return packagePath + "service" + File.separator + "I"+ tableEntity.getClassName() + "Service.java";
		}
		
		if(template.contains("ServiceImpl.java.vm")){
			return packagePath + "service" + File.separator + "impl" + File.separator +  tableEntity.getClassName() + "ServiceImpl.java";
		}
		
		if(template.contains("Controller.java.vm")){
			return packagePath + "controller" + File.separator +  tableEntity.getClassName() + "Controller.java";
		}
		if(template.contains("OracleDao.xml.vm")){
			return projectName+"/src/main" + File.separator + "resources" + File.separator + "mapper" + File.separator + module + File.separator +  tableEntity.getClassName() + "Dao.xml";
		}
		if(template.contains("Dao.xml.vm")){
			return projectName+"/src/main" + File.separator + "resources" + File.separator + "mapper" + File.separator + module + File.separator +  tableEntity.getClassName() + "Dao.xml";
		}

		if(template.contains("list.html.vm")){
			return projectName+"/src/main" + File.separator + "resources" + File.separator + "views" + File.separator
					+ "modules" + File.separator + module + File.separator +  tableEntity.getClassname() + ".html";
		}
		
		if(template.contains("list.js.vm")){
			return projectName+"/src/main" + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator
					+ "modules" + File.separator + module + File.separator +  tableEntity.getClassname() + ".js";
		}

		if(template.contains("menu.sql.vm")){
			return  tableEntity.getClassName().toLowerCase() + "_menu.sql";
		}
		
		if(template.contains("SaveVO.java.vm")){
			return packagePath + "vo" + File.separator + tableEntity.getClassName() + "SaveVO.java";
		}
		
		if(template.contains("EditVO.java.vm")){
			return packagePath + "vo" + File.separator + tableEntity.getClassName() + "EditVO.java";
		}
		
		if(template.contains("QueryVO.java.vm")){
			return packagePath + "vo" + File.separator + tableEntity.getClassName() + "QueryVO.java";
		}
		
		if(template.contains("DeleteVO.java.vm")){
			return packagePath + "vo" + File.separator + tableEntity.getClassName() + "DeleteVO.java";
		}
		
		return null;
	}
}
