// ProductDTO.java
package com.Feira_On_Line.Feira.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProdutoDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private String unit;
    private Integer stock;
    private String imageUrl;
    private Boolean organic;
    private Boolean local;
    private Double rating;
    private Integer views;
    private Long vendorId;
    private String vendorName;
}