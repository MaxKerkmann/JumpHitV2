package presentation;

import javafx.scene.layout.Pane;

public abstract class ViewController {
	protected Pane rootView;
	
	public ViewController() {

	}
	
	public abstract void initialize(); 

	public Pane getRootView() {
		return rootView;
	}
}
