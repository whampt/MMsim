
import java.util.LinkedList;

public class LruStack 
{
	private LinkedList<Integer> list; 
	private int maxSize;
	public LruStack(int size)
	{
		maxSize = size;
		list = new LinkedList<Integer>();
	}
	public boolean push(Integer item) 
	{
		if(list.size() <= maxSize)
		{
			list.addFirst(item);
			return true;
		}
		return false;
	}
	public Integer pop() 
	{
		return list.removeFirst();
	}
	public Integer peek() 
	{
		return list.getFirst();
	}
	public int size() 
	{
		return list.size();
	}
	public boolean isEmpty() 
	{
		return list.isEmpty();
	}
	public void remove(int bye)
	{
		list.remove(bye);
	}
}
