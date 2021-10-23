import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author Hannah Foley
 *  @VERSION 09/10/18 11:13:22
 *  @timeSpent 6.5 hours so far
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data:
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 *
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{
    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    public class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
        // value once from the constructor DLLNode.
        private DLLNode next;
        public DLLNode prev;

        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode)
        {
            data = theData;
            prev = prevNode;
            next = nextNode;
        }

        public DLLNode getNext() {
            return next;
        }

        public void setNext(DLLNode next) {
            this.next = next;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList()
    {
        head = null;
        tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost:
     * Constant running time. Big theta(1)
     *
     * Justification:
     * This method only includes comparisons and simple operations which take a constant running time
     */
    public boolean isEmpty()
    {
        //If the list is empty then both head and tail are null.
        return head == null && tail == null;
    }

    //returns head of DLL
    public DLLNode getHead()
    {
        return head;
    }

    //returns tail of DLL
    public DLLNode getTail()
    {
        return tail;
    }

    // Counts the number of nodes in a DLL
    /** Worst-case asymptotic running time cost: T(N) = big theta (N)
     *
     * Justification:
     * - lines 1-3 = constant running time big theta(1)
     * - while loop = counts for the number of nodes in the DLL so if there is N nodes in the DLL then the
     * running time will be big theta(N)
     * -the final line will also have a constant running time of big theta(1)
     * - all these added together is big theta (1) + big theta(N) + big theta(1)
     * - the properties of asymptotic notation tells us that when adding, we should only keep the highest order of terms
     * - therefore the asymptotic running time cost of size is T(N) = big theta (N)
     *
     */
    public int size()
    {
        int count;
        DLLNode currentNode = head;
        if (head != null)
        {
            count = 1;
            while (currentNode != tail && currentNode != null)
            {
                count++;
                currentNode = currentNode.next;
            }
        }
        else
        {
            count = 0;
            return count;
        }
        return count;
    }

    //returns a node at the given position (index) of the DLL
    /** Worst-case asymptotic running time cost: T(N) = N
     *
     * Justification:
     * The worst case scenario for this function is that the position passed in is in the later half of the DLL, so all
     * the if statements must be judged before entering the else statement. The longest running time would occur if the
     * position is in the middle of the list
     * - first line is getting the number of nodes in the DLL, as shown above, this has an asymptotic order of growth of
     *   big theta(N)
     * - the next line is a simple comparison in an if statement, resulting in asymptotic order of growth of big theta(1)
     * - similarly the next if statement consists of constant time operations, big theta(1)
     * - in this case, none of the if statements will be entered and the else statement will be implemented
     * - the first three lines in the else statement are constant time operations as they are basic assignments, this
     * gives a constant order of growth, big theta(1)
     * - the while loop iterates while the index is greater than the requested position. In this case, the position can
     * only be greater than the halfway mark, For this reason the maximum iterations the loop can do is N/2, N being the
     * size of the DLL. The operations within the while loop are constant time. So the asymptotic order of growth of
     * the while loop is big theta(N/2)
     * - Then the current node is returned, which is again a constant time operation
     * - All of these added together gives:
     *  big theta(N) + big theta(1) + big theta(1) + big theta(N/2)
     * - The properties of adding asymptotic order of growths tells us that only the highest order term should be kpet
     * - Therefore the asymptotic running time of this function is big theta(N)
     *
     */

    public DLLNode getNodeAt(int pos)
    {
        //if the DLL is empty return null
        if(isEmpty())
        {
            return null;
        }
        int DLLLength = size();
        //if the position given is lower 0, the first node is returned
        if (pos < 0)
        {
            pos = 0;
        }
        //if the position is greater than the number of nodes in the DLL, then the last node is returned
        if (pos > DLLLength-1)
        {
            pos = DLLLength - 1;
        }
        //if the node requested is in the first half of the DLL, then we will start searching from the head
        if (pos < DLLLength/2)
        {
            int index = 0;
            DLLNode currentNode = head;
            DLLNode previousNode = null;
            while (index < pos) {
                previousNode = currentNode;
                currentNode = currentNode.getNext();
                index++;
            }
            return currentNode;
        }
        //otherwise we start searching from the tail
        else
        {
            int index = DLLLength-1;
            DLLNode currentNode = tail;
            DLLNode previousNode = tail.prev;
            while (index > pos) {
                currentNode = previousNode;
                previousNode = previousNode.prev;
                index--;
            }
            return currentNode;
        }
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: T(N) = big theta (N)
     *
     * Justification:
     *  Worst case is when a node is added to the middle of the list as the list must be iterated through until the end
     *  to get to the last node
     *  -The first if statement condition will be checked and the statement will not be entered, this will give a
     *  constant run time of big theta (1)
     *  -The second if statement condition will be checked and also not entered. The checking gives a constant run time of
     *  big theta (1)
     *  -The else statement will then be entered. The first operation here is creating a node. This is a constant running
     *  time operation of big theta (1)
     *  -Then the previous node to the position we want to add to is accessed using the getNodeAt function. This function
     *  gives a worse case scenario running time of big theta(N)
     *  -This operation is performed again to access the node after the position which we want to add. Big theta (N)
     *  -The remaining lines within the else statement are constant time operations, Big theta (1)
     *  All this added together gives:
     *  big theta (1) + big theta (1) + big theta (1) + big theta(N) + Big theta (1)
     *      the properties of asymptotic notation tells us that when adding, we should only keep the highest order of terms
     *    - therefore the asymptotic running time cost of insertBefore is T(N) = big theta (N)
     */
    public void insertBefore( int pos, T data )
    {
        if (pos < 0 || pos == 0){
            //node is added to first position in the list and made the head of the list
            int size = size();
            DLLNode temp = new DLLNode(data, null, (size == 0 ? null : getNodeAt(0)));
            //if this is the node added is the first node being added to the DLL, it will be both the head
            // and the tail
            if (size == 0)
            {
                head = temp;
                tail = head;
            }
            else {
                //change prev of next note
                if(pos < 0)
                {
                    pos = 0;
                }
                DLLNode nextNode = getNodeAt(pos);
                nextNode.prev = temp;
                nextNode.next = (pos+1>=size? null : getNodeAt(pos+1));
                head = temp;
            }
        }
        else if (pos >= size()){
            //node is added to the end of the list and made the tail of the list
            //create temp node
            int size = size();
            if (size == 0)
            {
                DLLNode temp = new DLLNode(data, null, null);
                head = tail = temp;
            }
            else
            {
                DLLNode temp = new DLLNode(data, getNodeAt(size-1), null);
                //move tail to be this node
                tail = temp;
                //change next of prev node
                DLLNode prevNode = getNodeAt(size-1);
                prevNode.next = temp;
                //change prev of node added to be the previous node, and not the tail poointer
                temp.prev = prevNode;
            }

        }
        else {
            DLLNode temp = new DLLNode(data, null, null);
            //change next of prev node
            DLLNode prevNode = getNodeAt(pos-1);
            temp.prev = prevNode;
            DLLNode nextNode = getNodeAt(pos);
            temp.next = nextNode;
            nextNode.prev = temp;
            prevNode.next = temp;
        }

        return;
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: T(N) = big theta(N)
     *
     * Justification:
     *  -first if statement is a simple compare operation and will take a constant running time of big theta (1)
     *  -The getNodeAt function has a worse case scenario run time of big theta(N)
     *  -Returning the data has a constant run time of big theta (1)
     *  -All this added together gives us:
     *  big theta(1) + big theta(N) + big theta(1)
     *  - The properties of adding orders of growth tells us to only keep the highest order term
     *  - So the asymptotic order of growth of this function is big theta(N)
     *
     */
    public T get(int pos)
    {
        //if the position is not within the bounds of the list, return null
        if (pos >= size() || pos < 0)
        {
            return null;
        }
        //else use get node function to get node in that position
        DLLNode currentNode = getNodeAt(pos);
        //return this nodes position
        return currentNode.data;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified.
     *
     * Worst-case asymptotic running time cost: T(N) = big theta (N)
     *
     * Justification:
     *  - The worst case scenario of this function is when the position requested is in between two nodes
     *  - The first line will count the number of nodes in the DLL, This uses the size() function which has an
     *  asymptotic run time of big theta (N)
     *  - The condition of the first if statement is checked and found to be false, so the function moves onto the
     *  next else if statement. This will have a constant running time of big theta (1)
     *  - The else if statement condition will also be checked, this will also have a constant running time of big theta (1)
     *  - The next else if statement will do the same, having a constant running time of big theta (1)
     *  - Finally, the else statement will be entered. The first two lines call the getNode function. This function
     *  has an asymptotic order of growth of big theta (N)
     *  - The final two lines in the function are simple assignments, giving a constant run time big theta (1)
     *  All of this added together gives:
     *  big theta (N) + big theta (1) + big theta (1) + big theta (1) + big theta (N) + big theta (N) + big theta (1)
     *  - The properties of adding orders of growth tells us to only keep the highest order term
     *  - So the asymptotic order of growth of this function is big theta(N)
     */
    public boolean deleteAt(int pos)
    {
        int DLLLength = size();
        //if the position is not within the bounds of the list, then no modification is made
        if (pos >= DLLLength || pos < 0) {
            return false;
        }
        if (DLLLength == 1)
        {
            head = null;
            tail = null;
        }
        else if (pos == 0) //looking to delete the head of the DLL
        {
            DLLNode nextNode = getNodeAt(1);
            nextNode.prev = null;
            head = nextNode;
        }
        else if (pos == DLLLength-1) //looking to delete the tail of the DLL
        {
            DLLNode prevNode = getNodeAt(DLLLength-2);
            prevNode.next = null;
            tail = prevNode;
        }
        else {
            //else, get to the node before and change next pointer
            DLLNode prevNode = getNodeAt(pos - 1);
            DLLNode nextNode = getNodeAt(pos + 1);
            prevNode.next = nextNode;
            //get to node after and change prev pointer
            nextNode.prev = prevNode;
        }
        return true;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: T(N) = big theta (N^2 /2)
     *
     * Justification:
     *  - First if statement has a constant run time = big theta(1)
     *  - Finding length function has a run time of big theta (N)
     *  - Finding halfway point is simple arithmetic and has a constant run time of big theta(1)
     *  - For loop iterates N/2 times. Within this loop, the get function is called twice, this function takes a worse
     *  case asymptotic run time of big theta (N). The deleteAt function is also called twice. This function has a
     *  worse case asymptotic run time of big theta(N). Finally in this loop, the insertBefore function is called twice
     *  which has an asymptotic run time of big thata(N). So the run time within the for loop is:
     *  2*big theta (N) + 2*big theta (N) + 2*big theta (N)
     *  - The for loop run time is therefore big theta (N)
     *  - The loop iterates N/2 times.
     *  - The multiplication properties of asymptotic functions tells us that we should multiply the inner functions
     *  - This will mean that the for loop will total to running time of big theta (N^2 /2)
     *  - All this added together gives:
     *  big theta(1) + big theta(N) + big theta(1) + big theta (N^2 /2)
     *  _ The addition properties of asymptotic functions tells us that we should only keep the highest order term
     *  - Therefore the asymptotic order of growth for the reverse function is big theta (N^2 /2)
     *
     */
    public void reverse()
    {
        if (isEmpty())
        {
            return;
        }
        //for each node
        //take end one and swap it with the start one
        //until get tp halfway point
        int DLLLength = size();
        int halfwayPoint = DLLLength/2;

        for(int i = 0; i < halfwayPoint; i++)
        {
            //moving last node to the top and deleting last node
            T data = get(DLLLength-1-i);
            deleteAt(DLLLength-1-i);

            T data2 = get(i);
            deleteAt(i);

            insertBefore(i, data);
            insertBefore(DLLLength-1-i, data2);
        }
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: T(N) = big theta (N^5 - N^4)
     *
     * Justification:
     * -Outer for loop condition statement takes a run time of big theta (N). This first for loop will iterate for
     * the length of the DLL
     * -The first line within the outer for loop will call the getNodeAt function which has a worse case asymptotic run
     * time of big theta (N)
     * - The next line within the outer for loop has a constant run time of big theta (1)
     * - The inner for loop will be iterated through N-1 times, given that the length of the DLL is N
     * - The condition for this loop will take a run time of big theta (N)
     * - The worst case scenario would be that each element in the list has one or more duplicates, but as they are deleted,
     * the length of the DLL changes so the outer loops will get iterated less.
     * - The if statement within the inner for loop has a run time cost of big theta (N) as it calls the get function
     * - If this if statement is entered, the deleteAt function will be called and this has a run time of big theta(N)
     * - Decrementing j has a constant run time of big theta (1)
     * - So the cost of the if statement within the inner for loop is  big theta (1) + big theta (N) + big theta (N) = big theta (N)
     * - The inner loop therefore will have a cost of times iterated * entry condition cost * whats within cost
     * - big theta (N-1) * big theta (N) * big theta (N)
     * - the inner loop has a total run time of big theta (N^3 - N^2)
     * - Similarly, the outer loop will have a cost of times iterated * entry condition cost * whats within cost
     * - big theta (N) * big theta (N) * (big theta (N^3 - N^2) + big theta (1) + big theta (N))
     * = big theta (N^5 - N^4)
     *
     */
    public void makeUnique()
    {
        //starting at head, loop through the remaining nodes until tail is reached
        //if duplicate is found, delete the duplicate node
        //deleting that node means the surrounding node's prev and nexts must be changed
        //deleting then and there could cause trouble in for loop
        //do this for every node in the DDL
        for(int i = 0; i < size(); i++)
        {
            DLLNode currentNode = getNodeAt(i);
            T currentData = currentNode.data;
            for (int j = i+1; j < size(); j++)
            {
                if (currentData == get(j))
                {
                    deleteAt(j);
                    //have to bring back j position now after the list has been altered
                    j--;
                }
            }
        }

    }


    /*----------------------- STACK API
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: T(N) = big theta (N)
     *
     * Justification:
     *  This method calls the insertBefore method which has a worst-case asymptotic running time cost of big theta (N)
     */
    public void push(T item)
    {
        insertBefore(0, item);
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: T(N) = big theta (N)
     *
     * Justification:
     *  -The if condition is checked using rhe isEmpty method which has a constant run time of big theta (1)
     *  -The data in the head of the DLL is then obtained. This will have a constant run time also, of big theta (1)
     *  -Then the deleteAt function will be called, This has a worse case asymptotic run time of big theta (N)
     *  -Finally the data is returned which will have a constant run time of big theta (1)
     *  -All of this added together gives us:
     *  = big theta (1) + big theta (1) + big theta (N) + big theta (1)
     *  - We should only keep the term that is of the highest order.
     *  - Therefore the run time of pop is big theta (N)
     */
    public T pop()
    {
        if (!isEmpty())
        {
            T data = head.data;
            deleteAt(0);
            return data;
        }
        return null;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: T(N) = big theta (N)
     *
     * Justification:
     *  This function calls the insertBefore function which has a worse case asymptotic run time of big theta (N)
     */
    public void enqueue(T item)
    {

        insertBefore(size(), item);
    }

    /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an equeue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: T(N) = big theta (N)
     *
     * Justification:
     * -The if condition is checked using rhe isEmpty method which has a constant run time of big theta (1)
     *  -The data in the head of the DLL is then obtained. This will have a constant run time also, of big theta (1)
     *  -Then the deleteAt function will be called, This has a worse case asymptotic run time of big theta (N)
     *  -Finally the data is returned which will have a constant run time of big theta (1)
     *  -All of this added together gives us:
     *  = big theta (1) + big theta (1) + big theta (N) + big theta (1)
     *  - We should only keep the term that is of the highest order.
     *  - Therefore the run time of dequeue is big theta (N)
     */
    public T dequeue()
    {
        if (!isEmpty())
        {
            T data = head.data;
            deleteAt(0);
            return data;
        }
        return null;
    }


    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        boolean isFirst = true;

        // iterate over the list, starting from the head
        for (DLLNode iter = head; iter != null; iter = iter.next)
        {
            if (!isFirst)
            {
                s.append(",");
            } else {
                isFirst = false;
            }
            s.append(iter.data.toString());
        }

        return s.toString();
    }


}


