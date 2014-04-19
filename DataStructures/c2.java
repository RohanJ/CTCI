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
        SOP("q2\tQuestion 2");
        SOP("q3\tQuestion 3");
        SOP("q4\tQuestion 4");
        SOP("q5\tQuestion 5");
        SOP("q7\tQuestion 7");
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
        else if(function.equals("q7")) q7(args);
        else SOP("ERROR: Unknown function");
    }

    public static sNode generateSinglyLinkedList(String[] args){
        if(args.length < 3){
            SOP("ERROR: Must specify at least two nodes");
            return null;
        }

        //generate singly linked list from args
        sNode sHead = new sNode(Integer.parseInt(args[1]));
        for(int i =2; i<args.length; i++){
            sHead.appendToTail(Integer.parseInt(args[i]));
        }

        sHead.printList(sHead);
        return sHead;
    }

    public static void q1(String[] args) throws Exception{
        //Question 2.1: Write a code to remove duplicates from an unsorted linked list. How would 
        //you solve this problem if a temporary buffer is not allowed?
        
        SOP("Running q1");
        sNode sHead = generateSinglyLinkedList(args);
        if(sHead == null) return;

        sHead = removeDuplicates(sHead);
        SOP("After Remove Duplicates: ");
        sHead.printList(sHead);
    }

    public static void q2(String[] args) throws Exception{
        //Question 2.2: Implement an algorithm to find the kth to last element of a singly linked list
        SOP("Running q2");
        sNode sHead = generateSinglyLinkedList(args);
        if(sHead == null) return;
        
        Scanner s = new Scanner(System.in);
        System.out.print("Enter k value: ");
        int k = s.nextInt();

        sNode retNode = findKthToLastElement(sHead, k);
        if(retNode == null) SOP("k value of \"" + k + "\" is invalid");
        else SOP("The kth to last element is " + retNode.data);
    }

    public static void q3(String[] args) throws Exception{
        //Question 2.3: Implement an algorithm to delete a node in the middle of a singly linked list,
        //given only access to that node. For example, input: the node 3 from the linked list
        //1->2->3->4->5
        //Result, nothing is returned, but the new linked list looks like 1->2->4->5
        SOP("Running q3");
        sNode sHead = generateSinglyLinkedList(args);
        if(sHead == null) return;

        SOP("WARNING: This method assumes that the node you want to delete is in the middle of the list.");
        SOP("Therefore, for sake of example, there is no error checking involved if the node is not in the middle");

        Scanner s = new Scanner(System.in);
        System.out.print("Enter node to remove: ");
        int nodeToRemove = s.nextInt();


        SOP("\nRemoved " + nodeToRemove);
        sHead = removeNodeFromMiddleOfList(sHead, nodeToRemove);
        sHead.printList(sHead);
    }

    public static void q4(String[] args) throws Exception{
        //Question 2.4: Write code to partition a linked list around a value x, such that all nodes less than
        //x come before all nodes greater than or eaual to x
        SOP("Running q4");
        sNode sHead = generateSinglyLinkedList(args);
        if(sHead == null) return;

        Scanner s = new Scanner(System.in);
        System.out.print("Enter node to partition around: ");
        int partitionNode = s.nextInt();

        sHead = partitionListAroundNode(sHead, partitionNode);
        if(sHead == null) SOP("Partition Node \"" + partitionNode + "\" not found");
        else{
            SOP("After paritioning: ");
            sHead.printList(sHead);
        }
    }

    public static void q5(String[] args) throws Exception{
        //Question 2.5: You have two numbers represented by a linked list, where each node contains a
        //single digit. The digits are stored in reverse order, such that the 1's digit is at the head of the list.
        //Write a function that adds the two numbers and returns the sum as a linked list
        //Example
        //Input: (7 -> 1 -> 6 ) + (5 -> 9 -> 2). That is, 617 + 295.
        //Output: 2 -> 1 -> 9. That is, 912

        //This runs O(n) time
        SOP("Running q5");
        if(args.length != 1){
            SOP("ERROR: This function does not take any arguments");
            return;
        }
        Scanner s = new Scanner(System.in);
        SOP("Enter two numbers to add:");
        System.out.print("Number 1: ");
        int num1 = s.nextInt();
        System.out.print("Number 2: ");
        int num2 = s.nextInt();

        SOP("Generating reversed linked lists...");
        sNode sList1 = createReversedListFromNumber(num1);
        sNode sList2 = createReversedListFromNumber(num2);
        sList1.printList(sList1);
        sList2.printList(sList2);

        sNode result = addNumbers(sList1, sList2);
        if(result == null) SOP("ERROR. Invalid lists");
        else{
            SOP("The result is: ");
            result.printList(result);
        } 
     }

     public static void q7(String[] args) throws Exception{
        //Question 2.7: Implement a function to check if a linked list is a palindrome
        SOP("Running q7");
        sNode sHead = generateSinglyLinkedList(args);
        if(sHead == null) return;

        
        if(isListAPalindrome(sHead) == true) SOP("The above list IS a palindrome");
        else SOP("The above list is NOT a palindrome");

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

    public static sNode findKthToLastElement(sNode sHead, int k){
        //This can be done in O(n) run time but can vary in space complexity depending on
        //whether you use external data strucutres.
        //If you use an external data structure, such as a hash map where the value is the node
        //position and node value, we only need to iterate through the linked list once but
        //there is aO(n) space complexity as well. If we want O(1) space complexity, we simply 
        //iterate through the linked list twice. The first time to figure out the length, the second
        //time to get the kth to last position. This is still O(n) run time.

        //Here we assume kth is 0-based. Meaning if k is 0, we would return the last element.
        sNode curr = sHead;
        int  numElements = 0;
        while(curr != null){
            numElements++;
            curr = curr.next;
        }

        if(k >= numElements) return null;
        int elementWeWant = numElements - k;

        curr = sHead;
        numElements = 0;
        while(curr != null){
            numElements++;
            if(numElements == elementWeWant) return curr;
            curr = curr.next;
        }

        return null;
    }

    public static sNode removeNodeFromMiddleOfList(sNode sHead, int d){
        //The trick here is for the node we want to delete to assume the value and next pointer
        //of the next node. 
        //Assumptions: we are told that the value will be somewhere in the middle of the list. Therefore,
        //we dont have to worry about the beginning or the end of the list, meaning we are guaranteed
        //that the next node available
        //We can possibly circumvent this by adding a dummy node in the end

        sNode curr = sHead;
        while(curr != null){
            if(curr.data == d){
                curr.data = curr.next.data;
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }
        return sHead;
    }

    public static sNode partitionListAroundNode(sNode head, int partitionNode){
        //To solve this, we will use two linked lists, one to store all those that are less than
        //and the other to store all that are greater
        
        //sList1 will be all values less than
        sNode sList1 = new sNode(-1); //dummy node for head
        sNode sList1Tail = sList1;

        //sList2 will be all values greater than or equal to
        sNode sList2 = new sNode(-1); //dummy node for head
        sNode sList2Tail = sList2;


        sNode curr = head;
        sNode nodeToPartitionAround = null;
        while(curr != null){
            if(curr.data < partitionNode){
                sList1Tail = sListAdd(sList1Tail, curr.data);
            }
            else if(curr.data > partitionNode){
                sList2Tail = sListAdd(sList2Tail, curr.data);
            }
            else{
                nodeToPartitionAround = curr;
            }
            curr = curr.next;
        }

        if(nodeToPartitionAround == null) return null; //meaning not found

        sList1Tail.next = nodeToPartitionAround;
        nodeToPartitionAround.next = sList2.next; //we skip the dummy node

        return sList1.next; //we skip the dummy node
    }

    public static sNode addNumbers(sNode sList1, sNode sList2){
        if(sList1==null || sList2==null) return null;
        int carry_over = 0;
        sNode head = new sNode(-1); //dummy node
        sNode tail = head;

        int iSum = 0;
        int onesDigit = 0;
        while(sList1 != null && sList2 != null){
            iSum = sList1.data + sList2.data + carry_over;
            //next we isolate ones digit
            onesDigit = iSum%10;
            SOP("iSum: " + iSum + "; onesDigit: " + onesDigit);
            tail = sListAdd(tail, onesDigit);
            carry_over = (iSum >= 10) ? 1 : 0;
            sList1 = sList1.next;
            sList2 = sList2.next;
        }

        if(sList1 != null || sList2 != null){ 
            sNode theRest = (sList1 == null) ? sList2 : sList1;
            while(theRest != null){
                iSum = theRest.data + 0 + carry_over;
                onesDigit = iSum%10;
                tail = sListAdd(tail, onesDigit);
                carry_over = (iSum >= 10) ? 1 : 0;
                theRest = theRest.next;
            }
        }

         if(carry_over == 1){
                tail = sListAdd(tail, carry_over);
         }
         return head.next; //skip over dummy node and return
    }

    public static sNode findLoopStart(sNode head){
        //Implement the fast/slow runner method. If loop exists, guaratneed to overlap
        sNode fast = head;
        sNode slow = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) break; //we know there is a loop because of collision, but not necessairly where
        }

        //reach here indicating either there was a collision or fast pointer simply reached the end with no loop
        if(fast == null || fast.next == null) return null; //indicating no loop

        //Now we must find where the loop beginning is
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    public static boolean isListAPalindrome(sNode head){
        //If were using doubly linked lists we would first figure out the length then accordingly from the middle
        //expand to both ends and compare values. This will be done O(n)

        //However given that this we only have a singly linked list, we would find the middle, reverse the second half
        //and then compare linearly. Again this is O(n) time.
        int length = 0;
        sNode curr = head;
        while(curr != null){
            length++;
            curr = curr.next;
        }
        
        //travel to the middle node
        curr = head;
        for(int i = 1; i<=length/2; i++){
            curr = curr.next;
        }

        sNode sHalf = null;
        if(length % 2 == 0)
            sHalf = reverseList(curr);
        else
            sHalf = reverseList(curr.next);
        
        //next we compare the halves
        for(int i =0; i<length/2; i++){
            if(head == null) break;
            if(head.data != sHalf.data) return false;
            head = head.next;
            sHalf = sHalf.next;
        }
        return true;
    }


    //==========UTILITY FUNCTIONS
    public static sNode sListAdd(sNode tail, int d){
        tail.next = new sNode(d);
        return tail.next;
    }

    public static sNode createReversedListFromNumber(int num){
        String str = Integer.toString(num);
        sNode head = new sNode(-1); //dummyNode
        sNode tail = head;
        for(int i = str.length() -1; i >=0; i--){
            tail = sListAdd(tail, Character.getNumericValue(str.charAt(i)));
        }
        return head.next; //skip over dummy node
    }

    public static sNode reverseList(sNode head){
        //runs in O(n) time
        if(head.next == null) return head;
        sNode prev = null;
        sNode curr = head;
        sNode next = head.next;
        int count = 0;
        while(next != null){
            curr.next = prev;
            prev = curr;
            sNode temp = next.next;
            next.next = curr;
            curr = next;
            next = temp;
        }
        return curr;
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