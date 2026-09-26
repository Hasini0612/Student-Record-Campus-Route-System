# University Student Record and Campus Route Management System

**Module:** CIT300 – Data Structures and Algorithms  
**Assignment:** Graded Practical Assignment 1 (Week 10)  
**Coverage:** Weeks 1–9 – Linear Data Structures, Trees, Hashing, and Graphs

## Project Overview

This is a Java console application that manages university student records and 
represents connections between campus locations. It demonstrates the practical 
use of linked lists, stacks, queues, BST (Binary Search Tree), hashing, and 
graphs.

## Group Members

| Name | Student ID | Assigned Responsibility |
|---|---|---|
| M.N.M. Rashad | 23DA2-1040 | Linked List implementation and student-record management |
| M.A. Mohamed Rimas | 23DA2-0572 | Stack and Queue implementation and related operations |
| K.J.F. Salma Jifry | 23DA2-0654 | Graph implementation, campus locations, connections, and BFS/DFS traversal |
| G.B. Hasini Kushalya | 23DA2-0885 | BST/AVL tree implementation and hashing/search functionality |

**All Members:** Integration, validation, testing, debugging, documentation, 
GitHub collaboration, and completion of the entire project.

## Individual Contributions

### M.N.M. Rashad (23DA2-1040)
I implemented the StudentRecord and StudentLinkedList classes for managing student information in the system. I developed linked list operations to add, update, delete, search, and display student records. I also included duplicate Student ID validation to prevent the same student from being added more than once. I tested the linked list functions using sample student records to make sure each operation worked correctly. I also participated in the final integration testing to ensure my components worked correctly with the complete system.

### M.A. Mohamed Rimas (23DA2-0572)
I implemented the ActionStack and ServiceQueue classes as part of the system. I developed the stack operations to record and manage recent system actions using the LIFO principle, including push, pop, and display operations. I also implemented the service queue to manage student service requests using the FIFO principle, including adding, processing, and displaying requests. I tested both classes with sample data to make sure the stack and queue operations worked correctly. I also participated in the final integration testing to ensure my components worked properly with the other parts of the system.

### K.J.F. Salma Jifry (23DA2-0654)
I implemented the CampusGraph class for representing campus locations and their connections. I developed operations for adding and removing campus locations and creating and removing connections between locations. I also implemented BFS and DFS traversal methods to navigate through the campus network. I tested the graph operations using sample campus locations and connections to make sure the functions worked correctly. I also participated in final integration testing to verify that the graph component worked correctly with the complete application.

### G.B. Hasini Kushalya (23DA2-0885)
I created and managed the GitHub repository and implemented the StudentBST and StudentHashTable classes. I developed the BST functionality for organizing student records by Student ID and the hash table functionality for fast Student ID searching. I tested the tree and hashing operations with sample student records to verify that the functions worked correctly. I also coordinated the final integration of the different components into the complete menu-driven application and participated in testing, debugging, documentation, and GitHub collaboration.

## System Features

- Add, update, delete, search, and display student records
- Linked list for storing and managing student records
- Stack for tracking recent actions / undo history
- Queue for managing student service requests in order of arrival
- BST for organizing and searching students by Student ID
- Hash table (separate chaining) for fast Student ID search
- Graph (adjacency list) for campus locations and connections
- BFS and DFS traversal of the campus network
- Menu-driven console interface with input validation
- Handles invalid inputs, duplicate IDs/locations, missing records, 
  invalid marks, and unavailable connections

## Technology Used

- Java (JDK 17 or later)
- Visual Studio Code
- Git and GitHub for version control and collaboration

## How to Compile and Run
cd src/main/java
javac cit300/*.java
java cit300.Main


## Project Structure
Student-Record-Campus-Route-System/
├── src/
│ └── main/
│ └── java/
│ └── cit300/
│ ├── Main.java
│ ├── StudentRecord.java
│ ├── StudentLinkedList.java
│ ├── ActionStack.java
│ ├── ServiceQueue.java
│ ├── StudentBST.java
│ ├── StudentHashTable.java
│ └── CampusGraph.java
├── .gitignore
└── README.md


## GitHub Collaboration

This project was developed collaboratively using GitHub, with each member 
working on a separate feature branch and merging into `main` via Pull 
Requests:

- `feature/linked-list` – Rashad
- `feature/stack-queue` – Rimas
- `feature/graph` – Salma
- `feature/bst-hashing` – Hasini
- `integration` – final merge of all components into the complete menu-driven system