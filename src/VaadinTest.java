import java.util.Arrays;
import java.util.HashMap;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.nio.file.Files.find;

public class VaadinTest {

    int solution(int[] A) {
        System.out.println("hhh" + A.length);
        int n = A.length;
        int smallest = 0;

        return smallest;
    }

    int binaryGap(int a) {
        int maxGap = 0;
        int count = 0;
        String st = Integer.toBinaryString(a);
        System.out.println(st);
        System.out.println(st.length());
        for (int i = 0; i < st.length(); i++) {

            if (Integer.valueOf(st.substring(i, i + 1)) == 0) count++;
            if (Integer.valueOf(st.substring(i, i + 1)) == 1 && count != 0) {
                if (count > maxGap) maxGap = count;

                count = 0;
            }
        }
        return maxGap;
    }


    int countDigits(int b) {
       /* int length = 0;
        long temp = 1;
        while(temp <= b){
            length++;
            temp *= 10;
        }*/
        return String.valueOf(b).length();
    }

    ////////////////////////////////////////////////////////////////////////////
 /* This function takes last element as pivot,
       places the pivot element at its correct
       position in sorted array, and places all
       smaller (smaller than pivot) to left of
       pivot and all greater elements to right
       of pivot */
    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    void quickSort(int arr[], int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");

    }


    //////////////////////////////////////////////////////////////////////////////////////////////////
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    /* A utility function to print array of size n */
 /*   static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }*/

  /*  // Driver code
    public static void main(String args[])
    {
        int arr[] = { 12, 11, 13, 5, 6, 7 };

        System.out.println("Given Array");
        printArray(arr);

        MergeSort ob = new MergeSort();
        ob.sort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array");
        printArray(arr);
    }*/
//////////////////////////////////////////////////////////////////////////////////////////////

    int distinct(int arr[]) {
        int count = arr.length;
        int rep = 0;
        mergeSort(arr, 0, count - 1);

        for (int i = 0; i < count; i++) {
            while (i < count - 1 && arr[i] == arr[i + 1]) {
                rep++;
            }
        }


        return rep;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    void revertSentenses(String p[]) {
        int y = p.length - 1;
        String s[] = new String[y + 1];

        for (int i = 0; i <= y; i++) {
            s[y - i] = p[i];
        }

// print
        for (int j = 0; j <= y; j++) {
            System.out.print(s[j] + " ");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    int inversion(int[] arr) {
        int count = 0;
        int l = arr.length;

        for (int i = 0; i < l; i++) {
            for (int j = i; j < l; j++) {
//                System.out.println("arr["+i+"]" + "  !  " +"arr["+j+"]" );
                if (arr[i] > 0 && arr[j] > 0 && arr[i] > arr[j]) {
                    count++;
                }
            }
        }

        return count;
    }

    /////////////////////////////////////////////////////////////////
    int inversionOn(int[] A) {


        int[] top = new int[A.length];
        int max = -Integer.MAX_VALUE;
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] > max) max = A[i];
            top[i] = max;
        }

        int best = 0;
        int curMaxIndex = 0;
        for (int i = 0; i < A.length; i++) {
            while (curMaxIndex < top.length && top[curMaxIndex] >= A[i]) curMaxIndex++;
            if ((curMaxIndex - 1 - i) > best) best = curMaxIndex - 1 - i;
        }

        return best;

    }
    /////////////////////////////////////////////////////////////////

    int perfectSquaare(int a) {
        int biggest = 0;
        biggest = (int) Math.floor(Math.sqrt(a)) + 1;
        return biggest * biggest;
    }

    /////////////////////////////////////////////////////////////////
    int oddOccurrencesInArray(int[] A) {
        int unpaired = 0;
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            if (A[i] != A[i + 1]) {
                unpaired = A[i];
                if (A[i + 1] == A[i + 2]) {
                    return unpaired;
                }
            }

        }
        return unpaired;
    }

    /////////////////////////////////////////////////////////////////
    int[] cycleArrayRotatation(int[] A, int K) {
        // Using the concept of "mod" (to make it cyclic)

        int[] new_array = new int[A.length]; // a new array

        for (int i = 0; i < A.length; i++) {
            int new_position = (i + K) % A.length; // using "mod" to do Cyclic Rotation
//            System.out.println("new_position = " + new_position);
            new_array[new_position] = A[i]; // put A[i] to the new position
        }
        System.out.println(Arrays.toString(new_array));
        return new_array; // return new array
      /*  if (k == A.length) {
            return A;

        } else {
            int[] rotatedArray = new int[A.length];

            rotatedArray[0] = A[k - 1];
            int up = 1;


            for (int i = k; i < A.length; i++) {
                rotatedArray[up++] = A[i];
            }
            for (int j = 0; j <= k - 2; j++) {
                rotatedArray[up++] = A[j];
            }


            printArray(rotatedArray);

            return rotatedArray;
        }*/
    }

    /////////////////////////////////////////////////////////////////
    /*public int solution2(int n) {
        // write your code in Java SE 8
        if(n < 10) return n;
        return Math.min(n%10, find(n/10));



    }*/

    /////////////////////////////////////////////////////////////////
/*

    @Test
    public void exampleTest() {
        Version version = new Version("3.8.0");
        // ...
        if(version.){}
    }

    @Test
    public void exampleTest2() {
        Version version = new Version("3.8.0-SNAPSHOT");
        // ...
    }

    // expected error messages:

    static final String errorVersionMustNotBeNull = "'version' must not be null!";
    static final String errorOtherMustNotBeNull =  "'other' must not be null!";
    static final String errorVersionMustMatchPattern =  "'version' must match: 'major.minor.patch(-SNAPSHOT)'!";*/
    /////////////////////////////////////////////////////////////////

}
