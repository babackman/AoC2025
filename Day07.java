import java.util.List;

public class Day07 {
    public static void Run(List<String> input)  {
        part1(input);
    }

    private static void part1(List<String> input){
        char[][] map=ReadMap(input);
        int splitCount = 0;
        int rows = map.length;
        int cols = map[0].length;
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                char ch = getMapElement(map,r,c);
                if ((ch=='S') || (ch=='|'))
                {
                    char below = getMapElement(map,r+1, c);
                    if (below == '^'){
                        splitCount++;
                        setMapElement(map, r+1 , c-1, '|');
                        setMapElement(map, r+1 , c+1, '|');
                    }
                    else
                        setMapElement(map, r+1, c, '|');
                }
            }
        }
        System.out.println("07.1: " + Integer.toString(splitCount));
    }

    private static char[][] ReadMap(List<String> input){
        int rows= input.size();
        int cols= input.get(0).length();
        char[][] map = new char[rows][cols];
        for (int r=0; r < rows; r++) {
            map[r] = input.get(r).toCharArray();
        }
        return map;
    }

    private static char getMapElement(char[][] map, int row, int col)
    {
        if ((row < 0) || (row >= map.length) 
            || (col < 0) || (col >= map[0].length)){
                return 'X';
            }
        return map[row][col];
    }

    private static void setMapElement(char[][] map, int row, int col, char newValue)
    {
        if ((row < 0) || (row >= map.length) 
            || (col < 0) || (col >= map[0].length)){
                return;
        }
        map[row][col] = newValue;
    }

}
