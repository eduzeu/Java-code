import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TreapTest {

    @Test
    //tostring method tester
    //this method will compare the expected string with the one that the program is outputing 
    public void testToString(){
    Treap<Integer> testString = new Treap<Integer>();

    testString.add(4, 19);
    assertEquals("(key=4, priority=19)\n null\n null\n", testString.toString());

    testString.add(2,31);
    assertEquals("(key=2, priority=31)\n null\n (key=4, priority=19)\n  null\n  null\n", testString.toString());

    testString.add(6,70);
		assertEquals(testString.toString(), "(key=6, priority=70)\n (key=2, priority=31)\n  null\n  (key=4, priority=19)\n   null\n   null\n null\n"); 
			 
    testString.add(1, 84);
		assertEquals("(key=1, priority=84)\n" + 
				" null\n" + 
				" (key=6, priority=70)\n" + 
				"  (key=2, priority=31)\n" + 
				"   null\n" + 
				"   (key=4, priority=19)\n" + 
				"    null\n" + 
				"    null\n" + 
				"  null\n", testString.toString());
    }

    //add method tester
    //it will add nodes and return true if they are succesfully added
    //it will return false if the nodes added are already part of the tree
    public void testAdd() {
        Treap<Integer> testadd = new Treap<Integer>();

        assertEquals(testadd.add(3,34), true);
        assertEquals(testadd.add(9,78), true);    
        assertEquals(testadd.add(9, 45), false); 
        assertEquals(testadd.add(5, 102), true);
        assertEquals(testadd.add(3, 89), false);
        assertEquals(testadd.add(null, 6), false);
        //assertTrue(null, null);
    }

    //find method tester. It will return true if the keys are succesfully found
    //if nodes that are not part of the treap are searched, it will return false
    public void testFind(){
        Treap<Integer> testfind = new Treap<Integer>();

        testfind.add(1,84);
        testfind.add(3,12);
        testfind.add(5,83);
        testfind.add(7,26);

        assertEquals(testfind.find(7),true);
        assertEquals(testfind.find(5), true);
        assertEquals(testfind.find(13), false);
        assertEquals(testfind.find(12), false);
        assertEquals(testfind.find(3), true);
        assertEquals(testfind.find(1), true); 
        assertEquals(testfind.find(40), false);
    }

    //delete method tester. It will return true if nodes are deleted succesfully 
    //return false if methods that never were added are deleted 
    public void testDelete(){
        Treap<Integer> testdelete = new Treap<Integer>();

        testdelete.add(6,70);
        testdelete.add(1, 56);
        testdelete.delete(1);
        testdelete.add(5, 56);
        assertEquals(testdelete.delete(1), true);
        assertEquals(testdelete.delete(2), false);
        assertEquals(testdelete.delete(6), true);
        assertEquals(testdelete.delete(11), false);
        assertEquals(testdelete.delete(5), true);

    }
}