public class Mergesort
{
    public static void main(String args[])
    {
        int[] array = {8, 2, 5, 3, 4, 7, 6, 1};
        int len = array.length;

        mergeSort(array);

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
       
        //Call recursive merge sort function on these two sub arrays. 
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