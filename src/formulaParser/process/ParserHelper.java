package formulaParser.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParserHelper {
    private static ParserHelper parserHelper;

    private ParserHelper(){}

    public static ParserHelper getInstance(){
        if(parserHelper==null){
            parserHelper = new ParserHelper();
        }
        return parserHelper;
    }

    public int[] extractFormulaBetweenParenthesis(List<String> lst, int endInd){
        int[] indexes = new int[2];
        indexes[1] = endInd;
        int parenthesisAmount = 0;
        for (int i = endInd; i >= 0; i--) {
            String str = lst.get(i);
            if(str.equals("(")){
                parenthesisAmount += 1;
            }
            if(str.equals(")")){
                parenthesisAmount -= 1;
            }
            if(parenthesisAmount==0){
                indexes[0] = i+1;
                break;
            }
        }
        return indexes;
    }

    public String[] extractSingleOperation(String formula){
        List<String> lst = new ArrayList<String>(Arrays.asList(formula.split("")));
        lst = acquireFloatNumber(lst);
        String[] partials = new String[3];

        int endInd = -1;
        int parenthesisAmount = 0;
        for (int i = 0; i < lst.size(); i++) {
            String str = lst.get(i);
            if(str.equals("(")){
                parenthesisAmount += 1;
            }
            if(str.equals(")")){
                parenthesisAmount -= 1;
            }
            if(parenthesisAmount==0){
                endInd = i;
                break;
            }
        }
        List<String> subLst = lst.subList(1, endInd);

        if(!subLst.get(0).equals("(")){
            partials[0] = subLst.get(0);
            partials[1] = subLst.get(1);
            partials[2] = subLst.subList(2,subLst.size()).stream().map(String::valueOf).collect(Collectors.joining());
        }else {
            parenthesisAmount = 0;
            endInd = -1;
            for (int i = 0; i < subLst.size(); i++) {
                String str = subLst.get(i);
                if(str.equals("(")){
                    parenthesisAmount += 1;
                }
                if(str.equals(")")){
                    parenthesisAmount -= 1;
                }
                if(parenthesisAmount==0){
                    endInd = i+1;
                    break;
                }
            }
            partials[0] = subLst.subList(0,endInd).stream().map(String::valueOf).collect(Collectors.joining());
            partials[1] = subLst.get(endInd);
            partials[2] = subLst.subList(endInd+1,subLst.size()).stream().map(String::valueOf).collect(Collectors.joining());
        }
        return partials;
    }

    public List<String> acquireFloatNumber(List<String> lst){
        List<String> returnedLst = new ArrayList<>();
        boolean isFloatStarted = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lst.size(); i++) {
            String curr = lst.get(i);
            String next = "";
            if(i<lst.size()-1)
                next = lst.get(i+1);
            if(next.equals(".")){
                isFloatStarted = true;
            }else if (isNumeric(curr) && isNumeric(next)) {
                isFloatStarted = true;
            }

            if(isFloatStarted){
                if(!(isNumeric(next)||next.equals("."))){
                    isFloatStarted = false;
                }else {
                    sb.append(curr);
                    continue;
                }
            }
            sb.append(curr);

            returnedLst.add(sb.toString());
            sb = new StringBuilder();
        }
        return returnedLst;
    }

    public List<String> acquireNegativeNumber(List<String> lst){
        List<String> returnedLst = new ArrayList<>();
        for (int i = 0; i < lst.size(); i++) {
            String curr = lst.get(i);
            String next = "";
            if(i<lst.size()-1)
                next = lst.get(i+1);
            returnedLst.add(curr);

            if(curr.equals("(") && next.equals("-")){
                returnedLst.add("0");
            }
        }
        return returnedLst;
    }

    public boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    public void removeRedundantParenthesis(List<String> lst){
        int prevStartInd = -1;
        int prevEndInd = -1;
        List<Integer> removedIndexes = new ArrayList<>();

        for (int i = 0; i < lst.size(); i++) {
            String str = lst.get(i);
            if(str.equals("(")){
                int[] indexes = removeRedundantParenthesisHelper(lst, i);
                int currStartInd = indexes[0] - 1;
                int currEndInd = indexes[1];
                if(prevStartInd+1==currStartInd && prevEndInd-1==currEndInd){
                    removedIndexes.add(prevStartInd);
                    removedIndexes.add(prevEndInd);
                }
                prevStartInd = currStartInd;
                prevEndInd = currEndInd;
            }
        }
        Collections.sort(removedIndexes);
        Collections.reverse(removedIndexes);

        for (int removedInd : removedIndexes) {
            lst.remove(removedInd);
        }
    }

    private static int[] removeRedundantParenthesisHelper(List<String> lst, int startInd){
        int[] indexes = new int[2];
        indexes[0] = startInd+1;
        int parenthesisAmount = 0;
        for (int i = startInd; i < lst.size(); i++) {
            String str = lst.get(i);
            if(str.equals("(")){
                parenthesisAmount += 1;
            }
            if(str.equals(")")){
                parenthesisAmount -= 1;
            }
            if(parenthesisAmount==0){
                indexes[1] = i;
                break;
            }
        }
        return indexes;
    }
}
