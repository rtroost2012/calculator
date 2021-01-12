package nl.aegon.calculator.web.dto;

import lombok.Data;
import nl.aegon.calculator.enums.CalculationType;

@Data
public class CalculationDto {
    private long id;
    private CalculationType type;
    private int a;
    private int b;
    private double result;
}
