package cit300;

public class StudentHashTable {

    // Internal node class for chaining (handles hash collisions)
    private class Node {
        StudentRecord data;
        Node next;

        Node(StudentRecord data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node[] table;
    private int capacity;
    private int size;

    public StudentHashTable() {
        this.capacity = 16; // initial bucket count
        this.table = new Node[capacity];
        this.size = 0;
    }

    // Simple hash function based on Student ID characters
    private int hash(String studentId) {
        int hash = 0;
        for (char c : studentId.toUpperCase().toCharArray()) {
            hash = (hash * 31 + c) % capacity;
        }
        return Math.abs(hash);
    }

    // Insert a student record. Returns false if ID already exists.
    public boolean insert(StudentRecord record) {
        if (search(record.getStudentId()) != null) {
            return false; // duplicate ID
        }
        int index = hash(record.getStudentId());
        Node newNode = new Node(record);
        newNode.next = table[index];
        table[index] = newNode;
        size++;
        return true;
    }

    // Search for a student record by ID (O(1) average case). Returns null if not found.
    public StudentRecord search(String studentId) {
        int index = hash(studentId);
        Node current = table[index];
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // Delete a student record by ID. Returns true if deleted, false if not found.
    public boolean delete(String studentId) {
        int index = hash(studentId);
        Node current = table[index];
        Node prev = null;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    // Display all entries bucket by bucket (shows hash distribution)
    public void displayAll() {
        if (size == 0) {
            System.out.println("Hash table is empty.");
            return;
        }
        System.out.println("----- Student Hash Table Contents (" + size + " entries) -----");
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                System.out.print("Bucket " + i + ": ");
                Node current = table[i];
                while (current != null) {
                    System.out.print("[" + current.data.getStudentId() + "] ");
                    current = current.next;
                }
                System.out.println();
            }
        }
    }

    public int getSize() {
        return size;
    }
}