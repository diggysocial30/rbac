package com.example.rbac.vo;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResourceType {

	API, SERVICE, APPLICATION;

	private static Map<String, ResourceType> resourceMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	static {
		resourceMap.put("api", API);
		resourceMap.put("service", SERVICE);
		resourceMap.put("application", APPLICATION);
	}

	@JsonValue
	public String toValue() {
		for (Map.Entry<String, ResourceType> entry : resourceMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey().toUpperCase();
		}
		return null; // or fail
	}

	public static ResourceType fromValue(String value) {
		ResourceType type = resourceMap.get(value);
		if (type == null) {
			throw new IllegalArgumentException("Unknown entity type " + value);
		}
		return type;
	}
}
