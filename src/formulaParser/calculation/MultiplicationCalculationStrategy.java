package formulaParser.calculation;

public class MultiplicationCalculationStrategy implements CalculationStrategy {
    @Override
    public String calculate(String val1, String val2) {
        float a = Float.parseFloat(val1);
        float b = Float.parseFloat(val2);
        return String.valueOf(a * b);
    }
}
