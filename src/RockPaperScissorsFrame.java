import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame
{

    JPanel mainPnl;
    JPanel buttonPnl;
    JPanel statPnl;
    JPanel displayPnl;

    JButton quitBtn;
    JButton rockBtn;
    JButton paperBtn;
    JButton scissorsBtn;

    JLabel computerWinLabel;
    JLabel playerWinLabel;
    JLabel tieLabel;

    String gameResult;

    JTextArea result;

    JTextField loseTxt;
    JTextField winTxt;
    JTextField tieTxt;

    JScrollPane scroller;

    ImageIcon rock;
    ImageIcon paper;
    ImageIcon scissors;

    int playerWin = 0;
    int computerWin = 0;
    int tie = 0;

    //Setup for the JFrame
    public RockPaperScissorsFrame()
    {
        //Setting up the frame size based on the size of the computer the user is using
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.75);
        int height = (int) (screenSize.height * 0.75);
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        mainPnl.setBackground(Color.BLACK);
        add(mainPnl);

        //adding the displays
        createDisplayPanel();
        createButtonPanel();
        createStatPanel();
        createCommandPanel();

        setVisible(true);
    }

    //Creating a panel that shows the number of wins, ties, and losses
    public void createStatPanel()
    {
        statPnl = new JPanel(new GridLayout(3, 2, 10, 10));

        playerWinLabel = new JLabel("Player Wins: ");
        winTxt = new JTextField(" " + playerWin + " ", 3);
        playerWinLabel.setFont(new Font("Pacifico", Font.ITALIC, 18));
        winTxt.setFont(new Font("Pacifico", Font.ITALIC, 18));
        winTxt.setEditable(false);
        winTxt.setToolTipText("Number of wins by the player");

        computerWinLabel = new JLabel("Computer Wins: ");
        loseTxt = new JTextField(" " + computerWin + " ", 3);
        computerWinLabel.setFont(new Font("Pacifico", Font.ITALIC, 18));
        loseTxt.setFont(new Font("Pacifico", Font.ITALIC, 18));
        loseTxt.setEditable(false);
        loseTxt.setToolTipText("Number of wins by the computer");

        tieLabel = new JLabel("Ties: ");
        tieTxt = new JTextField(" " + tie + " ", 3);
        tieLabel.setFont(new Font("Pacifico", Font.ITALIC, 18));
        tieTxt.setFont(new Font("Pacifico", Font.ITALIC, 18));
        tieTxt.setEditable(false);
        tieTxt.setToolTipText("Number of ties");

        statPnl.add(playerWinLabel);
        statPnl.add(winTxt);
        statPnl.add(computerWinLabel);
        statPnl.add(loseTxt);
        statPnl.add(tieLabel);
        statPnl.add(tieTxt);

        mainPnl.add(statPnl, BorderLayout.NORTH);
    }


    //creating the actual buttons
    //the imageicon have the same height and same format so probably can shorten it later if needed
    private void createButtonPanel()
    {
        paper = new ImageIcon(new ImageIcon("src/Paper.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        rock = new ImageIcon(new ImageIcon("src/Rock.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        scissors = new ImageIcon(new ImageIcon("src/Scissor.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));

        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1, 2));

        paperBtn = new JButton(paper);
        rockBtn = new JButton(rock);
        scissorsBtn = new JButton(scissors);

        paperBtn.addActionListener
                (
                        (ActionEvent ae) ->
                        {
                            result.append(computerChoice(1) + "\n");
                        }
                );

        rockBtn.addActionListener
                (
                        (ActionEvent ae) ->
                        {
                            result.append(computerChoice(0) + "\n");
                        }
                );

        scissorsBtn.addActionListener
                (
                        (ActionEvent ae) ->
                        {
                            result.append(computerChoice(2) + "\n");
                        }
                );

        buttonPnl.add(paperBtn);
        buttonPnl.add(rockBtn);
        buttonPnl.add(scissorsBtn);

        mainPnl.add(BorderLayout.CENTER, buttonPnl);
    }

    
   private String computerChoice(int playerChoice)
   {
       Random rnd = new Random();
       int computerChoice = rnd.nextInt(3);
       String[] results = {
               "Tie!", "Paper beats Rock! You lose!", "Rock beats Scissors! You win!",
               "Scissors beats Paper! You lose!", "Paper beats Rock! You win!",
               "Rock beats Scissors! You lose!", "Scissors beats Paper! You win!"
       };

       if (computerChoice == playerChoice) {
           gameResult = results[0];
           tie++;
           tieTxt.setText(tie + "");
       } else {
           int resultIndex = playerChoice * 2 + (computerChoice > playerChoice ? 1 : 2);
           gameResult = results[resultIndex];
           if (gameResult.contains("win")) {
               playerWin++;
               winTxt.setText(playerWin + "");
           } else {
               computerWin++;
               loseTxt.setText(computerWin + "");
           }
       }
       return gameResult;
   }


    private void createDisplayPanel()
    {
        displayPnl = new JPanel();
        result = new JTextArea(10, 20);
        scroller = new JScrollPane(result);
        result.setFont(new Font("Pacifico", Font.ITALIC, 30));

        displayPnl.add(scroller);
        mainPnl.add(displayPnl, BorderLayout.EAST);
    }

    public void createCommandPanel()
    {
        buttonPnl = new JPanel();
        buttonPnl.setLayout(new GridLayout(1, 2));

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Pacifico", Font.ITALIC, 18));
        quitBtn.setBackground(Color.YELLOW);
        quitBtn.setForeground(Color.BLACK);
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        buttonPnl.add(quitBtn);
        mainPnl.add(buttonPnl, BorderLayout.SOUTH);
    }
}