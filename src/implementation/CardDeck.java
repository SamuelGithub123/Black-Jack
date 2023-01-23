package implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.stream.IntStream;

public class CardDeck {

	private static CardDeck deck;
	private final Random rnd;
	private Collection<Integer> cards;

	private CardDeck() {
		rnd = new Random();
		cards = new ArrayList<>();
	}

	private CardDeck(int seed) {
		rnd = new Random(seed);
		cards = new ArrayList<>();
	}

	public static CardDeck getDeck() {
		if (deck == null) {
			deck = new CardDeck();
		}
		return deck;
	}

	public static CardDeck getDeck(int seed) {
		if (deck == null) {
			deck = new CardDeck(seed);
		}
		return deck;
	}

	public int drawCard() {
		if (cards.isEmpty()) {
			IntStream.range(1, 12).forEach(x -> {
				for (int i = 0; i < 4; i++) {
					cards.add(x);
				}
			});
		}
		int[] arr = cards.stream().mapToInt(Integer::intValue).toArray();
		int i = rnd.nextInt(0, Integer.MAX_VALUE);
		return arr[i % arr.length];
	}
}
