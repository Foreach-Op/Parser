package formulaParser.calculation;

public class CalculationStrategyFactory {
    public static CalculationStrategy getCalculation(String operation){
        return switch (operation) {
            case "^" -> new PowerCalculationStrategy();
            case "*" -> new MultiplicationCalculationStrategy();
            case "/" -> new DivisionCalculationStrategy();
            case "+" -> new AdditionCalculationStrategy();
            case "-" -> new SubtractionCalculationStrategy();
            default -> null;
        };
    }
}
