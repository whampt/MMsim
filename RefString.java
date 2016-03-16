public class RefString {

    private static int[] ref;
	private static int page;

	public static int[] createGoodReferenceString() {

		ref = new int[100];
		page = 0;
		for (int i = 0; i < 100; i++) {

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

		return ref;
	}
    
    public static int[] createBadReferenceString() {

    	ref = new int[100];
	    page = 0;
	    for (int i = 0; i < 100; i++) {

	    	double random = Math.random();
	    	page = (int) (random * 100);
	    	ref[i] = page;
	    }

	    return ref;
	}
}