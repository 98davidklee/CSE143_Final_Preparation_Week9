// CSE 143 Final Preparation Answers (LinkedIntList) - Week 9  

public class LinkedIntList {
    private ListNode front;

    // #4 bubble    
    public boolean bubble() {
        boolean swap = false;
        if (front != null && front.next != null) {
            if (front.data > front.next.data) {
                swap = true;
                ListNode temp = front; 
                front = front.next;
                temp.next = front.next;
                front.next = temp;
            } 
            ListNode newFront = front;
            while (newFront.next != null && newFront.next.next != null) {
                if (newFront.next.data > newFront.next.next.data) {
                    swap = true;
                    ListNode temp = newFront.next;
                    newFront.next = newFront.next.next;
                    temp.next = newFront.next.next;
                    newFront.next.next = temp; 
                } 
                newFront = newFront.next;
            }
        }
        return swap;
    }

    // #5 compress
    public void compress(int factor) {
	    ListNode current = front;
	    while (current != null) {
		    int i = 1;
		    ListNode current2 = current.next;
		    while (current2 != null && i < factor) {
			    current.data += current2.data;
			    current.next = current.next.next;
			    i++;
			    current2 = current2.next;
		    }
		    current = current.next;
	    }
    }

    // #6 interleave
    public void interleave(LinkedIntList other) {
    	ListNode list1 = front;
    	ListNode list2 = other.front;
	    while (list1 != null && list2 != null) {
	    	ListNode temp1 = list1.next;
	    	ListNode temp2 = list2.next;
	    	list1.next = list2;
		    if (temp1 != null) {
			    list2.next = temp1;
		    }
		    list1 = temp1;
		    list2 = temp2;
	    }   
	    other = null;
    }   

    // post: appends the value to the end of the list
    public void add(int value) {
        if (front == null) {
            front = new ListNode(value);
        } else {
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(value);
        }
    }
}
