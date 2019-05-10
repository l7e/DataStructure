package queue;

import array.DynamicArray;

public class ArrayQueue<E> implements Queue<E>
{
    private DynamicArray<E> data;

    public ArrayQueue(int capacity)
    {
        data = new DynamicArray<E>(capacity);
    }

    public ArrayQueue()
    {
        this(10);
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

    @Override
    public E getFront()
    {
        return data.getFirst();
    }

    @Override
    public void enqueue(E e)
    {
        data.addLast(e);
    }

    @Override
    public E dequeue()
    {
        return data.removeFirst();
    }

    public int getCapacity()
    {
        return data.getCapacity();
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Queue: ");
        builder.append("front [");
        for (int i = 0; i < data.getSize(); i++) {
            builder.append(data.get(i));
            if (i != data.getSize() - 1)
                builder.append(", ");
        }
        builder.append("] tail");

        return builder.toString();
    }
}
