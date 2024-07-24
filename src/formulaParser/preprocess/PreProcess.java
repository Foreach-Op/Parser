package formulaParser.preprocess;

import formulaParser.process.ParserHelper;
import formulaParser.enums.Operations;

import java.util.List;

public class PreProcess {
    private final Operations[] MAIN_OPERATIONS;
    private final ParserHelper parserHelper = ParserHelper.getInstance();

    protected PreProcess(Operations[] MAIN_OPERATIONS) {
        this.MAIN_OPERATIONS = MAIN_OPERATIONS;
    }

    public List<String> addParenthesis(List<String> lst, int startInd, int endInd, boolean passFirstParenthesis, int layer){
        List<String> copyList = List.copyOf(lst);
//        System.out.println("copyList:"+copyList.subList(startInd,endInd) + ",indexes:"+startInd+","+endInd);
        boolean isStarted = false;
        int parenthesisNum = 0;

        for (int i = endInd-1; i >= startInd; i--) {
            String prev = "";
            String nextSign = "";
            if(i<endInd-1)
                prev = copyList.get(i+1);
            if(i>=2)
                nextSign = copyList.get(i-2);
            String curr = copyList.get(i);
            if(isInMainOperations(curr)){
                if (passFirstParenthesis) {
                    passFirstParenthesis = false;
                    if(!(nextSign.equals("+")||nextSign.equals("-"))){
                        continue;
                    }
                }
                else if(!isStarted){
                    isStarted = true;
                }
                else if(prev.equals("(")){
                    continue;
                }

                lst.add(i+2,")");
                parenthesisNum += 1;
            } else if (isInOtherOperations(curr)) {
                passFirstParenthesis = false;
                for (int j = 0; j < parenthesisNum; j++) {
                    lst.add(i+1, "(");
                }
                parenthesisNum = 0;
                isStarted = false;
            } else if (i==startInd && isStarted) {
                for (int j = 0; j < parenthesisNum; j++) {
                    lst.add(startInd, "(");
                }
                parenthesisNum = 0;
                isStarted = false;
            } else if (curr.equals(")")) {
                isStarted = true;
                lst.add(i,")");
                parenthesisNum += 1;
                int[] indexes = parserHelper.extractFormulaBetweenParenthesis(copyList, i);
                int startIndex = indexes[0];
                int endIndex = indexes[1];
                i = indexes[0];
                lst = addParenthesis(lst, startIndex, endIndex, false, layer+1);
            }
        }
        return lst;
    }


    private boolean isInMainOperations(String str){
        for(Operations opt: MAIN_OPERATIONS){
            if(str.equals(opt.operation)){
                return true;
            }
        }
        return false;
    }

    private boolean isInOtherOperations(String str){
        for(Operations opt: Operations.values()){
            if(str.equals(opt.operation)){
                return true;
            }
        }
        return false;
    }
}
