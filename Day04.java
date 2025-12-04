import java.util.List;

public class Day04 {
    private char[][] _rollMap;
    private int _rows;
    private int _cols;
        
        public Day04(List<String> rollMap) {
            super();
            _rows= rollMap.size();
            _cols= rollMap.get(0).length();
            _rollMap = new char[_rows][_cols];
            for (int r=0; r<_rows; r++) {
                _rollMap[r] = rollMap.get(r).toCharArray();
            }
        }
    
        public static void Run(List<String> input)  {
            Day04 day04 = new Day04(input);
            day04.part2();
        }
        
        void part1(){
            int accessibleCount =0;
            for (int r=0; r<_rows; r++) {
                for (int c=0; c<_cols; c++) {
                    if (IsAccessibleRoll(r,c)) {
                        accessibleCount++;
                    }
                }
            }
            System.out.println("04.1: "+Integer.toString(accessibleCount));
        }
        
        void part2() {
            int totalRemoved = 0;
            boolean removedSomething = true;
            do {
                removedSomething = false;
                for (int r = 0; r < _rows; r++) {
                    for (int c = 0; c < _cols; c++) {
                        if (IsAccessibleRoll(r, c)) {
                            totalRemoved++;
                            removedSomething = true;
                            RemoveRoll(r, c);
                        }
                    }
                }
            } while (removedSomething);
            System.out.println("04.2: " + Integer.toString(totalRemoved));
        }
        
        boolean SpaceIsOccupied(int row, int col) {
            if (row<0 || row>=_rows || col<0 || col>=_cols) {
                return false;
            }
            return _rollMap[row][col]=='@';
        }
        void  RemoveRoll(int row, int col){
            if (row<0 || row>=_rows || col<0 || col>=_cols) {
                return;
            }
            _rollMap[row][col] = '.';
        }
        
        int CountAdjacentRolls(int row, int col){
            int adjacentRolls=0;
            for (int r=row-1; r<=row+1; r++) {
                for (int c=col-1; c<=col+1; c++) {
                    if (r==row && c==col) {
                        continue;
                    }
                    if (SpaceIsOccupied(r,c)) {
                        adjacentRolls++;
                    }
                }
            }
            return adjacentRolls;
        }
        
        boolean IsAccessibleRoll(int row, int col) {
            if (!SpaceIsOccupied(row,col)) {
                return false;
            }
            int adjacentRolls = CountAdjacentRolls(row,col);
            return (adjacentRolls<4);
        }   
}
