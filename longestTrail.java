public class longestTrail {
    public static int maxSignal(char[] strInput) {
        int length = strInput.length;
        if (strInput.length <= 1) return 0;
        int currStart = 0;
        int currEnd = 0;
        int maxDifference = -1;
        //loop through all chars, if chars are different we will calculate the difference between indexes and compare
        //it to our current max. We will skip this if the current end is the end of the given string.
        //after doing so we set the current start point as the current index and continue.
        for (int i = 0; i < length; i++) {
            if (i > 0 && strInput[i - 1] != strInput[i]) {
                currEnd = i - 1;
                System.out.println(currEnd);
                if (currStart != 0 && currEnd != length - 1) {
                    int difference = currEnd - currStart;
                    if (maxDifference < difference) maxDifference = difference;
                }
                currStart = i;
            }
            currEnd++;
        }
        return maxDifference + 1;
    }

    public static void main(String[] args) {
        char[] test = new char[]{1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0,
                0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1,
                0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1,
                1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1};
        System.out.println(maxSignal(test));
    }
}
