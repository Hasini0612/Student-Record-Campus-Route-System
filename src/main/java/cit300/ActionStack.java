package cit300;

public class ActionStack {

    // Internal node class for the stack (linked-list based stack)
    private class Node {
        String actionDescription;
        Node next;

        Node(String actionDescription) {
            this.actionDescription = actionDescription;
            this.next = null;
        }
    }

    private Node top;
    private int size;
    private static final int MAX_HISTORY = 50; // cap history size

    public ActionStack() {
        top = null;
        size = 0;
    }

    // Push a new action description onto the stack
    public void pushAction(String actionDescription) {
        Node newNode = new Node(actionDescription);
        newNode.next = top;
        top = newNode;
        size++;

        // Optional cap: remove oldest if exceeding MAX_HISTORY
        if (size > MAX_HISTORY) {
            trimOldest();
        }
    }

    // Pop (undo) the most recent action. Returns null if stack is empty.
    public String popAction() {
        if (top == null) {
            return null;
        }
        String action = top.actionDescription;
        top = top.next;
        size--;
        return action;
    }

    // Peek at the most recent action without removing it
    public String peekAction() {
        if (top == null) {
            return null;
        }
        return top.actionDescription;
    }

    // Display all recent actions, most recent first
    public void displayActions() {
        if (top == null) {
            System.out.println("No recent actions recorded.");
            return;
        }
        Node current = top;
        System.out.println("----- Recent Actions (most recent first) -----");
        while (current != null) {
            System.out.println(current.actionDescription);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }

    // Helper: remove the oldest entry (bottom of stack) when history cap exceeded
    private void trimOldest() {
        if (top == null || top.next == null) return;
        Node current = top;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null; // drop the last node
        size--;
    }
}