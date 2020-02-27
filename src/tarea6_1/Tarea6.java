package tarea6_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.*;

/**
 *
 * @author jjalv
 */
public class Tarea6 {

    static Pattern patNombre = null;
    static Pattern patFichero = null;
    static Matcher mat;
    static Matcher matF;
    static String nombre;
    static String nombreF;
    static boolean flag;
    static Logger logger;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        // CREO EL LOGGER
        logger = Logger.getLogger("miLogger");

        // LO ASOCIO A UN FICHERO
        FileHandler fh = new FileHandler("c:\\tarea6/miFichero.log", true);
        logger.addHandler(fh);

        // Y VISUALIZO POR PANTALLA 
        logger.setUseParentHandlers(true);

        // EL FORMATO DE DATOS PARA EL LOG SERA txt
        SimpleFormatter sp = new SimpleFormatter();
        fh.setFormatter(sp);
        
        

        nombre = JOptionPane.showInputDialog("Cual es tu nombre");
        // true para que utilice el patrón nombre de usuario
        flag = true;
        patron(flag);
        nombreF = JOptionPane.showInputDialog("introduzca "
                + "un nombre de fichero...");
        // false para utilizar el patrón nombre fichero
        flag = false;
        patron(flag);

        // MUESTRO POR PANTALLA EL CONENIDO DEL FICHERO .log
        FileReader fr = new FileReader("c:\\tarea6/miFichero.log");
        BufferedReader br = new BufferedReader(fr);
        System.out.println("****************************************");
        System.out.println("******** EL CONTENIDO DEL .log *********");
        System.out.println("****************************************");
        
        boolean c;
        while (c = br.read() != -1) {

            System.out.println(br.readLine());

        }
    }
    
    static void Peticiones(){
    
    
    
    }

    /**
     *
     * @param a para diferenciar si es nombre de usuario "true" o es un nombre
     * de fichero "false"
     */
    static void patron(boolean a) {
        boolean comprobante=true;

        // PRIMERO ME EVALUA EL NOMBRE 
        if (a) {
            while(comprobante){
            
              // PARA EL NOMBRE ADMITE SOLO  OCHO minúsculas
            patNombre = Pattern.compile("[a-z]{8}");
            mat = patNombre.matcher(nombre);
            if (mat.find()) {

                // EL PATRON COINCIDE
                logger.log(Level.INFO, "NOMBRE DE USUARIO " + nombre
                        + " cumple con la estructura prevista...\n");

            } else if (!mat.find()) {

                //EL PATRON NO COINCIDE
                logger.log(Level.WARNING, "NOMBRE DE USUARIO " + nombre
                        + " no cumple con el patrón sugerido...\n");

            }
            }

          
            
            

        } else if (!a) {
            // PARA EL FICHERO ADMITE MAYUSCULAS, minúsculas y número
            // COMO EXTEXION SOLO ADMITE TRES minúsculas 
            patFichero = Pattern.compile("[a-z]{1,8}.[a-z]{3}");
            // AHORA EVALUA EL NOMBRE DEL FICHERO
            matF = patFichero.matcher(nombreF);

            if (matF.find()) {
                // EL PATRON COINCIDE
                logger.log(Level.INFO, "NOMBRE DE FICHERO " + nombreF
                        + " cumple con la estructura prevista...\n");

            } else if (!matF.find()) {
                //EL PATRON NO COINCIDE
                logger.log(Level.WARNING, "NOMBRE DE FICHERO " + nombreF
                        + " NO cumple con el patrón sugerido...\n");

            }
        }

    }

}
