package com.example.rbac.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rbac.model.Users;

public interface UserRepository extends JpaRepository<Users, UUID>{

}
