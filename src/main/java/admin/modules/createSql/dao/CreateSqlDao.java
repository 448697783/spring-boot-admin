package admin.modules.createSql.dao;

import admin.modules.createSql.entity.CreateSqlEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * 快速构建sql
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2020-06-04 00:01:06
 */
@Mapper
public interface CreateSqlDao {

		@Select({
			"<script>",
			"select",
					"`orderNum`, ",
					"`customerNum`",
			"from",
				"create_sql F",
			"<where>",
				"<if test='entity.ordernum != null and entity.ordernum != \"\" '>AND `orderNum` = #{entity.ordernum} </if>",
				"<if test='entity.customernum != null and entity.customernum != \"\" '>AND `customerNum` = #{entity.customernum}</if>",
			"</where>",
			"limit #{startRow},#{endRow}  ",
			"</script>"
    })
	List<CreateSqlEntity> selectEntityListForPage(@Param("entity")CreateSqlEntity createSqlEntity,@Param("startRow")Long startRow,@Param("endRow")Long endRow);
	
	@Select({
			"<script>",
			"select",
			        "count(1) ",
			"from",
				"create_sql F",
			"<where>",
				"<if test='entity.ordernum != null and entity.ordernum != \"\" '>AND `orderNum` = #{entity.ordernum} </if>",
				"<if test='entity.customernum != null and entity.customernum != \"\" '>AND `customerNum` = #{entity.customernum}</if>",
			"</where>",
			"</script>"
    })
	Long selectEntityListForPageCount(@Param("entity")CreateSqlEntity createSqlEntity);
	
	@Select({
			"<script>",
			"select",
					"`orderNum`, ",
					"`customerNum`",
			"from",
				"create_sql",
			"<where>",
				"<if test='ordernum != null and ordernum != \"\" '>AND `orderNum` = #{ordernum} </if>",
				"<if test='customernum != null and customernum != \"\" '>AND `customerNum` = #{customernum}</if>",
			"</where>",
			"</script>"
    })
	List<CreateSqlEntity> selectEntityListAll(CreateSqlEntity createSqlEntity);

	@Insert({
			"insert into create_sql",
				"(",
					"`orderNum`, ",
					"`customerNum`",
				")",
			"values",
				"(",
					"#{ordernum,jdbcType=VARCHAR}, ",
					"#{customernum,jdbcType=VARCHAR}",
				")"
    })
       int insertEntity(CreateSqlEntity createSqlEntity);
	
	@Insert({
			"<script>",
			"insert into create_sql",
				"(",
				"`orderNum`, ",
				"`customerNum`",
				")",
			"values",
			"<foreach collection ='list' item='obj' separator =','>",
				"(",
					"#{obj.ordernum,jdbcType=VARCHAR}, ",
					"#{obj.customernum,jdbcType=VARCHAR}",
				")",
			"</foreach >",
			"</script>",
    })
       int batchInsertEntity(@Param("list")List<CreateSqlEntity> createSqlEntitys);
	
	@Update({
			"<script>",
			"update create_sql",
				"<set>",
						"`customerNum` = #{entity.customernum}",
				"</set>",
				"where orderNum = #{entity.ordernum} and version_optimized_lock=#{entity.versionOptimizedLock}",
			"</script>"
	})
	int updateEntity(@Param("entity")CreateSqlEntity createSqlEntity);

	@Update({
			"<script>",
			"<foreach collection ='list' item='obj' separator =';'>",
				"update create_sql",
					"<set>",
						"`customerNum` = #{obj.customernum}",
					"</set>",
					"where orderNum = #{obj.ordernum} and version_optimized_lock=#{obj.versionOptimizedLock}",
			"</foreach >",
			"</script>"
	})
	int batchUpdateEntity(@Param("list")List<CreateSqlEntity> createSqlEntitys);
	
	@Delete({
			"<script>",
				"delete from create_sql",
				"<where>",
				"<if test='entity.customernum != null  and entity.customernum  != \"\" '> and `customerNum` = #{entity.customernum}</if>",
				"</where>",
				"and orderNum = #{entity.ordernum}",
			"</script>"
	})
	int deleteEntity(@Param("entity")CreateSqlEntity createSqlEntity);
	
	@Delete({
		"<script>",
		"delete from create_sql ",
		"where orderNum in",
		"<foreach collection ='list' item='obj' open='(' separator=',' close=')'>",
			" #{obj.ordernum}",
		"</foreach >",
		"</script>"
	})
	int batchDeleteEntity(@Param("list")List<CreateSqlEntity> createSqlEntitys);
}
