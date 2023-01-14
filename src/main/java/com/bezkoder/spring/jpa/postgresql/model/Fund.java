package com.bezkoder.spring.jpa.postgresql.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "funds",schema = "financial_schema")
public class Fund {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    protected Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    protected Date updatedAt;

    @Column(name="amount_allocated")
    private Long amountAllocated;

    @Column(name="amount_needed")
    private Long amountNeeded;

    @Column(name="user_id")
    private Long userId;

    @Column(name="active")
    private Boolean active;

    public Fund() {
    }

    public Fund(String name, Long amountAllocated, Long amountNeeded, Long userId, Boolean active) {
        this.name = name;
        this.amountAllocated = amountAllocated;
        this.amountNeeded = amountNeeded;
        this.userId = userId;
        this.active = active;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmountAllocated() {
        return amountAllocated;
    }

    public void setAmountAllocated(Long amountAllocated) {
        this.amountAllocated = amountAllocated;
    }

    public Long getAmountNeeded() {
        return amountNeeded;
    }

    public void setAmountNeeded(Long amountNeeded) {
        this.amountNeeded = amountNeeded;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
