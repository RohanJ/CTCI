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
import java.lang.StringBuilder;
//==========

public class c5{
    public static void main(String[] args) throws Exception{
        SOP("==========Chapter 5 Bit Manipulation===");
        SOP("To run: java c5 [function name] [function arguments]");
        SOP("Example: java c5 q1");
        SOP("");
        SOP("Possible functions:");
        SOP("q1\tQuestion 1");
        SOP("q2\tQuestion 2");
        SOP("===============================");
        SOP("");

        if(args.length < 1){
            SOP("ERROR: Must specify function to run");
            return;
        }

        String function = args[0];
        if(function.equals("q1")) q1(args);
        else if(function.equals("q2")) q2(args);
        else SOP("ERROR: Unknown function");
    }

    public static void q1(String[] args) throws Exception{
        //Question 5.1: You are given two 32-bit numbers, N and M, and two bit positions, i and j. Write
        //a method to insert M into N such that M starts at bit j and ends at bit i. You can assume that the
        //bits j through i have enough space to fit all of M. That is, if M = 10011, you can assume that there
        //are at least 5 bits between j and i. You would not, for example, have j=3 and i=2, because M could
        //fully fit between bit 3 and bit 2.
        //Example
        //Input: N = 10000000000, M = 10011, i = 2, j=6
        //Output: N = 10001001100
        SOP("Running q1");
        if(args.length != 5){
            SOP("ERROR: Must specify two integers to represent the two numbers and two integers for the index start and index end");
            return;
        }

        int n = Integer.parseInt(args[1]);
        int m = Integer.parseInt(args[2]);
        int i = Integer.parseInt(args[3]);
        int j = Integer.parseInt(args[4]);

        if(j <= i) {
            SOP("ERROR: second index must be greater than first index");
            return;
        }

        if((j-i+1) != Integer.toBinaryString(m).length()){
            SOP("ERROR: invalid indexes. Not enough space");
            return;
        }

        if(n<=m){
            SOP("ERROR: n must be greater than m");
            return;
        }

        int result = insertMIntoN(n, m, i, j);
        SOP2("Inserting \"" +n + "->[" + Integer.toBinaryString(n) + "]\"");
        SOP2(" into \""  + m + "->[" + Integer.toBinaryString(m) + "]\"" );
        SOP2(" between indexes [" + i + "," + j +"]");
        SOP(" yields: " +  result + "->[" + Integer.toBinaryString(result) +"]" );
    }

    public static void q2(String[] args) throws Exception{
        //Question 5.2: Given a real number between 0 and 1 (e.g. 0.72) that is passed in as a double, print
        //the binary representation. If the number cannot be represented accurately in binary with at most 32
        //characters, print "ERROR."
        SOP("Running q2");
        if(args.length != 2){
            SOP("Must specify decimal number");
            return;
        }

        Double theNum = Double.parseDouble(args[1]);

        if(theNum <= 0 || theNum >= 1){
            SOP("Must specify decimal number");
            return;
        }

        String retVal = convertDecimalToBinary(theNum);
        if(retVal.equals("ERROR")) SOP("ERROR");
        else SOP("\"" + theNum + "\" in binary is " + retVal );
    }

    public static int insertMIntoN(int n, int m, int i, int j){
        //at this point we are guarateed i and j are valid indexes with enough space
        //The ultimate goal is to | the n with mask based off  of M, but we must first 0 
        //out the region

        //First we create the zero mask
        int leftMask = (~0)<<j; //11111111 -> 11110000
        int rightMask = ~((~0)<<i); //11111111 -> 11111100 -> 00000011
        int zeroMask = leftMask | rightMask; //11110000 | 00000011 -> 11110011

        n &= zeroMask; //so now n is zerod out in the regious

        int maskBasedFromM = (m << i);
        return n | maskBasedFromM;
    }

    public static String convertDecimalToBinary(double num){
        StringBuilder mSB = new StringBuilder();
        mSB.append(".");

        while(num > 0){
            if(mSB.length() >= 32) break;
            
            double temp = num * 2;
            if(temp>=1){
                mSB.append("1");
                num  = temp - 1;
            }
            else{
                mSB.append("0");
                num = temp;
            }
        }
        return mSB.toString();
    }

    //==========UTILITY FUNCTIONS
    public static boolean getBit(int num, int i){
        //false represents 0 and true represents 1
        boolean retVal = (num&(1<<i)) == 0 ? false : true;
        return retVal;
    }

    public static int setBit(int num, int i){
        //sets the bit in num at specified i to 1
        return num | (1 << i);
    }

    public static int clearBit(int num, int i){
        //sets the bit in num at specified i to 0
        int mask = ~(1<<i); //so something like 0000100 -> 1111011
        return num & mask;
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
