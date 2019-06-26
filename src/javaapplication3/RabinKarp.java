package javaapplication3;

import javax.swing.JOptionPane;

public class RabinKarp {
	static final long q = 10014521L;
	static final int d = 128;

	public static int RKSearch(String p, String t) {
		long dm = 1, h1 = 0, h2 = 0;
		int i;
		int m = p.length();
		int n = t.length();
		if (n < m) // texto MENOR que o padr�o
			return -1;
		for (i = 1; i < m; i++)
			dm = (d * dm) % q;
		for (i = 0; i < m; i++) {
			h1 = (h1 * d + p.charAt(i)) % q;
			h2 = (h2 * d + t.charAt(i)) % q;
		}
		for (i = 0; h1 != h2; i++) {
			if (i >= n - m) // chegou ao final do texto sem encontrar
				return -1;
			h2 = (h2 + d * q - t.charAt(i) * dm) % q;
			h2 = (h2 * d + t.charAt(i + m)) % q;
		}
		return i;
	}

	public static void main(String args[]) {
		String t = "texto de teste";
		String p = "teste";

		int pos = RKSearch(p, t);

		if (pos == -1)
			JOptionPane.showMessageDialog(null, "Texto: " + t + "\nPadr�o: "
					+ p + "\nPadr�o nao encontrado!");
		else
			JOptionPane.showMessageDialog(null, "Texto: " + t + "\nPadr�o: "
					+ p + "\nPadr�o encontrado na posi��o: " + pos + ".");

		System.exit(0);
	}
}