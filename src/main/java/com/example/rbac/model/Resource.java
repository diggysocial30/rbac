package com.example.rbac.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.example.rbac.orm.PostgreSQLEnumType;
import com.example.rbac.vo.ResourceType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Tolerate;;


@javax.persistence.Entity
@Table(name = "resources")
@EqualsAndHashCode(exclude = "parent")
@ToString(exclude = "parent")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@TypeDef(name = "ResourceType", typeClass = PostgreSQLEnumType.class)
@Data
@Builder
public class Resource extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    @Type(type = "ResourceType")
    private ResourceType configType;

    @ManyToOne
    @JoinColumn(name="parent_id")
    @JsonIgnore
    private Resource parent;
    
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "parent", cascade=CascadeType.REMOVE, orphanRemoval = true)
    private Set<Resource> children;
    
    @Tolerate
    public Resource() {}
}
