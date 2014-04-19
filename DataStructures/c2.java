/*==========NOTES==========
@author: Rohan Jyoti
@compile: javac c2.java sNode.java
@run: java c2 [function name] [function arguments]
@project: Data Structures
@purpose: Chapter 2 Linked Lists
=========================*/


//==========IMPORTS
import java.util.*;
import java.lang.String;
import java.lang.StringBuilder;
//==========

public class c2{
    public static void main(String[] args) throws Exception{
        SOP("==========Chapter 1 Arrays and Strings===");
        SOP("To run: java c2 [function name] [function arguments (i.e. node values in linked list)]");
        SOP("Example: java c2 q1 1 2 3 4");
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

    public static void q1(String[] args) throws Exception{
        //Question 2.1: Write a code to remove duplicates from an unsorted linked list. How would 
        //you solve this problem if a temporary buffer is not allowed?
        
        SOP("Running q1");
        if(args.length < 3){
            SOP("ERROR: Must specify at least two nodes");
            return;
        }

        //generate singly linked list from args
        sNode sHead = new sNode(Integer.parseInt(args[1]));
        for(int i =2; i<args.length; i++){
            sHead.appendToTail(Integer.parseInt(args[i]));
        }

        sHead.printList(sHead);

        sHead = removeDuplicates(sHead);
        SOP("After Remove Duplicates: ");
        sHead.printList(sHead);
    }

    public static sNode removeDuplicates(sNode head){
        //If a temporary buffer / external data structure were allowed, then we could solve this
        //in O(n) time. You would use a hash map and has you iterate through the the list (O(n))
        //you would add the value to hash map. If the value already exists in the map, you will delete the node (O(1))

        //However, given the constraint that we cannot use a temporary buffer / external data structure, this
        //problem becomes O(n^2). We will have to use a nested while loop where in the inner loop we check 
        //for matching values to the current valuie in the outer loop and call delete the node accordingly.

        sNode curr = head;
        sNode potential = null;
        sNode potential_prev = null;
        while(curr != null){
            //O(n) traversal
            potential = curr.next;
            potential_prev = curr;
            while(potential != null){
                //O(n) traversal
                if(potential.data == curr.data){
                    //O(1) delete
                    potential_prev.next = potential.next;
                    potential = potential_prev; //tricky, but we do this so below potential can be set correctly
                }
                potential_prev = potential;
                potential = potential.next;
            }
            
            curr = curr.next;
        }

        return head;
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