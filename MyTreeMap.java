/**
 * A binary search tree using AVL balancing.  It uses a structure similar to
 * that of Lab 5 whereby special null trees are used to represent children that
 * aren't present.
 * 
 * @author John Donaldson (Fall 2007)
 * @author Benjamin Kuperman (Spring 2008, Spring 2011)
 * @author Alexa Sharp (Fall 2009)
 * @author Bob Geitz (Fall 2014)
 * @author TODO You!
 */

// STUDENTS need to implement the following methods to complete the class:
//  - get()
//  - put()
//  - part of restructure()
//  - optionally add in a remove method

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyTreeMap<K extends Comparable<? super K>,V> extends AbstractMap<K,V>  {

    /** The index by which information is stored */
    K key;
    /** Value associated with the key above */
    V value;
    /** The height of this subtree */
    int height;
    /** The children of this subtree -- if both null, this tree is empty */
    MyTreeMap<K,V> left,right;
    /** The number of nodes within this subtree (including self) */
    int size;

    // change this value to true and I'll print the rotations
    private boolean debug = false;

    //--------------------------------------------------------------------------
    // Things TODO - I've moved all the methods you need to change up to here
    //--------------------------------------------------------------------------

    // TODO - implement me! - and give me some comments
    private V get(K searchKey) {
	//base case, if it's not in the treemap
	if (isEmpty()){
	    return null;
	}
	//if you found it!!
	else if (searchKey.compareTo(this.key) == 0) {
	    return this.value;
	}

	//if its smaller you go down the left side of the tree
	else if (searchKey.compareTo(this.key) < 0) {
	    return left.get(searchKey);
	}

	//if its larger you go down the right side
	else {
	    return right.get(searchKey);
	}
    }

    // TODO - implement me too!
    public V put(K key, V value){
	if (isEmpty()) {
	    this.key=key;
	    this.value=value;
	    this.left= new MyTreeMap<K,V>();
	    this.right= new MyTreeMap<K,V>();
	    this.size=1;
	    this.height=0;
	    this.setSize();
	    return null;

	}	

	else if (key.compareTo(this.key) == 0) {
		System.out.print(value);
	    V tempValue= this.value;
	    this.value=value;
	    return tempValue;
	}
	else if (key.compareTo(this.key) < 0) {
		V temp = left.put(key, value);
	    int y= left.getHeight() - right.getHeight();
	    if (y<0){
	    	y= y*(-1);
	    }
	    if (y>1){
		    restructure(this);
	    }
	    this.setHeight();
	    this.setSize();
	    return temp;
	}
	else {
		V temp1= right.put(key, value);
	    int y= left.getHeight() - right.getHeight();
	    if (y<0){
	    	y= y*(-1);
	    }
	    if (y>1){
		    restructure(this);
	    }
	    this.setHeight();
	    this.setSize();
	    return temp1;
	}


	// insert node or update value

	// now check to see if I am unbalanced

	// fix my height

	// fix my size

	// return old value or null

    }


    public void setSize() {
	if (isEmpty()) 
	    this.size = 0;
	else
	    this.size = 1+left.size() + right.size();

    }



    /**
     * Rebalances an AVL internal node using the restructure algorithm from 
     * class. Suppose Z is the node that fails the height balance property,
     * and let Y be Z's tallest child, and X be Y's tallest child.
     * Given these labels, let a, b, and c be X,Y,Z in sorted order,
     * whatever that may be (so, if the tree looks like
     * 
     *                  Z
     *                /   \
     *               Y     o
     *              / \       
     *             o   X        
     *                / \       
     *               o   o
     * 
     * then the sorted order is Y,X,Z, and therefore a is Y, b is X,
     * and c is Z.)
     * Let t1, t2, t3, and t4 be the subtrees of X, Y, and Z from left to 
     * right, so the example above becomes
     *                  Z
     *                /   \
     *               Y     t4
     *              / \       
     *             t1  X        
     *                / \       
     *               t2   t3
     *
     * You will be setting these variables according to the definitions
     * above, and depending on which subtrees are causing the imbalance.
     * Once you've made these assignments, the provided code will return the
     * tree with the final arrangement of
     * 
     *                  b
     *                /   \
     *               a     c
     *              / \   / \
     *             t0 t1 t2 t3
     * 
     * @param node Internal node failing the height balance property
     */
    private void restructure(MyTreeMap<K, V> node) {
	// fill in these values such that
	//      a < b < c
	// and
	//      t0->t3 are the left to right trees
	MyTreeMap<K,V> a, b, c, t1, t2, t3, t4;
	

	if (node.left.height > node.right.height) {
	    // left side taller, right subtree is last
	    if (node.left.left.height > node.left.right.height) {
		// left->left
		if (debug) System.err.println("Single right rotation at: "+node.key);

		c = node;
		b = node.left;
		a = node.left.left;
		t1 = a.left;
		t2 = a.right;
		t3 = b.right;
		t4 = c.right;

	    } else {
		// left->right turn
		if (debug) System.err.println("Double right rotation at: "+node.key);

		// TODO - replace the following line DONE
		
		a=node.left;
		b=node.left.right;
		c=node;
		t1=a.left;
		t2=b.left;
		t3=b.right;
		t4=c.right;

	    }
	} else {
	    if (node.right.right.height > node.right.left.height) {
		// right->right
		if (debug) System.err.println("Single left rotation at: "+node.key);

		// TODO - replace the following line DONE
		a=node;
		b=node.right;
		c=node.right.right;
		t1=a.left;
		t2=b.left;
		t3=c.left;
		t4=c.right;

	    } else {
		// right->left turn
		if (debug) System.err.println("Double left rotation at: "+node.key);

		// TODO - replace the following line
		a=node;
		b=node.right.left;
		c=node.right;
		t1=a.left;
		t2=b.left;
		t3=b.right;
		t4=c.right;

	    }
	}


	// If you've done the above correctly, this should fix your current node
	MyTreeMap<K, V> newLeft = new MyTreeMap<K, V>(a.key,a.value,t1,t2);
	MyTreeMap<K, V> newRight = new MyTreeMap<K, V>(c.key,c.value,t3,t4);
	// now fix myself
	this.key = b.key;
	this.value = b.value;
	this.left = newLeft;
	this.right = newRight;
	setHeight();
    }

    // TODO - optional implementation
    public V remove(Object rkey){
	throw new UnsupportedOperationException("Not a required method: remove");
    }


    //--------------------------------------------------------------------------
    // ** NO CHANGES NEEDED BELOW - BUT YOU MIGHT WANT TO READ AND UNDERSTAND **
    //--------------------------------------------------------------------------


    //--------------------------------------------------------------------------
    // Constructors - outside users can only make an empty tree
    //--------------------------------------------------------------------------

    /**
     * Creates an empty tree.
     */
    public MyTreeMap(){
	this(null,null,null,null);
	this.size = 0;
    }

    /**
     * Build a tree from existing components.  To be used only within MyTreeMap
     * and subchildren.  
     * @param key Key to store at this location
     * @param value Value to store at this location (associated with Key)
     * @param left An existing subtree, all Key values should be smaller than key
     * @param right An existing subtree, all Key values should be greater than key
     */
    protected MyTreeMap(K key, V value, MyTreeMap<K, V> left, MyTreeMap<K, V> right) {
	super();
	this.key = key;
	this.value = value;
	this.left = left;
	this.right = right;
	// fix the height
	setHeight();
	// and the size
	this.size = 1;
	if (left!=null) this.size+=left.size;
	if (right!=null) this.size+=right.size;
    }

    /**
     * Used to construct a single leaf element.  Empty subtrees are put in place of
     * children to allow for recursive methods to not have to worry about null
     * pointers.
     * @param key Key to store at this location
     * @param value Value to store at this location (associated with Key)
     */
    protected MyTreeMap(K key, V value) {
	this(key,value,new MyTreeMap<K,V>(), new MyTreeMap<K,V>());
	this.size = 1;
    }

    /**
     * Copies the values from the root of another tree into this root.  Note 
     * that it does not clone those values, just duplicates the structure. 
     * @param other The root whose values are copied into this node
     * @return the current node with new values
     */
    private MyTreeMap<K,V> selfCopy(MyTreeMap<K,V> other) {
	this.key = other.key;
	this.value = other.value;
	this.left = other.left;
	this.right = other.right;
	this.height = other.height;
	return this;
    }

    /**
     * Determine if this is a placeholder subtree for an empty leaf branch.
     * @return true only if this is a placeholder node, false otherwise
     */
    public boolean isEmpty(){
	return left==null && right==null;
    } 

    /**
     * Determine if this is a leaf node (not an empty node).
     * @return true if this node is not a placeholder and has no children
     */
    public boolean isLeaf() {
	return !isEmpty() && left.isEmpty() && right.isEmpty();
    }

    // This is required for implementing AbstractMap
    public Set<Map.Entry<K,V>> entrySet(){

	return new AbstractSet<Map.Entry<K,V>>() {

	    public Iterator<Map.Entry<K, V>> iterator() {
		return MyTreeMap.this.entries();
	    }

	    @Override
	    public int size() {
		return MyTreeMap.this.size;
	    }
	};
    }

    // This is a wrapper method to meet the requirements of the interface
    @SuppressWarnings("unchecked")
    public V get(Object searchKey){
	// We're not allowed to check to see if searchKey is an instanceof K :-(
	try {    
	    K mykey = (K) searchKey;
	    return get(mykey);
	} catch (ClassCastException cce) {
	    return null;
	}
    }

    /**
     * Creates a StringBuffer (String takes too long) and then starts the
     * recursive traversal of nodes.
     * @return String representation of the (Key:Value) pairs
     */
    private String toStringHelper(){
	StringBuilder sbuf = new StringBuilder();
	toStringHelper(sbuf);
	return sbuf.toString();
    }

    /**
     * Adds (Key:Value) pairs inorder into the StringBuffer.
     * @param sbuf where to store the string version of the tree
     */
    private void toStringHelper(StringBuilder sbuf) {
	if(isEmpty())
	    return;
	else {
	    if(!left.isEmpty()) {
		left.toStringHelper(sbuf);
		sbuf.append(",");
	    }
	    sbuf.append( "("+key+":"+value+")" );
	    if(!right.isEmpty()) {
		sbuf.append(",");
		right.toStringHelper(sbuf);
	    }
	}
    }

    public String toString(){
	return "[" + toStringHelper() + "]";
    }

    // helper methods for trees
    private void setHeight() {
	if (isEmpty()) 
	    this.height = -1;
	else
	    this.height = 1+Math.max(left.getHeight(),right.getHeight());
    }

    public int getHeight() {
	return height;
    }

    public int size() {
	return this.size;
    }


    //--------------------------------------------------------------------------
    // Some iterators that you might find useful
    //--------------------------------------------------------------------------

    /**
     * Create an inorder iterator of the Keys in the tree.
     * @return inorder iterator of the Keys
     */
    public Iterator<K> keys() {
	return keys("in");
    }

    /**
     * Create an iterator over the Keys in the tree.  Will traverse the tree
     * inorder, preorder, or postorder depending upon the argument.
     * @param s string indicating what traversal to do.  Acceptable values
     *          are {"in", "inorder", "pre", "preorder", "post", "postorder"}
     * @return iterator over the Keys in the tree
     */
    public Iterator<K> keys(String s) {
	List<K> list = new LinkedList<K>();
	if (s.equalsIgnoreCase("in")||s.equalsIgnoreCase("inorder")) {
	    keysInOrder(list);
	} else if (s.equalsIgnoreCase("pre") || s.equalsIgnoreCase("preorder")) {
	    keysPreOrder(list);
	} else if (s.equalsIgnoreCase("post") || s.equalsIgnoreCase("postorder")) {
	    keysPostOrder(list);
	} else {
	    throw new IllegalArgumentException("Unknown traversal method: "+s);
	}
	return list.iterator();
    }

    private void keysInOrder(List<K> l) {
	if (isEmpty()) return;
	this.left.keysInOrder(l);
	l.add(this.key);
	this.right.keysInOrder(l);
    }

    private void keysPreOrder(List<K> l) {
	if (isEmpty()) return;
	l.add(this.key);
	this.left.keysPreOrder(l);
	this.right.keysPreOrder(l);
    }

    private void keysPostOrder(List<K> l) {
	if (isEmpty()) return;
	this.left.keysPostOrder(l);
	this.right.keysPostOrder(l);
	l.add(this.key);
    }


    //--------------------------------------------------------------------------

    public Iterator<Entry<K, V>> entries() {
	List<Entry<K,V>> list = new LinkedList<Entry<K,V>>();
	entriesInOrder(list);
	return list.iterator();
    }
    private void entriesInOrder(List<Entry<K, V>> list) {
	if (isEmpty()) return;
	this.left.entriesInOrder(list);
	list.add(new MyEntry(key,value));
	this.right.entriesInOrder(list);
    }

    // This is the class you get from the above "entries()" iterator
    class MyEntry implements Map.Entry<K,V>{
	private K key;
	private V value;
	public MyEntry(K key, V value) {
	    this.key=key; this.value=value;
	}
	public K getKey() {
	    return key;
	}

	public V getValue() {
	    return value;
	}

	public V setValue(V value) {
	    throw new UnsupportedOperationException();
	}
    }


    //--------------------------------------------------------------------------
    // Nice to have a main, isn't it?
    //--------------------------------------------------------------------------

    public static void main(String[] args) {
	MyTreeMap<String, Integer> map = new MyTreeMap<String, Integer>();

	int i = 0;
	for (String s: args) {
	    System.out.println("Adding: >"+s+"<");
	    map.put(s,i++);
	    //System.out.println(map.put(s, i++));
	    System.out.println("newheight = " + map.height);
	}

	System.out.println("Size is: " + map.size());

	System.out.println(map);

	System.out.println("Does it contain Apple? " + map.containsKey("Apple"));

	System.out.println("Keys\n----");
	for (String s: map.keySet()) {
	    System.out.println(s);
	}
	System.out.println("Values\n------");
	for (Integer s: map.values()) {
	    System.out.println(s);
	}
    }
}
