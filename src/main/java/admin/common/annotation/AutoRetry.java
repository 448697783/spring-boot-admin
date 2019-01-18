package admin.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoRetry {

	/**
	 * 重试次数
	 * @return
	 */
    int value() default 5;
    /**
     * 重试延迟时间
     * @return
     */
    long waitTime() default 200;
}
