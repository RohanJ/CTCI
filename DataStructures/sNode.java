public class sNode{
    //Singly Linked List node
    sNode next = null;
    int data;

    public sNode(int d) {
        data = d;
    }

    void appendToTail(int d){
        sNode end = new sNode(d);
        sNode curr = this;
        while(curr.next != null){
            curr = curr.next;
        }
        curr.next = end;
    }

    sNode deleteNode(sNode head, int d){
        sNode curr = head;
        if(curr.data == d){
            return head.next; //return the new head of the list
        }

        while(curr.next != null){
            if(curr.next.data == d){
                curr.next = curr.next.next;
                return head; //return the original head of the list
            }
            curr = curr.next;
        }
        return head;
    }

    void printList(sNode head){
        if(head == null) return;
        System.out.println("The following linked list was generated: ");
        System.out.print("" + head.data);
        head = head.next;
        while(head != null){
            System.out.print(" - > " + head.data);
            head = head.next;
        }
        System.out.println(";");
    }


}