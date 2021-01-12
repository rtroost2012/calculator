package nl.aegon.calculator.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.aegon.calculator.enums.CalculationType;
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
                .andExpect(result -> {
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

}
