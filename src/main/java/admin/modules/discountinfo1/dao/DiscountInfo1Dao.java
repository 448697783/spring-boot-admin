package admin.modules.discountinfo1.dao;

import admin.modules.discountinfo1.entity.DiscountInfo1Entity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 折扣活动信息表
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-12 16:21:22
 */
@Mapper
public interface DiscountInfo1Dao {

	@Select({
			"<script>",
			"SELECT * FROM ",  
			"(  ",
			"SELECT A.* ", 
			"FROM (",
			"select",
			        "ROWNUM RN, ",
					"ID, ",
					"ENABLED, ",
					"ACTIVE_NUMBER, ",
					"UPDATED_ON, ",
					"VALID_TIME, ",
					"DISCOUNT_TYPE, ",
					"VERSION_OPTIMIZED_LOCK, ",
					"RANG, ",
					"CREATED_ON, ",
					"PRIORITY, ",
					"ACTIVE_NAME, ",
					"MERCHANT_ID, ",
					"END_TIME, ",
					"START_TIME, ",
					"PRODUCT_ID",
			"from",
				"(SW_DISCOUNT_ACTIVE_INFO) F",
			"<where>",
				"<if test='entity.id != null'>AND ID = #{entity.id} </if>",
				"<if test='entity.enabled != null'>AND ENABLED = #{entity.enabled} </if>",
				"<if test='entity.activeNumber != null'>AND ACTIVE_NUMBER = #{entity.activeNumber} </if>",
				"<if test='entity.updatedOn != null'>AND UPDATED_ON = #{entity.updatedOn} </if>",
				"<if test='entity.validTime != null'>AND VALID_TIME = #{entity.validTime} </if>",
				"<if test='entity.discountType != null'>AND DISCOUNT_TYPE = #{entity.discountType} </if>",
				"<if test='entity.versionOptimizedLock != null'>AND VERSION_OPTIMIZED_LOCK = #{entity.versionOptimizedLock} </if>",
				"<if test='entity.rang != null'>AND RANG = #{entity.rang} </if>",
				"<if test='entity.createdOn != null'>AND CREATED_ON = #{entity.createdOn} </if>",
				"<if test='entity.priority != null'>AND PRIORITY = #{entity.priority} </if>",
				"<if test='entity.activeName != null'>AND ACTIVE_NAME = #{entity.activeName} </if>",
				"<if test='entity.merchantId != null'>AND MERCHANT_ID = #{entity.merchantId} </if>",
				"<if test='entity.endTime != null'>AND END_TIME = #{entity.endTime} </if>",
				"<if test='entity.startTime != null'>AND START_TIME = #{entity.startTime} </if>",
				"<if test='entity.productId != null'>AND PRODUCT_ID = #{entity.productId}</if>",
			"</where>",
			") A ",
			"WHERE <![CDATA[ ROWNUM < #{endRow}  ]]>",
			") B",
			"WHERE <![CDATA[ RN > #{startRow} ]]>",
			"</script>"
    })
	List<DiscountInfo1Entity> selectEntityListForPage(@Param("entity")DiscountInfo1Entity discountInfo1Entity,@Param("startRow")Long startRow,@Param("endRow")Long endRow);
	
	@Select({
			"<script>",
			"select",
			        "count(1) ",
			"from",
				"(SW_DISCOUNT_ACTIVE_INFO) F",
			"<where>",
				"<if test='entity.id != null'>AND ID = #{entity.id} </if>",
				"<if test='entity.enabled != null'>AND ENABLED = #{entity.enabled} </if>",
				"<if test='entity.activeNumber != null'>AND ACTIVE_NUMBER = #{entity.activeNumber} </if>",
				"<if test='entity.updatedOn != null'>AND UPDATED_ON = #{entity.updatedOn} </if>",
				"<if test='entity.validTime != null'>AND VALID_TIME = #{entity.validTime} </if>",
				"<if test='entity.discountType != null'>AND DISCOUNT_TYPE = #{entity.discountType} </if>",
				"<if test='entity.versionOptimizedLock != null'>AND VERSION_OPTIMIZED_LOCK = #{entity.versionOptimizedLock} </if>",
				"<if test='entity.rang != null'>AND RANG = #{entity.rang} </if>",
				"<if test='entity.createdOn != null'>AND CREATED_ON = #{entity.createdOn} </if>",
				"<if test='entity.priority != null'>AND PRIORITY = #{entity.priority} </if>",
				"<if test='entity.activeName != null'>AND ACTIVE_NAME = #{entity.activeName} </if>",
				"<if test='entity.merchantId != null'>AND MERCHANT_ID = #{entity.merchantId} </if>",
				"<if test='entity.endTime != null'>AND END_TIME = #{entity.endTime} </if>",
				"<if test='entity.startTime != null'>AND START_TIME = #{entity.startTime} </if>",
				"<if test='entity.productId != null'>AND PRODUCT_ID = #{entity.productId}</if>",
			"</where>",
			"</script>"
    })
	Long selectEntityListForPageCount(@Param("entity")DiscountInfo1Entity discountInfo1Entity);
	
	@Select({
			"<script>",
			"select",
					"ID, ",
					"ENABLED, ",
					"ACTIVE_NUMBER, ",
					"UPDATED_ON, ",
					"VALID_TIME, ",
					"DISCOUNT_TYPE, ",
					"VERSION_OPTIMIZED_LOCK, ",
					"RANG, ",
					"CREATED_ON, ",
					"PRIORITY, ",
					"ACTIVE_NAME, ",
					"MERCHANT_ID, ",
					"END_TIME, ",
					"START_TIME, ",
					"PRODUCT_ID",
			"from",
				"SW_DISCOUNT_ACTIVE_INFO",
			"<where>",
				"<if test='id != null'>AND ID = #{id} </if>",
				"<if test='enabled != null'>AND ENABLED = #{enabled} </if>",
				"<if test='activeNumber != null'>AND ACTIVE_NUMBER = #{activeNumber} </if>",
				"<if test='updatedOn != null'>AND UPDATED_ON = #{updatedOn} </if>",
				"<if test='validTime != null'>AND VALID_TIME = #{validTime} </if>",
				"<if test='discountType != null'>AND DISCOUNT_TYPE = #{discountType} </if>",
				"<if test='versionOptimizedLock != null'>AND VERSION_OPTIMIZED_LOCK = #{versionOptimizedLock} </if>",
				"<if test='rang != null'>AND RANG = #{rang} </if>",
				"<if test='createdOn != null'>AND CREATED_ON = #{createdOn} </if>",
				"<if test='priority != null'>AND PRIORITY = #{priority} </if>",
				"<if test='activeName != null'>AND ACTIVE_NAME = #{activeName} </if>",
				"<if test='merchantId != null'>AND MERCHANT_ID = #{merchantId} </if>",
				"<if test='endTime != null'>AND END_TIME = #{endTime} </if>",
				"<if test='startTime != null'>AND START_TIME = #{startTime} </if>",
				"<if test='productId != null'>AND PRODUCT_ID = #{productId}</if>",
			"</where>",
			"</script>"
    })
	List<DiscountInfo1Entity> selectEntityListAll(DiscountInfo1Entity discountInfo1Entity);
	
	@Insert({
			"insert into SW_DISCOUNT_ACTIVE_INFO",
				"(",
					"ID, ",
					"ENABLED, ",
					"ACTIVE_NUMBER, ",
					"UPDATED_ON, ",
					"VALID_TIME, ",
					"DISCOUNT_TYPE, ",
					"VERSION_OPTIMIZED_LOCK, ",
					"RANG, ",
					"CREATED_ON, ",
					"PRIORITY, ",
					"ACTIVE_NAME, ",
					"MERCHANT_ID, ",
					"END_TIME, ",
					"START_TIME, ",
					"PRODUCT_ID",
				")",
			"values",
				"(",
					"#{id,jdbcType=BIGINT}, ",
					"#{enabled,jdbcType=BIGINT}, ",
					"#{activeNumber,jdbcType=VARCHAR}, ",
					"#{updatedOn,jdbcType=TIMESTAMP}, ",
					"#{validTime,jdbcType=VARCHAR}, ",
					"#{discountType,jdbcType=BIGINT}, ",
					"#{versionOptimizedLock,jdbcType=BIGINT}, ",
					"#{rang,jdbcType=VARCHAR}, ",
					"#{createdOn,jdbcType=TIMESTAMP}, ",
					"#{priority,jdbcType=BIGINT}, ",
					"#{activeName,jdbcType=VARCHAR}, ",
					"#{merchantId,jdbcType=VARCHAR}, ",
					"#{endTime,jdbcType=TIMESTAMP}, ",
					"#{startTime,jdbcType=TIMESTAMP}, ",
					"#{productId,jdbcType=BIGINT}",
				")"
    })
	int insertEntity(DiscountInfo1Entity discountInfo1Entity);
	
	@Insert({
			"<script>",
			"insert into SW_DISCOUNT_ACTIVE_INFO",
				"(",
				"ID, ",
				"ENABLED, ",
				"ACTIVE_NUMBER, ",
				"UPDATED_ON, ",
				"VALID_TIME, ",
				"DISCOUNT_TYPE, ",
				"VERSION_OPTIMIZED_LOCK, ",
				"RANG, ",
				"CREATED_ON, ",
				"PRIORITY, ",
				"ACTIVE_NAME, ",
				"MERCHANT_ID, ",
				"END_TIME, ",
				"START_TIME, ",
				"PRODUCT_ID",
				")",
			"values",
			"<foreach collection ='list' item='obj' separator =','>",
				"(",
					"#{obj.id,jdbcType=BIGINT}, ",
					"#{obj.enabled,jdbcType=BIGINT}, ",
					"#{obj.activeNumber,jdbcType=VARCHAR}, ",
					"#{obj.updatedOn,jdbcType=TIMESTAMP}, ",
					"#{obj.validTime,jdbcType=VARCHAR}, ",
					"#{obj.discountType,jdbcType=BIGINT}, ",
					"#{obj.versionOptimizedLock,jdbcType=BIGINT}, ",
					"#{obj.rang,jdbcType=VARCHAR}, ",
					"#{obj.createdOn,jdbcType=TIMESTAMP}, ",
					"#{obj.priority,jdbcType=BIGINT}, ",
					"#{obj.activeName,jdbcType=VARCHAR}, ",
					"#{obj.merchantId,jdbcType=VARCHAR}, ",
					"#{obj.endTime,jdbcType=TIMESTAMP}, ",
					"#{obj.startTime,jdbcType=TIMESTAMP}, ",
					"#{obj.productId,jdbcType=BIGINT}",
				")",
			"</foreach >",
			"</script>",
    })
	int batchInsertEntity(@Param("list")List<DiscountInfo1Entity> discountInfo1Entitys);
	
	@Update({
			"<script>",
			"update SW_DISCOUNT_ACTIVE_INFO",
				"<set>",
					"<if test='entity.enabled != null'>ENABLED = #{entity.enabled}, </if>",
					"<if test='entity.activeNumber != null'>ACTIVE_NUMBER = #{entity.activeNumber}, </if>",
					"<if test='entity.updatedOn != null'>UPDATED_ON = #{entity.updatedOn}, </if>",
					"<if test='entity.validTime != null'>VALID_TIME = #{entity.validTime}, </if>",
					"<if test='entity.discountType != null'>DISCOUNT_TYPE = #{entity.discountType}, </if>",
					"<if test='entity.versionOptimizedLock != null'>VERSION_OPTIMIZED_LOCK = #{entity.versionOptimizedLock}, </if>",
					"<if test='entity.rang != null'>RANG = #{entity.rang}, </if>",
					"<if test='entity.createdOn != null'>CREATED_ON = #{entity.createdOn}, </if>",
					"<if test='entity.priority != null'>PRIORITY = #{entity.priority}, </if>",
					"<if test='entity.activeName != null'>ACTIVE_NAME = #{entity.activeName}, </if>",
					"<if test='entity.merchantId != null'>MERCHANT_ID = #{entity.merchantId}, </if>",
					"<if test='entity.endTime != null'>END_TIME = #{entity.endTime}, </if>",
					"<if test='entity.startTime != null'>START_TIME = #{entity.startTime}, </if>",
					"<if test='entity.productId != null'>PRODUCT_ID = #{entity.productId}</if>",
				"</set>",
				"where ID = #{entity.id}",
			"</script>"
	})
	int updateEntity(@Param("entity")DiscountInfo1Entity discountInfo1Entity);

	@Update({
			"<script>",
			"<foreach collection ='list' item='obj' separator =';'>",
				"update SW_DISCOUNT_ACTIVE_INFO",
					"<set>",
						"<if test='list.enabled != null'>ENABLED = #{obj.enabled}, </if>",
						"<if test='list.activeNumber != null'>ACTIVE_NUMBER = #{obj.activeNumber}, </if>",
						"<if test='list.updatedOn != null'>UPDATED_ON = #{obj.updatedOn}, </if>",
						"<if test='list.validTime != null'>VALID_TIME = #{obj.validTime}, </if>",
						"<if test='list.discountType != null'>DISCOUNT_TYPE = #{obj.discountType}, </if>",
						"<if test='list.versionOptimizedLock != null'>VERSION_OPTIMIZED_LOCK = #{obj.versionOptimizedLock}, </if>",
						"<if test='list.rang != null'>RANG = #{obj.rang}, </if>",
						"<if test='list.createdOn != null'>CREATED_ON = #{obj.createdOn}, </if>",
						"<if test='list.priority != null'>PRIORITY = #{obj.priority}, </if>",
						"<if test='list.activeName != null'>ACTIVE_NAME = #{obj.activeName}, </if>",
						"<if test='list.merchantId != null'>MERCHANT_ID = #{obj.merchantId}, </if>",
						"<if test='list.endTime != null'>END_TIME = #{obj.endTime}, </if>",
						"<if test='list.startTime != null'>START_TIME = #{obj.startTime}, </if>",
						"<if test='list.productId != null'>PRODUCT_ID = #{obj.productId}</if>",
					"</set>",
					"where ID = #{entity.id}",
			"</foreach >",
			"</script>"
	})
	int batchUpdateEntity(@Param("list")List<DiscountInfo1Entity> discountInfo1Entitys);
	
	@Delete({
			"<script>",
				"delete from SW_DISCOUNT_ACTIVE_INFO",
				"<where>",
					"<if test='entity.id != null'>ID = #{id} </if>",
					"<if test='entity.enabled != null'>ENABLED = #{enabled} </if>",
					"<if test='entity.activeNumber != null'>ACTIVE_NUMBER = #{activeNumber} </if>",
					"<if test='entity.updatedOn != null'>UPDATED_ON = #{updatedOn} </if>",
					"<if test='entity.validTime != null'>VALID_TIME = #{validTime} </if>",
					"<if test='entity.discountType != null'>DISCOUNT_TYPE = #{discountType} </if>",
					"<if test='entity.versionOptimizedLock != null'>VERSION_OPTIMIZED_LOCK = #{versionOptimizedLock} </if>",
					"<if test='entity.rang != null'>RANG = #{rang} </if>",
					"<if test='entity.createdOn != null'>CREATED_ON = #{createdOn} </if>",
					"<if test='entity.priority != null'>PRIORITY = #{priority} </if>",
					"<if test='entity.activeName != null'>ACTIVE_NAME = #{activeName} </if>",
					"<if test='entity.merchantId != null'>MERCHANT_ID = #{merchantId} </if>",
					"<if test='entity.endTime != null'>END_TIME = #{endTime} </if>",
					"<if test='entity.startTime != null'>START_TIME = #{startTime} </if>",
					"<if test='entity.productId != null'>PRODUCT_ID = #{productId}</if>",
				"</where>",
			"</script>"
	})
	int deleteEntity(@Param("entity")DiscountInfo1Entity discountInfo1Entity);
	
	@Delete({
		"<script>",
		"<foreach collection ='list' item='obj' separator =';'>",
			"delete from SW_DISCOUNT_ACTIVE_INFO",
			"<where>",
				"<if test='obj.id != null'>ID = #{obj.id}</if>",
				"<if test='obj.enabled != null'>ENABLED = #{obj.enabled}</if>",
				"<if test='obj.activeNumber != null'>ACTIVE_NUMBER = #{obj.activeNumber}</if>",
				"<if test='obj.updatedOn != null'>UPDATED_ON = #{obj.updatedOn}</if>",
				"<if test='obj.validTime != null'>VALID_TIME = #{obj.validTime}</if>",
				"<if test='obj.discountType != null'>DISCOUNT_TYPE = #{obj.discountType}</if>",
				"<if test='obj.versionOptimizedLock != null'>VERSION_OPTIMIZED_LOCK = #{obj.versionOptimizedLock}</if>",
				"<if test='obj.rang != null'>RANG = #{obj.rang}</if>",
				"<if test='obj.createdOn != null'>CREATED_ON = #{obj.createdOn}</if>",
				"<if test='obj.priority != null'>PRIORITY = #{obj.priority}</if>",
				"<if test='obj.activeName != null'>ACTIVE_NAME = #{obj.activeName}</if>",
				"<if test='obj.merchantId != null'>MERCHANT_ID = #{obj.merchantId}</if>",
				"<if test='obj.endTime != null'>END_TIME = #{obj.endTime}</if>",
				"<if test='obj.startTime != null'>START_TIME = #{obj.startTime}</if>",
				"<if test='obj.productId != null'>PRODUCT_ID = #{obj.productId}</if>",
			"</where>",
		"</foreach >",
		"</script>"
	})
	int batchDeleteEntity(@Param("list")List<DiscountInfo1Entity> discountInfo1Entitys);
}
