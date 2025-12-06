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
        long freshCount = 0;
        for (int i=0; i < db.freshRanges.size(); i++) {
            IdRange range = db.freshRanges.get(i);
            if (i==0) {
                freshCount = range.max - range.min + 1;
                continue;
            }
            for (long ingredient=range.min; ingredient <= range.max; ingredient++) {
                // check if the ingredient is in any of the previous ranges
                boolean inPreviousRange = false;
                for (int prevIndex = 0; prevIndex < i; prevIndex++) {
                    IdRange prevRange = db.freshRanges.get(prevIndex);
                    if (Long.compareUnsigned(ingredient, prevRange.min) < 0) {
                        // ingredient is less than the minimum of this range, so it can't be in any
                        // further ranges
                        break;
                    }
                    if (Long.compareUnsigned(ingredient, prevRange.max) <= 0) {
                        // ingredient is in this range
                        inPreviousRange = true;
                        break;
                    }
                }
                if (!inPreviousRange) {
                    freshCount++;
                }
            }
        }  
        System.out.println("5.2: fresh count: " + freshCount);
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
    }
}
