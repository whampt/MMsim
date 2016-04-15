
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
	 * call to equal method checks if two pages equals 
	 */
	public boolean equals(int i){
		return pageNum==i;
	}
	
	public String toString() {
		String s = pageNum + " " + useCount;
		return s;
	}
	
}