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
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Builder
public class Users extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;

    @Column(name="username")
    private String username;
    
    @ManyToMany
    @JoinTable(name="user_group",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="group_id"))
    private Set<Groups> groups;
    
    @Tolerate
    public Users() {}
}
