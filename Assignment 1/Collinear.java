// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  
 *  @version 18/09/18 12:21:09
 */
class Collinear
{

   // ----------------------------------------------------------
    /**
     * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
     * A non-horizontal line will have to cross all three of these lines. Thus
     * we are looking for 3 points, each in a1, a2, a3 which lie on the same
     * line.
     *
     * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
     * 
     * x1(y2âˆ’y3)+x2(y3âˆ’y1)+x3(y1âˆ’y2)=0 
     *
     * In our case y1=1, y2=2, y3=3.
     *
     * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
     *
     * ----------------------------------------------------------
     *
     * 
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of growth: TODO
     *	T(N)= big pheta(N^3)
     *
     *  Explanation: TODO
     *  This is because:
     *  lines 1-5 are basic arithmetic and assigning values so take up pheta(1)
     *  the outermost forloop is iterated pheta N times, N being the size of array a1
     *  the second forloop in the middle is iterates pheta  N^2 times
     *  the thrid for loop is iterated pheta N^3 times 
     *  within the third for loop this calculation takes up pheta 1
     *  all of these totals added together is: pheta(1) + pheta(N) + pheta(N^2) + pheta(N^3) + pheta(1)
     *  the principles of addition tell us that we should keep only the highest order value, which is N^3
     *  so therefore the total running time T(N)= big pheta(N^3)
     */
    static int countCollinear(int[] a1, int[] a2, int[] a3)
    {
      //TODO: implement this method
      //x1*(y2−y3) + x2*(y3−y1) + x3*(y1−y2)=0 if collinear
    	int count = 0;
    	int y1 = 1;
    	int y2 = 2;
    	int y3 = 3;
    	for(int i = 0; i < a1.length; i++)
    	{
    		for(int j = 0; j < a2.length; j++)
    		{
    			for(int k = 0; k <a3.length; k++)
    			{
    				if((a1[i]*(y2-y3) + a2[j]*(y3-y1) + a3[k]*(y1-y2)) == 0)
    				{
    					count++;
    				}
    			}
    		}
    	}
    	
      return count;
    }

    // ----------------------------------------------------------
    /**
     * Counts for the number of non-hoizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinearFast(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * In this implementation you should make non-trivial use of InsertionSort and Binary Search.
     * The performance of this method should be much better than that of the above method.
     *
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: 
     *  T(N) = big pheta (N^2)
     *
     *  Explanation: TODO
     *	This is because:
     *	-lines 1-5 are basic arithmetic and assignning values so take up big pheta (1)
     *	-the inserion sort method takes up big pheta (N^2) (see inserion sort explination)
     *	-the 1 st for loop is iterated N times, N being the lenght of array a1, big pheta (N)
     *	-the 2nd for loop is implemented N^2 times, big pheta (N^2)
     *	-within the second for loop we see basic arithmetic again, big pheta(1)
     *	-the binary search then takes up big pheta(logN) (see binary sort explination)
     *	-the count instruction takes up pheta(1)
     *	all this added up gives:
     *	big pheta(1) + big pheta(N^2) + big pheta(N) + big pheta(N^2) + big pheta(1) + big pheta(logN) + big pheta(1)
     *   the principles of addition tell us that we should keep only the highest order value, which is N^2
     *   Therefore T(N) = big pheta (N^2)
     *
     *
     */
    static int countCollinearFast(int[] a1, int[] a2, int[] a3)
    {
      //TODO: implement this method
      //get a sum of first 2 and then search array 3 for the missing number
    	//x1*(y2−y3) + x2*(y3−y1) + x3*(y1−y2)=0 if collinear
    	//isolate x3   
    	// x3 = (x1*(y2-y3) + x2*(y3-y1))/(y1-y2)
    	
    	int count = 0;
    	int y1 = 1;
    	int y2 = 2;
    	int y3 = 3;
    	int x3;
    	
    	sort(a3);
    	 
    	for (int i = 0; i < a1.length; i++)
    	{
    		for (int j = 0; j < a2.length; j++)
    		{
    			x3 = (a1[i]*(y2-y3) + a2[j]*(y3-y1))/(y1-y2);
    			if (binarySearch(a3, x3) == true)
    			{
    				count++;
    			}
    		}
    	}
      return count;
    }

    // ----------------------------------------------------------
    /**
     * Sorts an array of integers according to InsertionSort.
     * This method is static, thus it can be called as Collinear.sort(a)
     * @param a: An UNSORTED array of integers. 
     * @return after the method returns, the array must be in ascending sorted order.
     *
     * ----------------------------------------------------------
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: N^2
     *
     *  Explanation: Two linear for-loops.
     *
     */
    static void sort(int[] a)
    {
    	if (a != null && (a.length != 1) )
    	{
    		for (int j = 1; j<a.length; j++)
    		{
    			int i = j - 1;
    			while(i>=0 && a[i]>a[i+1])
    			{
    				int temp = a[i];
    				a[i] = a[i+1];
    				a[i+1] = temp;
    				i--;
                }
    		}
    	}
    }

    // ----------------------------------------------------------
    /**
     * Searches for an integer inside an array of integers.
     * This method is static, thus it can be called as Collinear.binarySearch(a,x)
     * @param a: A array of integers SORTED in ascending order.
     * @param x: An integer.
     * @return true if 'x' is contained in 'a'; false otherwise.
     *
     * ----------------------------------------------------------
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Caclulate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: TODO
     *  T(N) = big pheta(logN)
     *
     *  Explanation: 
     *  The declaration and assignment of variables in the first two lines takes big pheta(1) time
     *  The while loop is iterated log(N) times as each time the array gets cut in half, so this takes up big pheta(logN)
     *  The inside of the while loop contains code that will take big pheta(1) 
     *  The final line return will also take big pheta(1) to run
     *  Adding this all up gives us:
     *  big pheta(1) + big pheta(logN) + big pheta(1) + big pheta(1)
     *  the principles of addition tell us that we should keep only the highest order value, which is logN
     *  Therefore the order of growht is T(N) = big pheta(logN)
     *
     */
    static boolean binarySearch(int[] a, int x)
    {
      //TODO: implement this method
    	if (a != null && a.length != 1)
    	{
    		int lo = 0;
    		int hi = a.length-1;
    		while (lo <= hi)
    		{
    			int mid = lo + (hi - lo) / 2;
    			if (x < a[mid])
    			{
    				hi = mid - 1;
    			}
    			else if (x > a[mid])
    			{
    				lo = mid + 1;
    			}
    			else return true;
    		}
    	}
		return false;
    }
	}