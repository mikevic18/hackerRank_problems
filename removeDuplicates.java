import java.util.*;

class MedianFinder {


    PriorityQueue<Integer> low = new PriorityQueue<>();
    PriorityQueue<Integer> high = new PriorityQueue<>();
    ArrayList<Integer> integerArrayList = new ArrayList<>();
    public MedianFinder() {
    }

    public void addNum(int num) {
        integerArrayList.add(num);
    }

    public double findMedian() {
        int size = integerArrayList.size();
        return size % 2 == 0 ? (double) (integerArrayList.get(size / 2) + integerArrayList.get((size - 1) / 2)) / 2 :
                (double) integerArrayList.get((size - 1) / 2) / 2;
    }
}


public class removeDuplicates {


    public static boolean isUpperCase(Character character){
        switch (character) {
            case 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                    'U', 'V', 'W', 'X', 'Y', 'Z' -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }
    public static int passwordChecker(String password){
        if(password.length() < 6) return 6 - password.length();
        int upperCaseCount = 0;
        int stepsNecessary = 0;
        int digitCounter = 0;
        int currentCount = 1;
        int maxCount = 0;
        int timesMaxCountReached = 0;
        for(int i = 0; i < password.length(); i++){
            char currentCharacter = password.charAt(i);
            if(isUpperCase(currentCharacter)) upperCaseCount++;
            if(isDigit(currentCharacter)) digitCounter++;
            if(i + 1 <password.length() && currentCharacter == password.charAt(i+1))currentCount++;
            else{
                if(currentCount >= 3) timesMaxCountReached++;
                if(currentCount > maxCount) maxCount =currentCount;
                currentCount=1;
            }
        }
        if(timesMaxCountReached >= 2) stepsNecessary++;
        if(timesMaxCountReached == 1 && digitCounter < 1) stepsNecessary++;
        if(upperCaseCount < 1)stepsNecessary++;
        if(digitCounter < 1)stepsNecessary++;
        return stepsNecessary;
    }
    public static int longestValidParentheses(String s) {
        if (s.length() < 2) return 0;
        int currentCount = 0;
        int maxCount = 0;
        Stack<Character> parentheses = new Stack<>();
        for(char element: s.toCharArray()){
            switch(element){
                case '(' -> parentheses.push(')');
                case '{' -> parentheses.push('}');
                case '[' -> parentheses.push(']');
                case ')','}',']' -> {
                    if (parentheses.isEmpty() || parentheses.pop() != element){
                        parentheses.clear();
                        if(currentCount > maxCount) maxCount = currentCount;
                        currentCount = 0;
                    }
                    else if(parentheses.empty()) currentCount += 2;
                }
            }
        }
        return Math.max(maxCount, currentCount);
    }
    public static boolean isValid(String s) {
        if (s.length() < 2 && s.length() % 2 != 0) return false;
        Stack<Character> parentheses = new Stack<>();
        for(char element: s.toCharArray()){
            switch(element){
                case '(' -> parentheses.push(')');
                case '{' -> parentheses.push('}');
                case '[' -> parentheses.push(']');
                case ')','}',']' -> {if (parentheses.isEmpty() || parentheses.pop() != element) return false;}
            }
        }
        return parentheses.isEmpty();
    }

    public static boolean isSign(Character character) {
        return switch (character) {
            case '-', '+' -> true;
            default -> false;
        };
    }

    public static boolean isDigit(Character character) {
        return switch (character) {
            case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> true;
            default -> false;
        };
    }

    public static int handleSign(Character character, int number1, int number2) {
        return switch (character) {
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            default -> 0;
        };
    }

    public static int recursivePart(int startingPoint, String s, int stringLength, int currentResult) {
        Stack<Character> currentSignBuffer = new Stack<>();
        int mainResult = 0;
        for (int i = startingPoint; i < stringLength; i++) {
            char currentCharacter = s.charAt(i);
            if (isSign(currentCharacter)) currentSignBuffer.push(currentCharacter);

            if (currentCharacter == '(') mainResult = handleSign(currentSignBuffer.pop(), mainResult, recursivePart(
                    i + 1, s, stringLength, currentResult));
            if (currentCharacter == ')') return currentResult;

            if (isDigit(currentCharacter)) {
                String tempIntegerBuilder = "";
                int currentIndex = i;
                while (isDigit(s.charAt(i))) {
                    tempIntegerBuilder += s.charAt(i);
                    i++;
                }
                i--;
                if (currentIndex == 0) {
                    mainResult += Integer.parseInt(tempIntegerBuilder);
                    continue;
                }
                if (!currentSignBuffer.empty()) currentResult = handleSign(currentSignBuffer.pop(), currentResult,
                        Integer.parseInt(tempIntegerBuilder));
                else currentResult = Integer.parseInt(tempIntegerBuilder);
            }
        }
        return mainResult;
    }

    public static int calculate(String s) {
        int stringLength = s.length();
        return recursivePart(0, s, stringLength, 0);
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        HashMap<Character, Integer> characterFrequency = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];
            for (int k = 0; k < currentWord.length(); i++) {
                characterFrequency.put(currentWord.charAt(k), characterFrequency.getOrDefault(currentWord.charAt(k),
                        0) + 1);
            }
        }
        int stringLength = s.length();
        HashMap<Character, Integer> currentCharacterFrequency = new HashMap<>();
        for (int i = 0; i < stringLength; i++) {
            char currentCharacter = s.charAt(i);
            int currentCharacterFreq = currentCharacterFrequency.getOrDefault(currentCharacter, 0);
            currentCharacterFrequency.put(currentCharacter, currentCharacterFreq);
            if (!characterFrequency.containsKey(currentCharacter) || characterFrequency.get(currentCharacter) < currentCharacterFreq) {
                currentCharacterFrequency.clear();
            }
            currentCharacterFrequency.put(currentCharacter, currentCharacterFreq);
        }
        System.out.println(characterFrequency);
        return result;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int nums1Length = nums1.length;
        int nums2Length = nums2.length;
        double indexOfTarget = (double) (nums1Length + nums2Length + 1) / 2;
        System.out.println(indexOfTarget);
        int[] targetIndexes = new int[2];
        int numberOfTargets;
        double result = 0;
        if (indexOfTarget % 1 == 0) {
            numberOfTargets = 1;
            targetIndexes[0] = (int) indexOfTarget - 1;

        } else {
            numberOfTargets = 2;
            targetIndexes[0] = (int) Math.floor(indexOfTarget) - 1;
            targetIndexes[1] = (int) Math.round(indexOfTarget) - 1;
        }
        System.out.println(targetIndexes[0] + " " + targetIndexes[1]);
        int currentTargetIndex = 0;
        int currentNum1Index = 0;
        int currentNum2Index = 0;
        ArrayList<Integer> processedList = new ArrayList<>();
        while (true) {
            if (currentNum1Index < nums1Length && currentNum2Index < nums2Length) {
                if (nums1[currentNum1Index] > nums2[currentNum2Index]) {
                    processedList.add(nums2[currentNum2Index]);
                    currentNum2Index++;

                } else {
                    processedList.add(nums1[currentNum1Index]);
                    currentNum1Index++;
                }
            } else if (currentNum1Index < nums1Length) {
                processedList.add(nums1[currentNum1Index]);
                currentNum1Index++;

            } else if (currentNum2Index < nums2Length) {
                processedList.add(nums2[currentNum2Index]);
                currentNum2Index++;
            }
            System.out.println(processedList);
            if (currentNum2Index + currentNum1Index - 1 == targetIndexes[currentTargetIndex]) {
                targetIndexes[currentTargetIndex] = processedList.get(processedList.size() - 1);
                currentTargetIndex++;
                if (currentTargetIndex >= numberOfTargets) {
                    if (numberOfTargets == 1) return targetIndexes[0];
                    result = (double) (targetIndexes[0] + targetIndexes[1]) / 2;
                    return result;
                }

            }
        }
    }

    public int wasd(String s) {
        int stringLength = s.length();
        if (stringLength == 0) return 0;
        if (stringLength == 1) return 1;
        int max = 0;
        int start = 0;
        int currentSize = 0;
        ArrayList<Character> currentBuffer = new ArrayList<>();
        for (int i = 0; i < stringLength; i++) {
            currentBuffer.clear();
            char currentCharacter = 'a';
            if (currentBuffer.contains(currentCharacter) && start != 0) {
                if (currentSize > max) max = currentSize;
                start = 0;
            } else {
                currentBuffer.add(currentCharacter);
            }
        }
        return max;
    }

    public static List<List<String>> displayTable(List<List<String>> orders) {
        if (orders == null) return null;
        HashSet<String> firstLine = new HashSet<>();
        firstLine.clear();
        ArrayList<Character> a = new ArrayList<>();
        HashMap<Integer, HashMap<String, Integer>> result = new HashMap<>();
        for (int i = 0; i < orders.size(); i++) {
            List<String> temp = orders.get(i);
            firstLine.add(temp.get(2));
            HashMap<String, Integer> temp2 = new HashMap<>();
            temp2.put(temp.get(2), Integer.valueOf(temp.get(1)));
            result.put(i, temp2);
        }
        System.out.println(result);
        return null;
    }

    public void swap(String a, int k) {
//        if(a.si)
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums2 == null) {
            int[] result = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) result[i] = -1;
            return result;
        }
        HashMap<Integer, Integer> processed = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            if (i + 1 <= nums2.length) processed.put(nums2[i], nums2[i + 1]);
            else processed.put(nums2[i], -1);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            if (processed.get(nums1[i]) > nums1[i]) result[i] = processed.get(nums1[i]);
            else result[i] = -1;
        }
        return result;
    }

    public static Stack<Character> pushOnStack(Stack<Character> s, char element, int times) {
        for (int i = 0; i <= times; i++) s.push(element);
        return s;
    }

    public static String removeDupesv2(String s, int k) {
        if (s.length() < 1 || k == 0) return s;
        Stack<Character> processed = new Stack<>();
        int charCounter = 0;
        int i = 0;
        while (true) {
            if (i >= s.length()) {
                break;
            }
            if (i >= 1 && s.charAt(i - 1) == s.charAt(i)) charCounter++;
            if (i >= 1 && s.charAt(i - 1) != s.charAt(i))
                if (charCounter >= k) pushOnStack(processed, s.charAt(i), charCounter % k);
            i++;
        }
        return "";
    }

    public static String removeDupes(String s) {
        s.contains("#");
        if (s.length() < 1) return "";
        Stack<Character> processed = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!processed.empty() && processed.get(processed.size() - 1) == s.charAt(i)) processed.pop();
            else processed.push(s.charAt(i));
        }
        StringBuilder result = new StringBuilder();
        for (char element : processed) result.append(element);
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(6/2);
        System.out.println(passwordChecker("aaa111"));
    }
}
