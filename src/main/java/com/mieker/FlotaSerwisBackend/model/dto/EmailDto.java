package com.mieker.FlotaSerwisBackend.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailDto {

    private String email;
    private String title;
    private String body;
}
