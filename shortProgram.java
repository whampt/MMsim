
public class shortProgram {

	private int[] refString;
	private int page;

	// Creates a "good" program of a set size. Use for testing algorithms
	public shortProgram(int size) {
		refString = new int[size];

		page = 0;
		for (int i = 0; i < size; i++) {

			double random = Math.random();
			if (random < 0.9) {
				page = (int) (random * 10);
				refString[i] = page;
			}
			
			else {
				page = (int) (Math.random() * 90) + 10;
				refString[i] = page;
			}

			// Uses locality to state that the next page will occur in a 
			// good program occasianally.
			while (Math.random() < 0.1) {
				i++;
				page++;
				refString[i] = page;
			}
		}
	}

	// Will's intial program constructor. Will be kept for now
	public shortProgram() {
		refString = new int[300];
		
		for(int i = 0; i< 243; i++)
			refString[i] = 1;

		for(int i = 243; i<257; i++)
			refString[i] = 0;

		for(int i=257; i<270;i++)
			refString[i] = 2;

		int y = 3;
		for(int i=270; i<297; i++)
		{
			refString[i] = y++;	
		}
		y = 3; 
		for(int i=297; i<300; i++)
		{
			refString[i] = y++;
		}
	}

	// Generates a "good" or "random" program based on boolean.  If true, create good. If false,
	// create a random string
	public shortProgram(boolean type) {

		refString = new int[10000];

		if (type == true) {

			page = 0;
			for (int i = 0; i < 10000; i++) {

				double random = Math.random();
				if (random < 0.9) {
					page = (int) (random * 10);
					refString[i] = page;
					}
			
				else {
					page = (int) (Math.random() * 90) + 10;
					refString[i] = page;
				}

				// Uses locality to state that the next page will occur in a 
				// good program occasianally.
				while (Math.random() < 0.1) {
					i++;
					page++;
					refString[i] = page;
				}
			}
		}

		else {

	    	page = 0;
	    	for (int i = 0; i < 10000; i++) {

	    		double random = Math.random();
	    		page = (int) (random * 100);
	    		refString[i] = page;
	    	}
		}
	}

	/** Byte is a 8-bit signed integer, ranging from -127 to 128. Send this constructor 100 or it wont be happy.
	*   Either a failure with ArrayOutOfBounds or an failure later during paging with null returns from array.
	*   This is a statically assigned string with pages 0-9 being used 90% of the time, split evenly among 
	*   the pages for 9% occurrence each(810 each). The remaining 90 pages called 1000 times have the 90/10 rule applied 
	*   again. Nine pages are called 900 times for 1% occurrence each. The remaining 81 pages are called only 100 times total.
	*   We could further the 90/10 rule here, and some of the pages from 0-99 pages will not occur at all.
	*   We will have all 0-99 pages appear, but will simulate this behavior by giving 10% of the (5)remaining 
	*   81 pages three occurrences and 3 numbers 2 occurrences. 
	*    
	*   Aha! Come to think of it, with a random spread , how will we know how many pages we have ?  
	*/
	public shortProgram(byte pages) {
		refString = new int[pages*100];
		int page = 0;
		for(int i = 0; i< 8999; i++)
		{ 	if(page < 10)
				refString[i] = page;
			else
				refString[i] = (page = 0);
			page++;
		}
		page = 10;
		for(int i = 8999; i<9899; i++)
		{		if(page < 19)
				refString[i] = page;
			else
				refString[i] = (page = 10);
			page++;
		}
		page = 19;
		for(int i = 9899; i < 9980;i++)
		{	if(page < 100)
				refString[i] = page;
			else
				refString[i] = (page = 19);
			page++;			
		}
			page = 19;
			{	for(int i = 9980; i < 9999;i++)
				if(page < 24)
					refString[i] = page;
				else
					refString[i] = (page = 19);
				page++;	
			}
	}

	public int getIndex(int i) {

		return refString[i];
	}

	public int size() {
		return refString.length;
	}

	 // swaps array elements i and j
    public void exch(int i, int j) {
        int swap = refString[i];
        refString[i] = refString[j];
        refString[j] = swap;
    }

    // take as input an array of strings and rearrange them in random order
    public void shuffle() {
        int N = refString.length;
        for (int i = 0; i < N; i++) {
            int r = i + (int) (Math.random() * (N-i));   // between i and N-1
            exch(i, r);
        }
    }

    // take as input an array of strings and print them out to standard output
    public void show() {
        for (int i = 0; i < refString.length; i++) {
            System.out.println(refString[i]);
        }
    }
}