package entity;

import exception.CustomException;

import java.util.Arrays;

public class SelfMadeArray {

    private int[] array;

    public SelfMadeArray() {
    }

    public SelfMadeArray(int... array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    public int[] getArray() {
        return Arrays.copyOf(this.array, this.array.length);
    }

    public void setArray(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    public int getSize() {
        return this.array.length;
    }

    public int getElementByPositionInArray(int positionOfElementInArray) throws CustomException {
        if (positionOfElementInArray < 0 || positionOfElementInArray >= this.array.length) {
            throw new CustomException("Index out of bounds");
        } else {
            return array[positionOfElementInArray];
        }
    }

    public void setElement(int positionOfElementInArray, int value) throws CustomException {
        if (positionOfElementInArray < 0 || positionOfElementInArray >= this.array.length) {
            throw new CustomException("Index out of bounds");
        } else {
            array[positionOfElementInArray] = value;
        }
    }

    public void removeElement(int positionOfElementInArray) throws CustomException {
        if (positionOfElementInArray < 0 || positionOfElementInArray >= this.array.length) {
            throw new CustomException("Index out of bounds");
        } else {
            array[positionOfElementInArray] = 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Array content: ");
        for (int element : array) {
            stringBuilder.append(element).append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SelfMadeArray that = (SelfMadeArray) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
