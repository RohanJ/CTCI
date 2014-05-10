/*==========NOTES==========
@author: Rohan Jyoti
@compile: javac c11.java
@run: java c11 [function name] [function arguments]
@project: Concepts And Algorithms
@purpose: Chapter 11 Sorting and Searching
=========================*/

//==========IMPORTS
import java.util.*;
import java.lang.String;
//==========

public class c11{
	public static class Person implements Comparable{
		int height;
		int weight;
		public Person(int height, int weight){
			this.height = height;
			this.weight = weight;
		}
		public String toString(){
			return "(height: " + height + ", weight: " + weight+")";
		}

		public int compareTo(Object s){
			Person second = (Person)s;
			if(this.height != second.height){
				return ((Integer)this.height).compareTo(second.height);
			}
			else{
				return ((Integer)this.weight).compareTo(second.weight);
			}
		}
	}

	public static void main(String[] args) throws Exception{
		SOP("==========Chapter 11 Sorting and Searching===");
        SOP("To run: java c11 [function name] [function arguments]");
        SOP("Example: java c11 sort 1 4 5 3 2");
        SOP("");
        SOP("Possible functions:");
        
        SOP("bubblesort\tBubblesort");
        SOP("selectionsort\tSelectionsort");
        SOP("melectionsort\tMelectionsort");
        SOP("q1\tQuestion 1");
        SOP("q3\tQuestion 3");
        SOP("q4\tQuestion 4");
        SOP("q5\tQuestion 5");
        SOP("q6\tQuestion 6");
        SOP("q7\tQuestion 7");
        SOP("LIS\tLongest Increasing Subsequence");
        SOP("===============================");
        SOP("");

        if(args.length < 1){
            SOP("ERROR: Must specify function to run");
            return;
        }

        String function = args[0];
        
        if(function.equals("bubblesort")) runBubblesort(args);
        else if(function.equals("selectionsort")) runSelectionsort(args);
        else if(function.equals("mergesort")) runMergesort(args);
        else if(function.equals("q1")) runQ1(args);
        else if(function.equals("q3")) runQ3(args);
        else if(function.equals("q4")) runQ4(args);
        else if(function.equals("q5")) runQ5(args);
        else if(function.equals("q6")) runQ6(args);
        else if(function.equals("q7")) runQ7(args);
        else if(function.equals("LIS")) runLIS(args);
        else SOP("ERROR: Unknown function");
	}

	public static void runBubblesort(String[] args) throws Exception{
		if(args.length < 3){
			SOP("Must Specify at least two elements");
			return;
		}

		ArrayList<Integer> mList = generateListFromArgs(args);
		Integer[] sortedList = bubblesort(mList.toArray(new Integer[mList.size()]));
		printList("sortedList", sortedList);
	}

	public static Integer[] bubblesort(Integer[] nums){
		//We start at the beginning of the array and swap the first two elements if
		//the first is greater than the second. Then, we go to the next pair, and so
		//on, continuously making sweeps of the array until it is sorted.
		//runtime: O(n^2) ; space: O(1)
		//1 3 2 5 4

		int length = nums.length;
		boolean swapped = true;
		while(swapped){
			swapped = false;
			for(int i=1; i<length; i++){
				if(nums[i-1] > nums[i]){
					Integer temp = nums[i-1];
					nums[i-1] = nums[i];
					nums[i] = temp;
					swapped = true;
				}
			}
		}

		return nums;
	}


	public static void runSelectionsort(String[] args) throws Exception{
		if(args.length < 3){
			SOP("Must Specify at least two elements");
			return;
		}

		ArrayList<Integer> mList = generateListFromArgs(args);
		Integer[] sortedList = selectionsort(mList.toArray(new Integer[mList.size()]));
		printList("sortedList", sortedList);

	}

	public static Integer[] selectionsort(Integer[] nums){
		//Find the smallest element using a linear scan and move it to the front, 
		//swapping it with the front element. Then, find the second smallest and
		//mofe it, again doing a linear scan. Continue doing this until all the
		//elements are in place
		//Runtime: O(n^2); Space: O(1)

		int length = nums.length;
		int currMinPos = 0, temp = 0;
		for(int i=0; i<length; i++){
			int currMin = Integer.MAX_VALUE;
			for(int j=i; j<length; j++){
				//find min value
				if(nums[j] < currMin){
					currMin = nums[j];
					currMinPos = j;
				}
			}

			//swap current i with currMinPos
			if(currMinPos != i){
				//we dont need to unnecessairly swap if they are the same
				temp = nums[i];
				nums[i] = nums[currMinPos];
				nums[currMinPos] = temp;
			}

		}
		return nums;
	}


	public static void runMergesort(String[] args) throws Exception{
		if(args.length < 3){
			SOP("Must Specify at least two elements");
			return;
		}

		ArrayList<Integer> aList = generateListFromArgs(args);
		Integer[] mList = aList.toArray(new Integer[aList.size()]);
		mergesort(mList);
		printList("sortedList", mList);

	}

	public static void mergesort(Integer[] nums){
		//divide array in half, sort each of those halves, and then merge them back
		//together
		//Runtime: O(n log n); Space: O(1)

		int length = nums.length;
		Integer[] helper = new Integer[length];
		mergesort(nums, helper, 0 ,length - 1);
	}

	public static void mergesort(Integer[] nums, Integer[] helper, int start, int end){
		if(start < end){
			int middle = (start + end) / 2;
			mergesort(nums, helper, start, middle);
			mergesort(nums, helper, middle+1, end);
			merge(nums, helper, start, middle, end);
		}
	}

	public static void merge(Integer[] nums, Integer[] helper, int start, int middle, int end){
		for(int i = start; i < end; i++){
			helper[i] = nums[i];
		}

		//Now we merge. We will compare the left and right half, copying back the smaller
		//element from the two halves into the original array
		int helperLeft = start, helperRight = middle+1, current = start;
		while(helperLeft <= middle && helperRight <= end){
			if(helper[helperLeft] <= helper[helperRight]){
				nums[current] = helper[helperLeft];
				helperLeft++;
			}
			else{
				nums[current] = helper[helperRight];
				helperRight++;
			}
			current++;
		}

		//Copy the rest of the left side of the array into the target array
		int remaining = middle - helperLeft;
		for(int i = 0; i<= remaining; i++){
			nums[current + i] = helper[helperLeft + i];
		}
	}


	public static void runQ1(String[] args){
		//Question 11.1: You are given two sorted arrays, A and B, where A has a large 
		//enough buffer at the end to hold B. Write a method to merge B into A in
		//sorted order.
		int min = Integer.MIN_VALUE;
		Integer[] A = new Integer[]{1, 3, 7, min, min, min};
		Integer[] B = new Integer[]{2, 4, 5};
		printList("A", A);
		printList("B", B);
		Integer[] result = mergeBintoA(A, B);
		printList("Final Result", result);
	}

	public static Integer[] mergeBintoA(Integer[] A, Integer[] B){
		//1 3 7 _ _ _
		//2 4 5
		//1 2 3 4 5 7

		//- - - 1 3 7
		//1 - - - 3 7
		//1 2

		//Let us assume the array is init with min value for missing spaces
		int aLength = A.length;

		//First we determine where the last element is 
		int lastElementPos = 0;
		for(int i =0; i<aLength; i++){
			if(A[i] == Integer.MIN_VALUE){
				lastElementPos = i-1;
				break;
			}
		}

		//Then we shift the array to the end
		int count = 0;
		for(int i = lastElementPos; i>=0; i--){
			A[aLength-1 - count] = A[i];
			A[i] = Integer.MIN_VALUE;
			count++;
		}

		//printList("Shifted:", A);


		//Now we can merge
		int aStart = 0;
		for(int i=0; i<aLength; i++){
			if(A[i] != Integer.MIN_VALUE){
				aStart = i;
				break;
			}
		}

		int bStart = 0, aHead = 0;
		while(bStart != (B.length) ){
			if(A[aStart] < B[bStart]){
				A[aHead] = A[aStart];
				A[aStart] = Integer.MIN_VALUE;
				aHead++;
				aStart++;
			}
			else{
				A[aHead] = B[bStart];
				aHead++;
				bStart++;
			}
		}

		return A;
	}

	public static void runQ3(String[] args){
		//Question 11.3: Given a sorted array of n integers that has been rotated an
		//unknown number of times, write code to find an element in the array. You may
		//assume that the array was originally sorted in increasing order.
		Integer[] mArray = new Integer[]{10, 15, 20, 0, 5};
		printList("Original mArray", mArray);
		int element = 0;
		int pos = findElementInRotatedSortedArray(mArray, 0, mArray.length -1, element);
		SOP("Position: " + pos);
	}

	public static int findElementInRotatedSortedArray(Integer[] mArray, int start, int end, int element){
		//This is a basic binary search question (with a twist). It can
		//be solved in O(log n) time.
		//10, 15, 20, 0, 5
		int mid = (start + end) / 2;
		if(mArray[mid] == element) return mid;
		if(end < start) return -1;

		if(mArray[start] < mArray[mid]){
			//then we know the left side is properly sorted
			if(mArray[start] <= element && element <= mArray[mid]){
				return findElementInRotatedSortedArray(mArray, 0, mid -1, element); //explore left
			}
			else{
				return findElementInRotatedSortedArray(mArray, mid+1, end, element); //explore right
			} 
		}
		else if(mArray[start] > mArray[mid]){
			//then we know the right side is properly sorted
			if(mArray[mid] <= element && element <= mArray[end]){
				return findElementInRotatedSortedArray(mArray, mid+1, end, element);
			}
			else{
				return findElementInRotatedSortedArray(mArray, start, mid-1, element);
			}
		}
		else if(mArray[start] == mArray[mid]){
			if(mArray[mid] != mArray[end]){
				return findElementInRotatedSortedArray(mArray, mid +1, end, element);
			}
			else{
				int ret = findElementInRotatedSortedArray(mArray, start, mid-1, element);
				if(ret==-1){
					return findElementInRotatedSortedArray(mArray, mid+1, end, element);
				}
				else return ret;
			}
		}
		return -1;
	}

	public static void runQ4(String[] args){
		//Question 11.4: Imagine you have a 20 GB file with one string per line.
		//Explain how you would sort the file

		SOP("See cource code for answer");
		//We can employ the idea behind merge sort to do this. In essence, we will
		//break apart the 20GB file into much more manageable chunks, sort them individually
		//and then merge the results in order.
	}

	public static void runQ5(String[] args){
		//Question 11.5: Given a sorted array of strings which is interspersed with empty strings,
		//write a method to find the location of a given string.
		

		SOP("See cource code for answer");
		//Again, we use the idea behind binary search to solve this in O(log n)
		//The twist here is the empty string. All we have to do is simply move mid to the closest
		//non empty string.

		//Note that this has best case O(log n) but worst case is O(n).
	}

	public static void runQ6(String[] args){
		//Question 11.6:Given a M by N matrix in which each row and each column is sorted in 
		//ascending order, write a method to find an element

		int numRows = 4, numColumns = 4;
		int[][] matrix = new int[numRows][numColumns];
		// 15	20 	40	85 
		// 20 	35 	80 	95
		// 30 	55 	95 	105
		// 40 	80 	100 120
		matrix[0][0] = 15; matrix[0][1] = 20; matrix[0][2] = 40; matrix[0][3] = 85;
		matrix[1][0] = 20; matrix[1][1] = 35; matrix[1][2] = 80; matrix[1][3] = 95;
		matrix[2][0] = 30; matrix[2][1] = 55; matrix[2][2] = 95; matrix[2][3] = 105;
		matrix[3][0] = 40; matrix[3][1] = 80; matrix[3][2] = 100; matrix[3][3] = 120;

		int element = 55;
		SOP("Find "+ element +" in the following matrix: ");
		printMatrix(matrix);
		findElementInSortedRowsAndColumnsMatrix(matrix, element);

	}

	public static void findElementInSortedRowsAndColumnsMatrix(int[][] matrix, int element){
		//The idea here is based off the following observations:
		//If the value at the top of the column, is greater than the element, we move a column down
		//If the start of the room value is less than the element, we move a row down
		//The opposite is true for each as well
		//This is solvable in O(m log n)

		int row = 0, column = matrix[0].length -1, totalRows = matrix.length;
		while(row < totalRows && column >= 0){
			if(matrix[row][column] == element){
				SOP("Element found at (row, column): (" + row +", " + column +")");
				return;
			}
			else if(matrix[row][column] > element){
				column--;
			}
			else{
				row++;
			}
		}

		//If we reach here, we know the element was not found
		SOP("Element \"" + element +"\" was not found");
	}

	public static void runQ7(String[] args){
		//Question 11.7: Given a set of peoople, sort them by weight and height such that
		//the person before the next weighs less and is shorter

		//Assumptions: Height is in inches and Weight is in pounds
		//Each Person Obect will be P(height, weight);
		ArrayList<Person> mList = new ArrayList<Person>();
		mList.add(new Person(65, 150));
		mList.add(new Person(64, 140));
		mList.add(new Person(72, 186));
		mList.add(new Person(62, 120));
		mList.add(new Person(73, 190));
		longestIncreasingSubsequenceInPairs(mList);
	}

	public static void longestIncreasingSubsequenceInPairs(ArrayList<Person> mPersons){
		//This problem is very similar to LIS, except with a twist. We will first sort 
		//according to height, and then perform LIS on the weight
		Collections.sort(mPersons);

		//next we run custom LIS
		int[] sizes = new int[mPersons.size()];
		String[] paths = new String[mPersons.size()];

		//First we init both sizes and paths
		for(int i = 0; i < mPersons.size(); i++){
			sizes[i] = 1;
			paths[i] = "P"+ i + "" + mPersons.get(i).toString() + " ";
		}

		//Then we use dynamic programming to build subsequences
		for(int i = 1; i < mPersons.size(); i++){
			for(int j=0; j<i; j++){
				if(mPersons.get(i).weight > mPersons.get(j).weight && sizes[i] < sizes[j] + 1){
					sizes[i] = sizes[j] + 1;
					paths[i] = paths[j] + "P"+ i + "" + mPersons.get(i).toString() + " ";
				}
			}
		}

		//Finally we print the longest increasing subsequence
		int maxLength = 0, maxIndex = 0;
		for(int i=0; i<sizes.length; i++){
			if(maxLength < sizes[i]){
				maxLength = sizes[i];
				maxIndex = i;
			}
		}
		SOP("LIS: " + paths[maxIndex]);

	}

	public static void runLIS(String[] args){
		if(args.length < 3){
			SOP("Must specify at least two integers");
			return;
		}

		ArrayList<Integer> mList = generateListFromArgs(args);
		Integer[] mArray = mList.toArray(new Integer[mList.size()]);
		LIS(mArray);
	}

	public static void LIS(Integer[] mArray){
		//This will implement the Longest Increasing Subsequence algorithm using
		//Dynamic Programming in O(n^2) time

		int[] sizes = new int[mArray.length];
		String[] paths = new String[mArray.length];

		//First we init both sizes and paths
		for(int i = 0; i < mArray.length; i++){
			sizes[i] = 1;
			paths[i] = mArray[i] + " ";
		}

		//Then we use dynamic programming to build subsequences
		for(int i = 1; i < mArray.length; i++){
			for(int j=0; j<i; j++){
				if(mArray[i] > mArray[j] && sizes[i] < sizes[j] + 1){
					sizes[i] = sizes[j] + 1;
					paths[i] = paths[j] + mArray[i] + " ";
				}
			}
		}

		//Finally we print the longest increasing subsequence
		int maxLength = 0, maxIndex = 0;
		for(int i=0; i<sizes.length; i++){
			if(maxLength < sizes[i]){
				maxLength = sizes[i];
				maxIndex = i;
			}
		}
		SOP("LIS: " + paths[maxIndex]);

	}


	//==================Helper Functions
	public static void printMatrix(int[][] matrix){
		for(int row = 0; row < matrix.length; row++){
			for(int column = 0; column < matrix[0].length; column++){
				SOP2(matrix[row][column]+"\t");
			}
			SOP("");
		}
	}

	public static ArrayList<Integer> generateListFromArgs(String[] args){
		ArrayList<Integer> mList = new ArrayList<Integer>();
		for(int i = 1; i<args.length; i++){
			mList.add(Integer.parseInt(args[i]));
		}

		SOP("mList");
		for(Integer i : mList) SOP2(""+i+" ");
		SOP("");
		return mList;
	}

	public static void printList(String header, Integer[] list){
		SOP(header);
		for(int i = 0; i<list.length; i++){
			SOP2(""+list[i] +" ");
		}
		SOP("");
	}

	public static void SWAP(Integer[] nums, int i, int j){
		Integer temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void SOP(String arg){
        System.out.println(arg);
    }

    public static void SOP2(String arg){
        System.out.print(arg);
    }

    public static void SOP(char[] arg){
        System.out.println(arg);
    }

    public static void SOP(int arg){
        System.out.println(arg);
    }

    public static void SOP(boolean arg){
        System.out.println(arg);
    }

}