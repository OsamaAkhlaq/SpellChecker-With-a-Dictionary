package spellcheck;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

public class FileReading {
  HashMap<String,String> hmap2 = new HashMap<>();
  CustomHashmap <String, String> customhash = new CustomHashmap <String, String> ();
  StringTokenizer st1;
  Soundex soundex= new Soundex();
  
  public void ReadWordsWithMeanings() throws FileNotFoundException
    {  
     Scanner s = new Scanner(new File("Dictionary.txt"));
     String word,meaning;
     while(s.hasNext())
     {
         word = toLowerCase(s.next());
         meaning = s.nextLine();
         customhash.put(word,meaning);
     }
    
    }
       
       public void ReadWordsWithCode() throws FileNotFoundException
    {  
     Scanner sc = new Scanner(new File("Dictionary.txt"));
     String word,meaning;
     while(sc.hasNext())
     {
         word = toLowerCase(sc.next());
         meaning = sc.nextLine();
         String code = soundex.getCode(word);
         hmap2.put(code, word);
     }
    
    }
  public void Comparator(String code)
  {
    int i=0;
    String array[] = new String[10];
    int y=0;
    Set set2 = hmap2.entrySet();
    Iterator iterator2 = set2.iterator();
    while(iterator2.hasNext())
    {
         Map.Entry mentry = (Map.Entry)iterator2.next();
         String a = (String)mentry.getKey();
         if(code.substring(0,3).equals(a.substring(0,3)))
         {
            array[i]=(String) mentry.getValue();
            i++;
            y++;
         }
         
    }
    if(array[0] == null)
    {
        System.out.println("No recommended searches!");
        System.out.println("(Due to limited size of sample dictionary, enter abak to ensure correct working)");
        
    }
    else
    {
        for(int z = 0; z < y ; z++)
         {
             System.out.println(""+array[z]);
         }
    }
  }

  
       public String Meaning(String word)
       {
         return customhash.get(word);
           
       }
}


