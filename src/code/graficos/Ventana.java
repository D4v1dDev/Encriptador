package code.graficos;

import code.codificador.Codificar;
import code.herramientas.CargadorRecursos;
import code.main.Principal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.*;

public class Ventana extends JFrame implements ActionListener, ItemListener, KeyListener{

	//VARIABLES
	private static final int MARGEN_X =25;
	private static final int MARGEN_Y =10;
	private static final int PADDING_X =10;
	private static final int PADDING_Y =5;
	
	private static final int LIMITE =2;

	public static final int SPANISH = 0, ENGLISH = 1;

	private static int lenguaje=0;

	private static final String[][] TEXTO = {{"Codificar:",	"Traducir",	"Descodificar:","Hecho por David","Onda"	,"Archivo texto"},
											{"Code:",		"Translate","Decode:",		"Made by David",	"Wave"	,"Text File"},
	};

	private static final String[] ERR_1 = {"Debes rellenar al menos un campo","You must fill at least one gap","You must fill at least one field"};
	private static final String[] ERR_2 = {"No pueden estar rellenados los dos campos a la vez","Both gaps mustn't be fill"};
	private static final String[] ERR_3 = {"Debes rellenar con números el campo de onda", "Wave must be fill with natural numbers"};

	//Componentes
	private static final JPanel PANEL = new JPanel();

	private static JButton traducir, ok, cargarArchivo;
	private static JTextField salida, entrada, onda;
	private static JLabel texto1, texto2, texto3, texto4, creditos;
	private static JComboBox<String> idioma;

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
		ventana.add(PANEL);
		ventana.setVisible(true);
	}
	
	//INICIALIZAR Y COLOCAR ELEMENTOS
	private void colocarElementos(Ventana ventana) {
		
		texto1 = new JLabel(TEXTO[lenguaje][0]);
		texto1.setBounds(MARGEN_X, MARGEN_Y,300,20);
		texto1.setVisible(true);
		
		entrada = new JTextField();
		entrada.setBounds(MARGEN_X, MARGEN_Y + PADDING_Y +texto1.getHeight(), 300,30);
		entrada.setVisible(true);
		
		traducir = new JButton(TEXTO[lenguaje][1]);
		traducir.setBounds(MARGEN_X +entrada.getWidth()/2-50, PADDING_Y + MARGEN_Y +seracionY(entrada),100,30);
		traducir.setVisible(true);
		traducir.addActionListener(this);
		
		texto2 = new JLabel(TEXTO[lenguaje][2]);
		texto2.setBounds(MARGEN_X,seracionY(traducir)+ MARGEN_Y + PADDING_Y,300,20);
		texto2.setVisible(true);
		
		salida = new JTextField();
		salida.setBounds(MARGEN_X,seracionY(texto2)+ MARGEN_Y + PADDING_Y, 300,30);
		salida.setVisible(true);
		
		texto3 = new JLabel();
		texto3.setBounds(entrada.getX(),traducir.getY()+130,400,texto2.getHeight());
		texto3.setVisible(false);
		
		texto4 = new JLabel();
		texto4.setBounds(10, 200, 350, 20);
		texto4.setVisible(true);
		texto4.setText(TEXTO[lenguaje][5]);
		
		ok = new JButton("Ok");
		ok.setBounds(seracionX(salida)+ PADDING_X,salida.getY(),50,30);
		ok.addActionListener(this);
		ok.setVisible(false);

		cargarArchivo = new JButton("Cargar Archivo");
		cargarArchivo.setBounds(MARGEN_X,seracionY(salida)+ PADDING_Y + MARGEN_Y, entrada.getWidth(),40);
		cargarArchivo.addActionListener(this);

		creditos = new JLabel(TEXTO[lenguaje][3]);
		creditos.setBounds(ventana.getWidth()- MARGEN_X -170,ventana.getHeight()-70,170,30);
		creditos.setOpaque(true);
		creditos.setVisible(true);
		
		idioma = new JComboBox<>();
		idioma.setBounds(seracionX(texto1)+180, texto1.getY(), 100 ,texto1.getHeight());
		idioma.addItem("Spanish");
		idioma.addItem("English");
		idioma.addItemListener(this);

		onda = new JTextField();
		onda.setBounds(traducir.getX()+300,traducir.getY(),traducir.getHeight(), traducir.getHeight());
		onda.setVisible(true);
		onda.addKeyListener(this);

		texto4 = new JLabel(TEXTO[lenguaje][4]);
		texto4.setBounds(onda.getX(),onda.getY()-25,traducir.getWidth(),onda.getWidth());
		texto4.setVisible(true);

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
		//ventana.add(cargarArchivo);
	}
	@Override
	//EVENTOS
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == traducir) {
			if(stringAInt(onda.getText()) != -1) {
			//Lee los campos de texto y juega con las posibilidades de que esten vacios o llenos
				if(entrada.getText().equals("") && salida.getText().equals("")) {
					texto3.setText(ERR_1[lenguaje]);
					texto3.setVisible(true);				
				}else if(salida.getText().equals("")) {
					if(onda.getText().equals("")) {
						salida.setText(Principal.codificar(entrada.getText(),0));
					}else {
						salida.setText(Principal.codificar(entrada.getText(),stringAInt(onda.getText())));
					}
				}else if(entrada.getText().equals("")) {
					if(onda.getText().equals("")) {
						entrada.setText(Principal.descodificar(salida.getText(),0));
					}else {
						entrada.setText(Principal.descodificar(salida.getText(),stringAInt(onda.getText())));
					}
				
				}else {
					texto3.setText(ERR_2[lenguaje]);
					texto3.setVisible(true);
				}
				ok.setVisible(true);
			}
		}else
		//Funcion de boton ok
		if(e.getSource() == ok) {
			salida.setText("");
			entrada.setText("");
			texto3.setVisible(false);
			ok.setVisible(false);
		}else if(e.getSource() == cargarArchivo){
			int eleccion = JOptionPane.showOptionDialog(this,"Que quiere hacer","Cargar Fichero", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, null, new String[]{"Traducir","Codificar"}, "Traducir");
			if(eleccion<0){
				return;
			}
			JFileChooser jfc = new JFileChooser();
			jfc.showOpenDialog(this);
			File f = jfc.getSelectedFile();

			if(eleccion == 1){
				String codificado = Codificar.decodTexto(CargadorRecursos.cargarArchivo(f.getAbsolutePath()).toCharArray(),0);
				CargadorRecursos.guardarArchivo(f.getParent(), f.getName()+".decod",codificado);
			}else{
				String codificado = Codificar.codTexto(CargadorRecursos.cargarArchivo(f.getAbsolutePath()).toCharArray(), 0);
				CargadorRecursos.guardarArchivo(f.getParent(), f.getName()+".cod",codificado);
			}
		}
	}

	private int stringAInt(String text) {
		int a =-1;
		if(!text.equals("")) {
			try {
				a= Integer.parseInt(text);
			}catch(Exception e) {
				e.printStackTrace();
			}
			if(a<0) {
				texto3.setText(ERR_3[lenguaje]);
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
		texto1.setText(TEXTO[lenguaje][0]);
		traducir.setText(TEXTO[lenguaje][1]);
		texto2.setText(TEXTO[lenguaje][2]);
		creditos.setText(TEXTO[lenguaje][3]);
		texto4.setText(TEXTO[lenguaje][4]);
	}
	 public void keyTyped(KeyEvent e) {		
		 if (onda.getText().length() == LIMITE) {
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
