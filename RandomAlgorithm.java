
/**
 * This is an implementation of one of the paging algorithms
 * 
 *
 */
public class RandomAlgorithm
{
    int[] pages;
	int[] resSet;
	Boolean inResSet;
	int numInRes;
	int pageFaults;
	public RandomAlgorithm(shortProgram len, int resSize)
	{
		pages=len.getArray();
		resSet=new int[resSize];
		inResSet=false;
		numInRes=0;
		genStringRan();
		pageFaults=0;
	}
	private void genStringRan()
	{
		for(int i=0;i<pages.length; i++)
		{
			int inty=(int) Math.round(Math.random()*99);
			pages[i]= inty;
		}
	}
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
	private void implementRandomAlgorithm(int s)
	{
		int rando= (int)Math.round(Math.random()*resSet.length);
		if(rando==resSet.length)
		{
			rando--;
		}
		resSet[rando]=s;
	}
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
