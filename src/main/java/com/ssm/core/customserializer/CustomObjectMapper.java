package com.ssm.core.customserializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ssm.core.customserializer.annotation.LocalDateTimeFormat;

import java.math.BigDecimal;

/**
 * 自定义序列化的ObjectMapper
 *
 * @author chenly
 * @create 2020-06-04 19:30
 */
public class CustomObjectMapper extends ObjectMapper {

	public static final CustomObjectMapper INSTANCE = new CustomObjectMapper();

	private CustomObjectMapper() {
		//设置null值不参与序列化(字段不被显示)
		this.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		// 将数字转成字符串输出
		this.configure(JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS.mappedFeature(), true);
		this.configure(JsonWriteFeature.WRITE_NAN_AS_STRINGS.mappedFeature(), true);

		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(BigDecimal.class,new BigDecimalSerializer());
		this.registerModule(simpleModule);
		this.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {
			@Override
			public Object findSerializer(Annotated annotated) {
				if (_hasAnnotation(annotated, LocalDateTimeFormat.class)) {
					LocalDateTimeFormat localDateTimeFormat = annotated.getAnnotation(LocalDateTimeFormat.class);
					return new LocalDateTimeSerializer(localDateTimeFormat.pattern());

				}
				return super.findSerializer(annotated);
			}
		});
	}
}
