package com.bayesiansamaritan.financialplanner.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "budget_plan",schema = "financial_schema")
public class BudgetPlan {
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

    @Column(name="plan_percentage")
    private Long planPercentage;

    @Column(name="expense_type_id")
    private Long expenseTypeId;

    @Column(name="user_id")
    private Long userId;


    public BudgetPlan() {
    }

    public BudgetPlan(Long planPercentage, Long expenseTypeId, Long userId) {
        this.planPercentage = planPercentage;
        this.expenseTypeId = expenseTypeId;
        this.userId = userId;
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

    public Long getPlanPercentage() {
        return planPercentage;
    }

    public void setPlanPercentage(Long planPercentage) {
        this.planPercentage = planPercentage;
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
}
