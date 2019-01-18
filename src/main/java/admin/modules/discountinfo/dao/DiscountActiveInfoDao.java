package admin.modules.discountinfo.dao;

import admin.modules.discountinfo.entity.DiscountActiveInfoEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

/**
 * 折扣活动信息表
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-06 15:41:29
 */
@Mapper
public interface DiscountActiveInfoDao {
	@Select({
			"<script>",
			"SELECT * FROM ",  
			"(  ",
			"SELECT A.*, ROWNUM RN ", 
			"FROM (",
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
			") A ",
			"WHERE <![CDATA[ ROWNUM <= #{endRow}  ]]>",
			") ",
			"WHERE <![CDATA[ RN >= #{startRow} ]]>",
			"</script>"
    })
	List<DiscountActiveInfoEntity> selectEntityListForPage(DiscountActiveInfoEntity discountActiveInfoEntity,Long startRow,Long endRow);

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
	List<DiscountActiveInfoEntity> selectEntityListAll(DiscountActiveInfoEntity discountActiveInfoEntity);
	
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
					"#{id}, ",
					"#{enabled}, ",
					"#{activeNumber}, ",
					"#{updatedOn}, ",
					"#{validTime}, ",
					"#{discountType}, ",
					"#{versionOptimizedLock}, ",
					"#{rang}, ",
					"#{createdOn}, ",
					"#{priority}, ",
					"#{activeName}, ",
					"#{merchantId}, ",
					"#{endTime}, ",
					"#{startTime}, ",
					"#{productId}",
				")"
    })
	int insertEntity(DiscountActiveInfoEntity discountActiveInfoEntity);
	
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
					"#{obj.id}, ",
					"#{obj.enabled}, ",
					"#{obj.activeNumber}, ",
					"#{obj.updatedOn}, ",
					"#{obj.validTime}, ",
					"#{obj.discountType}, ",
					"#{obj.versionOptimizedLock}, ",
					"#{obj.rang}, ",
					"#{obj.createdOn}, ",
					"#{obj.priority}, ",
					"#{obj.activeName}, ",
					"#{obj.merchantId}, ",
					"#{obj.endTime}, ",
					"#{obj.startTime}, ",
					"#{obj.productId}",
				")",
			"</foreach >",
			"</script>",
    })
	int batchInsertEntity(List<DiscountActiveInfoEntity> discountActiveInfoEntitys);
	
	@Update({
			"<script>",
			"update SW_DISCOUNT_ACTIVE_INFO",
				"<set>",
					"<if test='enabled != null'>ENABLED = #{enabled}, </if>",
					"<if test='activeNumber != null'>ACTIVE_NUMBER = #{activeNumber}, </if>",
					"<if test='updatedOn != null'>UPDATED_ON = #{updatedOn}, </if>",
					"<if test='validTime != null'>VALID_TIME = #{validTime}, </if>",
					"<if test='discountType != null'>DISCOUNT_TYPE = #{discountType}, </if>",
					"<if test='versionOptimizedLock != null'>VERSION_OPTIMIZED_LOCK = #{versionOptimizedLock}, </if>",
					"<if test='rang != null'>RANG = #{rang}, </if>",
					"<if test='createdOn != null'>CREATED_ON = #{createdOn}, </if>",
					"<if test='priority != null'>PRIORITY = #{priority}, </if>",
					"<if test='activeName != null'>ACTIVE_NAME = #{activeName}, </if>",
					"<if test='merchantId != null'>MERCHANT_ID = #{merchantId}, </if>",
					"<if test='endTime != null'>END_TIME = #{endTime}, </if>",
					"<if test='startTime != null'>START_TIME = #{startTime}, </if>",
					"<if test='productId != null'>PRODUCT_ID = #{productId}</if>",
				"</set>",
				"where ID = #{id}",
			"</script>"
	})
	int updateEntity(DiscountActiveInfoEntity discountActiveInfoEntity);

	@Update({
			"<script>",
			"<foreach collection ='list' item='obj' separator =';'>",
				"update SW_DISCOUNT_ACTIVE_INFO",
					"<set>",
						"<if test='obj.enabled != null'>ENABLED = #{obj.enabled}, </if>",
						"<if test='obj.activeNumber != null'>ACTIVE_NUMBER = #{obj.activeNumber}, </if>",
						"<if test='obj.updatedOn != null'>UPDATED_ON = #{obj.updatedOn}, </if>",
						"<if test='obj.validTime != null'>VALID_TIME = #{obj.validTime}, </if>",
						"<if test='obj.discountType != null'>DISCOUNT_TYPE = #{obj.discountType}, </if>",
						"<if test='obj.versionOptimizedLock != null'>VERSION_OPTIMIZED_LOCK = #{obj.versionOptimizedLock}, </if>",
						"<if test='obj.rang != null'>RANG = #{obj.rang}, </if>",
						"<if test='obj.createdOn != null'>CREATED_ON = #{obj.createdOn}, </if>",
						"<if test='obj.priority != null'>PRIORITY = #{obj.priority}, </if>",
						"<if test='obj.activeName != null'>ACTIVE_NAME = #{obj.activeName}, </if>",
						"<if test='obj.merchantId != null'>MERCHANT_ID = #{obj.merchantId}, </if>",
						"<if test='obj.endTime != null'>END_TIME = #{obj.endTime}, </if>",
						"<if test='obj.startTime != null'>START_TIME = #{obj.startTime}, </if>",
						"<if test='obj.productId != null'>PRODUCT_ID = #{obj.productId}</if>",
					"</set>",
					"where ID = #{obj.id}",
			"</foreach >",
			"</script>"
	})
	int batchUpdateEntity(List<DiscountActiveInfoEntity> discountActiveInfoEntitys);
	
	@Delete({
			"<script>",
				"delete from SW_DISCOUNT_ACTIVE_INFO",
				"<where>",
					"<if test='id != null'>ID = #{id} </if>",
					"<if test='enabled != null'>ENABLED = #{enabled} </if>",
					"<if test='activeNumber != null'>ACTIVE_NUMBER = #{activeNumber} </if>",
					"<if test='updatedOn != null'>UPDATED_ON = #{updatedOn} </if>",
					"<if test='validTime != null'>VALID_TIME = #{validTime} </if>",
					"<if test='discountType != null'>DISCOUNT_TYPE = #{discountType} </if>",
					"<if test='versionOptimizedLock != null'>VERSION_OPTIMIZED_LOCK = #{versionOptimizedLock} </if>",
					"<if test='rang != null'>RANG = #{rang} </if>",
					"<if test='createdOn != null'>CREATED_ON = #{createdOn} </if>",
					"<if test='priority != null'>PRIORITY = #{priority} </if>",
					"<if test='activeName != null'>ACTIVE_NAME = #{activeName} </if>",
					"<if test='merchantId != null'>MERCHANT_ID = #{merchantId} </if>",
					"<if test='endTime != null'>END_TIME = #{endTime} </if>",
					"<if test='startTime != null'>START_TIME = #{startTime} </if>",
					"<if test='productId != null'>PRODUCT_ID = #{productId}</if>",
				"</where>",
			"</script>"
	})
	int deleteEntity(DiscountActiveInfoEntity discountActiveInfoEntity);
	
	@Delete({
		"<script>",
		"<foreach collection ='list' item='obj' separator =';'>",
			"delete from SW_DISCOUNT_ACTIVE_INFO",
			"where",
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
		"</foreach >",
		"</script>"
	})
	int batchDeleteEntity(List<DiscountActiveInfoEntity> discountActiveInfoEntitys);
}
