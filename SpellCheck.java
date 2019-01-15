package spellcheck;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class SpellCheck {

    public static void main(String[] args) throws FileNotFoundException {
        Soundex soundex = new Soundex();
        FileReading fr = new FileReading();
        Scanner scanner = new Scanner(System.in);
        fr.ReadWordsWithMeanings();
        fr.ReadWordsWithCode();
        System.out.println("Welcome to our auto-correcting program!");
        System.out.println();
        System.out.println("The program will require you to enter a word.\nIf the word is not found in our sample dictionary,\nit will give the user a list of recommended searches");
        System.out.println();
        System.out.println("Please enter a word of your choice: ");
        String input = scanner.nextLine();
        String code = soundex.getCode(input);
        do{
            if(fr.customhash.get(input) != null){
                System.out.println("Meaning: "+fr.Meaning(input));
            }
            else{
                System.out.println("Word not Found!");
                System.out.println("Did you mean: ");
                fr.Comparator(code);
            }
            System.out.println();
            System.out.println("Enter a correct word: ");
            input = scanner.nextLine();
            System.out.println("Meaning: "+fr.Meaning(input));
            code = soundex.getCode(input);
            System.out.println();
        }
        while(fr.customhash.get(input) == null);
}
}
