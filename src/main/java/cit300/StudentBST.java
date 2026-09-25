package cit300;

public class StudentBST {

    // Internal node class for the BST
    private class Node {
        StudentRecord data;
        Node left;
        Node right;

        Node(StudentRecord data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public StudentBST() {
        root = null;
        size = 0;
    }

    // Insert a student record, ordered by Student ID. Returns false if ID already exists.
    public boolean insert(StudentRecord record) {
        if (search(record.getStudentId()) != null) {
            return false; // duplicate ID
        }
        root = insertRec(root, record);
        size++;
        return true;
    }

    private Node insertRec(Node node, StudentRecord record) {
        if (node == null) {
            return new Node(record);
        }
        int cmp = record.getStudentId().compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) {
            node.left = insertRec(node.left, record);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, record);
        }
        return node;
    }

    // Search for a student record by ID. Returns null if not found.
    public StudentRecord search(String studentId) {
        return searchRec(root, studentId);
    }

    private StudentRecord searchRec(Node node, String studentId) {
        if (node == null) {
            return null;
        }
        int cmp = studentId.compareToIgnoreCase(node.data.getStudentId());
        if (cmp == 0) {
            return node.data;
        } else if (cmp < 0) {
            return searchRec(node.left, studentId);
        } else {
            return searchRec(node.right, studentId);
        }
    }

    // Delete a student record by ID. Returns true if deleted, false if not found.
    public boolean delete(String studentId) {
        if (search(studentId) == null) {
            return false;
        }
        root = deleteRec(root, studentId);
        size--;
        return true;
    }

    private Node deleteRec(Node node, String studentId) {
        if (node == null) {
            return null;
        }
        int cmp = studentId.compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) {
            node.left = deleteRec(node.left, studentId);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, studentId);
        } else {
            // Node found
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Two children: replace with in-order successor (smallest in right subtree)
            Node successor = findMin(node.right);
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data.getStudentId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Display all students in ascending Student ID order (in-order traversal)
    public void displayInOrder() {
        if (root == null) {
            System.out.println("No student records in the tree.");
            return;
        }
        System.out.println("----- Students in BST (ascending Student ID order) -----");
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.println(node.data);
        inOrderRec(node.right);
    }

    public int getSize() {
        return size;
    }
}