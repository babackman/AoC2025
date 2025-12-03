import java.util.List;

public class Day03 {
    public static void Run(List<String> input)  {
        //part1(input);
        part2(input);
    }
    
    public static void part1(List<String> input)
    {
        Long totalJoltage = 0L;
        for(String line:input){
            Long bankJoltage = MaxBankJoltage(line, 2);
            totalJoltage += bankJoltage;
        }
        
        System.out.println("03.1: "+Long.toUnsignedString(totalJoltage));
    }
    
    public static void part2(List<String> input)
    {
        Long totalJoltage = 0L;
        for(String line:input){
            Long bankJoltage = MaxBankJoltage(line, 12);
            totalJoltage += bankJoltage;
        }
        
        System.out.println("03.2: "+Long.toUnsignedString(totalJoltage));
    }

    public static Long MaxBankJoltage(String batteryJoltages, int maxDigits){
        if (maxDigits >= batteryJoltages.length()){
            return Long.parseLong(batteryJoltages);
        }
        
        String bankJoltage="";
        int previousDigitIndex=-1;// index where previous digit was selected
        // digitPlace is the place value of the next digit to select (0-based index from right)
        for(int digitPlace = maxDigits-1; digitPlace >= 0; digitPlace--){
            int digitIndex = previousDigitIndex+1;
            char nextDigit=batteryJoltages.charAt(digitIndex);
            for (int i=previousDigitIndex+1; i<batteryJoltages.length()-digitPlace; i++){
                if (nextDigit=='9'){
                    break; // can't do better than 9
                }
                if (batteryJoltages.charAt(i)>nextDigit){
                    nextDigit=batteryJoltages.charAt(i);
                    digitIndex=i;
                }
            }
            previousDigitIndex=digitIndex;
            bankJoltage += nextDigit;
        }

        return Long.parseUnsignedLong(bankJoltage);
    }
}
