package evolution.gui;

import evolution.Animal;
import evolution.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    protected VBox vertical;
    public GuiElementBox(IMapElement element) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(element.getResources()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        vertical= new VBox(imageView);
        if (element.getClass() == Animal.class) {
            Label label = new Label(element.toString());
            vertical.getChildren().addAll(label);
        }
        vertical.setAlignment(Pos.CENTER);
    }

}
