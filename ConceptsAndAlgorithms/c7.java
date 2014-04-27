/*==========NOTES==========
@author: Rohan Jyoti
@compile: javac c5.java
@run: java c5 [function name] [function arguments]
@project: Data Structures
@purpose: Chapter 5 Bit Manipulation
=========================*/

//==========IMPORTS
import java.util.*;
import java.lang.String;
//==========

public class c7{

	public static void main(String[] args) throws Exception{
		SOP("==========Chapter 7 Mathematics and Probability===");
        SOP("To run: java c7 [function name] [function arguments]");
        SOP("Example: java c5 isPrime");
        SOP("");
        SOP("Possible functions:");
        SOP("isPrime\tChecks if number is prime");
        SOP("q4\tQuestion 4");
        SOP("===============================");
        SOP("");

        if(args.length < 1){
            SOP("ERROR: Must specify function to run");
            return;
        }

        String function = args[0];
        if(function.equals("isPrime")) isPrimeRunner(args);
        else if(function.equals("q4")) q4(args);
        else SOP("ERROR: Unknown function");
	}

	public static void isPrimeRunner(String[] args) throws Exception{
		if(args.length != 2){
			SOP("Must specify number");
			return;
		}
		int theNum = Integer.parseInt(args[1]);
		if(isPrime(theNum)) SOP("\""+ theNum + "\" IS a prime number");
		else SOP("\""+ theNum + "\" is NOT a prime number");

	}

	public static void q4(String[] args) throws Exception{
		//Question 7.4: Write methods to implement the multiply, subtract, and divide
		//operation for integers. Use only the add operator.
		SOP("Running q4");
		Scanner s = new Scanner(System.in);
		SOP("Please Enter two numbers to perform computation on");
		SOP2("Number 1: ");
		int num1 = s.nextInt();
		SOP2("Number 2: ");
		int num2 = s.nextInt();

		int option = -1;
		while(option == -1){
			SOP("");
			SOP("========");
			SOP("Please specify option");
			SOP("1\tMultiply");
			SOP("2\tSubtract");
			SOP("3\tDivide");
			SOP("");
			SOP2("Option: ");
		 	option = s.nextInt();
		 	if(option != 1 && option != 2 && option != 3){
		 		SOP("Invalid Option. Please Try again");
		 		option = -1;
		 	}
		}

		if(option == 1) SOP("\""+num1+"\" * \""+num2 +"\" = " + mMultiply(num1, num2));
		else if(option == 2)  SOP("\""+num1+"\" - \""+num2 +"\" = " + mSubtract(num1, num2));
		else if(option == 3) SOP("\""+num1+"\" \\ \""+num2 +"\" = " + mDivide(num1, num2));
		else {};

	}

	public static boolean isPrime(int num){
		int sqrt_num = (int)Math.sqrt(num);
		for(int i=2 ; i<= sqrt_num; i++){
			if(num%i==0) return false;
		}
		return true;
	}

	public static int mMultiply(int num1, int num2){
		int sign = 1;
		if(num1 < 0){ 
			sign = -sign;
			num1 = -num1;
		}
		if(num2 < 0){
			sign = -sign;
			num2 = -num2;
		}

		int result = num1;
		for(int i=1; i<num2; i++){
			result += num1;
		}

		result = (sign == -1) ? -result : result;
		return result;
	}

	public static int mSubtract(int num1, int num2){
		return num1 + (-num2);
	}

	public static int mDivide(int num1, int num2){
		int sign = 1;
		if(num1 < 0){ 
			sign = -sign;
			num1 = -num1;
		}
		if(num2 < 0){
			sign = -sign;
			num2 = -num2;
		}

		int count = 0;
		int result = num2;
		while(result <= num1){
			result += num2;
			count++;
		}
		
		count = (sign == -1) ? -count : count;
		return count;
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