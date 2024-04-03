import java.util.*;

public class Hard {
    static class  Pair implements Comparable<Pair>{
        int vertex;
        int distance;
        Pair(int curr_vertex,int its_distance){
            this.vertex=curr_vertex;
            this.distance=its_distance;
        }
        public int compareTo(Pair p2){
            return this.distance-p2.distance;
        }
    }
    static class Edge{
        int src;
        int des;
        int weight;
         Edge(int src,int des,int weight){
            this.src=src;
            this.des=des;
            this.weight=weight;
        }
    }
    public List<String>  findItinerary(List<List<String>> tickets){
        ArrayList<String> ticket=new ArrayList<>();
        HashMap<String,String> details=new HashMap<>();
        String start="";

        for (List<String> tic:tickets){
            details.put(tic.get(0),tic.get(1));
        }
        HashMap<String,String> rev_tic=new HashMap<>();

        for (Map.Entry<String,String> chithi:details.entrySet()){
            rev_tic.put(chithi.getValue(),chithi.getKey());
        }


        for (String w:
             details.keySet()) {
            if(!rev_tic.containsKey(w)){
                ticket.add(w);
//                System.out.print(w+"->");
                start=w;
            }
        }
        while (!details.isEmpty()){
            ticket.add(details.get(start));
            String temp=start;
            start=details.get(start);
            details.remove(temp);
        }

        return ticket;

    }
    public static int solve(int lenght_rod,int[] sizes){
        if(lenght_rod==0){
            return 0;
        }
        if(lenght_rod<0){
            return -1;
        }
        int min=Integer.MAX_VALUE;
        for(int i=0;i<sizes.length;i++){
            int min_for_that_size=solve(lenght_rod-sizes[i],sizes);
            if (min_for_that_size>=0) {
                min = Math.min(min, min_for_that_size);
            }
        }
        return min+1;
    }
    public static int minCost(int n, int[] cuts) {
        return solve(n,cuts);
    }
    public static int Devide_chocolate_1231(ArrayList<Integer> a, int n, int m){
        Collections.sort(a);
        if(m>n){
            return -1;
        }
        int ans=Integer.MAX_VALUE;
        int maxi=m-1;
        int min=0;
        while(maxi<n){
            int curr_min=a.get(maxi)- a.get(min);
            ans=Math.min(curr_min,ans);
            maxi++;
            min++;
        }

        return ans;
    }
    public  void dfs(ArrayList<ArrayList<Integer>> graphs,int current,boolean[] visit){
        visit[current]=true;
        count++;
        for (int i=0;i<graphs.get(current).size();i++){
            int curr=graphs.get(current).get(i);
            if (!visit[curr]){
                dfs(graphs,curr,visit);
            }
        }
    }
    public  void topoSorting_dfs(int[] edges,int current,boolean[] visisted,Stack<Integer> st)  {
        visisted[current]=true;
        int child=edges[current];
        if (child!=-1 && !visisted[child]){
            topoSorting_dfs(edges,child,visisted,st);
        }
        st.push(current);
    }
    static int count;
    public int longestCycle_2360(int[] edges) {
        int len=edges.length;
        boolean[] visited=new boolean[len];
        Stack<Integer> st=new Stack<>();
        for (int i=0;i<len;i++){
            if (!visited[i]){
                topoSorting_dfs(edges,i,visited,st);
            }
        }
        Arrays.fill(visited,false);

        ArrayList<ArrayList<Integer>> revereseArr=new ArrayList<>();
        for (int i=0;i<len;i++){
            revereseArr.add(new ArrayList<>());
        }

        for (int i=0;i<len;i++){
            int des=edges[i];
            if(des!=-1){
                revereseArr.get(des).add(i);
            }
        }

        int maxCycle=0;
        while (!st.isEmpty()){
            int curr_node=st.pop();
            if(!visited[curr_node]){
                visited[curr_node]=true;
                count=0;
                dfs(revereseArr,curr_node,visited);
                maxCycle=Math.max(maxCycle,count);
            }
        }
        return  maxCycle>1?maxCycle:-1;
    }
    public static void dfs_bridge_finding(List<List<Integer>> list,boolean[] visist,int time,int[] discover_tym,int[] lowest_discover_time,int current,int parent,List<List<Integer>> ans){
        visist[current]=true;
        discover_tym[current]=lowest_discover_time[current]=++time;

        for (int i=0;i<list.size();i++){
            int curr_src=list.get(i).get(0);
            int curr_neighbour=list.get(i).get(1);
            if (curr_neighbour==parent){
                continue;
            }
            else if (!visist[curr_neighbour]) {
                dfs_bridge_finding(list,visist,time,discover_tym,lowest_discover_time,curr_neighbour,current,ans);
                lowest_discover_time[current]=Math.min(lowest_discover_time[current],lowest_discover_time[curr_neighbour]);
            }
            if (discover_tym[curr_src]<lowest_discover_time[curr_neighbour]){
                ans.add(new ArrayList<>(){{add(current);add(curr_neighbour);}});
            }
            else {
                lowest_discover_time[curr_neighbour]=Math.min(lowest_discover_time[current],discover_tym[curr_neighbour]);
            }
        }
    }
    public List<List<Integer>> criticalConnections_1192(int n, List<List<Integer>> connections) {
        boolean[] visit=new boolean[n];
        int[] discover_time=new int[n];
        int[] lowest_discover_time=new int[n];
        int time=0;
        List<List<Integer>> ans=new ArrayList<>();

        for (int i=0;i<n;i++){
            if (!visit[i]){
                dfs_bridge_finding(connections,visit,time,discover_time,lowest_discover_time,i,-1,ans);
            }

        }
        return ans;
    }
//    public void getting_articulation(ArrayList<ArrayList<Integer>> connections,int current,int parent,int[] dt,int [] ldt,int time,boolean[] visited,List<List<Integer>> ans){
//        visited[current]=true;
//        ldt[current]=dt[current]=++time;
//        int child=0;
//        for (int i:connections.get(current)){
//            int neighbour=i;
//            if (neighbour==parent){
//                continue;
//            }
//            else if(!visited[neighbour]){
//                getting_articulation(connections,neighbour,current,dt,ldt,time,visited,ans);
//                ldt[current]=Math.min(ldt[current],ldt[neighbour]);
//
//                if (parent!=-1 && dt[current]<=ldt[neighbour]){
//                    ans.add(new ArrayList<>(){{add(current);add(parent);}});
//                }
//                child++;
//            }
//            else{
//                ldt[current]=Math.min(ldt[current],dt[neighbour]);
//            }
//
//            if (parent==-1 && child>1){
//                ans.add(new ArrayList<>(){{add(current);add(parent);}});
//            }
//        }
//
//    }
//    public List<List<Integer>> criticalConnections1_1192(int n, List<List<Integer>> connections) {
//        int[] dt=new int[n];
//        int[] ldt=new int[n];
//        boolean[] visited=new boolean[n];
//        List<List<Integer>> ans=new ArrayList<>();
//        int time =0;
//        int parent=-1;
//        ArrayList<ArrayList<Integer>> edge=create_connection(n,connections);
//        for (int i=0;i<n;i++) {
//            if (!visited[i]) {
//                getting_articulation(edge, i, parent, dt, ldt, time, visited, ans);
//            }
//        }
//        return ans;
//    }
//    public ArrayList<ArrayList<Integer>> create_connection(int n,List<List<Integer>> connections){
//        ArrayList<ArrayList<Integer>> edges=new ArrayList<>();
//        for (int i=0;i<n;i++){
//            edges.add(new ArrayList<>());
//        }
//
//        for (List<Integer> edge:connections){
//            int u=edge.get(0);
//            int v=edge.get(1);
//
//            edges.get(u).add(v);
//            edges.get(v).add(u);
//
//        }
//        return edges;
//    }
    public int[] articulationPoints(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans=new ArrayList<>();
        boolean[] visited=new boolean[V];
        int[] dictionary_time=new int[V];
        int[] lowest_dictionary_time=new int[V];
        int time=0;
        int parent=-1;
        for (int i=0;i<V;i++) {
            if (!visited[i]) {
                dfs_articulation_time(adj, i, parent,visited,dictionary_time,lowest_dictionary_time,time,ans);
            }
        }
        ArrayList<Integer> the_final=new ArrayList<>();
        for(int i:ans){
            if(!the_final.contains(i)){
                the_final.add(i);
            }
        }
        int[] final_ans=new int[the_final.size()];
        if(the_final.size()!=0){
            for (int i=0;i<the_final.size();i++){
                final_ans[i]=the_final.get(i);
            }
            Arrays.sort(final_ans);
            return final_ans;
        }
        else{
            int ans1[]=new int[1];
            ans1[0]=-1;
            return ans1;
        }
    }
    public void dfs_articulation_time(ArrayList<ArrayList<Integer>> list,int current,int parrent,boolean[] visisted,int[] dictionary_time,int[] lowest_dictionary_time,int time,ArrayList<Integer> ans ){
        visisted[current]=true;
        dictionary_time[current]=lowest_dictionary_time[current]=++time;
        int child=0;

        for (int i:list.get(current)){
            int neighbour=i;

            if (neighbour==parrent){
                continue;
            }
            else if (!visisted[neighbour]) {
                dfs_articulation_time(list,neighbour,current,visisted,dictionary_time,lowest_dictionary_time,time,ans);
                lowest_dictionary_time[current]=Math.min(lowest_dictionary_time[current],lowest_dictionary_time[neighbour]);
                if (parrent!=-1 && dictionary_time[current]<=lowest_dictionary_time[neighbour]) {
                    ans.add(current);
                }
                child++;
            }
            else {
                lowest_dictionary_time[current]=Math.min(dictionary_time[neighbour],lowest_dictionary_time[current]);
            }
        }
        if (parrent==-1 && child>1){
            ans.add(current);
        }
    }
    class Node{
        Node[] children;
        boolean is_the_end=false;
        Node(){
            children=new Node[256];
            for (int i=0;i<256;i++){
                children[i]=null;
            }

        }
    }
    Boolean[] dp;
    public boolean searching(String word,Node root){
        Node curr=root;
        for (char alpha:word.toCharArray()){

            int index=alpha-'a';
            if(curr.children[index]==null){
                return false;
            }

            curr=curr.children[index];
        }
        return curr.is_the_end;
    }
    public void insertion(String word,Node root){
        Node curr=root;
        for (char alpha:word.toCharArray()){
            int index=alpha-'a';
            if (curr.children[index]==null){
                curr.children[index]=new Node();
            }
            curr=curr.children[index];
        }
        curr.is_the_end=true;

    }
    public boolean wordbreak_check(String word,Node root,int index){
        if (word.length()==index){
            return true;
        }

        if(dp[index]!=null){
            return dp[index];
        }
        for (int i=index+1;i<=word.length();i++){
            String first_word=word.substring(index,i);


            if (searching(first_word,root) && wordbreak_check(word,root,i)){
                return dp[index]=true;
            }
        }
        return dp[index]=false;
    }
    public boolean wordBreak_139(String s, List<String> wordDict) {
        Node root_139=new Node();

        for (String word:wordDict){
            insertion(word,root_139);
        }
        dp= new Boolean[s.length()];
        return wordbreak_check(s,root_139,0);
    }
    public static int maxi(int[] arr){
        for (int i:arr){
            System.out.print(i);
        }
        System.out.println();
        System.out.println("j");
        Arrays.sort(arr);
        for (int i:arr){
            System.out.print(i);
        }
        System.out.println("l");
        System.out.println();
        return arr[arr.length-1];
    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> ans=new ArrayList<>();
        int length=nums.length;
        int[] arr=new int[k];
        for (int i=0;i<k;i++){
            arr[i]=nums[i];
        }
        int end=k;
        ans.add(maxi(arr));
        while (end<length){
            arr[arr.length-1]=nums[end];
            arr[0]=nums[end-k+1];
            ans.add(maxi(arr));
            end++;
        }
//        System.out.println(ans);
        Integer[] integerArray =  ans.toArray(new Integer[0]);
        int[] fin=new int[integerArray.length];
        for (int i=0;i<integerArray.length;i++){
            fin[i]= integerArray[i];
        }
        return fin;
    }
    public static boolean check_pelin(String temp){
        if (temp.isEmpty()){
            return false;
        }
        int start=0;
        int end=temp.length()-1;
        while (start<=end){
            if (temp.charAt(start)!=temp.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    public static void min_cut(String s,int index){
        if (index>=s.length()){
            return;
        }
        for (int i=index;i<s.length();i++){
            if(i>index && s.charAt(i)==s.charAt(i-1)){
                continue;
            }
            String first_string=s.substring(index,i+1);
            String second_String=s.substring(i+1);
            if (check_pelin(first_string) && check_pelin(second_String)){
                System.out.println(first_string+"  "+second_String);

                count1=count1+1;
                min_cut(s,i+1);
            }
        }
    }
    static int count1=0;
    public static  int minCut(String s) {
        min_cut(s,0);
        return count1;
    }
    public ListNode reverseKGroup_25(ListNode head, int k) {
        if (head==null){
            return null;
        }

        ListNode prev=null;
        ListNode next=null;
        ListNode curr=head;

        ListNode checker=head;
        for(int first_count=0;first_count<k;first_count++ ){
            if(checker==null){
                return curr;
            }
            checker=checker.next;
        }
        int counter=0;
        while (curr!=null && counter<k){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
            counter++;
        }

        if (curr!=null){
            head.next=reverseKGroup_25(curr,k);
        }

        return prev;

    }
    public ListNode merge_sort(ListNode head1,ListNode head2){
        if(head1==null ){
            return head2;
        }
        if(head2==null){
            return head1;
        }
        ListNode dummy=new ListNode();
        ListNode dum = dummy;
        while (head1!=null && head2!=null){
            if(head1.val<=head2.val){
                dum.next=head1;
                dum=dum.next;
                head1=head1.next;
            }
            else {
                dum.next=head2;
                dum=dum.next;
                head2=head2.next;
            }
        }

        if(head1!=null){
            dum.next=head1;
        }

        if(head2!=null){
            dum.next=head2;
        }

        return dummy.next;
    }
    public ListNode mergeKListsHelper(ListNode[] lists,int start,int end){
        if(start==end){
            return lists[start];
        }
        if(start+1==end){
            return merge_sort(lists[start],lists[end]);
        }
        int mid=start+(end-start)/2;
        ListNode left=mergeKListsHelper(lists,start,mid);
        ListNode right=mergeKListsHelper(lists,mid+1,end);
        return merge_sort(left,right);
    }
    public ListNode mergeKLists_23(ListNode[] lists) {
        if(lists==null || lists.length==0){
            return null;
        }
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }
    public static void left_smalest_values(int[] heights,int left_smalleft[]){
        Stack<Integer> st=new Stack<>();
        st.push(-1);
        for (int i=0;i<heights.length;i++){
            int curr=heights[i];
            while (st.peek()!=-1 && heights[st.peek()]>=curr){
                st.pop();
            }
            left_smalleft[i]=st.peek();
            st.push(i);
        }
    }
    public static void right_smallest_value(int[] heights,int[] right_smallest){
        Stack<Integer> st=new Stack<>();
        st.push(-1);
        for (int i=heights.length-1;i>=0;i--){
            int curr=heights[i];
            while (st.peek()!=-1 && heights[st.peek()]>=curr){
                st.pop();
            }
            right_smallest[i]=st.peek();
            st.push(i);
        }
    }
    public static int largestRectangleArea(int[] heights) {
        int len=heights.length;
        int[] left_smallest=new int[len];
        int[] right_smallest=new int[len];
        left_smalest_values(heights,left_smallest);

        right_smallest_value(heights,right_smallest);
        int ans=Integer.MIN_VALUE;
        for (int i=0;i<len;i++){
            int height=heights[i];
            if(right_smallest[i]==-1){
                right_smallest[i]=len;
            }
            int breadth=right_smallest[i]-left_smallest[i]-1;

            int area=height*breadth;
            ans=Math.max(area,ans);
        }
        return ans;
    }
    public  static void left_side(long[] hist,long[] left_side_smallest){
        Stack<Integer> st=new Stack<>();
        st.push(-1);
        for (int i=0;i<hist.length;i++){
            long curr=hist[i];
            while (st.peek()!=-1 && hist[st.peek()]>=curr){
                st.pop();
            }
            left_side_smallest[i]=st.peek();
            st.push(i);
        }
    }
    public  static void right_side(long[] hist,long[] right_side_smallest){
        Stack<Integer> st=new Stack<>();
        st.push(-1);
        for (int i=hist.length-1;i>=0;i--){
            long curr=hist[i];
            while (st.peek()!=-1 && hist[st.peek()]>=curr){
                st.pop();
            }
            right_side_smallest[i]=st.peek();
            st.push(i);
        }
    }
    public static long getMaxArea(long hist[], long n) {
        long[] left_side_smallest=new long[(int) n];
        long[] right_side_largest=new long[(int) n];
        left_side(hist,left_side_smallest);
        right_side(hist,right_side_largest);
        long ans=0;
        for (int i=0;i<n;i++){
            long height=hist[i];
            if(right_side_largest[i]==-1){
                right_side_largest[i]=n;
            }
            long width=right_side_largest[i]-left_side_smallest[i]-1;
            long area=height*width;
            ans=Math.max(area,ans);
        }
        return  ans;
    }
    public static int celebrity(int M[][], int n) {
        Stack<Integer> no=new Stack<>();
        for(int i=0;i<n;i++){
            no.push(i);
        }
        while(no.size()>1){
            int a=no.pop();
            int b=no.pop();
            if (M[a][b]==1){
                no.push(b);
            }
            else {
                no.push(a);
            }
        }
        int candi=no.peek();

        int row_count=0;
        boolean row_checking=false;
        for(int i=0;i<n;i++){
            if(M[candi][i]==0){
                row_count++;
            }
        }

        if(row_count==n){
            row_checking=true;
        }

        boolean column_checking=false;
        int column_count=0;
        for(int i=0;i<n;i++){
            if (M[i][candi]==1){
                column_count++;
            }
        }
        if (column_count==n-1){
            column_checking=true;
        }

        if(row_checking && column_checking){
            return candi;
        }
        return -1;
    }

    public static void left_smallest(int[] matrix,int[] left_checks){
        Stack<Integer> st=new Stack<>();
        st.push(-1);
        for (int i=0;i<matrix.length;i++){
            int curr=matrix[i];
            while (st.peek()!=-1 && matrix[st.peek()]>=curr){
                st.pop();
            }
            left_checks[i]=st.peek();
            st.push(i);

        }
    }
    public static void right_smallest(int[] matrix,int[] right_checks){
        Stack<Integer> st=new Stack<>();
        st.push(-1);
        for (int i=matrix.length-1;i>=0;i--){
            int curr=matrix[i];
            while (st.peek()!=-1 && matrix[st.peek()]>=curr){
                st.pop();
            }
            right_checks[i]=st.peek();
            st.push(i);
        }
    }
    public static int maxi_area(int[] matrix){
        int[] left_val=new int[matrix.length];
        int[] right_val=new int[matrix.length];
        left_smallest(matrix,left_val);
        right_smallest(matrix,right_val);
        int area=Integer.MIN_VALUE;
        for(int i=0;i<matrix.length;i++){
            int curr_length=matrix[i];

            if(right_val[i]==-1) {
                right_val[i] = matrix.length;
            }
            int width=right_val[i]-left_val[i]-1;
            int curr_area=curr_length*width;
            area=Math.max(curr_area,area);
        }
        return area;
    }
    public static  int maximalRectangle_85(char[][] matrix) {
        int[][] matrix_copy=new int[matrix.length][matrix[0].length];
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[i].length;j++){
                matrix_copy[i][j]=Character.getNumericValue(matrix[i][j]);
            }
        }
        int max_area=maxi_area(matrix_copy[0]);
        for (int i=1;i<matrix_copy.length;i++){
            for (int j=0;j<matrix_copy[i].length;j++){
                if (matrix_copy[i][j]==1){
                    matrix_copy[i][j]=matrix_copy[i][j]+matrix_copy[i-1][j];
                }
                else {
                    matrix_copy[i][j]=0;
                }
            }
            int curr_area=maxi_area(matrix_copy[i]);
            max_area=Math.max(max_area,curr_area);
        }
        return max_area;
    }
    public static ArrayList<Integer> sum_maxi_mini_for_every_slide(int[] arr,int k){
        Queue<Integer> maxi=new LinkedList<>();
        Queue<Integer> mini=new LinkedList<>();
        ArrayList<Integer> nos=new ArrayList<>();
        for (int i=0;i<k;i++){
            while (!maxi.isEmpty() && arr[maxi.peek()]<=arr[i]){
                maxi.poll();
            }
            while(!mini.isEmpty() && arr[mini.peek()]>=arr[i]){
                mini.poll();
            }
            maxi.add(i);
            mini.add(i);
        }
       assert !mini.isEmpty();
        int ans=arr[maxi.peek()]+arr[mini.peek()];
        nos.add(ans);
        for (int i=k;i<arr.length;i++){
            //removing
            while (!maxi.isEmpty() && i-maxi.peek()>=k){
                maxi.poll();
            }
            while (!mini.isEmpty() && i-mini.peek()>=k){
                mini.poll();
            }
            while (!maxi.isEmpty() && arr[maxi.peek()]<=arr[i]){
                maxi.poll();
            }
            while(!mini.isEmpty() && arr[mini.peek()]>=arr[i]){
                mini.poll();
            }

            maxi.add(i);
            mini.add(i);
            ans=arr[maxi.peek()]+arr[mini.peek()];
            nos.add(ans);
        }
        return nos;
    }

    public int[] maxSlidingWindow_239(int[] nums, int k) {
            if(k==1){
                return nums;
            }
            int[] ans=new int[nums.length-k+1];
            Deque<Integer> q=new LinkedList<>();
            for (int i=0;i<k;i++){
                while (!q.isEmpty() && nums[q.peek()]<=nums[i]){
                    q.pollLast();
                }
                q.add(i);
            }
            int index=0;

            for (int i=k;i<nums.length;i++){
                ans[index]=nums[q.peek()];
                while (!q.isEmpty() && i-q.peek()>=k){
                    q.poll();
                }
                while (!q.isEmpty() && nums[q.peek()]<=nums[i]){
                    q.pollLast();
                }

                q.add(i);
                index++;


            }
            ans[index]=nums[q.peek()];
            return ans;
        }


}
