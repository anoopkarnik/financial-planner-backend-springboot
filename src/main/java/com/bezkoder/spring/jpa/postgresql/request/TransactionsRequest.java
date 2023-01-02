package com.bezkoder.spring.jpa.postgresql.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionsRequest {
    private String accountName;
    private Long userId;
    private Long cost;
    private String name;
    private String categoryName;
    private String subCategoryName;
    private String subAccountName;

    private String expenseName;


}
