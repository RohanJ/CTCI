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
        SOP("q4\tQuestion 4");
        SOP("q5\tQuestion 5");
        SOP("q6\tQuestion 6");
        SOP("q7\tQuestion 7");
        SOP("q9\tQuestion 9");
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
        else if(function.equals("q6")) q6(args);
        else if(function.equals("q7")) q7(args);
        else if(function.equals("q9")) q9(args);
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
        //Question 4.3: Given a sorted (increasing order) array with unique integer elements, write an algorithm 
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

    public static void q4(String[] args) throws Exception{
        //Question 4.4: Given a binary tree, design an algorithm which creates a linked list of all the nodes at
        //each depth(e.g., if you have a tree with depth D, you'll have D linked lists).
        SOP("Running q4");
        if(args.length != 1){
            SOP("This question takes no parameters");
            return;
        }

        bNode root = generateSampleTree1();
        ArrayList<LinkedList<bNode>> mList = generateLinkedListPerDepth(root);
        int level = 0;
        for(LinkedList<bNode> mLL : mList){
            SOP("Level " + level +": ");
            level++;
            for(bNode n : mLL){
                System.out.print(n.data + " ");
            }
            SOP("");
        }

    }

    public static void q5(String[] args) throws Exception{
        //Question 4.5: Implement a function to check if a binary tree is a binary search tree

        //Recall that binary search tree enforces that the left of each node is <= and the right is >
        SOP("Running q5");
        if(args.length != 1){
            SOP("This question takes no parameters");
            return;
        }

        bNode defNotABST = generateSampleTree1();

        ArrayList<Integer> mList = new ArrayList<Integer>();
        for(int i = 0; i < 16; i++){
            mList.add(i);
        }
        bNode isABST = buildMinimalBST(mList, 0, mList.size() -1);

        if(isBinaryTreeABinarySearchTree(defNotABST) == true) SOP("Tree 1 IS a Binary Search Tree. Function does not work.");
        else SOP("Tree 1 is NOT a Binary Search Tree. Function works!");

        if(isBinaryTreeABinarySearchTree(isABST) == true) SOP("Tree 2 IS a Binary Search Tree. Function works!");
        else SOP("Tree 2 is NOT a Binary Search Tree. Function does not work.");
    }

    public static void q6(String[] args) throws Exception{
        //Question 4.6: Write an algorithm to find the 'next' node (i.e., in order successor) of a given node in
        //a binary search tree. You may assume that each node has a link to its parent.
        SOP("Running q6");
        if(args.length != 1){
            SOP("This question takes no parameters");
            return;
        }

        bNode mBinarySearchTree = generateSampleBinarySearchTree1();

        for(int i = 1; i<=7; i++){
            bNode result = findNextInOrderSucessor(mBinarySearchTree, i);
            if(result != null){
                SOP("The next node for ["+i+"] is [" + result.data +"]");
                if(result.data == (i+1) ) SOP("Function CORRECT");
                else SOP("Function INCORRECT");
            }
            else{
                SOP("The next node for ["+i+"] is null");
                if(i==7) SOP("Function CORRECT");
                else SOP("Function INCORRECT");
            }
        }
    }

    public static void q7(String[] args) throws Exception{
        //Question 4.7: Design an algorithm and write code to find the first common ancestor of two nodes
        //in a binary tree. Avoid storing addidiotnal nodes in a data structure. NOTE: This not necessairly a
        //binary search tree
        SOP("Running q7");
        if(args.length != 1){
            SOP("The question takes no parameters");
            return;
        }

        bNode root = generateSampleTree1();
        //4 and 5 should return 2
        //4 and 7 should return 1
        //5 and 6 should return 1
        //1 and 5 should return 1
        //1 and 7 should return 1

        bNode retVal1 = findFirstCommonAncestor(root, 4, 5);
        bNode retVal2 = findFirstCommonAncestor(root, 4, 7);
        bNode retVal3 = findFirstCommonAncestor(root, 5, 6);
        bNode retVal4 = findFirstCommonAncestor(root, 1, 5);
        bNode retVal5 = findFirstCommonAncestor(root, 1, 7);

         
        testResult(retVal1.data, 2, "Test 1");
        testResult(retVal2.data, 1, "Test 2");
        testResult(retVal3.data, 1, "Test 3");
        testResult(retVal4.data, 1, "Test 4");
        testResult(retVal5.data, 1, "Test 5");
    }

    public static void q9(String[] args) throws Exception{
        //Question 4.9: You are given a binary tree in which each node contains a value. Design an algorithm
        //to print all pathswhich sum to a given value. The path does not need to start or end at the root or
        //a leaf
        SOP("Running q9");
        if(args.length != 1){
            SOP("This question takes no parameters");
            return;
        }

        bNode root = generateSampleTreeForPathsWithSumQuestion();
        printAllPathsWithSum(root, 5);
    }

    public static void printAllPathsWithSum(bNode root, int sum){
        //Notice that we cannot make any assumption on whether the numbers are positive or negative
        //
        int treeHeight = findTreeHeight(root);
        int[] path = new int[treeHeight];
        findPathWithSum(root, sum, path, 0);
    }

    public static void findPathWithSum(bNode root, int sum, int[] path, int currLevel){
        if(root == null) return;
        
        path[currLevel] = root.data;
    
        int currSum = 0;
        for(int i = currLevel; i>=0; i--){
            currSum += path[i];
            if(currSum == sum){
                printPath(path, i, currLevel);
            }
        }

        findPathWithSum(root.left, sum , path, currLevel + 1);
        findPathWithSum(root.right, sum , path, currLevel + 1);
    }

    public static int findTreeHeight(bNode root){
        if(root == null) return 0;
        else return 1+ Math.max(findTreeHeight(root.left), findTreeHeight(root.right));
    }

    public static void printPath(int[] path, int start, int end){
        for(int i = start; i <= end; i++){
            System.out.print(path[i] + " ");
        }
        SOP("");
    }


    public static bNode findFirstCommonAncestor(bNode root, int node1, int node2){
        return findFirstCommonAncestor(root, new bNode(node1), new bNode(node2));
    }

    public static bNode findFirstCommonAncestor(bNode root, bNode node1, bNode node2){
        if(root == null || node1 == null || node2 == null) return null;
        //Note that we cannot assume if the nodes have links to their parent. If they do, then we can simply start at
        //each node and traverse up marking the nodes as visited and see where they converge.

        //Given the case where the nodes do not have links to their parents
        if(root.data == node1.data || root .data == node2.data) return root;
        boolean isNode1OnLeft = isNodeOnSide(root.left, node1);
        boolean isNode2OnLeft = isNodeOnSide(root.left, node2);

        if(isNode1OnLeft != isNode2OnLeft) return root; //meaning we have found the divergent point

        //reach here indicating that they are on the same side. So if the boolean above was true, they
        //are both on the left side.
        if(isNode1OnLeft) return findFirstCommonAncestor(root.left, node1, node2);
        else return findFirstCommonAncestor(root.right, node1, node2);
    }

    public static boolean isNodeOnSide(bNode root, bNode tNode){
        if(root == null) return false;
        if(root.data == tNode.data) return true;
        return isNodeOnSide(root.left, tNode) || isNodeOnSide(root.right, tNode);
    }

    public static bNode findNextInOrderSucessor(bNode root, int origin){
        //Due to the properties of a BST (left child is <= and right child is >), we can use the following algorithm:
        //If right subtree exists, find the left most leaf in right subtree
        //else backtrack upwards until you find a node such that the node is the left
        //child of its parent

        if(root == null) return null;
        
        bNode start = binarySearch(root, origin);
        if(start == null) return null; //meaning not found

        if(start.right != null) return findLeftMostChild(start.right);
        else{
            //backtrack up until we find a node who is the left child of its parent
            if(start.parent == null) return null; //meaning there is no next in order successor
            bNode parent = start.parent;
            bNode curr = start;
            while(parent.right == curr){
                curr = parent;
                parent = curr.parent;
                if(parent == null) return null;
            }
            return parent;
        }
    }

    public static boolean isBinaryTreeABinarySearchTree(bNode root){
        if(root == null) return true;
        if(root.left != null){
            if(root.left.data > root.data) return false;
        }
        if(root.right != null){
            if(root.right.data <= root.data) return false;
        }

        return isBinaryTreeABinarySearchTree(root.left) && isBinaryTreeABinarySearchTree(root.right);
    }

    public static ArrayList<LinkedList<bNode>> generateLinkedListPerDepth(bNode root){
        ArrayList<LinkedList<bNode>> mList = new ArrayList<LinkedList<bNode>>();
        gLLPD_helper(root, mList, 0);
        return mList;
    }

    public static void gLLPD_helper(bNode root, ArrayList<LinkedList<bNode>> mList, int level){
        if(root == null) return;

        LinkedList<bNode> tList = null;
        if(mList.size() == level){
            tList = new LinkedList<bNode>();
            mList.add(tList);
        }
        else{
            tList = mList.get(level);
        }

        tList.add(root);
        gLLPD_helper(root.left, mList, level +1);
        gLLPD_helper(root.right, mList, level +1);

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
        printInOrderTraversal(root.left);
        SOP(root.data);
        printInOrderTraversal(root.right);
    }

    public static void printPostOrderTraversal(bNode root){
        if(root == null) return;
        printPostOrderTraversal(root.left);
        printPostOrderTraversal(root.right);
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

    public static bNode generateSampleBinarySearchTree1(){
        /*
        *                     4
        *                   /  \
        *                 2     6
        *               /  \   / \
        *             1    3  5  7
        */

        //Level 0
        bNode root = new bNode(4);
        
        //Level 1
        root.left = new bNode(2);
        root.right = new bNode(6);
        root.left.parent = root;
        root.right.parent = root;
        
        //Level 2
        root.left.left = new bNode(1);
        root.left.right = new bNode(3);
        root.left.left.parent = root.left;
        root.left.right.parent = root.left;
        
        //Level 3
        root.right.left = new bNode(5);
        root.right.right = new bNode(7);
        root.right.left.parent = root.right;
        root.right.right.parent = root.right;
        
        return root;
    }

     public static bNode generateSampleTreeForPathsWithSumQuestion(){
        /*
        *                     1
        *                   /  \
        *                 2     3
        *               /  \   / \
        *             2   5  6  1
        *           /            \
        *         3               -5
        *           \
        *            -1
        */

        bNode root = new bNode(1);
        root.left = new bNode(2);
        root.right = new bNode(3);
        root.left.left = new bNode(2);
        root.left.right = new bNode(5);
        root.right.left = new bNode(6);
        root.right.right = new bNode(1);

        root.left.left.left = new bNode(3);
        root.left.left.left.right = new bNode(-1);
        root.right.left.right = new bNode(-5);
        return root;
    }

    public static bNode findLeftMostChild(bNode root){
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    public static bNode binarySearch(bNode root, int d){
        //only works on binary search tree
        if(root == null) return null;
        if(root.data == d) return root;
        if(d<=root.data) return binarySearch(root.left, d);
        else return binarySearch(root.right, d);
    }

    public static void testResult(int result, int whatItShouldBe, String testName){
        if(result == whatItShouldBe) SOP(testName + " PASSED");
        else{ 
            SOP(testName + " FAILED"); 
            SOP("Result was: " + result +"; whatItShouldBe: " + whatItShouldBe);
        }
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