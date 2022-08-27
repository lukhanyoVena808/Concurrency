

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;

<<<<<<< HEAD
=======
/*
 * Updated the class by making all functions synchronized
 */

>>>>>>> 9bb0a9a4c19bca499c7a6a9645fceefd281eab30
//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	static AtomicBoolean done ; //REMOVE
	static AtomicBoolean pause; //REMOVE
	
	private static  FallingWord[] words; //list of words
	private static ArrayList<FallingWord> HungryWords = new ArrayList<>(); //hungry words
	private static int noWords; //how many
	private static Score score; //user score
	
	CatchWord(String typedWord) {
		target=typedWord;
	}
	
	public static void setWords(FallingWord[] wordList) {
		words=wordList;	
		noWords = words.length;
	}


	public static void setHungryWords( ArrayList<FallingWord> wordList) {
		HungryWords=wordList;	
	}
	
	public static synchronized void setScore(Score sharedScore) {
		score=sharedScore;
	}
	
	public static synchronized void setFlags(AtomicBoolean d, AtomicBoolean p) {
		done=d;
		pause=p;
	}

<<<<<<< HEAD
	// sort Array according to y-values,  so that lowest word is selected
=======
	// sort Array according to y-values , so that lowest word is selected
>>>>>>> 9bb0a9a4c19bca499c7a6a9645fceefd281eab30
	public static synchronized void mySort(){
		Arrays.sort(words, new Comparator<FallingWord>() {
			@Override
			public int compare(FallingWord o1, FallingWord o2) {
				return o2.getY() - o1.getY();
			}
		});
	}
	
	@Override
	public void run() {
		int i=0;
		mySort();
		while (i<noWords) {
				if (words[i].matchWord(target, false) && !pause.get()) {
					System.out.println( " score! " + target); //for checking
					score.caughtWord(target.length());	
					break;
				}

<<<<<<< HEAD
				//check if HungryWord is typed
=======
				//check if the typed word is the HungryWord
>>>>>>> 9bb0a9a4c19bca499c7a6a9645fceefd281eab30
				if ((HungryWords.get(0)).matchWord(target, true) && !pause.get()) {
					System.out.println( " score! " + target); //for checking
					score.caughtWord(target.length());	
					break;
				}	

		   i++;
		}		
	}
	
}
