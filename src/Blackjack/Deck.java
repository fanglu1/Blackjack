package Blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;

    public Deck(){
        this.cards = new ArrayList<Card>();
    }

    public void createFullDeck() {

        for(Suit cardSuit: Suit.values()) {
            for(Value cardValue : Value.values()) {
                this.cards.add(new Card(cardSuit, cardValue));

            }
        }

    }

    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for(int i = 0; i < originalSize; i++){
            randomCardIndex = random.nextInt((this.cards.size() - 1) + 1);
            tempDeck.add(this.cards.get(randomCardIndex));
            this.cards.remove(randomCardIndex);
        }
        this.cards = tempDeck;
    }

    public String toString(){
        String cardListOutput = "";
        for( Card aCard : this.cards){
            cardListOutput += "\n " + aCard.toString();
        }
        return cardListOutput;
    }

    public void removeCard(int i) {
        this.cards.remove(i);
    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    public void draw(Deck comingFrom){
        this.cards.add(comingFrom.getCard(0));
        comingFrom.cards.remove(0);
    }

    public int deckSize(){
        return this.cards.size();
    }
    public void moveToDeck(Deck moveTo){
        int thisDeckSize = this.cards.size();
        for(int i = 0; i < thisDeckSize; i++){
            moveTo.addCard(this.getCard(i));
        }
        for(int i = 0; i < thisDeckSize; i++){
            this.removeCard(0);
        }
    }
    public int cardsValue(){
        int totalValue = 0;
        int aces = 0;

        for(Card aCard : this.cards){
            switch (aCard.getValue()) {
                case TWO -> totalValue += 2;
                case THREE -> totalValue += 3;
                case FOUR -> totalValue += 4;
                case FIVE -> totalValue += 5;
                case SIX -> totalValue += 6;
                case SEVEN -> totalValue += 7;
                case EIGHT -> totalValue += 8;
                case NINE -> totalValue += 9;
                case TEN -> totalValue += 10;
                case JACK -> totalValue += 10;
                case QUEEN -> totalValue += 10;
                case KING -> totalValue += 10;
                case ACE -> totalValue += 1;
            }
        }
        for (int i = 0; i < aces; i++){
            if (totalValue > 10) {
                totalValue +=1;
            }
            else{
                totalValue += 11;
            }
        }

        return totalValue;
    }

}
