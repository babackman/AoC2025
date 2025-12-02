import java.util.List;
import java.util.Scanner;

public class Day01 {
    public static void Run(List<String> input)  {
        DialMovement[] movements = new DialMovement[input.size()];
        for (int i=0;i<input.size(); i++) {
            movements[i] = new DialMovement(input.get(i));
        }
        p1(movements);
        //p2(input);
    }

    public static void p1(DialMovement[] movements)
    {
        Dial dial = new Dial();
        int zeroCount=0;
        for (DialMovement movement : movements){
            int newPosition = dial.Move(movement);
            if (newPosition == 0)
                zeroCount++;
        }
        System.out.println("01.1: "+zeroCount);
    }

    
private static class DialMovement {
    public char _direction;
    public int _steps;

    public DialMovement(String movement){
        
        this._direction = movement.charAt(0);
        this._steps = Integer.parseInt(movement.substring(1));
    }
    
    public char GetDirection(){
        return this._direction;
    }
    public int GetSteps(){
        return this._steps;
    }   
}

private static class Dial{
    private int _position=50;
    private int _maxPosition=99;
    
    public int Move(DialMovement movement){
        int steps = movement.GetSteps() % (_maxPosition+1);
        if (movement.GetDirection()=='R'){
            _position += steps;
            if (_position > _maxPosition)
                _position -= (_maxPosition+1);
        }
        else if (movement.GetDirection()=='L'){
            _position -= steps;
            if (_position < 0){
                _position += (_maxPosition+1);
        }
        }
        return _position;
    }
    
}

}

