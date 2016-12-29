package com.ui.settingwindow;

import com.ui.resource.R;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SettingWnd extends Application{
	private static Stage stage;

	public static Stage getStage(){
		return stage;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("SettingWnd.fxml"));
		Scene scene = new Scene(root,650,450);
		primaryStage.getIcons().add(new Image(R.image_url_icon));
		primaryStage.setTitle("CC…Ë÷√");
		scene.getStylesheets().add(getClass().getResource("settingStyle.css").toExternalForm());
//		primaryStage.initModality(Modality.APPLICATION_MODAL);
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
