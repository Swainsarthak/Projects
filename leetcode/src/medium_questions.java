import com.sun.source.tree.Tree;

import java.awt.event.ItemEvent;
import java.beans.PropertyEditorManager;
import java.security.Key;
import java.util.*;
public class medium_questions {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int value) {
            this.val = value;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Pair implements Comparable<Pair> {
        int vertex;
        int distance;

        Pair(int curr_vertex, int its_distance) {
            this.vertex = curr_vertex;
            this.distance = its_distance;
        }

        public int compareTo(Pair p2) {
            return this.distance - p2.distance;
        }
    }

    static class Edge {
        int src;
        int des;
        int weight;

        Edge(int src, int des, int weight) {
            this.src = src;
            this.des = des;
            this.weight = weight;
        }
    }

    static int i = -1;

    public static TreeNode buildBinaryTree(int[] nodes) {
        i++;
        if (nodes[i] == -1) {
            return null;
        }

        TreeNode newNode = new TreeNode(nodes[i]);
        newNode.left = buildBinaryTree(nodes);
        newNode.right = buildBinaryTree(nodes);

        return newNode;
    }

    public static ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        int carry = 0;
        ListNode domi = new ListNode(-1);
        ListNode head1 = l1;
        ListNode head2 = l2;
        ListNode temp1 = domi;
        while (head1 != null || head2 != null || carry == 1) {
            int sum = 0;
            if (head1 != null) {
                sum += head1.val;
                head1 = head1.next;
            }
            if (head2 != null) {
                sum += head2.val;
                head2 = head2.next;
            }

            sum = sum + carry;
            carry = sum / 10;
            temp1.next = new ListNode(sum % 10);
            temp1 = temp1.next;
        }

        return domi.next;
    }

    public static int longestSubstringWithoutRepeating_3(String s) {
        HashSet<Character> seen = new HashSet<>();

        int left = 0;
        int right = 0;
        int max = 0;
        while (left < s.length()) {
            char current = s.charAt(left);

            if (seen.add(current)) {
                left++;
                max = Math.max(max, seen.size());
            } else {
                while (s.charAt(right) != current) {
                    seen.remove(s.charAt(right));
                    right++;

                }
                seen.remove(s.charAt(right));
                right++;
            }
        }

        return max;
    }

    public static String longest_palindromic_substring(String s) {
        if (s.length() <= 1) {
            return s;
        }

        String the_longest_palindrome = "";

        for (int i = 1; i < s.length(); i++) {
            int left = i;
            int right = i;

            while (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;

                if (left == -1 || right == s.length()) {
                    break;
                }
            }
            String the_current_string = s.substring(left + 1, right);
            if (the_current_string.length() > the_longest_palindrome.length()) {
                the_longest_palindrome = the_current_string;
            }
            left = i - 1;
            right = i;

            while (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;

                if (left == -1 || right == s.length()) {
                    break;
                }
            }
            the_current_string = s.substring(left + 1, right);


            if (the_current_string.length() > the_longest_palindrome.length()) {
                the_longest_palindrome = the_current_string;
            }
        }

        return the_longest_palindrome;
    }

    public static String intToRoman(int num) {

        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] no = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder original_no = new StringBuilder();
        for (int i = 0; num > 0; i++) {
            System.out.println(no[i]);
            while (num >= no[i]) {
                System.out.println("true");
                num = num - no[i];
                original_no.append(romans[i]);
                System.out.println(num);
            }
        }

        return original_no.toString();

//        Map<String,Integer> no_values=new HashMap<>();
//        no_values.put("M",1000);
//        no_values.put("CM",900);
//        no_values.put("D",500);
//        no_values.put("CD",400);
//        no_values.put("C",100);
//        no_values.put("XC",90);
//        no_values.put("L",50);
//        no_values.put("XL",40);
//        no_values.put("X",10);
//        no_values.put("IX",9);
//        no_values.put("V",5);
//        no_values.put("IV",4);
//        no_values.put("I",1);

    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[right] >= target && target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

        }
        return -1;
//        HashMap<Integer,Integer> de=new HashMap<>();
//        for(int i=0;i<nums.length;i++){
//            de.put(nums[i],i);
//        }
//        if(de.containsKey(target)){
//            return de.get(target);
//        }
//
//        return -1;

    }

    public static List<List<Integer>> levelOrder_102(TreeNode root) {

        List<List<Integer>> tree_levels = new ArrayList<>();
        if (root == null) return tree_levels;

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            List<Integer> levels = new ArrayList<>();
            int nos = nodes.size();
            for (int i = 0; i < nos; i++) {
                TreeNode poped = nodes.remove();

                if (poped.left != null) nodes.add(poped.left);
                if (poped.right != null) nodes.add(poped.right);
                levels.add(poped.val);
            }

            tree_levels.add(levels);
        }
        return tree_levels;
    }

    public static TreeNode inorder_successor(TreeNode right) {
        while (right.left != null) {
            right = right.left;
        }

        return right;
    }

    public static TreeNode deleteNode_450(TreeNode root, int key) {
        if (root.val > key) {
            root.left = deleteNode_450(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode_450(root.right, key);          //search the node that has the same value has the key which has to be deleted.
        } else {
            if (root.left == null && root.right == null) {            //got a key that is the leaf.so directly return the null.
                return null;
            }

            if (root.left == null) {                               //got a key the that has the single child.either left or right.
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            TreeNode inorder_successor = inorder_successor(root.right);       //if the key node has two child.
            root.val = inorder_successor.val;                                 //root value replaced with the inorder successor.
            root.right = deleteNode_450(root.right, inorder_successor.val);    //delete the  successor node.Search the last left item by going to the right of that node.
        }
        return root;
    }

    public static int[] checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> indexx = new HashMap<>();
        int start = 0;
        int second = 0;
        int sum = nums[second];
        while (second < nums.length - 1) {
            if (sum == k) {
                return new int[]{start, second};

            }
            if (sum < k) {
                second++;
                sum += nums[second];

            } else {

                sum -= nums[start];
                start++;
            }
        }
        return new int[]{};

    }

    public static List<Integer> majorityElement2_229(int[] nums) {
        int len = nums.length / 3;
        List<Integer> values = new ArrayList<>();
        Map<Integer, Integer> nos_list = new HashMap<>();
        for (int i : nums) {
            if (nos_list.containsKey(i)) {
                nos_list.put(i, nos_list.get(i) + 1);
            } else {
                nos_list.put(i, 1);
            }
        }

        for (Map.Entry<Integer, Integer> sets : nos_list.entrySet()) {
            if (sets.getValue() > len) {
                values.add(sets.getKey());
            }
        }

        return values;
    }

    public static int subarraySum_560(int[] nums, int k) {
//        int count=0;
//        for(int i=0;i<nums.length;i++){
//            int sum=0;
//            for(int j=i;j<nums.length;j++){
//                sum+=nums[j];
//                if(sum==k) count++;
//            }
//        }
//        return count;

        HashMap<Integer, Integer> values = new HashMap<>();
        int sum = 0;
        int count = 0;
        values.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int remain = sum - k;
            count += values.getOrDefault(remain, 0);
            values.put(sum, values.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static TreeNode createBinaryTree_2196(int[][] descriptions) {
        HashMap<Integer, TreeNode> parent_set = new HashMap<>();
        HashSet<Integer> set_root = new HashSet<>();

        for (int[] sets : descriptions) {
            int parent = sets[0];
            int child = sets[1];
            int hands = sets[2];
            set_root.add(sets[1]);
            TreeNode parent_node = parent_set.getOrDefault(parent, new TreeNode(parent));
            TreeNode child_node = parent_set.getOrDefault(child, new TreeNode(child));

            if (hands == 1) parent_node.left = child_node;
            else parent_node.right = child_node;

            parent_set.put(parent, parent_node);
            parent_set.put(child, child_node);
        }
        TreeNode root = null;
        for (int[] i : descriptions) {
            if (!set_root.contains(i[0])) root = parent_set.get(i[0]);
        }
        return root;
    }

    public static int[] subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> values = new HashMap<>();

        values.put(0, -1);
        int current_sum = 0;
        for (int i = 0; i < nums.length; i++) {
            current_sum += nums[i];
            int remain = current_sum - k;

            if (values.containsKey(remain)) {
                return new int[]{values.get(remain) + 1, i};
            }

            values.put(current_sum, i);
        }
        return new int[]{};
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        return (isSameTree(p.right, q.right) && isSameTree(p.left, q.left));
    }

    public static ListNode insertionSortList_147(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dommi = new ListNode();
        dommi.next = head;
        ListNode current = head, prev;

        while (current.next != null) {
            if (current.val > current.next.val) {
                ListNode nextNode = current.next;
                prev = dommi;
                while (prev.next.val < nextNode.val) {
                    prev = prev.next;
                }
                current.next = nextNode.next;
                nextNode.next = prev.next;
                prev.next = nextNode;
            } else {
                current = current.next;
            }
        }
        return dommi.next;

    }

    public static ListNode sortList_148(ListNode head) {
        // linked list sort using bubble sort
//        if(head==null){
//            return null;
//        }
//        ListNode temp1=null;
//        while(temp1!=head.next){
//            ListNode temp2=head;
//            while(temp2.next!=temp1){
//                ListNode curr=temp2.next;
//                if(temp2.val>curr.val){
//                    int val=temp2.val;
//                    temp2.val=curr.val;
//                    curr.val=val;
//                }
//                temp2=temp2.next;
//            }
//            temp1=temp2;
//        }
//        return head;


        //selection sort of linked list
//        if(head==null)return null;
//
//        ListNode temp1=head;
//        while(temp1.next!=null){
//            ListNode leight=temp1;
//            ListNode temp2=temp1.next;
//            while(temp2!=null){
//                if(leight.val>temp2.val){
//                    leight=temp2;
//                }
//                temp2=temp2.next;
//            }
//
//            int val=temp1.val;
//            temp1.val=leight.val;
//            leight.val=val;
//
//            temp1=temp1.next;
//        }
//
//        return head;


        //insertion sort
//        if(head==null || head.next==null){
//            return head;
//        }
//        ListNode dommi=new ListNode();
//        dommi.next=head;
//        ListNode current=head,prev;
//
//        while( current!=null && current.next!=null ){
//            if(current.val>current.next.val){
//                ListNode nextNode=current.next;
//                prev=dommi;
//                while(prev.next.val<nextNode.val){
//                    prev=prev.next;
//                }
//                current.next=nextNode.next;
//                nextNode.next=prev.next;
//                prev.next=nextNode;
//            }
//
//            else {
//                current=current.next;
//            }
//        }
//        return dommi.next;

        return null;
    }

    public static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void rotate_189(int[] nums, int k) {
        int rotate_count = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, rotate_count - 1);
        reverse(nums, rotate_count, nums.length - 1);
    }

    public static int maxSubArray_53(int[] nums) {
        int sum = 0;
        int maxi = nums[0];
        for (int num : nums) {
            sum += num;
            maxi = Math.max(sum, maxi);

            if (sum < 0) sum = 0;
        }

        return maxi;
    }

    public static void in_order(TreeNode root, ArrayList<Integer> arr) {
        if (root == null) {
            return;
        }

        in_order(root.left, arr);

        arr.add(root.val);
        in_order(root.right, arr);
    }

    public static int kthSmallest_230(TreeNode root, int k) {
        ArrayList<Integer> arr = new ArrayList<>();
        in_order(root, arr);
        return arr.get(k - 1);
    }

    List<List<Integer>> arr_main = new ArrayList<>();

    public void two_sum(int[] nums, int start, int end, int first_index) {

        while (start < end) {
            if (nums[start] + nums[end] > -nums[first_index]) {
                end--;
            } else if (nums[start] + nums[end] < -nums[first_index]) {
                start++;
            } else {
                List<Integer> arr = new ArrayList<>();

                arr.add(nums[first_index]);
                arr.add(nums[start]);
                arr.add(nums[end]);
                arr_main.add(arr);
                while (start < end && nums[start] == nums[start + 1]) {
                    start++;
                }
                while (start < end && nums[end] == nums[end - 1]) {
                    end--;
                }
                start++;
                end--;
            }
        }
    }

    public List<List<Integer>> threeSum_15(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                two_sum(nums, i + 1, nums.length - 1, i);
            }
        }
        return arr_main;
    }

    List<List<Integer>> answers = new ArrayList<>();

    public void two_Sum(int[] nums, int start, int end, long targe, int ith_index, int jth_index) {
        while (start < end) {

            if (nums[start] + nums[end] == targe) {
                List<Integer> arr = new ArrayList<>();
                arr.add(nums[ith_index]);
                arr.add(nums[jth_index]);
                arr.add(nums[start]);
                arr.add(nums[end]);

                answers.add(arr);

                while (start < end && nums[start] == nums[start + 1]) {
                    start++;
                }
                while (start < end && nums[end] == nums[end - 1]) {
                    end--;
                }
                start++;
                end--;
            } else if (nums[start] + nums[end] < targe) {
                start++;
            } else end--;
        }

    }

    public List<List<Integer>> fourSum_18(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < len - 2; j++) {
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        long temp = nums[i] + nums[j];
                        long tar = target - (temp);
                        two_Sum(nums, j + 1, len - 1, tar, i, j);
                    }
                }
            }
        }
        return answers;
    }

    public int threeSumClosest_16(int[] nums, int target) {
        Arrays.sort(nums);
        int maxi = nums[0] + nums[1] + nums[2];
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int start = i + 1;
                int end = len - 1;
                while (start < end) {
                    int cr_sum = nums[start] + nums[end] + nums[i];
                    if (Math.abs(target - cr_sum) < Math.abs(target - maxi)) {
                        maxi = cr_sum;
                    } else if (nums[start] + nums[end] > target - nums[i]) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }
        }
        return maxi;
    }

    public int longestConsecutive_128(int[] nums) {
//        HashMap<Integer, Boolean> explore=new HashMap<>();
//        for(int i:nums){
//            explore.put(i,false);
//        }
//        int size=0;
//        for(int i:nums){
//            int length=1;
//            int tocheck=i+1;
//            while (explore.containsKey(tocheck) && !explore.get(tocheck)){
//                length++;
//                explore.put(tocheck,true);
//                tocheck++;
//            }
//
//            int tocheck2=i-1;
//            while (explore.containsKey(tocheck2) && !explore.get(tocheck2)){
//                length++;
//                explore.put(tocheck2,true);
//                tocheck2--;
//            }
//
//            size=Math.max(length,size);
//        }
//        return size;


        Set<Integer> arr = new HashSet<>();
        for (int i : nums) {
            arr.add(i);
        }
        int length = 0;
        for (int i : nums) {
            if (!arr.contains(i - 1)) {
                int size = 1;

                int count = i + 1;
                while (arr.contains(count)) {
                    count++;
                    size++;
                }
                length = Math.max(size, length);
            }

        }
        return length;
    }

    public static String convert_6(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        StringBuilder[] lists = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            lists[i] = new StringBuilder();
        }
        int flag = 0;
        int direction = 1;
        for (char c : s.toCharArray()) {
            lists[flag].append(c);

            if (flag == 0) {
                direction = 1;
            }
            if (flag == numRows - 1) {
                direction = -1;
            }
            flag += direction;
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder words : lists) {
            ans.append(words.toString());
        }
        return ans.toString();

    }

    public static int myAtoi_8(String s) {
        if (s.length() == 0) return 0;
        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        s = s.substring(i);
        if (s.length() == 0) return 0;
        int sign = 0;
        if (s.charAt(0) == '-') sign = -1;
        else sign = 1;
        i = (s.charAt(0) == '-' || s.charAt(0) == '+') ? 1 : 0;
        long ans = 0;
        while (i < s.length()) {
            if ((!Character.isDigit(s.charAt(i)) || s.charAt(i) == ' ')) {
                break;
            }
            ans = ans * 10 + (s.charAt(i) - '0');

            if (ans * sign > Integer.MAX_VALUE && sign == 1) return Integer.MAX_VALUE;
            if (ans * sign < Integer.MIN_VALUE && sign == -1) return Integer.MIN_VALUE;
            i++;
        }
        return (int) ans * sign;
    }

    public static int check_no_of_coins(int[] coins_set, int[] no_of_coin_used, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }
        if (no_of_coin_used[amount] != -1) {
            return no_of_coin_used[amount];
        }

        int min = Integer.MAX_VALUE;
        for (int j : coins_set) {
            int ans = check_no_of_coins(coins_set, no_of_coin_used, amount - j);
            if (ans != Integer.MAX_VALUE && ans >= 0) {
                min = Math.min(min, ans);
            }
        }
        if (min == Integer.MAX_VALUE) return -1;
        no_of_coin_used[amount] = min + 1;
        return min + 1;
    }

    public int coinChange_322(int[] coins, int amount) {
        //using only recursion
//        if(amount==0){
//            return 0;
//        }
//        if(amount<0){
//            return Integer.MAX_VALUE;
//        }
//        int min=Integer.MAX_VALUE;
//        for (int i=0;i<coins.length;i++){
//            int ans=coinChange(coins,amount-coins[i]);
//            if(ans!=Integer.MAX_VALUE && ans>=0){
//                min=Math.min(min,ans);
//            }
//        }
//        if(min==Integer.MAX_VALUE){
//            return -1;
//        }
//        return min+1;

        //using memoisation.
//        int[] sums=new int[amount+1];
//        Arrays.fill(sums,-1);
//
//        return check_no_of_coins(coins,sums,amount);

        //using buttom-up tabulisation
        int[] sums = new int[amount + 1];
        Arrays.fill(sums, Integer.MAX_VALUE);

        sums[0] = 0;
        if (amount == 0) {
            return 0;
        }
        for (int i = 1; i < sums.length; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && sums[i - coin] != Integer.MAX_VALUE) {
                    sums[i] = Math.min(sums[i], 1 + sums[i - coin]);
                }
            }
        }
        if (sums[amount] == Integer.MAX_VALUE) return -1;

        return sums[amount];
    }

    public int robbing_using_recursion(int[] houses, int sum, int index, int house_no[]) {
        if (index >= houses.length) {
            return 0;
        }
        int iclusive = robbing_using_recursion(houses, sum, index + 2, house_no) + houses[index];
        int exclusive = robbing_using_recursion(houses, sum, index + 1, house_no);
        sum = Math.max(iclusive, exclusive);
        return sum;
    }

    public int rob_198(int[] nums) {
        int length = nums.length;
//    int[] house_no=new int[nums.length+1];

        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }

//    house_no[0]=nums[0];
//    house_no[1]=Math.max(house_no[0],nums[1]);
//
//    for(int i=2;i<nums.length;i++){
//        int include=house_no[i-2]+nums[i];
//        int exclusive=house_no[i-1];
//        house_no[i]=Math.max(include,exclusive);
//    }
//    return house_no[length-1];

        int prev_of_previous = nums[0];
        int prev = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int include = nums[i] + prev_of_previous;
            int exclude = prev;
            int maximum_answer = Math.max(include, exclude);
            prev_of_previous = prev;
            prev = maximum_answer;
        }

        return prev;

    }

    static long Mod = 1000000007;

    public static long counting(int n, long[] arr) {
        if (n == 1) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (arr[n] != -1) {
            return arr[n];
        }

        arr[n] = (((n - 1) % Mod * ((counting(n - 2, arr)) % Mod + (counting(n - 1, arr)) % Mod)) % Mod);
        return arr[n];

    }

    public static long countDerangements_634(int n) {
        long[] arr = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            arr[i] = -1;
        }
//        return counting(n,arr);
        arr[0] = 0;
        arr[1] = 0;
        arr[2] = 1;

        for (int i = 3; i < arr.length; i++) {
            long a1 = arr[i - 2] % Mod;
            long a2 = arr[i - 1] % Mod;
            long sum = (a1 + a2) % Mod;
            arr[i] = ((i - 1) % Mod * sum) % Mod;
        }
        return arr[n];
    }

    public static String reverseWords_151(String s) {
//        List<String> words=new ArrayList<>();
//        StringTokenizer sr=new StringTokenizer(s," ");
//        while (sr.hasMoreTokens()){
//            words.add(sr.nextToken());
//        }
//        String ans="";
//        for (int i=words.size()-1;i>=0;i--){
//            ans+=words.get(i);
//            ans+=" ";
//        }
//        return ans;

        String[] words = s.trim().split("\\s+");
        StringBuilder ans = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            ans.append(words[i]);
            if (i != 0) {
                ans.append(" ");
            }
        }
        return ans.toString();
    }

    public void print_all(int[][] graphs, int start, int target, List<Integer> temp, List<List<Integer>> ans, boolean[] check) {
        if (start == target) {
            temp.add(start);
            ans.add(new ArrayList<>(temp));
        } else {
            temp.add(start);
            check[start] = true;
            for (int i : graphs[start]) {
                if (!check[i]) {
                    print_all(graphs, i, target, temp, ans, check);
                }
            }
        }
        check[start] = false;
        temp.remove(temp.size() - 1);

    }

    public List<List<Integer>> allPathsSourceTarget_797(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        boolean[] check = new boolean[graph.length];
        print_all(graph, 0, graph.length - 1, temp, ans, check);
        return ans;
    }

    public boolean checking_cycle(ArrayList<ArrayList<Integer>> des, int curr, boolean[] recursive_check, boolean[] visit) {
        if (recursive_check[curr]) {
            return true;
        }
        if (visit[curr]) {
            return false;
        }
        recursive_check[curr] = true;
        visit[curr] = true;

        for (int i = 0; i < des.get(curr).size(); i++) {
            int curr_des = des.get(curr).get(i);
            if (checking_cycle(des, curr_des, recursive_check, visit)) {
                return true;
            }

        }
        visit[curr] = false;
        return false;
    }

    public boolean isCycle_directional(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] recursive_check = new boolean[V];
        boolean[] visit = new boolean[V];
        for (int i = 0; i < V; i++) {
            return checking_cycle(adj, 0, recursive_check, visit);
        }
        return false;

    }

    public boolean checking_cycle_undirectional(ArrayList<ArrayList<Integer>> adj, boolean[] check, int curr, int parent) {

        check[curr] = true;
        for (int i = 0; i < adj.get(curr).size(); i++) {
            int current_no = adj.get(curr).get(i);
            if (check[current_no] && parent != curr) {
                return true;
            }
            if (!check[current_no]) {
                if (checking_cycle_undirectional(adj, check, current_no, curr)) return true;
            }

        }
        return false;
    }

    public boolean isCycle_undirectional(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] check = new boolean[V];
        for (int i = 0; i < V; i++) {
            return checking_cycle_undirectional(adj, check, i, -1);
        }
        return false;
    }

    public void adding_bfs_value(ArrayList<ArrayList<Integer>> des, boolean[] visit, ArrayList<Integer> arr) {
        Queue<Integer> values = new LinkedList<>();
        values.add(0);
        while (!values.isEmpty()) {
            int curr = values.poll();
            if (!visit[curr]) {
                arr.add(curr);
                visit[curr] = true;
                for (int i = 0; i < des.get(curr).size(); i++) {
                    values.add(des.get(curr).get(i));
                }
            }
        }
    }

    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> arr = new ArrayList<>();
        boolean[] visit = new boolean[V];
        adding_bfs_value(adj, visit, arr);
        return arr;
    }

    public void adding_gfs_value(ArrayList<ArrayList<Integer>> des, int curr, ArrayList<Integer> arr, boolean[] visit) {
        arr.add(curr);
        visit[curr] = true;

        for (int i = 0; i < des.get(curr).size(); i++) {
            int curr_num = des.get(curr).get(i);
            if (!visit[curr_num]) {
                adding_gfs_value(des, curr_num, arr, visit);
            }
        }
        visit[curr] = false;
    }

    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> arr = new ArrayList<>();
        boolean[] visit = new boolean[V];
        adding_gfs_value(adj, 0, arr, visit);
        return arr;
    }

    public void track_all_path_graph(int[][] graphs, List<List<Integer>> ans, int curr, boolean[] check, int target, List<Integer> paths) {
        if (curr == target) {
            paths.add(curr);
            ans.add(new ArrayList<>(paths));
        } else {
            paths.add(curr);

            for (int i = 0; i < graphs[curr].length; i++) {
                int num = graphs[curr][i];
                check[curr] = true;
                if (!check[num]) {
                    track_all_path_graph(graphs, ans, num, check, target, paths);
                }
            }
        }
        check[curr] = false;
        paths.remove(paths.size() - 1);
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = graph.length;
        boolean[] visit = new boolean[len];
        List<Integer> temp = new ArrayList<>();
        track_all_path_graph(graph, ans, 0, visit, len - 1, temp);
        return ans;
    }

    public boolean is_cyclic_for_directed(ArrayList<ArrayList<Integer>> adj, int current, boolean[] visit, boolean[] recursion_stack) {
        visit[current] = true;
        recursion_stack[current] = true;

        for (int i = 0; i < adj.get(current).size(); i++) {
            int curr = adj.get(current).get(i);
            if (recursion_stack[curr]) {
                return true;
            } else if (!visit[curr]) {
                if (is_cyclic_for_directed(adj, curr, visit, recursion_stack)) {
                    return true;
                }
            }
        }
        recursion_stack[current] = false;
        return false;
    }

    public boolean isCyclic_directed_graph(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        boolean[] reccursion_check = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (is_cyclic_for_directed(adj, i, visited, reccursion_check)) {
                return true;
            }
        }
        return false;

    }

    public static boolean contains_cycle_in_undirected_graph(ArrayList<ArrayList<Integer>> adj, int current, boolean[] visisted, int parent) {
        visisted[current] = true;
        for (int i = 0; i < adj.get(current).size(); i++) {
            int curr = adj.get(current).get(i);

            if (curr != parent && visisted[curr]) {
                return true;
            } else if (!visisted[curr]) {
                if (contains_cycle_in_undirected_graph(adj, curr, visisted, current)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (contains_cycle_in_undirected_graph(adj, i, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        PriorityQueue<Pair> duo_pairs = new PriorityQueue<>();
        int[] distance_ans = new int[V];
        for (int i = 0; i < V; i++) {
            if (i != 0) {
                distance_ans[i] = Integer.MAX_VALUE;
            }
        }
        boolean[] visited = new boolean[V];

        duo_pairs.add(new Pair(S, S));

        while (!duo_pairs.isEmpty()) {
            Pair curr_pair = duo_pairs.remove(); //gives the shortest distance path

            int curr_smallest_dist = curr_pair.vertex;

            if (!visited[curr_smallest_dist]) {
                visited[curr_smallest_dist] = true;

                for (int i = 0; i < adj.get(curr_smallest_dist).size(); i++) {
                    int curr_src = curr_smallest_dist;
                    int curr_des = adj.get(curr_smallest_dist).get(i).get(0);
                    int curr_edge_weight = adj.get(curr_smallest_dist).get(i).get(1);

                    if (distance_ans[curr_src] + curr_edge_weight < distance_ans[curr_des]) {
                        distance_ans[curr_des] = distance_ans[curr_src] + curr_edge_weight;
                        duo_pairs.add(new Pair(curr_des, distance_ans[curr_des]));
                    }
                }
            }

        }

        return distance_ans;
    }

    public int networkDelayTime_743(int[][] times, int n, int k) {
        ArrayList<Hard.Edge> graphs[] = new ArrayList[n + 1];
        for (int i = 0; i < graphs.length; i++) {
            graphs[i] = new ArrayList<>();
        }
        for (int i = 0; i < times.length; i++) {
            Hard.Edge e = new Hard.Edge(times[i][0], times[i][1], times[i][2]);
            graphs[times[i][0]].add(e);
        }
        if (graphs[k].size() == 0) {
            return -1;
        }
        return networkDelayTime_ans(graphs, k, graphs.length);
    }

    public int networkDelayTime_ans(ArrayList<Hard.Edge>[] graphs, int src, int size) {
        boolean[] visisted = new boolean[size];
        int[] distance = new int[size];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;
        PriorityQueue<Hard.Pair> sets = new PriorityQueue<>();
        sets.add(new Hard.Pair(src, 0));
        while (!sets.isEmpty()) {
            Hard.Pair curr_pair = sets.remove();
            int curr_src = curr_pair.vertex;
            if (!visisted[curr_src]) {
                visisted[curr_src] = true;
                for (int i = 0; i < graphs[curr_src].size(); i++) {
                    Hard.Edge crrnt_edge = graphs[curr_src].get(i);
                    int cur_src = crrnt_edge.src;
                    int crr_neighbour = crrnt_edge.des;
                    int weight = crrnt_edge.weight;

                    if (distance[cur_src] + weight < distance[crr_neighbour]) {
                        distance[crr_neighbour] = distance[cur_src] + weight;
                        sets.add(new Hard.Pair(crr_neighbour, distance[crr_neighbour]));
                    }
                }
            }
        }
        for (int i = 1; i < visisted.length; i++) {
            if (!visisted[i])
                return -1;
        }
        int max = 0;
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] > max) {
                max = distance[i];
            }
        }
        return max;
    }

    public static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[S] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < edges.size(); j++) {
                int src = edges.get(j).get(0);
                int dest = edges.get(j).get(1);
                int weight = edges.get(j).get(2);

                if (distance[src] != Integer.MAX_VALUE && distance[src] + weight < distance[dest]) {
                    distance[dest] = distance[src] + weight;
                }
            }
        }

        return distance;
    }

    public static int spanningTree(int V, int E, int edges[][]) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            adj.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
            adj.get(edges[i][1]).add(new Pair(edges[i][0], edges[i][2]));
        }
        PriorityQueue<Pair> sets = new PriorityQueue<>();
        boolean[] visited = new boolean[V];
        sets.add(new Pair(0, 0));
        int cost = 0;
        while (!sets.isEmpty()) {
            Pair curr_pair = sets.remove();
            if (!visited[curr_pair.vertex]) {
                visited[curr_pair.vertex] = true;
                cost += curr_pair.distance;
                for (int i = 0; i < adj.get(curr_pair.vertex).size(); i++) {
                    int curr_destination = adj.get(curr_pair.vertex).get(i).vertex;
                    int curr_weight = adj.get(curr_pair.vertex).get(i).distance;
                    if (!visited[curr_destination]) {
                        sets.add(new Pair(curr_destination, curr_weight));
                    }
                }
            }
        }
        return cost;
    }

    public void topo_sort(ArrayList<ArrayList<Integer>> graphs, Stack<Integer> sets, boolean[] visit, int curr) {
        visit[curr] = true;
        for (int i = 0; i < graphs.get(curr).size(); i++) {
            if (!visit[graphs.get(curr).get(i)]) {
                topo_sort(graphs, sets, visit, graphs.get(curr).get(i));
            }
        }
        sets.push(curr);
    }

    public void dfs(ArrayList<ArrayList<Integer>> graphs, int curr, boolean[] visited) {
        visited[curr] = true;

        for (int i = 0; i < graphs.get(curr).size(); i++) {
            int curr_node = graphs.get(curr).get(i);
            if (!visited[curr_node]) {
                dfs(graphs, curr_node, visited);
            }
        }
    }

    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> st = new Stack<>();
        boolean[] visit = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visit[i]) {
                topo_sort(adj, st, visit, i);
            }
        }

        Arrays.fill(visit, false);

        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            for (int n : adj.get(i)) {
                arr.get(n).add(i);
            }
        }
        int scc = 0;
        while (!st.isEmpty()) {
            int node = st.pop();
            if (!visit[node]) {
                visit[node] = true;
                dfs(arr, node, visit);
                scc++;
            }
        }
        return scc;
    }

    class Node_trie {
        public Node_trie[] children;
        public boolean is_the_word;

        public Node_trie() {
            children = new Node_trie[26];
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            is_the_word = false;
        }
    }

    public class Trie_208 {
        private Node_trie root;

        public Trie_208() {
            root = new Node_trie();
        }

        public void insert(String word) {
            Node_trie curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (curr.children[index] == null) {
                    curr.children[index] = new Node_trie();
                }

                curr = curr.children[index];
            }
            curr.is_the_word = true;
        }

        public boolean search(String word) {
            Node_trie curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (curr.children[index] == null) {
                    return false;
                }
                curr = curr.children[index];
            }
            return curr.is_the_word;
        }

        public boolean startsWith(String prefix) {
            Node_trie curr = root;
            for (char ch : prefix.toCharArray()) {
                int index = ch - 'a';
                if (curr.children[index] == null) {
                    return false;
                }
                curr = curr.children[index];
            }
            return true;
        }
    }

    public void heapify(int[] nums, int n, int i) {
        int smallest = i;
        int left_child = (i * 2) + 1;
        int right_child = (i * 2) + 2;
        if (left_child < n && nums[smallest] < nums[left_child]) {
            smallest = left_child;
        }
        if (right_child < n && nums[smallest] < nums[right_child]) {
            smallest = right_child;
        }
        if (smallest != i) {
            int temp = nums[i];
            nums[i] = nums[smallest];
            nums[smallest] = temp;
            heapify(nums, n, smallest);
        }
    }

    public void heap_sort(int[] arr, int size) {
        int len = size;
        while (len > 0) {
            int temp = arr[0];
            arr[0] = arr[len];
            arr[len] = temp;

            heapify(arr, len, 0);
            len--;
        }
    }

    public int[] sortArray_912(int[] nums) {
        int siz = nums.length;

        for (int i = (siz / 2) - 1; i >= 0; i--) {
            heapify(nums, siz, i);
        }
        siz = siz - 1;
        heap_sort(nums, siz);
        return nums;
    }

    public void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public void give_permut(int index, int[] nums, List<List<Integer>> ans) {
        if (index >= nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int i : nums) {
                list.add(i);
            }
            ans.add(list);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            give_permut(index + 1, nums, ans);
            swap(nums, index, i);
        }
    }

    public List<List<Integer>> permute_46(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        give_permut(0, nums, ans);
        return ans;

    }

    public static void level(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void sortColors_75(int[] nums) {
        int middle = 0;
        int start = 0;
        int end = nums.length - 1;
        while (middle <= end) {
            if (nums[middle] == 0) {
                swap(nums, start, middle);
                middle++;
                start++;
            } else if (nums[middle] == 1) {
                middle++;
            } else {
                swap(nums, start, end);
                end--;
            }
        }
    }

    public long maximumSubarraySum_2461(int[] nums, int k) {
        HashSet<Integer> sets = new HashSet<>();
        int arr_length = nums.length;
        int start = 0;
        int end = 0;
        long sum = 0;
        long max_sum = 0;
        while (end < arr_length) {
            //if the window size is increased by the size given den remove the 1st value from the sum.
            if (end - start == k) {
                sum -= nums[start];
                sets.remove(nums[start]);
                start++;
            }

            //untill the duplicate get removed keep removing elements from the first.
            while (sets.contains(nums[end])) {
                sum -= nums[start];
                sets.remove(nums[start]);
                start++;
            }

            //add values from the front.
            sum += nums[end];
            sets.add(nums[end]);


            // when the window is of the size check the sum
            if (end - start + 1 == k) {
                max_sum = Math.max(sum, max_sum);
            }
            end++;

        }
        return max_sum;
    }

    public int left_check(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        int mid = start + (end - start) / 2;
        int pos = -1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == k) {
                pos = mid;
                end = mid - 1;
            }

            if (nums[mid] < k) {
                start = mid + 1;
            } else if (nums[mid] > k) {
                end = mid - 1;
            }
        }
        return pos;
    }

    public int right_check(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        int mid = start + (end - start) / 2;
        int pos = -1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == k) {
                pos = mid;
                start = mid + 1;
            }

            if (nums[mid] < k) {
                start = mid + 1;
            } else if (nums[mid] > k) {
                end = mid - 1;
            }
        }
        return pos;
    }

    public long left_check(long[] nums, long k) {
        long start = 0;
        long end = nums.length - 1;
        long mid = start + (end - start) / 2;
        long pos = -1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[(int) mid] == k) {
                pos = mid;
                end = mid - 1;
            }

            if (nums[(int) mid] < k) {
                start = mid + 1;
            } else if (nums[(int) mid] > k) {
                end = mid - 1;
            }
        }
        return pos;
    }

    public int[] searchRange_34(int[] nums, int target) {
        int left = left_check(nums, target);
        int right = right_check(nums, target);
        return new int[]{left, right};
    }

    public int count(int[] arr, int n, int x) {
        int count = 0;
        int left_count = left_check(arr, x);
        int right_count = right_check(arr, x);
        return right_count - left_count + 1;

    }

    public int peakIndexInMountainArray_852(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
//            if(arr[mid-1]<arr[mid] && arr[mid]>arr[mid+1]){
//                return arr[mid];
//            }
            if (arr[mid] < arr[mid + 1]) {
                start = mid + 1;
            } else if (arr[mid] > arr[mid + 1]) {
                end = mid - 1;
            } else {
                mid = end;
            }
        }
        return mid;
    }

    public static int pivotIndex_point(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int mid = start + (end - start) / 2;
        while (start < end) {
            if (nums[mid] >= nums[0]) {
                start = mid + 1;
            } else {
                end = mid;
            }
            mid = start + (end - start) / 2;
        }
        return start;
    }

    public static int b_search(int[] nums, int target, int start, int end) {
        int mid = 0;
        int pos = -1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                pos = mid;
                return pos;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            }
        }
        return pos;
    }

    public static int search_33(int[] nums, int target) {
        int piv_index = pivotIndex_point(nums);
        System.out.println(piv_index);
        if (nums[piv_index] <= target && nums[nums.length - 1] >= target) {
            return b_search(nums, target, piv_index, nums.length - 1);
        } else {
            return b_search(nums, target, 0, piv_index - 1);
        }

    }

    public void build_map(Hashtable<Integer, String> hmap) {
        hmap.put(2, "abc");
        hmap.put(3, "def");
        hmap.put(4, "ghi");
        hmap.put(5, "jkl");
        hmap.put(6, "mno");
        hmap.put(7, "pqrs");
        hmap.put(8, "tuv");
        hmap.put(9, "wxyz");
    }

    public void create_mapings(Hashtable<Integer, String> maps, List<String> ans, int index, StringBuilder output, String digit) {
        if (index >= digit.length()) {
            ans.add(output.toString());
            return;
        }
        int num = digit.charAt(index) - '0';
        String val = maps.get(num);
        for (int i = 0; i < val.length(); i++) {
            output.append(val.charAt(i));
            create_mapings(maps, ans, index + 1, output, digit);
            output.deleteCharAt(output.length() - 1);

        }
    }

    public List<String> letterCombinations_17(String digits) {
        Hashtable<Integer, String> maps = new Hashtable<>();
        build_map(maps);
        List<String> ans = new ArrayList<>();
        StringBuilder out = new StringBuilder();
        if (digits.isEmpty()) {
            return ans;
        }
        create_mapings(maps, ans, 0, out, digits);
        return ans;


    }

    public static void reversing_list(ListNode temp) {
        if (temp == null) {
            return;
        }
        if (temp.next == null) {
            return;
        }
        int temp_value = temp.val;
        temp.val = temp.next.val;
        temp.next.val = temp_value;
        temp = temp.next.next;
        reversing_list(temp);
    }

    public ListNode swapPairs_24(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode temp = head;
        reversing_list(temp);
        return head;
    }

    public static void gnrte_parenthesis(StringBuilder temp, int opener, int closer, int number, List<String> ans) {
        if (temp.length() == number * 2) {

            ans.add(String.valueOf(temp));
            return;
        }

        if (opener < number) {
            temp.append('(');
            gnrte_parenthesis(temp, opener + 1, closer, number, ans);
            temp.deleteCharAt(temp.length() - 1);
        }

        if (closer < opener) {
            temp.append(')');
            gnrte_parenthesis(temp, opener, closer + 1, number, ans);
            temp.deleteCharAt(temp.length() - 1);
        }

    }

    public static List<String> generateParenthesis_22(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        gnrte_parenthesis(temp, 0, 0, n, ans);
        return ans;


    }

    public static void reverse_string_in_between(char[] ch, int first_index, int second_index) {
        while (first_index <= second_index) {
            char temp = ch[first_index];
            ch[first_index] = ch[second_index];
            ch[second_index] = temp;
            first_index++;
            second_index--;
        }
    }

    public static void reverse_alphabets(char[] arr) {
//        if(arr.length==1){
//            return arr;
//        }
        int first_index = 0;
        while (first_index < arr.length) {
            int second_index = first_index;
            while (arr[second_index] != ' ' && second_index < arr.length) {
                second_index++;
            }
            reverse_string_in_between(arr, first_index, second_index - 1);

        }

    }

    public static boolean binary_search_in_array(int[][] mat, int target, int size, int row) {
        int start = 0;
        int end = size - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mat[row][mid] == target) {
                return true;
            } else if (mat[row][mid] > target) {
                end = mid - 1;
            } else {
                start = start + 1;
            }
        }
        return false;
    }

    public boolean searchMatrix_74(int[][] matrix, int target) {
        int len = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < len; i++) {
            if (matrix[i][0] <= target && target <= matrix[i][col - 1]) {
                return binary_search_in_array(matrix, target, col, i);
            }
        }
        return false;
    }

    public boolean check_prime(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int countPrimes_204(int n) {
        if (n <= 1) return 0;
        boolean[] nos = new boolean[n];
        Arrays.fill(nos, true);
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (nos[i]) {
                count++;
            }
            for (int j = i * 2; j < n; j = j + i) {
                nos[j] = false;
            }
        }
        return count;

    }

    public static double power_using_recursion(double no, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return no;
        }
        double ans = power_using_recursion(no, n / 2);
        if (n % 2 == 0) {
            return ans * ans;
        } else {
            return ans * ans * no;
        }
    }

    public static double myPow_50(double x, int n) {
        if (n >= 0) {
            return power_using_recursion(x, n);
        }
        return 1 / power_using_recursion(x, n);

    }

    public static void swaps(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = temp;
        nums[b] = temp;
    }

    public static void permutation_adding(int[] nums, int index, List<List<Integer>> ans, int size) {
        if (index >= size) {
            List<Integer> temp = new ArrayList<>();
            for (int i : nums) {
                temp.add(i);
            }
            ans.add(temp);
            return;
        }
        for (int i = index; i < size; i++) {
            swaps(nums, index, i);
            permutation_adding(nums, index + 1, ans, size);
            swaps(nums, index, i);
        }
    }

    public List<List<Integer>> permutes_46(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        permutation_adding(nums, 0, ans, nums.length);
        return ans;
    }

    public static void swaping(StringBuilder string, int i, int j) {
        char temp = string.charAt(i);
        string.setCharAt(i, string.charAt(j));
        string.setCharAt(j, temp);
    }

    public static void getting_permutant(int string_size, List<String> permutants, int index, StringBuilder temp) {
        if (index >= string_size) {
            permutants.add(temp.toString());
            return;
        }

        for (int i = index; i < string_size; i++) {
            swaping(temp, index, i);
            getting_permutant(string_size, permutants, index + 1, temp);
            swaping(temp, index, i);
        }
    }

    public static boolean checking_permuting_in_anaother_sring(String str1_bada_word, String str2_permut) {
        int length_bada_wala_word = str1_bada_word.length();
        int length_permut_word = str2_permut.length();
        if (str2_permut.isEmpty()) {
            return true;
        }
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < length_permut_word; i++) {
            word.append(str1_bada_word.charAt(i));
        }
        if (word.toString().equals(str2_permut)) {
            return true;
        }
        int count = length_permut_word;
        while (count < length_bada_wala_word) {
            word.append(str1_bada_word.charAt(count));
            word.deleteCharAt(0);
            if (word.toString().equals(str2_permut)) {
                return true;
            }
            count++;
        }
        return false;
    }

    public static boolean checkInclusion_567(String s1, String s2) {
        List<String> permutants = new ArrayList<>();
        StringBuilder sr = new StringBuilder(s1);
        getting_permutant(sr.length(), permutants, 0, sr);

        for (String word : permutants) {
            if (checking_permuting_in_anaother_sring(s2, word)) {
                return true;
            }
        }
        return false;
    }

    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<List<Integer>> levels = new ArrayList<>();
        Levels(root, levels);
        int max = Integer.MIN_VALUE;
        int ans = 0;
        for (int i = 0; i < levels.size(); i++) {
            int sum = 0;
            for (int j : levels.get(i)) {
                sum += j;
            }
            if (sum > max) {
                max = sum;
                ans = i;
            }
        }
        return ans + 1;
    }

    public void Levels(TreeNode root, List<List<Integer>> levels) {
        Queue<TreeNode> nos = new LinkedList<>();
        nos.add(root);
        while (nos != null) {
            List<Integer> lev = new ArrayList<>();
            int size = nos.size();
            for (int i = 0; i < size; i++) {
                TreeNode poped = nos.poll();
                if (poped.left != null) {
                    nos.add(poped.left);
                }
                if (poped.right != null) {
                    nos.add(poped.right);
                }
                lev.add(poped.val);
            }
            levels.add(lev);
        }
    }

    public int level_order(TreeNode root) {
        Queue<TreeNode> checks = new LinkedList<>();
        checks.add(root);
        int level_no = 1;
        int ans = 0;
        int maxi = Integer.MIN_VALUE;
        while (!checks.isEmpty()) {
            int sum = 0;
            int nos = checks.size();
            for (int i = 0; i < nos; i++) {
                TreeNode poped = checks.poll();
                assert poped != null;
                sum += poped.val;
                if (poped.left != null) {
                    checks.add(poped.left);
                }
                if (poped.right != null) {
                    checks.add(poped.right);
                }

            }
            if (sum > maxi) {
                maxi = sum;
                ans = level_no;
            }
            level_no++;

        }
        return ans;
    }

    public int maxLevelSum_1161(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return level_order(root);
    }

    public static StringBuilder count_say(int counter, int the_no, StringBuilder ans) {
        if (counter == the_no) {
            return ans;
        }
        int size_check = 0;

        char first_char = ans.charAt(0);
        int point = 0;

        StringBuilder temp_ans = new StringBuilder();
        while (point < ans.length()) {
            if (ans.charAt(point) != first_char) {
                temp_ans.append(size_check);
                temp_ans.append(first_char);
                first_char = ans.charAt(point);
                size_check = 0;
            } else {
                point++;
                size_check++;
            }
        }
        temp_ans.append(size_check);
        temp_ans.append(ans.charAt(point - 1));
        return count_say(counter + 1, the_no, temp_ans);
    }

    public static String countAndSay_38(int n) {
        StringBuilder ans = new StringBuilder();
        ans.append('1');
        int counter = 0;
        if (n == counter) {
            return ans.toString();
        }
        return count_say(counter + 1, n, ans).toString();
    }

    public static boolean checking_road(int i, int j, int n, int[][] checks, int[][] m) {
        if ((i < n && j < n) && (i >= 0 && j >= 0)) {
            return checks[i][j] == 0 && m[i][j] == 1;
        }
        return false;
    }

    public static void getting_paths(int[][] roads, int[][] checks, int n, ArrayList<String> ans, String word, int x, int y, int[] direct_x, int[] direct_y) {
        if (x == n - 1 && y == n - 1) {
            ans.add(word);
            return;
        }
        String directions = "DLRU";

        for (int i = 0; i < 4; i++) {
            int di_x = x + direct_x[i];
            int di_y = y + direct_y[i];
            boolean condi = checking_road(di_x, di_y, n, checks, roads);
            if (condi) {
                checks[x][y] = 1;
                getting_paths(roads, checks, n, ans, word + directions.charAt(i), di_x, di_y, direct_x, direct_y);
                checks[di_x][di_y] = 0;
            }
        }
    }

    public static ArrayList<String> findPath(int[][] m, int n) {
        ArrayList<String> ans = new ArrayList<>();
        int[][] check = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(check[i], 0);
        }
        String ref = "";
        int[] direct_x = new int[]{+1, 0, 0, -1};//up,right,left,down
        int[] direct_y = new int[]{0, -1, +1, 0};
        if (m[0][0] == 1) {
            getting_paths(m, check, n, ans, ref, 0, 0, direct_x, direct_y);
        }
        if (ans.isEmpty()) {
            ans.add("-1");
            return ans;
        }
        return ans;
    }

    public static void make_zeroes(HashMap<List<Integer>, Integer> map, int i, int j) {
        for (Map.Entry<List<Integer>, Integer> rows_column : map.entrySet()) {
            List<Integer> temp = rows_column.getKey();
            if (temp.get(0) == i || temp.get(1) == j) {
                map.put(temp, 0);
            }
        }
    }

    public static void making_zeros(int[][] matrix, HashMap<List<Integer>, Integer> array) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    make_zeroes(array, i, j);
                }
            }
        }
    }

    public static void setZeroes_73(int[][] matrix) {
        HashMap<List<Integer>, Integer> array_2d = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                List<Integer> temp = Arrays.asList(i, j);
                array_2d.put(temp, matrix[i][j]);
            }
        }
        making_zeros(matrix, array_2d);

        for (Map.Entry<List<Integer>, Integer> ans : array_2d.entrySet()) {
            List<Integer> index = ans.getKey();
            if (ans.getValue() == 0) {
                matrix[index.get(0)][index.get(1)] = 0;
            }
        }
    }

    public ListNode reverse_linked_list_for_rotate_right(ListNode start) {
        if (start == null || start.next == null) {
            return start;
        }
        ListNode curr = start;
        ListNode prev = null;
        ListNode next = curr.next;
        while (next != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        ListNode head1 = head;
        int size = 1;
        while (head1.next != null) {
            size += 1;
            head1 = head1.next;
        }
        if (size % k == 0 || head.next == null) {
            return head;
        }
        head1.next = head;
        int rem = size % k;
        ListNode temp = head;
        for (int i = 0; i < rem; i++) {
            temp = temp.next;
        }
        ListNode ans = temp.next;
        temp.next = null;
        return ans;
    }

    public static void combi_sum(int[] candidates, int target, int index, List<List<Integer>> ans, int sum, List<Integer> temp) {
        if (sum == target) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            sum += candidates[i];
            temp.add(candidates[i]);
            combi_sum(candidates, target, i, ans, sum, temp);
            sum -= candidates[i];
            temp.remove(temp.size() - 1);
        }
    }

    public static List<List<Integer>> combinationSum_39(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        combi_sum(candidates, target, 0, ans, 0, temp);
        return ans;
    }

    public static void combi_sum2(int[] candi, int sum, int target, List<List<Integer>> ans, List<Integer> temp, int index) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < candi.length; i++) {
            if (i > index && candi[i] == candi[i - 1]) {
                continue;
            }
            sum += candi[i];
            temp.add(candi[i]);
            combi_sum2(candi, sum, target, ans, temp, i + 1);
            sum -= candi[i];
            temp.remove(temp.size() - 1);
        }
    }

    public static List<List<Integer>> combinationSum2_40(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        combi_sum2(candidates, 0, target, ans, temp, 0);
        return ans;
    }

    public static void swap1(int[] n, int a, int b) {
        int temp = n[a];
        n[a] = n[b];
        n[b] = temp;
    }

    public static void permuting(int[] nums, List<List<Integer>> ans, List<Integer> temp, int index, int size, boolean[] check) {
        if (temp.size() >= size && !ans.contains(temp)) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < size; i++) {
            if (check[i]) {
                continue;
            }

            check[i] = true;
            temp.add(nums[i]);
            permuting(nums, ans, temp, i + 1, size, check);
            temp.remove(temp.size() - 1);
            check[i] = false;
        }
    }

    public static List<List<Integer>> permuteUnique_47(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        permuting(nums, ans, temp, 0, nums.length, new boolean[nums.length]);
        return ans;
    }

    public static void subset_withdup(int[] nums, List<List<Integer>> ans, List<Integer> temp, int index) {
        if (!ans.contains(temp)) {
            ans.add(new ArrayList<>(temp));
        }
        for (int i = index; i < nums.length; i++) {
            temp.add(nums[i]);
            subset_withdup(nums, ans, temp, i + 1);
            temp.remove(temp.size() - 1);

        }
    }

    public static List<List<Integer>> subsetsWithDup_90(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        subset_withdup(nums, ans, new ArrayList<>(), 0);
        return ans;
    }

    public static boolean check_pelin(String temp) {
        int start = 0;
        int end = temp.length() - 1;
        while (start <= end) {
            if (temp.charAt(start) != temp.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void partion_check(String word, List<List<String>> ans, List<String> temp, int index) {
        if (index >= word.length()) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < word.length(); i++) {
            String part_word = word.substring(index, i + 1);
            if (check_pelin(part_word)) {
                temp.add(part_word);
                partion_check(word, ans, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static List<List<String>> partition_131(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        partion_check(s, ans, temp, 0);
        return ans;
    }

    public static String multiply_43(String num1, String num2) {
        int num1_length = num1.length();
        int num2_length = num2.length();

        int[] product = new int[num2_length + num1_length];

        for (int i = num1_length - 1; i >= 0; i--) {
            for (int j = num2_length - 1; j >= 0; j--) {
                int a = num1.charAt(i) - '0';
                int b = num2.charAt(j) - '0';

                product[i + j + 1] += a * b;
            }
        }

        int carry = 0;
        for (int i = product.length - 1; i >= 0; i--) {
            int temp = (product[i] + carry) % 10;
            carry = (product[i] + carry) / 10;
            product[i] = temp;
        }

        StringBuilder ans = new StringBuilder();
        for (int num : product) {
            ans.append(num);
        }

        while (!ans.isEmpty() && ans.charAt(0) == '0') {
            ans.deleteCharAt(0);
        }

        if (ans.isEmpty()) {
            return "0";
        }
        return ans.toString();
    }

    public void deleteNode_237(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;

    }

    public ListNode deleteMiddle_2095(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }

        prev.next = slow.next;
        return head;
    }

    class Node {
        Node next = null;
        int data;

        public Node(int value) {
            this.data = value;
        }
    }

    class MyLinkedList {
        Node head = null;
        int size = 0;

        public MyLinkedList() {
            head = null;
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            } else {
                int count = 0;
                Node temp = head;
                while (count < index) {
                    count++;
                    temp = temp.next;

                }
                return temp != null ? temp.data : 0;
            }
        }

        public void addAtHead(int val) {

            Node new_node = new Node(val);
            new_node.next = head;
            head = new_node;
            size++;


        }

        public void addAtTail(int val) {
            Node new_node = new Node(val);
            if (head == null) {
                head = new_node;

            } else {
                Node temp = head;
                while (temp.next != null) {
                    temp = temp.next;

                }
                temp.next = new_node;
            }
            size++;

        }

        public void addAtIndex(int index, int val) {
            if (index == size) {
                addAtTail(val);
            } else if (index > size) {
                return;
            } else {
                Node newNode = new Node(val);
                if (head == null) {
                    head = newNode;
                } else if (index == 0) {
                    newNode.next = head;
                    head = newNode;
                } else {
                    Node current = head;
                    Node previous = null;
                    for (int i = 0; i < index; i++) {
                        previous = current;
                        current = current.next;
                    }
                    newNode.next = current;
                    previous.next = newNode;
                }
                size++;
            }


        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            Node current = head;
            Node previous = null;
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            if (previous == null) {
                head = head.next;
            } else {
                previous.next =
                        current.next;
            }
            size--;
        }
    }

    public int pairSum_2130(ListNode head) {
        int max_ans = 0;
        Stack<Integer> nos = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            nos.push(temp.val);
            temp = temp.next;
        }
        int mid = nos.size() / 2;
        while (nos.size() != mid) {
            int sum = head.val + nos.peek();
            max_ans = Math.max(max_ans, sum);
            nos.pop();
            head = head.next;
        }
        return max_ans;
    }

    public ListNode deleteDuplicates_82(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode prv = dummy;

        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            if (next == null) {
                prv.next = curr;
                return dummy.next;
            } else if (curr.val != next.val) {
                prv = curr;

            } else {
                while (next != null && curr.val == next.val) {
                    next = next.next;
                }
                prv.next = next;

            }
            curr = next;

        }
        return dummy.next;
    }

    public ListNode partition_86(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode smaller = new ListNode();
        ListNode larger = new ListNode();
        ListNode curr = head;
        ListNode small = smaller;
        ListNode large = larger;
        while (curr != null) {
            if (curr.val < x) {
                small.next = curr;
                small = small.next;
            } else {
                large.next = curr;
                large = large.next;
            }
            curr = curr.next;
        }
        large.next = null;
        small.next = larger.next;
        return smaller.next;

    }

    public static int[] pivotArray_2161(int[] nums, int pivot) {
//        if (nums.length==1){
//            return nums;
//        }
//        int[] ans=new int[nums.length];
//        int[] larger=new int[nums.length];
//        int[] pivots=new int[nums.length];
//        int piv_counter=0;
//        int ans_index=0;
//        int looper=0;
//        int larger_index=0;
//        while (looper<nums.length){
//            if (nums[looper]<pivot){
//                ans[ans_index]=nums[looper];
//                ans_index++;
//            }
//            else if(nums[looper]>pivot) {
//                larger[larger_index]=nums[looper];
//                larger_index++;
//            }
//            else {
//                pivots[piv_counter]=nums[looper];
//                piv_counter++;
//            }
//            looper++;
//        }
//
//        larger=Arrays.copyOfRange(larger,0,larger_index);
//        pivots=Arrays.copyOfRange(pivots,0,piv_counter);
//        for (int i:pivots){
//            ans[ans_index]=i;
//            ans_index++;
//        }
//
//        for (int i:larger){
//            ans[ans_index]=i;
//            ans_index++;
//        }
//
//        return ans;

        //easy way
        int[] ans = new int[nums.length];
        int ans_counter = 0;
        int pivot_counter = 0;
        for (int i : nums) {
            if (i < pivot) {
                ans[ans_counter] = i;
                ans_counter++;
            } else if (i == pivot) {
                pivot_counter++;
            }
        }

        for (int i = 0; i < pivot_counter; i++) {
            ans[ans_counter] = pivot;
            ans_counter++;
        }
        for (int i : nums) {
            if (i > pivot) {
                ans[ans_counter] = i;
                ans_counter++;
            }
        }
        return ans;

    }

    public ListNode cycle(ListNode head) {

        ListNode speed = head;
        ListNode slow = head;
        while (speed != null && speed.next != null) {
            speed = speed.next.next;
            slow = slow.next;

            if (speed == slow) {
                return slow;
            }
        }
        return null;
    }

    public ListNode detectCycle_142(ListNode head) {
        if (head == null) {
            return null;
        }
//
//            HashMap<ListNode,Boolean> checker=new HashMap<>();
//            ListNode temp=head;
//            while (!checker.containsKey(temp) || checker.get(temp)!=true){
//                checker.put(temp,true);
//                if(temp.next!=null) {
//                    temp = temp.next;
//                }
//                else return null;
//            }
//            return temp;
        ListNode second = cycle(head);
        if (second == null) {
            return null;
        }

        ListNode head1 = head;
        while (head1 != second) {
            head1 = head1.next;
            second = second.next;
        }
        return second;
    }

    public ListNode removeDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
//        ListNode curr=head;
//        ListNode  next=curr.next;
//        while (curr!=null){
//            next=curr.next;
//            ListNode prev=curr;
//            while (next!=null){
//                if(curr.val!=next.val){
//                    prev.next=next;
//                    prev=prev.next;
//                }
//                next=next.next;
//            }
//            prev.next=null;
//            curr=curr.next;
//        }
//        return head;
        Set<Integer> s = new HashSet<>();
        ListNode curr = head;
        ListNode curr1 = head;
        while (curr1 != null) {
            if (s.contains(curr1.val)) {
                curr.next = curr1.next;
                curr1 = curr.next;
            } else {
                curr = curr1;
                s.add(curr1.val);
                curr1 = curr1.next;
            }
        }
        return head;
    }

    public ListNode rev(ListNode head) {
        if (head == null || head.next == null) {

            return head;
        }
        ListNode head1 = rev(head.next);
        head.next.next = head;
        head.next = null;
        return head1;
    }

    public ListNode addTwoNumbers_445(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode();
        ListNode dum = dummy;
        ListNode head1 = l1;
        ListNode head2 = l2;

        ListNode head11 = rev(head1);
        ListNode head22 = rev(head2);
        int carry = 0;

        while (head11 != null && head22 != null) {
            int curr_sum = head11.val + head22.val + carry;
            carry = curr_sum / 10;
            int no = curr_sum % 10;
            dum.next = new ListNode(no);
            dum = dum.next;

            head11 = head11.next;
            head22 = head22.next;
        }

        while (head11 != null) {
            int curr_sum = head11.val + carry;
            carry = curr_sum / 10;
            int no = curr_sum % 10;
            dum.next = new ListNode(no);
            dum = dum.next;
            head11 = head11.next;
        }

        while (head22 != null) {
            int curr_sum = head22.val + carry;
            carry = curr_sum / 10;
            int no = curr_sum % 10;
            dum.next = new ListNode(no);
            dum = dum.next;
            head22 = head22.next;
        }

        if (carry == 1) {
            dum.next = new ListNode(1);
            dum = dum.next;

        }
        dum.next = null;
        assert dummy.next != null;
        return rev(dummy.next);
    }

    public int getSum_371(int a, int b) {
        if (b == 0) {
            return a;
        }
        int xor = a ^ b;
        int carry = a & b;
        carry = carry << 1;
        return getSum_371(xor, carry);

    }

    class Node1 {
        public int val;
        public Node1 prev;
        public Node1 next;
        public Node1 child;
    }

    public Node1 flatten_430(Node1 head) {
        flatten1(head);
        return head;
    }

    public Node1 flatten1(Node1 head) {
        if (head == null) {
            return null;
        }
        Node1 curr = head;
        Node1 tail = head;
        while (curr != null) {
            if (curr.child != null) {
                Node1 temp = flatten1(curr.child);
                Node1 next = curr.next;
                if (next != null) {
                    temp.next = next;
                    next.prev = temp;
                }
                curr.next = curr.child;
                curr.child.prev = curr;

                curr.child = null;
                curr = temp;


            }
            tail = curr;
            curr = curr.next;
        }
        return tail;
    }

    class MinStack {
        ListNode head;
        int size;
        ListNode top;

        public MinStack() {
            head = new ListNode();
            size = 0;
            top = null;
        }

        public void push(int val) {
            ListNode new_node = new ListNode(val);
            if (top == null) {
                top = new_node;
                return;
            }
            new_node.next = top;
            top = new_node;
        }

        public void pop() {
            if (top == null) {
                return;
            }
            ListNode poped = top;
            top = top.next;
            poped.next = null;
        }

        public int top() {
            if (top == null) {
                return -1;
            }
            return top.val;
        }

        public int getMin() {
            int min = Integer.MAX_VALUE;

            ListNode dum = top;
            while (dum != null) {
                int min_temp = dum.val;
                min = Math.min(min, min_temp);
                dum = dum.next;
            }

            return min;

        }
    }

    public int minAddToMakeValid_91(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < len; i++) {
            char curr = s.charAt(i);
            if (curr == 'c') {
                st.push(curr);
            } else {
                if (!st.isEmpty() && st.peek() == '(') {
                    st.pop();
                } else {
                    st.push(curr);
                }
            }
        }
        return st.size();
//        int left = 0, right = 0;
//
//        for(int i = 0; i < s.length(); ++i) {
//            if(s.charAt(i) == '(')
//                left++;
//            else if(left > 0)
//                left--;
//            else
//                right++;
//        }
//
//        return left + right;


    }

    public class Queue1 {
        int[] arr;
        int size = 100001;
        int head;
        int tail;

        Queue1() {
            arr = new int[size];
            head = 0;
            tail = 0;

        }

        /*----------------- Public Functions of Queue -----------------*/

        boolean isEmpty() {
            if (tail == head) {
                return true;
            }
            return false;
        }

        void enqueue(int data) {
            if (tail == size) {
                System.out.println("the queue is full.");
                return;
            }
            arr[tail] = data;
            tail++;
        }

        int dequeue() {
            if (head == tail) {
                return -1;
            }
            // Implement the dequeue() function
            else {
                int ans = arr[head];
                arr[head] = -1;
                head++;
                if (tail == head) {
                    tail = 0;
                    head = 0;
                }
                return ans;
            }
        }

        int front() {
            if (head == tail) {
                return -1;
            }
            return arr[head];

        }

    }

    public class queue1 {
        ListNode node;
        ListNode head;
        ListNode tail;

        queue1() {
            node = new ListNode();
            head = node;
            tail = node;
        }

        boolean isEmpty() {
            if (tail == head) {
                return true;
            }
            return false;
        }

        void enqueue(int data) {
            tail.next = new ListNode(data);
            tail = tail.next;
        }

        int dequeue() {
            if (head == null) {
                return -1;
            }
            // Implement the dequeue() function
            else {
                int temp = head.val;
                ListNode prev = head;
                head = head.next;
                prev.next = null;
                if (head == null) {
                    ListNode new_list = new ListNode();
                    head = new_list;
                    tail = new_list;
                }
                return temp;
            }
        }

        int front() {
            if (head == null) {
                return -1;
            }
            return head.val;

        }


    }

    public List<String> buildArray_144(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int index = 0;
        int arr_length = target.length;
        for (int i = 1; i <= n; i++) {
            if (index >= arr_length) {
                break;
            } else if (target[index] == i) {
                ans.add("Push");
                index++;
            } else {
                ans.add("Push");
                ans.add("Pop");
            }

        }
        return ans;
    }

    public static int divide_29(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE && divisor == 1) {
            return Integer.MIN_VALUE;
        }
        long div = Math.abs((long) dividend);
        long divis = Math.abs((long) divisor);

        int ans = 0;


        while (div >= divis) {
            int count = 0;
            while (div >= (divis << (count + 1))) {
                count += 1;
            }
            ans += 1 << count;


            div -= divis << count;
        }
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            return -ans;
        }
        return ans;


    }

    class MyCircularQueue_622 {
        int[] arr;
        int front = -1;
        int rear = -1;
        int size;

        public MyCircularQueue_622(int k) {
            arr = new int[k];
            size = k;
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            if (rear == -1 && front == -1) {
                front += 1;
            }
            rear = (rear + 1) % size;
            arr[rear] = value;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }

            if (rear == front) {
                rear = front = -1;
            } else {
                front = (front + 1) % size;
            }
            return true;

        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return arr[front];
        }

        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return arr[rear];
        }

        public boolean isEmpty() {
            if (rear == -1 && front == -1) {
                return true;
            }
            return false;
        }

        public boolean isFull() {
            if ((rear + 1) % size == front) {
                return true;
            }
            return false;
        }
    }

    class MyCircularDeque_641 {
        int[] arr;
        int front;
        int rear;
        int size;

        public MyCircularDeque_641(int k) {
            arr = new int[k];
            size = k;
            front = -1;
            rear = -1;

        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            if (front == -1 && rear == -1) {
                front = rear = 0;
            } else if (front == 0) {
                front = size - 1;
            } else {
                front--;
            }
            arr[front] = value;
            return true;

        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            if (front == -1 && rear == -1) {
                front = 0;
            }
            rear = (rear + 1) % size;
            arr[rear] = value;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            if (rear == front) {
                rear = front = -1;
            } else {
                front = (front + 1) % size;
            }
            return true;

        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            if (rear == front) {
                rear = front = -1;
            } else if (rear == 0) {
                rear = size - 1;
            } else {
                rear--;
            }
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return arr[front];
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return arr[rear];
        }

        public boolean isEmpty() {
            if (rear == -1 && front == -1) {
                return true;
            }
            return false;
        }

        public boolean isFull() {
            if ((rear + 1) % size == front) {
                return true;
            }
            return false;
        }
    }

    class MyCircularDeque {
        class Node {
            Node next;
            Node prev;
            int data;

            Node(int data) {
                this.data = data;
                next = null;
                prev = null;
            }
        }

        Node front;
        Node rear;
        int size;
        int total;

        public MyCircularDeque(int k) {
            front = null;
            rear = null;
            size = 0;
            total = k;

        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            Node newNode = new Node(value);
            if (isEmpty()) {
                front = rear = newNode;
            } else {
                newNode.next = front;
                front.prev = newNode;
                front = newNode;
            }
            size++;
            return true;

        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            Node newNode = new Node(value);
            if (isEmpty()) {
                front = rear = newNode;
            } else {
                newNode.prev = rear;
                rear.next = newNode;
                rear = newNode;
            }
            size++;
            return true;

        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            front = front.next;
            if (front != null) {
                front.prev = null;
            } else {
                rear = null;
            }
            size--;
            return true;

        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            rear = rear.prev;
            if (rear != null) {
                rear.next = null;
            } else {
                front = null;
            }
            size--;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return front.data;
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return rear.data;
        }

        public boolean isEmpty() {
            if (size == 0) {
                return true;
            }
            return false;
        }

        public boolean isFull() {
            if (size == total) {
                return true;
            }
            return false;
        }
    }

    static class FrontMiddleBackQueue_1670 {
        static class Node {
            Node prev;
            Node next;
            int data;

            Node(int data) {
                this.data = data;
                prev = null;
                next = null;
            }
        }

        Node front;
        Node rear;
        int size;

        public FrontMiddleBackQueue_1670() {
            front = null;
            rear = null;
            size = 0;
        }

        public void pushFront(int val) {
            Node newNode = new Node(val);
            if (size == 0) {
                rear = front = newNode;
            } else {
                newNode.next = front;
                front.prev = newNode;
                front = newNode;
            }
            size++;
        }

        public void pushMiddle(int val) {
            Node newNode = new Node(val);
            if (size == 0) {
                front = rear = newNode;
            } else if (size == 1) {
                newNode.next = front;
                front.prev = newNode;
                front = newNode;
            } else {
                Node middle = front;
                for (int i = 0; i < size / 2; i++) {
                    middle = middle.next;
                }
                newNode.prev = middle.prev;
                newNode.next = middle;
                middle.prev.next = newNode;
                middle.prev = newNode;
            }
            size++;
        }

        public void pushBack(int val) {
            Node newNode = new Node(val);
            if (size == 0) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                newNode.prev = rear;
                rear = newNode;
            }
            size++;
        }

        public int popFront() {
            if (size == 0) {
                return -1;
            }
            int temp = front.data;
            front = front.next;
            if (front != null) {
                front.prev = null;
            } else {
                rear = null;
            }
            size--;
            return temp;
        }

        public int popMiddle() {
            if (size == 0) {
                return -1;
            } else if (size == 1) {
                int ans = front.data;
                front = rear = null;
                size = 0;
                return ans;
            }
            Node middle = front;
            for (int i = 0; i < (size - 1) / 2; i++) {
                middle = middle.next;
            }
            int val = middle.data;
            if (middle.prev != null) {
                middle.prev.next = middle.next;

            }
            if (middle.next != null) {
                middle.next.prev = middle.prev;
            }
            size--;
            if (size == 1) {
                front = rear;
            }
            return val;

        }

        public int popBack() {
            if (size == 0) {
                return -1;
            }
            int temp = rear.data;
            rear = rear.prev;
            if (rear != null) {
                rear.next = null;
            } else {
                front = null;
            }
            size--;
            return temp;
        }
    }

    public int canCompleteCircuit_134(int[] gas, int[] cost) {
        int start = 0;
        int balance = 0;
        int need = 0;
        for (int i = 0; i < gas.length; i++) {
            balance += gas[i] - cost[i];
            if (balance < 0) {
                need += balance;
                start = i + 1;
                balance = 0;
            }
        }
        if (balance + need >= 0) {
            return start;
        }
        return -1;
    }

    public List<List<Integer>> levelOrderBottom_107(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, 0, ans);
        Collections.reverse(ans);
        return ans;
    }

    public void dfs(TreeNode node, int depth, List<List<Integer>> ans) {
        if (node == null) return;
        if (depth == ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(depth).add(node.val);
        dfs(node.left, depth + 1, ans);
        dfs(node.right, depth + 1, ans);
    }

    int max_sum = Integer.MIN_VALUE;
    int nodes = 0;

    public void summing(TreeNode root, int sum, int length) {
        if (root == null) {
            if (length > nodes) {
                nodes = length;
                max_sum = sum;
            } else if (length == nodes) {
                max_sum = Math.max(max_sum, sum);
            }
            return;
        }
        sum += root.val;
        summing(root.left, sum, length + 1);
        summing(root.right, sum, length + 1);
    }

    public int sumOfLongRootToLeafPath(TreeNode root) {
        summing(root, 0, 0);
        return max_sum;
    }

    int sum = 0;

    public void suming(TreeNode root, int no) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            no += root.val;
            sum += no;
            return;
        }
        no += root.val;
        suming(root.left, no * 10);
        suming(root.right, no * 10);
    }

    public int sumNumbers_129(TreeNode root) {
        suming(root, 0);
        return sum;
    }

    List<List<Integer>> ans = new ArrayList<>();

    public void pathsum2(TreeNode root, int target, int sum, List<Integer> nos) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            sum += root.val;
            if (sum == target) {
                nos.add(root.val);
                ans.add(new ArrayList<>(nos));
                nos.remove(nos.size() - 1);
            }

            return;
        }
        sum += root.val;
        nos.add(root.val);
        pathsum2(root.left, target, sum, nos);
        pathsum2(root.right, target, sum, nos);
        nos.remove(nos.size() - 1);
    }

    public List<List<Integer>> pathSum_113(TreeNode root, int targetSum) {
        pathsum2(root, targetSum, 0, new ArrayList<>());
        return ans;
    }

    public TreeNode lowestCommonAncestor_236(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();
        checking(root, p, a1);
        checking(root, q, a2);
        while (!a1.isEmpty() && !a2.isEmpty()) {
            if (a1.get(a1.size() - 1) == a2.get(a2.size() - 1)) {
                return new TreeNode(a1.get(a1.size() - 1));
            }
            a1.remove(a1.size() - 1);
            a2.remove(a2.size() - 1);
        }
        return null;

//        if(root==null || root==p || root==q){
//            return root;
//        }
//        TreeNode left=lowestCommonAncestor_236(root.left,p,q);
//        TreeNode right=lowestCommonAncestor_236(root.right,p,q);
//        if(left!=null && right!=null){
//            return root;
//        }
//        return left==null?right:left;
    }

    public boolean checking(TreeNode root, TreeNode data, ArrayList<Integer> a1) {
        if (root == null) {
            return false;
        }
        if (root == data) {
            a1.add(root.val);
            return true;
        }
        boolean b1 = checking(root.left, data, a1);
        boolean b2 = checking(root.right, data, a1);
        if (b1) {
            a1.add(root.val);
        }
        if (b2) {
            a1.add(root.val);
        }
        return false;
    }
    int count = 0;
    public void values_path(TreeNode root, int targetsum, ArrayList<Integer> nos) {
        if (root == null) {
            return;
        }
        nos.add(root.val);
        long sum = 0;
        for (int i = nos.size() - 1; i >= 0; i--) {
            sum += nos.get(i);
            if (sum == targetsum) {
                count++;
            }
        }
        values_path(root.left, targetsum, nos);
        values_path(root.right, targetsum, nos);
        nos.remove(nos.size() - 1);
    }
    public int pathSum_437(TreeNode root, int targetSum) {
            ArrayList<Integer> nos=new ArrayList<>();
            values_path(root,targetSum,nos);
            return count;
    }

    public TreeNode tracking(TreeNode root,int k,int node){
        if (root==null){
            return null;
        }
        if (root.val==node){
            return root;
        }
        TreeNode left=tracking(root.left,k,node);
        TreeNode right=tracking(root.right,k,node);
        if (left!=null || right!=null){
            k--;
        }
        if (k==0){
            return root;
        }
        else if(left!=null){
            return left;
        }
        else if(right!=null){
            return right;
        }
        return null;
    }
    public int kthAncestor(Node root, int k, int node) {

    }

}

