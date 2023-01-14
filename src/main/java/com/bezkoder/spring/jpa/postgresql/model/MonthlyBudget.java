package com.bezkoder.spring.jpa.postgresql.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "monthly_budget",schema = "financial_schema")
public class MonthlyBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    protected Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    protected Date updatedAt;
    @Column(name="cost")
    private Long cost;

    @Column(name="expense_type_id")
    private Long expenseTypeId;

    @Column(name="active")
    private Boolean active;
    @Column(name="category_type_id")
    private Long categoryTypeId;

    @Column(name="sub_category_type_id")
    private Long subCategoryTypeId;

    @Column(name="user_id")
    private Long userId;

    public MonthlyBudget() {
    }


    public long getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }


    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getExpenseTypeId() {
        return expenseTypeId;
    }

    public void setExpenseTypeId(Long expenseTypeId) {
        this.expenseTypeId = expenseTypeId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getCategoryTypeId() {
        return categoryTypeId;
    }

    public void setCategoryTypeId(Long categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    public Long getSubCategoryTypeId() {
        return subCategoryTypeId;
    }

    public void setSubCategoryTypeId(Long subCategoryTypeId) {
        this.subCategoryTypeId = subCategoryTypeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public MonthlyBudget(Long cost, Long expenseTypeId, Boolean active, Long categoryTypeId, Long subCategoryTypeId, Long userId) {
        this.cost = cost;
        this.expenseTypeId = expenseTypeId;
        this.active = active;
        this.categoryTypeId = categoryTypeId;
        this.subCategoryTypeId = subCategoryTypeId;
        this.userId = userId;
    }
}
