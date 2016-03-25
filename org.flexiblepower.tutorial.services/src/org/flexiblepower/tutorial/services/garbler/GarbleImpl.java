package org.flexiblepower.tutorial.services.garbler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.flexiblepower.tutorial.api.GarbleService;

public class GarbleImpl implements GarbleService {
	@Override
	public String garbleWord(String word) {
		List<String> solution = new ArrayList<String>();
		for (String s : word.split(""))
		{
		    solution.add(s);
		}
		Collections.shuffle(solution);
		StringBuilder builder = new StringBuilder();
		for(String s : solution) {
			builder.append(s);
		}
		return builder.toString();
	}
}
