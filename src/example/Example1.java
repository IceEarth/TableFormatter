package example;

import tableformatter.ColumnEntry;
import tableformatter.Table;
import tableformatter.TableFormatter;

public class Example1 {
    public static void main(String[] args) {
        Table table = new Table(
                new ColumnEntry("name", "age", "weight"),
                new ColumnEntry("James", "21", "75"),
                new ColumnEntry("Robert", "30", "84"));
        TableFormatter formatter = new TableFormatter();

        System.out.println(formatter.format(table));

        /* output:

                —————————————————————————
                |  name  | age | weight |
                *************************
                | James  | 21  |   75   |
                | Robert | 30  |   84   |
                —————————————————————————

         */
    }
}
