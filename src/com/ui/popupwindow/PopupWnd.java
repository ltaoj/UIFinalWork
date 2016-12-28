package com.ui.popupwindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopupWnd extends Application{

	private static Stage stage;

	public static Stage getStage(){
		return stage;
	}
	@Override
	public void start(Stage primaryStage){
		try {
			stage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("PopupWnd.fxml"));
			Scene scene = new Scene(root,400,200);
			scene.getStylesheets().add(getClass().getResource("popupStyle.css").toExternalForm());
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
//			primaryStage.show();
			primaryStage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
