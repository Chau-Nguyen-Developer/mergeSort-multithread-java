public class Mergesort
{
    public static void main(String args[])
    {
        int[] array = {8, 2, 5, 3, 4, 7, 6, 1};
        int len = array.length;
        int middle = len / 2;

        //Create left array
        int[] bigLeftArray = new int[middle];

        //Copy elements from main array to sub-left array
        for (int i = 0; i < middle; ++i)
        {
            bigLeftArray[i] = array[i];
        }

        //Create right array
        int remainLen = len - middle;
        int[] bigRightArray = new int[remainLen];

        //Copy elements on second half part of the main array to right array.
        for (int i = 0; i < remainLen; ++i)
        {
            bigRightArray[i] = array[middle + i];
        }

        //Create two threads to sort each half
        System.out.println("Creating and doing merge sort on threads...");
        Thread leftThread = new Thread(() -> mergeSort(bigLeftArray));
        Thread rightThread = new Thread(() -> mergeSort(bigRightArray));

        //Start two threads
        leftThread.start();
        rightThread.start();

        //Wait for both threads to finish
        try
        {
            leftThread.join();
            rightThread.join();
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
            System.out.println("Error(s) happen when waiting for threads to join.");
        }

        merge(bigLeftArray, bigRightArray, array);

        System.out.println("Merge sort finishes.");
        System.out.println("Result: ");
        //mergeSort(array);

        for (int i = 0; i < len; ++i)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        
    }

    //PRIVATE FUNCTIONS
    //RECURSIVE MERGESORT FUNCTION
    private static void mergeSort(int[] givenArray)
    {
        int len = givenArray.length;
        //IMPORTATNT: BASE CASE
        if(len <= 1)
        {
            return;
        }
        //else
        //Find two sub-array
        int mid = len / 2;
        int[] leftArray = new int[mid];

        //copy elements from given array to left array
        for (int i = 0; i < mid; ++i)
        {
            leftArray[i] = givenArray[i];
        }

        //copy elements from given array to right array
        int rightLen = len - mid;
        int[] rightArray = new int[rightLen];

        for(int i = 0; i < rightLen; ++i)
        {
            rightArray[i] = givenArray[mid + i];
        }
       
        //Call regular recursive merge sort function on these two sub arrays.
        //No new threads here. 
        mergeSort(leftArray);
        mergeSort(rightArray);

        //After mergeSort functions finish and return, put everything back together
        merge(leftArray, rightArray, givenArray);
    }

    //worry that values are pass by integer 
    private static void merge(int[]leftArray, int[] rightArray, int[] mergedArray)
    {
        int leftLen = leftArray.length;
        int rightLen = rightArray.length;
        int totalLen = leftLen + rightLen;
        int indexLeft = 0;
        int indexRight = 0;
        int mainIndex = 0;

        //Use while loop to compare elements from left array and right array
        while(indexLeft < leftLen && indexRight < rightLen)
       {
            //Choose smaller elements btw two arrays to put it in main array 
            if(leftArray[indexLeft] < rightArray[indexRight])
            {
                mergedArray[mainIndex] = leftArray[indexLeft];
                ++indexLeft;
            }
            else if (leftArray[indexLeft] > rightArray[indexRight])
            {
                mergedArray[mainIndex] = rightArray[indexRight];
                ++indexRight;
            }
            else //two elements equal
            {
                mergedArray[mainIndex] = leftArray[indexLeft];
                ++mainIndex;
                mergedArray[mainIndex] = rightArray[indexRight];

            }
            ++mainIndex;
       }

        //In case left array still have elements
        while(indexLeft < leftLen)
        {
            mergedArray[mainIndex] = leftArray[indexLeft];
            ++indexLeft;
            ++mainIndex;
        }

        //In case right array still have elements
        while(indexRight < rightLen)
        {
            mergedArray[mainIndex] = rightArray[indexRight];
            ++mainIndex;
            ++indexRight;
        }

    }
}