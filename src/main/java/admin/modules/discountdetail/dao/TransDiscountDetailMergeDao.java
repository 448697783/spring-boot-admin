package admin.modules.discountdetail.dao;

import admin.modules.discountdetail.entity.TransDiscountDetailMergeEntity;
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
 * @date 2018-12-07 17:00:23
 */
@Mapper
public interface TransDiscountDetailMergeDao {
		
	@Select({
			"<script>",
			"SELECT * FROM ",  
			"(  ",
			"SELECT A.* ", 
			"FROM (",
			"select",
			        "ROWNUM RN, ",
					"ACTIVE_NUMBER, ",
					"DISCOUNT_TYPE, ",
					"CREATED_ON, ",
					"TOTAL_AMT, ",
					"DISCOUNT_AMT, ",
					"MONEY_TYPE, ",
					"AMT, ",
					"USER_NAME, ",
					"ORDER_ID, ",
					"TRANS_ID, ",
					"NOTITY_FINISH_TIME, ",
					"MERCHANT_NAME",
			"from",
				"(SELECT MM.MERCHANT_NAME,MM.PRODUCT_NAME,MM.USER_NAME,DAI.ACTIVE_NUMBER,DAI.DISCOUNT_TYPE,MM.ORDER_ID,MM.TRANS_ID,DDI.TOTAL_AMT,MM.AMT,DDI.DISCOUNT_AMT,MM.MONEY_TYPE,MM.CREATED_ON,MM.NOTITY_FINISH_TIME FROM SW_DISCOUNT_ACTIVE_INFO DAI, SW_TRANS_DISCOUNT_DETAIL DDI LEFT JOIN (SELECT * FROM SW_TRANS_QUERY Q LEFT JOIN  (SELECT C.MERCHANT_NAME, D.MERCHANT_NAME PRODUCT_NAME,C.ID,D.ID PRODUCT_ID FROM SW_MERCHANT_INFO C ,SW_MERCHANT_INFO D WHERE  C.ID = D.PID) M ON Q.PRODUCT_ID=M.PRODUCT_ID ) MM  ON DDI.FK_PS_TRANS_ID = MM.PS_TRANS_ID WHERE   DAI.ACTIVE_NUMBER=DDI.ACTIVE_NUMBER) F",
			"<where>",
				"<if test='entity.activeNumber != null'>AND ACTIVE_NUMBER = #{entity.activeNumber} </if>",
				"<if test='entity.discountType != null'>AND DISCOUNT_TYPE = #{entity.discountType} </if>",
				"<if test='entity.createdOn != null'>AND CREATED_ON = #{entity.createdOn} </if>",
				"<if test='entity.totalAmt != null'>AND TOTAL_AMT = #{entity.totalAmt} </if>",
				"<if test='entity.discountAmt != null'>AND DISCOUNT_AMT = #{entity.discountAmt} </if>",
				"<if test='entity.moneyType != null'>AND MONEY_TYPE = #{entity.moneyType} </if>",
				"<if test='entity.amt != null'>AND AMT = #{entity.amt} </if>",
				"<if test='entity.userName != null'>AND USER_NAME = #{entity.userName} </if>",
				"<if test='entity.orderId != null'>AND ORDER_ID = #{entity.orderId} </if>",
				"<if test='entity.transId != null'>AND TRANS_ID = #{entity.transId} </if>",
				"<if test='entity.notityFinishTime != null'>AND NOTITY_FINISH_TIME = #{entity.notityFinishTime} </if>",
				"<if test='entity.merchantName != null'>AND MERCHANT_NAME = #{entity.merchantName}</if>",
			"</where>",
			") A ",
			"WHERE <![CDATA[ ROWNUM < #{endRow}  ]]>",
			") B",
			"WHERE <![CDATA[ RN > #{startRow} ]]>",
			"</script>"
    })
	List<TransDiscountDetailMergeEntity> selectEntityListForPage(@Param("entity")TransDiscountDetailMergeEntity transDiscountDetailMergeEntity,@Param("startRow")Long startRow,@Param("endRow")Long endRow);
	
	@Select({
			"<script>",
			"select",
			        "count(1) ",
			"from",
				"(SELECT MM.MERCHANT_NAME,MM.PRODUCT_NAME,MM.USER_NAME,DAI.ACTIVE_NUMBER,DAI.DISCOUNT_TYPE,MM.ORDER_ID,MM.TRANS_ID,DDI.TOTAL_AMT,MM.AMT,DDI.DISCOUNT_AMT,MM.MONEY_TYPE,MM.CREATED_ON,MM.NOTITY_FINISH_TIME FROM SW_DISCOUNT_ACTIVE_INFO DAI, SW_TRANS_DISCOUNT_DETAIL DDI LEFT JOIN (SELECT * FROM SW_TRANS_QUERY Q LEFT JOIN  (SELECT C.MERCHANT_NAME, D.MERCHANT_NAME PRODUCT_NAME,C.ID,D.ID PRODUCT_ID FROM SW_MERCHANT_INFO C ,SW_MERCHANT_INFO D WHERE  C.ID = D.PID) M ON Q.PRODUCT_ID=M.PRODUCT_ID ) MM  ON DDI.FK_PS_TRANS_ID = MM.PS_TRANS_ID WHERE   DAI.ACTIVE_NUMBER=DDI.ACTIVE_NUMBER) F",
			"<where>",
				"<if test='entity.activeNumber != null'>AND ACTIVE_NUMBER = #{entity.activeNumber} </if>",
				"<if test='entity.discountType != null'>AND DISCOUNT_TYPE = #{entity.discountType} </if>",
				"<if test='entity.createdOn != null'>AND CREATED_ON = #{entity.createdOn} </if>",
				"<if test='entity.totalAmt != null'>AND TOTAL_AMT = #{entity.totalAmt} </if>",
				"<if test='entity.discountAmt != null'>AND DISCOUNT_AMT = #{entity.discountAmt} </if>",
				"<if test='entity.moneyType != null'>AND MONEY_TYPE = #{entity.moneyType} </if>",
				"<if test='entity.amt != null'>AND AMT = #{entity.amt} </if>",
				"<if test='entity.userName != null'>AND USER_NAME = #{entity.userName} </if>",
				"<if test='entity.orderId != null'>AND ORDER_ID = #{entity.orderId} </if>",
				"<if test='entity.transId != null'>AND TRANS_ID = #{entity.transId} </if>",
				"<if test='entity.notityFinishTime != null'>AND NOTITY_FINISH_TIME = #{entity.notityFinishTime} </if>",
				"<if test='entity.merchantName != null'>AND MERCHANT_NAME = #{entity.merchantName}</if>",
			"</where>",
			"</script>"
    })
	Long selectEntityListForPageCount(@Param("entity")TransDiscountDetailMergeEntity transDiscountDetailMergeEntity);
}
