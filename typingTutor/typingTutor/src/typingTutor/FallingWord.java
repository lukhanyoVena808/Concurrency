package typingTutor;


public class FallingWord {
	private String word; // the word
	private int x; //position - width
	private int y; // postion - height
	private int maxY; //maximum height
	private int maxX; //maximum height
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
		maxX=300;   // fro HungryWordMover
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	}
	
	FallingWord(String text) { 
		this();
		this.word=text;
	}
	
	
	FallingWord(String text,int x,int Y, int maxY, int maxX,boolean hungry) { //most commonly used constructor - sets it all.
		this(text);
		if(hungry){this.y = Y/2; this.x=0;}
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

	public synchronized void resetHungryPos() {
		setX(0);
	}

	public synchronized void resetHungryWord() {
		resetHungryPos();
		word=dict.getNewHungryWord();
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
		
	}

	public synchronized void resetWord() {
		resetPos();
		word=dict.getNewWord();
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	
	}
	
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

	//Shift Hungry Word
	public synchronized  void dropHungryWord(int inc) {
		setX(x+inc);
	}
	
	public synchronized  boolean dropped() {
		return dropped;
	}

}
