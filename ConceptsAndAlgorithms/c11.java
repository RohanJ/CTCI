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

	public static void main(String[] args) throws Exception{
		SOP("==========Chapter 11 Sorting and Searching===");
        SOP("To run: java c11 [function name] [function arguments]");
        SOP("Example: java c11 sort 1 4 5 3 2");
        SOP("");
        SOP("Possible functions:");
        
        SOP("bubblesort\tBubblesort");
        SOP("selectionsort\tSelectionsort");
        SOP("===============================");
        SOP("");

        if(args.length < 1){
            SOP("ERROR: Must specify function to run");
            return;
        }

        String function = args[0];
        
        if(function.equals("bubblesort")) runBubblesort(args);
        else if(function.equals("selectionsort")) runSelectionsort(args);
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


	//==================Helper Functions

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