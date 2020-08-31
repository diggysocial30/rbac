package com.example.rbac.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;;


@javax.persistence.Entity
@Table(name = "groups")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Builder
public class Groups extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;

    @Column(name="name")
    private String name;
    
    @Column(name="type")
    private String type;
   
    @ManyToMany
    @JoinTable(name="user_group",joinColumns = @JoinColumn(name="group_id"),inverseJoinColumns = @JoinColumn(name="user_id"))
    private Set<Users> users;
    
    @ManyToMany
    @JoinTable(name="group_role",joinColumns = @JoinColumn(name="group_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Roles> roles;
    
    @Tolerate
    public Groups() {}
}
