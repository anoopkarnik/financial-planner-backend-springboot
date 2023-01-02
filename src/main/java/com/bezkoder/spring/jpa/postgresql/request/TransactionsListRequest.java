package com.bezkoder.spring.jpa.postgresql.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionsListRequest {
    private Long userId;
    private String dateFrom;
    private String dateTo;
    private List<String> expenseTypes;
    private List<String> accountTypes;
    private List<String> categoryTypes;
    private List<String> subCategoryTypes;
    private List<String> subAccountTypes;

}
