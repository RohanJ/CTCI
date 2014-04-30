/*==========NOTES==========
@author: Rohan Jyoti
@compile: javac c9.java
@run: java c9 [function name] [function arguments]
@project: Concepts And Algorithms
@purpose: Chapter 9 Recursion and Dynamic Programming
=========================*/

//==========IMPORTS
import java.util.*;
import java.lang.String;
//==========

public class c9{
    private static long[] cache = new long[100000];

	public static void main(String[] args) throws Exception{
		SOP("==========Chapter 9 Recursion And Dynamic Programming===");
        SOP("To run: java c9 [function name] [function arguments]");
        SOP("Example: java c9 fib 10");
        SOP("");
        SOP("Possible functions:");
        SOP("fib\tCompute nth fibonacci number");
        SOP("q1\tQuestion 1");
        SOP("q2\tQuestion 2");
        SOP("q3\tQuestion 3");
        SOP("===============================");
        SOP("");

        if(args.length < 1){
            SOP("ERROR: Must specify function to run");
            return;
        }

        String function = args[0];
        if(function.equals("fib")) fibRunner(args);
        else if(function.equals("q1")) q1(args);
        else if(function.equals("q2")) q2(args);
        else if(function.equals("q3")) q3(args);
        else SOP("ERROR: Unknown function");
	}

    public static void fibRunner(String[] args) throws Exception{
        if(args.length != 2){
            SOP("ERROR: Must specify n to calculate nth fibonacci number");
            return;
        }

        int num = Integer.parseInt(args[1]);

        SOP(cache.length);
        SOP("The \"" + num + "\" -th fibonacci number is: " + fib(num));
    }

    public static void q1(String[] args) throws Exception{
        //Question 1: A child is running up a staircase with n steps, and can hop either 1 step,
        //2 steps, or 3 steps at a time. Implement a method to count how many possible ways the
        //child can run up the stairs.
        SOP("Running q1");
        if(args.length != 2){
            SOP("ERROR: Must specify n number of steps");
            return;
        }

        int n = Integer.parseInt(args[1]);
        SOP("The number of way the child can run up \"" + n + "\" steps is: " + countWaysChildCanRunUpSteps(n));
    }

    public static void q2(String[] args) throws Exception{
        //Question 9.2: Imagine a robot sitting on the upper left corner of an X by Y grid. The robot can only
        //move in two directions: right and down. How many possible paths are there for the robot to go from
        //(0,0) to (X,Y)?
        SOP("Running q2");
        if(args.length != 3){
            SOP("ERROR: Must specify two integers to represent X and Y coordinates");
            return;
        }

        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);

        SOP("The number of possible paths for the robot from (0,0) to \"(" + x +"," + y +")\" is: " + possiblePathsForRobot(x, y) );
    }

    public static void q3(String[] args) throws Exception{
        //Question 9.3: A magic index in an array A[1....n-1] is defined to be an index such that
        //A[i] = i. Given a sorted array of distinct integers, write a method to find a magic index,
        //if one exists, in array A.
        //Follow up: What if the values are not distinct.
        SOP("Running q3");
        if(args.length < 3){
            SOP("ERROR: Must specify at least two elements for the array");
            return;
        }

        int[] mArray = new int[args.length - 1];
        for(int i = 0; i<args.length-1; i++){
            mArray[i] = Integer.parseInt(args[i+1]);
        }

        //determine if sorted, if sorted call findMagicIndex
        if(!isArraySorted(mArray))
            Arrays.sort(mArray);
        
        SOP("MagicIndex: " + findMagicIndex(mArray, 0, mArray.length-1));
    }

    public static long countWaysChildCanRunUpSteps(int n){
        if(n<0) return 0;
        if(n==0) return 1;
        if(cache[n] != 0) return cache[n];
        else{
            cache[n] = countWaysChildCanRunUpSteps(n-1) + countWaysChildCanRunUpSteps(n-2) + countWaysChildCanRunUpSteps(n-3);
        }   return cache[n];
    }

    public static int possiblePathsForRobot(int x, int y){
        //This question is simply the binomial equation
        //n choose r = (n!)/( (r! * (n-r)!))
        //in this case n = x+y
        //So it becomes, (x+y)! / (x!y!)
        return (factorial(x+y) / (factorial(x) * factorial(y)));
    }

    public static int findMagicIndex(int[] array, int i, int j){
        //Since the array is sorted, we can perform a binary search in log(n) time
        //WHAT about if elements are not distinct? If elements are not distinct, this
        //algorithm will not work. Instead we must iterate through each element and search
        //in O(n) time
        if(j < i || i < 0 || j >= array.length) return -1;
        int mid = (i+j)/2;
        if(array[mid] == mid) return mid;

        if(array[mid] < mid) return findMagicIndex(array, mid+1, j);
        else return findMagicIndex(array, i, mid-1);
    }

    
    public static long fib(int num){
        if(num == 0) return 0;
        if(num == 1) return 1;
        if(cache[num] != 0) return cache[num];
        else{
            cache[num] = fib(num - 1) + fib(num - 2);
            return cache[num];
        }
    }


    //==========UTILITY FUNCTIONS
    public static int factorial(int num){
        if(num == 0) return 1;
        int retVal = 1;
        while(num>1){
            retVal*=num;
            num--;
        }
        return retVal;
    }

    public static boolean isArraySorted(int[] array){
        for(int i = 1; i<array.length; i++){
            if(array[i] < array[i-1]) return false;
        }
        return true;
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