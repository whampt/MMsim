
/**
 * This is an implementation of one of the paging algorithms
 * TODO: Unlike the other two classes that implement an algorithm, this class has a constructor.  While this may be the best way to implement the algorithm, its differences are such that I must wonder if they are to great to be rectified without some noteworthy edits.  An MMSimAlgorithm abstract superclass that each algorithm must be a subtype of may make things more uniform, as well as make this code easier to improve in the future.
 *
 */
public class RandomAlgorithm
{
    int[] pages;
	int[] resSet;
	Boolean inResSet;
	int numInRes;
	int pageFaults;
	/**
	 * This is the constructor for the class.
	 * @param len
	 * @param resSize
	 */
	public RandomAlgorithm(shortProgram len, int resSize)
	{
		pages=len.getArray();
		resSet=new int[resSize];
		inResSet=false;
		numInRes=0;
		genStringRan();
		pageFaults=0;
	}
	/**
	 * This method generates random numbers for your page array.
	 */
	private void genStringRan()
	{
		for(int i=0;i<pages.length; i++)
		{
			int inty=(int) Math.round(Math.random()*99);
			pages[i]= inty;
		}
	}
	/**
	 * This method simulates paging.
	 * @return
	 */
	public int simulateDemandPagingRandomAlgorithm()
	{
		for(int i=0;i<pages.length;i++)
		{
			inResSet=false;			//Figure out what the deal is when we have this line commented out.
			checkIfInRes(pages[i]); //I know something bad should happen, but I don't understand why it's this.

			if(inResSet==true){continue;}

			if(numInRes<resSet.length)
			{resSet[numInRes]=pages[i];
			 numInRes++;
			}

			else
			{
				implementRandomAlgorithm(pages[i]);
			}
			
			pageFaults++;
		}
		return pageFaults;
	}
	/**
	 * This method checks if a page is in the resSet.
	 * @param s
	 */
	private void checkIfInRes(int s)
	{
		int i;
		for(i=0;i<resSet.length; i++)
		{
			if(resSet[i]==s)
			{
				inResSet=true;
				break;
			}
		}
		
	}
	/**
	 * Implements the random algorithm.
	 * @param s
	 */
	private void implementRandomAlgorithm(int s)
	{
		int rando= (int)Math.round(Math.random()*resSet.length);
		if(rando==resSet.length)
		{
			rando--;
		}
		resSet[rando]=s;
	}
	
	
	/**
	 * This method returns the number of page faults.
	 * @return
	 */
	public int getNumPageFaults()
	{
		return pageFaults;
	}
}


//last statement in forloop of genStringRan
//System.out.println(pages[i]);



//First line after the for loop was declared in checkIfInRes
//System.out.println("_"+resSet[i]+"_"+s);


//Found in checkIfInRes after the end of the code before the closing bracket
//if(numInRes<resSet.length)//have to deal with null values
//{
//	if(done==true)
//	{
//		inResSet=false;
//	}
//}
//System.out.println("Greetings");



//public static Boolean checkForNullInRes()
//{
//	for(int i=0; i<resSet.length;i++)
//	{
//		if(resSet[i]=="null")
//		{
//			return true;
//		}
//	}
//	return false;
//}
