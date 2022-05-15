package Blackjack;

import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args){
        System.out.println("Welcome to Fang's not rigged Blackjack!!");

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 100;

        Scanner input = new Scanner(System.in);

        while(playerMoney > 0) {
            System.out.println("You have $" + playerMoney + " . What do you wanna risk(bet)?");
            double playerBet = input.nextDouble();
            if(playerBet > playerMoney){
                System.out.println("You are betting more than you have right now.");
                break;
            }

            boolean endRound = false;
            playerDeck.draw(playerDeck);
            playerDeck.draw(playerDeck);

            dealerDeck.draw(dealerDeck);
            dealerDeck.draw(dealerDeck);

            while(true) {
                System.out.println("Your hand: ");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand total is: " + playerDeck.cardsValue());

                System.out.println("The dealer's hand is: " + dealerDeck.getCard(0).toString() + " and [Hidden]");

                System.out.println("What's your move? 1) Hit 2) Stand");
                int move = input.nextInt();

                if (move == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    if(playerDeck.cardsValue()> 21){
                        System.out.println("You busted!!! Card value: " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if(move == 2){
                    break;
                }
            }
            System.out.println("Dealer's hand: " + dealerDeck.toString());
            if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && !endRound){
                System.out.println("House wins! lol definitely not rigged!");
                playerMoney -= playerBet;
                endRound = true;
            }
            while((dealerDeck.cardsValue()) < 17 && !endRound){
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }
            System.out.println("Dealers hand total: " + dealerDeck.cardsValue());
            if((dealerDeck.cardsValue()> 21) && !endRound){
                System.out.println("Dealer messed up, you win!");
                playerMoney += playerBet;
                endRound = true;
            }
            if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && !endRound){
                System.out.println("Push");
                endRound = true;
            }

            if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && !endRound){
                System.out.println("You won!");
                playerMoney += playerBet;
                endRound = true;
            }
            else if(!endRound){
                System.out.println("You lost");
                playerMoney -= playerBet;
                endRound = true;
            }

            playerDeck.moveToDeck(playingDeck);
            dealerDeck.moveToDeck(playingDeck);
            System.out.println("Round ends!");
        }
        System.out.println("You out of cash! Go to the ATM and get some more $$");
    }
}