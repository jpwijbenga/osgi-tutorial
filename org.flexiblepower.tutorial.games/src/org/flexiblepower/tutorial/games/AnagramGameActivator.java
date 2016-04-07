package org.flexiblepower.tutorial.games;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class AnagramGameActivator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		AnagramGame game = new AnagramGame(context);
		Thread thread = new Thread(game);
		thread.start();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
	}
}
