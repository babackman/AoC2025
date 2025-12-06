import java.util.ArrayList;
import java.util.List;

public class Day06 {
    public static void Run(List<String> input) {
        part1(input);
        part2(input);
    }

    private static void part1(List<String> input) {
        List<MathProblem> mathProblems = ReadMathProblems(input);
        Long totalSum = 0L;
        for (MathProblem problem : mathProblems) {
            totalSum += problem.Evaluate();
        }
        System.out.println("06.1: " + Long.toUnsignedString(totalSum));
    }

    private static void part2(List<String> input) {
        List<MathProblem> mathProblems = ReadCephalopodMathProblems(input);
        Long totalSum = 0L;
        for (MathProblem problem : mathProblems) {
            totalSum += problem.Evaluate();
        }
        System.out.println("06.2: " + Long.toUnsignedString(totalSum));
    }
    
    private static List<MathProblem> ReadMathProblems(List<String> input) {
        List<MathProblem> problems;
        int numOperands =input.size() -1;
        
        String inputLine = input.get(numOperands);
        String[] tokens = inputLine.split("\\s+");
        problems = new ArrayList<MathProblem>(tokens.length);
        for (String token : tokens) {
            char operator = token.charAt(0);
            MathProblem problem = new MathProblem(operator, numOperands);
            problems.add(problem);
        }
        
        for (int i=0; i<numOperands; i++) {
            inputLine = input.get(i).trim();
            tokens = inputLine.split("\\s+");
            for (int j=0; j<tokens.length; j++) {
                long operand = Long.parseLong(tokens[j]);
                problems.get(j).AddOperand(operand);
            }
        }
        return problems;
    }
    
    private static List<MathProblem> ReadCephalopodMathProblems(List<String> input) {
        // read operands from columns, not tokens

        // positions of operators mark the leading edges of problems
        ArrayList<Integer> operatorPositions = new ArrayList<Integer>();
        String operandLine = input.get(input.size() - 1);
        for (int i = 0; i < operandLine.length(); i++) {
            if (operandLine.charAt(i) != ' ') {
                operatorPositions.add(i);
            }
        }
        ArrayList<MathProblem> problems = new ArrayList<MathProblem>(operatorPositions.size());

        for (int problemIndex = 0; problemIndex < operatorPositions.size(); problemIndex++) {
            int startIndex = operatorPositions.get(problemIndex);
            char operator = operandLine.charAt(startIndex);
            int endIndex = (problemIndex == (operatorPositions.size() - 1))
                    ? operandLine.length() - 1
                    : operatorPositions.get(problemIndex + 1) - 2;
            MathProblem problem = new MathProblem(operator, endIndex - startIndex + 1);
            for (int column = endIndex; column >= startIndex; column--) {
                String operandString = "";
                for (int row = 0; row < input.size() - 1; row++) {
                    char ch = input.get(row).charAt(column);
                    if (ch != ' ')
                        operandString = operandString + ch;
                }
                problem.AddOperand(Long.parseLong(operandString));
            }
            problems.add(problem);
        }
        return problems;
    }
    
    static class MathProblem {
        ArrayList<Long> _operands;
        char _operator;
        
        public MathProblem(char operator, int initialCapacity) {
            _operands = new ArrayList<Long>(initialCapacity);
            _operator = operator;
        }
        
        public void AddOperand(Long operand) {
            _operands.add(operand);
        }
        
        public Long Evaluate() {
            if (_operands.size()==0)
                return 0L ;
            Long result = _operands.get(0);
            for (int i=1; i<_operands.size(); i++) {
                Long operand = _operands.get(i);
                switch (_operator) {
                    case '+':
                        result += operand;
                        break;
                    case '*':
                        result *= operand;
                        break;
                }
            }
            return result;  
        }
    }
}
