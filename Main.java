import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("Tabbed Pane");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1150,760);
        f.setLocationRelativeTo(null);
        f.setResizable(false);

        JTabbedPane tp = new JTabbedPane();
        JPanel p1 = new JPanel(); p1.setBackground(Color.cyan);
        tp.addTab("Clock",p1);

        //current date and time
        Font font = new Font(Font.SERIF,1,48);
        JLabel date = new JLabel();
        date.setFont(font);
        date.setForeground(Color.red);
        date.setText(""+LocalDate.now());
        p1.add(date);

        JLabel time = new JLabel();
        time.setFont(font);
        time.setForeground(Color.red);
        time.setText(""+LocalTime.now().getHour()+" : "+ LocalTime.now().getMinute()+" : "+ LocalTime.now().getSecond());
        p1.add(time);

        p1.setLayout(null);
        date.setBounds(100,100,300,80);
        time.setBounds(100,250,300,80);

        Thread t = new Thread(
            ()->{
                while(true){
                    try{Thread.sleep(1000);}
                    catch(InterruptedException e){}
                    time.setText(""+LocalTime.now().getHour()+" : "+ LocalTime.now().getMinute()+" : "+ LocalTime.now().getSecond());
                }
            }
        );
        t.start();

        JPanel p2 = new JPanel(); p2.setBackground(Color.cyan);
        tp.addTab("Stop Watch",p2);

        JPanel p3 = new JPanel(); p3.setBackground(Color.cyan);
        tp.addTab("World Clock",p3);

        class MyListener implements ChangeListener{
            public void stateChanged(ChangeEvent e) {
                if(tp.getSelectedIndex()==0){
                    System.out.println("first");
                }
                else if(tp.getSelectedIndex()==1){
                    StopwatchPanel stopwatchPanel = new StopwatchPanel();
                    tp.setComponentAt(1, stopwatchPanel);
                }
                else if(tp.getSelectedIndex()==2){
                    p3.setSize(1150,760);
                    JLabel l = new JLabel("Location of Mouse",JLabel.CENTER);
                    p3.add(l,BorderLayout.NORTH);
                    l.setFont(new Font(Font.SERIF,1,36));

                    JLabel jl = new JLabel(new ImageIcon("map.jpg"));
                    p3.add(jl);

                    class MyListerner extends MouseAdapter{
                    int x;
                    int y;
                    public void mouseMoved(MouseEvent m) {
                x = m.getX();
                y = m.getY();
                l.setText("Mouse Moved: "+x+", "+y);
                if((x>=750 && x<=810) && (y<=480 && y>=400)){
                    l.setText("India");
                }
                else if((x>=150 && x<=300) && (y<=400 && y>=350)){
                    l.setText("New York (USA)");
                }
                else if((x>=900 && x<=1000) && (y<=600 && y>=550)){
                    l.setText("Perth (Australia)");
                }
                else if((x>=800 && x<=900) && (y<=420 && y>=370)){
                    l.setText("Shanghai (China)");
                }else if((x>=144 && x<=228) && (y<=300 && y>=250)){
                    l.setText("Vancouver (Canada)");
                }
            }
            public void mouseClicked(MouseEvent m) {
                if((x>=750 && x<=810) && (y<=480 && y>=400)){
                    //india time zone
                    ZoneId z = ZoneId.of("Asia/Calcutta");
                    LocalTime t = LocalTime.now(z);
                    JOptionPane.showMessageDialog(p3, t.getHour()+":"+t.getMinute()+":"+t.getSecond(),"India Current Time",JOptionPane.INFORMATION_MESSAGE);
                }
                else if((x>=150 && x<=300) && (y<=400 && y>=350)){
                    ZoneId z = ZoneId.of("America/New_York");
                    LocalTime t = LocalTime.now(z);
                    JOptionPane.showMessageDialog(p3, t.getHour()+":"+t.getMinute()+":"+t.getSecond(),"New York Current Time",JOptionPane.INFORMATION_MESSAGE);
                }
                else if((x>=900 && x<=1000) && (y<=600 && y>=550)){
                    ZoneId z = ZoneId.of("Australia/Perth");
                    LocalTime t = LocalTime.now(z);
                    JOptionPane.showMessageDialog(p3, t.getHour()+":"+t.getMinute()+":"+t.getSecond(),"Perth Current Time",JOptionPane.INFORMATION_MESSAGE);
                }
                else if((x>=800 && x<=900) && (y<=420 && y>=370)){
                    ZoneId z = ZoneId.of("Asia/Shanghai");
                    LocalTime t = LocalTime.now(z);
                    JOptionPane.showMessageDialog(p3, t.getHour()+":"+t.getMinute()+":"+t.getSecond(),"Shanghai Current Time",JOptionPane.INFORMATION_MESSAGE);
                }else if((x>=144 && x<=228) && (y<=300 && y>=250)){
                    ZoneId z = ZoneId.of("America/Vancouver");
                    LocalTime t = LocalTime.now(z);
                    JOptionPane.showMessageDialog(p3, t.getHour()+":"+t.getMinute()+":"+t.getSecond(),"Vancouver Current Time",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            public void mouseReleased(MouseEvent m) {
                System.out.println("Mouse Released");
            }
            public void mouseDragged(MouseEvent m) {
                x = m.getX();
                y = m.getY();
                l.setText("Mouse Dragged: "+x+", "+y);
            }
        }

        MyListerner ml = new MyListerner();
        p3.addMouseMotionListener(ml);
        p3.addMouseListener(ml);
                }
                
            }
        }
        MyListener ml = new MyListener();
        tp.addChangeListener(ml);
        f.add(tp);
        f.setVisible(true);

    }
}
