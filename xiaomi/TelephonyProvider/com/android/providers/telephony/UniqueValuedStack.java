package com.android.providers.telephony;

import android.util.Log;
import java.util.HashMap;

public class UniqueValuedStack<V> {
    private static final String TAG = "UniqueValuedStack";
    private Node mHead;
    private HashMap<V, Node> mValueToPrevNode;

    private class Node {
        public Node next;
        public V value;

        private Node() {
        }
    }

    public UniqueValuedStack() {
        this.mValueToPrevNode = new HashMap();
        this.mHead = new Node();
    }

    public void push(V value) {
        Node node;
        Node nextNode;
        Node prevNode = (Node) this.mValueToPrevNode.get(value);
        if (prevNode != null) {
            node = prevNode.next;
            nextNode = node.next;
            prevNode.next = nextNode;
            if (nextNode != null) {
                this.mValueToPrevNode.put(nextNode.value, prevNode);
            }
        } else {
            node = new Node();
            node.value = value;
        }
        node.next = this.mHead.next;
        nextNode = node.next;
        this.mHead.next = node;
        this.mValueToPrevNode.put(value, this.mHead);
        if (nextNode != null) {
            this.mValueToPrevNode.put(nextNode.value, node);
        }
    }

    public V pop() {
        Node node = this.mHead.next;
        if (node == null) {
            return null;
        }
        Node nextNode = node.next;
        this.mHead.next = nextNode;
        this.mValueToPrevNode.remove(node.value);
        if (nextNode != null) {
            this.mValueToPrevNode.put(nextNode.value, this.mHead);
        }
        return node.value;
    }

    public boolean isEmpty() {
        return this.mHead.next == null;
    }

    public void dump() {
        StringBuilder v = new StringBuilder("dump:");
        long remaining = 100;
        Node n = this.mHead.next;
        while (n != null && remaining > 0) {
            v.append(' ');
            v.append(n.value.toString());
            n = n.next;
            remaining--;
        }
        if (remaining > 0) {
            v.append("...");
        }
        Log.v(TAG, v.toString());
    }
}
