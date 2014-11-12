package ca.bradj.fx;

import javafx.application.Platform;

public class FXThreading {

	public static void invokeLater(Runnable runnable) {
		try {
			Platform.runLater(runnable);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
