package nl.aegon.calculator.service;

import nl.aegon.calculator.enums.CalculationType;
import nl.aegon.calculator.model.Calculation;
import nl.aegon.calculator.repository.CalculatorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CalculatorPersistenceServiceTest {
    @Mock
    private CalculatorRepository calculatorRepository;

    @InjectMocks
    private CalculatorPersistenceService calculatorPersistenceService;

    @Captor
    private ArgumentCaptor<Calculation> calculationArgumentCaptor;

    @Test
    void testCanPersistCalculation() {
        // given
        final Long id = 1L;
        final CalculationType type = CalculationType.ADDITION;
        final int a = 5;
        final int b = 10;
        final double result = 15;

        // when
        calculatorPersistenceService.save(new Calculation(id, type, a, b, result));

        // then
        Mockito.verify(calculatorRepository).save(calculationArgumentCaptor.capture());

        final Calculation receivedValue = calculationArgumentCaptor.getValue();
        assertEquals(receivedValue.getId(), id);
        assertEquals(receivedValue.getA(), a);
        assertEquals(receivedValue.getB(), b);
        assertEquals(receivedValue.getType(), type);
        assertEquals(receivedValue.getResult(), result);
    }

    @Test
    void testCanFindAllCalculations() {
        // when
        calculatorPersistenceService.findAll();

        // then
        Mockito.verify(calculatorRepository, times(1)).findAll();
    }
}
