import java.util.List;

public class Day03 {
    public static void Run(List<String> input)  {
        part1(input);
    }
    
    public static void part1(List<String> input)
    {
        long totalJoltage = 0L;
        for(String line:input){
            long bankJoltage = MaxBankJoltage(line);
            totalJoltage += bankJoltage;
        }
        
        System.out.println("03.1: "+Long.toUnsignedString(totalJoltage));
    }
    public static long MaxBankJoltage(String batteryJoltages){
        char firstDigit=batteryJoltages.charAt(0);
        int firstDigitIndex=0;
        for(int i=1; i<batteryJoltages.length()-1; i++){
            if (batteryJoltages.charAt(i)>firstDigit){
                firstDigit=batteryJoltages.charAt(i);
                firstDigitIndex=i;
                if (firstDigit=='9'){
                    break; // can't do better than 9
                }
            }
        }
        
        char secondDigit=batteryJoltages.charAt(firstDigitIndex+1);
        for(int i =firstDigitIndex+1; i<batteryJoltages.length(); i++){
            if (batteryJoltages.charAt(i)>secondDigit){
                secondDigit=batteryJoltages.charAt(i);
                if (secondDigit=='9'){
                    break; // can't do better than 9
                }
            }
        }
        String bankJoltage=""+firstDigit+secondDigit;
        return Long.parseLong(bankJoltage);
    }
}
