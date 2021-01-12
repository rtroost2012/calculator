package nl.aegon.calculator.repository;

import nl.aegon.calculator.model.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculatorRepository extends JpaRepository<Calculation, Long> {
}