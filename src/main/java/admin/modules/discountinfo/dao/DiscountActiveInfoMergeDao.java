package admin.modules.discountinfo.dao;

import admin.modules.discountinfo.entity.DiscountActiveInfoMergeEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 
 * 
 * @author wanghonghui
 * @email sunlightcs@gmail.com
 * @date 2018-12-07 15:19:14
 */
@Mapper
public interface DiscountActiveInfoMergeDao {
		
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
					"PRODUCT_ID, ",
					"MERCHANT_NAME",
			"from",
				"(SELECT A.*,B.MERCHANT_NAME,B.PRODUCT_NAME FROM SW_DISCOUNT_ACTIVE_INFO A LEFT JOIN (SELECT C.MERCHANT_NAME, D.MERCHANT_NAME PRODUCT_NAME,C.ID,D.ID PRODUCT_ID FROM SW_MERCHANT_INFO C ,SW_MERCHANT_INFO D WHERE  C.ID = D.PID) B ON A.PRODUCT_ID = B.PRODUCT_ID) F",
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
				"<if test='entity.productId != null'>AND PRODUCT_ID = #{entity.productId} </if>",
				"<if test='entity.merchantName != null'>AND MERCHANT_NAME = #{entity.merchantName}</if>",
			"</where>",
			") A ",
			"WHERE <![CDATA[ ROWNUM < #{endRow}  ]]>",
			") B",
			"WHERE <![CDATA[ RN > #{startRow} ]]>",
			"</script>"
    })
	List<DiscountActiveInfoMergeEntity> selectEntityListForPage(@Param("entity")DiscountActiveInfoMergeEntity discountActiveInfoMergeEntity,@Param("startRow")Long startRow,@Param("endRow")Long endRow);
	
	@Select({
			"<script>",
			"select",
			        "count(1) ",
			"from",
				"(SELECT A.*,B.MERCHANT_NAME,B.PRODUCT_NAME FROM SW_DISCOUNT_ACTIVE_INFO A LEFT JOIN (SELECT C.MERCHANT_NAME, D.MERCHANT_NAME PRODUCT_NAME,C.ID,D.ID PRODUCT_ID FROM SW_MERCHANT_INFO C ,SW_MERCHANT_INFO D WHERE  C.ID = D.PID) B ON A.PRODUCT_ID = B.PRODUCT_ID) F",
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
				"<if test='entity.productId != null'>AND PRODUCT_ID = #{entity.productId} </if>",
				"<if test='entity.merchantName != null'>AND MERCHANT_NAME = #{entity.merchantName}</if>",
			"</where>",
			"</script>"
    })
	Long selectEntityListForPageCount(@Param("entity")DiscountActiveInfoMergeEntity discountActiveInfoMergeEntity);
}
