import static org.junit.Assert.*;

import org.junit.Test;


public class MyTreeMapTest1 {

    //@Test
    public void TestPut() {
	MyTreeMap<Integer,Integer> theMap = new MyTreeMap<Integer,Integer>();
	theMap.put(22, 1);
	theMap.put(25, 2);
	theMap.put(14, 3);
	theMap.put(8, 4);
	theMap.put(17,6);
	theMap.put(15,7);
	theMap.put(19,6);
	theMap.put(22,3);
	int y= theMap.get(22);
	assertEquals(y, 3);
	System.out.print(theMap.height);
	System.out.print(theMap.toString());
    }
    
    @Test
    public void TestPut1() {
	MyTreeMap<String,Integer> theMap = new MyTreeMap<String,Integer>();
	theMap.put("a", 1);
	theMap.put("b", 2);
	theMap.put("c", 3);
	theMap.put("c", 4);
	theMap.put("d",6);
	theMap.put("e",7);
	theMap.put("f",6);
	theMap.put("a",3);
	int y= theMap.get("a");
	assertEquals(y, 3);
	//System.out.print(theMap.height);
	System.out.print(theMap.toString());
    }
    

}
