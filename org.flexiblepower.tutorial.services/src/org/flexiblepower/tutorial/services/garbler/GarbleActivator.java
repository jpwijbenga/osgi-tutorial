package org.flexiblepower.tutorial.services.garbler;

import java.util.Hashtable;

import org.flexiblepower.tutorial.api.GarbleService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class GarbleActivator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		ShuffleGarbler garbler = new ShuffleGarbler(); 
		Hashtable<String, String> props = new Hashtable<String, String>();
        props.put("GarbleType", "Shuffle");
        props.put("CompatibleStrings", "All");
        context.registerService(
            GarbleService.class.getName(), garbler, props);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// The service is unregistered automatically.		
	}

}
