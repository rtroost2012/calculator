package nl.aegon.calculator.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.aegon.calculator.enums.CalculationType;
import nl.aegon.calculator.exception.CalculatorException;
import nl.aegon.calculator.service.CalculatorService;
import nl.aegon.calculator.web.dto.CalculationDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorServiceMock;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCanPerformAddition() throws Exception {
        // given
        final CalculationDto input = new CalculationDto();
        final int a = 10;
        final int b = 10;
        input.setA(a);
        input.setB(b);

        // when
        Mockito.when(calculatorServiceMock.add(a, b)).thenReturn(20.0);

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(result -> { // then
                    final MockHttpServletResponse response = result.getResponse();
                    final CalculationDto output = objectMapper.readValue(response.getContentAsString(), CalculationDto.class);
                    assertEquals(HttpStatus.OK.value(), response.getStatus());
                    assertEquals(CalculationType.ADDITION, output.getType());
                    assertEquals(20.0, output.getResult());
                    assertEquals(a, output.getA());
                    assertEquals(b, output.getB());
                });
        
        Mockito.verify(calculatorServiceMock, times(1)).add(a, b);
    }

    @Test
    public void testCanPerformSubtraction() throws Exception {
        // given
        final CalculationDto input = new CalculationDto();
        final int a = 10;
        final int b = 10;
        input.setA(a);
        input.setB(b);

        // when
        Mockito.when(calculatorServiceMock.subtract(a, b)).thenReturn(0.0);

        mockMvc.perform(post("/subtract")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(result -> { // then
                    final MockHttpServletResponse response = result.getResponse();
                    final CalculationDto output = objectMapper.readValue(response.getContentAsString(), CalculationDto.class);
                    assertEquals(HttpStatus.OK.value(), response.getStatus());
                    assertEquals(CalculationType.SUBTRACTION, output.getType());
                    assertEquals(0.0, output.getResult());
                    assertEquals(a, output.getA());
                    assertEquals(b, output.getB());
                });

        Mockito.verify(calculatorServiceMock, times(1)).subtract(a, b);
    }

    @Test
    public void testCanPerformDivision() throws Exception {
        // given
        final CalculationDto input = new CalculationDto();
        final int a = 10;
        final int b = 5;
        input.setA(a);
        input.setB(b);

        // when
        Mockito.when(calculatorServiceMock.divide(a, b)).thenReturn(2.0);

        mockMvc.perform(post("/divide")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(result -> { // then
                    final MockHttpServletResponse response = result.getResponse();
                    final CalculationDto output = objectMapper.readValue(response.getContentAsString(), CalculationDto.class);
                    assertEquals(HttpStatus.OK.value(), response.getStatus());
                    assertEquals(CalculationType.DIVISION, output.getType());
                    assertEquals(2.0, output.getResult());
                    assertEquals(a, output.getA());
                    assertEquals(b, output.getB());
                });

        Mockito.verify(calculatorServiceMock, times(1)).divide(a, b);
    }

    @Test
    public void testCannotPerformDivisionByZero() throws Exception {
        // given
        final CalculationDto input = new CalculationDto();
        final int a = 15;
        final int b = 0;
        input.setA(a);
        input.setB(b);

        // when
        Mockito.when(calculatorServiceMock.divide(a, b)).thenThrow(CalculatorException.class);

        mockMvc.perform(post("/divide")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(result ->
                    assertTrue(result.getResolvedException() instanceof CalculatorException)
                );
    }

    @Test
    public void testCanPerformMultiplication() throws Exception {
        // given
        final CalculationDto input = new CalculationDto();
        final int a = 10;
        final int b = 10;
        input.setA(a);
        input.setB(b);

        // when
        Mockito.when(calculatorServiceMock.multiply(a, b)).thenReturn(100.0);

        mockMvc.perform(post("/multiply")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(result -> { // then
                    final MockHttpServletResponse response = result.getResponse();
                    final CalculationDto output = objectMapper.readValue(response.getContentAsString(), CalculationDto.class);
                    assertEquals(HttpStatus.OK.value(), response.getStatus());
                    assertEquals(CalculationType.MULTIPLICATION, output.getType());
                    assertEquals(100.0, output.getResult());
                    assertEquals(a, output.getA());
                    assertEquals(b, output.getB());
                });

        Mockito.verify(calculatorServiceMock, times(1)).multiply(a, b);
    }
}
