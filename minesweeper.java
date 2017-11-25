import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class minesweeper_copy extends Applet implements ActionListener//,Runnable
{
Label display,status,hint;
Button restart;
int result;
Button arr[][]=new Button[4][4];
int place[][]=new int[4][4];

int i;
Label l1;
int safe,mines,count;

public void init()
{
setSize(542,400);
setForeground(Color.red);
setLayout(new BorderLayout());
Panel p1=new Panel();
p1.setLayout(new GridLayout(2,1));
p1.setBackground(Color.black);
p1.setForeground(Color.red);
p1.setFont(new Font ("Verdana", Font.BOLD, 20));
l1=new Label("MINESWEEPER GAME",Label.CENTER);
p1.add(l1);
status=new Label("",Label.CENTER);
p1.add(status);
add(p1,"North");
Panel p2=new Panel();
p2.setLayout(new GridLayout(4,4));
p2.setBackground(Color.black);
p2.setForeground(Color.red);
p2.setFont(new Font ("Verdana", Font.BOLD, 20));

for(int i=0;i<4;i++)
{
for(int j=0;j<4;j++)
{
arr[i][j]=new Button("");
arr[i][j].addActionListener(this);
p2.add(arr[i][j]);
}
}
add(p2,"Center");
Panel p3=new Panel();
p3.setLayout(new GridLayout(3,1));
p3.setBackground(Color.lightGray);
p3.setForeground(Color.darkGray);
p3.setFont(new Font ("Verdana", Font.BOLD, 20));
hint=new Label("",Label.CENTER);
p3.add(hint);
display=new Label("",Label.CENTER);
p3.add(display);
restart=new Button("Play Again");
restart.addActionListener(this);
p3.add(restart);
add(p3,"South");
initialize();
}
public String givehint()
{
String ss;
int i,j;
for(i=0;i<4;i++)
{
for(j=0;j<4;j++)
{
if(place[i][j]==1)
{
continue;
}
if(i==0 && j==0)
{
ss="top left corner";
return ss;
}
if(i==0 && j==1)
{
ss="first row second column";
return ss;
}
if(i==0 && j==2)
{
ss="first row third column";
return ss;
}
if(i==0 && j==(4-1))
{
ss="top right corner";
return ss;
}
if(i==(4-1) && j==0)
{
ss="bottom left corner";
return ss;
}
if(i==(4-1) && j==(4-1))
{
ss="bottom right corner";
return ss;
}
}
}
return "..... sorry no hint !";
}

public void generate()
{
int count=0;
int x,i,j,size;
size=arr.length;
for(i=0;i<size;i++)
{
for(j=0;j<size;j++)
{
place[i][j]=0;
}
}

for(i=0;i<size;i++)
{
for(j=0;j<size;j++)
{
x=(int)(2*Math.random());
if(x==1)
{
count++;
if(count>6)
{
count--;
mines=count;
safe=16-mines;
return;
}
place[i][j]=x;
}}}
mines=count;
safe=16-mines;
}
public void initialize()
{
makeactive();
display.setText("");
restart.setVisible(false);
String temp="";
for(int i=0;i<4;i++)
{
for(int j=0;j<4;j++)
{
arr[i][j].setLabel(temp);
temp=temp+' ';

}}
generate();
status.setText("mines - "+mines+"  safe cells - "+safe);
count=0;
hint.setVisible(true);
String s=givehint();
hint.setText("You can start with "+s);
return;
}
public int countmines(int row,int col)
{
int c,i,j,size;
size=place.length;
c=0;
for(i=row-1;i<=row+1;i++)
{
if(i<0 || i ==size)
{
continue;
}
for(j=col-1;j<=col+1;j++)
{
if(j<0 || j ==size)
{
continue;
}
if(place[i][j]==1)
{
c++;
}
}
}
return c;
}
public void makeactive()
{
int i,j,size;
size=place.length;
for(i=0;i<size;i++)
{
for(j=0;j<size;j++)
{

arr[i][j].setEnabled(true);
}
}
}

public void makeinactive(int row,int col)
{
int i,j,size;
size=place.length;
for(i=0;i<size;i++)
{
for(j=0;j<size;j++)
{
if(i==row && j ==col)
{
continue;
}
arr[i][j].setEnabled(false);
}
}
}

public void actionPerformed(ActionEvent ae)
{
String str=ae.getActionCommand();
if(str.equals("Play Again"))
{
restart.setVisible(false);
initialize();
return;
}
hint.setVisible(false);
int x=str.length();
int row=x/4;
int col=x%4;
if(place[row][col]==1)
{
arr[row][col].setLabel("X");
makeinactive(row,col);
display.setText("OOPS ! U STEPPPED ON A MINE ! ...GAME OVER");
restart.setVisible(true);
return;
}
int b=countmines(row,col);
arr[row][col].setLabel(""+b);
arr[row][col].setEnabled(false);
count++;
if(count==safe)
{
display.setText(" GAME OVER ! ....... U WIN  ! ");
restart.setVisible(true);
makeinactive(20,20);
}

}
}