package com.example.base.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatImage {
    private String id;
    private Integer width;
    private Integer height;
    private String url;
}
