# TableFormatter
**Hey**, 
this is a library to format _tables_. 

What are you able to do with this lib?
* **format table to image**
* **format table to String** 

## Format to Image:

### Code:

```java
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

//do something with the image

```

### Image Output:

![](https://s20.directupload.net/images/220306/xijp7tk7.png)



## Format to String:



### 1. Normal Format


### Code:

```java
Table table = new Table(
                new ColumnEntry("name", "age", "weight"),
                new ColumnEntry("James", "21", "75"),
                new ColumnEntry("Robert", "30", "84"));
TableFormatter formatter = new TableFormatter();

System.out.println(formatter.format(table));
```

### Output:
```
—————————————————————————
|  name  | age | weight |
*************************
| James  | 21  |   75   |
| Robert | 30  |   84   |
—————————————————————————
```

                
## 2. Adjusted Format
You are also able to edit the spacer, leftBracket, the tightBracket, the headerLineChar and the lineSpaceChar.
If you are wondering what that could be, here is a short explaination:

![](https://gcdnb.pbrd.co/images/vE0IKLpQrn0D.jpg?o=1)





### Constructor:
```java
public TableFormatter(String spacer, String columnLeftBracket, String columnRightBracket, char headerValuesSpacerChar, char lineValuesSpaceChar)
```

### Code:
```java
Table table = new Table(
                new ColumnEntry("name", "age", "weight"),
                new ColumnEntry("James", "21", "75"),
                new ColumnEntry("Robert", "30", "84"));
TableFormatter formatter = new TableFormatter(" | ", "[ ", " ]", '+', '-');

System.out.println(formatter.format(table));


```

### Output:

![](https://s20.directupload.net/images/220306/wr73kox6.png)




## A few last words:
I hope my TableFormatter is useful to you! It would make me really happy and motivate me if you give my project a star...

You are allowed to fork my project as well.

Enjoy! ;)