package com.ui.chatwindow;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.ui.resource.R;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class ChatWnd extends Application {
	public static final String icon = "http://121.42.15.212/head.jpg";
	public static Stage stage;// 窗体对象
	public static double screenWidth;
	public static double screenHeight;
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			// 获取屏幕的宽高
			Rectangle2D primaryScreenBounds  = Screen.getPrimary().getVisualBounds();
			screenWidth =primaryScreenBounds.getWidth();
			screenHeight = primaryScreenBounds.getHeight();
			Parent root = FXMLLoader.load(getClass().getResource("ChatWnd.fxml"));
			Scene scene = new Scene(root,850,600);
			primaryStage.getIcons().add(new Image(R.image_url_icon));
			primaryStage.setTitle("CC,聊一聊");
			scene.getStylesheets().add(getClass().getResource("chatStyle.css").toExternalForm());
			// 系统托盘
			if(SystemTray.isSupported()){
				SystemTray tray = SystemTray.getSystemTray();
				BufferedImage image = ImageIO.read(ChatWnd.class.getResourceAsStream(R.image_url_small_icon));
				System.out.println(image);
				PopupMenu popup = new PopupMenu();
				MenuItem item = new MenuItem("退出CC");
				popup.add(item);
				TrayIcon trayIcon = new TrayIcon(image,"CC",popup);
	            ActionListener listener = new ActionListener() {
	                @Override
	                public void actionPerformed(java.awt.event.ActionEvent arg0) {
	                    System.exit(0);
	                }
	            };
	            ActionListener listenerTray = new ActionListener() {
	                @Override
	                public void actionPerformed(java.awt.event.ActionEvent arg0) {
	                	Platform.runLater(new Runnable() {

							@Override
							public void run() {
								primaryStage.hide();
							}
						});
	                }
	            };
	            MouseListener mouseListenerTray = new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mousePressed(MouseEvent e) {

					}

					@Override
					public void mouseExited(MouseEvent e) {

					}

					@Override
					public void mouseEntered(MouseEvent e) {

					}
					@Override
					public void mouseClicked(MouseEvent e) {
	                	Platform.runLater(new Runnable() {

							@Override
							public void run() {
								if(e.getButton() == 1){
									System.out.println(primaryStage.isIconified());
									System.out.println(primaryStage.getX() + " "+primaryStage.getY());
									if(primaryStage.isIconified()){
										primaryStage.setIconified(false);
										primaryStage.centerOnScreen();
										primaryStage.show();
									}else{
										primaryStage.setIconified(true);
										primaryStage.hide();
									}
									System.out.println(primaryStage.getWidth() + " "+primaryStage.getHeight());
								}
							}
						});
					}
				};
	            trayIcon.addMouseListener(mouseListenerTray);
	            trayIcon.addActionListener(listenerTray);
	            item.addActionListener(listener);
	            try {
					tray.add(trayIcon);
				} catch (Exception e) {
					System.err.println("不支持添加系统托盘");
				}
			}
			Platform.setImplicitExit(false);
			primaryStage.setResizable(true);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
