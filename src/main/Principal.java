package main;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Principal {
	//VARIABLES
	private static final ImageIcon imagen=new ImageIcon("src\\recursos\\imagenes\\iconos\\icon.png");
	private static final Image icono=imagen.getImage();
	//CLASES
	static graficos.Ventana ventana=new graficos.Ventana();
	static codificador.Codificar codificar=new codificador.Codificar();
	//CONSTRUCTOR
	public Principal() {
		
	}
	//AQUI EMPIEZA EL PROGRAMA
	public static void main(final String args[]) {


		if(false) {
			char[] abece=codificar.abece;
			boolean f = true;
			System.out.println("Hay exactamente "+abece.length+" caracteres");
			for (int i = 0; i < abece.length; i++) {
				for (int j = i + 1; j < abece.length; j++) {
					if (abece[i] == abece[j]) {
						System.out.println("Cuidado, posicion " + i + " = " + j + "[" + abece[i] + "]");
						f=false;
					}
				}
			}
			if(f) {
				System.out.println("No hay fallos");
			} else {
				System.out.println("Failed");
			};
		}else{
			ventana.crear("Codificador--C@e\\hçf*h8w",icono);
		}
	}
	//CAMBIOS DE STRING A CHAR[] Y VICEVERSA
	public String charAString(final char[] cadenaDescompuesta) {
		return cadenaDescompuesta.toString();
	}
	public static char[] stringAChar(final String cadena){
		return cadena.toCharArray();
	}
	//CODIFICAR O DESCODIFICAR EL MENSAJE ENVIADO DESDE LA VENTANA
	public static String codificar(final String cadena, final int onda) {
		return codificar.codTexto(stringAChar(cadena),onda);
	}
	public static String descodificar(final String cadena,final int onda) {
		return codificar.decodTexto(stringAChar(cadena),onda);
	}
}
