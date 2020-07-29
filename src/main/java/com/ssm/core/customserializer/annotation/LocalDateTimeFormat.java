package com.ssm.core.customserializer.annotation;

import java.lang.annotation.*;

/**
 * 时区转换注解
 *
 * @author chenly
 * @create 2020-06-03 23:24
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
public @interface LocalDateTimeFormat {

	String pattern() default "";
}
