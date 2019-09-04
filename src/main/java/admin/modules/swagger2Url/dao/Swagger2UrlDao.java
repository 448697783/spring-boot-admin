package admin.modules.swagger2Url.dao;

import admin.modules.swagger2Url.entity.Swagger2UrlEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-09-02 18:13:02
 */
@Mapper
public interface Swagger2UrlDao {

		@Select({
			"<script>",
			"select",
					"`name`, ",
					"`id`, ",
					"`url`, ",
					"`version_optimized_lock`",
			"from",
				"swagger2_url F",
			"<where>",
				"<if test='entity.name != null and entity.name != \"\" '>AND `name` = #{entity.name} </if>",
				"<if test='entity.id != null '>and `id` = #{entity.id} </if>",
				"<if test='entity.url != null and entity.url != \"\" '>AND `url` = #{entity.url} </if>",
				"<if test='entity.versionOptimizedLock != null '>and `version_optimized_lock` = #{entity.versionOptimizedLock}</if>",
			"</where>",
			"limit #{startRow},#{endRow}  ",
			"</script>"
    })
	List<Swagger2UrlEntity> selectEntityListForPage(@Param("entity")Swagger2UrlEntity swagger2UrlEntity,@Param("startRow")Long startRow,@Param("endRow")Long endRow);
	
	@Select({
			"<script>",
			"select",
			        "count(1) ",
			"from",
				"swagger2_url F",
			"<where>",
				"<if test='entity.name != null and entity.name != \"\" '>AND `name` = #{entity.name} </if>",
				"<if test='entity.id != null '>and `id` = #{entity.id} </if>",
				"<if test='entity.url != null and entity.url != \"\" '>AND `url` = #{entity.url} </if>",
				"<if test='entity.versionOptimizedLock != null '>and `version_optimized_lock` = #{entity.versionOptimizedLock}</if>",
			"</where>",
			"</script>"
    })
	Long selectEntityListForPageCount(@Param("entity")Swagger2UrlEntity swagger2UrlEntity);
	
	@Select({
			"<script>",
			"select",
					"`name`, ",
					"`id`, ",
					"`url`, ",
					"`version_optimized_lock`",
			"from",
				"swagger2_url",
			"<where>",
				"<if test='name != null and name != \"\" '>AND `name` = #{name} </if>",
				"<if test='id != null '>and `id` = #{id} </if>",
				"<if test='url != null and url != \"\" '>AND `url` = #{url} </if>",
				"<if test='versionOptimizedLock != null '>and `version_optimized_lock` = #{versionOptimizedLock}</if>",
			"</where>",
			"</script>"
    })
	List<Swagger2UrlEntity> selectEntityListAll(Swagger2UrlEntity swagger2UrlEntity);

	@Insert({
			"insert into swagger2_url",
				"(",
					"`name`, ",
					"`id`, ",
					"`url`, ",
					"`version_optimized_lock`",
				")",
			"values",
				"(",
					"#{name,jdbcType=VARCHAR}, ",
					"#{id,jdbcType=INTEGER}, ",
					"#{url,jdbcType=VARCHAR}, ",
					"1",
				")"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")    int insertEntity(Swagger2UrlEntity swagger2UrlEntity);
	
	@Insert({
			"<script>",
			"insert into swagger2_url",
				"(",
				"`name`, ",
				"`id`, ",
				"`url`, ",
				"`version_optimized_lock`",
				")",
			"values",
			"<foreach collection ='list' item='obj' separator =','>",
				"(",
					"#{obj.name,jdbcType=VARCHAR}, ",
					"#{obj.id,jdbcType=INTEGER}, ",
					"#{obj.url,jdbcType=VARCHAR}, ",
					"1",
				")",
			"</foreach >",
			"</script>",
    })
    @Options(useGeneratedKeys=true,keyProperty="id")    int batchInsertEntity(@Param("list")List<Swagger2UrlEntity> swagger2UrlEntitys);
	
	@Update({
			"<script>",
			"update swagger2_url",
				"<set>",
						"`name` = #{entity.name}, ",
						"`url` = #{entity.url}, ",
						"`version_optimized_lock` = version_optimized_lock+1",
				"</set>",
				"where id = #{entity.id} and version_optimized_lock=#{entity.versionOptimizedLock}",
			"</script>"
	})
	int updateEntity(@Param("entity")Swagger2UrlEntity swagger2UrlEntity);

	@Update({
			"<script>",
			"<foreach collection ='list' item='obj' separator =';'>",
				"update swagger2_url",
					"<set>",
						"`name` = #{obj.name}, ",
						"`url` = #{obj.url}, ",
						"`version_optimized_lock` = version_optimized_lock+1",
					"</set>",
					"where id = #{obj.id} and version_optimized_lock=#{obj.versionOptimizedLock}",
			"</foreach >",
			"</script>"
	})
	int batchUpdateEntity(@Param("list")List<Swagger2UrlEntity> swagger2UrlEntitys);
	
	@Delete({
			"<script>",
				"delete from swagger2_url",
				"<where>",
				"<if test='entity.name != null  and entity.name  != \"\" '> and `name` = #{entity.name} </if>",
				"<if test='entity.url != null  and entity.url  != \"\" '> and `url` = #{entity.url} </if>",
				"<if test='entity.versionOptimizedLock != null  '> and `version_optimized_lock` = #{entity.versionOptimizedLock}</if>",
				"</where>",
				"and id = #{entity.id}",
			"</script>"
	})
	int deleteEntity(@Param("entity")Swagger2UrlEntity swagger2UrlEntity);
	
	@Delete({
		"<script>",
		"delete from swagger2_url ",
		"where id in",
		"<foreach collection ='list' item='obj' open='(' separator=',' close=')'>",
			" #{obj.id}",
		"</foreach >",
		"</script>"
	})
	int batchDeleteEntity(@Param("list")List<Swagger2UrlEntity> swagger2UrlEntitys);
}
