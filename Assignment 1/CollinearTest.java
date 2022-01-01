import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 18/09/18 12:21:26
 */

@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;
        
        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;
        
        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;
        
        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }
    
   /* @Test
    public void testCountCollinear1()
    {
    	//using arrays that will give 1 collinear line
    	int[] a3 = {1};
        int[] a2 = {2};
        int[] a1 = {3};
        
        int expectedResult = 1;
        
        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    } */
    
    @Test
    public void testCountCollinearNone()
    {
    	//using arrays that should give no collinear lines
    	int [] a1 = {4, 7, 21};
        int [] a2 = {5, 7, 9};
        int [] a3 = {5, 84, 16};
        
        int expectedResult = 0;
        
        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinearFast(a1, a2, a3)); 
    }
    
    @Test
    public void testSort()
    {
    	//test with array to be sorted
    	int [] a1 = {5, 9, 2, 7, 12, 75, 10};
    	Collinear.sort(a1);
    	int expected1 = 2;
    	int expectedLast = 75;
    	
    	assertEquals("sort(" + Arrays.toString(a1) + ") minimum", expected1, a1[0]);
    	assertEquals("sort(" + Arrays.toString(a1) + ") maximum", expectedLast, a1[a1.length-1]);
    	
    	//test with empty array
    	int [] a2 = { };
    	Collinear.sort(a2);
    	
    	//test with array with one number
    	int [] a3 = {18};
    	Collinear.sort(a3);
    	
    }
    
    public void testBinarySearch()
    {
    	//test with number that is in the given array
    	int [] a1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    	int valueToFind = 3;
    	boolean expectedResult = true;
        assertEquals("binarySearch(" + Arrays.toString(a1) + "," + valueToFind + ")",     expectedResult, Collinear.binarySearch(a1, valueToFind)); 

    	//test with number that is not in the array
        int [] a2 = {1, 2, 4, 5, 6, 7, 8, 9, 10};
        // valueToFind = 3;
    	expectedResult = false;
        assertEquals("binarySearch(" + Arrays.toString(a2) + "," + valueToFind + ")",     expectedResult, Collinear.binarySearch(a2, valueToFind)); 

    	//test with number and empty array
        int [] a3 = { };
        // valueToFind = 3;
        // expectedResult = false; 
        assertEquals("binarySearch(" + Arrays.toString(a3) + "," + valueToFind + ")",     expectedResult, Collinear.binarySearch(a3, valueToFind)); 
    }
    
    
}

