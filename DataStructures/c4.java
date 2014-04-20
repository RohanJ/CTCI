/*==========NOTES==========
@author: Rohan Jyoti
@compile: javac c4.java bNode.java
@run: java c4 [function name] [function arguments]
@project: Data Structures
@purpose: Chapter 4 Trees and Graphs
=========================*/


//==========IMPORTS
import java.util.*;
import java.lang.String;
import java.lang.StringBuilder;
//==========

public class c4{
    public static void main(String[] args) throws Exception{
        SOP("==========Chapter 4 Trees and Graphs===");
        SOP("To run: java c4 [function name] [function arguments]");
        SOP("Example: java c4 q1");
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
        //Implement a function to check if a binary tree is balanced. For the purpose of
        //this question, a balaced tree is defined to be a tree such that the heights of the
        //two subtrees of any node never differ by more than one
         SOP("Running q1");
         if(args.length != 1){
            SOP("This function takes no arguments");
            return;
         }

         bNode root1 = generateSampleTree1(); //a balanced binary tree
         bNode root2 = generateSampleTree2(); //an imbalanced binary tree
         bNode root3 = generateSampleTree3(); //another imbalanced binary tree

        if(isBalanced(root1)) SOP("Tree 1 IS balanced");
        else SOP("Tree 1 is NOT balanced");

        if(isBalanced(root2)) SOP("Tree 2 IS balanced");
        else SOP("Tree 2 is NOT balanced");

        if(isBalanced(root3)) SOP("Tree 3 IS balanced");
        else SOP("Tree 3 is NOT balanced");
    }   

    public static boolean isBalanced(bNode root){
        if(getHeight(root) == -1) return false;
        else return true;
    }

    public static int getHeight(bNode root){
        if(root == null) return 0;
        
        int left = getHeight(root.left);
        if(left == -1) return -1;

        int right = getHeight(root.right);
        if(right == -1) return -1;

        if(Math.abs(left - right) > 1) return -1;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    //==========UTILITY FUNCTIONS
    // Sample Binary Trees
    public static bNode generateSampleTree1(){
        /*
        *                     1
        *                   /  \
        *                 2     3
        *               /  \   / \
        *             4    5  6   7
        */

        bNode root = new bNode(1);
        root.left = new bNode(2);
        root.right = new bNode(3);
        root.left.left = new bNode(4);
        root.left.right = new bNode(5);
        root.right.left = new bNode(6);
        root.right.right = new bNode(7);
        return root;
    }

    public static bNode generateSampleTree2(){
        /*
        *                     1
        *                   /  \
        *                 2     3
        *               /  \   / \
        *             4    5  6   7
        *            / 
        *          8
        *            \
        *              9
        */

        bNode root = new bNode(1);
        root.left = new bNode(2);
        root.right = new bNode(3);
        root.left.left = new bNode(4);
        root.left.right = new bNode(5);
        root.right.left = new bNode(6);
        root.right.right = new bNode(7);
        root.left.left.left = new bNode(8);
        root.left.left.left.right = new bNode(9);
        return root;
    }

    public static bNode generateSampleTree3(){
        /*
        *                     1
        *                   /  
        *                 2     
        *               /    
        *             4    
        *            / 
        *          8
        *            
        *             
        */

        bNode root = new bNode(1);
        root.left = new bNode(2);
        root.left.left = new bNode(4);
        root.left.left.left = new bNode(8);
        return root;
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
}