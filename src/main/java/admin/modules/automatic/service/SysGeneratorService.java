package admin.modules.automatic.service;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zaxxer.hikari.HikariDataSource;

import admin.common.utils.GenUtils;
import admin.datasource.MultiDataSource;
import admin.modules.automatic.dao.OracleDao;
import admin.modules.automatic.dao.SysGeneratorDao;
import admin.modules.automatic.vo.CoderGeneratorVO;

/**
 * 代码生成器
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2016年12月19日 下午3:33:38
 */
@Service
public class SysGeneratorService {
	@Autowired
	private SysGeneratorDao sysGeneratorDao;
	@Autowired
	private MultiDataSource multiDataSource;
	@Autowired
	private OracleDao oracleDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	

	public List<Map<String, Object>> queryList(Map<String, Object> map) {
		HikariDataSource bean = (HikariDataSource) multiDataSource.getDataSource();
		if (bean.getJdbcUrl().indexOf("oracle")!=-1) { 
			if(map.get("tableName")!=null){
				map.put("tableName", map.get("tableName").toString().toUpperCase());
			}
			return oracleDao.queryList(map);
		}else{
			return sysGeneratorDao.queryList(map);
		}
	}
	public int queryTotal(Map<String, Object> map) {
		HikariDataSource bean = (HikariDataSource) multiDataSource.getDataSource();

		if (bean.getJdbcUrl().indexOf("oracle")!=-1) { 
			if(map.get("tableName")!=null){
				map.put("tableName", map.get("tableName").toString().toUpperCase());
			}
			return oracleDao.queryTotal(map);
		}else{
			return sysGeneratorDao.queryTotal(map);
		}
	}
	public Map<String, String> queryTable(String tableName) {
		HikariDataSource bean = (HikariDataSource) multiDataSource.getDataSource();
		if (bean.getJdbcUrl().indexOf("oracle")!=-1) { 
			return oracleDao.queryTable(tableName);
		}else{
			return sysGeneratorDao.queryTable(tableName);
		}
	}
	public List<Map<String, Object>> queryColumns(String tableName) {
		HikariDataSource bean = (HikariDataSource) multiDataSource.getDataSource();
		if (bean.getJdbcUrl().indexOf("oracle")!=-1) {
			return oracleDao.queryColumns(tableName);
		}else{
			return sysGeneratorDao.queryColumns(tableName);
		}
	}
	public Map<String, String> queryColumnsForSql(String sql) {
		Map<String,String> map = new HashMap<>();
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			Statement createStatement = connection.createStatement();
			ResultSet executeQuery = createStatement.executeQuery(sql);
			ResultSetMetaData metaData = executeQuery.getMetaData();
			int count = metaData.getColumnCount();
			for (int i = 1; i < count+1; i++) {
				metaData.getColumnLabel(i);
				metaData.getColumnTypeName(i);
				metaData.getCatalogName(i);
				map.put(metaData.getColumnName(i), metaData.getColumnTypeName(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public byte[] generatorCode(CoderGeneratorVO vo) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		Map<String,String> map = new HashMap<>();
		if(StringUtils.isNotBlank(vo.getSql())&&(vo.getTables()==null||vo.getTables().size()!=0)) {
			
//			queryColumnsForSql(vo.getSql());(\\w+)?,?\\s*(\\w+)\\s*
			String columsAliasRegEx1 =  "select([\\s*\\w*,\\.]*)from";
			Pattern columsAliaspattern = Pattern.compile(columsAliasRegEx1,Pattern.CASE_INSENSITIVE);
			Matcher columsAliasmatcher = columsAliaspattern.matcher(vo.getSql());
			HashMap<String,String> columsAliasMap = new HashMap<>();
			while (columsAliasmatcher.find()) {
				String group = columsAliasmatcher.group(1);
				String[] split = group.trim().split(",");
				for (int i = 0; i < split.length; i++) {
					String[] split2 = split[i].trim().split("\\s+");
					if(split2.length==2) {
						columsAliasMap.put(split2[1].trim().replaceAll("\\w*\\.", ""), split2[0].trim().replaceAll("\\w*\\.", ""));
					}
				}
			}
			
			
			String tableNamesRegEx1 =  "(from|into)([\\s*\\w*,)]*)(?:where|left|start|on|$|;)";
//			String tableNamesRegEx1 =  "(from|join)(,?(\\s*\\w*\\.)?(\\s*\\w*\\s*)(\\w*))(?:where|left|start|on|$|;)?";

		    Pattern pattern = Pattern.compile(tableNamesRegEx1,Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(vo.getSql());
		    String tempTableName,tempStr;
		    List<Map<String, Object>> columns =  new ArrayList<>();
		    Set setTableNames =  new HashSet<>();
		    Set setCloumnNames =  new HashSet<>();
		    Map<String, String> queryColumnsForSql = queryColumnsForSql(vo.getSql());

		    while(matcher.find()){
		    	tempStr = matcher.group(2);
		    	String[] split = tempStr.trim().split("[,\\s]");
		    	for (int i = 0; i < split.length; i++) {
		    		String str =  split[i].trim().replaceAll("\\s+\\w+", "");
		    		if(setTableNames.add(str)) {
		    			List<Map<String, Object>> queryColumns = queryColumns(str);
		    			for (int j = 0; j < queryColumns.size(); j++) {
		    				if(setCloumnNames.add(queryColumns.get(j).get("COLUMNNAME"))){
		    					String columsAlias = columsAliasMap.get(queryColumns.get(j).get("COLUMNNAME"));
		    					if(queryColumns.get(j).get("COLUMNNAME").equals("NOTITY_FINISH_TIME")) {
		    						queryColumns.get(j).get("COLUMNNAME");
		    					}
		    					if(columsAlias!=null) {
		    						if(queryColumnsForSql.containsKey(columsAlias)) {
		    							Map<String, Object> map2 = queryColumns.get(j);
		    							map2.put(columsAlias, queryColumns.get(j));
		    							columns.add(map2);
		    						}
		    					}else 
		    						if(queryColumnsForSql.containsKey(queryColumns.get(j).get("COLUMNNAME"))) 
		    							columns.add(queryColumns.get(j));
		    				}
						}
		    		}
				}
		    }
		    int size = columns.size();
		    Set<String> keySet = queryColumnsForSql.keySet();
		    Iterator<String> iterator = keySet.iterator();
		    
		    while(iterator.hasNext()) {
		    	boolean isHave = false;
		    	String next = iterator.next();
		    	for (int i = 0; i < size; i++) {
		    		String columnname = (String) columns.get(i).get("COLUMNNAME");
		    		if(next.equals(columnname)) {
		    			isHave = true;
		    			break;
		    		}
		    	}
		    	if(!isHave) {
					HashMap mapCloums = new HashMap<String, Object>();
		    		mapCloums.put("IS_NULLABLE", "NO");
					mapCloums.put("DATATYPE", queryColumnsForSql.get(next));
					mapCloums.put("COLUMNCOMMENT", "NO");
					mapCloums.put("COLUMNKEY", "");
					mapCloums.put("EXTRA", "");
					mapCloums.put("COLUMNNAME", next);
					mapCloums.put("NUMERIC_PRECISION", "");
					mapCloums.put("COLUMNCOMMENT", "别名");
					columns.add(mapCloums);
		    	}
		    }
		    
		    
//			String tableNamesRegEx =  "(from|join)\\s+(\\w+)\\s*";

//		    while(matcher.find()){
//		    	matcher.groupCount();
//		    	tempTableName = matcher.group(1);
////		    	tempTableName = matcher.group();
////		    	map.putAll(queryTable(tempTableName));
//		    	columns.addAll(queryColumns(tempTableName));
		    	map.put("COMMENTS", "联合查询");
		    	map.put("CREATETIME",  DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		    	map.put("TABLENAME",vo.getSql().trim());
//		    	map.put("TABLENAME",vo.getSql().trim().replaceFirst("select.*from", ""));
//		    }
		    	GenUtils.generatorCode(map, columns, zip, vo.getProjectName(), vo.getPackageName(),vo);

		}else {
			for (String tableName : vo.getTables()) {
				// 查询表信息
				Map<String, String> table = queryTable(tableName);
				// 查询列信息
				List<Map<String, Object>> columns = queryColumns(tableName);
				// 生成代码
				GenUtils.generatorCode(table, columns, zip, vo.getProjectName(), vo.getPackageName(),vo);
			}
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}
	
}
