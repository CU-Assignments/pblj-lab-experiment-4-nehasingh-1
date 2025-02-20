/*Experiment 4.2: Card Collection System*/
import java.util.*;
class Card {
    private String symbol;
    private String value;
    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }
    public String getSymbol() {
        return symbol;
    }
    public String getValue() {
        return value;
    }
    @Override
    public String toString() {
        return value + " of " + symbol;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return Objects.equals(symbol, card.symbol) && Objects.equals(value, card.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(symbol, value);
    }
}
class CardCollection {
    private Map<String, List<Card>> cardMap;
    private Set<Card> cardSet;
    public CardCollection() {
        cardMap = new HashMap<>();
        cardSet = new HashSet<>();
    }
    public void addCard(String symbol, String value) {
        Card newCard = new Card(symbol, value);
        if (cardSet.contains(newCard)) {
            System.out.println("Error: Card \"" + newCard + "\" already exists.");
            return;
        }
        cardSet.add(newCard);
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(newCard);
        System.out.println("Card added: " + newCard);
    }
    public void findCardsBySuit(String symbol) {
        if (!cardMap.containsKey(symbol) || cardMap.get(symbol).isEmpty()) {
            System.out.println("No cards found for " + symbol + ".");
            return;
        }
        for (Card card : cardMap.get(symbol)) {
            System.out.println(card);
        }
    }
    public void displayAllCards() {
        if (cardSet.isEmpty()) {
            System.out.println("No cards found.");
            return;
        }
        for (Card card : cardSet) {
            System.out.println(card);
        }
    }

    public void removeCard(String symbol, String value) {
        Card card = new Card(symbol, value);
        if (!cardSet.contains(card)) {
            System.out.println("Card not found: " + card);
            return;
        }
        cardSet.remove(card);
        cardMap.get(symbol).remove(card);
        if (cardMap.get(symbol).isEmpty()) {
            cardMap.remove(symbol);
        }
        System.out.println("Card removed: " + card);
    }
}
public class Main {
    public static void main(String[] args) {
        CardCollection collection = new CardCollection();
        // Test Case 1: No Cards Initially
        collection.displayAllCards();
        // Test Case 2: Adding Cards
        collection.addCard("Spades", "Ace");
        collection.addCard("Hearts", "King");
        collection.addCard("Diamonds", "10");
        collection.addCard("Clubs", "5");
        // Test Case 3: Finding Cards by Suit
        collection.findCardsBySuit("Hearts");
        // Test Case 4: Searching Suit with No Cards
        collection.findCardsBySuit("Diamonds");
        // Test Case 5: Displaying All Cards
        collection.displayAllCards();
        // Test Case 6: Preventing Duplicate Cards
        collection.addCard("Hearts", "King");
        // Test Case 7: Removing a Card
        collection.removeCard("Diamonds", "10");
    }
}

/*
Objective:
Develop a Java program that collects and stores playing cards to help users find all the cards of a given symbol (suit).
The program should utilize the Collection interface (such as ArrayList, HashSet, or HashMap) to manage the card data efficiently.
Understanding the Problem Statement
1. Card Structure:
Each card consists of a symbol (suit) and a value (rank).
Example card representations:
Ace of Spades
King of Hearts
10 of Diamonds
5 of Clubs
2. Operations Required:
Add Cards → Store card details in a collection.
Find Cards by Symbol (Suit) → Retrieve all cards belonging to a specific suit (e.g., all "Hearts").
Display All Cards → Show all stored cards.
3. Collections Usage:
ArrayList: To store cards in an ordered manner.
HashSet: To prevent duplicate cards.
HashMap<String, List<Card>>: To organize cards based on suits for faster lookup.
Test Cases
Test Case 1: No Cards Initially
Input:
Display All Cards
Expected Output:
No cards found.
Test Case 2: Adding Cards
Input:
Add Card: Ace of Spades
Add Card: King of Hearts
Add Card: 10 of Diamonds
Add Card: 5 of Clubs
Expected Output:
Card added: Ace of Spades
Card added: King of Hearts
Card added: 10 of Diamonds
Card added: 5 of Clubs
Test Case 3: Finding Cards by Suit
Input:
Find All Cards of Suit: Hearts
Expected Output:
King of Hearts
Test Case 4: Searching Suit with No Cards
Input:
Find All Cards of Suit: Diamonds
(If no Diamonds were added)
Expected Output:
No cards found for Diamonds.
Test Case 5: Displaying All Cards
Input:
Display All Cards
Expected Output:
Ace of Spades
King of Hearts
10 of Diamonds
5 of Clubs
Test Case 6: Preventing Duplicate Cards
Input:
Add Card: King of Hearts
Expected Output:
Error: Card "King of Hearts" already exists.
Test Case 7: Removing a Card
Input:
Remove Card: 10 of Diamonds
Expected Output:
Card removed: 10 of Diamonds
  */
