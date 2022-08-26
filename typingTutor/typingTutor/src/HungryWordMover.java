

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class HungryWordMover extends Thread {
	private FallingWord myWord;
	private AtomicBoolean done;
	private AtomicBoolean pause;
	private AtomicBoolean sleepy; 
	private Score score;
	CountDownLatch startLatch; //so all can start at once
	
	HungryWordMover( FallingWord word) {
		myWord = word;
	}
	
	HungryWordMover( FallingWord word,WordDictionary dict, Score score,
			CountDownLatch startLatch, AtomicBoolean d, AtomicBoolean p, AtomicBoolean sly) {
		this(word);
		this.startLatch = startLatch;
		this.score=score;
		this.done=d;
		this.pause=p;
		this.sleepy = sly;
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
						myWord.dropHungryWord(8);
						try {
							// sleepy.set(true);
							sleep(myWord.getSpeed());
							// sleep(10000);
							// sleepy.set(false);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		
						while(pause.get()&&!done.get()){}
				}
					
					if (!done.get() && myWord.dropped()) {
						score.missedWord();
						myWord.resetHungryWord();
						}
					
				myWord.resetHungryWord();						
				}
				
	}
	
}

