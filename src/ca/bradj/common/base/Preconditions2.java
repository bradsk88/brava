package ca.bradj.common.base;

import com.google.common.base.Preconditions;

public class Preconditions2 {

	public static String checkNotEmpty(String s) {
		Preconditions.checkArgument(!s.isEmpty());
		return s;
	}

}
