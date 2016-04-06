
public class shortProgram 
{

	private int[] refString;

	public shortProgram(int size)
	{
		refString = new int[size];
	}
	public shortProgram() 
	{
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

	public int getIndex(int i)
	{

		return refString[i];
	}
	public int size()
	{
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