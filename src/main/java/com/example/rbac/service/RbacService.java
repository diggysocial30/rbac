package com.example.rbac.service;

import com.example.rbac.model.Roles;
import com.example.rbac.request.ResourceRequest;

public interface RbacService {

	Roles createResource(ResourceRequest resource);

}
