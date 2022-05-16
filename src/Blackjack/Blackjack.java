package Blackjack;

import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        final String ANSI_RED_BACKGROUND = "\u001B[41m";
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        final String ANSI_WHITE = "\u001B[37m";

        System.out.println(ANSI_RED_BACKGROUND + ANSI_BLACK + "Welcome to Fang's not rigged Blackjack!!" + ANSI_RESET);
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();

        double playerMoney = 1000;

        Scanner input = new Scanner(System.in);

        while (playerMoney > 0) {
            System.out.println("You have $" + playerMoney + " . You can only bet by increment of 5s. What do you wanna risk(bet)?");
            double playerBet = input.nextDouble();
            if (playerBet % 5 != 0) {
                System.out.println("Not an increment of 5!");
                break;
            } else if (playerBet > playerMoney) {
                System.out.println("You are betting more than you have right now.");
                break;
            }

            boolean endRound = false;
            playerHand.draw(playingDeck);
            playerHand.draw(playingDeck);

            dealerHand.draw(playingDeck);
            dealerHand.draw(playingDeck);

            while (true) {
                System.out.println("Your hand: ");
                System.out.println(ANSI_RED + playerHand.toString() + ANSI_RESET);
                System.out.println("Your hand total is: " + playerHand.cardsValue());

                System.out.println("The dealer's hand is: " + ANSI_RED + dealerHand.getCard(0).toString() + ANSI_RESET);
                System.out.println(ANSI_RED + "Second Card Covered" + ANSI_RESET);
                System.out.println("What's your move? 1) Hit 2) Stand");
                int move = input.nextInt();

                if (move == 1) {
                    playerHand.draw(playingDeck);
                    System.out.println("You draw: " + ANSI_RED + playerHand.getCard(playerHand.deckSize() - 1).toString() + ANSI_RESET);
                    if (playerHand.cardsValue() > 21) {
                        System.out.println(ANSI_RED_BACKGROUND + "You busted!!! Card value: " + playerHand.cardsValue() + ANSI_RESET);
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if (move == 2) {
                    break;
                }
            }
            System.out.println("Dealer's hand: " + ANSI_RED + dealerHand.toString() + ANSI_RESET);
            if ((dealerHand.cardsValue() > playerHand.cardsValue()) && !endRound) {
                System.out.println(ANSI_RED_BACKGROUND + "House wins! lol definitely not rigged!" + ANSI_RESET);
                playerMoney -= playerBet;
                endRound = true;
            }
            while ((dealerHand.cardsValue()) < 17 && !endRound) {
                dealerHand.draw(playingDeck);
                System.out.println("Dealer draws: " + ANSI_RED + dealerHand.getCard(dealerHand.deckSize() - 1).toString() + ANSI_RESET);
            }
            System.out.println("Dealers hand total: " + dealerHand.cardsValue());
            if ((dealerHand.cardsValue() > 21) && !endRound) {
                System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "Dealer messed up, you win!" + ANSI_RESET);
                playerMoney += playerBet;
                endRound = true;
            }
            if ((playerHand.cardsValue() == dealerHand.cardsValue()) && !endRound) {
                System.out.println(ANSI_BLUE_BACKGROUND + ANSI_BLACK + "Push" + ANSI_RESET);
                endRound = true;
            }

            if ((playerHand.cardsValue() > dealerHand.cardsValue()) && !endRound) {
                System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "You won!" + ANSI_RESET);
                playerMoney += playerBet;
                endRound = true;
            } else if (!endRound) {
                System.out.println(ANSI_RED_BACKGROUND + "You lost!" + ANSI_RESET);
                playerMoney -= playerBet;
                endRound = true;
            }

            playerHand.moveToDeck(playingDeck);
            dealerHand.moveToDeck(playingDeck);
            System.out.println( "Round ends!");
        }
        if (playerMoney == 0) {
            System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "You out of cash! Go to the ATM and get some more $$" + ANSI_RESET);
        }
    }
}