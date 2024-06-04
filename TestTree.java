//Name: Evan Eggerud & Ashling Cruz
//Source: Deitel & Deitel
//Class: CS 145
//Assignment: #7 Binary Trees
//Purpose: This is a test class with a main class which executes the program given an array provided here, it will print the map and the preorder, inorder and postorder traversal
//Then it also demonstrates the usage of the search function and the delete function and prints a map with the corresponding deleted values.
//Extra Credit: The tree class also uses iteration rather than recursion for its function.

public class TestTree {
    public static void main(String[] args) {
        // Create a Tree of integers
        Tree<Integer> tree = new Tree<>();

        // Array of values to be inserted into the tree
        int[] values = {45, 15, 79, 90, 10, 55, 12, 20, 50};

        // Insert nodes into the tree using a for loop
        for (int value : values) {
            tree.insertNode(value);
        }
        //Binary tree demonstration
        System.out.println("Tree (horizontal representation):");
        tree.outputTree();

        System.out.println("\nPreorder traversal:");
        tree.preorderTraversal();

        System.out.println("\nInorder traversal:");
        tree.inorderTraversal();

        System.out.println("\nPostorder traversal:");
        tree.postorderTraversal();
        //Search demonstration
        int searchValue1 = 45;
        int searchValue2 = 100;
        System.out.printf("\nSearch for %d: %s\n", searchValue1, tree.search(searchValue1) ? "found" : "not found");
        System.out.printf("Search for %d: %s\n", searchValue2, tree.search(searchValue2) ? "found" : "not found");
        //remove demonstration with corresponding maps after removal.
        int removeValue1 = 79;
        int removeValue2 = 55;
        System.out.printf("\nRemoving %d\n", removeValue1);
        tree.remove(removeValue1);
        tree.outputTree();

        System.out.printf("\nRemoving %d\n", removeValue2);
        tree.remove(removeValue2);
        tree.outputTree();
    }
}
