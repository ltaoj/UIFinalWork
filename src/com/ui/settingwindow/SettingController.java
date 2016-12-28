package com.ui.settingwindow;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingController implements Initializable{

	@FXML HBox hbox_titlebar; // ø…Õœ∂Ø«¯”Ú
	@FXML Button bt_column1;
	@FXML Button bt_column2;

	@FXML VBox vbox_line1;
	@FXML VBox vbox_line2;
	@FXML VBox vbox_line3;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	public void column1BTAction(){

	}
	public void column2BTAction(){

	}
	public void column3BTAction(){

	}
	public void c1rowHBoxAction(){

	}

	public void rowReset(int column){
		switch (column) {
		case 1:

			break;

		default:
			break;
		}
	}

}
