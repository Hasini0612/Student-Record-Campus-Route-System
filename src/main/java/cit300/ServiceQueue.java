package cit300;

public class ServiceQueue {

    // Internal node class for the queue (linked-list based queue)
    private class Node {
        String requestDescription;
        Node next;

        Node(String requestDescription) {
            this.requestDescription = requestDescription;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public ServiceQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Add a new service request to the back of the queue
    public void addRequest(String requestDescription) {
        Node newNode = new Node(requestDescription);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // Process (remove and return) the next request in line. Returns null if empty.
    public String processNextRequest() {
        if (front == null) {
            return null;
        }
        String request = front.requestDescription;
        front = front.next;
        if (front == null) {
            rear = null; // queue is now empty
        }
        size--;
        return request;
    }

    // Peek at the next request without removing it
    public String peekNextRequest() {
        if (front == null) {
            return null;
        }
        return front.requestDescription;
    }

    // Display all pending requests in order
    public void displayQueue() {
        if (front == null) {
            System.out.println("No pending service requests.");
            return;
        }
        Node current = front;
        System.out.println("----- Pending Service Requests (order of arrival) -----");
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.requestDescription);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }
}