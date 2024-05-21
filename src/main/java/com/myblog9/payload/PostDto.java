package com.myblog9.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private Long id;

    @NotEmpty                                                            //Spring Validation(if field is empty thm it will show error)
    @Size(min = 2, message = "POst Title Should be at least 2 character")//Spring Validation(if size is less thn 2 character than the error msg will be shown in the postman response)
    private String title;

    @NotEmpty
    @Size(min = 4, message = "POst Description Should be at least 4 character")
    private String description;

    @NotEmpty
    @Size(min = 5, message = "POst content Should be at least 5 character")
    private String content;
}
