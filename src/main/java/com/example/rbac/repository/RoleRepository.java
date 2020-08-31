package com.example.rbac.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rbac.model.Roles;

public interface RoleRepository extends JpaRepository<Roles, UUID>{

}
