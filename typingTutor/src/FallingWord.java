
public class FallingWord {
	private String word; // the word
	private int x; //position - width
	private int y; // postion - height
	private int maxY; //maximum height
	private int maxX; //maximum width
	private boolean dropped; //flag for if user does not manage to catch word in time
	
	private int fallingSpeed; //how fast this word is
	private static int maxWait=1000;
	private static int minWait=100;

	public static WordDictionary dict;
	
	FallingWord() { //constructor with defaults
		word="computer"; // a default - not used
		x=0;
		y=0;	
		maxY=400;
		maxX=300;   
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	}
	
	FallingWord(String text) { 
		this();
		this.word=text;
	}

	
	/*
	 * Creates a new FallingWord. If the new word is a HungryWord the x-value is set to zero
	 * and the y-vlaue is height/2 of screen
	 */
	FallingWord(String text,int x,int Y, int maxY, int maxX,boolean isHungryWord) { //most commonly used constructor - sets it all.
		this(text);
		if(isHungryWord){this.y = Y/2; this.x=0;}
		else{this.x=x;} //only need to set x, word is at top of screen at start

		this.maxY=maxY;
		this.maxX=maxX;
	}

	
	/*
	 * Increase speed of FallingWords
	 */
	public static void increaseSpeed( ) {
		minWait+=50;
		maxWait+=50;
	}
	
	/*
	 * Resets teh speed to default speed
	 */
	public static void resetSpeed( ) {
		maxWait=1000;
		minWait=100;
	}
	

// all getters and setters must be synchronized
	public synchronized  void setY(int y) {
		if (y>maxY) {
			y=maxY;
			dropped=true; //user did not manage to catch this word
		}
		this.y=y;
	}
	
	/*
	 * Modified method. If the x value goes beyond max, the word is set to dropped,
	 * else just set the x-value
	 */
	public synchronized  void setX(int x) {
		if (x>maxX) {
			x=maxX;
			dropped=true; //user did not manage to catch this word
		}
		this.x=x;
	}
	
	/* 
	 * Sets the value of a FallingWord
	*/
	public synchronized  void setWord(String text) {
		this.word=text;
	}

	public synchronized  String getWord() {
		return word;
	}
	

	/* 
	 * Returns the the x-value of a FallingWord
	*/
	public synchronized  int getX() {
		return x;
	}	
	
	/* 
	 * Returns the the y-value of a FallingWord
	*/
	public synchronized  int getY() {
		return y;
	}
	
	/*
	 * Returns the speed of the FallingWord
	 */
	public synchronized  int getSpeed() {
		return fallingSpeed;
	}

	/*
	 * Resets both x and y values of a FallingWord
	 */
	public void setPos(int x, int y) {
		setY(y);
		setX(x);
	}

	/*
	 * Resets the y-value of a FallingWord
	 */
	public synchronized void resetPos() {
		setY(0);
	}

	/*
	 * HungryWord Method. Resets the x-value of the HungryWord
	 * Resets the position of the HungryWord, by resetting the x-value
	 */
	public synchronized void resetHungryPos() {
		setX(0);
	}

	/*
	 * Resets the value and x-coordinate of the HungryWord
	 * Resets the position and value of HungryWord
	 */
	public synchronized void resetHungryWord(){
		resetHungryPos();
		word=dict.getNewHungryWord();
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait);
		
	}

	/*
	 * Get the distance of the of the longest word
	 * Assumption is that each letter in a word takes approximately 4 spaces.
	 * So the length of the longest word is multiplied by 4.
	 */
	public synchronized int getLongLength(FallingWord w){
		if ((w.getWord()).length() >= (this.word).length()){
			return ((w.getWord()).length() *4);
		}
		return ((this.word).length() *4);
	}

	/*
	 * Checks if two FallingWords are colliding, by checking if the distance
	 * between the words is below the length of the longest word
	 */
	public synchronized boolean collide(FallingWord w){
		int LongLength = getLongLength(w);  //get Longest Distance
		double checkX = Math.pow((double)this.getX()-w.getX(),2);  // (x1-x2)^2
		double checkY = Math.pow((double)this.getY()-w.getY(),2);  //(y1-y2)^2
		int distance = (int)Math.pow(checkX+checkY, 0.5); ///get distance sqrt(x^2 + y^2)
		 //for checking if distance is calculated
		// System.out.println(this.word); 
		// System.out.println("(x,y): "+this.getX()+","+this.getY());
		// System.out.println("H(x,y): "+w.getX()+","+w.getY());
		// System.out.println("dist: "+distance);
		if ( distance<=LongLength){return true;}
		return false;
	}

	/*
	 * Resets the position and value of a FallingWord
	 */
	public synchronized void resetWord() {
		resetPos();
		word=dict.getNewWord();
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	
	}
	
	/*
	 * Modified Method. If the matched Word is a HungryWord, 
	 * then reset the HungryWord, else check if word matches with a 
	 * FallingWord
	 */
	public synchronized boolean matchWord(String typedText, boolean isHungry) {
		if (typedText.equals(this.word)) {
			if(isHungry){ resetHungryWord();}
			else{ resetWord();}
			return true;
		}
		else
			return false;
	}

	/*
	 * Drops FallingWord by increasing the y-value
	 */
	public synchronized  void drop(int inc) {
		setY(y+inc);
	}

	/*
	 * Drop hungryWord by increasing its x-value, shifting it horizontally
	 */
	public synchronized  void dropHungryWord(int inc) {
		setX(x+inc);
	}
	
	public synchronized  boolean dropped() {
		return dropped;
	}

}
