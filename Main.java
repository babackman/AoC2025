// Advent of Code 2025
// https://adventofcode.com/2025

// Imports for file processing and arrays
import java.nio.file.*;
import java.util.*;

class Main {

  public static void main(String[] args) throws java.io.IOException {

    List<String> lines = Files.readAllLines(Paths.get("data","07_data.txt"));
    Day07.Run(lines); 
  }

}