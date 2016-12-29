package com.ui.popupwindow;

import com.ui.resource.R;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopupWnd extends Application{

	private static Stage stage;
	private static String title = "";
	private static String content = "";
	private static String subContent = "";

	public static String getTitle() {
		return title;
	}
	public static void setTitle(String title) {
		PopupWnd.title = title;
	}
	public static String getContent() {
		return content;
	}
	public static void setContent(String content) {
		PopupWnd.content = content;
	}
	public static String getSubContent() {
		return subContent;
	}
	public static void setSubContent(String subContent) {
		PopupWnd.subContent = subContent;
	}
	public static Stage getStage(){
		return stage;
	}
	@Override
	public void start(Stage primaryStage){
		try {
			stage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("PopupWnd.fxml"));
			Scene scene = new Scene(root,400,200);
			primaryStage.getIcons().add(new Image(R.image_url_icon));
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
