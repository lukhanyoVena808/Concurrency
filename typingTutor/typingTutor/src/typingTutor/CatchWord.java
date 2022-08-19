package typingTutor;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * Updated the class by making all functions synchronized, including run method
 */

//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	static AtomicBoolean done ; //REMOVE
	static AtomicBoolean pause; //REMOVE
	
	private static  FallingWord[] words; //list of words
	private static int noWords; //how many
	private static Score score; //user score
	
	CatchWord(String typedWord) {
		target=typedWord;
	}
	
	public static void setWords(FallingWord[] wordList) {
		words=wordList;	
		noWords = words.length;
	}
	
	public static synchronized void setScore(Score sharedScore) {
		score=sharedScore;
	}
	
	public static synchronized void setFlags(AtomicBoolean d, AtomicBoolean p) {
		done=d;
		pause=p;
	}
	
	public void run() {
		int i=0;
		Arrays.sort(words, CatchWord::compare); //sort array
		while (i<noWords) {		
			// while(pause.get()) {}// when game is paused,
				if (words[i].matchWord(target) && !pause.get()) {
					System.out.println( " score! '" + target); //for checking
					score.caughtWord(target.length());	
					//FallingWord.increaseSpeed();
					break;
				}
			
		   i++;
		}		
	}
	
	//Compare Method
	public static int compare(FallingWord a, FallingWord b) {
	return a.getWord().compareTo(b.getWord());
}
}
