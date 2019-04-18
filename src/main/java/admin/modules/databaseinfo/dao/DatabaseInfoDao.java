package admin.modules.databaseinfo.dao;

import admin.modules.databaseinfo.entity.DatabaseInfoEntity;
import admin.mapper.base.BaseMappper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

/**
 * 数据库信息表
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2018-06-15 17:49:11
 */
@Mapper
public interface DatabaseInfoDao {
	@Select({
			"<script>",
			"select",
					"aliase, ",
					"url, ",
					"pass_word, ",
					"user_name, ",
					"driver_class_name",
			"from",
				"database_info",
			"<where>",
				"<if test='aliase != null'>AND aliase = #{aliase} </if>",
				"<if test='url != null'>AND url = #{url} </if>",
				"<if test='passWord != null'>AND pass_word = #{passWord} </if>",
				"<if test='userName != null'>AND user_name = #{userName} </if>",
				"<if test='driverClassName != null'>AND driver_class_name = #{driverClassName}</if>",
			"</where>",
			"</script>"
    })
	List<DatabaseInfoEntity> selectEntityListAll(DatabaseInfoEntity databaseInfoEntity);

	@Insert({
			"insert into database_info",
				"(",
					"aliase, ",
					"url, ",
					"pass_word, ",
					"user_name, ",
					"driver_class_name",
				")",
			"values",
				"(",
					"#{aliase}, ",
					"#{url}, ",
					"#{passWord}, ",
					"#{userName}, ",
					"#{driverClassName}",
				")"
    })
	int insertEntity(DatabaseInfoEntity databaseInfoEntity);
	
	@Insert({
			"<script>",
			"insert into database_info",
				"(",
				"aliase, ",
				"url, ",
				"pass_word, ",
				"user_name, ",
				"driver_class_name",
				")",
			"values",
			"<foreach collection ='list' item='obj' separator =','>",
				"(",
					"#{obj.aliase}, ",
					"#{obj.url}, ",
					"#{obj.passWord}, ",
					"#{obj.userName}, ",
					"#{obj.driverClassName}",
				")",
			"</foreach >",
			"</script>",
    })
	int batchInsertEntity(List<DatabaseInfoEntity> databaseInfoEntitys);
	
	@Update({
			"<script>",
			"update database_info",
				"<set>",
					"<if test='url != null'>url = #{url}, </if>",
					"<if test='passWord != null'>pass_word = #{passWord}, </if>",
					"<if test='userName != null'>user_name = #{userName}, </if>",
					"<if test='driverClassName != null'>driver_class_name = #{driverClassName}</if>",
				"</set>",
				"where aliase = #{aliase}",
			"</script>"
	})
	int updateEntity(DatabaseInfoEntity databaseInfoEntity);

	@Update({
			"<script>",
			"<foreach collection ='list' item='obj' separator =';'>",
				"update database_info",
					"<set>",
						"<if test='obj.url != null'>url = #{obj.url}, </if>",
						"<if test='obj.passWord != null'>pass_word = #{obj.passWord}, </if>",
						"<if test='obj.userName != null'>user_name = #{obj.userName}, </if>",
						"<if test='obj.driverClassName != null'>driver_class_name = #{obj.driverClassName}</if>",
					"</set>",
					"where aliase = #{obj.aliase}",
			"</foreach >",
			"</script>"
	})
	int batchUpdateEntity(List<DatabaseInfoEntity> databaseInfoEntitys);
	
	@Delete({
			"<script>",
				"delete from database_info",
				"<where>",
					"<if test='aliase != null'>aliase = #{aliase} </if>",
					"<if test='url != null'>url = #{url} </if>",
					"<if test='passWord != null'>pass_word = #{passWord} </if>",
					"<if test='userName != null'>user_name = #{userName} </if>",
					"<if test='driverClassName != null'>driver_class_name = #{driverClassName}</if>",
				"</where>",
			"</script>"
	})
	int deleteEntity(DatabaseInfoEntity databaseInfoEntity);
	
	@Delete({
		"<script>",
		"<foreach collection ='list' item='obj' separator =';'>",
			"delete from database_info",
			"where",
				"<if test='obj.aliase != null'>aliase = #{obj.aliase}</if>",
				"<if test='obj.url != null'>url = #{obj.url}</if>",
				"<if test='obj.passWord != null'>pass_word = #{obj.passWord}</if>",
				"<if test='obj.userName != null'>user_name = #{obj.userName}</if>",
				"<if test='obj.driverClassName != null'>driver_class_name = #{obj.driverClassName}</if>",
		"</foreach >",
		"</script>"
	})
	int batchDeleteEntity(List<DatabaseInfoEntity> databaseInfoEntitys);
}
