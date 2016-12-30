package com.ui.chatwindow;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.ui.bubble.BubbleSpec;
import com.ui.bubble.BubbledLabel;
import com.ui.callback.CellRenderer;
import com.ui.messages.Message;
import com.ui.messages.Status;
import com.ui.messages.User;
import com.ui.popupwindow.PopupWnd;
import com.ui.resource.R;
import com.ui.settingwindow.SettingWnd;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChatController implements Initializable{

	private String[] dialogdata = {"谢雨晗","安妮","CC Team"};
	private String[] frienddata = {"安妮","CC Team","李港钦","李涛江","谢雨晗",};
	ArrayList<User> dialogs;
	ArrayList<User> friends;
	// 当前聊天的好友
	String current_dialog;
	String current_friend;
	// 当鼠标在指定区域按下时，记录改点坐标值以及该状态下窗体的原点坐标
	double xoffset = 0;
	double yoffset = 0;
	double xorigin = 0;
	double yorigin = 0;
	@FXML BorderPane bdpane_root;
	// 输入多行文本框
	@FXML TextArea ta_input_dialog;
	@FXML TextArea ta_input_friend;
	// 头像
	@FXML ImageView iv_head;
	// 三个Tab
	@FXML ImageView iv_tab_friend;
	@FXML ImageView iv_tab_dialog;
	@FXML ImageView iv_tab_space;
	// 系统设置
	@FXML ImageView iv_setting;
	// 添加好友
	@FXML ImageView iv_add_friend;
	// 右上方的界面控制按钮（最小化，最大化，关闭）
	@FXML ImageView iv_window_min;
	@FXML ImageView iv_window_max;
	@FXML ImageView iv_window_exit;
	@FXML ImageView iv_heart;
	// 消息工具条
	@FXML ImageView iv_font;
	@FXML ImageView iv_emoticon;
	@FXML ImageView iv_file;
	@FXML ImageView iv_screenshot;
	@FXML ImageView iv_videochat;
	// 左边容纳Tab的面板和右边容纳最小化、最大化、关闭的面板
	@FXML VBox vbox_leftbar;
	@FXML HBox hbox_title_friend;
	@FXML HBox hbox_title_dialog;
	@FXML HBox hbox_title_space;
	// 三个每次只能显示一个根据Tab点击来确定
	@FXML VBox vbox_friend_list;
	@FXML VBox vbox_dialog_list;
	@FXML VBox vbox_space_list;
	// 右边三个面板，每次只显示一个
	@FXML BorderPane bdpane_friend;
	@FXML BorderPane bdpane_dialog;
	@FXML BorderPane bdpane_space;
	// 好友列表
	@FXML ListView lv_friends;
	@FXML ListView lv_dialog;
	// titlebar信息Label
	@FXML Label lb_chatting;
	@FXML Label lb_friend;
	// 输入输出界面
	@FXML BorderPane bdpane_input_friend;
	@FXML ListView lv_messages_friend;
	@FXML BorderPane bdpane_input_dialog;
	@FXML ListView lv_messages_dialog;
	// 发送按钮
	@FXML Button bt_send_dialog;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 设置初始按钮图片
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
		// 设置可拖动区域
		setDragEnable(vbox_leftbar);
		setDragEnable(hbox_title_friend);
		setDragEnable(hbox_title_dialog);
		setDragEnable(hbox_title_space);
		// 隐藏区域
		hideIO(1);
		hideIO(2);
		lv_friends.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

			@Override
			public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
				lb_friend.setText(newValue.getUsername());
				showIO(1);
				current_friend = newValue.getUsername();
				ta_input_friend.requestFocus();
			}
		});
		lv_dialog.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

			@Override
			public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
				if(oldValue!=null&&!oldValue.getUsername().equals(newValue.getUsername())){
					lv_messages_dialog.getItems().clear();
				}
				lb_chatting.setText("正在和" + newValue.getUsername()+"聊天");
				showIO(2);
				current_dialog = newValue.getUsername();
				ta_input_dialog.requestFocus();
			}
		});
		friends = new ArrayList<>();
		dialogs = new ArrayList<>();
		// 初始化好友列表
		for(int i = 0;i < frienddata.length;i++){
			User user = new User();
			user.setUsername(frienddata[i]);
			user.setPicture(frienddata[i]);
			user.setStatus(Status.ONLINE);
			friends.add(user);
			updateFriendList(friends,lv_friends);
		}
		// 初始化会话列表
		for(int i = 0;i < dialogdata.length;i++){
			User user = new User();
			user.setUsername(dialogdata[i]);
			user.setPicture(dialogdata[i]);
			user.setStatus(Status.ONLINE);
			Date date = new Date();
			date.setHours(date.getHours()-i*2);
			date.setMinutes(date.getMinutes() + i*15);
			date.setSeconds(date.getSeconds() - i*27);
			user.setDate(date.getHours() + ":" +date.getMinutes()+":"+date.getSeconds());
			dialogs.add(user);
			updateFriendList(dialogs,lv_dialog);
		}
		bdpane_root.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				ta_input_dialog.requestFocus();
			}
		});
		// 添加KeyEvent
		ta_input_dialog.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				System.out.println(event.getCode());
				switch (event.getCode()) {
				case ENTER:
					event.consume();
					sendDBTAction();
					break;
				default:
					break;
				}
			}
		});
	}
	// 设置可拖动区域，包括左边和上边
	private void setDragEnable(Pane pane){
		// 鼠标按下时，记录当前位置信息
		pane.setOnMousePressed((MouseEvent event) ->{
			event.consume();
			xoffset = event.getScreenX();
			yoffset = event.getScreenY();
			xorigin = ChatWnd.stage.getX();
			yorigin = ChatWnd.stage.getY();
		});
		// 鼠标拖动时如果最大化，那么以鼠标为中心，缩小后再拖动
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
		// 鼠标松开时，偏移数据置0
		pane.setOnMouseReleased((MouseEvent event) -> {
			event.consume();
			xoffset = yoffset = xorigin = yorigin = 0;
		});
		// 点击两下可拖动区域，最大或者恢复原状
		pane.setOnMouseClicked((MouseEvent event) -> {
			event.consume();
			if(event.getClickCount() == 2)
				ChatWnd.stage.setMaximized(!ChatWnd.stage.isMaximized());
		});
	}
	// 按钮图片响应事件
	// 点击好友按钮
	public void friendIVAction(){
		ivReset();
		iv_tab_friend.setImage(new Image(R.image_url_friend_pressed));
		stackReset();
		vbox_friend_list.setVisible(true);
		bdpane_friend.setVisible(true);
	}
	// 点击会话按钮
	public void dialogIVAction(){
		ivReset();
		iv_tab_dialog.setImage(new Image(R.image_url_dialog_pressed));
		stackReset();
		vbox_dialog_list.setVisible(true);
		bdpane_dialog.setVisible(true);
	}
	// 点击圈子Tab
	public void spaceIVAction(){
		ivReset();
		iv_tab_space.setImage(new Image(R.image_url_space_pressed));
		stackReset();
		vbox_space_list.setVisible(true);
		bdpane_space.setVisible(true);
		PopupWnd popupWnd = new PopupWnd();
		popupWnd.setTitle("提示");
		popupWnd.setContent("该页面代码还没生产出来");
		popupWnd.setSubContent("项目会在https://github.com/ltaoj/UIFinalWork更新");
		popupWnd.start(new Stage());
	}
	// 点击设置
	public void settingIVAction() throws Exception{
		SettingWnd settingWnd = new SettingWnd();
		settingWnd.start(new Stage());
	}
	// 点击最小化
	public void minIVAction(){
		ChatWnd.stage.setIconified(true);
	}
	// 点击最大化
	public void maxIVAction(){
		ChatWnd.stage.setMaximized(!ChatWnd.stage.isMaximized());
		ChatWnd.stage.centerOnScreen();
	}
	// 点击退出
	public void exitIVAction(){
		ChatWnd.stage.close();
		Platform.exit();
		System.exit(0);
	}
	// 重置三个Tab图片
	public void ivReset(){
		iv_tab_friend.setImage(new Image(R.image_url_friend_normal));
		iv_tab_dialog.setImage(new Image(R.image_url_dialog_normal));
		iv_tab_space.setImage(new Image(R.image_url_space_normal));
	}
	// 重置面板
	public void stackReset(){
		vbox_friend_list.setVisible(false);
		vbox_dialog_list.setVisible(false);
		vbox_space_list.setVisible(false);
		bdpane_friend.setVisible(false);
		bdpane_dialog.setVisible(false);
		bdpane_space.setVisible(false);
	}
	// 隐藏面板
	public void hideIO(int column){
		switch (column) {
		case 1:// friend
			lv_messages_friend.setVisible(false);
			bdpane_input_friend.setVisible(false);
			break;
		case 2:// dialog
			lv_messages_dialog.setVisible(false);
			bdpane_input_dialog.setVisible(false);
			break;
		default:
			break;
		}
	}
	// 显示面板
	public void showIO(int column){
		switch (column) {
		case 1:
			lv_messages_friend.setVisible(true);
			bdpane_input_friend.setVisible(true);
			break;
		case 2:
			lv_messages_dialog.setVisible(true);
			bdpane_input_dialog.setVisible(true);
			break;
		default:
			break;
		}
	}
	// 好友列表
	public void updateFriendList(ArrayList<User> users,ListView listview){
        Platform.runLater(() -> {
            ObservableList<User> newusers = FXCollections.observableList(users);
            listview.setItems(newusers);
            listview.setCellFactory(new CellRenderer());
        });
	}
	public void sendDBTAction(){
		String msg = ta_input_dialog.getText();
		if(msg.equals("")){
			return;
		}
		ta_input_dialog.setText("");
		ta_input_dialog.requestFocus();
		Message message = new Message();
		System.out.println(msg.toString());
		message.setMsg(msg);
		message.setPicture(R.image_url_head);
		message.setUsername("李涛江");
		addToChat(message, lv_messages_dialog);
		message.setPicture(current_dialog);
		message.setUsername(current_dialog);
		addToChat(message, lv_messages_dialog);
		lv_messages_dialog.scrollTo(lv_messages_dialog.getItems().size() - 1);
	}
	public void sendFBTAction(){
		PopupWnd popupWnd = new PopupWnd();
		popupWnd.setTitle("提示");
		popupWnd.setContent("该页面不能操作");
		popupWnd.setSubContent("请点击左侧会话窗口操作");
		popupWnd.start(new Stage());
	}
	// 将发送的消息添加到聊天面板上
    public synchronized void addToChat(Message msg,ListView listView) {
        Task<HBox> othersMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                Image image = new Image("res/" + msg.getPicture() + ".jpg");
                ImageView profileImage = new ImageView(image);
                profileImage.setFitHeight(32);
                profileImage.setFitWidth(32);
                BubbledLabel bl6 = new BubbledLabel();
                bl6.setText(msg.getMsg());
                bl6.setBackground(new Background(new BackgroundFill(Color.WHITE,null, null)));
                HBox x = new HBox();
                x.setAlignment(Pos.CENTER_LEFT);
                x.setSpacing(10);
                bl6.setBubbleSpec(BubbleSpec.FACE_LEFT_CENTER);
                x.getChildren().addAll(profileImage, bl6);
//                x.setHgrow(null, Priority.ALWAYS);
                return x;
            }
        };

        othersMessages.setOnSucceeded(event -> {
        	listView.getItems().add(othersMessages.getValue());
        });

        Task<HBox> yourMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                Image image = iv_head.getImage();
                ImageView profileImage = new ImageView(image);
                profileImage.setFitHeight(32);
                profileImage.setFitWidth(32);

                BubbledLabel bl6 = new BubbledLabel();
                bl6.setText(msg.getMsg());
                bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,
                        null, null)));
                HBox x = new HBox();
//                x.setMaxWidth(listView.getWidth() - 20);
                x.setAlignment(Pos.CENTER_RIGHT);
                x.setSpacing(10);
                bl6.setBubbleSpec(BubbleSpec.FACE_RIGHT_CENTER);
                x.getChildren().addAll(bl6, profileImage);
                return x;
            }
        };
        yourMessages.setOnSucceeded(event -> listView.getItems().add(yourMessages.getValue()));
        if (msg.getUsername().equals("李涛江")) {
            Thread t2 = new Thread(yourMessages);
            t2.setDaemon(true);
            t2.start();
        } else {
            Thread t = new Thread(othersMessages);
            t.setDaemon(true);
            t.start();
        }
    }
}
