package collections_2021_2022;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Each instance of this interface stores a list of objects.
 * 
 * @invar | stream().allMatch(e -> e != null)
 */
public interface List {
	
	/**
	 * @inspects | this
	 * 
	 * @post | result != null
	 * 
	 * @creates | result
	 */
	public Object[] toArray();
	
	/**
	 * @inspects | this
	 * 
	 * @post | result == toArray().length
	 */
	public int size();
	
	/**
	 * @inspects | this
	 * 
	 * @pre | 0 <= index && index < size()
	 * @post | result == toArray()[index]
	 */
	public Object get(int index);
	
	/**
	 * @pre | object != null
	 * 
	 * @inspects | this
	 * 
	 * @post | result == IntStream.range(0, size())
	 *       |           .filter(i -> toArray()[i].equals(object))
	 *       |           .findFirst().orElse(-1)
	 */
	default int indexOf(Object object) {
		for (int i = 0; i < size; i++)
			if (get(i).equals(object))
				return i;
		return -1
	}
	
	public default Stream<Object> stream() { return Arrays.stream(toArray()); }
	
	/**
	 * @inspects | this
	 * 
	 * @pre | object != null
	 * @post | result == stream().anyMatch(e -> object.equals(e))
	 */
	public boolean contains(Object object);
	
	/**
	 * @pre | object != null
	 *  
	 * @mutates | this
	 * 
	 * @post | size() == old(size() + 1)
	 * @post | Arrays.equals(toArray(), 0, old(size()), old(toArray()), 0, old(size()))
	 * @post | toArray()[old(size())] == object
	 */
	default void add(Object object);
	
	/**
	 * @pre | 0 <= index && index < size()
	 * 
	 * @mutates | this
	 * 
	 * @post | size() == old(size()) - 1
	 * @post | Arrays.equals(toArray(), 0, index, old(toArray()), 0, index)
	 * @post | Arrays.equals(toArray(), index, size(), old(toArray()), index + 1, old(size()))
	 * @post | result == old(toArray()[index])
	 */
	public Object remove(int index);
	
	/**
	 * @pre | object != null
	 * 
	 * @post The new sequence of elements equals the old sequence of elements minus the first element that equals the given object.
	 *       | Arrays.equals(toArray(),
	 *       |     IntStream.range(0, old(size()))
	 *       |     .filter(i -> i != old(indexOf(object)))
	 *       |     .<Object>mapToObj(i -> old(toArray())[i]).toArray())
	 */
	public void remove(Object object);

}
