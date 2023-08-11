import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MyGame extends JFrame implements ActionListener {

    JLabel heading, clockLabel;
    JPanel mainPanel;
    JButton []btns =new JButton[9];
    Font font=new Font("",Font.BOLD,40);


    int gameChance[] ={2,2,2,2,2,2,2,2,2};
    int activePlayer=0;

    int wps[][]={
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };

    int winner=2;

    boolean gameOver=false;
    public MyGame(){
        super.setTitle("TICTACTOE Game");
        super.setSize(850,850);
//        super.setLocation(300,500);
        ImageIcon icon= new ImageIcon("src/ticIcon.jpg");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);

    }

    private void createGUI(){

        this.getContentPane().setBackground(Color.decode("#2196f3"));
        this.setLayout(new BorderLayout());

        //north heading

        heading = new JLabel("TIC TAC TOE");
        heading.setIcon(new ImageIcon("src/icon.jpg"));
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);

        this.add(heading, BorderLayout.NORTH);


        //creating object JLabel for Clock

        clockLabel =new JLabel("Clock");
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clockLabel.setForeground(Color.white);
        this.add(clockLabel,BorderLayout.SOUTH);

        Thread t=new Thread(){
            public void run(){
                try {
                    while(true){
                        String datetiime =new Date().toLocaleString();

                        clockLabel.setText(datetiime);

                        Thread.sleep(1000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

            t.start();



            //Starting Panel Section

        mainPanel =new JPanel();

        mainPanel.setLayout(new GridLayout(3,3));

        for (int i=1;i<=9;i++){
            JButton btn=new JButton();
//            btn.setIcon(new ImageIcon("src/o.jpg"));
            btn.setBackground(Color.decode("#90caf9"));
            btn.setFont(font);
            mainPanel.add(btn);
            btns[i-1]=btn;
            btn.addActionListener(this);
            btn.setName((String.valueOf(i-1)));

        }

        this.add(mainPanel,BorderLayout.CENTER);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton curr=(JButton) e.getSource();

        String name=curr.getName();

        int name1= Integer.parseInt(name.trim());

        if(gameOver){
            JOptionPane.showMessageDialog(this,"Game Over");
            return;
        }

        if(gameChance[name1]==2){

            if(activePlayer==1){
                curr.setIcon(new ImageIcon("src/x.jpg"));

                gameChance[name1]=activePlayer;
                activePlayer= 0;
            }
            else{
                curr.setIcon(new ImageIcon("src/o.jpg"));
                gameChance[name1]=activePlayer;
                activePlayer=1;
            }



            //find the winner

            for (int []temp:wps){
                if((gameChance[temp[0]]==gameChance[temp[1]]) && (gameChance[temp[1]]==gameChance[temp[2]]) && gameChance[temp[2]]!=2){
                    winner=gameChance[temp[0]];
                    gameOver=true;
                    JOptionPane.showMessageDialog(null,"Player "+winner +" won the game..." );
                    int i=JOptionPane.showConfirmDialog(null,"Wanna Play Again");
                    if(i==0){
                        this.setVisible(false);
                        new MyGame();
                    }
                    else if(i==1){
                        System.exit(22);
                    }
                    else{

                    }
                    break;
                }
            }

            //draw logic
            int count=0;
            for (int x:gameChance){
                if(x==2){
                    count++;
                            break;
                }
            }

            if(count==0 && gameOver==false){
                JOptionPane.showMessageDialog(null,"Game Draw");
                int j=JOptionPane.showConfirmDialog(null,"wanna Play again..?");
                if(j==0){
                    this.setVisible(false);
                    new MyGame();
                }
                else if(j==1){
                    System.exit(333);
                }
                else{

                }
                gameOver=true;

            }
        }
        else{
            JOptionPane.showMessageDialog(this,"Position already Occupied");
         }
    }
}
