package code.codificador;

public class Codificar {

	private static String codificado;

	public static String codTexto(char[] cadena,int onda) {
		codificado="";
		System.out.println(cadena.length);
		for (int i = 0; i < cadena.length; i++) {
			codificado+=Character.toString((char)(((int) cadena[i])+onda+i));
		}
		return codificado;
	}

	public static String decodTexto(char[] cadena, int onda) {
		codificado="";
		System.out.println(cadena.length);
		for (int i = 0; i < cadena.length; i++) {

			codificado+=Character.toString((char)(((int) cadena[i])-onda-i));
		}
		return codificado;
	}
}
