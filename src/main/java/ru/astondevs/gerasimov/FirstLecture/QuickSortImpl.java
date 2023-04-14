package ru.astondevs.gerasimov.FirstLecture;

import ru.astondevs.gerasimov.FirstLecture.arrayList.MyArrayList;

import java.util.Comparator;

/**
 * Утильный класс, реализующий алгоритм быстрой сортировки при помощи рекурсии.
 */
public class QuickSortImpl {

    /**
     * Статический метод, который принимает динамический обобщенный список, необходимый для сортировки.
     * Объекты списка должны принадлежать классу, реализующему интерфейс Comparable.
     * В методе вызывается метод @recQuickSort куда передаются кроме листа, начальный и конечный индексы
     * подмассива, который необходимо отсортировать.
     */
    public static <T extends Comparable<? super T>> void quickSort(MyArrayList<T> list) {
        recQuickSort(list, 0, list.size() - 1);
    }

    /**
     * Статический метод, который принимает динамический обобщенный список, необходимый для сортировки.
     * Также в метод передается объект реализующий интерфейс Comparator и описывающий правила сортировки.
     * В методе вызывается метод @recQuickSort куда передаются кроме листа, начальный и конечный индексы
     * подмассива, который необходимо отсортировать.
     */
    public static <T> void quickSortWithComparator(MyArrayList<T> list, Comparator<T> comparator) {
        recQuickSortWithComparator(comparator, list, 0, list.size() - 1);
    }

    /**
     * Рекурсивная реализация быстрой сортировки.
     * Если переданный лист состоит из одного элемента - он считается отсортированным.
     * Если элементов в листе больше одного, опорный элемент берется крайний правый элемент.
     * Выполняется разделение массива на два подмассива (больших и меньших опорного элемента)
     * и возвращается индекс опорного элемента.
     * Далее рекурсивно вызывается метод @recQuickSort, куда передаются индексы левого и правого
     * подмассивов.
     */
    private static <T extends Comparable<? super T>> void recQuickSort(MyArrayList<T> list, int left, int right) {
        if (right - left > 0) {
            T pivot = list.get(right);
            int partition = partitionIt(list, left, right, pivot);
            recQuickSort(list, left, partition - 1);
            recQuickSort(list, partition + 1, right);

        }
    }

    /**
     * Аналогичный @recQuickSort метод. Сортировка производится на основании правил описанных аргументом @comparator.
     */
    private static <T> void recQuickSortWithComparator(Comparator<T> comparator, MyArrayList<T> list, int left, int right) {
        if (right - left > 0) {
            T pivot = list.get(right);
            int partition = partitionItWithComparator(comparator, list, left, right, pivot);
            recQuickSortWithComparator(comparator, list, left, partition - 1);
            recQuickSortWithComparator(comparator, list, partition + 1, right);

        }
    }

    /**
     * Метод принимает лист, индексы, описывающие диапазон разбиваемого подмассива и значение опорного элемента,
     * относительно которого массив разбивается на два подмассива.
     * Два указателя с лева и справа перебирают все элемента массива до тех пор, пока не встретятся друг с другом,
     * если левый указатель указывает на элемент больший опорного, а правый указывает на элемент меньший опорного,
     * элементы меняются местами.
     * После разбиения массива на два подмассива, правый крайний опорный элемент меняется местами с левым элементом
     * и занимает место между двумя массивами (меньшим и большим).
     * Метод возвращает индекс опорного элемента, установленного между двумя разделенными массивами.
     */
    private static <T extends Comparable<? super T>> int partitionIt(MyArrayList<T> list, int left, int right, T pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {
            while (list.get(++leftPtr).compareTo(pivot) < 0) ;
            while (rightPtr > 0 && list.get(--rightPtr).compareTo(pivot) > 0) ;
            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(list, leftPtr, rightPtr);
            }
        }
        swap(list, leftPtr, right);
        return leftPtr;
    }

    /**
     * Аналогичный @partitionIt метод. Сравнение объектов при разбиении массива на подмассивы производится
     * на основании правил описанных аргументом @comparator.
     */
    private static <T> int partitionItWithComparator(Comparator<T> comparator, MyArrayList<T> list, int left, int right, T pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {

            while (comparator.compare(list.get(++leftPtr), pivot) < 0) ;
            while (rightPtr > 0 && comparator.compare(list.get(--rightPtr), pivot) > 0) ;
            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(list, leftPtr, rightPtr);
            }
        }
        swap(list, leftPtr, right);
        return leftPtr;
    }

    /**
     * Вспомогательный метод, который меняет два элемента массива местами.
     */
    private static <T> void swap(MyArrayList<T> list, int dex1, int dex2) {
        T temp;
        temp = list.get(dex1);
        list.set(dex1, list.get(dex2));
        list.set(dex2, temp);
    }
}
