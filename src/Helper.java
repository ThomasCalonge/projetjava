import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Contient des fonctions d'aide. 
 * Toutes les fonctions de cette classe sont statiques. Le constructeur de cette classe est privé il est donc impossible de construire une instance de cette classe
 */
public class Helper 
{
	// Pas d'instance possible
	private Helper(){}

	/**
	 * Permet de choisir un entier entre deux valeurs (<b>les deux valeurs comprises</b>). Les entiers seront entrés via un Scanner passé en argument.
	 * Les deux valeurs peuvent être entrées dans n'importe quel sens : a n'a pas besoin d'être plus petit que b.
	 * Ecrire le code {@code choose_int(4,5,...); } aura le même effet que d'écrire {@code choose_int(5,4,...); }.
	 * Enfin la fonction vérifie que la valeur entrée soit bien un entier.<br>
	 * choose_int retourne <b>toujours</b> un entier compris entre les bornes. Si au moment de choisir un entier
	 * l'utilisateur entre une lettre : un message d'erreur sera affiché et la fonction lui redemandera un entier.<br>
	 * L'affichage se fait selon le format "> (x,y) " où x = min(a,b) et y = max(a,b)
	 * 
	 * @param a Une des bornes de l'intervalle dans lequel sera choisit l'entier
	 * @param b L'autre borrne
	 * @param sc Le scanner depuis lequel l'entier sera lu
	 * 
	 * @return un entier x qui respecte la spécification x >= min(a,b) et x <= max(a,b)
	 * @see java.util.Scanner
	 */
	static int choose_int(int a, int b, Scanner sc) {

		if (a > b) {
			// swap
			final Integer tmp = a;
			a = b;
			b = tmp;
		}
		
		int choix = a-1;

		do {
			System.out.print(" > (" + a + ";" + b + ") ");
			try {
				choix = sc.nextInt();

				if (choix < a)
					System.out.println("Le nombre doit être supérieur ou égal à " + a);

				if (choix > b)
					System.out.println("Le nombre doit être inférieur ou égal à " + b);
			} catch (InputMismatchException e) {
				System.err.println("Veuillez entrer un nombre entier");
			}

			sc.nextLine();

		} while ((choix < a) || (choix > b));

		return choix;
	}

	/**
	 * Permet d'afficher sur la sortie standard différents choix ainsi qu'un message expliquant à quoi ils servent.
	 * L'affichage de ses choix se fait selon un certain format:<br>
	 * {@literal <explainMsg>} <br>
	 *  - 1 {@literal <options[0]> }<br>
	 *  - 2 {@literal <options[1]> }<br>
	 * 	.<br>
	 * 	.<br>
	 * 	.<br>
	 *  - n {@literal <options[n-1]> }<br>
	 * 	Par exemple, l'appel de {@code printGameFormatted("exemples", new String[]{"exemple 1", "exemple 2", "exemple 3"}); }
	 *  affichera sur la sortie standard:<br>
	 *  "exemples<br>
	 *  - 1 exemple 1<br>
	 *  - 2 exemple 2<br>
	 *  - 3 exemple 3"
	 * 
	 * @param explainMsg un message expliquant à quoi correspondent les choix qui seront affichée
	 * @param options un tableau contenant des String correspondant aux choix
	 * @see java.lang.System#out stdout
	 */
	public static void printGameFormatted(final String explainMsg, final String[] options) {
		System.out.println(explainMsg);

		for (int i = 0; i < options.length; ++i)
			System.out.println(" - " + (i + 1) + " " + options[i]);
	}

	/**
	 * Affiche n fois une ligne contenant le caractère '*' sur la sortie standard.
	 * @param n nombre de fois que l'on veut afficher le caractère '*'
	 * @see java.lang.System#out stdout
	 */
	public static void pass(final int n) {
		for (int i = 0; i < n; ++i)
			System.out.println("*");
	}
}