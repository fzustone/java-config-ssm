package com.ssm.core.customserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author chenly
 * @create 2020-06-03 23:27
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

	private final String pattern;

	public LocalDateTimeSerializer(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider) throws
			IOException {
		//时区可以变成可变的，比如存储在线程本地变量中
		final String newValue = value.atZone(ZoneId.systemDefault())
				.format(DateTimeFormatter.ofPattern(pattern));
		jgen.writeString(newValue);
	}
}
