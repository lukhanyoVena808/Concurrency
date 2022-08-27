import java.util.Comparator;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class WordMover extends Thread {
	private FallingWord myWord;
	private static FallingWord[] words;
	private FallingWord HungryWord;
	private AtomicBoolean done;
	private AtomicBoolean pause; 
	private Score score;
	CountDownLatch startLatch; //so all can start at once
	
	WordMover( FallingWord word) {
		myWord = word;
	}
	
	WordMover( FallingWord[] wrds,FallingWord word,WordDictionary dict, 
	FallingWord HungryWord, Score score,CountDownLatch startLatch, AtomicBoolean d, AtomicBoolean p) {

		this(word);
		this.startLatch = startLatch;
		this.score=score;
		this.done=d;
		this.pause=p;
		words = wrds;
		this.HungryWord = HungryWord;
	}
	
	// sort Array accordeing to y-values, so that lowest word is selected
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

		mySort();
		try {
			System.out.println(myWord.getWord() + " waiting to start " );
			startLatch.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //wait for other threads to start
		System.out.println(myWord.getWord() + " started" );

		
			while (!done.get()) {
				//animate the word
				while (!myWord.dropped() && !done.get()) {
						myWord.drop(10);
						try {
							sleep(myWord.getSpeed());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		
						while(pause.get()&&!done.get()){}
						
<<<<<<< HEAD
						//checks if the word collides with the HungryWord Mover
=======
						
						//checks if the FallingWord is clashing with the the HungryWord
>>>>>>> 9bb0a9a4c19bca499c7a6a9645fceefd281eab30
						if (myWord.collide(HungryWord)) {
							score.missedWord();
							myWord.resetWord();
							}
				}

					if (!done.get() && myWord.dropped()) {
						score.missedWord();
						myWord.resetWord();
						}
					
				myWord.resetWord();
				}
	}
	
}
