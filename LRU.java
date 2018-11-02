/**
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;


/**
 * This is an implementation of one of the paging algorithms
 * 
 *
 */
public class LRU 
{

	/**
	 * @param args
	 * 
	 * lru method that accepts a list and a resident set size as parameters.
	 * It makes a priority queue of the resident set.
	 * If a page is in the resident set, it checks if it's equal with the new page, and if it is, it will increment its useCount and set flag to true
	 * If page is not flagged, increment pages.if resident set is greater than the set size,take out the least recently used, create a new page and put it in the resident set 
	 */
	public static int lru(shortProgram program, int setSize) 
	{
		PriorityQueue<Page> resSet = new PriorityQueue<Page>();
		int pageFaults=0;
		boolean flag;
		for(int i=0; i< program.size(); i++){
			flag=false;
			for(Page p : resSet){
				if(p.equals(program.getIndex(i))) {
					p.access();
					flag=true;
					break;
				}
			}
			if(!flag){
				pageFaults++;
				if(resSet.size()>=setSize){
					//take out the least recently used page and put a new one in
					resSet.poll();
					
				}
				Page p = new Page(program.getIndex(i));
				resSet.add(p);
			}
		}
		return pageFaults;
	}

}