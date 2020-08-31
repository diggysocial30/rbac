package com.example.rbac.service;

import com.example.rbac.model.Permissions;
import com.example.rbac.model.Roles;
import com.example.rbac.request.ResourceRequest;

public interface DAOService {

	Roles createResource(ResourceRequest resource);

}
