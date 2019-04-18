package admin.modules.automatic.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import admin.mapper.base.BaseMappper;

/**
 * 代码生成器
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2016年12月19日 下午3:32:04
 */
@Mapper
public interface OracleDao extends BaseMappper<Map<String, Object>, Long>{
	
	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	Map<String, String> queryTable(String tableName);
	
	List<Map<String, Object>> queryColumns(String tableName);
	
}
