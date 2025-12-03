import java.util.List;
import java.util.Scanner;

public class Day01 {
    public static void Run(List<String> input)  {
        DialMovement[] movements = new DialMovement[input.size()];
        for (int i=0;i<input.size(); i++) {
            movements[i] = new DialMovement(input.get(i));
        }
        //p1(movements);
        p2(movements);
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

    public static void p2(DialMovement[] movements)
    {
        Dial dial = new Dial();
        int zeroCount=0;
        for (DialMovement movement : movements){
            zeroCount += dial.MoveAndCountZeroes(movement);
        }
        System.out.println("01.2: "+zeroCount);
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
    private final int _positionCount=100;
    private final int _maxPosition=_positionCount-1;
    
    public int Move(DialMovement movement){
        int steps = movement.GetSteps() % _positionCount;
        if (movement.GetDirection()=='R'){
            _position += steps;
            if (_position > _maxPosition)
                _position -= _positionCount;
                
        }
        else if (movement.GetDirection()=='L'){
            _position -= steps;
            if (_position < 0)
                _position += _positionCount;
        }
        return _position;
    }
    
    public int MoveAndCountZeroes(DialMovement movement) {
        boolean startedAtZero = (_position == 0);
        // count full revolutions:
        int zeroCount = movement.GetSteps() / _positionCount;
        // leftover steps after full revolutions:
        int steps = movement.GetSteps() % _positionCount;
        if (steps != 0) {
            if (movement.GetDirection() == 'R') {
                _position += steps;
                if (_position > _maxPosition){
                    _position -= _positionCount;
                    if (!startedAtZero)
                        zeroCount++;
                }
                else if (_position == 0){
                    zeroCount++;
                }
            } else if (movement.GetDirection() == 'L') {
                _position -= steps;
                if (_position < 0){
                    _position += _positionCount;
                    if (!startedAtZero)
                        zeroCount++;
                }
                else if (_position == 0){
                    zeroCount++;
                }
            }
        }
        return zeroCount;
    }
    
  }

}

