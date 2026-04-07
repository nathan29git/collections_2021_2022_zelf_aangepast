package collections_2021_2022;

import java.util.Arrays;
import java.util.stream.IntStream;

//Dit heb ik zelf gemaakt in het kader van oef HC10bis_RingBuffer


 // Deque staat voor Double Ended Queue; je kan aan beide uiteinden elementen toevoegen en/of verwijderen
public class ArrayDeque implements List {
	
	
	/**
	 * @invar | elements != null
	 * @invar | 0 <= size
	 * @invar | size <= elements.length
	 * @invar | 0 <= start
	 * @invar | start < elements.length
	 * @invar | IntStream.range(0, elements.length).allMatch(i -> (elements[(start + i) % elements.length] == null) == (i >= size))
	 * @representationObject
	 */
	private Object[] elements;
	private int size;
	private int start; //dat is het grote verschil met ArrayList

	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		for (int i = 0; i < size ; i++)
			result[i] = elements[(start + i) % elements.length];
		return result;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public Object get(int index) {
		return elements[(start + index) % elements.length];
	}
	
	/**
	 * @post | size() == 0
	 */
	public ArrayDeque() {
		elements = new Object[10];
	}
	
	
	public void add(int index, Object element) {
		if (size == elements.length) {
			elements = Arrays.copyOf(toArray(), size * 2); // maal twee om een gemiddelde cte tijd van toevoeging te behouden
			start = 0;
		}
		
		if (index < size/2) {
			start = (start + elements.length -1)%elements.length; //zodat het nooit kleiner dan 0 is
			for (int i = 0; i < index; i++)
				elements[(start + i) % elements.length] =
					elements[(start + i + 1) % elements.length];
			
			
		}
		else {
			for (int i = index; index < i; i --)
				elements[(start + i)%elements.length] = elements[(start + i - 1 )%elements.length];
			
		}
		elements[(start + index) % elements.length]= element;
		size++;
	}
	
	
	public void set(int index, Object element) {
		elements[(start + index)%elements.length] = element;
	}
	
	@Override
	public void remove(int index) {
		if (index < size / 2) {
			for (int i = index; 0 < i ; i--) //van R naar L 
				elements[(start + i)%elements.length] = elements[(start+i-1)%elements.length];
			start = (start + 1)%elements.length;
		}
		else {
			for (int i = index; i < size ; i++) //van L naar R 
				elements[(start + i)%elements.length] = elements[(start+i+1)%elements.length];
			
		}
		size--;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
