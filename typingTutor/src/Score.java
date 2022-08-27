

public class Score {
	private int missedWords;
	private int caughtWords;
	private int gameScore;
	
	Score() {
		missedWords=0;
		caughtWords=0;
		gameScore=0;
	}
		
	// all getters and setters must be synchronized
	
	/*
	 * Returns missed Counter
	 */
	synchronized public int getMissed() {
		return missedWords;
	}

	/*
	 * Returns cuaght Counter
	 */
	synchronized public int getCaught() {
		return caughtWords;
	}
	
	/*
	 * Returns Total Score
	 */
	synchronized public int getTotal() {
		return (missedWords+caughtWords);
	}

	/*
	 * Returns gameScore
	 */
	synchronized public int getScore() {
		return gameScore;
	}
	
	/*
	 * Increments missed Counter
	 */
	synchronized public void missedWord() {
		missedWords++;
	}

	/*
	 * Increments caught Counter and gamescore
	 */
	synchronized public void caughtWord(int length) {
		caughtWords++;
		gameScore+=length;
	}

	/*
	 * Resets counters
	 */
	synchronized public void reset() {
		caughtWords=0;
		missedWords=0;
		gameScore=0;
	}
}
