import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class largestInt {
    public static String largestNumber(int[] cost, int target) {
        HashMap<Integer, HashMap<Integer,ArrayList<Integer>>> wasd = new HashMap<>();
        for(int i = 0;i<cost.length;i++){
            if(wasd.containsKey(cost[i])&&cost[i]<=target){
                HashMap<Integer,ArrayList<Integer>> temp15 = wasd.get(cost[i]);
                ArrayList<Integer> largest = temp15.get(0);
                ArrayList<Integer> smallest = temp15.get(1);
                int currLargest = largest.get(0);
                if(i+1>largest.get(0)){ largest.remove(0); largest.add(i+1);}
                smallest.add(currLargest);
                temp15.replace(0,largest);temp15.replace(1,smallest);
                wasd.replace(cost[i],temp15);

            }else{
                HashMap<Integer,ArrayList<Integer>> temp15 = new HashMap<>();
                ArrayList<Integer> largest = new ArrayList<>();
                ArrayList<Integer> smallest = new ArrayList<>();
                largest.add(i+1);
                temp15.put(0,largest);temp15.put(1,smallest);
                wasd.put(cost[i],temp15);
            }
        }

        for(int i = 0;i<cost.length;i++){
            HashMap<Integer,ArrayList<Integer>> temp15 = wasd.get(cost[i]);
            ArrayList<Integer> largest = temp15.get(0);
            ArrayList<Integer> smallest = temp15.get(1);

        }
        System.out.println(wasd);
        return "";

    }
    public static void main(String[] args){
        int[] cost = new int[]{4,3,2,5,6,7,2,5,5};
        int target = 9;
        String test = "abc";
        StringBuilder a = new StringBuilder(test);
        a.setCharAt(0,'c');
        a.setCharAt(1,'b');
        a.setCharAt(2,' ');
        String result = a.toString();
        System.out.println(result);
        largestNumber(cost,target);
    }
}
