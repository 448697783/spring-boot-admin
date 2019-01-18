package admin.common.validator;

import org.apache.commons.lang.StringUtils;

import admin.common.exception.CheckException;


/**
 * 
 * @ClassName: Assert 
 * @Description: 数据校验 
 * @author honghuiwang 
 * @date 2017年9月10日 下午5:36:47 
 *
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new CheckException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new CheckException(message);
        }
    }
}
