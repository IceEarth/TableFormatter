package tableformatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Table {
    private List<ColumnEntry> entries;
    private Integer numberOfRows;
    private boolean entriesVertical = true;

    public Table(List<ColumnEntry> entries) {
        this.entries = entries;
        this.numberOfRows = this.entries.get(0).getSize();
    }
    public Table(ColumnEntry headRow, List<ColumnEntry> entries) {
        entries.add(0, headRow);
        this.entries = entries;
        this.numberOfRows = this.entries.get(0).getSize();
    }

    public Table(ColumnEntry ... entries){
        this.entries = Arrays.stream(entries).toList();
        this.numberOfRows = this.entries.get(0).getSize();
    }

    public List<ColumnEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ColumnEntry> entries){
        this.entries = entries;
        this.numberOfRows = entries.get(0).getSize();
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public Integer getLongestCharLength(){
        int longestLength = 0;
        for(ColumnEntry entry : entries){
            longestLength = Math.max(longestLength, entry.getCharLength());
        }
        return longestLength;
    }

    static List<ColumnEntry> getEntriesHorizontal(Table table) {
        List<ColumnEntry> result = new ArrayList<>();
        for (int i = 0; i < table.getNumberOfRows(); i++) {
            ColumnEntry entry = new ColumnEntry();
            for (int j = 0; j < table.entries.size(); j++) {
                entry.add(table.entries.get(j).get(i));
            }
            result.add(entry);
        }
        return result;
    }

    static List<ColumnEntry> getEntriesVertical(Table table) {
        List<ColumnEntry> result = new ArrayList<>();
        for (int i = 0; i < table.getNumberOfRows(); i++) {
            ColumnEntry entry = new ColumnEntry();
            for (int j = 0; j < table.entries.size(); j++) {
                entry.add(table.entries.get(j).get(i));
            }
            result.add(entry);
        }
        return result;
    }






}
