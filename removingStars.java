import java.util.Stack;

public class removingStars {
    public static String removeStars(String s){
        if(s.length() <1 ) return "";
        if(s.contains("*")) System.out.println("IT WORKS");
        Stack<Character> processed = new Stack<>();
        for(int i = 0; i < s.length(); i ++){
            if(s.charAt(i) == '*' && !processed.empty()) processed.pop();
            else processed.push(s.charAt(i));
        }
        StringBuilder result = new StringBuilder();
        for (Character character : processed) {
            result.append(character);
        }
        return result.toString();
    }
    public static void main(String[] args){
        System.out.println(removeStars("leet**cod*e"));
    }
}
