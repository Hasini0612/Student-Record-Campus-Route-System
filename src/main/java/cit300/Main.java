package cit300;


import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static StudentLinkedList studentList = new StudentLinkedList();
    private static ActionStack actionStack = new ActionStack();
    private static ServiceQueue serviceQueue = new ServiceQueue();
    private static StudentBST studentBST = new StudentBST();
    private static StudentHashTable studentHashTable = new StudentHashTable();
    private static CampusGraph campusGraph = new CampusGraph();

    public static void main(String[] args) {
        System.out.println("=== University Student Record and Campus Route Management System ===");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1: addStudentRecord(); break;
                case 2: updateStudentRecord(); break;
                case 3: deleteStudentRecord(); break;
                case 4: displayAllRecordsLinkedList(); break;
                case 5: addServiceRequest(); break;
                case 6: processNextServiceRequest(); break;
                case 7: displayRecentActions(); break;
                case 8: displayStudentsBST(); break;
                case 9: searchStudentHashing(); break;
                case 10: addCampusLocation(); break;
                case 11: removeCampusLocation(); break;
                case 12: addCampusConnection(); break;
                case 13: removeCampusConnection(); break;
                case 14: displayCampusConnections(); break;
                case 15: traverseCampusLocations(); break;
                case 16:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 16.");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n===================== MAIN MENU =====================");
        System.out.println("1. Add Student Record");
        System.out.println("2. Update Student Record");
        System.out.println("3. Delete Student Record");
        System.out.println("4. Display All Records using Linked List");
        System.out.println("5. Add Service Request to Queue");
        System.out.println("6. Process Next Service Request");
        System.out.println("7. Display Recent Actions using Stack");
        System.out.println("8. Display Students using BST/AVL");
        System.out.println("9. Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS or DFS");
        System.out.println("16. Exit");
        System.out.println("======================================================");
    }

    // ---------- Input validation helpers ----------

    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid whole number.");
            }
        }
    }

    private static double readDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(sc.nextLine().trim());
                if (value < 0 || value > 100) {
                    System.out.println("Marks must be between 0 and 100. Please try again.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }
            return value;
        }
    }

    // ---------- Student Record operations (Requirement 12) ----------

    private static void addStudentRecord() {
        String id = readNonEmptyString("Enter Student ID: ");
        if (studentList.searchStudent(id) != null) {
            System.out.println("Error: A student with ID " + id + " already exists.");
            return;
        }
        String name = readNonEmptyString("Enter Name: ");
        String programme = readNonEmptyString("Enter Programme: ");
        double marks = readDoubleInput("Enter Marks (0-100): ");

        StudentRecord record = new StudentRecord(id, name, programme, marks);
        studentList.addStudent(record);
        studentBST.insert(new StudentRecord(id, name, programme, marks));
        studentHashTable.insert(new StudentRecord(id, name, programme, marks));
        actionStack.pushAction("Added student " + id + " (" + name + ")");

        System.out.println("Student record added successfully.");
    }

    private static void updateStudentRecord() {
        String id = readNonEmptyString("Enter Student ID to update: ");
        if (studentList.searchStudent(id) == null) {
            System.out.println("Error: No student found with ID " + id + ".");
            return;
        }
        String name = readNonEmptyString("Enter new Name: ");
        String programme = readNonEmptyString("Enter new Programme: ");
        double marks = readDoubleInput("Enter new Marks (0-100): ");

        studentList.updateStudent(id, name, programme, marks);
        // Keep BST and hash table in sync: remove and re-insert with updated data
        studentBST.delete(id);
        studentBST.insert(new StudentRecord(id, name, programme, marks));
        studentHashTable.delete(id);
        studentHashTable.insert(new StudentRecord(id, name, programme, marks));
        actionStack.pushAction("Updated student " + id);

        System.out.println("Student record updated successfully.");
    }

    private static void deleteStudentRecord() {
        String id = readNonEmptyString("Enter Student ID to delete: ");
        StudentRecord deleted = studentList.deleteStudent(id);
        if (deleted == null) {
            System.out.println("Error: No student found with ID " + id + ".");
            return;
        }
        studentBST.delete(id);
        studentHashTable.delete(id);
        actionStack.pushAction("Deleted student " + id + " (" + deleted.getName() + ")");

        System.out.println("Student record deleted successfully.");
    }

    private static void displayAllRecordsLinkedList() {
        studentList.displayAll();
    }

    // ---------- Queue operations ----------

    private static void addServiceRequest() {
        String id = readNonEmptyString("Enter Student ID for the request: ");
        String description = readNonEmptyString("Enter request description (e.g. Transcript request): ");
        serviceQueue.addRequest(id + " - " + description);
        actionStack.pushAction("Added service request for " + id);
        System.out.println("Service request added to queue.");
    }

    private static void processNextServiceRequest() {
        String request = serviceQueue.processNextRequest();
        if (request == null) {
            System.out.println("No pending service requests.");
        } else {
            actionStack.pushAction("Processed service request: " + request);
            System.out.println("Processed request: " + request);
        }
    }

    // ---------- Stack operations ----------

    private static void displayRecentActions() {
        actionStack.displayActions();
    }

    // ---------- BST operations ----------

    private static void displayStudentsBST() {
        studentBST.displayInOrder();
    }

    // ---------- Hashing operations ----------

    private static void searchStudentHashing() {
        String id = readNonEmptyString("Enter Student ID to search: ");
        StudentRecord record = studentHashTable.search(id);
        if (record == null) {
            System.out.println("No student found with ID " + id + ".");
        } else {
            System.out.println("Found: " + record);
        }
    }

    // ---------- Graph operations (Requirements 7-11) ----------

    private static void addCampusLocation() {
        String name = readNonEmptyString("Enter new campus location name: ");
        boolean added = campusGraph.addLocation(name);
        if (added) {
            System.out.println("Campus location added successfully.");
        } else {
            System.out.println("Error: Location '" + name + "' already exists.");
        }
    }

    private static void removeCampusLocation() {
        String name = readNonEmptyString("Enter campus location name to remove: ");
        boolean removed = campusGraph.removeLocation(name);
        if (removed) {
            System.out.println("Campus location removed successfully.");
        } else {
            System.out.println("Error: Location '" + name + "' not found.");
        }
    }

    private static void addCampusConnection() {
        String loc1 = readNonEmptyString("Enter first location: ");
        String loc2 = readNonEmptyString("Enter second location: ");
        boolean added = campusGraph.addConnection(loc1, loc2);
        if (added) {
            System.out.println("Connection added successfully.");
        } else {
            System.out.println("Error: Unable to add connection. Check that both locations exist and are not already connected.");
        }
    }

    private static void removeCampusConnection() {
        String loc1 = readNonEmptyString("Enter first location: ");
        String loc2 = readNonEmptyString("Enter second location: ");
        boolean removed = campusGraph.removeConnection(loc1, loc2);
        if (removed) {
            System.out.println("Connection removed successfully.");
        } else {
            System.out.println("Error: Connection not found between '" + loc1 + "' and '" + loc2 + "'.");
        }
    }

    private static void displayCampusConnections() {
        campusGraph.displayNetwork();
    }

    private static void traverseCampusLocations() {
        if (campusGraph.getLocationCount() == 0) {
            System.out.println("No campus locations available to traverse.");
            return;
        }
        String start = readNonEmptyString("Enter starting location: ");
        if (!campusGraph.locationExists(start)) {
            System.out.println("Error: Location '" + start + "' not found.");
            return;
        }
        System.out.println("Choose traversal type: 1. BFS   2. DFS");
        int type = readIntInput("Enter choice: ");

        List<String> result;
        if (type == 1) {
            result = campusGraph.bfsTraversal(start);
            System.out.println("BFS Traversal from " + start + ": " + result);
        } else if (type == 2) {
            result = campusGraph.dfsTraversal(start);
            System.out.println("DFS Traversal from " + start + ": " + result);
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }
}