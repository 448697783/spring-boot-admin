package admin.modules.takeoutUser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import admin.modules.takeoutUser.entity.TakeoutUserEntity;

/**
 * 
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2019-07-01 19:19:04
 */
@Mapper
public interface TakeoutUserDao {

		@Select({
			"<script>",
			"select",
					"`fID`, ",
					"`fName`, ",
					"`fPhoneNumber`, ",
					"`fAddress`",
			"from",
				"takeout_user F",
			"<where>",
				"<if test='entity.fid != null '>and `fID` = #{entity.fid} </if>",
				"<if test='entity.fname != null and entity.fname != \"\" '>AND `fName` = #{entity.fname} </if>",
				"<if test='entity.fphonenumber != null and entity.fphonenumber != \"\" '>AND `fPhoneNumber` = #{entity.fphonenumber} </if>",
				"<if test='entity.faddress != null and entity.faddress != \"\" '>AND `fAddress` = #{entity.faddress}</if>",
				"<if test='entity.sysRoleDeptEntity != null and entity.sysRoleDeptEntity.size() != 0'>",
					"and dept_id in (",
					"<foreach collection='entity.sysRoleDeptEntity' item='item' index='index' separator=',' >",
						"#{item.deptId}",
					"</foreach>",
					")",
				"</if>",
			"</where>",
			"limit #{startRow},#{endRow}  ",
			"</script>"
    })
	List<TakeoutUserEntity> selectEntityListForPage(@Param("entity")TakeoutUserEntity takeoutUserEntity,@Param("startRow")Long startRow,@Param("endRow")Long endRow);
	
	@Select({
			"<script>",
			"select",
			        "count(1) ",
			"from",
				"takeout_user F",
			"<where>",
				"<if test='entity.fid != null '>and `fID` = #{entity.fid} </if>",
				"<if test='entity.fname != null and entity.fname != \"\" '>AND `fName` = #{entity.fname} </if>",
				"<if test='entity.fphonenumber != null and entity.fphonenumber != \"\" '>AND `fPhoneNumber` = #{entity.fphonenumber} </if>",
				"<if test='entity.faddress != null and entity.faddress != \"\" '>AND `fAddress` = #{entity.faddress}</if>",
				"<if test='entity.sysRoleDeptEntity != null and entity.sysRoleDeptEntity.size() != 0'>",
					"and dept_id in (",
					"<foreach collection='entity.sysRoleDeptEntity' item='item' index='index' separator=',' >",
						"#{item.deptId}",
					"</foreach>",
					")",
				"</if>",
			"</where>",
			"</script>"
    })
	Long selectEntityListForPageCount(@Param("entity")TakeoutUserEntity takeoutUserEntity);
	
	@Select({
			"<script>",
			"select",
					"`fID`, ",
					"`fName`, ",
					"`fPhoneNumber`, ",
					"`fAddress`",
			"from",
				"takeout_user",
			"<where>",
				"<if test='fid != null '>and `fID` = #{fid} </if>",
				"<if test='fname != null and fname != \"\" '>AND `fName` = #{fname} </if>",
				"<if test='fphonenumber != null and fphonenumber != \"\" '>AND `fPhoneNumber` = #{fphonenumber} </if>",
				"<if test='faddress != null and faddress != \"\" '>AND `fAddress` = #{faddress}</if>",
				"<if test='sysRoleDeptEntity != null and sysRoleDeptEntity.size() != 0'>",
					"and dept_id in (",
					"<foreach collection='sysRoleDeptEntity' item='item' index='index' separator=',' >",
						"#{item.deptId}",
					"</foreach>",
					")",
				"</if>",
			"</where>",
			"</script>"
    })
	List<TakeoutUserEntity> selectEntityListAll(TakeoutUserEntity takeoutUserEntity);

	@Insert({
			"insert into takeout_user",
				"(",
					"`fID`, ",
					"`fName`, ",
					"`fPhoneNumber`, ",
					"`fAddress`",
				")",
			"values",
				"(",
					"#{fid,jdbcType=INTEGER}, ",
					"#{fname,jdbcType=VARCHAR}, ",
					"#{fphonenumber,jdbcType=VARCHAR}, ",
					"#{faddress,jdbcType=VARCHAR}",
				")"
    })
    @Options(useGeneratedKeys=true,keyProperty="fid")	int insertEntity(TakeoutUserEntity takeoutUserEntity);
	
	@Insert({
			"<script>",
			"insert into takeout_user",
				"(",
				"`fID`, ",
				"`fName`, ",
				"`fPhoneNumber`, ",
				"`fAddress`",
				")",
			"values",
			"<foreach collection ='list' item='obj' separator =','>",
				"(",
					"#{obj.fid,jdbcType=INTEGER}, ",
					"#{obj.fname,jdbcType=VARCHAR}, ",
					"#{obj.fphonenumber,jdbcType=VARCHAR}, ",
					"#{obj.faddress,jdbcType=VARCHAR}",
				")",
			"</foreach >",
			"</script>",
    })
     @Options(useGeneratedKeys=true,keyProperty="fid")	int batchInsertEntity(@Param("list")List<TakeoutUserEntity> takeoutUserEntitys);
	
	@Update({
			"<script>",
			"update takeout_user",
				"<set>",
						"`fName` = #{entity.fname}, ",
						"`fPhoneNumber` = #{entity.fphonenumber}, ",
						"`fAddress` = #{entity.faddress}",
				"</set>",
				"where fID = #{entity.fid} ",
			"</script>"
	})
	int updateEntity(@Param("entity")TakeoutUserEntity takeoutUserEntity);

	@Update({
			"<script>",
			"<foreach collection ='list' item='obj' separator =';'>",
				"update takeout_user",
					"<set>",
						"`fName` = #{obj.fname}, ",
						"`fPhoneNumber` = #{obj.fphonenumber}, ",
						"`fAddress` = #{obj.faddress}",
					"</set>",
					"where fID = #{obj.fid} ",
			"</foreach >",
			"</script>"
	})
	int batchUpdateEntity(@Param("list")List<TakeoutUserEntity> takeoutUserEntitys);
	
	@Delete({
			"<script>",
				"delete from takeout_user",
				"<where>",
				"<if test='entity.fname != null  and entity.fname  != \"\" '> and `fName` = #{entity.fname} </if>",
				"<if test='entity.fphonenumber != null  and entity.fphonenumber  != \"\" '> and `fPhoneNumber` = #{entity.fphonenumber} </if>",
				"<if test='entity.faddress != null  and entity.faddress  != \"\" '> and `fAddress` = #{entity.faddress}</if>",
				"</where>",
				"and fID = #{obj.fid}",
			"</script>"
	})
	int deleteEntity(@Param("entity")TakeoutUserEntity takeoutUserEntity);
	
	@Delete({
		"<script>",
		"<foreach collection ='list' item='obj' separator =';'>",
			"delete from takeout_user",
			"<where>",
				"<if test='obj.fname != null  and obj.fname  != \"\" '> and `fName` = #{obj.fname}</if>",
				"<if test='obj.fphonenumber != null  and obj.fphonenumber  != \"\" '> and `fPhoneNumber` = #{obj.fphonenumber}</if>",
				"<if test='obj.faddress != null  and obj.faddress  != \"\" '> and `fAddress` = #{obj.faddress}</if>",
			"</where>",
			"and fID = #{obj.fid}",
		"</foreach >",
		"</script>"
	})
	int batchDeleteEntity(@Param("list")List<TakeoutUserEntity> takeoutUserEntitys);
}
