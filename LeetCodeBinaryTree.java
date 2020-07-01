import java.util.*;

public class LeetCodeBinaryTree {
    public static void main(String[] args) {
        // Root, Left , Right
        // TreeNode rNode = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        // List<Integer> result = preorderTraversal(rNode);
        // System.out.println(Arrays.toString(result.toArray()));

        // Left, Root, Right
        // TreeNode rNode = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        // List<Integer> result = inOrderTraversal(rNode);
        // System.out.println(Arrays.toString(result.toArray()));

        // Left, Right, Root
        // TreeNode rNode = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        // List<Integer> result = postOrderTraversal(rNode);
        // System.out.println(Arrays.toString(result.toArray()));

        // Breadth-First Search, Depth-First Search
        // TreeNode rNode = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        // List<List<Integer>> result = levelOrderTraversal(rNode);
        // System.out.println(Arrays.toString(result.toArray()));

        // TreeNode rNode = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        // System.out.println(maxDepth(rNode));   
        
        TreeNode rNode = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), 
        new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        System.out.println(isSymmetric(rNode)); 
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
        if(root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
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