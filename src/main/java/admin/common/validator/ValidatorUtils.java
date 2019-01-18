package admin.common.validator;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import admin.common.exception.CheckException;

/**
 *
 * @ClassName: ValidatorUtils 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author honghuiwang 
 * @date 2017年9月10日 下午5:35:48 
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws CheckException  校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws CheckException {
    	if(object==null) {
            throw new CheckException("请填写请求参数");
    	}
    	if(object instanceof List) {
    		List list = (List)object;
    		for (Object temp : list) {
    			Set<ConstraintViolation<Object>> constraintViolations = validator.validate(temp, groups);
    			if (!constraintViolations.isEmpty()) {
        			StringBuilder msg = new StringBuilder();
        			for(ConstraintViolation<Object> constraint:  constraintViolations){
        				msg.append(constraint.getMessage());
        				throw new CheckException(msg.toString());
        			}
        		}
			}
    	}else {
    		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
    		if (!constraintViolations.isEmpty()) {
    			StringBuilder msg = new StringBuilder();
    			for(ConstraintViolation<Object> constraint:  constraintViolations){
    				msg.append(constraint.getMessage());
    				throw new CheckException(msg.toString());
    			}
    		}
    	}
    }
}
