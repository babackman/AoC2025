import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day05 {
    public static void Run(List<String> input) {
        IngredientDb db = new IngredientDb(input);
        part1(db);
        part2(db);
    }
    
    private static void part1(IngredientDb db) {
        long freshCount = 0;
        for (long ingredient : db.ingredients) {
            
            // check if the ingredient is in any of the ranges
            for (int rangeIndex = 0; rangeIndex < db.freshRanges.size(); rangeIndex++) {
                IdRange range = db.freshRanges.get(rangeIndex);
                if (Long.compareUnsigned(ingredient, range.min) < 0) {
                    // ingredient is less than the minimum of this range, so it can't be in any
                    // further ranges
                    break;
                }
                if (Long.compareUnsigned(ingredient, range.max) <= 0) {
                    // ingredient is in this range
                    freshCount++;
                    break;
                }
            }
        }

        System.out.println("5.1: fresh count: " + freshCount);
    }

    private static void part2(IngredientDb db) {
        ArrayList<IdRange> uniqueRanges = new ArrayList<IdRange>();
        for (int i=0; i < db.freshRanges.size(); i++) {
            ArrayList<IdRange> newRanges = new ArrayList<IdRange>();
            newRanges.add( db.freshRanges.get(i));
            while(!newRanges.isEmpty()){
                IdRange candidate = newRanges.remove(0);
                boolean isUnique = true;
                for(IdRange existing : uniqueRanges){
                    if (candidate.overlaps(existing)){
                        isUnique = false;
                        // split candidate into non-overlapping parts
                        if (Long.compareUnsigned(candidate.min, existing.min) < 0){
                            newRanges.add( new IdRange(candidate.min + "-" + (existing.min - 1)));
                        }
                        if (Long.compareUnsigned(candidate.max, existing.max) > 0){
                            newRanges.add( new IdRange((existing.max + 1) + "-" + candidate.max));
                        }
                        break;
                    }
                }
                if (isUnique) {
                    uniqueRanges.add(candidate);
                }
            }
        }  

        long freshCount = 0;
        for(IdRange range : uniqueRanges){
            freshCount += range.count();
        }

        System.out.println("5.2: fresh count: " + Long.toUnsignedString(freshCount));
    }
    private static class IngredientDb {
        public List<IdRange> freshRanges;
        public long[] ingredients;
        
        public IngredientDb(List<String> input) {
            super();
            int lineIndex;
            String line;

            // read ranges
            freshRanges = new ArrayList<IdRange>();
            for (lineIndex = 0; lineIndex < input.size(); lineIndex++) {
                line = input.get(lineIndex);
                if (line.isBlank()) 
                    break;
                freshRanges.add(new IdRange(line));
            }
            // sort by minimums to make searching easier
            freshRanges.sort((a,b) -> Long.compare(a.min, b.min));
            
            // skip the blank line
            lineIndex++;
            
            // read ingredients
            ingredients = new long[input.size() - lineIndex];
            for (int i = 0; lineIndex < input.size(); lineIndex++, i++) {
                line = input.get(lineIndex);
                ingredients[i] = Long.parseLong(line);
            }
            Arrays.sort(ingredients);
        }
    }
    
    private static class IdRange{
        public long min;
        public long max;
        public IdRange(String rangeString) {
            super();
            String[] tokens = rangeString.split("-");
            min=Long.parseLong(tokens[0]);
            max=Long.parseLong(tokens[1]);
        } 

        public boolean overlaps(IdRange other){
            return Long.compareUnsigned(this.min, other.max) <= 0 &&
                   Long.compareUnsigned(other.min, this.max) <= 0;
        }
        
        public long count(){
            return (max - min + 1);
        }
    }
}
