package application;

import ui.UIHandler;

public class Application {

	private UIHandler uiHandler = UIHandler.getInstance();

	public static void main(String[] args) {
		Application application = new Application();
		application.run();
	}

	private void run() {
		uiHandler.run();
		// TODO some method that saves the data
	}
}
