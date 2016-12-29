package com.ui.settingwindow;

import java.net.URL;
import java.util.ResourceBundle;

import com.ui.popupwindow.PopupWnd;
import com.ui.resource.R;
import com.ui.settings.Config;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class SettingController implements Initializable{
	// �������ָ��������ʱ����¼�ĵ�����ֵ�Լ���״̬�´����ԭ������
	double xoffset = 0;
	double yoffset = 0;
	double xorigin = 0;
	double yorigin = 0;

	@FXML HBox hbox_titlebar; // ���϶�����
	@FXML Button bt_column1;
	@FXML Button bt_column2;
	@FXML BorderPane border_column1;
	@FXML BorderPane border_column2;

	@FXML Button bt_myinfo;
	@FXML Button bt_close;
	@FXML Button bt_update;
	@FXML Button bt_about;
	@FXML VBox vbox_line1;
	@FXML VBox vbox_line2;
	@FXML VBox vbox_line3;
	@FXML VBox vbox_line4;
	@FXML ImageView iv_dot1;
	@FXML ImageView iv_dot2;
	@FXML ImageView iv_dot3;
	@FXML ImageView iv_dot4;

	@FXML VBox vbox_row11;
	@FXML VBox vbox_row12;
	@FXML VBox vbox_row13;
	@FXML VBox vbox_row14;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// ����dotͼƬ
		iv_dot1.setImage(new Image(R.image_slider_dot_normal));
		iv_dot2.setImage(new Image(R.image_slider_dot_normal));
		iv_dot3.setImage(new Image(R.image_slider_dot_normal));
		iv_dot4.setImage(new Image(R.image_slider_dot_normal));
		column1BTAction();
		myinfoBTAction();
		// ���ÿ��϶�����
		setDragEnable(hbox_titlebar);
	}
	public void minIVAction(){
		SettingWnd.getStage().setIconified(true);
	}
	public void exitIVAction(){
		SettingWnd.getStage().close();
	}
	public void column1BTAction(){
		columnReset();
		border_column1.setVisible(true);
	}
	public void column2BTAction(){
		columnReset();
		border_column2.setVisible(true);
	}

	public void c1rowHBoxAction(){

	}

	public void columnReset(){
		border_column1.setVisible(false);
		border_column2.setVisible(false);
	}

	public void myinfoBTAction(){
		rowReset(1);
		vbox_line1.setStyle("-fx-background-color: "+Config.color.color_pressed+";-fx-background-insets: 0 4.2;");
		iv_dot1.setImage(new Image(R.image_slider_dot_pressed));
		bt_myinfo.setTextFill(Paint.valueOf("#1296db"));
		vbox_row11.setVisible(true);
	}
	public void closeBTAction(){
		rowReset(1);
		iv_dot2.setImage(new Image(R.image_slider_dot_pressed));
		vbox_line2.setStyle("-fx-background-color: "+Config.color.color_pressed+";-fx-background-insets: 0 4.2;");
		bt_close.setTextFill(Paint.valueOf("#1296db"));
		vbox_row12.setVisible(true);
	}
	public void updateBTAction(){
		rowReset(1);
		vbox_line3.setStyle("-fx-background-color: "+Config.color.color_pressed+";-fx-background-insets: 0 4.2;");
		iv_dot3.setImage(new Image(R.image_slider_dot_pressed));
		bt_update.setTextFill(Paint.valueOf("#1296db"));
		vbox_row13.setVisible(true);
	}
	public void aboutBTAction(){
		rowReset(1);
		vbox_line4.setStyle("-fx-background-color: "+Config.color.color_pressed+";-fx-background-insets: 0 4.2;");
		iv_dot4.setImage(new Image(R.image_slider_dot_pressed));
		bt_about.setTextFill(Paint.valueOf("#1296db"));
		vbox_row14.setVisible(true);
	}
	public void rowReset(int column){
		switch (column) {
		case 1:
			vbox_row11.setVisible(false);
			vbox_row12.setVisible(false);
			vbox_row13.setVisible(false);
			vbox_row14.setVisible(false);
//			bt_myinfo.setTextFill(Paint.valueOf("#1296db"));
			bt_myinfo.setTextFill(Paint.valueOf("#3e3e40"));
			bt_close.setTextFill(Paint.valueOf("#3e3e40"));
			bt_update.setTextFill(Paint.valueOf("#3e3e40"));
			bt_about.setTextFill(Paint.valueOf("#3e3e40"));
			iv_dot1.setImage(new Image(R.image_slider_dot_normal));
			iv_dot2.setImage(new Image(R.image_slider_dot_normal));
			iv_dot3.setImage(new Image(R.image_slider_dot_normal));
			iv_dot4.setImage(new Image(R.image_slider_dot_normal));
			vbox_line1.setStyle("-fx-background-color: "+Config.color.color_titlebar+";-fx-background-insets: 0 4.5;");
			vbox_line2.setStyle("-fx-background-color: "+Config.color.color_titlebar+";-fx-background-insets: 0 4.5;");
			vbox_line3.setStyle("-fx-background-color: "+Config.color.color_titlebar+";-fx-background-insets: 0 4.5;");
			vbox_line4.setStyle("-fx-background-color: "+Config.color.color_titlebar+";-fx-background-insets: 0 4.5;");
			break;

		default:
			break;
		}
	}
	public void updateAction(){
		PopupWnd popupWnd = new PopupWnd();
		PopupWnd.setTitle("��ʾ");
		PopupWnd.setContent("����ʧ��,�����ƺ����˵�����");
		PopupWnd.setSubContent("�������������Ƿ���");
		popupWnd.start(new Stage());
	}
	public void feedbackBTAction(){
		PopupWnd popupWnd = new PopupWnd();
		PopupWnd.setTitle("��ʾ");
		PopupWnd.setContent("��л���ķ���");
		PopupWnd.setSubContent("����������������ɼ�Ⱥ625871322һ������");
		popupWnd.start(new Stage());

	}
	private void setDragEnable(Pane pane){
		// ��갴��ʱ����¼��ǰλ����Ϣ
		pane.setOnMousePressed((MouseEvent event) ->{
			event.consume();
			xoffset = event.getScreenX();
			yoffset = event.getScreenY();
			xorigin = SettingWnd.getStage().getX();
			yorigin = SettingWnd.getStage().getY();
		});
		// �϶�
		pane.setOnMouseDragged((MouseEvent event) ->{
			event.consume();
			SettingWnd.getStage().setX(xorigin + event.getScreenX() - xoffset);
			SettingWnd.getStage().setY(yorigin + event.getScreenY() - yoffset);
		});
		// ����ɿ�ʱ��ƫ��������0
		pane.setOnMouseReleased((MouseEvent event) -> {
			event.consume();
			xoffset = yoffset = xorigin = yorigin = 0;
		});

		pane.setOnMouseClicked((MouseEvent event) -> {
			event.consume();
		});
	}
}
