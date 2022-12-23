import java.util.Stack;

public class test5 {
    public static boolean backspaceCompare(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return false;
        int sLength = s.length();
        int tLength = t.length();
        int sIndex = 0;
        int tIndex = 0;
        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();
        while (true) {
            if (sIndex < sLength) {
                if (s.charAt(sIndex) != '#') {
                    s1.push(s.charAt(sIndex));
                } else if (!s1.empty()) {
                    s1.pop();
                }
            }
            if (tIndex < tLength) {
                if (t.charAt(tIndex) != '#') {
                    s2.push(t.charAt(tIndex));
                } else if (!s2.empty()) {
                    s2.pop();
                }
            }
            if (sIndex >= sLength && tIndex >= tLength) break;
            sIndex++;
            tIndex++;
        }
        System.out.println(s1.size()+" "+s2.size());
        System.out.println(s2);
        if (s1.size() != s2.size()) return false;
        for (int i = 0; i < s1.size(); i++) {
            if (s1.get(i) != s2.get(i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "ab##";
        String t = "c#d#";
        System.out.println(backspaceCompare(s, t));
    }
}
