package herramientas;

import main.Principal;

import java.io.*;
import java.util.Scanner;

public class CargadorRecursos {

    public static String cargarArchivo(String ruta){
        File f = new File(ruta);
        StringBuilder vuelta= new StringBuilder();
        if(f.exists()) {
            if (f.canRead()) {
                try {
                    Scanner s = new Scanner(f);
                    while(s.hasNext()) {
                        vuelta.append(s.nextLine());
                        vuelta.append("\n");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return vuelta.toString();
    }

    public static void guardarArchivo(String ruta, String nombre, String informacion){
        File f = new File(ruta+"\\"+nombre.replace(' ','_'));
        System.out.println(f.getAbsolutePath());

        try {
            if (f.exists()) {
                f.delete();
            }

            f.createNewFile();

            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            System.out.println("Guardar en : (" + f.getAbsolutePath() + ") la informacion : (" + informacion + ")");
            bw.write(informacion);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String codificarArchivo(File selectedFile) {
        String informacion;
        if(selectedFile.isDirectory()){
            File[] archivos = selectedFile.listFiles();
            informacion = "$·C>";
            for (int i = 0; i < archivos.length; i++) {
                informacion+=">>"+codificarArchivo(archivos[i])+"<<";
            }
        }else{
            informacion=(cargarArchivo(selectedFile.getAbsolutePath())+"/|\\~#"+selectedFile.getName());
        }
        return informacion;
    }

    public static String decodificarArchivo(File selectedFile) {
        String informacion =cargarArchivo(selectedFile.getAbsolutePath());

        if(informacion.startsWith("$·C>")){
            informacion=informacion.substring(4);
        }else{
            System.out.println(informacion.split("/~#")[0]);
            guardarArchivo(selectedFile.getParent(),informacion.split("/~#")[1],informacion.split("/~#")[0]);
        }

        return informacion;
    }
}
