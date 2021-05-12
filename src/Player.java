import javax.swing.*;
import java.util.ArrayList;

public class Player {



    private ArrayList<Integer> hand = new ArrayList<>();
    private double points = 0;
    private boolean coveredCard = true;

    public ArrayList<Integer> getHand() { return hand; }

    public boolean isCoveredCard() { return coveredCard;}

    public void setCover(boolean coverCard){this.coveredCard = coverCard;}

    public double getPoints() { return points; }

    public void addPoints(double points){this.points += points;}

    public String drawACard(){
        int card = customRandom.random(1,10);
        hand.add(card);
        addPoints(card > 7 ? 0.5 : card);
        return "CARTE/" + card +".jpg";
    }

    public boolean over7andhalf(){
        return this.points > 7.5;
    }

    public String comparePoint(Player enemy){
        return this.points > enemy.getPoints() ? "L'utente" : "Il bot";
    }

}
