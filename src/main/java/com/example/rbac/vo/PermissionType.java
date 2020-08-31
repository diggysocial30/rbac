package com.example.rbac.vo;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PermissionType {

	ALLOWED,NOT_ALLOWED;

	private static Map<String, PermissionType> permissionMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	static {
		permissionMap.put("allowed", ALLOWED);
		permissionMap.put("not_allowed", NOT_ALLOWED);
	}

	@JsonValue
	public String toValue() {
		for (Map.Entry<String, PermissionType> entry : permissionMap.entrySet()) {
			if (entry.getValue() == this)
				return entry.getKey().toUpperCase();
		}
		return null; // or fail
	}

	public static PermissionType fromValue(String value) {
		PermissionType type = permissionMap.get(value);
		if (type == null) {
			throw new IllegalArgumentException("Unknown entity type " + value);
		}
		return type;
	}
}
