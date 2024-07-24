package formulaParser.process;

import java.util.Map;

public class CalculationProcess {
    private final ParserProcess parserProcess = ParserProcess.getInstance();
    private static CalculationProcess calculationProcess;

    private CalculationProcess() {}

    public static CalculationProcess getInstance(){
        if (calculationProcess==null){
            calculationProcess = new CalculationProcess();
        }
        return calculationProcess;
    }

    public String evaluateFormula(String formula, Map<String, Float> variables) throws Exception {
        String parsedFormula = parserProcess.handleParserProcess(formula, variables);
        CalculationTree calculationTree = new CalculationTree();
        calculationTree.createTree(parsedFormula);
        return calculationTree.evaluateFormula(calculationTree.getRoot());
    }
}
