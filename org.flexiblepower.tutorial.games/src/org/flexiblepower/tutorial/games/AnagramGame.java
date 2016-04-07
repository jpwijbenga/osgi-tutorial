package org.flexiblepower.tutorial.games;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import org.flexiblepower.tutorial.api.GarbleService;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class AnagramGame implements Runnable {
	// The anagram challenge words.
	private String[] vocabulary = { "crossword", "osgi-service", "flexiblepower", "powermatcher", "alliance",
			"anagram" };
	// The root object that gives us the advantages of the OSGi functionality.
	private BundleContext context;
	// This object can look up the right service to garble our words. 
	private ServiceTracker<GarbleService, GarbleService> tracker;
	// The random number generator to pick a random word.
	private Random rnd = new Random();

	public AnagramGame(final BundleContext theContext) throws InterruptedException {
		context = theContext;
		// Create the tracker and open it.
		tracker = new ServiceTracker<GarbleService, GarbleService>(context, GarbleService.class, null);
		tracker.open();
	}

	@Override
	public void run() {
		System.out.println("Searching for the garble service...");
		GarbleService garbler = tracker.getService();
		while (garbler == null) {
			System.out.println("Garble service is not online yet. Retrying in a few seconds...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			garbler = tracker.getService();
		}
		System.out.println("Picking a new challenge.");
		String challenge = vocabulary[rnd.nextInt(vocabulary.length)];
		
		startNewGame(garbler, challenge);
	}

	private void startNewGame(GarbleService garbler, String challenge){
		System.out.println("Garbling a word now...");
		String garbled = "";
		if (garbler !=null) {
			garbled = garbler.garbleWord(challenge);
		} else {
			System.out.println("Can not find garbling service. Closing...");
			return;
		}
		System.out.println("What's the hidden word in this anagram? (type and press Enter)  " + garbled);
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();

		if (input.equals(challenge)) {
			System.out.println("Correct! You've found the answer.");
		} else {
			System.out.println("Incorrect answer. The correct answer was " + challenge + ". ");
		}
		System.out.println("Thanks for playing.");
		
		// Closing the streams.
		scanner.close();
		try {
			System.in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}