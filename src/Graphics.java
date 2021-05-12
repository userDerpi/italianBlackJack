import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics extends JFrame implements ActionListener {


    private int width = 400;
    private int height = 900;
    private JLabel labelPlayer;
    private JLabel labelComputer;
    private JButton buttonPlayer;
    private JButton buttonComputer;
    private JPanel panelAll;
    private JPanel panelPlayerPC;
    private JPanel panelPlayer;
    private JPanel panelComputer;
    private JPanel panelButton;
    private JButton resetGame;
    private JTextField winner;



    public void setWidth(int width) { this.width = width; }

    public Graphics() {

        ///Creating panels, for formatting graphics
        panelAll = new JPanel();
        panelPlayer = new JPanel();
        panelComputer = new JPanel();
        panelPlayerPC = new JPanel();
        panelButton = new JPanel();

        ///Setting up panels' layout and graphics size
        this.add(panelAll);
        panelAll.setLayout(new BoxLayout(panelAll, BoxLayout.Y_AXIS));
        panelPlayer.setLayout(new FlowLayout());
        panelComputer.setLayout(new FlowLayout());
        panelButton.setLayout(null);
        setSize(width, height);

        //create button "Draw a card"
        buttonPlayer = new JButton("Pesca una carta");
        buttonPlayer.addActionListener(this);
        panelPlayer.add(buttonPlayer);

        ///adding card player
        ImageIcon coverCard = new ImageIcon("CARTE/00.png");
        labelPlayer = new JLabel(coverCard);
        panelPlayer.add(labelPlayer);

        ///create button "Finish the hand"
        buttonComputer = new JButton("Termina la mano");
        buttonComputer.addActionListener(this);
        panelComputer.add(buttonComputer);

        ///adding bot card
        labelComputer = new JLabel(coverCard);
        panelComputer.add(labelComputer);

        ///player and computer panel in panelPlayerPC
        panelPlayerPC.add(panelPlayer);
        panelPlayerPC.add(panelComputer);


        //create textfield that say the winner of the match
        winner = new JTextField(20);
        winner.setBounds(100,180,180,25);
        winner.setEditable(false);
        panelButton.add(winner);

        //button that reset hands (doesn't work)
        resetGame= new JButton("Resetta la partita");
        resetGame.setEnabled(false);
        resetGame.addActionListener(this);
        resetGame.setBounds(100, 200,180,25);
        panelButton.add(resetGame);

        //adding all panels in panelAll
        panelAll.add(panelPlayerPC);
        panelAll.add(panelButton);

    }


    private Player computer = new Player();
    private Player person = new Player();


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "Pesca una carta":
                if (person.isCoveredCard()) {
                    labelPlayer.setIcon(new ImageIcon(person.drawACard()));
                    person.setCover(false);
                } else {
                    panelPlayer.add(new JLabel(new ImageIcon(person.drawACard())));
                }
                if (person.over7andhalf()) {
                    buttonPlayer.setEnabled(false);
                    buttonComputer.setEnabled(false);
                    resetGame.setEnabled(true);
                    winner.setText("Vincitore: il bot");
                }

                break;
            case "Termina la mano":
                resetGame.setEnabled(true);
                buttonPlayer.setEnabled(false);
                buttonComputer.setEnabled(false);
                while (computer.getPoints() <= person.getPoints()) {
                    if (computer.isCoveredCard()) {
                        labelComputer.setIcon(new ImageIcon(computer.drawACard()));
                        computer.setCover(false);
                    } else if (computer.getPoints() <= person.getPoints()) {
                        panelComputer.add(new JLabel(new ImageIcon(computer.drawACard())));
                    }
                }

                winner.setText("Vincitore: " + person.comparePoint(computer));

                break;
            case "Resetta la partita":
                person.getHand().clear();
                computer.getHand().clear();
                break;
        }

        //update window
        if (computer.getHand().size() > person.getHand().size() || person.getHand().size() > computer.getHand().size()) {
            setWidth(width + 200);
            setSize(width, height);
        }

    }

}
