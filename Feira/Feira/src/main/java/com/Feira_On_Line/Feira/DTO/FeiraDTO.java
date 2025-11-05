// FeiraDTO.java
package com.Feira_On_Line.Feira.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeiraDTO {
    private Long id;
    private String name;
    private String description;
    private String location;
    private String neighborhood;
    private Double latitude;
    private Double longitude;
    private String schedule;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private String amenities;
}
