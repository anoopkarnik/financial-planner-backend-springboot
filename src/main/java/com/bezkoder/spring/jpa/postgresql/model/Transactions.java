package com.bezkoder.spring.jpa.postgresql.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "transactions",schema = "financial_schema")
public class Transactions {
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

    @Column(name="cost")
    private Long cost;

    @Column(name="expense_type_id")
    private Long expenseTypeId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="account_id")
    private Long accountId;

    @Column(name="category_id")
    private Long categoryId;

    @Column(name="sub_account_id")
    private Long subAccountId;

    @Column(name="sub_category_id")
    private Long subCategoryId;

    @Column(name="active")
    private Boolean active;

    public Transactions() {
    }

    public Transactions(String name, Long cost, Long expenseTypeId, Long userId, Long accountId, Long categoryId, Long subAccountId, Long subCategoryId, Boolean active) {
        this.name = name;
        this.cost = cost;
        this.expenseTypeId = expenseTypeId;
        this.userId = userId;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.subAccountId = subAccountId;
        this.subCategoryId = subCategoryId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSubAccountId() {
        return subAccountId;
    }

    public void setSubAccountId(Long subAccountId) {
        this.subAccountId = subAccountId;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
