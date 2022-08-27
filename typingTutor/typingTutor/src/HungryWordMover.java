
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

<<<<<<< HEAD

/*
 * @author: Lukhanyo Vena 
 * Program to create a thread for the HungryWordr
=======
/*
 * @author: Lukhanyo Vena
 * The hungryWordMOver IS A THREAD using a FallingWord.
 * The hungryWord is dropped (horizontally) until it reaches end of screen
>>>>>>> 9bb0a9a4c19bca499c7a6a9645fceefd281eab30
 */

public class HungryWordMover extends Thread {
	private FallingWord myWord;
	private AtomicBoolean done;
	private AtomicBoolean pause;
	private Score score;
	CountDownLatch startLatch; //so all can start at once
	
	HungryWordMover( FallingWord word) {
		myWord = word;
	}
	
	HungryWordMover( FallingWord word,WordDictionary dict, Score score,
			CountDownLatch startLatch, AtomicBoolean d, AtomicBoolean p) {
		this(word);
		this.startLatch = startLatch;
		this.score=score;
		this.done=d;
		this.pause=p;
	}
	
	
	
	public void run() {

		//System.out.println(myWord.getWord() + " falling speed = " + myWord.getSpeed());
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

						//drop HungryWord
						myWord.dropHungryWord(8);
						try {
							sleep(myWord.getSpeed());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		
						while(pause.get()&&!done.get()){}
				}
					
<<<<<<< HEAD
					//if the HungryWord is dropped, increase miss counter and reset word
=======
					// Checks if the HungryWord is dropped and reset it
>>>>>>> 9bb0a9a4c19bca499c7a6a9645fceefd281eab30
					if (!done.get() && myWord.dropped()) {
						score.missedWord();
						myWord.resetHungryWord();
						}
				
				// resets the HungryWord
				myWord.resetHungryWord();						
				}
				
	}
	
}

