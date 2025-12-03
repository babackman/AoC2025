import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 {
    public static void Run(List<String> input)  {
        List<IdRange> ranges = ReadRanges(input.get(0));
        part1(ranges);
    }
    
    public static void part1(List<IdRange> ranges)
    {
        Long invalidIdSum = 0L;
        for (IdRange range : ranges){
            invalidIdSum += SumInvalidIdsInRange(range);
        }
        System.out.println("02.1: "+Long.toUnsignedString(invalidIdSum));
    }
    
    public static List<IdRange> ReadRanges(String input){
        String[] rangeStrings=input.split(",");
        ArrayList<IdRange> idRanges = new ArrayList<IdRange>(rangeStrings.length);
        for(String rangeString:rangeStrings){
            idRanges.add(new IdRange(rangeString));
        }
        return idRanges;
    }
    
    /**
     * Counts the number of invalid IDs in the given range.
     * @param range
     * @return the sum of invalid IDs in the range
     * @remarks An ID is invalid if it is a sequence repeated twice
     */
    public static Long SumInvalidIdsInRange(IdRange range){
        Long invalidIdSum = 0L;
        String minString = Long.toString(range._min);
        int minLength = minString.length();
        int maxLength = Long.toString(range._max).length();
        for (int idLength = minLength; idLength <= maxLength; idLength++){
            if (idLength %2 == 1){
                continue;  // odd lengths can't be invalid
            }
            int halfLength = idLength / 2;
            char[] repeatedPattern= new char[halfLength];
            if (idLength == minLength){
                // digits of repeated pattern have to be at least as large
                //  as leading digits of the minimum, or the resulting ID
                //  will be less than the minimum
                for (int i=0; i<halfLength; i++){
                    repeatedPattern[i]=minString.charAt(i);
                }
            }
            else{
                // start at all zeroes except 1st digit
                Arrays.fill(repeatedPattern,'0');
                repeatedPattern[0]='1';
            }
            Boolean done=false;
            while(!done){
                String candidateIdStr = new String(repeatedPattern) + new String(repeatedPattern);
                long candidateId = Long.parseLong(candidateIdStr);
                if (candidateId > range._max)
                    break;
                if (candidateId >= range._min)
                    invalidIdSum += candidateId;
                
                // increment the repeated pattern:
                int pos = halfLength -1;
                while (pos >=0){
                    if (repeatedPattern[pos] < '9'){
                        repeatedPattern[pos]++;
                        break;
                    }
                    else{
                        // don't roll over the first digit
                        if (pos==0){
                            done=true;
                            break;
                        }
                        repeatedPattern[pos]='0';
                        pos--;
                    }
                }
            }
        }
        
        
        return invalidIdSum;
    }

    static class IdRange{
        public long _min;
        public long _max;

        public IdRange(String rangeStr){
            String[] parts = rangeStr.split("-");
            this._min = Long.parseLong(parts[0]);
            this._max = Long.parseLong(parts[1]);
        }
    }
}
