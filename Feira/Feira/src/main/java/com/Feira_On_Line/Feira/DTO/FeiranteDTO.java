// VendorDTO.java

package com.Feira_On_Line.Feira.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeiranteDTO {
    private Long id;
    private String barracaName;
    private String description;
    private String logoUrl;
    private Double rating;
    private Integer totalReviews;
    private String ownerName;
    private String ownerEmail;
}