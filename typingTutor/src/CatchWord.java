import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;


//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	static AtomicBoolean done ; //REMOVE
	static AtomicBoolean pause; //REMOVE
	
	private static  FallingWord[] words; //list of words
	private static FallingWord HungryWord; //hungry words
	private static int noWords; //how many
	private static Score score; //user score
	
	CatchWord(String typedWord) {
		target=typedWord;
	}
	
	/*
	 * set FallingWords
	 */
	public static void setWords(FallingWord[] wordList) {
		words=wordList;	
		noWords = words.length;
	}

	/*
	 * Set the HungryWord
	 */
	public static void setHungryWord( FallingWord wordList) {
		HungryWord=wordList;	
	}
	
	/*
	 * Set the Score
	 */
	public static synchronized void setScore(Score sharedScore) {
		score=sharedScore;
	}
	
	/*
	 * Set the game flags, done and pause
	 */
	public static synchronized void setFlags(AtomicBoolean d, AtomicBoolean p) {
		done=d;
		pause=p;
	}


	/*
	 * sort Array according to y-values , so that the lowest word is selected
	 */
	public static synchronized void mySort(){
		Arrays.sort(words, new Comparator<FallingWord>() {
			@Override
			public int compare(FallingWord o1, FallingWord o2) {
				return o2.getY() - o1.getY();
			}
		});
	}
	
	@Override
	/*
	 * CatchWord Thread. Checks if a word matches one the FallingWords or the HungryWords
	 */
	public void run() {
		int i=0;
		mySort();  //sort array before catching Words
		while (i<noWords) {
				if (words[i].matchWord(target, false) && !pause.get()) {
					System.out.println( " score! " + target); //for checking
					score.caughtWord(target.length());	
					break;
				}

				//check if the typed word is the HungryWord
				if ((HungryWord).matchWord(target, true) && !pause.get()) {
					System.out.println( " score! " + target); //for checking
					score.caughtWord(target.length());	
					break;
				}	

		   i++;
		}		
	}
	
}
