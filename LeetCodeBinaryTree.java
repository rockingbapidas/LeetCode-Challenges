import java.util.*;

public class LeetCodeBinaryTree {
    public static void main(String[] args) {
        TreeNode rNode = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        List<Integer> result = preorderTraversal(rNode);
        System.out.println(Arrays.toString(result.toArray()));
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return null;
        List<Integer> list = new ArrayList<>();

        return list;
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {

        }

        public TreeNode(int val) { 
           this.val = val; 
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
        }
    }
}