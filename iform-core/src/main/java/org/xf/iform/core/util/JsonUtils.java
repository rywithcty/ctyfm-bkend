package org.xf.iform.core.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {

	private final static ObjectMapper objectMapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	private JsonUtils() {

	}

	public static String toJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error("Occur error during parsing data to json: {}", obj, e);
			return "";
		}
	}

	public static <T> T toObject(String json, Class<T> objectClass) {
		try {
			return objectMapper.readValue(json, objectClass);
		} catch (IOException e) {
			log.error("Occur error during mapping json to an object: {}", json, e);
			return null;
		}
	}
}
