package tableformatter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TableFormatter {
    private final String spacer;
    private final String leftBracket;
    private final String rightBracket;
    private final char headerLineChar;
    private final char lineChar;


    public TableFormatter(String spacer, String columnLeftBracket, String columnRightBracket, char headerValuesSpacerChar, char lineValuesSpaceChar) {
        this.spacer = spacer;
        this.leftBracket = columnLeftBracket;
        this.rightBracket = columnRightBracket;
        this.headerLineChar = headerValuesSpacerChar;
        this.lineChar = lineValuesSpaceChar;
    }

    public TableFormatter() {
        this.spacer = " | ";
        this.leftBracket = "| ";
        this.rightBracket = " |";
        this.headerLineChar = '*';
        this.lineChar = '—';
       /* Example:
         lineValuesSpaceChar -->    —————————————————————————
                                    |  name  | age | weight | <-- columnRightBracket
                                    ************************* <-- headerValuesSpacerChar
                                    | James  | 21  |   75   |
                                    | Robert | 30  |   84   |
                                    —————————————————————————

         */
    }

    public String format(Table table){
        table.setEntries(Table.getEntriesHorizontal(table));
        table.setEntries(adjustLength(table.getEntries()));
        table.setEntries(Table.getEntriesVertical(table));
        table.setEntries(insertSpacersAndBrackets(table.getEntries()));

        return getFormattedTable(table);
    }

    public BufferedImage formatToImage(Table table, TableImageFormat tableImageFormat){


        table.setEntries(Table.getEntriesHorizontal(table));

        return getTableImage(table, tableImageFormat);
    }

    private BufferedImage getTableImage(Table table, TableImageFormat tableImageFormat){
        int spacerLength = 8;
        int x = 0;
        int y = 0;
        int cursorX = 0;
        int cursorY = 0;
        int line = 0;



        BufferedImage dummy = new BufferedImage(1000, 1000, 1);
        Graphics2D graphics2D = dummy.createGraphics();

        //BufferedImage result = new BufferedImage(getTableWidth(table.getEntries(), spacerLength), table.getEntries().size() * 16, 1);
        BufferedImage result = new BufferedImage(
                getTableWidth(table.getEntries(), spacerLength, graphics2D.getFontMetrics()),
                table.getEntries().size() * 16, 1);

        Graphics2D g2D = result.createGraphics();
        FontMetrics fontMetrics = g2D.getFontMetrics();
        g2D.setPaint(tableImageFormat.backgroundColor);
        g2D.fillRect(0, 0, result.getWidth(), result.getHeight());


        int counter = 0;

        for(ColumnEntry entry : table.getEntries()){
            int longestLength  = fontMetrics.stringWidth(getLongestString(entry, g2D.getFontMetrics()));
            int rectWidth = getRectWidth(longestLength, spacerLength);


            for (int i = 0; i < entry.getSize(); i++){
                String s = entry.get(i);

                g2D.setPaint(getRectColor(tableImageFormat, i));
                g2D.fillRect(x, y, rectWidth, 16);

                g2D.setPaint(tableImageFormat.outlineColor);
                g2D.drawRect(x, y, rectWidth, 16);

                y += 16;

                cursorY = line + 13;
                cursorX = x + (rectWidth - fontMetrics.stringWidth(s)) / 2;

                g2D.setPaint(tableImageFormat.textColor);
                g2D.drawString(s, cursorX, cursorY);

                line += 16;
            }
            x += rectWidth;
            y = 0;
            cursorX = x;
            line = 0;
            counter++;
        }

        g2D.dispose();
        return result;
    }

    private Color getRectColor(TableImageFormat format, int counter){
        if (counter == 0) {
            return format.headlineFill;
        }else if(counter == 1){
            return format.rowFill1;
        }else if(counter % 2 == 0){
            return format.rowFill2;
        }else {
            return format.rowFill1;
        }
    }

    private int getRectWidth(int stringLength, int spacerLength){
        return spacerLength + stringLength;
    }

    private int getTableWidth(List<ColumnEntry> entries, int spacerLength, FontMetrics metrics){
        int xResult = 0;
        for(ColumnEntry entry : entries){
            int longestLength  = metrics.stringWidth(getLongestString(entry, metrics));
            int rectWidth = getRectWidth(longestLength, spacerLength);
            xResult += rectWidth;
        }

        return xResult;

    }

    private String getLongestString(ColumnEntry entry, FontMetrics metrics){
        String result = "";
        for(String s : entry.getEntries()){
            if(metrics.stringWidth(result) < metrics.stringWidth(s)){
                result = s;
            }
        }
        return result;
    }

    private String[] adjustLength(String ... strings){
        int longestLength = 0;
        for(String s : strings){
            longestLength = Math.max(longestLength, s.length());
        }

        for(int i = 0; i < strings.length; i++){
            var entry = strings[i];

            boolean frontOrBack = false;

            while(entry.length() < longestLength){
                if(!frontOrBack){
                    entry += " ";
                    frontOrBack = true;
                }else {
                    entry = " " + entry;
                    frontOrBack = false;
                }
            }
            strings[i] = entry;
        }
        return strings;
    }

    private List<ColumnEntry> adjustLength(List<ColumnEntry> entries){
        List<ColumnEntry> result = new ArrayList<>();
        for (ColumnEntry entry : entries) {
            result.add(new ColumnEntry(adjustLength(entry.getEntries())));
        }
        return result;
    }

    private List<ColumnEntry> insertSpacersAndBrackets(List<ColumnEntry> entries){

        for(ColumnEntry entry : entries){
            String firstElement = entry.get(0);
            entry.setEntry(this.leftBracket + firstElement, 0);
            String lastElement = entry.get(entry.getSize() - 1);
            entry.setEntry(lastElement + this.rightBracket, entry.getSize() - 1);

            for (int i = 0; i < entry.getSize() - 1; i++) {
                entry.setEntry(entry.get(i) + this.spacer, i);
            }
        }
        return entries;
    }

    private String getFormattedTable(Table table){
        StringBuilder builder = new StringBuilder();
        List<ColumnEntry> entries = table.getEntries();
        int longestLength = table.getLongestCharLength();
        builder.append(String.valueOf(this.lineChar).repeat(longestLength));
        builder.append("\n");
        builder.append(entries.get(0).toString());
        builder.append("\n");
        builder.append(String.valueOf(this.headerLineChar).repeat(longestLength));
        builder.append("\n");
        for (int i = 1; i < entries.size(); i++) {
            builder.append(entries.get(i).toString());
            builder.append("\n");
        }
        builder.append(String.valueOf(this.lineChar).repeat(longestLength));

        return builder.toString();
    }


}
