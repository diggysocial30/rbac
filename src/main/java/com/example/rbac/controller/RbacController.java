package com.example.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rbac.model.Roles;
import com.example.rbac.request.ResourceRequest;
import com.example.rbac.service.RbacService;

@RestController
@RequestMapping("/api/rbac")
public class RbacController {

	@Autowired
	private RbacService rbacService;

	@PostMapping(value = "/resource")
	public Roles createResource(@RequestBody ResourceRequest resource) {
		return rbacService.createResource(resource);
	}
	
	@GetMapping(value = "/hello")
	public String hello() {
		return "hello";
	}
}
