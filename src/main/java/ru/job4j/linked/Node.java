package ru.job4j.linked;

public final class Node<T> {
    final private Node<T> next;
    final private T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        Node<T> copyOfNode = new Node<>(next, value);
        return copyOfNode.next;
    }

    public T getValue() {
        Node<T> copyOfNode = new Node<>(next, value);
        return copyOfNode.value;
    }
}