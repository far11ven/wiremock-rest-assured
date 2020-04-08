package com.auto1.qa.models;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "page",
        "pageSize",
        "totalPageCount",
        "wkda"
})
public class ApiResponse {

    @JsonProperty("page")
    private int page;
    @JsonProperty("pageSize")
    private Double pageSize;
    @JsonProperty("totalPageCount")
    private int totalPageCount;
    @JsonProperty("wkda")
    private Map<String,String> wkda = null;

    @JsonProperty("page")
    public int getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(int page) {
        this.page = page;
    }

    @JsonProperty("pageSize")
    public Double getPageSize() {
        return pageSize;
    }

    @JsonProperty("pageSize")
    public void setPageSize(Double pageSize) {
        this.pageSize = pageSize;
    }

    @JsonProperty("totalPageCount")
    public int getTotalPageCount() {
        return totalPageCount;
    }

    @JsonProperty("totalPageCount")
    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }


    @JsonProperty("wkda")
    public Map<String,String> getWkda() {
        return wkda;
    }

    @JsonProperty("wkda")
    public void setWkda(Map<String,String> wkda) {
        this.wkda = wkda;
    }


}

