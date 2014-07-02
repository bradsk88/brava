package ca.bradj.fx.dialogs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ca.bradj.common.base.Failable;
import ca.bradj.common.base.Preconditions2;

public class TextInputQuestionDialog {
	private static final int DEFAULT_COMPONENT_SPACING = 10;
	private static final double DEFAULT_HEIGHT = 140;
	private static final double DEFAULT_WIDTH = 600;
	private String message;
	protected Failable<String> answer = Failable.fail("No directory selected");

	TextInputQuestionDialog() {
		// TODO Auto-generated constructor stub
	}
	
	public void setMessage(String message) {
		this.message = Preconditions2.checkNotEmpty(message);
	}

	public Failable<String> showDialog() {
		Stage s = new Stage();
		s.setHeight(DEFAULT_HEIGHT);
		s.setWidth(DEFAULT_WIDTH);
		VBox pane = new VBox(DEFAULT_COMPONENT_SPACING);
		pane.getChildren().add(new Label(message));
		TextField textField = new TextField();
		pane.getChildren().add(textField);
		HBox buttons = new HBox(DEFAULT_COMPONENT_SPACING);

		Button okButton = makeOkButton(s, textField);
		Button cancelButton = makeCancelButton(s);
		buttons.getChildren().add(okButton);
		buttons.getChildren().add(cancelButton);

		pane.getChildren().add(buttons);

		s.initModality(Modality.APPLICATION_MODAL);
		s.initStyle(StageStyle.UTILITY);
		s.setScene(new Scene(pane));
		s.showAndWait();

		return answer;
	}

	private Button makeCancelButton(final Stage s) {
		Button button = new Button("Cancel");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				s.close();
			}
		});
		return button;
	}

	private Button makeOkButton(final Stage s, final TextField field) {
		Button button = new Button("Ok");
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				answer = Failable.ofSuccess(field.getText());
				s.close();
			}
		});
		return button;
	}

}
