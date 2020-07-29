package com.ssm.core.customserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author chenly
 * @create 2020-06-04 20:49
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {
	private final String format = "###,##0.00";

	@Override
	public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		final String newValue = new DecimalFormat(format).format(value);
		gen.writeString(newValue);
	}
}
