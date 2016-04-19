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


public class LRU 
{

	/**
	 * @param args
	 */


	/**
	 * lru method that accept a list and resident set size as parameter 
	 * make a priority queue of the resident set
	 * if a page is in the resident set, check if its equal with the new page, and if it is, increment its useCount and set flag to true
	 * if page is not flagged, increment pages.if resident set is greater than the set size,take out the least recently used, create a new page and put it in the resident set 
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