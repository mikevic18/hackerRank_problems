import java.util.ArrayList;
import java.util.HashMap;

public class test2 {
    public static boolean wordPattern(String pattern, String s) {
        int stringLength = s.length();
        int patternLength = pattern.length();
        if (patternLength < 1 && stringLength < 1) return false;
        if (patternLength > stringLength) return false;
        if (stringLength == 1 && s.charAt(0) != pattern.charAt(0)) return false;
        String[] list = s.split(" ");
        HashMap<Character,String> linker = new HashMap<>();
        int patternIndex = 0;
        int nextPattern = 0;
        for (String value : list) {
            if (linker.getOrDefault(pattern.charAt(patternIndex), "-1").equals("-1")&&!linker.containsValue(value)) {
                linker.put(pattern.charAt(patternIndex),value);
                patternIndex++;
            }
            if (linker.containsValue(value)||!(linker.get(pattern.charAt(nextPattern)).equals(value))) return false;
            System.out.println(linker);
            nextPattern++;
            if (nextPattern > patternLength - 1) nextPattern = 0;
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog dog dog dog";
        System.out.println(wordPattern(pattern, s));

    }
}

