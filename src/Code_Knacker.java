import java.util.*;

public class Code_Knacker {

	public static void main(String[] arcs) {

		final int LENGTH = 4;
		final int POSSIBLE =6;
		int[] pin = new int[LENGTH];
		Random rnd = new Random();
		boolean stopcondition = false;

		// Erstellt den Pin via Zufall mit den Zahlen 1 - 6
		for (int i = 0; i < pin.length; i++) {

			pin[i] = rnd.nextInt(POSSIBLE + 1);

			if (pin[i] == 0) {
				i--;
			}
//			for (int j = 0; j < i; j++) {
//				if (pin[i] == pin[j])
//					i--;
//			}

		}

		for (int i = 0; i < pin.length; i++) {

			System.out.print(pin[i] + " ");
		}

		while (stopcondition == false) {
			
			int r = 0;
			int k = 0;
			int d = 0;

			String Nutzer_Eingabe = nutzereingabeFordern();

			try {

				kontrolleLänge(Nutzer_Eingabe, pin);
				kontrolleHöhe(Nutzer_Eingabe, POSSIBLE);

				// Kontrolliert wie viele Richtige Zahlen vorhanden sind

				for (int i = 0; i < pin.length; i++) {

					for (int j = 0; j < pin.length; j++) {

						if (Nutzer_Eingabe.charAt(i) - '0' == pin[j]) {
							r++;

						}
//						if (pin[i] != pin[j]) {
//							d--;
//						}

					}

				}
				System.out.println(d);

				// Kontrolliert wie viele Zahlen auch auf dem korrekten Platz sind.
				for (int i = 0; i < pin.length; i++) {
					if (Nutzer_Eingabe.charAt(i) - '0' == pin[i]) {
						k++;
					}

				}

				System.out.print(Nutzer_Eingabe + " " +  r + "/" + k + "\n");

				if (k == pin.length) {
					stopcondition = true;
					System.out.print("Zutritt gewährt");
				}

				else {
					System.out.println("Leider Falsch");
				}
			} catch (FehlerhafterPinException x) {
				System.out.println(x.getMessage());
			}

		}
	}

	public static String nutzereingabeFordern() {

		Scanner scanner = new Scanner(System.in);

		String n;

		System.out.print("Bitte geben sie die Pinnummer ein:");
		n = scanner.next();

		return n;

	}

	public static void printNutzereingabe(char[] a) {

		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
		}
	}

	public static void kontrolleHöhe(String a, int b) throws FehlerhafterPinException {
		// guckt ob die 4 Stellen der Nutzereingabe zwischen 1 und 6 sind.
		for (int i = 0; i < a.length(); i++) {
			// +0 weil wir von int auf char casten
			if (a.charAt(i) > b + '0') {

				throw new FehlerhafterPinException();

			}
		}
	}

	public static void kontrolleLänge(String a, int[] b) throws FehlerhafterPinException {
		// guckt ob die Nutzereingabe 4 stellen hat
		if (a.length() > b.length) {

			throw new FehlerhafterPinException();
		}

	}

	// "public static class" weil die Methode die die Exception auffruft auch
	// "static" ist
	public static class FehlerhafterPinException extends Exception {
		FehlerhafterPinException() {
			// Die Nachricht die beim auslösen der Exception erscheint.
			super("Bitte geben sie eine 4-stellige Pinnummer, bestehend aus den Zahlen 1 bis 6 ein");
		}
	}
}
