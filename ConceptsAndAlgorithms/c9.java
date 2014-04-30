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

    public static int factorial(int num){
        if(num == 0) return 1;
        int retVal = 1;
        while(num>1){
            retVal*=num;
            num--;
        }
        return retVal;
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