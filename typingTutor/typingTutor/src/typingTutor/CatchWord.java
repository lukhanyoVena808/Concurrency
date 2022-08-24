package typingTutor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
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
	private static ArrayList<FallingWord> HungryWords = new ArrayList<>(); //hungry words
	private static int noWords; //how many
	private static Score score; //user score

	private static ArrayList<Integer> yLengths = new ArrayList<>();  //stores the y-lengths 
	private static HashMap<Integer,FallingWord> linkLengths = new HashMap<>(); //stores the y-length and the Falling word

	
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

	public static synchronized boolean matcher(FallingWord [] w, String word){
		boolean inWords = false;
		for(int i=0;i<w.length;i++){
			if((w[i].getWord()).equals(word)){
				yLengths.add(w[i].getY());
				linkLengths.put((w[i].getY()),w[i]);
				inWords=true;
			}
		}
		Collections.sort(yLengths); // sort lengths
		return inWords;
	}
	
	@Override
	public void run() {
		int i=0;
		while (i<noWords) {

			// if(matcher(words, target)){
				// int pos = yLengths.size()-1;
				// int fallingPos = yLengths.get(pos);
				if (words[i].matchWord(target, false) && !pause.get()) {
					System.out.println( " score! " + target); //for checking
					score.caughtWord(target.length());	
					break;
				}
			// }
			//check Hungry words
			if ((HungryWords.get(0)).matchWord(target, true) && !pause.get()) {
				System.out.println( " score! " + target); //for checking
				score.caughtWord(target.length());	
				break;
			}
		   i++;
		}		
	}
	
}
