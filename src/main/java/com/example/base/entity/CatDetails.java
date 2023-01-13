package com.example.base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatDetails {
    private String id;
    private String name;
    private String temperament;
    private String origin;
    private String lifeSpan;
    private String wikipediaUrl;
    private Integer adaptability;
    private Integer affectionLevel;
    private Integer childFriendly;
    private Integer grooming;
    private Integer intelligence;
    private Integer healthIssues;
    private Integer socialNeeds;
    private Integer strangeFriendly;
    @Nullable
    private CatImage image;
}
