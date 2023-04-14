package ru.astondevs.gerasimov.FirstLecture.arrayList;

import java.util.Arrays;
import java.util.Objects;

/**
 * Обобщенный класс реализующий интерфейс MyArrayList и описывающий абстрактный тип данных -
 * динамически расширяемый массив.
 * Класс реализован на базе массива Object - data.
 * По умолчанию массив создается с размером равным 10 (DEFAULT_CAPACITY).
 * Переменная size - указывает на текущее кол-во элементов, которое содержится в массиве.
 */
public class MyArrayListImpl<T> implements MyArrayList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    Object[] data;


    /**
     * Конструктор по умолчанию. В случае его применения, при создании объекта класса, внутри вызывается конструктор
     * с аргументом customCapacity, куда передается размер массива по умолчанию (DEFAULT_CAPACITY) равный десяти.
     */
    public MyArrayListImpl() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Конструктор принимает размера массива и создает внутренний массив со значением длинны, переданной
     * в параметры конструктора.
     * Если переданный параметр меньше 0, выбрасывается IllegalArgumentException.
     */
    public MyArrayListImpl(int customCapacity) {
        if (customCapacity < 0) {
            throw new IllegalArgumentException("Capacity cant be less then 0");
        }
        data = new Object[customCapacity];
    }

    /**
     * Возвращает количество элементов, которое содержится в массиве.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Возвращает текущую длину внутреннего массива.
     */
    @Override
    public int currentCapacity() {
        return data.length;
    }

    /**
     * Проверяет пуст ли наш динамический массив. Если в массиве не содержится ни одного элемента, он считается пустым,
     * иначе заполненным.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Добавляет переданный элемент в динамический массив.
     * Перед добавлением проверяется, достаточно ли места во внутреннем массиве. Если внутренний массив заполнен,
     * вызывается приватный вспомогательный метод resizeData(), который пересоздает внутренний массив, увеличивая его,
     * и копируя все значения в новый внутренний массив.
     */
    @Override
    public boolean add(T elem) {
        if (size == data.length) {
            resizeData();
        }
        data[size++] = elem;
        return true;
    }

    /**
     * Добавляет переданный элемент в динамический массив по заданному индексу.
     * Перед добавлением проверяется, не находится ли индекс за пределами массива. Если это так, выбрасывается
     * IndexOutOfBoundsException().
     * Также проверяется, достаточно ли места во внутреннем массиве. Если внутренний массив заполнен,
     * вызывается приватный вспомогательный метод resizeData(), который пересоздает внутренний массив, увеличивая его,
     * и копируя все значения в новый внутренний массив.
     */
    @Override
    public void add(int idx, T elem) {
        checkIndexForChangeArray(idx);
        if (size == data.length) {
            resizeData();
        }
        System.arraycopy(data, idx, data, idx + 1, size - idx);
        data[idx] = elem;
        size++;
    }

    /**
     * Удаляет элемент по переданному индексу. Перед удалением проверяется, не выходит ли заданный индекс
     * за пределы массива.
     * После чего вычисляется сколько элементов нужно сдвинуть с конца к середине, удаляемый элемент затирается.
     * После успешной операции сдвига, размера массива уменьшается на единицу и возвращается удаленный элемент.
     */
    @Override
    public T remove(int idx) {
        checkIndexForChangeArray(idx);
        T removedElem = (T) data[idx];
        int totalCopyElem = size - idx - 1;
        System.arraycopy(data, idx + 1, data, idx, totalCopyElem);
        size--;
        return removedElem;
    }

    /**
     * Удаление переданного элемента. Находим индекс искомого элемента. Если элемента в коллекции нет,
     * возвращаем null. Если элемент найден, то вызываем метод удаления элемента по индексу remove(int idx).
     */
    @Override
    public T remove(T elem) {
        int idx = find(elem);
        if (idx == -1) {
            return null;
        }
        return remove(idx);
    }

    /**
     * Ищет переданный элемент в коллекции перебором всех элементов. Если элемента в коллекции нет, возвращается
     * значение -1. Возвращает индекс первого вхождения элемента в коллекцию.
     */
    @Override
    public int find(T elem) {
        int elemNotFound = -1;
        for (int i = 0; i < data.length; i++) {
            if (elem.equals(data[i])) {
                return i;
            }
        }
        return elemNotFound;
    }

    /**
     * Возвращает элемент, который находится по переданному индексу.
     * Если заданный индекс находится за пределами массива, выкидывает IndexOutOfBoundsException.
     */
    @Override
    public T get(int idx) {
        checkIndexForGetAndSet(idx);
        return (T) data[idx];
    }

    /**
     * Устанавливает новое значение по указанному индексу.
     * Если заданный индекс находится за пределами массива, выкидывает IndexOutOfBoundsException.
     */
    @Override
    public T set(int index, T element) {
        checkIndexForGetAndSet(index);
        data[index] = element;
        return element;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                result.append(data[i] + "]");
            } else {
                result.append(data[i]).append(", ");
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof MyArrayList)) {
            return false;
        }

        if (this.size != ((MyArrayList<?>) o).size()) {
            return false;
        }

        for (int i = 0; i < this.size; i++) {
            if (this.get(i) != ((MyArrayList<?>) o).get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    /**
     * Проверка индекса на вхождение в диапазон массива.
     * Индекс может ровняться переменной size.
     * Используется в методах @remove() и @add
     */
    private void checkIndexForChangeArray(int idx) {
        if (idx > size || idx < 0)
            throw new IndexOutOfBoundsException();
    }

    /**
     * Проверка индекса на вхождение в диапазон массива.
     * Индекс не может ровняться переменной size.
     * Используется в методах @get и @set
     */
    private void checkIndexForGetAndSet(int idx) {
        if (idx >= size || idx < 0)
            throw new IndexOutOfBoundsException();
    }

    /**
     * Вспомогательный метод, который увеличивает внутренний массив в 1.5 раза.
     */
    private void resizeData() {
        Object[] tmp = data;
        data = new Object[(data.length * 3) / 2];
        System.arraycopy(tmp, 0, data, 0, tmp.length);
    }
}
