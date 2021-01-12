package nl.aegon.calculator.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.aegon.calculator.enums.CalculationType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculationDto {
    private long id;
    private CalculationType type;
    private int a;
    private int b;
    private double result;
}
