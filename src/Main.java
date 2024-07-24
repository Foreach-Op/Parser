import formulaParser.process.CalculationProcess;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            String formula1 = "((2*(4/(5+(90/5)()))^2))";
            String formula2 = "2^3/  a-0^2";
            String formula3 = "(0.6*0.2)-0*10";
            String formula4 = "(1*1)+(0*10)";
            String formula5 = "(1-(-1.5))(0+10)";
            String formula6 = "4^(-1/2)*(a+b)";
            Map<String, Float> mp = new HashMap<>();
            mp.put("a",-10f);
            mp.put("b",-10f);
            String result = CalculationProcess.getInstance().evaluateFormula(formula6, mp);
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
