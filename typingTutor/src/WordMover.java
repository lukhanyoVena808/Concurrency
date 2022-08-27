
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class WordMover extends Thread {
	private FallingWord myWord;
	private FallingWord HungryWord;
	private AtomicBoolean done;
	private AtomicBoolean pause; 
	private Score score;
	CountDownLatch startLatch; //so all can start at once
	
	WordMover( FallingWord word) {
		myWord = word;
	}
	
	WordMover(FallingWord word,WordDictionary dict, 
	FallingWord HungryWord, Score score,CountDownLatch startLatch, AtomicBoolean d, AtomicBoolean p) {
		this(word);
		this.startLatch = startLatch;
		this.score=score;
		this.done=d;
		this.pause=p;
		this.HungryWord = HungryWord;
	}
	


	@Override
	/*
	 * Drops FallingWord and checks if FallingWord is not colliding with the HungryWord
	 */
	public void run() {
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
						while(pause.get()&&!done.get()){} //while paused do nothing
						
						
						//checks if the FallingWord is clashing with the the HungryWord
						if (myWord.collide(HungryWord)) {
							score.missedWord();
							myWord.resetWord();
							}
				}
					//if word is dropped, increase miss counter and reset word
					if (!done.get() && myWord.dropped()) {
						score.missedWord();
						myWord.resetWord();
						}
					
				myWord.resetWord();
				}
	}
	
}
