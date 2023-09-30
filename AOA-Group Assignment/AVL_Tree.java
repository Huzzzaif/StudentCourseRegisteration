import java.util.ArrayList;
import java.util.List;

class Node {
    Courses key;
    int height;
    Node left, right;

    Node(Courses key) {
        this.key = key;
        this.height = 1;
    }
}

public class AVL_Tree {
    Node root;
    List<Courses> result = new ArrayList<>();
    // Get the height of a node
    int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // Get the balance factor of a node
    int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // Right rotate a subtree rooted with y
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left rotate a subtree rooted with x
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert a key (Courses) into the AVL tree
    Node insert(Node root, Courses key) {
        if (root == null)
            return new Node(key);

        if (key.getCourseId() < root.key.getCourseId())
            root.left = insert(root.left, key);
        else if (key.getCourseId() > root.key.getCourseId())
            root.right = insert(root.right, key);
        else // Duplicate keys are not allowed
            return root;

        // Update height of this ancestor node
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Get the balance factor
        int balance = getBalance(root);

        // Perform rotations if necessary
        if (balance > 1 && key.getCourseId() < root.left.key.getCourseId())
            return rightRotate(root);

        if (balance < -1 && key.getCourseId() > root.right.key.getCourseId())
            return leftRotate(root);

        if (balance > 1 && key.getCourseId() > root.left.key.getCourseId()) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && key.getCourseId() < root.right.key.getCourseId()) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Print the AVL tree in-order and return a list of Courses
    List<Courses> inOrderTraversal(Node node) {

        if (node == root) {
            result.clear();
        }
        if (node != null) 
        {
            inOrderTraversal(node.left);
            result.add(node.key);
            inOrderTraversal(node.right);
        }
        return result;
    }
}
