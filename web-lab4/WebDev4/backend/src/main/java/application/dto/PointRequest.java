package application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok
@Data
@NoArgsConstructor
public class PointRequest {
    private String x;
    private String y;
    private String r;
}
