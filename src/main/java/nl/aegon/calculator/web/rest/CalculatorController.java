package nl.aegon.calculator.web.rest;

import nl.aegon.calculator.enums.CalculationType;
import nl.aegon.calculator.service.CalculatorService;
import nl.aegon.calculator.web.dto.CalculationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    /**
     * CalculatorController constructor
     * @param calcService The calculation service to use
     */
    public CalculatorController(CalculatorService calcService) {
        calculatorService = calcService;
    }

    @PostMapping("/add")
    public ResponseEntity<CalculationDto> add(@RequestBody CalculationDto input) {
        input.setType(CalculationType.ADDITION);
        input.setResult(calculatorService.add(input.getA(), input.getB()));
        return ResponseEntity.ok(input);
    }

    @PostMapping("/subtract")
    public ResponseEntity<CalculationDto> subtract(@RequestBody CalculationDto input) {
        return null;
    }

    @PostMapping("/divide")
    public ResponseEntity<CalculationDto> divide(@RequestBody CalculationDto input) {
        return null;
    }

    @PostMapping("/multiply")
    public ResponseEntity<CalculationDto> multiply(@RequestBody CalculationDto input) {
        return null;
    }
}
