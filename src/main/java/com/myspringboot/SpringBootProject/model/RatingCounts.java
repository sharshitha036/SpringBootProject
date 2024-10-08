package com.myspringboot.SpringBootProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingCounts {
    private int high;
    private int medium;
    private int low;
}
