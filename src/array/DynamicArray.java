package array;

public class DynamicArray<E>
{
    private E[] data;
    private int size;

    public DynamicArray()
    {
        this(10);
    }

    public DynamicArray(int capacity)
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
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. require index >= 0 and index <= size.");

        if (data.length == size)
            resize(data.length * 2);         //扩容,每次容量扩大1倍

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

        if (size == data.length / 4 && data.length / 2 != 0)      //当长度只有当前容量的1/4的时候,容量缩小为原容量的一半. 这个做是为了防止复杂度震荡
            resize(data.length / 2);

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

    //增缩容量
    private void resize(int capacity)
    {
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
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
