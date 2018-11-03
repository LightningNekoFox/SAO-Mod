package thegoldenproof.saomod.capabilities;

public class Sharpenedness {
	
	public int progress;
	
	public Sharpenedness() {
		progress = 0;
	}
	
	public Sharpenedness(int number) {
		progress = number;
	}
	
	public int getProgress() {
		return progress;
	}
	
	public void setProgress(int number) {
		progress = number;
	}
	
	public void addProgress(int amount) {
		progress += amount;
	}
	
}
