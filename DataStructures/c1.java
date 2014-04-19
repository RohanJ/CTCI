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
        SOP("q3\tQuestion 3");
        SOP("q4\tQuestion 4");
        SOP("q5\tQuestion 5");
        SOP("===============================");
        SOP("");

        if(args.length < 1){
            SOP("ERROR: Must specify function to run");
            return;
        }

        String function = args[0];
        if(function.equals("q1")) q1(args);
        else if(function.equals("q2")) q2(args);
        else if(function.equals("q3")) q3(args);
        else if(function.equals("q4")) q4(args);
        else if(function.equals("q5")) q5(args);
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

public static void q3(String[] args){
    //Question 1.3: Given two strings, write a method to decide if one is a permutation of the other

    //Option 1: We can sort the two strings and then do equality check --> O(nlogn) run time O(1) space
    //Option 2: We can use hash maps and do this instead in O(n) time and O(n) space
    SOP("Running q3");
    if(args.length != 3){
        SOP("ERROR: Must specify two strings");
        return;
    }

    if(isPermutation(args[1].toCharArray(), args[2].toCharArray()) == true)
        SOP("String: \"" + args[1] + "\" and \"" + args[2] + "\" ARE permutations of each other");
    else
        SOP("String: \"" + args[1] + "\" and \"" + args[2] + "\" are NOT permutations of each other");
}

public static void q4(String[] args){
    //Question 1.4: Write a method to replace all spaces in a string with '%20'.
    //You may assume that the string has sufficient space at the end to hold the additional characters,
    //and that you are given the "true" length of the string. (Note: if implementing in java, please use a character
    //array so that you can perform this operation in place).
    SOP("Running q4");
    if(args.length != 2){
        SOP("ERROR: Must specify string");
        return;
    }

    char[] str = args[1].toCharArray();
    str = replaceSpacesInStr(str, str.length);
    if(str == null){
        SOP("ERROR: Insufficient space OR no spaces in string");
        return;
    }

    System.out.print("String: \"" + args[1] + "\" replaced is \"");
    System.out.print(str);
    SOP("\"");
}

public static void q5(String[] args){
    //Question 1.5: Implement a method to perform basic string compression using the counts
    //of repeated characters. For example, the strign aabcccccaaa would become a2b2c5a3. 
    //If the "compressed" string would not become smaller than the original string, your method
    //should return the original string
    SOP("Running q5");
    if(args.length != 2){
        SOP("ERROR: Must specify string");
        return;
    }

    String compressed = compressString(args[1]); 
    if(args[1].equals(compressed))
        SOP("String \"" + args[1] + "\" cannot be compressed");
    else
        SOP("String \"" + args[1] + "\" compressed is \"" + compressed + "\"");
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

public static boolean isPermutation(char[] str1, char[] str2){
    //Hashmap is O(1) insert, read, and delete
    //The idea here is to create a hashmap mapping of each char and its count (O(n) time)
    //and then compare the counts for each char  between the two hash maps

    if(str1.length != str2.length) return false;

    HashMap<String, Integer> mHashMap1 = generateMapFromStr(str1);
    HashMap<String, Integer> mHashMap2 = generateMapFromStr(str2);

    for(Map.Entry<String, Integer> e : mHashMap1.entrySet()){
        if(e.getValue() != mHashMap2.get(e.getKey())) return false;
    }

    return true;
}

public static HashMap<String, Integer> generateMapFromStr(char[] str){
    HashMap<String, Integer> mHashMap = new HashMap<String, Integer>();
    Integer count;
    for(int i=0; i<str.length; i++){
         count = mHashMap.get(String.valueOf(str[i]));
        if(count == null){
            count = 0;
        }
        mHashMap.put(String.valueOf(str[i]), count++);
    }

    return mHashMap;
}

public static char[] replaceSpacesInStr(char[] str, int true_length){
    System.out.print("\"");
    System.out.print(str);
    SOP("\"");
    SOP(true_length);
    
    //first get pos of last char
    //then we'll go through and figure out num spaces from start to last char
    //if true_length != (last_char_pos+1) + (2*num_spaces) then return meaning not enough space
    //if there is enough space,  then we work from the end of the string and copy over one by one
    //when we encounter space, we write %20

    int last_char_pos = true_length -1;
    for(int i = true_length - 1; i>=0; i--){
        if(str[i] != ' '){
            last_char_pos = i;
            break;
        }
    }

    int num_spaces = 0;
    for(int i=0; i<=last_char_pos; i++)
        if(str[i] == ' ') num_spaces++;

    if(true_length != (last_char_pos +1) + (2*num_spaces)) return null;

    int curr = true_length - 1;

    while(last_char_pos >=0){
        if(str[last_char_pos] == ' '){
            str[curr] = '0';
            str[curr-1] = '2';
            str[curr-2] = '%';
            curr-=3;
            last_char_pos--;
        }
        else{
            str[curr] = str[last_char_pos];
            curr--;
            last_char_pos--;
        }
    }


    return str;
}

public static String compressString(String str){
    StringBuilder mSB = new StringBuilder();
    int length = str.length();
    char letter = str.charAt(0);
    int count = 1;   
    str+=" ";

   for(int i = 1; i<str.length(); i++){
        if(str.charAt(i) == letter) count++;
        else{
            String toAppend = letter + "" + count;
            mSB.append(toAppend);
            letter = str.charAt(i);
            count = 1;
        }
   }

   String mSB_str = mSB.toString();
   if(mSB_str.length() >= str.length()) return str.substring(0, str.length()-1);
   else return mSB_str;
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