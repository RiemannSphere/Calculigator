package calculligator.control;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import calculligator.model.Model;
import calculligator.view.View;
import javafx.application.Platform;

public class Controller {

	private View view;
	private Model model;

	private ArrayBlockingQueue<String> queue;

	public void runController() {
		queue = new ArrayBlockingQueue<>(ConstControl.QUEUE_SIZE);
		// Consumer
		new Thread(new Runnable() {
			@Override
			public void run() {
				String out;
				try {
					while (!(out = queue.take()).equals("exit")) {
						System.out.println("consumed -> " + out);
						service(out);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void service(String arg) {

		if (arg.contains("print")) {
			String toPrint = arg.substring(arg.indexOf("print") + "print".length(), arg.length());
			Platform.runLater(() -> {
				view.printToResult(toPrint);
			});
		} else {
			model.process(arg);
		}

	}

	public void consume(String in) {
		try {
			queue.offer(in, ConstControl.OFFER_TIMEOUT, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setView(View view) {
		this.view = view;
	}

}
