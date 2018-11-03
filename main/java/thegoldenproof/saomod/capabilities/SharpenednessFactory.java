package thegoldenproof.saomod.capabilities;

import java.util.concurrent.Callable;

public class SharpenednessFactory implements Callable<Sharpenedness>{

	@Override
	public Sharpenedness call() throws Exception {
		return new Sharpenedness();
	}

}
