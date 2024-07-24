package formulaParser.calculation;

public class PowerCalculationStrategy implements CalculationStrategy {
    @Override
    public String calculate(String val1, String val2) {
        float a = Float.parseFloat(val1);
        float b = Float.parseFloat(val2);
        return String.valueOf(Math.pow(a, b));
    }
}
