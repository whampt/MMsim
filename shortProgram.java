
public class shortProgram {

	public enum Type {
		GOOD, RANDOM
	}

	private int[] refString;
	private Type type;
	private int page;

	// Creates a "good" program of a set size. Use for testing algorithms
	public shortProgram(int size) {
		refString = new int[size];
		this.type = GOOD;

		page = 0;
		for (int i = 0; i < size; i++) {

			double random = Math.random();
			if (random < 0.9) {
				page = (int) (random * 10);
				ref[i] = page;
			}
			
			else {
				page = (int) (Math.random() * 90) + 10;
				ref[i] = page;
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

	// Generates a "good" or "random" program based on type parameter
	public shortProgram(Type type) {

		refString = new int[10000];
		this.type = type;

		switch (type) {

			case GOOD:

				page = 0;
					for (int i = 0; i < 10000; i++) {

						double random = Math.random();
						if (random < 0.9) {
							page = (int) (random * 10);
							ref[i] = page;
						}
			
						else {
							page = (int) (Math.random() * 90) + 10;
							ref[i] = page;
						}
					}
				break;

			case RANDOM:

	    		page = 0;
	    		for (int i = 0; i < 10000; i++) {

	    			double random = Math.random();
	    			page = (int) (random * 100);
	    			ref[i] = page;
	    		}
	    		break;

	    	default:

	    		System.out.println("Not a valid type of reference string");
	    		break;
		}
	}

	public int getIndex(int i) {

		return refString[i];
	}

	public int size() {
		return refString.length;
	}

	public Type getType() {
		return type;
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