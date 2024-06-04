import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

// class Tree definition
public class Tree<T extends Comparable<T>> {
   // class TreeNode definition
   private static class TreeNode<T> {
      // package access members
      TreeNode<T> leftNode; 
      T data; 
      TreeNode<T> rightNode; 

      // constructor initializes data and makes this a leaf node
      public TreeNode(T nodeData) { 
         data = nodeData;              
         leftNode = rightNode = null; 
      }
   }

   private TreeNode<T> root;

   // constructor initializes an empty Tree
   public Tree() { 
      root = null; 
   }

   //This method inserts a new node in the binary search tree using an iterative approach rather than a recursive one
   public void insertNode(T insertValue) {
      if (root == null) {
         root = new TreeNode<>(insertValue); 
      } else {
         TreeNode<T> currentNode = root;
         TreeNode<T> parentNode = null;

         while (currentNode != null) {
            parentNode = currentNode;
            if (insertValue.compareTo(currentNode.data) < 0) {
               currentNode = currentNode.leftNode;
            } else if (insertValue.compareTo(currentNode.data) > 0) {
               currentNode = currentNode.rightNode;
            } else {
               // duplicate value, do nothing
               return;
            }
         }

         // Insert the new node as a child of the parent node
         if (insertValue.compareTo(parentNode.data) < 0) {
            parentNode.leftNode = new TreeNode<>(insertValue);
         } else {
            parentNode.rightNode = new TreeNode<>(insertValue);
         }
      }
   }

   //This method searches for a node in the binary search tree
   public boolean search(T value) {
      TreeNode<T> currentNode = root;

      while (currentNode != null) {
         if (value.compareTo(currentNode.data) < 0) {
            currentNode = currentNode.leftNode;
         } else if (value.compareTo(currentNode.data) > 0) {
            currentNode = currentNode.rightNode;
         } else {
            return true; 
         }
      }

      return false; 
   }

   //This method removes a node from the binary search tree
   public void remove(T value) {
      root = removeNode(root, value);
   }

   //This is a helper method to remove a node
   private TreeNode<T> removeNode(TreeNode<T> node, T value) {
      if (node == null) {
         return null; 
      }

      if (value.compareTo(node.data) < 0) {
         node.leftNode = removeNode(node.leftNode, value);
      } else if (value.compareTo(node.data) > 0) {
         node.rightNode = removeNode(node.rightNode, value);
      } else { 
         if (node.leftNode == null) {
            return node.rightNode;
         } else if (node.rightNode == null) {
            return node.leftNode;
         }

         node.data = findMin(node.rightNode).data;
         node.rightNode = removeNode(node.rightNode, node.data);
      }

      return node;
   }

   //This is a helper method to find the minimum value in a subtree
   private TreeNode<T> findMin(TreeNode<T> node) {
      while (node.leftNode != null) {
         node = node.leftNode;
      }
      return node;
   }

   //This method is an iterative preorder traversal
   public void preorderTraversal() {
      if (root == null) {
         return;
      }

      Stack<TreeNode<T>> stack = new Stack<>();
      stack.push(root);

      while (!stack.isEmpty()) {
         TreeNode<T> currentNode = stack.pop();
         System.out.printf("%s ", currentNode.data);

         if (currentNode.rightNode != null) {
            stack.push(currentNode.rightNode);
         }

         if (currentNode.leftNode != null) {
            stack.push(currentNode.leftNode);
         }
      }
   }

   //This method is an iterative inorder traversal
   public void inorderTraversal() {
      Stack<TreeNode<T>> stack = new Stack<>();
      TreeNode<T> currentNode = root;

      while (currentNode != null || !stack.isEmpty()) {
         while (currentNode != null) {
            stack.push(currentNode);
            currentNode = currentNode.leftNode;
         }

         currentNode = stack.pop();
         System.out.printf("%s ", currentNode.data);
         currentNode = currentNode.rightNode;
      }
   }

   //This method is an iterative postorder traversal
   public void postorderTraversal() {
      if (root == null) {
         return;
      }

      Stack<TreeNode<T>> stack1 = new Stack<>();
      Stack<TreeNode<T>> stack2 = new Stack<>();
      stack1.push(root);

      while (!stack1.isEmpty()) {
         TreeNode<T> currentNode = stack1.pop();
         stack2.push(currentNode);

         if (currentNode.leftNode != null) {
            stack1.push(currentNode.leftNode);
         }

         if (currentNode.rightNode != null) {
            stack1.push(currentNode.rightNode);
         }
      }

      while (!stack2.isEmpty()) {
         TreeNode<T> currentNode = stack2.pop();
         System.out.printf("%s ", currentNode.data);
      }
   }

   //This method is an iterative method to print tree horizontally
   public void outputTree() {
      if (root == null) {
         return;
      }
      printTree(root, "", true);
   }

   //This is a helper method to print tree horizontally. It uses spaces and lines to connect between the numbers to print 
   //as a tree in a horizontal manner given the postorder traversal
   private void printTree(TreeNode<T> node, String prefix, boolean isTail) {
      if (node.rightNode != null) {
         printTree(node.rightNode, prefix + (isTail ? "│   " : "    "), false);
      }
      System.out.println(prefix + (isTail ? "└── " : "┌── ") + node.data);
      if (node.leftNode != null) {
         printTree(node.leftNode, prefix + (isTail ? "    " : "│   "), true);
      }
   }
}
