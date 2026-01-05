package com.assessment.api_design.common.page;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class DefaultPageRequest extends PageRequest {
    public DefaultPageRequest(int pageNumber, int pageSize) {
        super(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createdDate"));
    }
}