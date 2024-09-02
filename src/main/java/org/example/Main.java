package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        //challenge1();
        challenge2();
    }

    private static void challenge2(){
        try {
            Stream<String> lines = Files.lines(Path.of("students.csv"));
            lines
                    //remove invalid lines
                    .filter(line -> !line.isEmpty() && Character.isDigit(line.charAt(0)))
                    //split comma separated values into String array
                    .map(line -> line.split(","))
                    //remove lines with array length other than 4
                    .filter(array -> array.length == 4)
                    //map array to Student object
                    .map(array -> new Student(Integer.parseInt(array[0]), array[1],array[2], Integer.parseInt(array[3])))
                    // remove duplicate values
                    .distinct()
                    /* creating a map failed with duplicate values
                    .collect(Collectors.toMap( Student::id, student-> student))
                     */

                    .forEach(System.out::println);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void challenge1() {
        List<Integer> numbers = List.of(3, 7, 8, 43, 56, 90, 104, 99, 45, 16);
        System.out.println(numbers);
        System.out.println("This was the original list ---------------------------------------------");
        numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
        System.out.println("These were the even elements -------------------------------");
        numbers.stream().map(n -> n * 2).forEach(System.out::println);
        System.out.println("These were alle elements doubled ---------------------------------");
        numbers.stream().sorted().forEach(System.out::println);
        //numbers.stream().sorted((a,b) -> b-a).forEach(System.out::println);  //reverse order
        System.out.println("These were the elements sorted -------------------------------");
        System.out.println("Sum is: " + numbers.stream().reduce(0, (a, b) -> a + b));
        System.out.println("------------------------------");
        List<Integer> collectedList = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println(collectedList);
    }

}