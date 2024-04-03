import com.sun.source.tree.Tree;

import java.util.*;
 class ListNode {
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
class TreeNode{
     TreeNode left;
     TreeNode right;
     int val;

     TreeNode(int value){
         this.val=value;
         this.left=null;
         this.right=null;
     }
}
public class easy_questions {
    List<String> paths = new ArrayList<>();
    public static int[] twoSum_1(int[] nums, int target) {
        //1st method
//        HashMap<Integer,Integer> no_set=new HashMap<>();
//        for(int i=0;i<nums.length;i++){
//            no_set.put(nums[i],i);
//        }
//
//        for(int i=0;i<nums.length;i++){
//            int remain = target-nums[i];
//
//            if(no_set.containsKey(remain) && no_set.get(remain)!=i){
//                return new int[]{i,no_set.get(remain)};
//            }
//        }
//        return new int[]{};

        //2nd method
        HashMap<Integer, Integer> no_Set = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int comp = target - nums[i];

            if (no_Set.containsKey(nums[i])) {
                return new int[]{no_Set.get(comp), i};
            }

            no_Set.put(nums[i], i);
        }
        return new int[]{};
    }
    public static int remove_duplicates_from_sorted_array_2(int nums[]) {
        //1st method
        //        int i = 0;
        // for (int n : nums)
        //     if (i < 2 || n > nums[i-2])
        //         nums[i++] = n;
        // return i;

        //2nd method
        int j = 1;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[j - 1]) {
                nums[++j] = nums[i];
            }
        }
        return j + 1;


    }
    public static boolean palindrome(int num) {
        int num2 = 0;
        int temp = num;
        while (num != 0) {
            int rem = num % 10;
            num2 = num2 * 10 + rem;
            num = num / 10;
        }
        return temp == num2;
    }
    public static int roman_to_integer(String romans) {
        HashMap<Character, Integer> alpha = new HashMap<>();

        alpha.put('I', 1);
        alpha.put('V', 5);
        alpha.put('X', 10);
        alpha.put('L', 50);
        alpha.put('C', 100);
        alpha.put('D', 500);
        alpha.put('M', 1000);
        int no = 0;
        for (int i = 0; i < romans.length(); ++i) {
            if (i < romans.length() - 1 && alpha.get(romans.charAt(i)) < alpha.get(romans.charAt(i + 1))) {
                no -= alpha.get(romans.charAt(i));
            } else {
                no += alpha.get(romans.charAt(i));
            }
        }

        return no;
    }
    public static int sliding_window_technique(int[] no, int k) {
        int current_sum = 0;
        for (int i = 0; i < k; i++) {
            current_sum = current_sum + no[i];
        }
        int highest_sum = Integer.MIN_VALUE;
        for (int j = k; j < no.length; j++) {
            current_sum = current_sum + no[j] - no[j - k];

            highest_sum = Math.max(highest_sum, current_sum);
        }

        return highest_sum;
    }
    public static String longest_common_prefix(String[] strs) {
        Arrays.sort(strs);
        String first_word = strs[0];
        String last_word = strs[strs.length - 1];
        String the_longest = "";
        for (int i = 0; i < first_word.length(); i++) {
            if (first_word.charAt(i) != last_word.charAt(i)) {
                break;
            }
            the_longest += first_word.charAt(i);
        }

        return the_longest;

    }
    public static boolean valid_parantheses(String s) {
        Stack<Character> open_brac = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                open_brac.push(s.charAt(i));
            } else {
                if (open_brac.isEmpty()) {
                    return false;
                } else if (s.charAt(i) == ')' && open_brac.peek() == '(') {
                    open_brac.pop();
                } else if (s.charAt(i) == ']' && open_brac.peek() == '[') {
                    open_brac.pop();
                } else if (s.charAt(i) == '}' && open_brac.peek() == '{') {
                    open_brac.pop();
                } else {
                    return false;
                }
            }
        }

        return open_brac.isEmpty();
    }
    public static ListNode merge_two_sorted_arry21(ListNode list1, ListNode list2) {
        ListNode dommi = new ListNode(-1);
        ListNode temp1 = list1;
        ListNode temp2 = list2;
        ListNode temp = dommi;
        if (temp1 == null && temp2 == null) return null;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }

            temp = temp.next;
        }

        if (temp1 == null) temp.next = temp2;
        else temp.next = temp1;
        return dommi.next;

    }
    public static int remove_duplicates_26(int num[]) {
        int r = 0;
        for (int i = 1; i < num.length; i++) {
            if (num[r] != num[i]) {
                r++;
                num[r] = num[i];
            }
        }
        return r + 1;
    }
    public static int remove_elements_27(int nums[], int val) {
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }

        return index;
    }
    public static int find_the_index_of_the_first_occurrence(String haystack, String needle) {
        int i = 0;

        if (needle.length() > haystack.length()) {
            return -1;
        }
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int count = 0;

                for (int w = 0, j = i; w < needle.length(); w++, j++) {
                    if (j >= haystack.length() || haystack.charAt(j) != needle.charAt(w)) break;
                    count++;
                }
                System.out.println(count);
                if (count == needle.length()) {
                    return i;
                }
            }
            i++;
        }
        return -1;

//        int hLen = haystack.length();
//        int nLen = needle.length();
//        int nIndex = 0;
//        for(int i=0; i<hLen; i++){
//            System.out.println("after for:" + i);
//            // as long as the characters are equal, increment needleIndex
//            if(haystack.charAt(i)==needle.charAt(nIndex)){
//                nIndex++;
//            }
//            else{
//                // start from the next index of previous start index
//                i=i-nIndex;
//                // needle should start from index 0
//                nIndex=0;
//            }
//            System.out.println(i);
//            // check if needleIndex reached needle length
//            if(nIndex==nLen){
//                // return the first index
//                return i-nLen+1;
//            }
//        }
//        return -1;
    }
    public static int search_insert_position(int[] nums, int target) {

        //binary search
        int last = nums.length - 1;
        int first = 0;


        while (first <= last) {
            int mid = (first + last) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                first = mid + 1;
            } else {
                last = mid - 1;
            }
        }
        return first;
    }
    public static int lengthOfLastWord(String s) {
// brute force
//        Stack<Integer>  word_no=new Stack<>();
//        int count=0;
//        for (int i=0;i<s.length();i++){
//
//            if((s.charAt(i)>='a' && s.charAt(i)<='z') || (s.charAt(i)>='A' && s.charAt(i)<='Z')){
//                count++;
//            }
//            else {
//                if(count==0){
//                    continue;
//                }
//                word_no.push(count);
//                count = 0;
//            }
//            if((i==s.length()-1) && (s.charAt(i)!=' ')){
//                word_no.push(sq);
//            }
//
//        }
//
//        return word_no.peek();

        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (i == s.length() - 1) {
                while (s.charAt(i) == ' ') {
                    i--;
                }
            }
            if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')) {
                count++;
            }
            if (s.charAt(i) == ' ') {
                break;
            }
        }

        return count;
    }
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;

        }

        digits = new int[digits.length + 1];
        digits[0] = 1;

        return digits;
    }
    public static String addBinary(String a, String b) {
        int lenA = a.length() - 1;
        int lenb = b.length() - 1;
        int carry = 0;
        StringBuilder the_final = new StringBuilder();

        while (lenb >= 0 || lenA >= 0 || carry != 0) {
            int digita = lenA >= 0 ? a.charAt(lenA) - '0' : 0;
            int digib = lenb >= 0 ? b.charAt(lenA) - '0' : 0;

            int sum = digita + digib + carry;
            carry = sum / 2;
            the_final.insert(0, sum % 2);
            lenb--;
            lenA--;
        }
        return the_final.toString();

    }
    public static int mySqrt(int x) {
        long start = 1;
        long end = x;

        long ans = -1;
        while (start <= end) {
            long mid = start + end / 2;
            long square = mid * mid;
            if (square == (long) x) {
                return (int) mid;
            } else if (square < (long) x) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }

        }

        return (int) ans;
//
//        int start=1;
//        int end=x;
//
//        int ans=-1;
//        while(start<=end){
//            int mid=(start+end)/2;
//            int square=mid*mid;
//            if(square==x){
//                return (mid);
//            }
//
//            else if(square<x){
//                ans=mid;
//                start=mid+1;
//            }
//
//            else{
//                end=mid-1;
//            }
//            System.out.println(ans);
//        }
//
//        return ans;

    }
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        ListNode temp1 = temp.next;

        while (temp1 != null) {
            if (temp.val != temp1.val) {
                temp.next = temp1;
                temp = temp.next;
            }

            temp1 = temp1.next;
        }

        temp.next = null;

        return head;

    }
    public static int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
    public static int climbstair(int target, int[] arr) {
//        if(n==target){
//            return 1;
//        }
//        if(n>target){
//            return 0;
//        }
//        n=climbstair(n+1,target)+climbstair(n+2,target);
//        return n;

        if (target == 0 || target == 1) {
            return 1;
        }
        if (arr[target] != -1) {
            return arr[target];
        }
        arr[target] = climbstair(target - 1, arr) + climbstair(target - 2, arr);
        return climbstair(target - 1, arr) + climbstair(target - 2, arr);
    }
    public static int climbStairs_70(int n) {
//        if (n == 1) {
//            return 1;
//        }
//        int[] arr = new int[n + 1];
//        arr[1] = 1;
//        arr[2] = 2;
//
//        for (int i = 3; i <= n; i++) {
//            arr[i] = arr[i - 1] + arr[i - 2];
//        }
//
//        return arr[n];
        int[] arr = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            arr[i] = -1;
        }
        return climbstair(n, arr);
    }
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int total_index = (m + n) - 1;
        int end_index_of_1starray = m - 1;
        int end_index_of_2ndarray = n - 1;

        while (end_index_of_2ndarray >= 0) {
            if (((end_index_of_1starray >= 0) && (nums2[end_index_of_2ndarray]) < (nums1[end_index_of_1starray]))) {
                nums1[total_index] = nums1[end_index_of_1starray];
                end_index_of_1starray--;

            } else {

                nums1[total_index] = nums2[end_index_of_2ndarray];
                end_index_of_2ndarray--;
            }

            total_index--;
        }
    }
    public static ListNode mergeTwoLinkedlist(ListNode list1, ListNode list2) {
        ListNode domi = new ListNode(-1);
        ListNode temp = domi;
        ListNode list1temp = list1;
        ListNode list2temp = list2;
        if (list1temp == null && list2temp == null) {
            return null;
        }
        while (list1temp != null && list2temp != null) {
            if (list1temp.val <= list2temp.val) {
                temp.next = list1temp;
                list1temp = list1temp.next;
            } else {
                temp.next = list2temp;
                list2temp = list2temp.next;
            }


            temp = temp.next;
        }

        if (list1temp == null) {
            temp.next = list2temp;
        } else {
            temp.next = list1temp;
        }

        return domi.next;
    }
    public static boolean hasCycle(ListNode head) {

        if (head == null) {
            return false;
        }
        ListNode hare = head;
        ListNode tortoise = head;

        while (hare != null && hare.next != null) {
            hare = hare.next.next;
            tortoise = tortoise.next;
            if (hare == tortoise) {
                return true;
            }

        }
        return false;
    }
    public static int LinkedListlength(ListNode head) {
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*
        HashSet approach
        if(headA==null || headB==null){
            return null;
        }
        HashSet<ListNode> list_nodes_of_lista=new HashSet<>();
        while(headA!=null){
            list_nodes_of_lista.add(headA);
            headA=headA.next;
        }
        while(headB!=null){
            if(list_nodes_of_lista.contains(headB)){
                return headB;
            }
            headB=headB.next;
        }

        return null;
        */

        // knowing lenght function.
        int length_of_list1 = LinkedListlength(headA);
        int length_of_list2 = LinkedListlength(headB);
        while (length_of_list1 > length_of_list2) {
            headA = headA.next;
            length_of_list1--;
        }
        while (length_of_list2 > length_of_list1) {
            headB = headB.next;
            length_of_list2--;
        }
        while (headA != headB) {
            headB = headB.next;
            headA = headA.next;
        }
        return headA;
    }
    public static ListNode removeElements(ListNode head, int val) {
//        ListNode domi=new ListNode();
//        domi.next=head;
//        if(head==null){
//            return null;
//        }
//        ListNode temp=domi;
//        while(temp.next!=null){
//            if(temp.next.value==val){
//                temp.next=temp.next.next;
//            }
//            else{
//                temp=temp.next;
//            }
//        }
//        return domi.next;

        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
    public static boolean isPalindrome(ListNode head) {
        /*
        if(head==null){
            return false;
        }
        ArrayList<Integer> rever=new ArrayList<>();
        ListNode temp=head;
        while(temp!=null){
            rever.add(temp.value);
            temp=temp.next;
        }

        Collections.reverse(rever);

        for (Integer integer : rever) {
            if (integer != head.value) {
                return false;
            }

            head = head.next;
        }

        return true;
        */

        /*
        if(head==null)return false;

        Stack<Integer> no=new Stack<>();
        ListNode temp=head;

        while (temp!=null){
            no.push(temp.val);
            temp=temp.next;
        }

        while(no.size()/2!=0){
            if(no.peek()!=head.val){
                return false;
            }

            no.pop();
            head=head.next;
        }

        return true;
        */
        return true;
    }
    public static String reverseString(String s) {
        StringBuilder rev = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            rev.append(s.charAt(i));
        }

        return rev.toString();
    }
    public static boolean validPalindrome(String s) {
        /*
        checking method
        StringBuilder original= new StringBuilder();
        for(int i=0;i<s.length();i++){
            if((s.charAt(i)>='a' && s.charAt(i)<='z') || (s.charAt(i)>='A' && s.charAt(i)<='Z')|| (s.charAt(i)>='0' && s.charAt(i)<='9')){
                original.append(s.charAt(i));
            }
        }

        String rev2= original.toString().toLowerCase();
        String rev1=reverseString(rev2);
        return rev1.equals(rev2);
        */

        //two pointer method
        int start = 0;
        int endno = s.length() - 1;
        while (start <= endno) {
            char currFirst = s.charAt(start);
            char currLast = s.charAt(endno);
            if (!Character.isLetterOrDigit(currFirst)) {
                start++;
            } else if (!Character.isLetterOrDigit(currLast)) {
                endno--;
            } else {
                if (Character.toLowerCase(currFirst) != Character.toLowerCase(currLast)) {
                    return false;
                }
                start++;
                endno--;
            }
        }

        return true;
    }
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Hashtable<Integer, Integer> arrays_val = new Hashtable<>();
        for (int i = 0; i < nums.length; i++) {
            if (arrays_val.containsKey(nums[i])) {
                int j = arrays_val.get(nums[i]);
                if (Math.abs(i - j) <= k) {
                    return true;
                }
            }
            if (arrays_val.containsKey(nums[i]) && arrays_val.get(nums[i]) != i) {
                arrays_val.put(nums[i], i);
            } else {
                arrays_val.put(nums[i], i);
            }
        }
        return false;
    }
    public static void leastnodivisible(int no) {
        Stack<Integer> nos = new Stack<>();
        for (int i = 9; i >= 2; i--) {
            if (no == 1) {
                break;
            }
            if (no % i == 0) {
                while (no % i == 0) {
                    no = no / i;
                    nos.push(i);
                }
            }

        }
        if (no != 1) {
            System.out.println(-1);
            return;
        }
        while (!nos.isEmpty()) {
            System.out.print(nos.pop());
        }
    }
    //    public static char rat_food(int r, int unit, int arr[], int n){
//        int total_food=r*unit;
//        int sum=0;
//        if(arr==null){
//            return -1;
//        }
//        for(int i=0;i<n;i++){
//            sum+=arr[i];
//            if(sum>=total_food){
//
//                return i+1;
//            }
//        }
//        return 0;
//    }
    public static char forA(char p, char a) {
        if (p == '1' && a == '1') return '1';
        else return '0';
    }
    public static char forB(char p, char a) {
        if (p == '0' && a == '0') return '0';
        else return '1';
    }
    public static char forC(char p, char a) {
        if ((p == '1' && a == '1') || (p == '0' && a == '0')) return '0';
        else return '1';
    }
    public static char OperationsBinaryString(String str) {
        char prev = str.charAt(0);
        char res;
        for (int i = 1; i < str.length() - 1; i += 2) {
            if (str.charAt(i) == 'A') {
                res = forA(prev, str.charAt(i + 1));

            } else if (str.charAt(i) == 'B') {
                res = forB(prev, str.charAt(i + 1));

            } else {
                res = forC(prev, str.charAt(i + 1));

            }
            prev = res;
        }
        return prev;
    }
    public static void inorder_Traversal_binary(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }
        inorder_Traversal_binary(root.left, values);
        values.add(root.val);
        inorder_Traversal_binary(root.right, values);

    }
    public static List<Integer> inorderTraversal_94(TreeNode root) {
        List<Integer> thevalues = new ArrayList<>();
        inorder_Traversal_binary(root, thevalues);
        return thevalues;
    }
    public static int differenceofSum(int n, int m) {
        int sum_n = 0;
        int sum_m = 0;

        for (int i = 1; i <= m; i++) {
            if (i % n == 0) {
                sum_n += i;
            } else {
                sum_m += i;
            }
        }

        return sum_m - sum_n;
    }
    public static ArrayList<Integer> subarraySum(int[] arr, int n, int s) {

        ArrayList<Integer> index = new ArrayList<>();
        int i = 0;
        int j = 0;
        if (s == 0) {
            index.add(-1);
            return index;
        }
        while (j < n) {
            int sum = 0;
            for (int w = i; w <= j; w++) {
                sum += arr[w];
            }
            if (sum == s) {
                index.add(i + 1);
                index.add(j + 1);
                return index;
            }
            if (sum < s) {
                j++;
            } else {
                i++;
            }
        }
        index.add(-1);
        return index;
//      ArrayList<Integer> index=new ArrayList<>();
//      int i=0;
//      int j=0;
//      if(s==0){
//          index.add(-1);
//          return index;
//      }
//      int sum=arr[j];
//      while(j<n){
//
//          if(sum==s){
//              index.add(i+1);
//              index.add(j+1);
//              return index;
//          }
//          if(sum<s){
//              j++;
//              sum+=arr[j];
//
//
//          }
//          else {
//              sum-=arr[i];
//              i++;
//          }
//      }
//      index.add(-1);
//      return index;
    }
    public static ArrayList<Integer> subarraysum1(int[] arr, int n, int s) {
        ArrayList<Integer> nos = new ArrayList<>();
        if (s == 0) {
            nos.add(-1);
            return nos;
        }
        int start = 0;
        int ending = 0;
        int sum = 0;
        while (ending < n) {
            sum += arr[ending];
            while (sum >= s) {
                if (sum == s) {
                    nos.add(start + 1);
                    nos.add(ending + 1);
                    return nos;
                }
                sum -= arr[start];
                start++;
            }
            ending++;
        }
        nos.add(-1);
        return nos;
    }
    public static int countNodes_222(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left_count = countNodes_222(root.left);
        int right_count = countNodes_222(root.right);
        return left_count + right_count + 1;
    }
    public static int maxDepth_104(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left_count = maxDepth_104(root.left);
        int right_count = maxDepth_104(root.right);
        return Math.max(left_count, right_count) + 1;
    }
    public static void post_order_binary_tree(TreeNode root, List<Integer> nos) {
        if (root == null) {
            return;
        }
        post_order_binary_tree(root.left, nos);
        post_order_binary_tree(root.right, nos);
        nos.add(root.val);
    }
    public static List<Integer> postorderTraversal_145(TreeNode root) {
        List<Integer> nos = new ArrayList<>();
        post_order_binary_tree(root, nos);
        return nos;
    }
    public static int diameter(TreeNode root, int[] no) {
        if (root == null) {
            return 0;
        }

        int left_side_height = diameter(root.left, no);
        int right_side_height = diameter(root.right, no);
        no[0] = Math.max(no[0], left_side_height + right_side_height);
        return 1 + Math.max(left_side_height, right_side_height);

    }
    public static int diameterOfBinaryTree(TreeNode root) {
        int[] nos = new int[1];
        diameter(root, nos);
        return nos[0];

    }
    public static boolean is_identical(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        if (root.val == subRoot.val) {
            return is_identical(root.left, subRoot.left) && is_identical(root.right, subRoot.right);
        }
        return false;
    }
    public static int[] prefix_sum(int[] arr) {
        int len = arr.length;
//        int[] prefix=new int[len];
//        prefix[0]=arr[0];
//        for(int i=1;i<len;i++){
//            prefix[i]=prefix[i-1]+arr[i];
//        }
        //without using new array

        for (int i = 1; i < len; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }

        return arr;
    }
    public static int[] suffix_sum(int[] aar) {
//        int [] suf=new int[aar.length];
//        suf[aar.length-1]=aar[aar.length-1];
//        for (int i=aar.length-2;i>=0;i--){
//            suf[i]=aar[i]+suf[i+1];
//        }
//        return suf;

        for (int i = aar.length - 2; i >= 0; i--) {
            aar[i] = aar[i] + aar[i + 1];
        }
        return aar;
    }
    public static int prefix_sume_between_range(int[] arr, int l, int r) {
        int[] sums = prefix_sum(arr);
        return sums[r] - sums[l - 1];
    }
    public static boolean isSubtree_527(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (root.val == subRoot.val) {
            if (is_identical(root, subRoot)) {
                return true;
            }
        }
        return isSubtree_527(root.left, subRoot) || isSubtree_527(root.right, subRoot);
    }
    public static int search_binary_704(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int middle = start + (end - start) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                start = middle + 1;

            } else {
                end = middle - 1;
            }
        }
        return -1;
    }
    public static void preorder(List<Integer> nos, TreeNode root) {
        if (root == null) {
            return;
        }
        nos.add(root.val);
        preorder(nos, root.left);
        preorder(nos, root.right);
    }
    public List<Integer> preorderTraversalbinarytree_144(TreeNode root) {
        List<Integer> nos = new ArrayList<>();
        preorder(nos, root);
        return nos;
    }
    public static void rangeSumBST1(TreeNode root, int low, int high, List<Integer> nos) {
        if (root == null) {
            return;
        }
        if (root.val >= low && root.val <= high) {
            rangeSumBST1(root.left, low, high, nos);
            nos.add(root.val);
            rangeSumBST1(root.right, low, high, nos);
        } else if (root.val < low) {
            rangeSumBST1(root.right, low, high, nos);
        } else {
            rangeSumBST1(root.left, low, high, nos);
        }
    }
    public static int rangeSumBst(TreeNode root, int low, int high) {
        List<Integer> nos = new ArrayList<>();
        int sum = 0;
        rangeSumBST1(root, low, high, nos);
        for (Integer no : nos) {
            sum += no;
        }
        return sum;
    }
    public int rangeSumBST_938(TreeNode root, int low, int high) {
        //efficient process
        int sum = 0;
        if (root == null) {
            return 0;
        }
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }
        if (root.val > low) {
            sum += rangeSumBST_938(root.left, low, high);
        }
        if (root.val < high) {
            sum += rangeSumBST_938(root.right, low, high);
        }
        return sum;
    }
    public static int firstUniqChar_387(String s) {
        HashMap<Character, Integer> apperance = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            apperance.put(s.charAt(i), apperance.getOrDefault(s.charAt(i), 0) + 1);
        }
        System.out.println(apperance);
        for (int i = 0; i < s.length(); i++) {
            if (apperance.containsKey(s.charAt(i)) && apperance.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
    public static boolean uniqueOccurrences_1207(int[] arr) {
        Map<Integer, Integer> nos = new HashMap<>();
        for (int no : arr) {
            if (nos.containsKey(no)) {
                nos.put(no, nos.get(no) + 1);
            } else {
                nos.put(no, 1);
            }
        }
        ArrayList<Integer> ind = new ArrayList<>(nos.values());
        Collections.sort(ind);

        for (int i = 0; i < ind.size() - 1; i++) {
            if (ind.get(i) == ind.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    public static int[] intersection_349(int[] nums1, int[] nums2) {
        Set<Integer> array_nos = new HashSet<>();
        ArrayList<Integer> the_val = new ArrayList<>();
        int count = 0;
        for (int i : nums1) {
            array_nos.add(i);
        }
        for (int i : nums2) {
            if (array_nos.contains(i)) {
                the_val.add(i);
                array_nos.remove(i);
                count++;
            }
        }
        int[] no = new int[count];

        for (int i = 0; i < count; i++) {
            no[i] = the_val.get(i);
        }
        return no;
    }
    public static int[] intersect_350(int[] nums1, int[] nums2) {
        ArrayList<Integer> array_nos = new ArrayList<>();
        ArrayList<Integer> the_val = new ArrayList<>();
        int count = 0;
        for (int i : nums1) {
            array_nos.add(i);
        }
        for (int i : nums2) {
            if (array_nos.contains(i)) {
                the_val.add(i);
                array_nos.remove(array_nos.indexOf(i));

                count++;
            }
        }
        int[] no = new int[count];

        for (int i = 0; i < count; i++) {
            no[i] = the_val.get(i);
        }
        return no;
    }
    public boolean isIsomorphic_205(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> matric = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char s_char = s.charAt(i);
            char t_char = t.charAt(i);

            if (!matric.containsKey(s_char)) {
                if (!matric.containsValue(t_char)) {
                    matric.put(s_char, t_char);
                } else {
                    return false;
                }
            } else {
                char temp_char = matric.get(s_char);
                if (temp_char != t_char) {
                    return false;
                }
            }
        }
        return true;
    }
    public static List<List<Integer>> generate_118(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) {
            return result;
        }
        List<Integer> first = new ArrayList<>();
        first.add(1);
        result.add(first);
        if (numRows == 1) {
            return result;
        }
        for (int j = 1; j < numRows; j++) {
            List<Integer> temp = result.get(j - 1);

            ArrayList<Integer> curr = new ArrayList<>();
            curr.add(1);

            for (int w = 0; w < j - 1; w++) {
                curr.add(temp.get(w) + temp.get(w + 1));
            }
            curr.add(1);

            result.add(curr);
        }
        return result;
    }
    public static List<Integer> getRow_119(int rowIndex) {
        List<List<Integer>> list_val = new ArrayList<>();
        List<Integer> final_key = new ArrayList<>();
        final_key.add(1);
        if (rowIndex == 0) return final_key;
        list_val.add(final_key);
        for (int i = 1; i <= rowIndex; i++) {
            List<Integer> curr = new ArrayList<>();
            List<Integer> temp = list_val.get(i - 1);
            curr.add(1);
            for (int j = 0; j < i - 1; j++) {
                curr.add(temp.get(j) + temp.get(j + 1));
            }
            curr.add(1);
            list_val.add(curr);
        }
        return list_val.get(rowIndex);
    }
    public static boolean subString(String s, String w) {
        if (s.length() < w.length()) {
            return false;
        }

        int s_length = s.length();
        int w_length = w.length();

        for (int i = 0; i <= s_length - w_length; i++) {
            boolean correct = true;

            for (int j = 0; j < w_length; j++) {
                if (s.charAt(i + j) != w.charAt(j)) {
                    correct = false;
                    break;
                }
            }
            if (correct) return true;
        }
        return false;
    }
    public static void decimal_binary(int no) {
        List<Integer> bina = new ArrayList<>();

        while (no != 0) {
            int rem = no % 2;
            bina.add(rem);
            no = no / 2;
        }
        for (int i = bina.size() - 1; i >= 0; i--) {
            System.out.print(bina.get(i));
        }
    }
    public static boolean is_prime_no(int no) {
        if (no == 1) {
            return true;
        }

        for (int i = 2; i <= no / 2; i++) {
            if (no % i == 0) {
                return false;
            }
        }

        return true;

    }
    public static ListNode insertion(ListNode root, int no) {
        ListNode temp = root;
        while (temp.next.val < no) {
            temp = temp.next;
        }
        ListNode the_node = new ListNode(no);
        the_node.next = temp.next;
        temp.next = the_node;


        return the_node;
    }
    public static void isSameTree(TreeNode p, TreeNode q) {
//        if(p==null){
//            return false;
//        }
//        if (q==null){
//            return true;
//        }
//        if(p.val==q.val){
//                if()
//        }
    }
    public List<String> binaryTreePaths(TreeNode root) {
        path_backtracking(root, "");
        return paths;
    }
    public void path_backtracking(TreeNode root, String s) {
        if (root == null) {
            return;
        }

        s += root.val + "->";

        path_backtracking(root.left, s);
        path_backtracking(root.right, s);


        if (root.left == null && root.right == null) paths.add(s.substring(0, s.length() - 2));
        return;
    }
    public static void reverse_string(String s) {
//        String[] w=s.split("\\.");
//        StringBuilder r= new StringBuilder();
//        for(int i=w.length-1;i>=0;i--){
//            System.out.println(w[i]);
//            r.append(w[i]);
//        }
//        System.out.println(r);
        List<String> words = new ArrayList<>();
        StringTokenizer w = new StringTokenizer(s, ".");
        while (w.hasMoreTokens()) {
            words.add(w.nextToken());
        }
//        System.out.println(words);
        StringJoiner v = new StringJoiner(".");
        for (int i = words.size() - 1; i >= 0; i--) {
            v.add(words.get(i));
        }

        String returned = v.toString();

        System.out.println(returned);
    }
    public static int maxProfit_121(int[] prices) {
        int buy_date = prices[0];
        int curr_profit = 0;
        int max_profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buy_date) {
                buy_date = prices[i];
            } else {
                curr_profit = prices[i] - buy_date;

                if (max_profit < curr_profit) {
                    max_profit = curr_profit;
                }
            }
        }
        return max_profit;
    }
    public static TreeNode in_order_successor(TreeNode root) {
        TreeNode temp = root;
        while (temp != null) {
            temp = temp.left;
        }
        return temp;
    }
    public static TreeNode delete_node(TreeNode root, int value) {
        if (root.val > value) {
            root.left = delete_node(root.left, value);
        } else if (root.val < value) {
            root.right = delete_node(root.right, value);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left == null) {
                return root.right;
            } else if (root.right == null)
                return root.left;

            TreeNode in_ord = in_order_successor(root.right);
            root.val = in_ord.val;
            root.right = delete_node(root.right, in_ord.val);

        }
        return root;

    }
    public static boolean hasUniqueCharacters(String s) {
        HashSet<Character> arr = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (!arr.add(s.charAt(i))) {

                return false;
            }
        }
        return true;
    }
    public static List<int[]> findPairs(int[] arr1, int[] arr2, int target) {
        HashSet<Integer> arr = new HashSet<>();
        List<int[]> answer = new ArrayList<>();
        for (int i : arr1) {
            arr.add(i);
        }
        for (int i : arr2) {
            int tar = target - i;
            if (arr.contains(tar)) {
                answer.add(new int[]{i, tar});
            }
        }

        return answer;

    }
    public static int find_missing_no(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i + 1] - arr[i] != 1) {
                return arr[i] + 1;
            }
        }
        return 0;
    }
    public static void shorted_array(int[] arr) {
        int i = 0;
        int[] arr1 = new int[arr.length];
        int temp = arr[0];
        for (int w : arr) {
            if (w != temp) {
                System.out.print(i);
                System.out.print(temp + " ");
                temp = w;
                i = 1;

            } else {
                i++;
            }

        }
        System.out.print(i);
        System.out.print(temp);
    }
    public static int longest_repeated_no(int[] arr) {
        int count = 1;
        int temp = arr[0];
        int longest = 0;
        for (int i : arr) {
            if (i != temp) {
                System.out.println(longest);
                longest = Math.max(count, longest);

                count = 1;
                temp = i;
            } else {
                count++;
            }
        }

        return longest;
    }
    public static int[] print_all_sub_array_of_size(int[] arr, int size) {
        if (arr.length == 0) return new int[]{};

        ArrayList<Integer> inte = new ArrayList<>();
        for (int i = 0; i <= arr.length - size; i++) {
            int sum = 0;
            for (int j = i; j < size + i; j++) {
                sum += arr[j];

            }
            inte.add(sum);
        }
        System.out.println(inte);
        Integer[] num = inte.toArray(new Integer[inte.size()]);

        int[] num1 = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            num1[i] = num[i];
        }
        return num1;
    }
    public static int no_of_sub_array_sum_equalto_k(int arr[], int sizee, int k, int lenghtof) {
        if (sizee == 0) return 0;
        int count = 0;
        for (int i = 0; i <= sizee - lenghtof; i++) {
            int sum = 0;
            for (int j = i; j < lenghtof + i; j++) {
                sum += arr[j];
            }
            if (sum == k) {
                count++;
            }
        }
        return count;
    }
    public static boolean isIdentical(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }

        return (root1.val == root2.val) && isIdentical(root1.left, root2.right) && isIdentical(root1.right, root2.left);
    }
    public static boolean isSymmetric_101(TreeNode root) {
        return isIdentical(root.left, root.right);
    }
    public TreeNode adding_nodes(int[] nums, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = adding_nodes(nums, start, mid - 1);
        root.right = adding_nodes(nums, mid + 1, end);
        return root;
    }
    public TreeNode sortedArrayToBST_108(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        return adding_nodes(nums, start, end);
    }
    public static int balancechecking(TreeNode root) {
        if (root == null) return 0;

        int left_side_no = balancechecking(root.left);
        if (left_side_no == -1)
            return -1;
        int right_side_no = balancechecking(root.right);
        if (right_side_no == -1)
            return -1;
        if (Math.abs(left_side_no - right_side_no) > 1) {
            return -1;
        }
        return Math.max(left_side_no, right_side_no) + 1;
    }
    public boolean isBalanced_110(TreeNode root) {
        return balancechecking(root) != -1;
    }
    public int minDepth_111(TreeNode root) {
//        if(root==null) {
//            return 0;
//        }
//
//        int left=minDepth(root.left);
//        int right=minDepth(root.right);
//
//        if(left==0 || right==0){
//            return Math.max(left,right)+1;
//        }
//        return Math.min(left,right)+1;

        if (root == null) {
            return 10000;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = minDepth_111(root.left);
        int right = minDepth_111(root.right);
        return Math.min(left, right) + 1;
    }
    public boolean checksumpath(TreeNode root, int sum, int target) {
        if (root == null) return false;
        sum += root.val;
        if (root.left == null && root.right == null) {
            return sum == target;
        }
        return checksumpath(root.left, sum, target) || checksumpath(root.right, sum, target);
    }
    public boolean hasPathSum_112(TreeNode root, int targetSum) {
        int sum = 0;
        return checksumpath(root, sum, targetSum);
    }
    public static int singleNumber_136(int[] nums) {
        int res = 0; //oxor with anyother number returns the same number.
        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];//^xor
        }
        return res;
    }
    public static int squaresum(int n) {
        int sum = 0;
        while (n > 0) {
            int rem = n % 10;
            sum += rem * rem;
            n = n / 10;
        }
        return sum;
    }
    public static boolean isHappy_202(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = squaresum(slow);
            fast = squaresum(squaresum(fast));
            if (slow == 1) {
                return true;
            }
        }
        while (slow != fast);
        return false;
    }
    public boolean isPowerOfTwo_231(int n) {
        if (n == 1) {
            return true;
        } else if (n % 2 != 0 || n == 0) {
            return false;
        }
        return isPowerOfTwo_231(n / 2);
    }
    static int n1 = 0;
    static int n2 = 1;
    static int n3;
    public static void printing_fibbonacii_series(int n) {
        if (n > 0) {
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
            System.out.print(n3 + " ");
            printing_fibbonacii_series(n - 1);
        }
//        int n1=0;
//        int n2=1;
//        int n3;
//        System.out.print(n1+" ");
//        System.out.print(n2+" ");
//        for(int i=0;i<n-2;i++){
//            n3=n1+n2;
//            n1=n2;
//            n2=n3;
//            System.out.print(n3+" ");
//        }
    }
    public static int cost_climbing_using_recursion_memoisation(int cost[], int n, int arr[]) {
        //create an array of the length of the cost array+1 and assing -1 to all the index of that array.
        if (n == 0) {
            return cost[0];
        }
        if (n == 1) {
            return cost[1];
        }
        if (arr[n] != -1) {
            return arr[n];
        }
        arr[n] = Math.min(cost_climbing_using_recursion_memoisation(cost, n - 1, arr), cost_climbing_using_recursion_memoisation(cost, n - 2, arr)) + cost[n];
        return arr[n];
    }
    public static int cost_Climbing_using_buttom_up_tabulation(int cost[], int stair_no) {
        int[] arr = new int[stair_no + 1];
        arr[0] = cost[0];
        arr[1] = cost[1];
        for (int i = 2; i < stair_no; i++) {
            arr[i] = Math.min(arr[i - 1], arr[i - 2]) + cost[i];
        }
        return Math.min(arr[stair_no - 1], arr[stair_no - 2]);
    }
    public static int cost_climbing_using_space_complexity(int[] cost, int stairno) {
        int stair0 = cost[0];
        int stair1 = cost[1];
        int stair_n = 0;
        for (int i = 2; i < stairno; i++) {
            stair_n = Math.min(stair0, stair1) + cost[i];
            stair0 = stair1;
            stair1 = stair_n;
        }
        return Math.min(stair0, stair1);
    }
    public int minCostClimbingStairs_746(int[] cost) {
        int len = cost.length;
        return cost_climbing_using_space_complexity(cost, len);
    }
    public static int meetings_rooms_252(int start[], int end[], int n) {
        int[][] nos = new int[n][2];
        for (int i = 0; i < n; i++) {
            nos[i][0] = start[i];
            nos[i][1] = end[i];
        }
        Arrays.sort(nos, (a, b) -> Integer.compare(a[1], b[1]));
        int count = 1;
        int ned = nos[0][1];

        for (int i = 1; i < nos.length; i++) {
            if (nos[i][0] > ned) {
                count++;
                ned = nos[i][1];
            }
        }
        return count;
    }
    public int minimumCost_2144(int[] cost) {
        Arrays.sort(cost);
        int temp = 1;
        int sum = 0;

        for (int i = cost.length - 1; i >= 0; i--) {
            if (temp % 3 == 0) {
                continue;
            } else {
                sum += cost[i];
            }
            temp++;
        }
        return sum;
    }
    public static int minimumDays_to_survive_in_island(int S, int N, int M) {
        int sundays = S / 7;
        int buying_days = S - sundays;
        int food_needed = S * M;
        int ans = 0;
        if (food_needed % N == 0) {
            ans = food_needed / N;
        } else {
            ans = (food_needed / N) + 1;
        }
        if (ans <= buying_days) {
            return ans;
        } else {
            return -1;
        }
    }
    public static long minCost(long arr[], int n) {
        PriorityQueue<Long> ropes = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            ropes.add(arr[i]);
        }
        long cost = 0;
        while (ropes.size() > 1) {
            long smallest = ropes.poll();
            long second_smallest = ropes.poll();

            long current_cost = smallest + second_smallest;

            cost += current_cost;
            ropes.add(current_cost);
        }
        return cost;
    }
    public static int HCF_of_two_no(int a, int b) {
        //conventional method
//        int min = Math.min(a, b);
//        for (int i = min; i >= 1; i--){
//            if (a % i == 0 && b % i == 0) {
//                return i;
//            }
//        }
//        return 1;

        // eclipse method

//        while (a!=b){
//            if(a>b)
//                a=a-b;
//            else
//                b=b-a;
//        }
//        return a;

        //efficient way

        while (a != 0 && b != 0) {
            if (a > b)
                a = a % b;
            else
                b = b % a;
        }
        if (a != 0) return a;
        return b;

    }
    public static int LCM_of_two_no(int a, int b) {
        //LCM *HCF=a*b

        return a * b / HCF_of_two_no(a, b);
    }
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double average = (double) sum / k;
        int end_index = k;

        while (end_index < nums.length) {
            int curr_start = end_index - k;
            sum = (sum - nums[curr_start]) + nums[end_index];
            double curr_avg = (double) sum / k;
            average = Math.max(average, curr_avg);
            end_index++;

        }
        return average;
    }
    public static ArrayList<ArrayList<Integer>> checking_tupple(int[] arr, int no) {
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
        HashMap<Integer, Integer> ans = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            ans.put(arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            int remain = arr[i] - no;

            if (ans.containsKey(remain) && ans.get(remain) != i) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(arr[i]);
                temp.add(remain);
                answer.add(temp);
            }
        }
        return answer;
    }
    public static TreeNode create_binary_search_tree(int no[]) {
        TreeNode root = null;
        for (int j : no) {
            root = insert_node(root, j);
        }
        return root;
    }
    public static int checking_the_end_value(ListNode root) {
        ListNode temp = root;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        return temp.val;
    }
    public static TreeNode insert_node(TreeNode root, int value) {
        TreeNode newNode = new TreeNode(value);
        if (root == null) {
            root = newNode;
            return root;
        }
        if (root.val < value) {
            root.right = insert_node(root.right, value);

        } else {
            root.left = insert_node(root.left, value);
        }

        return root;
    }
    public static void smallest_node(TreeNode root, ArrayList<Integer> ans) {
        if (root == null) {
            return;
        }
        smallest_node(root.left, ans);
        ans.add(root.val);
        smallest_node(root.right, ans);
    }
    public static void print_length_character(String[] names, int no) {
        for (String n : names) {
            if (n.length() == no) {
                System.out.println(n);
            }
        }
    }
    public boolean check_wheather_one(int n, int i) {
        int w = 1 << i - 1;
        return (w & n) != 0;
    }
    public int reverseBits_190(int n) {
        int ans = 0;
        for (int i = 1; i <= 32; i++) {
            if (check_wheather_one(n, i)) {
                int p = 1 << 32 - i;
                ans = n | ans;
            }
        }
        return ans;
    }
    public int hammingWeight_191(int n) {
        int count = 0;
        for (int i = 1; i <= 32; i++) {
            if (check_wheather_one(n, i)) {
                count++;
            }
        }
        return count;
    }
    public void multiples(int n, int m, ArrayList<Integer> multi) {
        multi.add(n);
        for (int i = n + 1; i <= m; i++) {
            if (i % n == 0) {
                multi.add(i);
            }
        }
    }
    public void non_multiples(int m, ArrayList<Integer> non_div, int n) {
        non_div.add(1);
        for (int i = 2; i <= m; i++) {
            if (i % n != 0) {
                non_div.add(i);
            }
        }
    }
    public int difference_in_sum(int n, int m) {
        ArrayList<Integer> multi = new ArrayList<>();
        ArrayList<Integer> not_div = new ArrayList<>();
        multiples(n, m, multi);
        non_multiples(m, not_div, n);
        int non_div_sum = 0;
        int div = 0;
        System.out.println(multi);
        System.out.println(not_div);
        for (int i = 0; i < not_div.size(); i++) {
            non_div_sum += not_div.get(i);
        }
        for (int i = 0; i < multi.size(); i++) {
            div += multi.get(i);
        }
        return non_div_sum - div;
    }
    public int LargeSmallSum(int[] arr) {
        ArrayList<Integer> lar = new ArrayList<>();
        ArrayList<Integer> small = new ArrayList<>();
        for (int i = 0; i < arr.length; i = i + 2) {
            lar.add(arr[i]);
        }
        for (int i = 1; i < arr.length; i = i + 2) {
            small.add(arr[i]);
        }
        Collections.sort(lar);
        Collections.sort(small);
        return lar.get(lar.size() - 2) + small.get(1);
    }
    public int no_of_carries(int[] arr1, int[] arr2) {
        int carry = 0;
        int count_arr1 = arr1.length - 1;
        int count_arr2 = arr2.length - 1;
        int count = 0;
        while (count_arr1 > -1 || count_arr2 > -1 || carry == 1) {
            int sum = 0;
            if (count_arr1 > -1) {
                sum += arr1[count_arr1];
                count_arr1--;

            }
            if (count_arr2 > -1) {
                sum += arr2[count_arr2];
                count_arr2--;
            }
            sum += carry;
            carry = sum / 10;
            if (carry != 0) {
                count++;
            }

        }

        return count;
    }
    public static int countCarry(Long A, Long B) {
        long r, sum;
        int count = 0;
        int carry = 0;
        int rem1 = 0;
        int rem2 = 0;
        while (A != 0 && B != 0) {
            rem1 = (int) (A % 10);
            rem2 = (int) (B % 10);
            r = rem1 + rem2 + carry;
            if (r > 9) {
                count++;
                carry = (int) (r / 10);
            } else {
                carry = 0;
            }
            A = A / 10;
            B = B / 10;
        }
        return count;
    }
    class MyStack {
        Queue<Integer> cr = null;

        public MyStack() {
            cr = new LinkedList<>();


        }

        public void push(int x) {
            cr.add(x);
            int n = cr.size();
            for (int i = 0; i < n - 1; i++) {
                cr.add(cr.peek());
                cr.poll();
            }
        }

        public int pop() {
            return cr.poll();
        }

        public int top() {
            return cr.peek();
        }

        public boolean empty() {
            return cr.isEmpty();
        }
        }
    public void backtrack_adding_values(List<List<Integer>> ans, int start, int[] no, List<Integer> temp) {
        ans.add(new ArrayList<>(temp));
        for (int i = start; i < no.length; i++) {
            temp.add(no[i]);
            backtrack_adding_values(ans, i + 1, no, temp);
            temp.remove(temp.size() - 1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack_adding_values(ans, 0, nums, new ArrayList<>());
        return ans;

    }
    public static long maximumSumSubarray(int K, ArrayList<Integer> Arr, int N) {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += Arr.get(i);
        }
        long max = sum;

        for (int j = N; j < K; j++) {
            sum += Arr.get(j);
            sum -= Arr.get(j - N);

            max = Math.max(sum, max);
        }

        return max;
    }
    public int sumCounts(List<Integer> nums) {
        return 0;
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
    public static int b_search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
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
    public static int[] reverse_array(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return arr;
    }
    public static int pivotIndex_724(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int sum2 = 0;
        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];
            System.out.println(sum2 + " " + sum);
            if (sum2 == sum) {
                return i;
            }
            sum2 += nums[i];
        }
        return -1;
    }
    public int mySqrt_69(int x) {
        int start = 0;
        int end = x;
        long mid = start + (end - start) / 2;
        int ans = -1;
        while (start <= end) {
            long prod = mid * mid;
            if (prod == x) {
                return (int) mid;
            }
            if (prod < x) {
                ans = (int) mid;
                start = (int) (mid + 1);
            } else {
                end = (int) (mid - 1);
            }
            mid = start + (end - start) / 2;
        }
        return ans;
    }
    public static void print_patterns(int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                System.out.print(" ");
                System.out.print('*');
            }
            System.out.println();
        }
    }
    public static void print_fibbomicci(int n) {
        int a = 0, b = 1;
        System.out.println(a);
        System.out.println(b);
        for (int i = 1; i <= n; i++) {
            int no = a + b;
            System.out.println(no);
            a = b;
            b = no;
        }
    }
    public int subtractProductAndSum_1281(int n) {
        int product = 1;
        int sum = 0;
        while (n != 0) {
            int temp = n % 10;
            product = product * temp;
            sum += temp;
            n = n / 10;
        }

        return product - sum;

    }
    public int bitwiseComplement_1009(int n) {
        if (n == 0) {
            return 1;
        }
        int m = n;
        int mask = 0;
        while (m != 0) {
            mask = (mask << 1) | 1;
            m = m >> 1;
        }
        int ans = (~n) & mask;
        return ans;
    }
    public boolean isPowerOfThree_326(int n) {
        for (int i = 0; i <= 30; i++) {
            long ans = (long) Math.pow(3, i);
            if (ans == n) {
                return true;
            }
        }
        return false;
    }
    public boolean isPowerOfFour_342(int n) {
        if (n == 1) {
            return true;
        }
        if (n % 4 != 0 || n <= 0) {
            return false;
        }
        return isPowerOfFour_342(n / 4);
    }
    public static int addDigits_258(int num) {
        if (num == 0) {
            return 0;
        }
        if (num / 10 == 0) {
            return num;
        }
        int sum = 0;
        while (num != 0) {
            int rem = num % 10;
            sum += rem;
            num = num / 10;
        }
        return addDigits_258(sum);
    }
    public static void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int heavy = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = heavy;
                }
            }
        }
    }
    public static ArrayList<Integer> removing(int[] nums) {
        ArrayList<Integer> no = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            return no;
        }
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                no.add(nums[i]);
            }
        }
        no.add(nums[n - 1]);
        return no;
    }
    public static int thirdMax_414(int[] nums) {
        sort(nums);

        ArrayList<Integer> ans = removing(nums);
        if (ans.size() == 2) {
            return ans.get(1);
        }
        if (ans.size() == 1) {
            return ans.get(0);
        }
        return ans.get(ans.size() - 3);
    }
    public static List<String> summaryRanges_228(int[] nums) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; ) {
            int start = i, end = i;
            while (end < nums.length - 1 && nums[end] + 1 == nums[end + 1]) {
                end++;
            }

            if (end > start) {
                ans.add(String.valueOf(nums[start]) + "->" + String.valueOf(nums[end]));
            } else {
                ans.add(String.valueOf(nums[start]));
            }
            i = end + 1;

        }
        return ans;
    }
    class MyQueue {
        Stack<Integer> input = new Stack<>();
        Stack<Integer> output = new Stack<>();

        public MyQueue() {

        }

        public void push(int x) {
            input.push(x);
        }

        public int pop() {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
            int ans = output.pop();
            while (!output.isEmpty()) {
                input.push(output.pop());
            }
            return ans;
        }
        public int peek() {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
            int ans = output.peek();
            while (!output.isEmpty()) {
                input.push(output.pop());
            }
            return ans;
        }

        public boolean empty() {
            return input.isEmpty();
        }
    }
    public boolean uniqueOccurrencess_1207(int[] arr) {
        HashMap<Integer, Integer> quantity = new HashMap<>();
        HashSet<Integer> ans = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (!quantity.containsKey(arr[i])) {
                quantity.put(arr[i], 1);
            } else {
                quantity.replace(arr[i], quantity.get(arr[i]) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : quantity.entrySet()) {
            int val = entry.getValue();
            if (ans.contains(val)) {
                return false;
            } else {
                ans.add(val);
            }

        }
        return true;
    }
    public static void reverseArray(ArrayList<Integer> arr, int m) {
        if (m == arr.size() - 1) {
            return;
        }
        int start = m + 1;
        int end = arr.size() - 1;

        while (start <= end) {
            int temp = arr.get(start);
            arr.set(start, arr.get(end));
            arr.set(end, temp);
            start++;
            end--;
        }
    }
    public static void moveZeroes_283(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int start = 0;

        while (start < nums.length - 1) {

            if (nums[start] == 0) {
                int second_pointer = start + 1;
                while (second_pointer < nums.length - 1 && nums[second_pointer] == 0) {
                    second_pointer++;
                }
                nums[start] = nums[second_pointer];
                nums[second_pointer] = 0;
            }
            start++;

        }
    }
    public void reverseString_344(char[] s) {
        int start = 0;
        int end = s.length - 1;
        while (start <= end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }

    }
    public static boolean checkPalindrome(String str) {
        if(str.length()==1){
            return true;
        }
        StringBuilder temp= new StringBuilder();
        StringBuilder temp1= new StringBuilder();
        int end=str.length()-1;
        while(end>=0){
            if((str.charAt(end)>='A' && str.charAt(end)<='Z') || (str.charAt(end)>='a' && str.charAt(end)<='z')){
                temp.append(str.charAt(end));
            } else if (str.charAt(end)>='0' && str.charAt(end)<='9') {
                temp.append(str.charAt(end));
            }
            end--;
        }
        end++;
        while(end<str.length()){
            if((str.charAt(end)>='A' && str.charAt(end)<='Z') || (str.charAt(end)>='a' && str.charAt(end)<='z')){
                temp1.append(str.charAt(end));
            } else if (str.charAt(end)>='0' && str.charAt(end)<='9') {
                temp1.append(str.charAt(end));
            }
            end++;
        }
//        System.out.println(temp1.toString().toLowerCase());
//        System.out.println(temp.toString().toLowerCase());
        str=str.replaceAll("[^a-zA-Z0-9]","");
        System.out.println(str);
        return temp.toString().equalsIgnoreCase(temp1.toString().toLowerCase());

    }
    public static List<Integer> addToArrayForm_989(int[] num, int k) {
        List<Integer> ans=new ArrayList<>();
        int array_count=num.length-1;
        int temp=k;
        int carry=0;
        while (array_count>=0 && temp!=0){
            int curr_val_ofArray=num[array_count];
            int curr_val_of_number=temp%10;
            int curr_sum=curr_val_of_number+curr_val_ofArray+carry;
            carry=curr_sum/10;
            ans.add(curr_sum%10);
            array_count--;
            temp=temp/10;
        }

        while (temp!=0){
            int curr_val_of_number=temp%10;
            int curr_sum=curr_val_of_number+carry;
            carry=curr_sum/10;
            ans.add(curr_sum%10);
            temp=temp/10;

        }
        while (array_count>-1){
            int curr_val=num[array_count];
            int curr_sum=curr_val+carry;
            carry=curr_sum/10;
            ans.add(curr_sum%10);
            array_count--;

        }
        if (carry==1){
            ans.add(1);
        }
        int a=10;
        Collections.reverse(ans);
        return ans;

    }
    public int get_hcf(int a,int b){
        //brute force
        // for(int i=a;i>=1;i--){
        //     if(a%i==0 && b%i==0){
        //         return i;
        //     }
        // }
        //euclidean algorithm
        // while(a!=b){
        //     if(a>b){
        //         a=a-b;
        //     }
        //     else{
        //         b=b-a;
        //     }
        // }

        //gabriel lame(optimal solution than Euclidean)
        while(a!=0 && b!=0){
            if(a>b){
                a=a%b;
            }
            else{
                b=b%a;
            }
        }
        return a==0?b:a;
    }
    public int findGCD_1979(int[] nums) {
        Arrays.sort(nums);
        int first=nums[0];
        int second=nums[nums.length-1];
        return get_hcf(first,second);
    }
    public static int fib_509(int n) {
        if(n==0 ){
            return 0;
        }
        
        else if(n==1){
            return 1;
        }

        return fib_509(n-1)+fib_509(n-2);
    }
    public static int array_sum(int[] nums,int size){
        if(size==0){
            return 0;
        }
        if(size==1){
            return nums[0];
        }
        int sum=array_sum(Arrays.copyOfRange(nums,1,size),nums.length);
        return sum+nums[0];
    }
    public static boolean binary_search_using_Recursion(int[] nums,int no,int start,int end){
        if(start>end){
            return false;
        }
        int mid=start+(end-start)/2;
        if(nums[mid]==no){
            return true;
        }
        if(nums[mid]>no){
            return binary_search_using_Recursion(nums,no,start,mid-1);
        }
        else  {
           return  binary_search_using_Recursion(nums,no,mid+1,end);
        }
    }
    static void swap(StringBuilder word,int start,int end){
        char temp=word.charAt(start);
        word.setCharAt(start,word.charAt(end));
        word.setCharAt(end,temp);
    }
    static void rev(StringBuilder S,int start,int end){
        if((start>end)|| (start==end)){
            return;
        }
        swap(S,start,end);
        System.out.println(S);
        rev(S,++start,--end);

    }
    static String revStr(String S) {
        StringBuilder sb=new StringBuilder(S);
        rev(sb,0,sb.length()-1);
        return sb.toString();
    }
    static int check_pelidrome_using_recursion(String S,int start,int end){
        if((start> end) || (start==end)){
            return 1;
        }
        if(S.charAt(start)!=S.charAt(end)){
            return 0;
        }
        return check_pelidrome_using_recursion(S,++start,--end);
    }
    static  int isPalindrome(String S) {
        return check_pelidrome_using_recursion(S,0,S.length()-1);
    }
    public static void insertion_using_recursion(int arr[],int size,int key){
        if(size==0 || size==1){
            return ;
        }
        if(key==size){
            return;
        }

        int count=key;
        int temp=arr[key];
        while(key>0 && temp<arr[key-1]){

            arr[key]=arr[key-1];
            key--;
        }
        arr[key]=temp;
        insertion_using_recursion(arr,size,count+1);
    }
    public static void insertionSort(int arr[], int n)
    {
        insertion_using_recursion(arr,n,1);
    }
    public static void merging_two_array(int[] arr,int start,int mid,int end){
        int iterator_for_first_array=start;
        int iterator_for_second_array=mid+1;
        int main_array_index_counter=0;
        int[] mergerd=new int[end-start+1];
        while (iterator_for_first_array<=mid && iterator_for_second_array<=end){
            if(arr[iterator_for_first_array]<=arr[iterator_for_second_array]){
                mergerd[main_array_index_counter]=arr[iterator_for_first_array];
                iterator_for_first_array++;
            }
            else {
                mergerd[main_array_index_counter]=arr[iterator_for_second_array];
                iterator_for_second_array++;
            }
            main_array_index_counter++;

        }
        while (iterator_for_first_array<=mid){
            mergerd[main_array_index_counter]=arr[iterator_for_first_array];
            iterator_for_first_array++;
            main_array_index_counter++;
        }

        while (iterator_for_second_array<=end){
            mergerd[main_array_index_counter]=arr[iterator_for_second_array];
            iterator_for_second_array++;
            main_array_index_counter++;
        }
        for (int i=0,j=start;i<mergerd.length;j++,i++){
            arr[j]=mergerd[i];
        }
    }
    public static void merge_sort(int[] arr, int start, int end){
    if(start>=end){
        return;
    }
    int mid=start+(end-start)/2;
    merge_sort(arr,start,mid);
    merge_sort(arr,mid+1,end);
    merging_two_array(arr,start,mid,end);
    }
    public static int partion_for_quick_sort(int[] arr,int start,int end){
        int piv=arr[end];
        int iterator_for_place=start-1;

        for(int i=start;i<end;i++){
            if(arr[i]<piv){
                iterator_for_place++;
                int temp=arr[iterator_for_place];
                arr[iterator_for_place]=arr[i];
                arr[i]=temp;
            }
        }
        iterator_for_place++;
        int temp=arr[iterator_for_place];
        arr[iterator_for_place]=piv;
        arr[end]=temp;
        return iterator_for_place;
    }
    public static void quick_sort(int[] arr,int start,int end){
        if(start<end){
            int pivot=partion_for_quick_sort(arr,start,end);
            quick_sort(arr,start,pivot-1);
            quick_sort(arr,pivot+1,end);
        }
    }
    public List<String> fizzBuzz_412(int n) {
        List<String> ans=new ArrayList<>();
        for(int i=1;i<=n;i++){
            if((i%3==0) && (i%5==0)){
                ans.add("FizzBuzz");
            }
            else if(i%3==0){
                ans.add("Fizz");
            }
            else if(i%5==0){
                ans.add("Buzz");
            }
            else{
                ans.add(String.valueOf(i));
            }
        }
        return ans;
    }
    public static long pairWithMaxSum(long arr[], long N) {
        List<List<Long>> sub_arrays=new ArrayList<>();
        for(int i=0;i<N-1;i++){

            for(int j=i+1;j<N;j++){
                List<Long> sub=new ArrayList<>();
                for (int k=i;k<=j;k++){
                    sub.add(arr[k]);
                }
                sub_arrays.add(sub);
            }

        }

        for (List<Long> ll:sub_arrays){
            Collections.sort(ll);
        }

        long ans=Integer.MIN_VALUE;
        for (List<Long> arrs:sub_arrays){
            long sums=arrs.get(0)+arrs.get(1);
            if(sums>ans){
                ans=sums;
            }
        }
        return ans;
    }
    public static void print_factors(int n){
        for(int i=1;i<=Math.sqrt(n);i++){
            if(n%i==0){
                System.out.println(i);
                if(i!=n/i){
                    System.out.println(n/i);
                }
            }
        }
    }
    public static void backtrack(String str,StringBuilder word,int start,ArrayList<String> ans){
        if(start!=0){
            String cur_ans=word.toString();
            ans.add(cur_ans);
        }

        for(int i=start;i<str.length();i++){
            word.append(str.charAt(i));
            backtrack(str,word,i+1,ans);
            word.deleteCharAt(word.length()-1);

        }
    }
    public static ArrayList<String> subsequences(String str) {
        ArrayList<String> ans=new ArrayList<>();
        StringBuilder sr=new StringBuilder();
        backtrack(str,sr,0,ans);
        return ans;
    }
    public static boolean isSubsequence_392(String s, String t) {
       if(s.length()>t.length()){
           return false;
       }
       if(s.length()==0){
           return true;
       }
       int count=0;
       for(int main_strind_pointer=0,to_check_string_pointer=0;main_strind_pointer<t.length();main_strind_pointer++){

           if(count==s.length()){
               break;
           }
           if(s.charAt(to_check_string_pointer)==t.charAt(main_strind_pointer)){
               to_check_string_pointer+=1;
               count++;
           }
       }
       return count==s.length();
    }
    public static TreeNode invertTree_226(TreeNode root) {
//        if(root==null){
//            return root;
//        }
//        invertTree_226(root.left);
//        invertTree_226(root.right);
//        TreeNode curr=root.left;
//        root.left=root.right;
//        root.right=curr;
//        return root;
        Queue<TreeNode> q=new LinkedList<>();
        if(root!=null){
            q.add(root);
        }
        while (!q.isEmpty()){
            TreeNode temp=q.poll();
            if(temp.left!=null){
                q.add(temp.left);
            }
            if(temp.right!=null){
                q.add(temp.right);
            }
            TreeNode curr=temp.left;
            temp.left=temp.right;
            temp.right=curr;
        }
        return root;
    }
    public static int result;
    public static void left_leave_sum(TreeNode root,boolean flag){
        if(root==null){
            return;
        }
        if((root.left==null && root.right==null) && flag){
            result+=root.val;
        }
        left_leave_sum(root.left,true);
        left_leave_sum(root.right,false);
    }
    public static int sumOfLeftLeaves_404(TreeNode root) {
        result=0;
        left_leave_sum(root.left,true);
        left_leave_sum(root.right,false);
        return result;
    }
    public static int numJewelsInStones_771(String jewels, String stones) {
        HashMap<Character,Integer> sets=new HashMap<>();
        for(char stone:stones.toCharArray()){
            if(sets.containsKey(stone)){
                sets.put(stone,sets.get(stone)+1);
            }
            else {
                sets.put(stone,1);
            }
        }
        int count=0;
        for (char each_stone:jewels.toCharArray()){
            if(sets.containsKey(each_stone)) {
                count += sets.get(each_stone);
            }
        }
        return count;
    }
    public boolean divisorGame(int n) {
//        return n % 2 == 0;
//
//        int c=n/2;
//
//        return c * 2 == n;

        return (n & 1) != 1;
    }
    public static String addStrings(String num1, String num2) {

        int num1_length=num1.length();
        int num2_length=num2.length();
        int maxi=Math.max(num1_length,num2_length);
        int[] sum=new int[maxi+1];
        int i=num1_length-1;
        int j=num2_length-1;
        int carry=0;
        int main_point=sum.length-1;
        while (i>=0 && j>=0){
            int a=num1.charAt(i)-'0';
            int b=num2.charAt(j)-'0';
            int temp=a+b+carry;
            carry=temp/10;
            int p=temp%10;
            sum[main_point]=p;
            main_point--;
            i--;
            j--;
        }
        while (i>=0){
            int a=num1.charAt(i)-'0';
            int sum_temp=a+carry;
            carry=sum_temp/10;
            int temp=sum_temp%10;
            sum[main_point]=temp;
            main_point--;
            i--;
        }
        while (j>=0){
            int a=num2.charAt(j)-'0';
            int sum_temp=a+carry;
            carry=sum_temp/10;
            int temp=sum_temp%10;
            sum[main_point]=temp;
            main_point--;
            j--;
        }
        if(carry==1){
            sum[0]=1;
        }
        StringBuilder ans=new StringBuilder();
        for (int num:sum){
            ans.append(num);
        }
        while (!ans.isEmpty() && ans.charAt(0)=='0'){
            ans.deleteCharAt(0);
        }
        return ans.isEmpty()?"0":ans.toString();
    }
    public static int ascii_value(int[] val,char[] x,char c){
        for (int i=0;i<x.length;i++){
            if (x[i]==c){
                return val[i];
            }
        }
        return -2000;
    }
    static String maxSum(String w,char x[],int b[], int n){
        StringBuilder ans=new StringBuilder();
        StringBuilder final_Ans=new StringBuilder();
        int maxi=0;
//        ans.append(w.charAt(0));
        int sum=(int)w.charAt(0);
        if(ascii_value(b,x,w.charAt(0))!=-2000){
            sum=ascii_value(b,x,w.charAt(0));
        }

        for (int i=0;i<w.length();i++){
            char at=w.charAt(i);
            ans.append(w.charAt(i));
            if(ascii_value(b,x,at)!=-2000){
                maxi+=ascii_value(b,x,w.charAt(0));
            }
            else {
                maxi+=(int)at;
            }
            if (maxi<0){
                ans=new StringBuilder();
                maxi=0;
            }

            if (sum>maxi){
                maxi=sum;
                final_Ans=ans;
            }

        }
        return final_Ans.toString();
    }
    public  boolean is_peli_check(ListNode head,Stack<Integer> num){
        if (num.isEmpty() && head==null){
            return true;
        }
        else if (head.val!=num.peek()){
            return false;
        }
        num.pop();
        boolean ans=is_peli_check(head.next,num);
        return ans;
    }
    public boolean isPalindrome1(ListNode head) {
        if (head==null){
            return false;
        }
        Stack<Integer> num=new Stack<>();
        ListNode temp=head;
        while (temp!=null){
            num.push(temp.val);
            temp=temp.next;
        }
        return is_peli_check(head,num);
    }
    public ListNode middleNode_876(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null  ){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
    public int getDecimalValue_1290(ListNode head) {
        int size=0;
        ListNode temp=head;
        while (temp!=null){
            temp=temp.next;
            size++;
        }
        size=size-1;
        ListNode counter=head;
        int sum=0;
        while (counter!=null){
            int curr_val=counter.val;
            int val_of_that_pos=(int)Math.pow(2,size) * curr_val;
            sum+=val_of_that_pos;
            size--;
            counter=counter.next;

        }
        return sum;

    }
    public static  void putting_greater(int[] num1,int[] ans,int no,int answer_index){
        int i=num1.length-1;
        int larger=Integer.MIN_VALUE;

        while (num1[i]!=no){
            if (num1[i]>no){
                larger=num1[i];
            }
            i--;
        }
        if (larger==Integer.MIN_VALUE){
            ans[answer_index]=-1;
        }
        else {
            ans[answer_index]=larger;
        }

    }
    public static int[] nextGreaterElement_496(int[] nums1, int[] nums2) {
        int[] ans=new int[nums1.length];
        for (int i=0;i<nums1.length;i++){
            putting_greater(nums2,ans,nums1[i],i);
        }
        return ans;
    }
    public int sumOfUnique(int[] nums) {
//        HashMap<Integer,Integer> couts=new HashMap<>();
//        for (int i:nums){
//            if (couts.containsKey(i)){
//                couts.put(i,couts.get(i)+1);
//            }
//            else {
//                couts.put(i,1);
//            }
//        }
//        int sum=0;
//        for (Map.Entry<Integer,Integer> values:couts.entrySet()){
//            if (values.getValue()==1){
//                sum+=values.getKey();
//            }
//        }
//        return sum;

        int sum=0;
        int[] num=new int[101];
        for(int i:nums){
            num[i]++;
        }
        for(int i=0;i<101;i++){
            if(num[i]==1){
                sum+=i;
            }
        }
        return sum;
    }
    public static Stack<Integer> insertAtBottom(Stack<Integer> st, int x) {
        if (st.isEmpty()){
            st.push(x);
            return st;
        }
        int temp=st.peek();
        st.pop();
        st=insertAtBottom(st,x);
        st.push(temp);
        return st;
    }
    public static void reverse(Stack<Integer> s) {
        if(s.isEmpty()){
            return;
        }
        int temp=s.peek();
        s.pop();
        reverse(s);
        insertAtBottom(s,temp);
    }
    public void asceding_order(Stack<Integer> s,int no){
        if(s.isEmpty()){
            s.push(no);
            return;
        }
        if (s.peek()>no){
            int temp=s.peek();
            s.pop();
            asceding_order(s,no);
            s.push(temp);
        }
        else{
            s.push(no);
        }
    }
    public Stack<Integer> sort(Stack<Integer> s) {
        if(s.isEmpty()){
            return s;
        }
        int temp=s.peek();
        s.pop();
        sort(s);
        asceding_order(s,temp);
        return s;
    }
    public static boolean findRedundantBrackets(String s) {
        Stack<Character> st=new Stack<>();
        for(int i=0;i<s.length();i++){
            char curr=s.charAt(i);
            if(curr=='('|| curr=='+' || curr=='-' || curr=='*' || curr=='/'){
                st.push(curr);
            }
            else{
                if(curr==')'){
                    boolean ans=true;
                    while(st.peek()!='('){
                        char c=st.peek();

                        if( c=='+' || c=='-' || c=='*' || c=='/'){
                            ans=false;
                        }
                        st.pop();

                    }
                    if(ans==true){
                        return true;
                    }
                    st.pop();
                }
            }
        }
        return false;
    }
    public int smaller_val(Stack<Integer> st,int no){
        if (st.isEmpty()){
            return no;
        }
        if(st.peek()<no){
            return st.peek();
        }
        int temp=st.peek();
        st.pop();
        int ans=smaller_val(st,no);
        st.push(temp);
        return ans;

    }
    public int[] finalPrices_1475(int[] prices) {

        Stack<Integer> st=new Stack<>();

        for(int i=prices.length-1;i>=0;i--){
            int curr=prices[i];
            if(st.isEmpty()){
                st.push(curr);
            }
            else if(st.peek()<curr){
                prices[i]=curr-st.peek();
                st.push(curr);
            }
            else{
                while(!st.isEmpty() && st.peek()>curr){
                    st.pop();
                }
                if(st.isEmpty()){
                    prices[i]=curr;
                }
                else{
                    prices[i]=curr-st.peek();
                }
                st.push(curr);
            }
        }
        return prices;
    }
    public int[] replaceElements(int[] arr) {
        // Stack<Integer> st=new Stack<>();
        // st.push(-1);
        // for(int i=arr.length-1;i>=0;i--){
        //         int temp=arr[i];
        //         arr[i]=st.peek();
        //         if(temp>st.peek()){
        //             st.push(temp);
        //         }

        // }
        // return arr;

        int max=-1;
        for(int i=arr.length-1;i>=0;i--){
            if(arr[i]>max){
                int temp=max;
                max=arr[i];
                arr[i]=temp;
            }
            else{
                arr[i]=max;
            }
        }
        return arr;
    }
    public Queue<Integer> rev(Queue<Integer> q){
        Stack<Integer> no=new Stack<>();
        while(!q.isEmpty()){
            no.push(q.poll());
        }
        while(!no.isEmpty()){
            q.add(no.pop());
        }
        return q;
    }
    public static long checking_first_negative(Deque<Long> dq){
        long no=0;
        Queue<Long> q=new LinkedList<>();
        while (!dq.isEmpty()){
            q.add(dq.poll());
        }
        while (!q.isEmpty()){
            if(q.peek()<0){
                no=q.peek();
                return no;
            }
            q.poll();
        }
        return no;
    }
    public static long[] printFirstNegativeInteger(long A[], int N, int K) {
        List<Long> ans=new ArrayList<>();
        Deque<Long> no=new LinkedList<>();
        for(int i=0;i<K;i++){
            if(A[i]<0){
                no.add((long)i);
            }
        }
        if (no.isEmpty()){
            ans.add(0L);
        }
        else {
            long ind=no.peek();
            ans.add(A[(int) ind]);
        }

        for (int i=K;i<N;i++){
            //removing index
            if(!no.isEmpty() && i-no.peek()>=K){
                no.pollFirst();
            }
            //adding
            if(A[i]<0){
                no.add((long)i);
            }

            if (no.isEmpty()){
                ans.add(0L);
            }
            else {
                long ind=no.peek();
                ans.add(A[(int)ind]);
            }
        }
        long[] the_ans=new long[ans.size()];
        int i=0;
        for (long n:ans){
            the_ans[i++]=n;
        }
        return the_ans;
    }
    public Queue<Integer> modifyQueue(Queue<Integer> q, int k) {
        int size=q.size();
        Stack<Integer> st=new Stack<>();
        for (int i=0;i<k;i++){
            st.push(q.poll());
        }
        while (!st.isEmpty()){
            q.add(st.pop());
        }
        for (int i=0;i<size-k;i++){
            q.add(q.poll());
        }
        return q;

    }
    public static ArrayList<Integer> rearrangeQueue(int N, Queue<Integer> q) {
        ArrayList<Integer> ans=new ArrayList<>();
        int mid=q.size()/2;
        Stack<Integer> st=new Stack<>();
        for (int i=0;i<mid;i++){
            st.push(q.poll());
        }
        while (!st.empty()){
            q.add(st.pop());
        }
        for (int i=0;i<mid;i++){
            q.add(q.poll());
        }
        for (int i=0;i<mid;i++){
            st.push(q.poll());
        }
        while (!st.empty()){
            ans.add(st.pop());
            ans.add(q.poll());
        }
        return ans;
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
        int ans=0;
        ans=arr[maxi.peek()]+arr[mini.peek()];
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
     void counting_leaves(TreeNode node,int count){
        if(node.left==null && node.right==null){
            count+=1;
            return;
        }
        counting_leaves(node.left,count);
        counting_leaves(node.right,count);

    }
    int countLeaves(TreeNode node) {
        if(node==null){
            return 0;

        }
        int count=0;
        counting_leaves(node,count);
        return count;
    }
}

