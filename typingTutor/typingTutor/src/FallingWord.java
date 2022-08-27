
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
	 * Initiates the constructor of a FallingWord. If the FallingWord is
	 * a HungryWord, the x-coordinate is set to zero and the y-value is set to height/2.
	 */
	FallingWord(String text,int x,int Y, int maxY, int maxX,boolean isHungryWord) { //most commonly used constructor - sets it all.
		this(text);
		if(isHungryWord){this.y = Y/2; this.x=0;}
		else{this.x=x;} //only need to set x, word is at top of screen at start

		this.maxY=maxY;
		this.maxX=maxX;
	}

	
	public static void increaseSpeed( ) {
		minWait+=50;
		maxWait+=50;
	}
	
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
	 * Modified method. If the x value goes beyond max, the word set to dropped,
	 * else just set the x-value
	 */
	public synchronized  void setX(int x) {
		if (x>maxX) {
			x=maxX;
			dropped=true; //user did not manage to catch this word
		}
		this.x=x;
	}
	
	public synchronized  void setWord(String text) {
		this.word=text;
	}

	public synchronized  String getWord() {
		return word;
	}
	
	public synchronized  int getX() {
		return x;
	}	
	
	public synchronized  int getY() {
		return y;
	}
	
	public synchronized  int getSpeed() {
		return fallingSpeed;
	}

	//change how sync here
	public void setPos(int x, int y) {
		setY(y);
		setX(x);
	}
	public synchronized void resetPos() {
		setY(0);
	}

	/*
	 * HungryWord Method. Resets the x-value of the HungryWord
	 */
	public synchronized void resetHungryPos() {
		setX(0);
	}

	/*
	 * Resets the value and x-coordinate of the HungryWord
	 */
	public synchronized void resetHungryWord(){
		resetHungryPos();
		word=dict.getNewHungryWord();
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait);
		
	}

	/*
	 * get the distance between two words
	 * Assumption is that each letter in a word takes approximately 4 spaces
	 */
	public synchronized int getLongLength(FallingWord w){
		if ((w.getWord()).length() >= (this.word).length()){
			return ((w.getWord()).length() *4);
		}
		return ((this.word).length() *4);
	}

	//checks if 2 words are colliding
	public synchronized boolean collide(FallingWord w){
		int LongLength = getLongLength(w);
		double checkX = Math.pow(this.getX()-w.getX(),2);
		double checkY = Math.pow(this.getY()-w.getY(),2);
		int distance = (int)Math.pow(checkX+checkY, 0.5);
		 //for checking if distance is calculated
		// System.out.println(this.word); 
		// System.out.println("(x,y): "+this.getX()+","+this.getY());
		// System.out.println("H(x,y): "+w.getX()+","+w.getY());
		// System.out.println("dist: "+dist);
		if ( distance<=LongLength){return true;}
		return false;
	}

	public synchronized void resetWord() {
		resetPos();
		word=dict.getNewWord();
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	
	}
	
	/*
	 * Modified Method. If the matche Word is a HungryWord, 
	 * then reset the HungryWord, else check if word matches with 
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
