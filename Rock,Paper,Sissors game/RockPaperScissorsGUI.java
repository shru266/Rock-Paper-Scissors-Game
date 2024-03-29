import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsGUI extends JFrame implements ActionListener {
    private JLabel userChoiceLabel, computerChoiceLabel, resultLabel;
    private JButton rockButton, paperButton, scissorsButton;
    private JPanel panel;

    public RockPaperScissorsGUI() {
        setTitle("Rock Paper Scissors Game");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        userChoiceLabel = new JLabel("Your Choice: ");
        computerChoiceLabel = new JLabel("Computer's Choice: ");
        resultLabel = new JLabel("Result: ");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); 

        rockButton = new JButton("Rock");
        rockButton.addActionListener(this);
        paperButton = new JButton("Paper");
        paperButton.addActionListener(this);
        scissorsButton = new JButton("Scissors");
        scissorsButton.addActionListener(this);

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        panel.add(userChoiceLabel);
        panel.add(buttonPanel);
        panel.add(computerChoiceLabel);
        panel.add(resultLabel);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userChoice = "";
        String computerChoice = "";
        String result = "";

        if (e.getSource() == rockButton) {
            userChoice = "Rock";
        } else if (e.getSource() == paperButton) {
            userChoice = "Paper";
        } else if (e.getSource() == scissorsButton) {
            userChoice = "Scissors";
        }

        Random random = new Random();
        int computerChoiceIndex = random.nextInt(3); 

        switch (computerChoiceIndex) {
            case 0:
                computerChoice = "Rock";
                break;
            case 1:
                computerChoice = "Paper";
                break;
            case 2:
                computerChoice = "Scissors";
                break;
        }

        if (userChoice.equals(computerChoice)) {
            result = "It's a tie!";
        } else if ((userChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (userChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (userChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            result = "You win!";
        } else {
            result = "Computer wins!";
        }

        userChoiceLabel.setText("Your Choice: " + userChoice);
        computerChoiceLabel.setText("Computer's Choice: " + computerChoice);
        resultLabel.setText("Result: " + result);

       
        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> {
                int choice = JOptionPane.showConfirmDialog(this, "Thanks for playing! Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    userChoiceLabel.setText("Your Choice: ");
                    computerChoiceLabel.setText("Computer's Choice: ");
                    resultLabel.setText("Result: ");
                } else {
                    System.exit(0);
                }
            });
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RockPaperScissorsGUI();
            }
        });
    }
}


