# TableFormatter
**Hey**, 
this is a libary to get the format for a table in a string: e.g.

```
—————————————————————————
|  name  | age | weight |
*************************
| James  | 21  |   75   |
| Robert | 30  |   84   |
—————————————————————————
```
                
 
You are also able to edit the spacer, leftBracket, the tightBracket, the headerLineChar and the lineSpaceChar.
If you are wondering what that could be, here is a short explaination:

![](https://gcdnb.pbrd.co/images/vE0IKLpQrn0D.jpg?o=1)


Here ary some examples how to use it:

#### 1.

```java
Table table = new Table(
                new ColumnEntry("name", "age", "weight"),
                new ColumnEntry("James", "21", "75"),
                new ColumnEntry("Robert", "30", "84"));
        TableFormatter formatter = new TableFormatter();

        System.out.println(formatter.format(table));
```

####2.
If you want to edit the table-structure (like I already said), you need to fill the values in the constructor of the TableFormatter:

This is the constructor:
```java
public TableFormatter(String spacer, String columnLeftBracket, String columnRightBracket, char headerValuesSpacerChar, char lineValuesSpaceChar)
```

Here is an example:
```java
Table table = new Table(
                new ColumnEntry("name", "age", "weight"),
                new ColumnEntry("James", "21", "75"),
                new ColumnEntry("Robert", "30", "84"));
        TableFormatter formatter = new TableFormatter(" | ", "[ ", " ]", '+', '-');

        System.out.println(formatter.format(table));

        /* output:

                -------------------------
                [  name  | age | weight ]
                +++++++++++++++++++++++++
                [ James  | 21  |   75   ]
                [ Robert | 30  |   84   ]
                -------------------------

         */
```

I hope my TableFormatter is useful to you! It would make me really happy and motivate me if you give my project a star...

You are allowed to fork my project as well!

Enjoy! ;)
