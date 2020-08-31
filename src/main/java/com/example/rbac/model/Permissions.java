package com.example.rbac.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.example.rbac.orm.PostgreSQLEnumType;
import com.example.rbac.vo.PermissionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;;


@javax.persistence.Entity
@Table(name = "permissions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@TypeDef(name = "PermissionType", typeClass = PostgreSQLEnumType.class)
@Data
@Builder
public class Permissions extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;

    @Column(name="name")
    private String name;
    
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    @Type(type = "PermissionType")
    private PermissionType permissionType;
   
    @ManyToMany
    @JoinTable(name="role_permission",joinColumns = @JoinColumn(name="permission_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Roles> roles;
    
    @OneToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
    private Resource resource;
    
    @Tolerate
    public Permissions() {}
}
