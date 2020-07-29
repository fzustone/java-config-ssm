package com.ssm.core.editor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssm.core.customserializer.CustomObjectMapper;

import java.beans.PropertyEditorSupport;

/**
 * @author chenly
 * @create 2020-06-30 21:02
 */
public class CustomJsonEditor extends PropertyEditorSupport {

	private final Class<?> collectionType;

	public CustomJsonEditor(Class<?> collectionType) {
		this.collectionType = collectionType;
	}

	@Override
	public void setAsText(String value) {
		setValue(value);
	}

	@Override
	public void setValue(Object value) {
		if(value==null){
			return;
		}
		try {
			Object readValue = CustomObjectMapper.INSTANCE.readValue(value.toString(), collectionType);
			super.setValue(readValue);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the value of the property.
	 *
	 * @return The value of the property.
	 */
	@Override
	public String getAsText() {
		return null;
	}
}
