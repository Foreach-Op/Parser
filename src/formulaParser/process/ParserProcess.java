package formulaParser.process;

import formulaParser.preprocess.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParserProcess {
    private final ParserHelper parserHelper = ParserHelper.getInstance();
    private final FormulaChecker formulaChecker = new FormulaChecker();
    private final PreProcess powerPreProcess = new PowerPreProcess();
    private final PreProcess multiplicationDivisionPreProcess = new MultiplicationDivisionPreProcess();
    private final PreProcess additionExtractionPreProcess = new AdditionSubtractionPreProcess();

    private static ParserProcess parserProcess;

    private ParserProcess(){}

    public static ParserProcess getInstance(){
        if (parserProcess==null){
            parserProcess = new ParserProcess();
        }
        return parserProcess;
    }

    public String handleParserProcess(String formula, Map<String, Float> variables) throws Exception {
        List<String> formulaList = formulaChecker.handleParserPreProcess(formula, variables);
        parserHelper.removeRedundantParenthesis(formulaList);

        formulaList = handleSingleParse(formulaList, powerPreProcess);
        formulaList = handleSingleParse(formulaList, multiplicationDivisionPreProcess);
        formulaList = handleSingleParse(formulaList, additionExtractionPreProcess);

        return formulaList.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private List<String> handleSingleParse(List<String> formulaList, PreProcess preProcess){
        formulaList = preProcess.addParenthesis(formulaList, 0, formulaList.size(), formulaList.get(formulaList.size()-1).equals(")"),1);
        parserHelper.removeRedundantParenthesis(formulaList);
        String s = formulaList.stream().map(String::valueOf).collect(Collectors.joining());
        System.out.println(s);
        return formulaList;
    }


}
