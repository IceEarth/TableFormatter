package tableformatter;

import java.util.ArrayList;
import java.util.List;

public class TableFormatter {
    private final String spacer;
    private final String columnLeftBracket;
    private final String columnRightBracket;
    private final char headerValuesSpacerChar;
    private final char lineValuesSpaceChar;
    private ColumnEntry[] entries;

    public TableFormatter(String spacer, String columnLeftBracket, String columnRightBracket, char headerValuesSpacerChar, char lineValuesSpaceChar) {
        this.spacer = spacer;
        this.columnLeftBracket = columnLeftBracket;
        this.columnRightBracket = columnRightBracket;
        this.headerValuesSpacerChar = headerValuesSpacerChar;
        this.lineValuesSpaceChar = lineValuesSpaceChar;
    }

    public TableFormatter() {
        this.spacer = " | ";
        this.columnLeftBracket = "| ";
        this.columnRightBracket = " |";
        this.headerValuesSpacerChar = '*';
        this.lineValuesSpaceChar = '—';
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
            entry.setEntry(this.columnLeftBracket + firstElement, 0);
            String lastElement = entry.get(entry.getSize() - 1);
            entry.setEntry(lastElement + this.columnRightBracket, entry.getSize() - 1);

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
        builder.append(String.valueOf(this.lineValuesSpaceChar).repeat(longestLength));
        builder.append("\n");
        builder.append(entries.get(0).toString());
        builder.append("\n");
        builder.append(String.valueOf(this.headerValuesSpacerChar).repeat(longestLength));
        builder.append("\n");
        for (int i = 1; i < entries.size(); i++) {
            builder.append(entries.get(i).toString());
            builder.append("\n");
        }
        builder.append(String.valueOf(this.lineValuesSpaceChar).repeat(longestLength));

        return builder.toString();
    }


}
