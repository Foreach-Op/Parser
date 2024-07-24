package formulaParser.calculation;

public class CalculationHandler {

    private static CalculationHandler calculationHandler;

    private CalculationHandler(){}

    public static CalculationHandler getInstance(){
        if(calculationHandler==null)
            calculationHandler = new CalculationHandler();
        return calculationHandler;
    }


    public String calculate(String operation, String val1, String val2){
        CalculationStrategy calculationStrategy = CalculationStrategyFactory.getCalculation(operation);
        Calculation calculation = new Calculation(calculationStrategy);
        return calculation.executeCalculation(val1, val2);
    }
}
