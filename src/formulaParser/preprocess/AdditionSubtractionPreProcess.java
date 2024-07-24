package formulaParser.preprocess;

import formulaParser.enums.Operations;

public class AdditionSubtractionPreProcess extends PreProcess {
    public AdditionSubtractionPreProcess() {
        super(new Operations[]{Operations.ADDITION, Operations.EXTRACTION});
    }
}
