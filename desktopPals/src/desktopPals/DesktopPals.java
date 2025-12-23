package desktopPals;

public class DesktopPals {

	public static void main(String[] args) {
		LoadingManager loadingManager = new LoadingManager();
		Thread loadingThread = new Thread(loadingManager);
		loadingThread.start();
		Pal pal = new Pal();
		if (pal.loaded()) {
			try {
				loadingManager.terminate();
				loadingThread.join();
				pal.setAllVisible();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
