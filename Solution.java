import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/*
 *
 */
class Master{
    public Master(){

    }
    public int guess(String word){
        return 1;
    }
}
public class Solution {
    public void findSecretWord(String[] words, Master master) {
        HashMap<Character, ArrayList<String>> map = new HashMap<>();
        HashSet<String> leftOver = new HashSet<>();


        int size = words.length;
        for (String word : words) {
            char[] wordChars = word.toCharArray();
            for (char character:wordChars) {
                ArrayList<String> wordLinked = map.getOrDefault(character, new ArrayList<>());
                wordLinked.add(word);
                map.put(character, wordLinked);
            }
        }
        for(String word : words){
            int queryResult = master.guess(word);
            if(queryResult == word.length()) break;
            if(queryResult == 0){
                char[] wordChars = word.toCharArray();
                for(char character:wordChars){
                    map.remove(character);
                }


            }
        }
        for(int i = 0; i < size; i++){
            int queryResult = master.guess(words[i]);
            if (queryResult == words[i].length()) break;
            if(queryResult == 0){

            }
        }
    }


    public static int minLength(int[] systemState, int[] dist) {
        int answer = 0;
        HashMap<Integer, Integer> state = new HashMap<>();
        for (int i = 0; i < systemState.length; i++) {
            if (systemState[i] == 1) {
                state.put(i, systemState[i]);
            }
        }


        return answer;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //input for systemState
        int systemState_size = in.nextInt();
        int systemState[] = new int[systemState_size];
        for (int idx = 0; idx < systemState_size; idx++) {
            systemState[idx] = in.nextInt();
        }
        //input for dist
        int dist_size = in.nextInt();
        int dist[] = new int[dist_size];
        for (int idx = 0; idx < dist_size; idx++) {
            dist[idx] = in.nextInt();
        }

        int result = minLength(systemState, dist);
        System.out.print(result);

    }
}
