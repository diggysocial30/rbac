package com.example.rbac.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rbac.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, UUID>{

}
