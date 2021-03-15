package admin.modules.createSql.service;

import admin.modules.createSql.entity.CreateSqlEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 快速构建sql
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2020-06-04 00:01:06
 */
public interface ICreateSqlService {

	String createSql(CreateSqlEntity createSqlEntity) throws UnsupportedEncodingException;
	
}
