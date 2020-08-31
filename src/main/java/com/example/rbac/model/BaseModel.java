package com.example.rbac.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "updated_at", "created_at", "created_by", "updated_by",
		"is_deleted" }, allowGetters = true)
@Data
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	@LastModifiedDate
	private Date updatedAt;

	@JsonProperty("created_at")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	private Date createdAt;

	@JsonProperty("created_by")
	@Column(name = "created_by", nullable = false, updatable = false)
	@CreatedBy
	private String createdBy;

	@JsonProperty("updated_by")
	@Column(name = "updated_by")
	@LastModifiedBy
	private String updatedBy;

	@JsonProperty("is_deleted")
	@Column(name = "is_deleted", nullable = false)
	private Character deleted ='N';

	@PrePersist
	public void prePersist() {
		String createdByUser = "admin";
		this.createdBy = createdByUser;
		this.updatedBy = createdByUser;
	}

	@PreUpdate
	public void preUpdate() {
		String modifiedByUser = "admin";
		this.updatedBy = modifiedByUser;
	}
}
