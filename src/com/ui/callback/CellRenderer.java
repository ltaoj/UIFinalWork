package com.ui.callback;

import com.ui.messages.User;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * A Class for Rendering users images / name on the userlist.
 */
public class CellRenderer implements Callback<ListView<User>,ListCell<User>>{
        @Override
    public ListCell<User> call(ListView<User> p) {

        ListCell<User> cell = new ListCell<User>(){

            @Override
            protected void updateItem(User user, boolean bln) {
                super.updateItem(user, bln);
                setGraphic(null);
                setText(null);
                if (user != null) {
                    HBox hBox = new HBox();

                    Text name = new Text(user.getUsername());
                    name.setFill(Paint.valueOf("#000000"));
                    name.setFont(Font.font(15));
                    ImageView pictureImageView = new ImageView();
                    Image image = new Image(getClass().getClassLoader().getResource("res/" + user.getPicture() + ".jpg").toString(),30,30,true,true);
                    pictureImageView.setImage(image);
                    hBox.setSpacing(10);
                    Text date = new Text(user.getDate());
                    HBox vbox_date = new HBox();
                    vbox_date.setOpacity(0.5);
                    vbox_date.setAlignment(Pos.CENTER_RIGHT);vbox_date.getChildren().add(date);
                    hBox.getChildren().addAll(pictureImageView, name,vbox_date);
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    hBox.setPrefHeight(50);
                    HBox.setHgrow(vbox_date, Priority.ALWAYS);

                    setGraphic(hBox);
                }
            }
        };
        return cell;
    }
}