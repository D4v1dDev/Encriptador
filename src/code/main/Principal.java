package code.main;

import code.codificador.Codificar;

import java.awt.Image;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class Principal {
	//VARIABLES
	private static final ImageIcon imagen=new ImageIcon("src\\recursos\\imagenes\\iconos\\icon.png");
	private static final Image icono=imagen.getImage();
	//CLASES
	public static code.graficos.Ventana ventana=new code.graficos.Ventana();

	//CONSTRUCTOR
	public Principal() {
		
	}



	//AQUI EMPIEZA EL PROGRAMA
	public static void main(final String[] args) {

		if(args.length==0){
			ventana.crear("Codificador--C@e\\h�f*h8w",icono);

		}else{
			byte accion=-1;
			int onda = 0;
			String cadenaACodificar="";
			for (int i = 0; i < args.length; i+=2) {
				if(args[i].equals("-d") || args[i].equals("--decode")){
					accion=0;
					cadenaACodificar=args[i+1];
				}
				if(args[i].equals("-c") || args[i].equals("--code")){
					accion=1;
					cadenaACodificar=args[i+1];
				}
				if(args[i].equals("-h") || args[i].equals("--help")){
					accion=2;
				}
				if(args[i].equals("-w") || args[i].equals("--wave")){
					onda = Integer.parseInt(args[i+1]);
				}
			}

			switch (accion){
				case -1:
					System.err.println("Error in the parameters, try with -h or --help to see the help menu");
					break;
				case 0:
					System.console().writer().println("Text correctly decoded : ["+Codificar.decodTexto(cadenaACodificar.toCharArray(),onda)+"]");
					break;
				case 1:
					System.console().writer().println("Text correctly coded : ["+Codificar.codTexto(cadenaACodificar.toCharArray(),onda)+"]");
					break;
				case 2:
					System.console().writer().println("Commands frequently used :\n\t-d --decode [text] : used to decode a text\n\t-c --code [text] : used to code a text\n\t-w --wave [number] : enters a determinated wave\n\t-h --help : Displays this help text");
			}
		}

	}

	public static char[] stringAChar(final String cadena){
		return cadena.toCharArray();
	}
	//CODIFICAR O DESCODIFICAR EL MENSAJE ENVIADO DESDE LA VENTANA
	public static String codificar(final String cadena, final int onda) {
		return Codificar.codTexto(stringAChar(cadena),onda);
	}
	public static String descodificar(final String cadena,final int onda) {
		return Codificar.decodTexto(stringAChar(cadena),onda);
	}
}
