package admin.modules.api.dao;

import org.apache.ibatis.annotations.Mapper;

import admin.mapper.base.BaseMappper;
import admin.modules.api.entity.TokenEntity;

/**
 * 用户Token
 * 
 * @author wanghonghui
 * @email 448697783@qq.com
 * @date 2017-03-23 15:22:07
 */
@Mapper
public interface TokenDao extends BaseMappper<TokenEntity,String> {
    
    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);
	
}
