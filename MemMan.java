/**
 * 
 */

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Smaug
 *
 */
public class MemMan 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Boolean b = new Boolean(false);
		Scanner in = new Scanner(System.in);
		int setSize = 0; 
		
		/**
		 * setSize is set by the user, and should normally be 8-15. 
		 * Some room is left for experimentation. 
		 */
		
		System.out.println("Please type the size of the working set:");
		try
		{
			setSize = in.nextInt();
			if(setSize > 4 && setSize < 21)
				b = true;
			else
				System.out.println("The size of the working set should be between 8-15");
		}catch(Exception e)
		{
			System.out.println("The size of the working set has to be an integer preferably between 8-15. Try again ");
		}
		
		System.out.println("Thanks");
		in.close();
		shortProgram program = new shortProgram();
		int pageFaults = Optimal(program, setSize);
		System.out.println("For program size: " + program.size() + ",  "+  pageFaults +" page faults occured with a resident set size of:  " +setSize);
		/**
		 * To implement optimal when workSet is full, make a copy of workSet called elimSet. compare each int i
		 * in elimSet to i+1, i+2, ... , i+n-1, i+n. If an element in elimSet is equal to an int in the upcoming
		 * page, remove that element from elimSet. Continue till elimSet has one element, or pages array has 
		 * been gone through. At that point, remove from workSet an element in elimSet.
		 */
		

	}
	public static int Optimal(shortProgram program, int res )
	{
		int pageFaults =0;
		Set<Integer> resSet = new HashSet<Integer>(res, (float)2.0);
		Set<Integer> elimSet = new HashSet<Integer>(res, (float)2.0);	
		
		program.shuffle();
		//program.show();
		/**
		 * outer loop in which we go through the pages required by our 'program'
		 * y is a counter for how many slots are filled in resident set
		 */
		int y = 0;
		for(int i=0; i< program.size(); i++)
		{ 
			  
			if ( !resSet.contains(program.getIndex(i)))
			{
				pageFaults++;

				if (y < res)
				{
					resSet.add(program.getIndex(i));
					y++;
				}
				else // a page needs to be replaced
				{
					Iterator<Integer> iter = resSet.iterator();

					while (iter.hasNext())
						elimSet.add(iter.next());

					int j=i+1;	
					while(j < program.size() && elimSet.size()>1)
					{
						if(elimSet.contains(program.getIndex(y)))
							elimSet.remove(program.getIndex(y));
						j++;					
					}
					iter = resSet.iterator();
					resSet.remove(iter.next());
					resSet.add(program.getIndex(i));
				}
			}	
		}
		return pageFaults;	
	}

}
