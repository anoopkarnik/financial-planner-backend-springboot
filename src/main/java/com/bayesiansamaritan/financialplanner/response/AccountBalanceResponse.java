package com.bayesiansamaritan.financialplanner.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountBalanceResponse {

    private Long id;
    private String name;
    private Long balance;
}
