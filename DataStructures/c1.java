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

public static void q2(String[] args){
    //Question 1.2: Implement a function void reverse(Char *str) in C or C++ which reverses a
    //null terminated string. This should be in place and should not use a temp variable.

    //We will do this in java using char array. 
    SOP("Running q2");
    if(args.length != 2){
        SOP("ERROR: Must specify string");
        return;
    }

    SOP("String: \"" + args[1] + "\" reversed is: \"" + reverseStr(args[1].toCharArray()) + "\"");
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

public static String reverseStr(char[] str){
    //The main part of this problem is how to swap two chars without using a temp variable
    //If we could use a temp variable, it would simply be:
    // temp = c1
    //c1 = c2
    //c2 = temp;

    //The trick here is to use xor. xor is true if and only if the two bits are different
    //0 ^ 0 = 0
    //0 ^ 1 = 1
    //1 ^ 0 = 1
    //1 ^ 1 = 0

    //Notice that if var1 is 1 and var2 is 0, I can do xor three times in the following manner 
    //and I will have swapped their values without the need of a temp variable
    //1. var1 ^= var2
    //2. var2 ^= var1
    //3. va1 ^= var2

    //so first, var1 = 1 ^ 0 = 1
    //next, var2 = 0 ^ 1 = 1
    //finally var1 = 1 ^ 1 = 0
    //var1 and var2 have swapped values

    int length = str.length;
    for(int i = 0; i<length/2; i++){
        str[i] ^= str[length - 1 - i];
        str[length -1 -i] ^= str[i];
        str[i] ^= str[length - 1 - i];
    }

    return new String(str);
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