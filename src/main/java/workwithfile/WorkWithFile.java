package workwithfile;

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

    private final static String fileSource = ("src/main/resources/data.txt");
    private final static String badWordsSource = ("src/main/resources/badwords.txt");
    private final static int MINIMAL_LETTERS_NUMBER = 3;
    Scanner sc = new Scanner(new FileInputStream(new File(fileSource)));
    Scanner sc1 = new Scanner(new FileInputStream(new File(fileSource)));
    ArrayList<String> shortWords = new ArrayList<>();
    private int count = 0;
    private int countMoreThanThree = 0;

    public WorkWithFile() throws FileNotFoundException {
    }

    public void wordCounter() {
        while (sc.hasNext()) {
            String[] s = sc.next().split("d*[.@:=#-]");
            for (String item : s) {
                if (!item.isEmpty()) {
                    count++;
                }
            }
        }
        System.out.println("Sum of words in the file : " + count);
    }

    public void smallWordsCounter() {
        while (sc1.hasNext()) {
            String[] s1 = sc1.next().split("d*[.@:=#-]");
            for (String value : s1) {
                if (value.length() > MINIMAL_LETTERS_NUMBER) {
                    countMoreThanThree++;
                } else {
                    shortWords.add(value);
                }
            }
        }
        System.out.println("Sum of words which have more than 3 char : " + countMoreThanThree);
        System.out.println("List of small words:" + "\n" + shortWords);
    }

    public void frequentWordsCounter() throws IOException {
        System.out.println("Three most used word: ");
        Arrays.stream(Files.readString(Path.of(fileSource)).split("\\W+"))
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, counting()))
                .entrySet()
                .stream()
                .sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue())))
                .limit(3)
                .forEach(System.out::println);
    }

    public void badWordsRemover() throws IOException {
        while (sc.hasNext()) {
            String[] s = sc.next().split("d*[.@:=#-]");
            for (String value : s) {
                if (value.toLowerCase().contains("burn")) {
                    String badWordsFileSource = badWordsSource;
                    try (PrintStream out = new PrintStream(new FileOutputStream(badWordsFileSource))) {
                        out.print(value);
                    }
                }
            }
        }
        System.out.println("List of bad words: " + new String(Files.readAllBytes(Paths.get(badWordsSource))));
    }
}