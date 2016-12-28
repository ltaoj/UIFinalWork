package com.ui.popupwindow;

import java.net.URL;
import java.util.ResourceBundle;

import com.ui.chatwindow.ChatWnd;
import com.ui.resource.R;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PopupController implements Initializable{
	// �������ָ��������ʱ����¼�ĵ�����ֵ�Լ���״̬�´����ԭ������
	double xoffset = 0;
	double yoffset = 0;
	double xorigin = 0;
	double yorigin = 0;
	@FXML ImageView iv_popup_exit;
	@FXML ImageView iv_type;
	@FXML BorderPane border_titlebar;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		iv_popup_exit.setImage(new Image(R.image_url_popup_exit_normal));
		iv_type.setImage(new Image(R.image__popup_warn));
		setDragEnable(border_titlebar);
	}
	// exit imageview click action
	public void exitIVAction(){
		PopupWnd.getStage().close();
	}
	// cancel button click action
	public void cancelBTAction(){
		PopupWnd.getStage().close();
	}
	private void setDragEnable(Pane pane){
		// ��갴��ʱ����¼��ǰλ����Ϣ
		pane.setOnMousePressed((MouseEvent event) ->{
			event.consume();
			xoffset = event.getScreenX();
			yoffset = event.getScreenY();
			xorigin = PopupWnd.getStage().getX();
			yorigin = PopupWnd.getStage().getY();
		});
		// �϶�
		pane.setOnMouseDragged((MouseEvent event) ->{
			event.consume();
			PopupWnd.getStage().setX(xorigin + event.getScreenX() - xoffset);
			PopupWnd.getStage().setY(yorigin + event.getScreenY() - yoffset);
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
