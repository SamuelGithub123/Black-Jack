package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Casino {

	public static void penguBlackJack() {

		CardDeck deck = CardDeck.getDeck();

		int token = 1000;

		System.out.println("Welcome to Pengu-BlackJack!");

		// Tokenstand muss größer als 0 sein
		while (token > 0) {
			int cardNumber = 1;
			int cardDNumber = 1;
			System.out.println("(1) Start a game or (2) exit");
			int input = readInt();

			// Eingabe muss 1 oder 2 sein
			while (input != 1 && input != 2) {
				System.out.println("What?!");
				System.out.println("(1) Start a game or (2) exit");
				input = readInt();
			}

			// Einsatz muss positiv und kleiner gleich als Tokenstand sein
			if (input == 1) {
				System.out.println("Your current balance: " + token);
				System.out.println("How much do you want to bet?");
				int bet = readInt();
				while (bet <= 0 || bet > token) {
					System.out.println("How much do you want to bet?");
					bet = readInt();
				}

				System.out.println("Player cards:");

				// erste Karte ziehen
				int card1 = deck.drawCard();
				System.out.println(cardNumber + " : " + card1);
				cardNumber++;

				// zweite Karte ziehen
				int card2 = deck.drawCard();
				System.out.println(cardNumber + " : " + card2);
				cardNumber++;

				// Werte zusammenzählen
				int points = card1 + card2;
				System.out.println("Current standing: " + points);

				// Werte kleiner als 21
				while (points < 21) {
					System.out.println("(1) draw another card or (2) stay");
					int input2 = readInt();

					// Eingabe muss 1 oder 2 sein
					while (input2 != 1 && input2 != 2) {
						System.out.println("What?!");
						System.out.println("(1) draw another card or (2) stay");
						input2 = readInt();
					}

					// neue Karte ziehen
					if (input2 == 1) {
						int newCard = deck.drawCard();
						System.out.println(cardNumber + " : " + newCard);
						cardNumber++;
						points += newCard;
						System.out.println("Current standing: " + points);

						// Karten ziehen beenden und Dealer zieht
					} else {
						System.out.println("Dealer cards:");
						int dealerSum = 0;

						// Weiterziehen bis Dealer Summe über Spieler Summe
						while (dealerSum <= points) {
							int cardD1 = deck.drawCard();
							System.out.println(cardDNumber + " : " + cardD1);
							cardDNumber++;
							dealerSum += cardD1;

							// Wenn Dealer über 21 ist
							if (dealerSum > 21) {
								break;
							}
						}
						System.out.println("Dealer: " + dealerSum);

						// Spieler gewinnt
						if (dealerSum > 21) {
							System.out.println("You won " + bet + " tokens.");
							token += bet;

							// Dealer gewinnt
						} else {
							System.out.println("Dealer wins. You lost " + bet + " tokens.");
							token -= bet;
						}
						break;
					}
				}

				// Spieler ist über 21
				if (points > 21) {
					System.out.println("You lost " + bet + " tokens.");
					token -= bet;
				}

				// Spieler Black-Jack
				if (points == 21) {
					bet *= 2;
					System.out.println("Blackjack! You won " + bet + " tokens.");
					token += bet;
				}
			}

			// Exit
			if (input == 2) {
				System.out.println("Your final balance: " + token);
				if (token > 1000) {
					System.out.println("Wohooo! Ez profit! :D");
				}
				else {
					System.out.println("That's very very sad :(");
				}
				System.out.println("Thank you for playing. See you next time.");
				break;
			}
		}

		// kein Token verfügbar
		if (token <= 0) {
			System.out.println("Sorry, you are broke. Better Luck next time.");
			System.out.println("Your final balance: " + token);
			if (token > 1000) {
				System.out.println("Wohooo! Ez profit! :D");
			}
			else {
				System.out.println("That's very very sad :(");
			}
			System.out.println("Thank you for playing. See you next time.");
		}
	}

	public static String readString() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int readInt() {
		while (true) {
			String input = readString();
			try {
				return Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("This was not a valid number, please try again.");
			}
		}
	}

	public static void main(String[] args) {
		penguBlackJack();
	}

}
