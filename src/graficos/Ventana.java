package graficos;

import herramientas.CargadorRecursos;
import main.Principal;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class Ventana extends JFrame implements ActionListener, ItemListener, KeyListener{

	//RUTAS
	main.Principal principal=new main.Principal();
	
	//VARIABLES
	private static final int margenX=25;
	private static final int margenY=10;
	private static final int paddingX=10;
	private static final int paddingY=5;
	
	private static final int limite=2;
	
	private static final String[][] texto= {{"Codificar:",	"Traducir",	"Descodificar:","Hecho por David","Onda"	,"Archivo texto"},
											{"Code:",		"Translate","Decode:",		"Made by David",	"Wave"	,"Text File"},
											{"Encoder",		"Traduire",	"Décoder",		"Fait par David",	"Onde"	,".txt"}};
	
	private static final String nulo="";
	private static final String err1[]= {"Debes rellenar al menos un campo","You must fill at least one gap","You must fill at least one field"};
	private static final String err2[]= {"No pueden estar rellenados los dos campos a la vez","Both gaps mustn´t be fill", "Les deux spaces ne doivent pas être comblées"};
	private static final String err3[]= {"Debes rellenar con números el campo de onda", "Wave must be fill with natural numbers"};
	private static final String dialogo[]= {"La onda es un número utilizado para codificar aún más el mensaje. Comparte una onda específica con el receptor para que solo os podais entender los dos.\nPara disminuir los fallos es mejor colocar valores cercanos a cero.",
											"Wave is a number use to code a bit more the message."};
	private static int lenguaje=0;
	//Componentes
	JPanel panel =new JPanel();
	JButton traducir,ok,ayuda1, traducirArchivo;
	JTextField salida,entrada,onda;
	JLabel texto1,texto2,texto3,texto4,texto5, creditos;
	JComboBox idioma,accion;
	JFileChooser elegirArchivoACargar;
	
	//CONSTRUCTOR
	public Ventana() {
	
	}
	//CREAR VENTANA
	public void crear(String titulo, Image icono) {
		Ventana ventana=new Ventana();
		
		ventana.setTitle(titulo);
		ventana.setBounds(10,10,650,500);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(null);
		
		ventana.setIconImage(icono);
		ventana.colocarElementos(ventana);
		ventana.add(panel);
		ventana.setVisible(true);
	}
	
	//INICIALIZAR Y COLOCAR ELEMENTOS
	private void colocarElementos(Ventana ventana) {
		
		texto1=new JLabel(texto[lenguaje][0]);
		texto1.setBounds(margenX,margenY,300,20);
		texto1.setVisible(true);
		
		entrada=new JTextField();
		entrada.setBounds(margenX, margenY+paddingY+texto1.getHeight(), 300,30);
		entrada.setVisible(true);
		
		traducir =new JButton(texto[lenguaje][1]);
		traducir.setBounds(margenX+entrada.getWidth()/2-50,paddingY+margenY+seracionY(entrada),100,30);
		traducir.setVisible(true);
		traducir.addActionListener(this);
		
		texto2=new JLabel(texto[lenguaje][2]);
		texto2.setBounds(margenX,seracionY(traducir)+margenY+paddingY,300,20);
		texto2.setVisible(true);
		
		salida=new JTextField();
		salida.setBounds(margenX,seracionY(texto2)+margenY+paddingY, 300,30);
		salida.setVisible(true);
		
		texto3=new JLabel();
		texto3.setBounds(entrada.getX(),traducir.getY()+130,400,texto2.getHeight());
		texto3.setVisible(false);
		
		texto4=new JLabel();
		texto4.setBounds(10, 200, 350, 20);
		texto4.setVisible(true);
		texto4.setText(texto[lenguaje][5]);
		
		ok=new JButton("Ok");
		ok.setBounds(seracionX(salida)+paddingX,salida.getY(),50,30);
		ok.addActionListener(this);
		ok.setVisible(false);
		
		creditos=new JLabel(texto[lenguaje][3]);
		creditos.setBounds(ventana.getWidth()-margenX-170,ventana.getHeight()-70,170,30);
		creditos.setOpaque(true);
		creditos.setVisible(true);
		
		idioma=new JComboBox();
		idioma.setBounds(seracionX(texto1)+180, texto1.getY(), 100 ,texto1.getHeight());
		idioma.addItem("Español");
		idioma.addItem("English");
		idioma.addItem("Français");
		idioma.addItemListener(this);

		onda=new JTextField();
		onda.setBounds(traducir.getX()+300,traducir.getY(),traducir.getHeight(), traducir.getHeight());
		onda.setVisible(true);
		onda.addKeyListener(this);
		
		
		texto4=new JLabel(texto[lenguaje][4]);
		texto4.setBounds(onda.getX(),onda.getY()-25,traducir.getWidth(),onda.getWidth());
		texto4.setVisible(true);
		
		ayuda1=new JButton();
		ayuda1.setBounds(seracionX(onda)+30,onda.getY(),onda.getWidth(),onda.getWidth());
		ayuda1.addActionListener(this);
		ayuda1.setVisible(true);
		ayuda1.setIcon(new ImageIcon("src/recursos/imagenes/iconos/help.png"));

		elegirArchivoACargar= new JFileChooser();
		elegirArchivoACargar.setBounds(margenX,200,325,300);
		elegirArchivoACargar.setVisible(true);

		accion = new JComboBox();
		accion.setBounds(margenX+elegirArchivoACargar.getWidth(),elegirArchivoACargar.getY(),100,30);
		accion.addItem("Codificar");
		accion.addItem("Descodificar");

		traducirArchivo = new JButton("Traducir");
		traducirArchivo.setBounds(accion.getX(), accion.getY()+40, 100,30);
		traducirArchivo.addActionListener(this);
		traducirArchivo.setVisible(true);

		ventana.add(texto1);
		ventana.add(entrada);
		ventana.add(traducir);
		ventana.add(texto2);
		ventana.add(salida);
		ventana.add(texto3);
		ventana.add(ok);
		ventana.add(creditos);
		ventana.add(idioma);
		ventana.add(onda);
		ventana.add(texto4);
		ventana.add(ayuda1);
		ventana.add(elegirArchivoACargar);
		ventana.add(accion);
		ventana.add(traducirArchivo);
	
	}
	@Override
	//EVENTOS
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==traducirArchivo){
			File a = elegirArchivoACargar.getSelectedFile();
			if(accion.getSelectedIndex()==0){
				CargadorRecursos.guardarArchivo(elegirArchivoACargar.getCurrentDirectory().getAbsolutePath(),"ArchivoEncriptado.fc",CargadorRecursos.codificarArchivo(elegirArchivoACargar.getSelectedFile()));
			}else{
				CargadorRecursos.decodificarArchivo(elegirArchivoACargar.getSelectedFile());
			}
		}

		if(e.getSource()==traducir) {
			if(stringAInt(onda.getText())!=-1) {
			//Lee los campos de texto y juega con las posibilidades de que esten vacios o llenos
				if(entrada.getText().equals(nulo) && salida.getText().equals(nulo)) {
					texto3.setText(err1[lenguaje]);
					texto3.setVisible(true);				
				}else if(salida.getText().equals(nulo)) {
					if(onda.getText().equals(nulo)) {
						salida.setText(principal.codificar(entrada.getText(),0));
					}else {
						salida.setText(principal.codificar(entrada.getText(),stringAInt(onda.getText())));
					}
				}else if(entrada.getText().equals(nulo)) {
					if(onda.getText().equals(nulo)) {
						entrada.setText(principal.descodificar(salida.getText(),0));
					}else {
						entrada.setText(principal.descodificar(salida.getText(),stringAInt(onda.getText())));
					}
				
				}else {
					texto3.setText(err2[lenguaje]);
					texto3.setVisible(true);
				}
				ok.setVisible(true);
			}
		}
		//Funcion de boton ok
		if(e.getSource()==ok) {
			salida.setText("");
			entrada.setText("");
			texto3.setVisible(false);
			ok.setVisible(false);
			
		}
		if(e.getSource()==ayuda1) {
			
			
			JOptionPane.showMessageDialog(this, dialogo[lenguaje], texto[lenguaje][4], JOptionPane.INFORMATION_MESSAGE, null);
			
		}
	}

	private int stringAInt(String text) {
		int a =-1;
		if(!text.equals(nulo)) {
			try {
				a= Integer.parseInt(text);
			}catch(Exception e) {
				e.printStackTrace();
			}
			if(a<0) {
				texto3.setText(err3[lenguaje]);
				texto3.setVisible(true);
				onda.setText("");
				ok.setVisible(true);
			}
		}else {
			a=-10;
		}
			
			return a;
	}
	//SEPARACION ESPECIAL QUE JUNTA EL TAMAÑO CON LA POSICION DE UN ELEMENTO
	private int seracionX(Component c) {
		return c.getX()+c.getWidth();
	}
	private int seracionY(Component c) {
		return c.getY()+c.getHeight();
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==idioma) {
			lenguaje=idioma.getSelectedIndex();
			cambiarElIdioma();
		}
		
	}
	private void cambiarElIdioma() {
		texto1.setText(texto[lenguaje][0]);
		traducir.setText(texto[lenguaje][1]);
		texto2.setText(texto[lenguaje][2]);
		creditos.setText(texto[lenguaje][3]);
		texto4.setText(texto[lenguaje][4]);
	}
	 public void keyTyped(KeyEvent e) {		
		 if (onda.getText().length()== limite) {
	         e.consume(); 
	    }
	 }
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
