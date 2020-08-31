package com.yurchyk.workwithfile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class WorkWithFile {

    private static final String FILE_SOURCE = ("src/main/resources/data.txt");
    private static final String BAD_WORDS_SOURCE = ("src/main/resources/badwords.txt");
    private static final int MINIMAL_LETTERS_NUMBER = 3;
    private static final String SYMBOLS = "d*[.@:=#-]";
    private static final String BAD_WORDS = "burn";
    final Scanner sc = new Scanner(new FileInputStream(new File(FILE_SOURCE)));

    ArrayList<String> shortWords = new ArrayList<>();
    private int count = 0;
    private int countMoreThanThree = 0;

    public WorkWithFile() throws FileNotFoundException {
    }

    public void wordCounter() {
        while (sc.hasNext()) {
            String[] firstString = sc.next().split(SYMBOLS);
            for (String item : firstString) {
                if (!item.isEmpty()) {
                    count++;
                }
            }
            String[] secondString = sc.next().split("SYMBOLS");
            for (String value : secondString) {
                if (value.length() > MINIMAL_LETTERS_NUMBER) {
                    countMoreThanThree++;
                } else {
                    shortWords.add(value);
                }
            }
        }
        System.out.println("Sum of words in the file : " + count);
        System.out.println("Sum of words which have more than 3 char : " + countMoreThanThree);
        System.out.println("List of small words:" + "\n" + shortWords);
    }

    public void frequentWordsCounter() throws IOException {
        System.out.println("Three most used word: ");
        Arrays.stream(Files.readString(Path.of(FILE_SOURCE)).split("\\W+"))
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, counting()))
                .entrySet()
                .stream()
                .sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue())))
                .limit(3)
                .forEach(System.out::println);
    }

    public void badWordsRemover() throws IOException {
        while (sc.hasNext()) {
            String[] s = sc.next().split(SYMBOLS);
            for (String value : s) {
                if (value.toLowerCase().contains(BAD_WORDS)) {
                    try (PrintStream out = new PrintStream(new FileOutputStream(BAD_WORDS_SOURCE))) {
                        out.print(value);
                    }
                }
            }
        }
        System.out.println("List of bad words: " + new String(Files.readAllBytes(Paths.get(BAD_WORDS_SOURCE))));
    }
}