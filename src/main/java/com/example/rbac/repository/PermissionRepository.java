package com.example.rbac.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rbac.model.Permissions;

public interface PermissionRepository extends JpaRepository<Permissions, UUID>{

}
