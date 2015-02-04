package misc;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RobotCoin {

	public static final int SIZE = 10000;

	public static Boolean[] coins = new Boolean[SIZE];

	static {
		boolean standard = false;
		for (int i = 0; i < SIZE; i++) {
			coins[i] = standard;
			standard = !standard;
		}
	}

	public static void main(String[] args) throws InterruptedException {

		int poolSize = 10;

		ExecutorService es = Executors.newFixedThreadPool(poolSize);
		for (int i = 0; i < poolSize; i++) {
			es.execute(new Robot());
		}

		Thread.sleep(10000);

		es.shutdownNow();

		// check true/false distribution
		int trueCoins = 0;
		for (int i = 0; i < coins.length; i++) {
			if (coins[i]) {
				trueCoins++;
			}
		}

		System.out.println("True Coins: " + trueCoins);
	}

}

class Robot implements Runnable {

	private Random rnd = new Random();

	@Override
	public void run() {

		while (!Thread.currentThread().isInterrupted()) {
			int coinNum = rnd.nextInt(RobotCoin.SIZE);
			synchronized (RobotCoin.coins[coinNum]) {
				if (RobotCoin.coins[coinNum] == false) {
					boolean next = rnd.nextBoolean();
					RobotCoin.coins[coinNum] = next;
					System.out.println(Thread.currentThread().getName()
							+ ": Coin " + coinNum + "false -> " + next);
				} else {
					RobotCoin.coins[coinNum] = false;
					System.out.println(Thread.currentThread().getName()
							+ ": Coin " + coinNum + "true -> false");
				}
			}
			Thread.yield();
		}

	}

}
