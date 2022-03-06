package tableformatter;

import java.awt.*;

public class TableImageFormat {
    public final Color backgroundColor;
    public final Color textColor;
    public final Color outlineColor;
    public final Color headlineFill;
    public final Color rowFill1;
    public final Color rowFill2;

    public TableImageFormat(Color backgroundColor, Color textColor, Color outlineColor, Color headlineFill, Color rowFill1, Color rowFill2){
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.outlineColor = outlineColor;
        this.headlineFill = headlineFill;
        this.rowFill1 = rowFill1;
        this.rowFill2 = rowFill2;
    }

    public TableImageFormat(Color headlineFill, Color rowFill1, Color rowFill2){
        this.backgroundColor = Color.white;
        this.textColor = Color.black;
        this.outlineColor = Color.black;
        this.headlineFill = headlineFill;
        this.rowFill1 = rowFill1;
        this.rowFill2 = rowFill2;
    }
}
