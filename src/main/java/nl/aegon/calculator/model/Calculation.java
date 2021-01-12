package nl.aegon.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.aegon.calculator.enums.CalculationType;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calculation {
    @Id
    private long id;

    private CalculationType type;

    private int a;

    private int b;

    private double result;
}
