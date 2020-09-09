package calculligator.model;

import java.util.LinkedList;

import calculligator.control.Controller;

public class Model {

	private Controller controller;
	private LinkedList<String> list;

	public Model(Controller controller) {
		this.controller = controller;
		this.list = new LinkedList<>();
	}

	public void process(String in) {
		controller.consume("print" + in);
		if (in.equals("=")) {
			controller.consume("print" + calculate(list));
			list = new LinkedList<>();
		} else if(in.equals("CE")){
			controller.consume("print ");
			list = new LinkedList<>();
		}else {
			list.add(in);
		}
	}

	private String calculate(LinkedList<String> list) {
		int a = Integer.valueOf(list.get(0));
		int b = Integer.valueOf(list.get(2));
		switch (list.get(1)) {
		case "+":
			return Integer.toString(a + b);
		case "-":
			return Integer.toString(a - b);
		case "x":
			return Integer.toString(a * b);
		case "/":
			return Integer.toString(a / b);
		}
		return "ERROR";
	}

}
