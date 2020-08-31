package com.example.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.model.Roles;
import com.example.rbac.request.ResourceRequest;
import com.example.rbac.service.DAOService;
import com.example.rbac.service.RbacService;

@Service
public class RbacServiceImpl implements RbacService {
	
	@Autowired
    private DAOService daoService;

	@Override
	public Roles createResource(ResourceRequest resource) {
		return daoService.createResource(resource);
	}

}
