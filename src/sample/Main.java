package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root,512, 256);

        Canvas canvas = new Canvas(512, 256);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image srcImage = new Image(getClass().getResourceAsStream("/images/lenna256px.png"));
        int height = (int) srcImage.getHeight();
        int width = (int) srcImage.getWidth();

        gc.drawImage(srcImage,0,0);
        WritableImage dstImage = new WritableImage(width, height);
        PixelReader reader = srcImage.getPixelReader();
        PixelWriter writer = dstImage.getPixelWriter();

        for (int x=0; x<width;x++) {
            for (int y=0; y<height; y++) {
                Color color = reader.getColor(x,y);
                writer.setColor(x,y, Color.color(color.getRed()/2, color.getGreen()/2, color.getBlue()/2));
            }
        }

        gc.drawImage(dstImage, 256, 0);
        //canvas.setOnMouseClicked(arg0);

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            System.out.println("x= " + mouseEvent.getX() + ", Y= " + mouseEvent.getY());
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
