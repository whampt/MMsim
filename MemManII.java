/**
 * 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Smaug
 *
 */
public class MemManII 
{

    /**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int setSize = 20; 
		shortProgram program = new shortProgram();
		shortProgram p1 = new shortProgram((byte)(100));
		p1.shuffle(); 
		shortProgram p2 = new shortProgram(true);
		
		File f=new File("texty1.txt");
		FileWriter fw1=new FileWriter(f.getAbsoluteFile());			//The horde of files and file writers is upon us!!!!!!!!!!
		BufferedWriter bw1=new BufferedWriter(new FileWriter("texty1.txt"));
		
		FileWriter fw2=new FileWriter("texty2.txt");
		BufferedWriter bw2=new BufferedWriter(fw2);
		
		FileWriter fw3=new FileWriter("texty3.txt");
		BufferedWriter bw3=new BufferedWriter(fw3);
		
		FileWriter fw4=new FileWriter("texty4.txt");
		BufferedWriter bw4=new BufferedWriter(fw4);

		FileWriter fw5=new FileWriter("texty5.txt");
		BufferedWriter bw5=new BufferedWriter(fw5);
		
		FileWriter fw6=new FileWriter("texty6.txt");
		BufferedWriter bw6=new BufferedWriter(fw6);
		
		FileWriter fw7=new FileWriter("texty7.txt");
		BufferedWriter bw7=new BufferedWriter(fw7);
		
		FileWriter fw8=new FileWriter("texty8.txt");
		BufferedWriter bw8=new BufferedWriter(fw8);
		
		FileWriter fw9=new FileWriter("texty9.txt");
		BufferedWriter bw9=new BufferedWriter(fw9);
		
		
		for(int i=0; i<7;i++)//Modify the upper bound please.
		{
			int pFO = Optimal(program, setSize);
			bw1.write(new Integer(pFO).toString());
			int pFO0 = Optimal(p1, setSize);
			bw2.write(new Integer(pFO0).toString());
			int pFO1 =Optimal(p2, setSize);
			bw3.write(new Integer(pFO1).toString());
			//--------------------------------------------------------
			//--------------------------------------------------------
			//--------------------------------------------------------
			int lruPF = Optimal(program, setSize);	//--------
			bw4.write(new Integer(lruPF).toString());
			int lruPF0 = Optimal(p1, setSize);		//--------
			bw5.write(new Integer(lruPF0).toString());
			int lruPF1 =Optimal(p2, setSize);		//--------
			bw6.write(new Integer(lruPF1).toString());
			//--------------------------------------------------------
			//--------------------------------------------------------
			//--------------------------------------------------------
			RandomAlgorithm algo=new RandomAlgorithm(program,setSize);
			int rPF=algo.simulateDemandPagingRandomAlgorithm();
			bw7.write(new Integer(rPF).toString());
			RandomAlgorithm algo0 = new RandomAlgorithm(program,setSize);
			int rPF0 = algo0.simulateDemandPagingRandomAlgorithm();
			bw8.write(new Integer(rPF0).toString());
			RandomAlgorithm algo1 =new RandomAlgorithm(program,setSize);
			int rPF1 = algo1.simulateDemandPagingRandomAlgorithm();
			bw9.write(new Integer(rPF1).toString());
			bw1.newLine();
			bw2.newLine();
			bw3.newLine();
			bw4.newLine();
			bw5.newLine();
			bw6.newLine();
			bw7.newLine();
			bw8.newLine();
			bw9.newLine();
		}
		bw1.close();
		bw2.close();
		bw3.close();
		bw4.close();
		bw5.close();
		bw6.close();
		bw7.close();
		bw8.close();
		bw9.close();
		
		
		/**
		 * To implement optimal when workSet is full, make a copy of workSet called elimSet. compare each int i
		 * in elimSet to i+1, i+2, ... , i+n-1, i+n. If an element in elimSet is equal to an int in the upcoming
		 * page, remove that element from elimSet. Continue till elimSet has one element, or pages array has 
		 * been gone through. At that point, remove from workSet an element in elimSet.
		 */
		

	}
	public static int Optimal(shortProgram program, int resSetMaxSize )
	{
		int pageFaults =0;
		Set<Integer> resSet = new HashSet<Integer>(resSetMaxSize, (float)2.0);
		Set<Integer> elimSet = new HashSet<Integer>(resSetMaxSize, (float)2.0);	
		
		program.shuffle();
		program.shuffle();
		//program.show();
		/**
		 * outer loop in which we go through the pages required by our 'program'
		 * y is a counter for how many slots are filled in resident set
		 */
		int resSetCurrSize = 0;
		for(int i=0; i< program.size(); i++)
		{ 
			  
			if ( !resSet.contains(program.getIndex(i)))
			{
				pageFaults++;

				if (resSetCurrSize <= resSetMaxSize)
				{
					resSet.add(program.getIndex(i));
					resSetCurrSize++;
				}
				else // a page needs to be replaced
				{
					Iterator<Integer> iter = resSet.iterator();

					while (iter.hasNext())	
						elimSet.add(iter.next());
					
					int j=i+1;	
					
					while(j < program.size() && elimSet.size()>1)
					{
						if(elimSet.contains(program.getIndex(j)))
							elimSet.remove(program.getIndex(j));
						j++;					
					}
					
					iter = elimSet.iterator();
					resSet.remove(iter.next());
					elimSet.clear();
					resSet.add(program.getIndex(i));
				}
			}	
		}
		return pageFaults;	
	}
	private static int lru(shortProgram program, int setSize) // EXTREMELY HIGH PAGE FAULTS!!
	
	{
		PriorityQueue<Page> resSet = new PriorityQueue<Page>();
		int pages=0;
		boolean flag;
		for(int i=0; i< program.size(); i++){
			flag=false;
			for(Page p : resSet){
				if(p.equals(i)) {
					p.access();
					flag=true;
					break;
				}
			}
			if(!flag){
				if(resSet.size()>=setSize){
					//take out the least recently used page and put a new one in
					resSet.poll();
					pages++;
				}
				Page p = new Page(i);
				resSet.add(p);
			}
		}
		return pages;
	}
}