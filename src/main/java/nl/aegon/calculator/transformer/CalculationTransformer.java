package nl.aegon.calculator.transformer;

import nl.aegon.calculator.model.Calculation;
import nl.aegon.calculator.web.dto.CalculationDto;

public class CalculationTransformer {
    /**
     * Maps a calculation model to a calculation DTO
     * @param calculation The calculation model to map
     * @return The calculation DTO
     */
    public static CalculationDto toDto(Calculation calculation) {
        return new CalculationDto(calculation.getId(),
                calculation.getType(),
                calculation.getA(),
                calculation.getB(),
                calculation.getResult());
    }

    /**
     * Maps a calculation DTO to a calculation model
     * @param calculationDto The calculation DTO to map
     * @return The calculation model
     */
    public static Calculation toModel(CalculationDto calculationDto) {
        return new Calculation(calculationDto.getId(),
                calculationDto.getType(),
                calculationDto.getA(),
                calculationDto.getB(),
                calculationDto.getResult());
    }
}
