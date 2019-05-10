package array;

public class GenericityArray<E>
{
    private E[] data;
    private int size;

    public GenericityArray()
    {
        this(10);
    }

    public GenericityArray(int capacity)
    {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //获取元素个数
    public int getSize()
    {
        return size;
    }

    //获取数组的容量
    public int getCapacity()
    {
        return data.length;
    }

    //数组是否为空
    public boolean isEmpty()
    {
        return size == 0;
    }

    //向尾部添加
    public void addLast(E element)
    {
        add(size, element);
    }

    //向头部添加
    public void addFirst(E element)
    {
        add(0, element);
    }

    //向指定索引添加
    public void add(int index, E element)
    {
        if (data.length == size)
            throw new IllegalArgumentException("Add failed. Array is full.");

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. require index >= 0 and index <= size.");

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    //删除指定索引的值
    public E remove(int index)
    {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Remove failed. index is Illegal.");

        E ret = data[index];

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }

        size--;
        data[size] = null;
        return ret;
    }

    //删除首元素
    public E removeFirst()
    {
        return remove(0);
    }

    //删除尾元素
    public E removeLast()
    {
        return remove(size - 1);
    }

    //删除指定元素,有就删除,没有就什么都不干.注意,这里删除的是第一个这样的元素,因为数组中可能会有很多相同的元素
    public void removeElement(E element)
    {
        int index = find(element);
        if (index != -1)
            remove(index);
    }

    //获取指定索引的值
    public E get(int index)
    {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. index is Illegal");
        }

        return data[index];
    }

    //更改指定索引的值
    public void set(int index, E element)
    {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. index is Illegal");
        }

        data[index] = element;
    }

    //是否包含元素
    public boolean contains(E element)
    {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element))
                return true;
        }

        return false;
    }

    //根据元素找索引
    public int find(E element)
    {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element))
                return i;
        }

        return -1;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array:size = %d , capacity = %d\n", size, data.length));
        builder.append("[");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");

        return builder.toString();
    }
}
