package nl.aegon.calculator.web.rest;

import nl.aegon.calculator.enums.CalculationType;
import nl.aegon.calculator.exception.CalculatorException;
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
        return ResponseEntity.ok(input); // int primitives can be considered 'safe'
    }

    @PostMapping("/subtract")
    public ResponseEntity<CalculationDto> subtract(@RequestBody CalculationDto input) {
        input.setType(CalculationType.SUBTRACTION);
        input.setResult(calculatorService.subtract(input.getA(), input.getB()));
        return ResponseEntity.ok(input);
    }

    @PostMapping("/divide")
    public ResponseEntity<CalculationDto> divide(@RequestBody CalculationDto input) throws CalculatorException {
        System.out.println("Dividing " + input.getA() + " by " + input.getB());
        input.setType(CalculationType.DIVISION);
        input.setResult(calculatorService.divide(input.getA(), input.getB()));
        return ResponseEntity.ok(input);
    }

    @PostMapping("/multiply")
    public ResponseEntity<CalculationDto> multiply(@RequestBody CalculationDto input) {
        input.setType(CalculationType.MULTIPLICATION);
        input.setResult(calculatorService.multiply(input.getA(), input.getB()));
        return ResponseEntity.ok(input);
    }
}
