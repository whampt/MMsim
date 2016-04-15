

public class RandomAlgorithm
{
    static String[] pages;
	static String[] resSet;
	static Boolean inResSet;
	static int numInRes;
	static int pageFaults;
	public RandomAlgorithm(int len, int resSize)//int thinkoMachigco
	{
		pages=new String[len];
		resSet=new String[resSize];
		inResSet=false;
		numInRes=0;
		genStringRan();
		pageFaults=0;
		for(int i=0;i<resSet.length;i++)
		{
			resSet[i]=new String("null");
		}
	}
	private static void genStringRan()
	{
		for(int i=0;i<pages.length; i++)
		{
			int inty=(int) Math.round(Math.random()*99);
			String s=Integer.toString(inty);
			pages[i]=new String(s);
//			System.out.println(pages[i]);
		}
	}
	public static void simulateDemandPagingRandomAlgorithm()
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
	}
	private static void checkIfInRes(String s)
	{
		int i;
		for(i=0;i<resSet.length; i++)
		{
//			System.out.println("_"+resSet[i]+"_"+s);
			if(resSet[i].equals(s))
			{
				inResSet=true;
				break;
			}
		}
//		if(numInRes<resSet.length)//have to deal with null values
//		{
//			if(done==true)
//			{
//				inResSet=false;
//			}
//		}
//		System.out.println("Greetings");
		
	}
	private static void implementRandomAlgorithm(String s)
	{
		int rando= (int)Math.round(Math.random()*resSet.length);
		if(rando==resSet.length)
		{
			rando--;
		}
		resSet[rando]=s;
	}
//	public static Boolean checkForNullInRes()
//	{
//		for(int i=0; i<resSet.length;i++)
//		{
//			if(resSet[i]=="null")
//			{
//				return true;
//			}
//		}
//		return false;
//	}
	public int getNumPageFaults()
	{
		return pageFaults;
	}
}
