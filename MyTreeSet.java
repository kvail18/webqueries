import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;


public class MyTreeSet<T extends Comparable<? super T>> extends AbstractSet<T> {

    
    /** Backing storage for the set */
    private MyTreeMap<T, Boolean> set;
    
    // TODO - STUDENTS SHOULD DO THE FOLLOWING
    //
    // 1. Create a 0-argument constructor
    public MyTreeSet(){
    	MyTreeMap<T, Boolean> set1 = new MyTreeMap<T, Boolean>();
    	set=set1;
    }
    
    // 2. Override the add method
    
    public boolean add(T item){
    	if (set.get(item)==null){
    		set.put(item, true);
    		return true;
    	}
    	return false;
    }
    
    // 3. Override iterator()
    public Iterator<T> iterator(){
    	Iterator<T> It = set.keys();
    	return It;
    }
    
    // 4. Override size()
    public int size(){
    	return set.size();
    }
    
    // 5. Override clear()
    public void clear(){
    	MyTreeMap<T, Boolean> Cleared = new MyTreeMap<T, Boolean>();
    	set = Cleared;
    }
    	

}
