// Advent of Code 2023
// https://adventofcode.com/2023

// Imports for file processing and arrays
import java.nio.file.*;
import java.util.*;

class Main {

  public static void main(String[] args) throws java.io.IOException {

    List<String> lines = Files.readAllLines(Paths.get("data","01_data.txt"));
    Day01.Run(lines); 
  }

}