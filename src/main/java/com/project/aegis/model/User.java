package com.project.aegis.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "users",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = "username")
    })
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Role role;

  private boolean isActive;

  @Column(name = "created_at", updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;
  private String createdBy;

  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;
  private String updatedBy;

  @Column(name = "is_delete", updatable = false)
  private boolean isDelete;

  @Column(name = "delete_at", updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date deletedAt;
  private String deletedBy;

  public User(Long id, String username, String password, Role role, boolean isActive, String createdBy) {
    Date date = new Date();
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
    this.isActive = isActive;
    this.createdAt = date;
    this.createdBy = createdBy;
    this.updatedAt = date;
    this.updatedBy = createdBy;
    this.isDelete = false;
    this.deletedAt = date;
    this.deletedBy = createdBy;
  }

  public User(String username, String password, Role role, boolean isActive, String createdBy) {
    Date date = new Date();
    this.username = username;
    this.password = password;
    this.role = role;
    this.isActive = isActive;
    this.createdAt = date;
    this.createdBy = createdBy;
    this.updatedAt = date;
    this.updatedBy = createdBy;
    this.isDelete = false;
    this.deletedAt = date;
    this.deletedBy = createdBy;
  }
}
