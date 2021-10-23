import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
    }

    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.

    //Check if the isEmpty works

    @Test
    public void testIsEmpty()
    {
        //with a DLL with 1 node --> should return false
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        assertTrue(testDLL.isEmpty() == false);

        //with a DLL with 3 nodes --> should return false
        testDLL.insertBefore(0, 2);
        testDLL.insertBefore(0, 3);
        assertTrue(testDLL.isEmpty() == false);

        //with an empty DLL --> should return true
        DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
        assertTrue(testDLL2.isEmpty() == true);
    }


    /**
     * Check if the size() works
     */
    @Test
    public void testSize()
    {
        //test with an empty DLL, should return 0
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        assertEquals( "Checking size() with an empty list - expected result 0", 0, testDLL.size() );

        //test with a DLL with 5 nodes, should return 5
        testDLL.insertBefore(0, 2);
        testDLL.insertBefore(0, 3);
        testDLL.insertBefore(0, 2);
        testDLL.insertBefore(0, 3);
        testDLL.insertBefore(0, 2);
        assertEquals( "Checking size() with a list of 5 nodes - expected result 5", 5, testDLL.size() );
    }

    //Check if the getNodeAt works

    @Test
    public void testGetNodeAt()
    {
        //test asking to get a position from an empty DLL, should return null
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        assertEquals( "Checking getNodeAt() - expected result null", null, testDLL.getNodeAt(7));

        //test asking to get a position from the second half of the DLL so the iteration will begin from tail
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(0, 2);
        testDLL.insertBefore(0, 3);
        testDLL.insertBefore(0, 4);
        testDLL.insertBefore(0, 5);
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(0, 2);
        testDLL.insertBefore(0, 3);
        testDLL.insertBefore(0, 4);
        testDLL.insertBefore(0, 5);
        int data = testDLL.getNodeAt(9).data;
        assertEquals( "Checking getNodeAt() - expected result 1", 1, data);


        //test asking to get a position from the first half of the DLL so the iteration will begin from head
        data = testDLL.getNodeAt(7).data;
        assertEquals( "Checking getNodeAt() - expected result 3", 3, data);

        //test asking to get a node from a minus position, should bring the first node in the DLL
        data = testDLL.getNodeAt(-3).data;
        assertEquals( "Checking getNodeAt() - expected result 5", 5, data);


        //test asking to get a node from a position outside of the bounds of the length of the DLL, should bring
        // the last node in the DLL
        data = testDLL.getNodeAt(90).data;
        assertEquals( "Checking getNodeAt() - expected result 1", 1, data);
    }


    @Test
    public void testGet()
    {
        //test when DLL is empty
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        assertEquals( "Checking get with an empty DLL - expected result null", null, testDLL.get(7) );

        //test when requesting a position less than 0
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(0, 2);
        testDLL.insertBefore(0, 3);
        testDLL.insertBefore(0, 4);
        testDLL.insertBefore(0, 5);
        Integer result = null;
        assertEquals( "Checking get requesting a position less than 0 - expected result null", result, testDLL.get(-9) );

        //test when requesting a position greater than the length of the DLL
        result = null;
        assertEquals( "Checking get requesting a position greater than  length - expected result 1", result, testDLL.get(300) );

        //test when requesting a valid index
        result = 4;
        assertEquals( "Checking get requesting a valid position - expected result 2", result, testDLL.get(1) );

    }

    @Test
    public void testDeleteAt()
    {
        //delete with empty DLL
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.deleteAt(7);
        assertEquals( "Checking deleteAt with an empty list", "", testDLL.toString() );

        //check on a list with one element, head and tail should become null again
        testDLL.insertBefore(0, 1);
        testDLL.deleteAt(0);
        assertEquals( "Checking deleteAt with a list with 1 element list", "", testDLL.toString() );
        assertTrue(testDLL.getHead()==null);
        assertTrue(testDLL.getTail()==null);

        //delete head
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(0, 2);
        testDLL.insertBefore(0, 3);
        testDLL.insertBefore(0, 4);
        testDLL.insertBefore(0, 5);
        testDLL.deleteAt(0);
        assertEquals( "Checking deleteAt to delete first node", "4,3,2,1", testDLL.toString() );

        //delete tail
        testDLL.deleteAt(3);
        assertEquals( "Checking deleteAt to delete last node", "4,3,2", testDLL.toString() );

        //delete a middle node
        testDLL.deleteAt(1);
        assertEquals( "Checking deleteAt to delete a middle node", "4,2", testDLL.toString() );

        //delete when position sent in is less than 0, should not modify the list
        testDLL.deleteAt(-7);
        assertEquals( "Checking deleteAt with requested position less than 0", "4,2", testDLL.toString() );

        //delete when position sent in is greater than the length of the DLL, should not modify the list
        testDLL.deleteAt(70);
        assertEquals( "Checking deleteAt with requested position greater than length of DLL", "4,2", testDLL.toString() );
    }

    @Test
    public void testReverse()
    {
        //test with an empty array
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.reverse();

        //test with a valid array
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(0, 2);
        testDLL.insertBefore(0, 3);
        testDLL.insertBefore(0, 4);
        testDLL.insertBefore(0, 5);
        testDLL.reverse();
        assertEquals( "Checking testReverse", "1,2,3,4,5", testDLL.toString() );

        //one of the webcat tests, test reverse on a 2-element list {800, 900}
        DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
        testDLL2.insertBefore(0, 900);
        testDLL2.insertBefore(0, 800);
        testDLL2.reverse();
        assertEquals( "Checking testReverse", "900,800", testDLL2.toString() );
    }

    @Test
    public void testMakeUnique()
    {
        //test with 2 of the same
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(0, 2);
        testDLL.insertBefore(0, 3);
        testDLL.insertBefore(0, 4);
        testDLL.insertBefore(0, 5);
        testDLL.insertBefore(0, 5);
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique with a DLL that has 2 of the same elements", "5,4,3,2,1", testDLL.toString() );

        //test with multiple duplicate data
        testDLL.insertBefore(0, 1);
        testDLL.insertBefore(0, 4);
        testDLL.insertBefore(0, 5);
        testDLL.insertBefore(0, 5);
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique with a DLL that has multiple duplicates", "5,4,1,3,2", testDLL.toString() );

        //test a DLL that is already unique
        DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
        testDLL2.insertBefore(0, 1);
        testDLL2.insertBefore(0, 2);
        testDLL2.insertBefore(0, 3);
        testDLL2.insertBefore(0, 4);
        testDLL2.insertBefore(0, 5);
        testDLL2.makeUnique();
        assertEquals( "Checking makeUnique with a DLL that has multiple duplicates", "5,4,3,2,1", testDLL2.toString() );

        //test with an empty DLL
        DoublyLinkedList<Integer> testDLL3 = new DoublyLinkedList<Integer>();
        testDLL3.makeUnique();
        assertEquals( "Checking makeUnique with a DLL that has multiple duplicates", "", testDLL3.toString() );

        //One of the webcat tests
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.insertBefore(0, "test");
        list.insertBefore(0, "test");
        list.makeUnique();
        assertEquals( "Checking makeUnique with a DLL that has a pair of the same string ", "test", list.toString() );
        list.deleteAt(0);
        assertEquals( "Checking deleteAt with a DLL that only has 1 has one element", "", list.toString() );
    }

    @Test
    public void testPush()
    {
        //test multiples pushes, should always push onto the head of the list, check that the data stored in the head
        //is the most recent element pushed on
        DoublyLinkedList<Integer> testDLL= new DoublyLinkedList<>();
        testDLL.push(6);
        assertEquals( "Checking push", "6", testDLL.toString() );
        assertTrue(testDLL.getHead().data == 6);

        testDLL.push(7);
        assertEquals( "Checking push", "7,6", testDLL.toString() );
        assertTrue(testDLL.getHead().data == 7);

        testDLL.push(8);
        assertEquals( "Checking push", "8,7,6", testDLL.toString() );
        assertTrue(testDLL.getHead().data == 8);
    }

    @Test
    public void testPop()
    {
        //should always pop from the head
        DoublyLinkedList<Integer> testDLL= new DoublyLinkedList<>();
        testDLL.push(5);
        testDLL.push(4);
        testDLL.push(3);
        testDLL.push(2);
        testDLL.push(1);
        testDLL.pop();
        assertEquals( "Checking pop", "2,3,4,5", testDLL.toString() );
        assertTrue(testDLL.getHead().data == 2);

        testDLL.pop();
        assertEquals( "Checking pop", "3,4,5", testDLL.toString() );
        assertTrue(testDLL.getHead().data == 3);
    }

    @Test
    public void testEnqueue()
    {
        //should add at the end of the DLL
        DoublyLinkedList<Integer> testDLL= new DoublyLinkedList<>();
        testDLL.enqueue(6);
        assertEquals( "Checking enqueue", "6", testDLL.toString() );
        assertTrue(testDLL.getTail().data == 6);

        testDLL.enqueue(7);
        assertEquals( "Checking enqueue", "6,7", testDLL.toString() );
        assertTrue(testDLL.getTail().data == 7);

        testDLL.enqueue(8);
        assertEquals( "Checking enqueue", "6,7,8", testDLL.toString() );
        assertTrue(testDLL.getTail().data == 8);
    }

    @Test
    public void testDequeue()
    {
        //should remove from the head of the DLL
        DoublyLinkedList<Integer> testDLL= new DoublyLinkedList<>();
        testDLL.enqueue(5);
        testDLL.enqueue(4);
        testDLL.enqueue(3);
        testDLL.enqueue(2);
        testDLL.enqueue(1);
        testDLL.dequeue();
        assertEquals( "Checking dequeue", "4,3,2,1", testDLL.toString() );
        assertTrue(testDLL.getHead().data == 4);

        testDLL.dequeue();
        assertEquals( "Checking pop", "3,2,1", testDLL.toString() );
        assertTrue(testDLL.getHead().data == 3);
    }

}
