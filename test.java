import java.util.*;
import java.lang.*;

public class test
{
    // Function to count the number of nodes
    // with maximum connections
    static void get(HashMap<Integer, ArrayList<Integer>> graph)
    {
        // Stores the number of connections
        // of each node
        ArrayList<Integer> v = new ArrayList<>();
        HashMap<Integer,ArrayList<Integer>> result = new HashMap<>();
        // Stores the maximum connections
        int mx = -1;
        for (int i = 1; i < graph.size(); i++) {
            ArrayList<Integer> temp = result.getOrDefault(graph.get(i).size(),new ArrayList<>());
            temp.add(i);
            result.put(graph.get(i).size(),temp);
            v.add(graph.get(i).size());
            mx = Math.max(mx, (int) graph.get(i).size());
        }

        // Resultant count
        for (int i : v) {
            if (i != mx){
                result.remove(i);
            }else{
                ArrayList<Integer> temp = result.get(i);
                System.out.println(temp);
            }
        }

        System.out.println(result);
    }

    public static int[]  maxTollRevenue(int num, int[][] roadList)
    {
        int[] answer = new int[100];
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        boolean[][] connected = new boolean[num+5][num+5];
        int[] counters = new int[num+1];
        for (int i = 1; i <= num; i++) {
            graph.put(i, new ArrayList<>());
        }
        for(int[] r : roadList){
            counters[r[0]]++;counters[r[1]]++;
            connected[r[0]][r[1]] = true;
            connected[r[1]][r[0]] = true;
            graph.get(r[0]).add(r[1]);
            graph.get(r[1]).add(r[0]);
        }
        HashMap<Integer,Integer> res = new HashMap<>();
        for(int i = 0;i<num-1;i++){
            for(int j=i+1;j<num;j++){
               res.put(roadList[i][0],counters[i]+counters[j]-(connected[i][j] ? 1 : 0));
            }
        }

        get(graph);
        return answer;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        // input for num
        int num = in.nextInt();

        // input for roadList
        int roadList_row = in.nextInt();
        int roadList_col = in.nextInt();
        int[][] roadList = new int[roadList_row][roadList_col];
        for(int idx = 0; idx < roadList_row; idx++)
        {
            for(int jdx = 0; jdx < roadList_col; jdx++)
            {
                roadList[idx][jdx] = in.nextInt();
            }
        }

        int[] result = maxTollRevenue(num, roadList);
        for(int idx = 0; idx < result.length - 1; idx++)
        {
            System.out.print(result[idx] + " ");
        }
        System.out.print(result[result.length - 1]);
    }
}
