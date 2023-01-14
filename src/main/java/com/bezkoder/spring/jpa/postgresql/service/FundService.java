package com.bezkoder.spring.jpa.postgresql.service;

import com.bezkoder.spring.jpa.postgresql.model.UserProfile;
import com.bezkoder.spring.jpa.postgresql.response.FundResponse;
import com.bezkoder.spring.jpa.postgresql.response.MonthlyBudgetResponse;

import java.text.ParseException;
import java.util.List;

public interface FundService {

    public FundResponse getFundSummary(Long userId);

}
