package com.example.rbac.request;

import java.util.List;
import java.util.Map;

import com.example.rbac.vo.ResourceType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceRequest {

	@JsonProperty("id")
	private String id;

	@JsonProperty("type")
	private ResourceType entityType;

	@JsonProperty("attributes")
	private Map<String, String> entityAttributes;

	@JsonProperty("child_resource")
	private List<ResourceRequest> childResource;

	@Tolerate
	public ResourceRequest() {
	}

	@Tolerate
	public ResourceRequest(ResourceRequest resourceRequest) {
	}
}
