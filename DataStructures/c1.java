/*==========NOTES==========
@author: Rohan Jyoti
@compile: javac c1.java
@run: java c1 [function name] [function arguments]
@project: Data Structures
@purpose: Chapter 1 Arrays and Strings
=========================*/


//==========IMPORTS
import java.util.*;
import java.lang.String;
import java.lang.StringBuilder;
//==========

public class c1{
    public static void main(String[] args){
        SOP("==========Chapter 1 Arrays and Strings===");
        SOP("To run: java c1 [function name] [function arguments]");
        SOP("Example: java c1 q1 abcdef");
        SOP("");
        SOP("Possible functions:");
        SOP("q1\tQuestion 1");
        SOP("===============================");
        SOP("");

        if(args.length < 1){
            SOP("ERROR: Must specify function to run");
            return;
        }

        String function = args[0];
        if(function.equals("q1")) q1(args);
        else SOP("ERROR: Unknown function");
    }

public static void q1(String[] args){
    //Question 1.1: Implement an algorith to determine if a string has all unique characters.
    //What if you cannot use additional data structures?
    SOP("Running q1");
    if(args.length != 2){
        SOP("ERROR: Must specify string");
        return;
    }

    boolean retVal = isAllUniqueChars(args[1].toCharArray());
    if(retVal){
        SOP("String: \""+ args[1] + "\" has all unique characters");
    }
    else{
        SOP("String: \""+ args[1] + "\" does NOT have all unique characters");
    }
}



public static boolean isAllUniqueChars(char[] str){
    //Without using external data structures, we must use bit arrays.
    //Assumption 1: String is ascii --> meaning 256 bits at most
    //To make this problem simpler for this example, lets assume also that the string only contain
    //letters a-z, i.e. 26 characters

    //An integer on a 32 bit machine is 4 bytes aka 32 bits (on 64 bit machine, its 64)
    //So for our case where there is only 26 possible characters, one int can hold it.
    //for overall ascii, the problem is a bit more complicated because we must manage 8 ints 
    //(or 4 ints on 64-bit machine).

    int mNum = 0;
    int pos =0;

    for(int i = 0; i<str.length; i++){
        pos = str[i] - 'a'; 
        if(isBitSet(mNum, pos) == false){
            mNum = setBit(mNum, i);
        }
        else return false; //meaning a bit was already set, so the string cannot contain all unique chars
    }
    return true;
}




//==========UTILITY FUNCTIONS
    public static boolean isBitSet(int mask, int pos){
        //returns true if the bit is set (aka is 1) for specified pos
        //pos is 0-th indexed
        return (((mask >> pos) & 1) == 1);
    }

    public static int setBit(int num, int pos){
        //return num with bit set (aka 1) for specified pos
        //pos is 0-th indexed
         return (num |= (1<<pos));
    }

    public static void SOP(String arg){
        System.out.println(arg);
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
//==========
}