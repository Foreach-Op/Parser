package formulaParser.calculation;

public class Calculation {
    private final CalculationStrategy calculationStrategy;

    public Calculation(CalculationStrategy calculationStrategy){
        this.calculationStrategy = calculationStrategy;
    }

    public String executeCalculation(String val1, String val2){
        return calculationStrategy.calculate(val1, val2);
    }
}
