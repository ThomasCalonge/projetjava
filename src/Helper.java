import java.util.Scanner;
import java.util.InputMismatchException;

public class Helper {
	static int choose_int(int min, int max, Scanner sc) {
		int choix = 0;

		if (min > max) {
			// swap
			final Integer tmp = min;
			min = max;
			max = tmp;
		}

		do {
			System.out.print(" > (" + min + ";" + max + ") ");
			try {
				choix = sc.nextInt();

				if (choix < min)
					System.out.println("Le nombre doit être supérieur ou égal à " + min);

				if (choix > max)
					System.out.println("Le nombre doit être inférieur ou égal à " + max);
			} catch (InputMismatchException e) {
				System.err.println("Veuillez entrer un nombre entier");
			}

			sc.nextLine();

		} while ((choix < min) || (choix > max));

		return choix;
	}

	public static void printGameFormatted(final String explainMsg, final String[] options) {
		System.out.println(explainMsg);

		for (int i = 0; i < options.length; ++i)
			System.out.println(" - " + (i + 1) + " " + options[i]);
	}

	public static void pass(final int n) {
		for (int i = 0; i < n; ++i)
			System.out.println("*");
	}
}