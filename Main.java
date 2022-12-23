import java.util.*;
import java.lang.*;
import java.io.*;


public class Main {
    public static int  isSameReflection(String word1, String word2)
    {
        int  answer = -1;
        if (word1 == null || word2 == null) return answer;
        if (word1.equals(word2)) return answer;
        int length = word1.length();
        if (length != word2.length() || length==1) return answer;
        int counter=0;

        for(int i = 0; i <length; i++){
            if(word1.charAt(0)==word2.charAt(i)){
                if(word2.substring(0, counter).equals(word1.substring(length - counter, length))&&word2.substring(counter,length).equals(word1.substring(0,length-counter))){
                        return 1;
                }
                break;
            }
            counter++;
        }

        return -1;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        // input for word1
        String word1 = "ertyuqw";

        // input for word2
        String word2 = "qwertyu";

        int result = isSameReflection(word1, word2);
        System.out.print(result);

    }
}