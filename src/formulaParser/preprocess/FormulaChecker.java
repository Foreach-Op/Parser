package formulaParser.preprocess;

import formulaParser.process.ParserHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FormulaChecker {

    private final ParserHelper parserHelper = ParserHelper.getInstance();

    public List<String> handleParserPreProcess(String formula, Map<String, Float> variables) throws Exception {
        formula = changeVariables(formula, variables);
        List<String> myList = new ArrayList<String>(Arrays.asList(formula.split("")));
        boolean areParenthesisValid = checkParenthesis(myList);
        if(!areParenthesisValid)
            throw new Exception();
        boolean areElementsValid = checkElements(myList);
        if(!areElementsValid)
            throw new Exception();
        trimSpaces(myList);
        System.out.println(myList);
        myList = parserHelper.acquireFloatNumber(myList);
        myList = parserHelper.acquireNegativeNumber(myList);
        System.out.println(myList);
        return myList;
    }

    private String changeVariables(String formula, Map<String, Float> variables){
        if(variables==null)
            return formula;
        for(String key: variables.keySet()){
            float value = variables.get(key);
            String str_val = "";
            if (value < 0)
                str_val = "(" + value + ")";
            else
                str_val = String.valueOf(value);

            formula = formula.replaceAll(key, str_val);
        }
        return formula;
    }

    private void trimSpaces(List<String> myList){
        myList.removeIf(String::isBlank);
    }

    private boolean checkParenthesis(List<String> myList){
        int parenthesisClose = 0;
        for(String s:myList){
            if(s.equals("(")){
                parenthesisClose += 1;
            }
            else if (s.equals(")")) {
                parenthesisClose -= 1;
            }
        }
        return (parenthesisClose==0);
    }

    private boolean checkElements(List<String> myList){
        for (String curr : myList) {
            if (Character.isLetter(curr.charAt(0))) {
                return false;
            }
        }
        return true;
    }
}

