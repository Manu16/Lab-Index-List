package arrayIndexList;



import indexList.IndexList;
import java.lang.reflect.*;

public class ArrayIndexList<E> implements IndexList<E> {
	private static final int INITCAP = 1; 
	private static final int CAPTOAR = 1; 
	private static final int MAXEMPTYPOS = 2; 
	private E[] element; 
	private int size; 

	public ArrayIndexList() { 
		element = (E[]) new Object[INITCAP]; 
		size = 0; 
	} 
	

	public void add(int index, E e) throws IndexOutOfBoundsException 
	{
		// ADD CODE AS REQUESTED BY EXERCISES
		if(index<0||index>size) //Checks if index is valid. Index can not be a negative or greater than the size.
		{
			throw new IndexOutOfBoundsException("Invalid index: "+index);
		}
		if (size == element.length) //Checks if the size of the array extends the length.
		{
			this.changeCapacity(CAPTOAR);
		}
		this.moveDataOnePositionTR(index, size-1); 
		element[index]= e;
		size++;
	}


	public void add(E e) 
	{
		// ADD CODE AS REQUESTED BY EXERCISES
		if (size==element.length) 
		{
			this.changeCapacity(CAPTOAR); 
		}
		element[size]=e; 
		size++; 
	}


	public E get(int index) throws IndexOutOfBoundsException 
	{
		// ADD AND MODIGY CODE AS REQUESTED BY EXERCISES
		if(index<0||index>size-1) //Checks if index is valid. Index can not be a negative or greater than the size.
		{
			throw new IndexOutOfBoundsException("Invalid index: "+index);
		}
		return element[index]; 
	}


	public boolean isEmpty() 
	{
		return size == 0;
	}


	public E remove(int index) throws IndexOutOfBoundsException 
	{
		// ADD AND MODIFY CODE AS REQUESTED BY EXERCISES
		if(index<0||index>size-1) //Checks if index is valid. Index can not be a negative, greater than the size or also it can not be 0.
		{
			throw new IndexOutOfBoundsException("Invalid index: "+index);
		}
		E oldReference1 = element[index]; 
		this.moveDataOnePositionTL(index+1, size-1);
		size--;		
		return oldReference1; //Reference to the element that was replaced
	}


	public E set(int index, E e) throws IndexOutOfBoundsException 
	{
		// ADD AND MODIFY CODE AS REQUESTED BY EXERCISES
		if(index<0||index>size) //Checks if index is valid. Index can not be a negative or greater than the size.
		{
			throw new IndexOutOfBoundsException("Invalid index: "+index);
		}
		E oldReference2 = element[index];
		element[index]=e;
		return oldReference2; //Reference to the element that was replaced
	}


	public int size() 
	{
		return size;
	}	
	
	//Exercise # 2:
	
	public int capacity()
	{
		return element.length;
	}
	
	
	
	// private methods  -- YOU CAN NOT MODIFY ANY OF THE FOLLOWING
	// ... ANALYZE AND USE WHEN NEEDED
	
	// you should be able to decide when and how to use
	// following method.... BUT NEED TO USE THEM WHENEVER
	// NEEDED ---- THIS WILL BE TAKEN INTO CONSIDERATION WHEN GRADING
	
	private void changeCapacity(int change) { 
		int newCapacity = element.length + change; 
		E[] newElement = (E[]) new Object[newCapacity]; 
		for (int i=0; i<size; i++) { 
			newElement[i] = element[i]; 
			element[i] = null; 
		} 
		element = newElement; 
	}
	
	// useful when adding a new element with the add
	// with two parameters....
	private void moveDataOnePositionTR(int low, int sup) { 
		// pre: 0 <= low <= sup < (element.length - 1)
		for (int pos = sup; pos >= low; pos--)
			element[pos+1] = element[pos]; 
	}

	// useful when removing an element from the list...
	private void moveDataOnePositionTL(int low, int sup) { 
		// pre: 0 < low <= sup <= (element.length - 1)
		for (int pos = low; pos <= sup; pos++)
			element[pos-1] = element[pos]; 
	}

	
	public <T1> T1[] toArray(T1[] array) { 
	    if (array.length < this.size()) { 
	        // if arr.length < this.size, allocate a new array of the same 
	    	// type as arr (components of the new array are set to be of equal
	    	// runtime type as components of the original array arr) 
	    	// and big enough to hold all the elements in this set. For 
	    	// this, we need to use the Array class....
	    	
	    	array = (T1[]) Array.newInstance(array.getClass().getComponentType(), this.size());
	    } else if (array.length > this.size()) 
	    	// Set to null all those positions of arr that won't be used. 
	    	for (int j=this.size(); j< array.length; j++) 
	    		array[j] = null;
	    
	    int i = 0;
	    for (E e: element)
	    {
	    	if(i==array.length)
	    	{
	    		break;
	    	}
	    	array[i] = (T1) e;   // It is assumed E can be casted to T
	        i++;
	    }
	    return array;	
	}
	
	public Object[] toArray() { 
		Object[] array = new Object[this.size()]; 
	    int i = 0;
	    for (E e: element) 
	    {
	        array[i] = e;
	        i++;
	    }
	    return array;	

	}

}
