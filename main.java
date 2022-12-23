import java.util.*;

public class main {
    public static int[] sumOfDistancesInTree(int n, int[][] edges) {
        if (n == 0) return new int[n];
        if (n == 1) return new int[]{0};
        if (n == 2) return new int[]{1, 1};
        final ArrayList<Integer>[] graph = new ArrayList[n];
        final int[] count = new int[n];
        Arrays.fill(count, 1);
        final int[] answer = new int[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        postOrder(0, -1, graph, count, answer);
        preOrder(0, -1, graph, count, answer, n);
        return answer;
    }

    public static void postOrder(int node, int parrent, ArrayList<Integer>[] graph, int[] count, int[] answer) {
        for (int childNode : graph[node]) {
            if (childNode != parrent) {
                postOrder(childNode, node, graph, count, answer);
                count[node] += count[childNode];
                answer[node] += answer[childNode] + count[childNode];
            }
        }
    }

    public static void preOrder(int node, int parrent, ArrayList<Integer>[] graph, int[] count, int[] answer, int n) {
        for (int childNode : graph[node]) {
            if (childNode != parrent) {
                answer[childNode] = answer[node] + (n - count[childNode]) - count[childNode];
                preOrder(childNode, node, graph, count, answer, n);
            }
        }
    }

    public int nthUglyNumber(int n, int a, int b, int c) {
        long lo = 1, hi = 2 * (int) 1e9;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (count(mid, a, b, c) < n) lo = mid + 1;
            else hi = mid;
        }
        return (int) lo;
    }

    private long count(long x, int a, int b, int c) {
        return (x / a + x / b + x / c - x / lcm(a, b) - x / lcm(b, c) - x / lcm(a, c) + x / lcm(a, (int) lcm(b, c)));
    }

    private long lcm(int a, int b) {
        return (long) a * b / gcd(a, b);
    }

    private long gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if (target > matrix[matrix.length - 1][matrix.length - 1]) return false;
        int leftBound = 0;
        int rightBound = matrix.length - 1;
        int currentMiddle = 0;
        while (leftBound <= rightBound) {
            currentMiddle = leftBound + (rightBound - leftBound) / 2;
            if (matrix[currentMiddle][0] < target) leftBound = currentMiddle + 1;
            else if (matrix[currentMiddle][0] > target) rightBound = currentMiddle - 1;
            else return true;
        }
        System.out.println(currentMiddle);
        for (int i = 0; i < matrix[0].length; i++) {
            System.out.println(matrix[currentMiddle][i]);
            if (matrix[currentMiddle][i] == target) return true;
        }
        return false;
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int numberOfCharFound = 0;
        int lastIndex = 0;
        for (int i = 0; i < s2.length(); i++) {
            if (numberOfCharFound == s1.length()) return true;
            char currentChar = s2.charAt(i);
            if (s1.indexOf(currentChar) != -1) {
                if (numberOfCharFound != 0) {
                    if (Math.abs(s1.indexOf(currentChar) - lastIndex) == 1) {
                        lastIndex = s1.indexOf(currentChar);
                    } else {
                        numberOfCharFound = 0;
                        continue;
                    }
                } else lastIndex = s1.indexOf(currentChar);
                numberOfCharFound++;
            } else numberOfCharFound = 0;
            System.out.println(numberOfCharFound + " " + currentChar);
        }
        return numberOfCharFound == s1.length();
    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        visited[0] = true;
        int numberVisited = 1;
        Deque<Integer> toVisit = new ArrayDeque<>();
        toVisit.push(0);
        while (!toVisit.isEmpty()) {
            for (int room : rooms.get(toVisit.pop())) {
                if (!visited[room]) {
                    toVisit.push(room);
                    visited[room] = true;
                    numberVisited++;
                }
            }
        }
        return rooms.size() == numberVisited;
    }

    public static String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        Stack<Character> currentWord = new Stack<>();
        for (int i = s.length() - 1; i > -1; i--) {
            if (s.charAt(i) != ' ') currentWord.push(s.charAt(i));
            else if (!currentWord.isEmpty()) {
                while (!currentWord.isEmpty()) result.append(currentWord.pop());
                if (i - 1 > -1) result.append(' ');
            }
        }

        if (!currentWord.isEmpty()) while (!currentWord.isEmpty()) result.append(currentWord.pop());
        return result.toString();
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> values = new Stack<>();
        for (String currentToken : tokens) {
            switch (currentToken) {
                case "+" -> values.push(values.pop() + values.pop());
                case "-" -> values.push(-values.pop() + values.pop());
                case "/" -> {
                    int number1 = values.pop();
                    int result = values.pop() / number1;
                    values.push(result);
                }
                case "*" -> values.push(values.pop() * values.pop());
                default -> values.push(Integer.parseInt(currentToken));
            }
        }
        return values.pop();
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numbers = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer currentDifference = target - nums[i];
            Integer possibleFiller = numbers.get(currentDifference);
            if (possibleFiller != null) return new int[]{possibleFiller, i};
            numbers.put(nums[i], i);
        }
        return null;
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int text2Length = text2.length();
        HashMap<Character, Boolean> found = new HashMap<>();
        for (int i = 0; i < text1.length(); i++) {
            char currentChar = text1.charAt(i);
            if (text2.indexOf(currentChar) != -1 && !found.getOrDefault(currentChar, false))
                found.put(currentChar, true);
        }
        return found.size();
    }

    public static int strStr(String haystack, String needle) {
        if (needle.length() == haystack.length()) {
            for (int i = 0; i < haystack.length(); i++) {
                if (needle.charAt(i) != haystack.charAt(i)) return -1;
            }
            return 0;
        }

        ArrayList<Integer> index = new ArrayList<>();

        int needleIndex = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (needleIndex == needle.length() - 1) {
                if (needle.charAt(needleIndex) != haystack.charAt(i) && !index.isEmpty())
                    index.remove(index.size() - 1);
                needleIndex = 0;
                continue;
            }
            if (needleIndex == 0) index.add(i);
            if (needle.charAt(needleIndex) != haystack.charAt(i)) {
                if (needleIndex + 1 != needle.length() && !index.isEmpty()) index.remove(index.size() - 1);
                needleIndex = 0;
            } else {
                System.out.println(needle.charAt(needleIndex));
                needleIndex++;
            }
        }
        return index.isEmpty() ? -1 : index.get(0);
    }


    public static String longestCommonPrefix(String[] strs) {
        String previousPrefix = strs[0];
        Stack<Character> prefix = new Stack<>();
        for (int i = 0; i < previousPrefix.length(); i++) {
            prefix.push(previousPrefix.charAt(i));
            System.out.println(prefix.peek());
        }

        return previousPrefix;
    }

    public static int minFallingPathSum(int[][] matrix) {
        int len = matrix.length;
        if (matrix.length < 3) {

        } else {

        }
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[0].length; k++) {

            }
        }
        return len % 2 == 0 ? 1 : -1;
    }

    public boolean isPowerOfTwo(int n) {

        return Math.sqrt(n) % 2 == 0 || n == 0;
    }

    public boolean isDIgit(char character) {
        switch (character) {
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public static int reverse(int x) {
        if (x == 0) return 0;
        int absX = Math.abs(x);
        StringBuilder digitFormer = new StringBuilder();
        int maxInt = Integer.MAX_VALUE;
        while (absX > 0) {
            digitFormer.append(absX % 10);
            try{
                Integer.parseInt(digitFormer.toString());
            }
            catch (Exception ex){
                return x < 0 ? Integer.MIN_VALUE : maxInt - 1;
            }
            absX = absX / 10;
        }
        int result = Integer.parseInt(digitFormer.toString());
        return x < 0 ? -result : result;
    }

    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        ArrayList<Integer> state0 = new ArrayList<>();
        ArrayList<Integer> state1 = new ArrayList<>();
        ArrayList<Integer> state2 = new ArrayList<>();
        state1.add(-prices[0]);
        state0.add(0);
        state2.add(Integer.MIN_VALUE);
        for (int i = 1; i < prices.length; i++) {
            state0.add(Math.max(state0.get(i - 1), state2.get(i - 1)));
            state1.add(Math.max(state1.get(i - 1), state2.get(i - 1) - prices[i]));
            state2.add(state1.get(i - 1) + prices[i]);
        }
        return Math.max(state0.get(prices.length - 1), state2.get(prices.length - 1));
    }
    public List<String> possibleLetters(int digit){
        List<String> result = new ArrayList<>();
        switch(digit){
            case 2 ->{
                result.add("a"); result.add("b"); result.add("c");
                return result;
            }
            case 3 ->{
                result.add("a"); result.add("b"); result.add("c");
                return result;
            }
            case 4 ->{
                result.add("a"); result.add("b"); result.add("c");
                return result;
            }
            case 5 ->{
                result.add("a"); result.add("b"); result.add("c");
                return result;
            }
            case 6 ->{
                result.add("a"); result.add("b"); result.add("c");
                return result;
            }
            case 7 ->{
                result.add("a"); result.add("b"); result.add("c");
                return result;
            }

        }
    }

    public static void main(String[] args) {
        System.out.println(reverse(0));
        System.out.println(Integer.MIN_VALUE);
    }
}
