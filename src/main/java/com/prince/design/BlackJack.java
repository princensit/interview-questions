package com.prince.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement Blackjack card game
 *
 * @author Prince Raj
 */
public class BlackJack {

    public enum Suit {
        CLUB(0), DIAMOND(1), HEART(2), SPADE(3);

        private int value;

        Suit(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    // Implemented Card as an abstract class because value() don't make much sense without any specific game attached to
    // them.
    public abstract class Card {

        // number on the card i.e. 2 through 10 or 11 for Jack, 12 for Queen, 13 for King, or 1 for Ace
        protected int faceValue;

        protected Suit suit;

        private boolean available = true;

        public Card(int faceValue, Suit suit) {
            this.faceValue = faceValue;
            this.suit = suit;
        }

        public abstract int value();

        public Suit getSuit() {
            return suit;
        }

        // checks if the card is available to be given to someone
        public boolean isAvailable() {
            return available;
        }

        public void markAvailable() {
            available = true;
        }

        public void markUnavailable() {
            available = false;
        }
    }

    public class Deck<T extends Card> {

        // all cards, dealt or not
        private List<T> cards;

        // marks first undealt card
        private int dealtIndex = 0;

        public void setDeckOfCards(List<T> deckOfCards) {
            this.cards = deckOfCards;
        }

        public void shuffle() {
            // TODO Implement me!
        }

        public int remainingCards() {
            return cards.size() - dealtIndex;
        }

        public T[] dealHand(int number) {
            // TODO Implement me!

            return null;
        }

        public T dealCard() {
            // TODO Implement me!

            return null;
        }
    }

    public class Hand<T extends Card> {

        protected List<T> cards = new ArrayList<>();

        public int score() {
            int score = 0;
            for (T card : cards) {
                score += card.value();
            }

            return score;
        }

        public void addCard(T card) {
            cards.add(card);
        }
    }

    public class BlackJackCard extends Card {

        public BlackJackCard(int faceValue, Suit suit) {
            super(faceValue, suit);
        }

        @Override
        public int value() {
            if (isAce()) {
                return 1;
            } else if (isFaceCard()) {
                return 10;
            } else {
                return faceValue;
            }
        }

        private int minValue() {
            if (isAce()) {
                return 1;
            } else {
                return value();
            }
        }

        private int maxValue() {
            if (isAce()) {
                return 11;
            } else {
                return value();
            }
        }

        private boolean isAce() {
            return faceValue == 1;
        }

        private boolean isFaceCard() {
            return faceValue >= 11 && faceValue <= 13;
        }
    }

    // Now, let's say we're building a blackjack game, so we need to know the value of the cards. Face cards are 10 and
    // an ace is 11.
    public class BlackJackHand extends Hand<BlackJackCard> {

        // There are multiple possible scores for a blackjack hand, since aces have multiple values. Return the highest
        // possible score that's under 21, or the lowest score that's over.
        public int score() {
            List<Integer> scores = possibleScores();

            int maxUnder = Integer.MIN_VALUE;
            int minOver = Integer.MAX_VALUE;

            for (int score : scores) {
                if (score > 21 && score < minOver) {
                    minOver = score;
                } else if (score <= 21 && score > maxUnder) {
                    maxUnder = score;
                }
            }

            return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
        }

        // return a list of all possible scores this hand could have (evaluating each ace as both 1 and 11)
        private List<Integer> possibleScores() {
            // TODO Implement me!

            return new ArrayList<>();
        }

        private boolean isBusted() {
            return score() > 21;
        }

        private boolean is21() {
            return score() == 21;
        }

        private boolean isBlackJack() {

            return true;
        }
    }
}
