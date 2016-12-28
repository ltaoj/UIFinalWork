package com.ui.chatwindow;

import java.net.URL;
import java.util.ResourceBundle;

import com.ui.popupwindow.PopupWnd;
import com.ui.resource.R;
import com.ui.settingwindow.SettingWnd;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatController implements Initializable{

	// �������ָ��������ʱ����¼�ĵ�����ֵ�Լ���״̬�´����ԭ������
	double xoffset = 0;
	double yoffset = 0;
	double xorigin = 0;
	double yorigin = 0;
	// ��������ı���
	@FXML TextArea ta_input;
	// ͷ��
	@FXML ImageView iv_head;
	// ����Tab
	@FXML ImageView iv_tab_friend;
	@FXML ImageView iv_tab_dialog;
	@FXML ImageView iv_tab_space;
	// ϵͳ����
	@FXML ImageView iv_setting;
	// ��Ӻ���
	@FXML ImageView iv_add_friend;
	// ���Ϸ��Ľ�����ư�ť����С������󻯣��رգ�
	@FXML ImageView iv_window_min;
	@FXML ImageView iv_window_max;
	@FXML ImageView iv_window_exit;
	@FXML ImageView iv_heart;
	// ��Ϣ������
	@FXML ImageView iv_font;
	@FXML ImageView iv_emoticon;
	@FXML ImageView iv_file;
	@FXML ImageView iv_screenshot;
	@FXML ImageView iv_videochat;
	// �������Tab�������ұ�������С������󻯡��رյ����
	@FXML VBox vbox_leftbar;
	@FXML HBox hbox_title_friend;
	@FXML HBox hbox_title_dialog;
	@FXML HBox hbox_title_space;
	// ����ÿ��ֻ����ʾһ������Tab�����ȷ��
	@FXML VBox vbox_friend_list;
	@FXML VBox vbox_dialog_list;
	@FXML VBox vbox_space_list;
	// �ұ�������壬ÿ��ֻ��ʾһ��
	@FXML BorderPane bdpane_friend;
	@FXML BorderPane bdpane_dialog;
	@FXML BorderPane bdpane_space;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// ���ó�ʼ��ťͼƬ
		iv_head.setImage(new Image(R.image_url_head));
		iv_tab_friend.setImage(new Image(R.image_url_friend_normal));
		iv_tab_dialog.setImage(new Image(R.image_url_dialog_pressed));
		iv_tab_space.setImage(new Image(R.image_url_space_normal));
		iv_setting.setImage(new Image(R.image_url_setting_normal));
		iv_add_friend.setImage(new Image(R.image_url_add_normal));
		iv_window_min.setImage(new Image(R.image_url_min_normal));
		iv_window_max.setImage(new Image(R.image_url_max_normal));
		iv_window_exit.setImage(new Image(R.image_url_exit_normal));
		iv_heart.setImage(new Image(R.image_url_heart));
		iv_font.setImage(new Image(R.image_url_font_normal));
		iv_emoticon.setImage(new Image(R.image_url_emoticon_normal));
		iv_file.setImage(new Image(R.image_url_file_normal));
		iv_screenshot.setImage(new Image(R.image_url_screenshot_normal));
		iv_videochat.setImage(new Image(R.image_url_videochat_normal));
		// ���ÿ��϶�����
		setDragEnable(vbox_leftbar);
		setDragEnable(hbox_title_friend);
		setDragEnable(hbox_title_dialog);
		setDragEnable(hbox_title_space);
		// ��ʼ�������б�

		// ��ʼ���Ự�б�

	}
	// ���ÿ��϶����򣬰�����ߺ��ϱ�
	private void setDragEnable(Pane pane){
		// ��갴��ʱ����¼��ǰλ����Ϣ
		pane.setOnMousePressed((MouseEvent event) ->{
			event.consume();
			xoffset = event.getScreenX();
			yoffset = event.getScreenY();
			xorigin = ChatWnd.stage.getX();
			yorigin = ChatWnd.stage.getY();
		});
		// ����϶�ʱ�����󻯣���ô�����Ϊ���ģ���С�����϶�
		pane.setOnMouseDragged((MouseEvent event) ->{
			event.consume();
			if(ChatWnd.stage.isMaximized()){
				ChatWnd.stage.setMaximized(false);
				if(pane == hbox_title_friend||pane == hbox_title_dialog||pane == hbox_title_space){
					System.out.println("1");
					ChatWnd.stage.setX(xorigin + event.getScreenX() - 300 - (550/(ChatWnd.screenWidth-300)*(event.getScreenX()-300)));
					ChatWnd.stage.setY(0);
				}else if(pane == vbox_leftbar){
					System.out.println("2");
					ChatWnd.stage.setX(0);
					ChatWnd.stage.setY(yorigin + event.getScreenY() - (600/(ChatWnd.screenHeight)*(event.getScreenY())));
				}
				xorigin = ChatWnd.stage.getX();
				yorigin = ChatWnd.stage.getY();
			}
			ChatWnd.stage.setX(xorigin + event.getScreenX() - xoffset);
			ChatWnd.stage.setY(yorigin + event.getScreenY() - yoffset);
		});
		// ����ɿ�ʱ��ƫ��������0
		pane.setOnMouseReleased((MouseEvent event) -> {
			event.consume();
			xoffset = yoffset = xorigin = yorigin = 0;
		});
		// ������¿��϶����������߻ָ�ԭ״
		pane.setOnMouseClicked((MouseEvent event) -> {
			event.consume();
			if(event.getClickCount() == 2)
				ChatWnd.stage.setMaximized(!ChatWnd.stage.isMaximized());
		});
	}
	// ��ťͼƬ��Ӧ�¼�
	// ������Ѱ�ť
	public void friendIVAction(){
		ivReset();
		iv_tab_friend.setImage(new Image(R.image_url_friend_pressed));
		stackReset();
		vbox_friend_list.setVisible(true);
		bdpane_friend.setVisible(true);
	}
	// ����Ự��ť
	public void dialogIVAction(){
		ivReset();
		iv_tab_dialog.setImage(new Image(R.image_url_dialog_pressed));
		stackReset();
		vbox_dialog_list.setVisible(true);
		bdpane_dialog.setVisible(true);
	}
	// ���Ȧ��Tab
	public void spaceIVAction(){
		ivReset();
		iv_tab_space.setImage(new Image(R.image_url_space_pressed));
		stackReset();
		vbox_space_list.setVisible(true);
		bdpane_space.setVisible(true);
	}
	// �������
	public void settingIVAction() throws Exception{
		SettingWnd settingWnd = new SettingWnd();
		settingWnd.start(new Stage());
	}
	// �����С��
	public void minIVAction(){
		ChatWnd.stage.hide();
		ChatWnd.stage.setIconified(true);
	}
	// ������
	public void maxIVAction(){
		ChatWnd.stage.setMaximized(!ChatWnd.stage.isMaximized());
		ChatWnd.stage.centerOnScreen();
	}
	// ����˳�
	public void exitIVAction(){
		ChatWnd.stage.close();
		Platform.exit();
		System.exit(0);
	}
	// ��������TabͼƬ
	public void ivReset(){
		iv_tab_friend.setImage(new Image(R.image_url_friend_normal));
		iv_tab_dialog.setImage(new Image(R.image_url_dialog_normal));
		iv_tab_space.setImage(new Image(R.image_url_space_normal));
	}
	// �������
	public void stackReset(){
		vbox_friend_list.setVisible(false);
		vbox_dialog_list.setVisible(false);
		vbox_space_list.setVisible(false);
		bdpane_friend.setVisible(false);
		bdpane_dialog.setVisible(false);
		bdpane_space.setVisible(false);
	}

	public void sendBTAction(){
		PopupWnd popupWnd = new PopupWnd();
		popupWnd.start(new Stage());
	}
}
