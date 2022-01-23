import java.util.*;
public class newTest {

    public static void main(String[] args) {
        //String s = "(A,B) (A,C) (B,D) (D,C)";
        String s = "(B,D) (D,E) (A,B) (C,F) (E,G) (A,C)";
        System.out.println(GetSExpression(s));
    }

    public static String GetSExpression(String s) {
        
        List<List<Integer>> adjList = new ArrayList<>();
        HashSet<Integer> nodes=new HashSet<>();
        for (int i = 0; i < 26; i++)
            adjList.add(new ArrayList<Integer>());
        boolean E2Flag = false;
        for (int i = 1; i < s.length(); i = i + 6) {
            int parent = s.charAt(i) - 'A';
            int child = s.charAt(i + 2) - 'A';
            nodes.add(parent);
            nodes.add(child);
            if (adjList.get(parent).contains(child))
                E2Flag = true;
            else {
                adjList.get(parent).add(child);
                if (adjList.get(parent).size() > 2)
                    return "E1";    //more than two children
            }
        }
        if (E2Flag) return "E2";    //Duplicate edge found

        char root = '#';
        int nvs = nodes.size();
        boolean E4flag = false;
        ArrayList<Integer> toplSort = new ArrayList<>();
        int[] indegree = new int[nvs];
        for (int i = 0; i < 26; i++)
            for (int x : adjList.get(i))
                indegree[x]++;

        Queue<Integer> qu = new LinkedList<>();
        for (int i = 0; i < 26; i++)
            if (nodes.contains(i) && indegree[i]==0) {
                qu.add(i);
                root = (char)(i+'A');
            }
        if (qu.size() >= 2)    //more than 1 root
            E4flag = true;

        while (!qu.isEmpty()) {
            int tmp = qu.poll();
            toplSort.add(tmp);

            for (int x : adjList.get(tmp)) {
                indegree[x]--;
                if (indegree[x] == 0)
                    qu.offer(x);
            }
        }
        //System.out.println(Arrays.toString(toplSort.stream().toArray()));
        if (toplSort.size() < nvs) return "E3";
        if (E4flag) return "E4";    //more than 1 root
        if (root == '#') return "E5";
        //return null;

        return makeExpression(root, adjList);
    }

    private static String makeExpression(char root, List<List<Integer>> adjList) {
        String left="";
        String right="";
        List<Integer> tmp=adjList.get(root-'A');
        if(tmp.size()==1)
            left=makeExpression( (char)(tmp.get(0)+'A'), adjList );
        else if(tmp.size()==2){
            left=makeExpression( (char)(tmp.get(0)+'A'), adjList );
            right=makeExpression( (char)(tmp.get(1)+'A'), adjList );
        }
        return "("+root+left+right+")";
    }
}