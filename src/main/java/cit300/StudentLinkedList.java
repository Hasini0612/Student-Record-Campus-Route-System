package cit300;

public class StudentLinkedList {

    // Internal node class for the linked list
    private class Node {
        StudentRecord data;
        Node next;

        Node(StudentRecord data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public StudentLinkedList() {
        head = null;
        size = 0;
    }

    // Add a new student record. Returns false if the ID already exists (duplicate).
    public boolean addStudent(StudentRecord record) {
        if (searchStudent(record.getStudentId()) != null) {
            return false; // duplicate ID not allowed
        }
        Node newNode = new Node(record);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    // Search for a student record by ID. Returns null if not found.
    public StudentRecord searchStudent(String studentId) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(studentId)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // Update an existing student's name, programme, and marks. Returns false if not found.
    public boolean updateStudent(String studentId, String newName, String newProgramme, double newMarks) {
        StudentRecord record = searchStudent(studentId);
        if (record == null) {
            return false;
        }
        record.setName(newName);
        record.setProgramme(newProgramme);
        record.setMarks(newMarks);
        return true;
    }

    // Delete a student record by ID. Returns the deleted record, or null if not found.
    public StudentRecord deleteStudent(String studentId) {
        if (head == null) {
            return null;
        }
        if (head.data.getStudentId().equalsIgnoreCase(studentId)) {
            StudentRecord deleted = head.data;
            head = head.next;
            size--;
            return deleted;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data.getStudentId().equalsIgnoreCase(studentId)) {
                StudentRecord deleted = current.next.data;
                current.next = current.next.next;
                size--;
                return deleted;
            }
            current = current.next;
        }
        return null; // not found
    }

    // Display all student records
    public void displayAll() {
        if (head == null) {
            System.out.println("No student records available.");
            return;
        }
        Node current = head;
        System.out.println("----- All Student Records (" + size + ") -----");
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public int getSize() {
        return size;
    }
}