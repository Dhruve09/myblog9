package com.myblog9.payload;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {

    private List<PostDto> postDto;

    private int pageNo;

    private int pageSize;

    private long totalElements;

    private int totalPages;

    private boolean last;


}
