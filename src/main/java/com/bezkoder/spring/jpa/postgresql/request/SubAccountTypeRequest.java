package com.bezkoder.spring.jpa.postgresql.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubAccountTypeRequest {
    private String accountName;
    private Long userId;
    private Long balance;
    private String name;
    private Boolean liquidity;
    private Boolean freeLiquidity;
}
