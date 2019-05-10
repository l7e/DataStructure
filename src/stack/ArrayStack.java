package stack;

import array.DynamicArray;

public class ArrayStack<E> implements Stack<E>
{
    private DynamicArray<E> data;

    public ArrayStack(int capacity)
    {
        data = new DynamicArray<>(capacity);
    }

    public ArrayStack()
    {
        this(10);
    }

    @Override
    public void push(E e)
    {
        data.addLast(e);
    }

    @Override
    public E pop()
    {
        return data.removeLast();
    }

    @Override
    public E peek()
    {
        return data.getLast();
    }

    @Override
    public int getSize()
    {
        return data.getSize();
    }

    @Override
    public boolean isEmpty()
    {
        return data.isEmpty();
    }

    public int getCapacity()
    {
        return data.getCapacity();
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Stack: ");
        builder.append("[");
        for (int i = 0; i < data.getSize(); i++) {
            builder.append(data.get(i));
            if (i != data.getSize() - 1)
                builder.append(", ");
        }
        builder.append("] Top");
        return builder.toString();
    }
}
