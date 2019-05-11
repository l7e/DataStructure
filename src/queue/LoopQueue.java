package queue;


public class LoopQueue<E> implements Queue<E>
{
    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity)
    {
        data = (E[]) new Object[capacity + 1];  //因为要浪费一个位置,所以 +1
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue()
    {
        this(10);
    }


    public int getCapacity()
    {
        return data.length - 1;
    }


    @Override
    public int getSize()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return front == tail;     //当front等于tail说明队列是空
    }

    @Override
    public E getFront()
    {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty");

        return data[front];
    }

    @Override
    public void enqueue(E e)
    {
        if ((tail + 1) % data.length == front)
            resize(getCapacity() * 2);

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue()
    {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }

    private void resize(int newCapacity)
    {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Queue:size = %d , capacity = %d\n", size, getCapacity()));
        builder.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            builder.append(data[i]);
            if ((i + 1) % data.length != tail) {
                builder.append(", ");
            }
        }
        builder.append("] tail");

        return builder.toString();
    }


    public static void main(String[] args)
    {
        LoopQueue<Object> queue = new LoopQueue<>();

        for (int i = 0; i < 15; i++) {
            queue.enqueue(i);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);

            }
            System.out.println(queue.getCapacity());
            System.out.println(queue);
        }
    }
}
