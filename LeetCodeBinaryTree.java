import java.util.*;

public class LeetCodeBinaryTree {
    public static void main(String[] args) {
        Node root = new Node(1, new Node(2, new Node(4), new Node(5), null), 
        new Node(3, new Node(6), new Node(7), null), null);
        connect(root);
    }

    public static Node connect(Node root) {
        if(root == null) return null;

        return null;
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    
        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        return buildTreePreInRecursive(preorder, inorder, 0, 0, inorder.length - 1);
        //return buildTreePreInIterative(preorder, inorder, 0, 0, inorder.length - 1);
    }

    public static TreeNode buildTreePreInIterative(int[] preorder, int[] inorder) {
        if(preorder.length == 0) return null;

        int prePosition = 0;
        int inPosition = 0;
        int flag = 0;

        TreeNode temp = new TreeNode(preorder[prePosition++]);
        System.out.print(temp.val);
        TreeNode root = temp;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(temp);

        while(prePosition < preorder.length) {
            if(!stack.isEmpty() && stack.peek().val == inorder[inPosition]) {
                flag = 1;
                temp = stack.pop();
                inPosition++;
            } else {
                if(flag == 0) {
                    temp.left = new TreeNode(preorder[prePosition++]);
                    temp = temp.left;
                    System.out.print(temp.val);
                    stack.push(temp);
                } else {
                    flag = 0;
                    temp.right = new TreeNode(preorder[prePosition++]);
                    temp = temp.right;
                    System.out.print(temp.val);
                    stack.push(temp);
                }
            }
        }
        return root;
    }
    
    public static TreeNode buildTreePreInRecursive(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if(preStart > preorder.length - 1 || inStart > inEnd) return null;

        int rootValue = preorder[preStart];
        System.out.print(rootValue);
        TreeNode root = new TreeNode(rootValue);

        int rootIndex  = 0;
        for(int i = 0; i <= inEnd; i++) {
            if(inorder[i] == rootValue) {
                rootIndex = i;
                break;
            }
        }

        int rootPredecessor = rootIndex - 1; // predeccessor of root
        int rootSuccessor = rootIndex + 1; // successor or root

        int nextRootForLeft = preStart + 1; //next root element for left
        int nextRootForRight = preStart + rootIndex - inStart + 1; // next root element for right
         

        //for left
        root.left = buildTreePreInRecursive(preorder, inorder, nextRootForLeft, inStart, rootPredecessor);

        //for right
        root.right = buildTreePreInRecursive(preorder, inorder, nextRootForRight, rootSuccessor, inEnd);

        return root;
    }

    public TreeNode buildTreeInPost(int[] inorder, int[] postorder) {
        return buildTreeInPostRecursive(inorder, postorder, 0, inorder.length - 1, 0, inorder.length - 1);
        //return buildTreeInPostIterative(inorder, postorder);
    }

    public static TreeNode buildTreeInPostIterative(int[] inorder, int[] postorder) {
        if(postorder.length == 0) return null;
        
        int postSize = postorder.length - 1;
        int inSize = inorder.length - 1;
        int flag = 0;

        TreeNode temp = new TreeNode(postorder[postSize--]);
        System.out.print(temp.val);
        TreeNode root = temp;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(temp);

        while(postSize >= 0) {
            if(!stack.isEmpty() && stack.peek().val == inorder[inSize]) {
                flag = 1;
                temp = stack.pop();
                inSize--;
            } else {
                if(flag == 0) {
                    temp.right = new TreeNode(postorder[postSize--]);
                    temp = temp.right;
                    System.out.print(temp.val);
                    stack.push(temp);
                } else {
                    flag = 0;
                    temp.left = new TreeNode(postorder[postSize--]);
                    temp = temp.left;
                    System.out.print(temp.val);
                    stack.push(temp);
                }
            }
        }
        return root;
    }
    
    public static TreeNode buildTreeInPostRecursive(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {
        if(inStart > inEnd) return null;

        int rootValue = postorder[postEnd];
        System.out.println(rootValue);

        TreeNode root = new TreeNode(rootValue);

        int rootIndex = 0;
        for(int i = 0; i <= inEnd; i++){
            if(inorder[i] == rootValue) {
                rootIndex = i;
                break;
            }
        }

        int rootPredecessor = rootIndex - 1; // predeccessor of root
        int rootSuccessor = rootIndex + 1; // successor or root

        int nextRootForLeft = postStart + (rootIndex -  inStart) - 1; //next root element for left
        int nextRootForRight = postEnd - 1; // next root element for right

        int rightPostStart = postEnd - (inEnd - rootIndex);

        //for left
        root.left = buildTreeInPostRecursive(inorder, postorder, inStart, rootPredecessor, postStart, nextRootForLeft);

        //for right
        root.right = buildTreeInPostRecursive(inorder, postorder, rootSuccessor, inEnd, rightPostStart, nextRootForRight);

        return root;
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        return hasPathSumRecursive(root, sum);
        //return hasPathSumIterative(root, sum);
    }

    public static boolean hasPathSumRecursive(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && sum - root.val == 0) return true;
        return hasPathSumRecursive(root.left, sum - root.val) || hasPathSumRecursive(root.right, sum - root.val);
    }

    public static boolean hasPathSumIterative(TreeNode root, int sum) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(stack.empty() == false) {
            TreeNode current = stack.pop();
            if(current != null) {
                if(current.left == null && current.right == null && current.val == sum) {
                    return true;
                }
                if(current.right != null) {
                    current.right.val += current.val;
                    stack.push(current.right);
                }
                if(current.left != null) {
                    current.left.val += current.val;
                    stack.push(current.left);
                }
            }
        }
        return false;
    }

    public static boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        if(root.left != null && root.right != null) {
            if(root.left.val == root.right.val){
                return isSymmetric(root.left) && isSymmetric(root.right);
            }
        }
        return false;
    }

    public static int maxDepth(TreeNode root) {
        //return maxDepthRecursive(root);
        return maxDepthIterative(root);
    }

    public static int maxDepthRecursive(TreeNode root) {
        if(root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static int maxDepthIterative(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        int ans = 0;
        while(!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if(current == null) {
                if(!queue.isEmpty()) {
                    queue.add(null);
                }
                ans++;
            } else {
                if(current.left != null) {
                    queue.offer(current.left);
                }
                if(current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> levelOrderTraversal(TreeNode root) {
        if(root == null) return null;
        List<List<Integer>> list = new ArrayList<>();
        //return levelOrderRecursion(root, list, 1);
        return levelOrderIterativeStructure(root, list);
        //return levelOrderIterative(root, list);
    }

    public static List<List<Integer>> levelOrderRecursion(TreeNode root, List<List<Integer>> list, int level) {
        if(root == null) return list;
        if(list.size() < level) {
            list.add(new ArrayList<Integer>());
        }
        list.get(level - 1).add(root.val);
        levelOrderRecursion(root.left, list, level + 1);
        levelOrderRecursion(root.right, list, level + 1);
        return list;
    }

    public static List<List<Integer>> levelOrderIterativeStructure(TreeNode root, List<List<Integer>> list) {
        if(root == null) return list;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()) {
            List<Integer> newList = new ArrayList<>();
            int size = queue.size();
            list.add(newList);
            for(int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                newList.add(current.val);
                if(current.left != null){
                    queue.offer(current.left);
                }
                if(current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        return list;
    }

    public static List<List<Integer>> levelOrderIterative(TreeNode root, List<List<Integer>> list) {
        if(root == null) return list;
        TreeNode current = root;
        while(current != null) {
            List<Integer> newList = new ArrayList<>();
            list.add(newList);
            if(current.left == null) {
                newList.add(current.val);
                current = current.right;
            } else {
                TreeNode predecessor = current.left;
                while(predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                if(predecessor.right == current) {
                    predecessor.right = null;
                    current = current.right;
                } else {
                    newList.add(current.val);
                    predecessor.right = current;
                    current = current.left;
                }
            }
        }
        return list;
    }

    public static List<Integer> postOrderTraversal(TreeNode root) {
        if(root == null) return null;
        List<Integer> list = new ArrayList<>();
        //return postOrderRecursion(root, list);
        //return postOrderIterativeStructure(root, list);
        return postOrderIterative(root, list);
    }

    public static List<Integer> postOrderRecursion(TreeNode root, List<Integer> list) {
        if(root == null) return list;
        postOrderRecursion(root.left, list);
        postOrderRecursion(root.right, list);
        list.add(root.val);
        return list;
    }

    public static List<Integer> postOrderIterativeStructure(TreeNode root, List<Integer> list) {
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        TreeNode previous = null;
        while(stack.empty() == false) {
            TreeNode current = stack.peek();
            if (previous == null || previous.left == current || previous.right == current) {
                if (current.left != null) {
                    stack.push(current.left);
                } else if(current.right != null) {
                    stack.push(current.right);
                } else {
                    stack.pop();
                    list.add(current.val);
                }
            } else if (current.left == previous) {
                if (current.right != null) {
                    stack.push(current.right);
                } else {
                    stack.pop();
                    list.add(current.val);
                }
            } else if (current.right == previous) {
                stack.pop();
                list.add(current.val);
            }
            previous = current;
         }
        return list;
    }

    public static List<Integer> postOrderIterative(TreeNode root, List<Integer> list) {
        if(root == null) return list;
        TreeNode current = root;
        while(current != null) {
            if(current.right == null) {
                list.add(0, current.val);
                current = current.left;
            } else {
                TreeNode successor = current.right;
                while(successor.left != current && successor.left != null) {
                    successor = successor.left;
                }

                if(successor.left == null) {
                    successor.left = current;
                    list.add(0, current.val);
                    current = current.right;
                } else {
                    successor.left = null;
                    current = current.left;
                }
            }
        }
        return list;
    }

    public static List<Integer> inOrderTraversal(TreeNode root) {
        if(root == null) return null;
        List<Integer> list = new ArrayList<>();
        //return inOrderRecursion(root, list);
        //return inOrderIterativeStructure(root, list);
        return inOrderIterative(root, list);
    }

    public static List<Integer> inOrderRecursion(TreeNode root, List<Integer> list) {
        if(root == null) return list;
        inOrderRecursion(root.left, list);
        list.add(root.val);
        inOrderRecursion(root.right, list);
        return list;
    }

    public static List<Integer> inOrderIterativeStructure(TreeNode root, List<Integer> list) {
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        while(true) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                if (stack.empty() == true) {
                    break;
                } else {
                    TreeNode temp = stack.pop();
                    list.add(temp.val);
                    current = temp.right;      
                }             
            }
        }
        return list;
    }

    public static List<Integer> inOrderIterative(TreeNode root, List<Integer> list) {
        if(root == null) return list;
        TreeNode current = root;
        while(current != null) {
            if(current.left == null) {
                list.add(current.val);
                current = current.right;
            } else {
                TreeNode predecessor = current.left;
                while(predecessor.right != current && predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                if(predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    list.add(current.val);
                    current = current.right;
                }
            }
        }
        return list;
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return null;
        List<Integer> list = new ArrayList<>();
        //return preOrderRecursion(root, list);
        //return preOrderIterativeStructure(root, list);
        return preOrderIterative(root, list);
    }

    public static List<Integer> preOrderRecursion(TreeNode root, List<Integer> list) {
        if(root == null) return list;
        list.add(root.val);
        preOrderRecursion(root.left, list);
        preOrderRecursion(root.right, list);
        return list;
    }

    public static List<Integer> preOrderIterativeStructure(TreeNode root, List<Integer> list) {
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<TreeNode>(); 
        stack.push(root);
        while (stack.empty() == false) { 
            TreeNode current = stack.pop(); 
            list.add(current.val);
            if (current.right != null) { 
                stack.push(current.right); 
            }
            if (current.left != null) { 
                stack.push(current.left); 
            }
        }
        return list;
    }

    public static List<Integer> preOrderIterative(TreeNode root, List<Integer> list) {
        if(root == null) return list;
        TreeNode current = root;
        while(current != null) {
            if(current.left == null) {
                list.add(current.val);
                current = current.right;
            } else {
                TreeNode predecessor = current.left;
                while(predecessor.right != current && predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = current;
                    list.add(current.val);
                    current = current.left;
                } else {
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
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