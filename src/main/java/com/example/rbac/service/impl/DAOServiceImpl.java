package com.example.rbac.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rbac.model.Groups;
import com.example.rbac.model.Roles;
import com.example.rbac.model.Users;
import com.example.rbac.repository.GroupRepository;
import com.example.rbac.repository.PermissionRepository;
import com.example.rbac.repository.ResourceRepository;
import com.example.rbac.repository.RoleRepository;
import com.example.rbac.repository.UserRepository;
import com.example.rbac.request.ResourceRequest;
import com.example.rbac.service.DAOService;

@Service
public class DAOServiceImpl implements DAOService{
	

    @Autowired
    private ResourceRepository resourceRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PermissionRepository permissionRepository;
    
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private GroupRepository groupRepository;

	@Override
	public Roles createResource(ResourceRequest resource) {
		Users user = userRepository.getOne(UUID.fromString("8860fca3-520b-4a21-8857-9c13ba47f0f9"));
		Set<Groups> groups=new HashSet<Groups>();
		groups.add(groupRepository.getOne(UUID.fromString("e62946d3-4b7c-4c02-a614-bf51a93b8b45")));
		groups.add(groupRepository.getOne(UUID.fromString("3c7afc54-215f-441d-94bd-65cbc54a5788")));
		user.setGroups(groups);
		userRepository.save(user);
		
		return null;
	}

}
