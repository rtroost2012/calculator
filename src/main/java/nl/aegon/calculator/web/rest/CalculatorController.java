package nl.aegon.calculator.web.rest;

import nl.aegon.calculator.enums.CalculationType;
import nl.aegon.calculator.exception.CalculatorException;
import nl.aegon.calculator.model.Calculation;
import nl.aegon.calculator.service.CalculatorPersistenceService;
import nl.aegon.calculator.service.CalculatorService;
import nl.aegon.calculator.transformer.CalculationTransformer;
import nl.aegon.calculator.web.dto.CalculationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CalculatorController {

    private final CalculatorService calculatorService;
    private final CalculatorPersistenceService calculatorPersistenceService;

    /**
     * CalculatorController constructor
     * @param calcService The calculation service to use
     */
    public CalculatorController(CalculatorService calcService, CalculatorPersistenceService calcPersistenceService) {
        calculatorService = calcService;
        calculatorPersistenceService = calcPersistenceService;
    }

    @PostMapping("/add")
    public ResponseEntity<CalculationDto> add(@RequestBody CalculationDto input) {
        final Calculation calculation = CalculationTransformer.toModel(input);
        calculation.setType(CalculationType.ADDITION);
        calculation.setResult(calculatorService.add(input.getA(), input.getB()));
        calculatorPersistenceService.save(calculation);
        return ResponseEntity.ok(CalculationTransformer.toDto(calculation));
    }

    @PostMapping("/subtract")
    public ResponseEntity<CalculationDto> subtract(@RequestBody CalculationDto input) {
        final Calculation calculation = CalculationTransformer.toModel(input);
        calculation.setType(CalculationType.SUBTRACTION);
        calculation.setResult(calculatorService.subtract(input.getA(), input.getB()));
        calculatorPersistenceService.save(calculation);
        return ResponseEntity.ok(CalculationTransformer.toDto(calculation));
    }

    @PostMapping("/divide")
    public ResponseEntity<CalculationDto> divide(@RequestBody CalculationDto input) throws CalculatorException {
        final Calculation calculation = CalculationTransformer.toModel(input);
        calculation.setType(CalculationType.DIVISION);
        calculation.setResult(calculatorService.divide(input.getA(), input.getB()));
        calculatorPersistenceService.save(calculation);
        return ResponseEntity.ok(CalculationTransformer.toDto(calculation));
    }

    @PostMapping("/multiply")
    public ResponseEntity<CalculationDto> multiply(@RequestBody CalculationDto input) {
        final Calculation calculation = CalculationTransformer.toModel(input);
        calculation.setType(CalculationType.MULTIPLICATION);
        calculation.setResult(calculatorService.multiply(input.getA(), input.getB()));
        calculatorPersistenceService.save(calculation);
        return ResponseEntity.ok(CalculationTransformer.toDto(calculation));
    }
}
