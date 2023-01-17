package com.bayesiansamaritan.financialplanner.service;

import com.bayesiansamaritan.financialplanner.response.FundResponse;

public interface FundService {

    public FundResponse getFundSummary(Long userId);

}
