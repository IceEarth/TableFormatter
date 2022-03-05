package tableformatter;

import java.util.ArrayList;
import java.util.Arrays;

public final class ColumnEntry {
    private ArrayList<String> entries;

    public ColumnEntry(String... entries) {
        this.entries = new ArrayList<>(Arrays.stream(entries).toList());
    }

    public int getSize() {
        return entries.size();
    }

    public String get(int index) {
        return entries.get(index);
    }

    public void add(String string) {
        entries.add(string);
    }

    public String[] getEntries() {
        return entries.toArray(String[]::new);
    }

    public void setEntry(String string, int index){
        entries.set(index, string);
    }

    public void setEntries(String ... entries){
        this.entries = new ArrayList<>(Arrays.stream(entries).toList());
    }

    public int getCharLength(){
        return String.join("", entries).length();
    }

    @Override
    public String toString(){
        return String.join("", entries);
    }




}
