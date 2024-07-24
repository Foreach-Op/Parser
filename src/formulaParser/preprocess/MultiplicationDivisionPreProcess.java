package formulaParser.preprocess;

import formulaParser.enums.Operations;

public class MultiplicationDivisionPreProcess extends PreProcess {
    public MultiplicationDivisionPreProcess() {
        super(new Operations[]{Operations.MULTIPLICATION, Operations.DIVISION});
    }
}
