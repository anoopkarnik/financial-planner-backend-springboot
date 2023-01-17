package com.bayesiansamaritan.financialplanner.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "sub_account_type",schema = "financial_schema")
public class SubAccountType {
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

    @Column(name="balance")
    private Long balance;

    @Column(name="account_type_id")
    private Long accountTypeId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="liquidity")
    private Boolean liquidity;

    @Column(name="free_liquidity")
    private Boolean freeLiquidity;

    public SubAccountType() {
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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Long accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getLiquidity() {
        return liquidity;
    }

    public void setLiquidity(Boolean liquidity) {
        this.liquidity = liquidity;
    }

    public Boolean getFreeLiquidity() {
        return freeLiquidity;
    }

    public void setFreeLiquidity(Boolean freeLiquidity) {
        this.freeLiquidity = freeLiquidity;
    }

    public SubAccountType(String name, Long balance, Long accountTypeId, Long userId, Boolean liquidity, Boolean freeLiquidity) {
        this.name = name;
        this.balance = balance;
        this.accountTypeId = accountTypeId;
        this.userId = userId;
        this.liquidity = liquidity;
        this.freeLiquidity = freeLiquidity;
    }
}
