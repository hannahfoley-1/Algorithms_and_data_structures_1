import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.w3c.dom.Node;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  TODO
 */

@RunWith(JUnit4.class)
public class BSTTest
{
    @Test
    public void testBSTConstructor(){
        new BST<Integer, Integer>();
    }

    @Test
    public void testIsEmpty()
    {
        //test with empty BST
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking isEmpty() with an empty tree", true, bst.isEmpty());

        //test with non-empty BST
        bst.put(7, 7);
        bst.put(8, 8);
        bst.put(3, 3);
        bst.put(1, 1);
        bst.put(2, 2);
        bst.put(6, 6);
        bst.put(4, 4);
        bst.put(5, 5);
        assertEquals("Checking isEmpty() with a non empty tree", false, bst.isEmpty());
    }

    @Test
    public void testSize()
    {
        //test on empty tree
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Testing size() using an empty tree", 0, bst.size());

        //test on non empty tree
        bst.put(7, 7);
        bst.put(8, 8);
        bst.put(3, 3);
        bst.put(1, 1);
        bst.put(2, 2);
        bst.put(6, 6);
        bst.put(4, 4);
        bst.put(5, 5);
        assertEquals("Testing size() using a tree with 8 nodes", 8, bst.size());
    }

    @Test
    public void testContains()
    {
        //test on an empty tree
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Testing contains() asking for a key '1' using an empty tree", false, bst.contains(1));

        //test a key that is not contained in the tree
        bst.put(7, 7);
        bst.put(8, 8);
        bst.put(3, 3);
        bst.put(1, 1);
        bst.put(2, 2);
        bst.put(6, 6);
        bst.put(4, 4);
        bst.put(5, 5);
        assertEquals("Testing contains() asking for a key '9' which is not contained in the tree ", false, bst.contains(9));

        //test a key that is contained in the tree
        assertEquals("Testing contains() asking for a key '4' which is contained in the tree", true, bst.contains(4));
    }

    @Test
    public void testPut()
    {
        //here i tested put as a replacing method, because put to add is implictly tested in the other test classes
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(7, 7);
        bst.put(8, 8);
        bst.put(3, 3);
        bst.put(1, 1);
        bst.put(2, 2);
        bst.put(6, 6);
        bst.put(4, 4);
        bst.put(5, 5);
        //test putting a null value into the BST, which will result in the node being deleted
        bst.put(6, null);
        assertEquals("Checking put, putting a null value into the node, which should result in the node" +
                "being deleted", "(((()1(()2()))3(()4(()5())))7(()8()))", bst.printKeysInOrder() );
        assertEquals("Checking new value in the key is equal to the new value", null, bst.get(6));

        //test putting a value into root
        bst.put(7, 6);
        assertEquals("Checking put, putting the value of 6 into the root", "(((()1(()2()))3(()4(()5())))7(()8()))"
        , bst.printKeysInOrder());
        Integer result= 6;
        assertEquals("Checking new value in the key is equal to the new value", result, bst.get(7));

        //test putting value into root's right subtree
        bst.put(8, 23);
        assertEquals("Checking put, putting the value of 23 into the right leaf", "(((()1(()2()))3(()4(()5())))7(()8()))"
                , bst.printKeysInOrder());
        result = 23;
        assertEquals("Checking new value in the key is equal to the new value", result, bst.get(8));

        //test putting value into root's left subtree
        bst.put(1, 0);
        assertEquals("Checking put, putting the value of 0 into the left leaf", "(((()1(()2()))3(()4(()5())))7(()8()))" ,
                bst.printKeysInOrder());
        result = 0;
        assertEquals("Checking new value in the key is equal to the new value", result, bst.get(1));
    }

    @Test
    public void testSelect()
    {
        //test select with empty BST
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        Integer result = null;
        assertEquals("Test select with an empty BST", result, bst.select(7));

        //test select with int < 0
        bst.put(7, 7);
        bst.put(8, 8);
        bst.put(3, 3);
        bst.put(1, 1);
        assertEquals("Test select with an int < 0", result, bst.select(-8));
    }

    @Test
    public void testHeight(){
        //Test with a valid BST
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(7, 7);
        bst.put(8, 8);
        bst.put(3, 3);
        bst.put(1, 1);
        bst.put(2, 2);
        bst.put(6, 6);
        bst.put(4, 4);
        bst.put(5, 5);
        //answer should be 4
        assertEquals("Checking height of a BST with height 4 ", 4, bst.height());

        //Test with an empty BST should return -1
        BST<Integer, Integer> bst1 = new BST<Integer, Integer>();
        int height = bst1.height();
        assertEquals("Checking the height of an empty BST ", -1, bst1.height());

        //Test with a BST with only a root node, should return 0
        BST<Integer, Integer> bst2 = new BST<Integer, Integer>();
        bst2.put(7, 7);
        //height = bst2.height();
        assertEquals("Checking the height of a BST with 1 node ", 0, bst2.height());
    }

    @Test
    public void testMedian(){
        //test with an empty BST
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        Integer result = null;
        assertEquals("Checking median on empty BST, should return null", result, bst.median());

        //test with valid BST, with median in left subtree
        bst.put(7, 7);
        bst.put(8, 8);
        bst.put(3, 3);
        bst.put(1, 1);
        bst.put(2, 2);
        bst.put(6, 6);
        bst.put(4, 4);
        bst.put(5, 5);
        result = 4;
        assertEquals("Checking median", result, bst.median());

        //test with another valid BST with only three nodes
        BST<Integer, Integer> bst2 = new BST<Integer, Integer>();
        bst2.put(7, 7);
        bst2.put(8, 8);
        bst2.put(3, 3);
        result = 7;
        assertEquals("Checking median", result, bst2.median());

        //test with another valid BST, with median in right subtree
        BST<Integer, Integer> bst3 = new BST<Integer, Integer>();
        bst3.put(5, 5);
        bst3.put(4, 4);
        bst3.put(8, 8);
        bst3.put(7, 7);
        bst3.put(6, 6);
        result = 6;
        assertEquals("Checking median", result, bst3.median());


    }

    @Test
    public void testPrintKeysInOrder()
    {
        //Test with valid BST
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.put(7, 7);
        bst.put(8, 8);
        bst.put(3, 3);
        bst.put(1, 1);
        bst.put(2, 2);
        bst.put(6, 6);
        bst.put(4, 4);
        bst.put(5, 5);
        assertEquals("Checking printKeysInOrder",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))",
                bst.printKeysInOrder());

        //test with empty BSt
        BST<String, String> bst2 = new BST<String, String>();
        assertEquals("Checking printKeysInOrder using an empty tree, should give ()", "()", bst2.printKeysInOrder());

        //test with BST containing only 1 node
        bst2.put("Hello", "Hi");
        assertEquals("Checking printKeysInOrder using a tree with only 1 node, should give (()Hello())", "(()Hello())",
                bst2.printKeysInOrder());
    }

    /** <p>Test {@link BST#prettyPrintKeys()}.</p> */

    @Test
    public void testPrettyPrint() {
        //test with empty BST
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        assertEquals("Checking pretty printing of empty tree",
                "-null\n", bst.prettyPrintKeys());

        //  -7
        //   |-3
        //   | |-1
        //   | | |-null
        bst.put(7, 7);       //   | |  -2
        bst.put(8, 8);       //   | |   |-null
        bst.put(3, 3);       //   | |    -null
        bst.put(1, 1);       //   |  -6
        bst.put(2, 2);       //   |   |-4
        bst.put(6, 6);       //   |   | |-null
        bst.put(4, 4);       //   |   |  -5
        bst.put(5, 5);       //   |   |   |-null
        //   |   |    -null
        //   |    -null
        //    -8
        //     |-null
        //      -null

        String result =
                "-7\n" +
                        " |-3\n" +
                        " | |-1\n" +
                        " | | |-null\n" +
                        " | |  -2\n" +
                        " | |   |-null\n" +
                        " | |    -null\n" +
                        " |  -6\n" +
                        " |   |-4\n" +
                        " |   | |-null\n" +
                        " |   |  -5\n" +
                        " |   |   |-null\n" +
                        " |   |    -null\n" +
                        " |    -null\n" +
                        "  -8\n" +
                        "   |-null\n" +
                        "    -null\n";
        String asText = bst.prettyPrintKeys();
        assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());

    }

    /** <p>Test {@link BST#delete(Comparable)}.</p> */
    @Test
    public void testDelete() {
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
        bst.delete(1);
        assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

        bst.put(7, 7);   //        _7_
        bst.put(8, 8);   //      /     \
        bst.put(3, 3);   //    _3_      8
        bst.put(1, 1);   //  /     \
        bst.put(2, 2);   // 1       6
        bst.put(6, 6);   //  \     /
        bst.put(4, 4);   //   2   4
        bst.put(5, 5);   //        \
        //                                   5

        assertEquals("Checking order of constructed tree",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

        bst.delete(9);
        assertEquals("Deleting non-existent key",
                "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

        bst.delete(8);
        assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

        bst.delete(6);
        assertEquals("Deleting node with single child",
                "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

        bst.delete(3);
        assertEquals("Deleting node with two children",
                "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());

        bst.delete(1);
        assertEquals("Test delete where left child is null ", "((()2(()4(()5())))7())", bst.printKeysInOrder() );
    }

}
