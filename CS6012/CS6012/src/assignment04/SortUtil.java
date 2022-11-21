package assignment04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class SortUtil<T> {
    private static int threshold_ = 6;

    public static <T> void insertionSort(ArrayList<T> arr, Comparator<? super T> comparator, int left, int right) {
        int index = left;
        while ( index <= right) {
            int j = index;
            while (j > 0 && comparator.compare(arr.get(j - 1), arr.get(j)) > 0) {
                swap(arr, j, j - 1);
                j--;
            }
            index++;
        }
    }

    public static <T> void mergesort(ArrayList<T> arr, Comparator<? super T> comparator) {
        T[] tempArr = (T[]) new Object[arr.size()];
        mergesortRecursive(arr, comparator, 0, arr.size() - 1, tempArr);
    }

    public static <T> void mergesortRecursive(ArrayList<T> arr, Comparator<? super T> comparator, int left, int right, T[] tempArr) {
        if(left >= right) {
            return;
        }
        int mid = (left + right) / 2;

        mergesortRecursive(arr, comparator, left, mid, tempArr);
        mergesortRecursive(arr, comparator, mid+1, right , tempArr);
        merge(arr, comparator, left, mid, right, tempArr);
    }

    public static <T> void merge(ArrayList<T> arr, Comparator<? super T> comparator, int left, int mid, int right, T[] tempArr) {

        if(right - left < threshold_ ){
            insertionSort(arr, comparator, left, right);
            return;
        }

        int one = left;
        int two = mid;
        int tempIterator = left;
        while(one < mid && two <= right) {
            if (comparator.compare(arr.get(one), arr.get(two)) > 0) {
                tempArr[tempIterator] = arr.get(two);
                two++;
            } else {
                tempArr[tempIterator] = arr.get(one);
                one++;
            }
            tempIterator++;
        }
        for (; one < mid; one++){
            tempArr[tempIterator] =arr.get(one);
            tempIterator++;
        }
        for (; two <= right; two++){
            tempArr[tempIterator] =arr.get(two);
            tempIterator++;
        }

        for(int i = left; i <= right; i++){
            arr.set(i, tempArr[i]);
        }

    }

    public static <T> void quicksort(ArrayList<T> arr, Comparator<? super T> comparator, int strategy) {
        quicksortRecursive(arr, comparator, 0, arr.size() - 1, strategy);

    }

    public static <T> void quicksortRecursive(ArrayList<T> arr, Comparator<? super T> comparator, int left, int right, int strategy) {

        if(left >= right)
            return;

        int pivot_index = partition(arr, comparator, left, right, strategy);
        quicksortRecursive(arr, comparator, left, pivot_index - 1, strategy);
        quicksortRecursive(arr, comparator, pivot_index + 1, right, strategy);
    }

    private static <T> int partition(ArrayList<T> arr, Comparator<? super T> comparator, int left, int right, int strategy) {

        T pivot;
        int pivot_index;

        if( strategy == 1){
            pivot_index = (right - left)/2 + left;
            pivot = arr.get(pivot_index);
        }
        else if( strategy == 2){
            pivot_index =(int) (Math.random() * (right - left)) + left;
            pivot = arr.get( pivot_index );

        }
        else {
            T first = arr.get(left);
            T middle = arr.get((right - left) / 2 + left);
            T last = arr.get(right - 1);

            ArrayList<T> pivots = new ArrayList<>();
            pivots.add( first );
            pivots.add( middle );
            pivots.add( last );
            pivots.sort(comparator);

            pivot = pivots.get(1);
            if(comparator.compare(first, pivots.get(1)) == 0){
                pivot_index = left;
            }
            else if(comparator.compare(last, pivots.get(1)) == 0){
                pivot_index = right - 1;
            }
            else{
                pivot_index = (right - left) / 2 + left;
            }

        }

        swap(arr, pivot_index, right);
        int L = left;
        int R = right - 1;
        while (L <= R) {
            if (comparator.compare(arr.get(L), pivot) < 0) {
                L++;
                continue;
            }
            if (comparator.compare(arr.get(R), pivot) > 0) {
                R--;
                continue;
            }
            swap(arr, L, R);
            L++;
            R--;
        }
        swap(arr, L , right);
        return L;
    }

    private static <T> void swap(ArrayList<T> arr, int left, int right) {
        T temp = arr.get(left);
        arr.set(left, arr.get(right));
        arr.set(right, temp);
    }

    public static ArrayList<Integer> generateBestCase(int size) {
        ArrayList<Integer> myArr = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            myArr.add(i+1);
        }
        return myArr;
    }

    public static ArrayList<Integer> generateAverageCase(int size) {
        ArrayList<Integer> myArr = generateBestCase(size);
        for (int i = myArr.size() - 1; i >= 0; i--) {
            swap(myArr, i, (int) (Math.random() * (i+1)));
        }
        return myArr;

    }

    public static ArrayList<Integer> generateWorstCase(int size) {
        ArrayList<Integer> myArr = new ArrayList<>(size);
        for (int i = size; i > 0; i--) {
            myArr.add(i);
        }
        return myArr;
    }

    public static void setThreshold(int input) {
        threshold_ = input;
    }
}
