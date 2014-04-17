//To compile: javac c1.java
//To run: java c1 [function name]


//==========IMPORTS
import java.util.*;
import java.lang.String;
import java.lang.StringBuilder;

//==========

public class c1{
    public static void main(String[] args){
        SOP("Hello World");
    }







//==========UTILITY FUNCTIONS
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