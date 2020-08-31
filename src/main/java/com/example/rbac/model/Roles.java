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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.experimental.Tolerate;;


@javax.persistence.Entity
@Table(name = "roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Roles extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;

    public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Groups> getGroups() {
		return groups;
	}

	public void setGroups(Set<Groups> groups) {
		this.groups = groups;
	}

	public Set<Permissions> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permissions> permissions) {
		this.permissions = permissions;
	}

	@Column(name="name")
    private String name;
    
    @Column(name="type")
    private String type;
   
    @ManyToMany
    @JoinTable(name="group_role",joinColumns = @JoinColumn(name="role_id"),inverseJoinColumns = @JoinColumn(name="group_id"))
    @Fetch(value=FetchMode.SELECT)
    private Set<Groups> groups;
    
    @ManyToMany
    @JoinTable(name="role_permission",joinColumns = @JoinColumn(name="role_id"),inverseJoinColumns = @JoinColumn(name="permission_id"))
    @Fetch(value=FetchMode.SELECT)
    private Set<Permissions> permissions;
    
    @Tolerate
    public Roles() {}
}
