package org.example.recommendationservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@ToString(includeFieldNames=true)
public class ProductDTO {

    private long id;
    private String name;
    private String description;
    private double price;

}
