import java.util.ArrayList;
import java.util.List;

public class Day06 {
    private List<MathProblem> _mathProblems;
    
    public static void Run(List<String> input) {
        Day06 day06 = new Day06(input);
        day06.part1();
        // day06.part2();
    }

    public Day06(List<String> input) {
        super();
        _mathProblems = ReadMathProblems(input);
    }
    
    private void part1() {
        Long totalSum = 0L;
        for (MathProblem problem : _mathProblems) {
            totalSum += problem.Evaluate();
        }
        System.out.println("06.1: " + Long.toUnsignedString(totalSum));
    }
    
    private List<MathProblem> ReadMathProblems(List<String> input) {
        List<MathProblem> problems = new ArrayList<MathProblem>();
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
    class MathProblem {
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
