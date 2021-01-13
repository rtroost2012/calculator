package nl.aegon.calculator.service;

import nl.aegon.calculator.model.Calculation;
import nl.aegon.calculator.repository.CalculatorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CalculatorPersistenceService {
    private final CalculatorRepository calculatorRepository;

    /**
     * CalculatorPersistenceService constructor
     * @param calcRepository The calculator repository to use
     */
    public CalculatorPersistenceService(CalculatorRepository calcRepository) {
        calculatorRepository = calcRepository;
    }

    /**
     * Find all the performed calculations
     * @return A list of calculation objects
     */
    @Transactional
    public List<Calculation> findAll() {
        return calculatorRepository.findAll();
    }

    /**
     * Persist a performed calculation
     * @param result The calculation result
     */
    @Transactional
    public void save(Calculation result) {
        calculatorRepository.save(result);
    }
}
