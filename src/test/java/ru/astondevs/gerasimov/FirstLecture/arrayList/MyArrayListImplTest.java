package ru.astondevs.gerasimov.FirstLecture.arrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.astondevs.gerasimov.FirstLecture.QuickSortImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

class MyArrayListImplTest {

    @Test
    void size() {
        //
        //  Given
        //
        MyArrayList<Integer> testArray = new MyArrayListImpl<>();
        testArray.add(1);
        testArray.add(2);

        //
        // When
        //
        int size = testArray.size();

        //
        // Then
        //
        assertThat(size, is(2));
        assertThat(size, not(3));
    }

    @Test
    void currentCapacity() {
        //
        //  Given
        //
        MyArrayList<Integer> defaultCapacityArray = new MyArrayListImpl<>();
        MyArrayList<Integer> customCapacityArray = new MyArrayListImpl<>(15);
        defaultCapacityArray.add(1);
        defaultCapacityArray.add(2);
        customCapacityArray.add(1);
        customCapacityArray.add(2);
        customCapacityArray.add(3);

        //
        // When
        //
        int defaultCapacity = defaultCapacityArray.currentCapacity();
        int customCapacity = customCapacityArray.currentCapacity();

        //
        // Then
        //
        assertThat(defaultCapacity, is(10));
        assertThat(customCapacity, is(15));
    }

    @Test
    void isEmpty() {
        //
        //  Given
        //
        MyArrayList<Integer> emptyList = new MyArrayListImpl<>();
        MyArrayList<Integer> notEmptyList = new MyArrayListImpl<>();
        notEmptyList.add(1);

        //
        // When
        //
        boolean isArrayEmpty = emptyList.isEmpty();
        boolean notEmptyArray = notEmptyList.isEmpty();

        //
        // Then
        //
        assertThat(isArrayEmpty, is(true));
        assertThat(notEmptyArray, is(false));
    }

    @Test
    void addElement() {
        //
        //  Given
        //
        MyArrayList<Integer> myArrayList = new MyArrayListImpl<>();
        myArrayList.add(1);
        myArrayList.add(2);
        MyArrayList<Integer> expectedList = new MyArrayListImpl<>();
        expectedList.add(1);
        expectedList.add(2);
        expectedList.add(3);

        //
        // When
        //
        boolean isAdded = myArrayList.add(3);
        Integer addedElem = myArrayList.get(myArrayList.size() - 1);

        //
        // Then
        //
        assertThat(isAdded, is(true));
        assertThat(myArrayList, is(expectedList));
    }

    @Test
    void addByIndex() {
        //
        //  Given
        //
        MyArrayList<Integer> myArrayList = new MyArrayListImpl<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        MyArrayList<Integer> expectedList = new MyArrayListImpl<>();
        expectedList.add(1);
        expectedList.add(4);
        expectedList.add(2);
        expectedList.add(3);

        //
        // When
        //
        myArrayList.add(1, 4);

        //
        // Then
        //
        assertThat(myArrayList, is(expectedList));
    }

    @Test
    void addByIndexOutOfBound() {
        //
        //  Given
        //
        MyArrayList<Integer> myArrayList = new MyArrayListImpl<>();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);

        //
        // When
        //
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.add(5, 4));
    }

    @Test
    void removeElem() {
        //
        //  Given
        //
        MyArrayList<String> myArrayList = new MyArrayListImpl<>();
        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("3");
        myArrayList.add("4");
        MyArrayList<String> expectedList = new MyArrayListImpl<>();
        expectedList.add("1");
        expectedList.add("2");
        expectedList.add("4");

        //
        // When
        //
        myArrayList.remove("3");

        //
        // Then
        //
        assertThat(myArrayList, is(expectedList));
    }

    @Test
    void removeByIndex() {
        //
        //  Given
        //
        MyArrayList<String> myArrayList = new MyArrayListImpl<>();
        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("3");
        myArrayList.add("4");
        MyArrayList<String> expectedList = new MyArrayListImpl<>();
        expectedList.add("1");
        expectedList.add("2");
        expectedList.add("4");
        int removeFromIndex = 2;

        //
        // When
        //
        myArrayList.remove(2);

        //
        // Then
        //
        assertThat(myArrayList, is(expectedList));
    }

    @Test
    void removeWithWrongIndex() {
        //
        //  Given
        //
        MyArrayList<String> myArrayList = new MyArrayListImpl<>();
        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("3");

        //
        // When
        //
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.remove(5));
    }

    @Test
    void find() {
        //
        //  Given
        //
        MyArrayList<String> myArrayList = new MyArrayListImpl<>();
        myArrayList.add("1");
        myArrayList.add("4");
        myArrayList.add("3");
        myArrayList.add("4");
        int expectedIndex = 1;
        //
        // When
        //
        int actualIndex = myArrayList.find("4");

        //
        // Then
        //
        assertThat(actualIndex, is(expectedIndex));
    }

    @Test
    void get() {
        //
        //  Given
        //
        MyArrayList<String> myArrayList = new MyArrayListImpl<>();
        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("3");
        myArrayList.add("4");
        String expectedElem = "3";

        //
        // When
        //
        String actualElem = myArrayList.get(2);

        //
        // Then
        //
        assertThat(actualElem, is(expectedElem));
    }

    @Test
    void getWithWrongIndex() {
        //
        //  Given
        //
        MyArrayList<String> myArrayList = new MyArrayListImpl<>();
        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("3");

        //
        // When
        //
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.get(3));
    }

    @Test
    void set() {
        //
        //  Given
        //
        MyArrayList<String> myArrayList = new MyArrayListImpl<>();
        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("3");
        myArrayList.add("4");
        String expectedElem = "5";
        MyArrayList<String> expectedList = new MyArrayListImpl<>();
        expectedList.add("1");
        expectedList.add("5");
        expectedList.add("3");
        expectedList.add("4");
        //
        // When
        //
        String actualElem = myArrayList.set(1, "5");

        //
        // Then
        //
        assertThat(actualElem, is(expectedElem));
        assertThat(myArrayList, is(expectedList));
    }

    @Test
    void setWithWrongIndex() {
        //
        //  Given
        //
        MyArrayList<String> myArrayList = new MyArrayListImpl<>();
        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("3");

        //
        // When
        //
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.set(5, "6"));
    }

    @Test
    void comparableQuickSort() {
        //
        //  Given
        //
        MyArrayList<String> myArrayList = new MyArrayListImpl<>();
        myArrayList.add("3");
        myArrayList.add("5");
        myArrayList.add("1");
        myArrayList.add("8");
        MyArrayList<String> expectedList = new MyArrayListImpl<>();
        expectedList.add("1");
        expectedList.add("3");
        expectedList.add("5");
        expectedList.add("8");
        //
        // When
        //
        QuickSortImpl.quickSort(myArrayList);

        //
        // Then
        //
        assertThat(myArrayList, is(expectedList));
    }

    @Test
    void quickSortWithComparator() {
        //
        //  Given
        //
        MyArrayList<Integer> myArrayList = new MyArrayListImpl<>();
        myArrayList.add(3);
        myArrayList.add(5);
        myArrayList.add(1);
        myArrayList.add(8);
        MyArrayList<Integer> expectedList = new MyArrayListImpl<>();
        expectedList.add(8);
        expectedList.add(5);
        expectedList.add(3);
        expectedList.add(1);
        //
        // When
        //
        QuickSortImpl.quickSortWithComparator(myArrayList, (o1, o2) -> o2 - o1);

        //
        // Then
        //
        assertThat(myArrayList, is(expectedList));
    }
}
