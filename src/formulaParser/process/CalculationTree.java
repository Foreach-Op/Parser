package formulaParser.process;

import formulaParser.calculation.CalculationHandler;

class CalculationTree {
    private final ParserHelper parserHelper = ParserHelper.getInstance();
    private CalculationNode root;
    private int size;

    public String evaluateFormula(CalculationNode node){
        if(node.getLeftChild() == null || node.getRightChild()==null)
            return node.getValue();

        String x = evaluateFormula(node.getLeftChild());
        String y = evaluateFormula(node.getRightChild());
        String operation = node.getValue();
        System.out.println(x+" "+operation+" "+y);
        return evaluate(operation, x, y);
    }

    public CalculationNode getRoot() {
        return root;
    }

    public String evaluate(String operation, String val1, String val2){
        CalculationHandler calculationHandler = CalculationHandler.getInstance();
        return calculationHandler.calculate(operation, val1, val2);
//        float a = Float.parseFloat(x);
//        float b = Float.parseFloat(y);
//
//        return switch (operation) {
//            case "^" -> String.valueOf(Math.pow(a, b));
//            case "*" -> String.valueOf(a * b);
//            case "/" -> String.valueOf(a / b);
//            case "+" -> String.valueOf(a + b);
//            case "-" -> String.valueOf(a - b);
//            default -> "0";
//        };
    }

    public void inorderTraversal(CalculationNode node){
        if (node == null)
            return;

        inorderTraversal(node.getLeftChild());

        System.out.print(node.getValue() + " ");

        inorderTraversal(node.getRightChild());
    }


    public CalculationNode createTree(String formula){
        if(parserHelper.isNumeric(formula)){
            this.size += 1;
            return new CalculationNode(formula);
        }
        String[] partials = parserHelper.extractSingleOperation(formula);
        CalculationNode node = new CalculationNode(partials[1]);
        this.size += 1;
        if(root==null){
            this.root = node;
        }

        node.setLeftChild(createTree(partials[0]));
        node.setRightChild(createTree(partials[2]));

        return node;
    }
}

class CalculationNode{
    private String value;
    private CalculationNode rightChild;
    private CalculationNode leftChild;

    public CalculationNode(String value) {
        this.value = value;
        this.rightChild = null;
        this.leftChild = null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CalculationNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(CalculationNode rightChild) {
        this.rightChild = rightChild;
    }

    public CalculationNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(CalculationNode leftChild) {
        this.leftChild = leftChild;
    }
}
