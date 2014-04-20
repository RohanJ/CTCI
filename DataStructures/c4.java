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
        SOP("q2\tQuestion 2");
        SOP("q3\tQuestion 3");
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
        else SOP("ERROR: Unknown function");
    } 

    public static void q1(String[] args) throws Exception{
        //Question 4.1: Implement a function to check if a binary tree is balanced. For the purpose of
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

    public static void q2(String[] args) throws Exception{
        //Question 4.2: Given a directed graph, design an algorithm to find out whether there is a route
        //between two nodes
        SOP("Please look at src code for answer");

        //This is a simple graph traversal/search problem.
        //We can use DFS or BFS

        //DFS vs BFS
        //DFS explores iteratively the adjancies in order. This means it can get far from the original node.
        //BFS explores using a queue and explores the nodes closest to the original node first
        //Therefore, use BFS if you know the two nodes are in the same region

        //DFS pseudocode
        /*
            void DFS(bNode root){
                if(root == null) return;
                visit(root);
                root.visited = true;
                for(bNode n : root.getAdjacent()){
                    if(n.visited == false){
                        DFS(root);
                    }
                }
            }
        */

        //BFS pseudocode
        /*
            void BFS(bNode root){
                if(root == null) return;
                Queue mQ = new Queue();
                visit(root);
                root.visited = true;
                mQ.enqueue(root);

                while(! mQ.isEmpty() ){
                    bNode next = mQ.dequeue();
                    for(bNode n : next.getAjdacent()){
                        if(n.visited == false){
                            visit(n);
                            n.visited = true;
                            queue.enqueue(n);
                        }
                    }
                }
            }
        */


        //So to solve this problem, we will call one of the above graph traversal algorithms on the start node
        //and our visit function will check if the node is the same as the node you are trying to get to.

    }  

    public static void q3(String[] args) throws Exception{
        //Given a sorted (increasing order) array with unique integer elements, write an algorithm 
        //to create a binary search tree with minimal height
        SOP("Running q3");
        if(args.length < 2){
            SOP("ERROR: Must specify list of nodes");
            return;
        }

        ArrayList<Integer> mList = new ArrayList<Integer>();
        for(int i = 1; i < args.length; i++){
            mList.add(Integer.parseInt(args[i]));
        }

        //make sure the list is sorted
        if(isListSorted(mList) == false)
            Collections.sort(mList);

        bNode root = buildMinimalBST(mList, 0, mList.size() -1);
        SOP("Minimal BST Created. Now printing inOrderTraversal");
        printInOrderTraversal(root);

    }

    public static bNode buildMinimalBST(ArrayList<Integer> mList, int start, int end){
        if(start > end) return null;

        int mid = (end+start)/2;
        bNode root = new bNode(mList.get(mid));
        root.left = buildMinimalBST(mList, start, mid -1);
        root.right = buildMinimalBST(mList, mid +1, end);
        return root;
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
    public static void printPreOrderTraversal(bNode root){
        if(root == null) return;
        SOP(root.data);
        printPreOrderTraversal(root.left);
        printPreOrderTraversal(root.right);
    }

    public static void printInOrderTraversal(bNode root){
        if(root == null) return;
        printPreOrderTraversal(root.left);
        SOP(root.data);
        printPreOrderTraversal(root.right);
    }

    public static void printPostOrderTraversal(bNode root){
        if(root == null) return;
        printPreOrderTraversal(root.left);
        printPreOrderTraversal(root.right);
        SOP(root.data);
    }

    public static boolean isListSorted(ArrayList<Integer> mList){
        int temp = mList.get(0);
        for(int i = 1; i < mList.size(); i++){
            if(mList.get(i) < temp) return false;
            temp = mList.get(i);
        }
        return true;
    }
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