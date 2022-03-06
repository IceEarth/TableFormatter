package example;

import tableformatter.ColumnEntry;
import tableformatter.Table;
import tableformatter.TableFormatter;
import tableformatter.TableImageFormat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Example3 {
    public static void main(String[] args) throws IOException {
        Table table = new Table(
                new ColumnEntry("name", "age", "weight"),
                new ColumnEntry("James", "21", "75"),
                new ColumnEntry("Robert", "30", "84"));
        TableFormatter formatter = new TableFormatter();

        BufferedImage img = formatter.formatToImage(table, new TableImageFormat(
                Color.CYAN,
                Color.yellow,
                Color.orange
        ));
        ImageIO.write(img, "png", new File("/home/simon/Schreibtisch/JavaTest/img.png"));
    }
}
