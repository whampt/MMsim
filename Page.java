
/**
 * This class represents a page of memory.
 * 
 *
 */
public class Page implements Comparable<Page>{
	int lastUsed;
	int pageNum;
	public static int useCount=0;
	
	public Page(int pageNum){
		this.pageNum=pageNum;
		this.lastUsed=useCount++;
	}
	
	/**
	 *access method call increments the lastUsed count  
	 */
	public void access(){
		this.lastUsed=useCount++;
	}
	
	/**
	 * compare the lru last used page count.
	 * TODO: get around to changing this to a boolean method
	 * 
	 */
	@Override
	public int compareTo(Page other) {
		if (this.lastUsed < other.lastUsed) {
			return -1;
		} else if (this.lastUsed > other.lastUsed) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * When you call the equals method, it returns
	 * a boolean that tells you if an index is equal
	 * to the current Page's index.
	 */
	public boolean equals(int i){
		return pageNum==i;
	}
	
	public String toString() {
		String s = pageNum + " " + useCount;
		return s;
	}
	
}