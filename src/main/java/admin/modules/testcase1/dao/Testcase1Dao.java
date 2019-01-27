package admin.modules.testcase1.dao;

import admin.modules.testcase1.entity.Testcase1Entity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 测试用例
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2019-01-27 22:30:03
 */
@Mapper
public interface Testcase1Dao {

		@Select({
			"<script>",
			"select",
					"controller_name, ",
					"test_case_id, ",
					"created_time, ",
					"updated_time, ",
					"exec_last_time, ",
					"status, ",
					"a, ",
					"b, ",
					"c, ",
					"d",
			"from",
				"testcase1 F",
			"<where>",
				"<if test='entity.controllerName != null and entity.controllerName != \"\" '>AND controller_name = #{entity.controllerName} </if>",
				"<if test='entity.testCaseId != null '>and test_case_id = #{entity.testCaseId} </if>",
				"<if test='entity.createdTime != null '>and created_time = #{entity.createdTime} </if>",
				"<if test='entity.updatedTime != null '>and updated_time = #{entity.updatedTime} </if>",
				"<if test='entity.execLastTime != null '>and exec_last_time = #{entity.execLastTime} </if>",
				"<if test='entity.status != null '>and status = #{entity.status} </if>",
				"<if test='entity.a != null '>and a = #{entity.a} </if>",
				"<if test='entity.b != null '>and b = #{entity.b} </if>",
				"<if test='entity.c != null '>and c = #{entity.c} </if>",
				"<if test='entity.d != null and entity.d != \"\" '>AND d = #{entity.d}</if>",
			"</where>",
			"limit #{startRow},#{endRow}  ",
			"</script>"
    })
	List<Testcase1Entity> selectEntityListForPage(@Param("entity")Testcase1Entity testcase1Entity,@Param("startRow")Long startRow,@Param("endRow")Long endRow);
	
	@Select({
			"<script>",
			"select",
			        "count(1) ",
			"from",
				"testcase1 F",
			"<where>",
				"<if test='entity.controllerName != null and entity.controllerName != \"\" '>AND controller_name = #{entity.controllerName} </if>",
				"<if test='entity.testCaseId != null '>and test_case_id = #{entity.testCaseId} </if>",
				"<if test='entity.createdTime != null '>and created_time = #{entity.createdTime} </if>",
				"<if test='entity.updatedTime != null '>and updated_time = #{entity.updatedTime} </if>",
				"<if test='entity.execLastTime != null '>and exec_last_time = #{entity.execLastTime} </if>",
				"<if test='entity.status != null '>and status = #{entity.status} </if>",
				"<if test='entity.a != null '>and a = #{entity.a} </if>",
				"<if test='entity.b != null '>and b = #{entity.b} </if>",
				"<if test='entity.c != null '>and c = #{entity.c} </if>",
				"<if test='entity.d != null and entity.d != \"\" '>AND d = #{entity.d}</if>",
			"</where>",
			"</script>"
    })
	Long selectEntityListForPageCount(@Param("entity")Testcase1Entity testcase1Entity);
	
	@Select({
			"<script>",
			"select",
					"controller_name, ",
					"test_case_id, ",
					"created_time, ",
					"updated_time, ",
					"exec_last_time, ",
					"status, ",
					"a, ",
					"b, ",
					"c, ",
					"d",
			"from",
				"testcase1",
			"<where>",
				"<if test='controllerName != null and controllerName != \"\" '>AND controller_name = #{controllerName} </if>",
				"<if test='testCaseId != null '>and test_case_id = #{testCaseId} </if>",
				"<if test='createdTime != null '>and created_time = #{createdTime} </if>",
				"<if test='updatedTime != null '>and updated_time = #{updatedTime} </if>",
				"<if test='execLastTime != null '>and exec_last_time = #{execLastTime} </if>",
				"<if test='status != null '>and status = #{status} </if>",
				"<if test='a != null '>and a = #{a} </if>",
				"<if test='b != null '>and b = #{b} </if>",
				"<if test='c != null '>and c = #{c} </if>",
				"<if test='d != null and d != \"\" '>AND d = #{d}</if>",
			"</where>",
			"</script>"
    })
	List<Testcase1Entity> selectEntityListAll(Testcase1Entity testcase1Entity);

	@Insert({
			"insert into testcase1",
				"(",
					"controller_name, ",
					"test_case_id, ",
					"created_time, ",
					"updated_time, ",
					"exec_last_time, ",
					"status, ",
					"a, ",
					"b, ",
					"c, ",
					"d",
				")",
			"values",
				"(",
					"#{controllerName,jdbcType=VARCHAR}, ",
					"#{testCaseId,jdbcType=BIGINT}, ",
					"#{createdTime,jdbcType=DATE}, ",
					"#{updatedTime,jdbcType=DATE}, ",
					"#{execLastTime,jdbcType=DATE}, ",
					"#{status,jdbcType=INTEGER}, ",
					"#{a,jdbcType=DECIMAL}, ",
					"#{b,jdbcType=DECIMAL}, ",
					"#{c,jdbcType=DECIMAL}, ",
					"#{d,jdbcType=VARCHAR}",
				")"
    })
	int insertEntity(Testcase1Entity testcase1Entity);
	
	@Insert({
			"<script>",
			"insert into testcase1",
				"(",
				"controller_name, ",
				"test_case_id, ",
				"created_time, ",
				"updated_time, ",
				"exec_last_time, ",
				"status, ",
				"a, ",
				"b, ",
				"c, ",
				"d",
				")",
			"values",
			"<foreach collection ='list' item='obj' separator =','>",
				"(",
					"#{obj.controllerName,jdbcType=VARCHAR}, ",
					"#{obj.testCaseId,jdbcType=BIGINT}, ",
					"#{obj.createdTime,jdbcType=DATE}, ",
					"#{obj.updatedTime,jdbcType=DATE}, ",
					"#{obj.execLastTime,jdbcType=DATE}, ",
					"#{obj.status,jdbcType=INTEGER}, ",
					"#{obj.a,jdbcType=DECIMAL}, ",
					"#{obj.b,jdbcType=DECIMAL}, ",
					"#{obj.c,jdbcType=DECIMAL}, ",
					"#{obj.d,jdbcType=VARCHAR}",
				")",
			"</foreach >",
			"</script>",
    })
	int batchInsertEntity(@Param("list")List<Testcase1Entity> testcase1Entitys);
	
	@Update({
			"<script>",
			"update testcase1",
				"<set>",
					"<if test='entity.controllerName != null'>controller_name = #{entity.controllerName}, </if>",
					"<if test='entity.createdTime != null'>created_time = #{entity.createdTime}, </if>",
					"<if test='entity.updatedTime != null'>updated_time = #{entity.updatedTime}, </if>",
					"<if test='entity.execLastTime != null'>exec_last_time = #{entity.execLastTime}, </if>",
					"<if test='entity.status != null'>status = #{entity.status}, </if>",
					"<if test='entity.a != null'>a = #{entity.a}, </if>",
					"<if test='entity.b != null'>b = #{entity.b}, </if>",
					"<if test='entity.c != null'>c = #{entity.c}, </if>",
					"<if test='entity.d != null'>d = #{entity.d}</if>",
				"</set>",
				"where test_case_id = #{entity.testCaseId}",
			"</script>"
	})
	int updateEntity(@Param("entity")Testcase1Entity testcase1Entity);

	@Update({
			"<script>",
			"<foreach collection ='list' item='obj' separator =';'>",
				"update testcase1",
					"<set>",
						"<if test='list.controllerName != null'>controller_name = #{obj.controllerName}, </if>",
						"<if test='list.createdTime != null'>created_time = #{obj.createdTime}, </if>",
						"<if test='list.updatedTime != null'>updated_time = #{obj.updatedTime}, </if>",
						"<if test='list.execLastTime != null'>exec_last_time = #{obj.execLastTime}, </if>",
						"<if test='list.status != null'>status = #{obj.status}, </if>",
						"<if test='list.a != null'>a = #{obj.a}, </if>",
						"<if test='list.b != null'>b = #{obj.b}, </if>",
						"<if test='list.c != null'>c = #{obj.c}, </if>",
						"<if test='list.d != null'>d = #{obj.d}</if>",
					"</set>",
					"where test_case_id = #{entity.testCaseId}",
			"</foreach >",
			"</script>"
	})
	int batchUpdateEntity(@Param("list")List<Testcase1Entity> testcase1Entitys);
	
	@Delete({
			"<script>",
				"delete from testcase1",
				"<where>",
					"<if test='entity.controllerName != null'>controller_name = #{controllerName} </if>",
					"<if test='entity.testCaseId != null'>test_case_id = #{testCaseId} </if>",
					"<if test='entity.createdTime != null'>created_time = #{createdTime} </if>",
					"<if test='entity.updatedTime != null'>updated_time = #{updatedTime} </if>",
					"<if test='entity.execLastTime != null'>exec_last_time = #{execLastTime} </if>",
					"<if test='entity.status != null'>status = #{status} </if>",
					"<if test='entity.a != null'>a = #{a} </if>",
					"<if test='entity.b != null'>b = #{b} </if>",
					"<if test='entity.c != null'>c = #{c} </if>",
					"<if test='entity.d != null'>d = #{d}</if>",
				"</where>",
			"</script>"
	})
	int deleteEntity(@Param("entity")Testcase1Entity testcase1Entity);
	
	@Delete({
		"<script>",
		"<foreach collection ='list' item='obj' separator =';'>",
			"delete from testcase1",
			"<where>",
				"<if test='obj.controllerName != null'>controller_name = #{obj.controllerName}</if>",
				"<if test='obj.testCaseId != null'>test_case_id = #{obj.testCaseId}</if>",
				"<if test='obj.createdTime != null'>created_time = #{obj.createdTime}</if>",
				"<if test='obj.updatedTime != null'>updated_time = #{obj.updatedTime}</if>",
				"<if test='obj.execLastTime != null'>exec_last_time = #{obj.execLastTime}</if>",
				"<if test='obj.status != null'>status = #{obj.status}</if>",
				"<if test='obj.a != null'>a = #{obj.a}</if>",
				"<if test='obj.b != null'>b = #{obj.b}</if>",
				"<if test='obj.c != null'>c = #{obj.c}</if>",
				"<if test='obj.d != null'>d = #{obj.d}</if>",
			"</where>",
		"</foreach >",
		"</script>"
	})
	int batchDeleteEntity(@Param("list")List<Testcase1Entity> testcase1Entitys);
}
