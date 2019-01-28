
package barcos;

import java.util.Scanner;


/**
 * Juego de Hundir la flota
 * 
 * 
 * Este juego trata de recrear el famoso juego de mesa de Hundir la flota haciendo uso de objetos.<br><br>
 * 
 * Para su correcto funcionamiento es necesario utilizar la clase Barco de la cuál se instanciarán los barcos que usaremos para jugar.<br><br>
 * 
 * Para su correcto funcionamiento esta clase hace uso de los siguientes métodos:
 * <ul>
 * <li>mensajeInicio()</li>
 * <li>reiniciaTableros()</li>
 * <li>imprimeTableroJ1()</li>
 * <li>imprimeTableroJ2()</li>
 * <li>compruebaFila()</li>
 * <li>compruebaColumna()</li>
 * <li>transformaFila()</li>
 * <li>pideCoordenadaFila()</li>
 * <li>pideCoordenadaColumna()</li>
 * <li>compruebaPosicionBarco()</li>
 * <li>insertaBarco()</li>
 * <li>asignaPosicionBarcosJ1()</li>
 * <li>asignaPosicionBarcosJ2()</li>
 * <li>validaDisparo()</li>
 * <li>disparoJ1()</li>
 * <li>disparoJ2()</li>
 * <li>tiradaAleatoria()</li>
 * <li>preguntaReinicio()</li>
 * <li>reiniciarJuego()</li>
 * <li>esperaSegundos()</li>
 * </ul>
 * 
 * Además es necesario importar la clase Barco para crear 6 instancias para el Jugador 1 y otras 6 para el Jugador 2 que harán uso de sus respectivos métodos 
 * que están descritos en el JavaDoc de la clase Barco.<br><br>
 * 
 * 
 * Posibles futuras mejoras:
 * <ul>
 * <li>El empleo de un método que borre la pantalla en vez de usar una susesión de saltos de línea para desplazar los tableros impresos en pantalla.</li>
 * <li>Implementar un sistema de juego para 1 jugador, ello implica crear una IA capaz de jugar contra el usuario.</li>
 * <li>Considero interesante ser capaz de implementar un sistema de juego en red, aunque sea a través de LAN, para que los jugadores no tuvieran que usar el mismo monitor a riesgo de ver el tablero del rival.</li>
 * <li>Finalmente, me gustaría ser capaz de dotar a este juego de una interfez gráfica, aunque sea sencilla.</li>
 * </ul>
 * 
 * 
 * Este juego ha sido creado por:
 * 
 * @author Francisco Javier González Sabariego.
 * 
 * @version 1.0  //  Fecha: 28/01/2019
 * 
 */
public class HundirLaFlota {
  public static void main(String[] args) {
    
    
    //Variables:
    String [][] tablero1a = new String [12][12];          //Tablero de juego del jugador 1.
    
    String [][] tablero1b = new String [12][12];          //Tablero de impactos que ha hecho el jugador 1 en el tablero del jugador 2.
    
    String [][] tablero2a = new String [12][12];          //Tablero de juego del jugador 2.
    
    String [][] tablero2b = new String [12][12];          //Tablero de impactos que ha hecho el jugador 2 en el tablero del jugador 1.
    
    int numeroJugadores = 2;                              //Variable que define el número de jugadores, en principio valdrá 2 puesto que no he programado el modo 1 jugador.
    
    int tiradaAzar;                                       //Variable que designa qué jugador empieza el juego si: <50 empieza Jugador 1, si >50 empieza Jugador 2.    
    
    boolean turnoJ1 = false;                                
    
    boolean turnoJ2 = false;
    
    boolean inicioJuego = true;
    
    boolean hayGanador = false;
    
    boolean reiniciarJuego = false; 
    
    
    
    //Barcos Jugador 1:
    Barco portaavionesJ1 = new Barco("Portaaviones", 5, 1);
    
    Barco acorazadoJ1 = new Barco("Acorazado", 4, 1);
    
    Barco destructorJ1 = new Barco("Destructor", 4, 1);
    
    Barco cruceroJ1 = new Barco("Crucero", 3, 1);
    
    Barco fragataJ1 = new Barco("Fragata", 3, 1);
    
    Barco submarinoJ1 = new Barco("Submarino", 2, 1);
    
    
    
    //Barcos Jugador 2:
    Barco portaavionesJ2 = new Barco("Portaaviones", 5, 2);
    
    Barco acorazadoJ2 = new Barco("Acorazado", 4, 2);
    
    Barco destructorJ2 = new Barco("Destructor", 4, 2);
    
    Barco cruceroJ2 = new Barco("Crucero", 3, 2);
    
    Barco fragataJ2 = new Barco("Fragata", 3, 2);
    
    Barco submarinoJ2 = new Barco("Submarino", 2, 2);
    
    
    
    
    
    //################################     PROGRAMA     ################################\\
    
    
    
    
    
    //Iniciamos antes de nada dando un valor a cada casilla de los tableros del juego:
    
    reiniciaTableros(tablero1a, tablero1b, tablero2a, tablero2b);
    
    
    
    //Mensaje de inicio:
    
    mensajeInicio();
    
    System.out.print("\n\nBienvenido al juego de hundir la flota.");
    
    
    
    //Menu (pendiente de realizar):
    
//    do {
//      
//    } while();
    
    
    
    //Modo 1 jugador:    //Pendiente de realizar
    
    if (numeroJugadores==1) {
      
    }
    
    
    
        
    //Modo 2 jugadores:
    
    if (numeroJugadores==2) {
      
      //Mientras no haya ganador se repite el bucle mediente el cual se van turnando los jugadores:
      while (!hayGanador) {        
        
        
        
        //Si tras terminar una partida se decide reiniciar el juego::
        if (reiniciarJuego) {
          
          
          //Llamamos al método reiniciarJuego():
          reiniciarJuego(tablero1a, tablero1b, tablero2a, tablero2b, 
              portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1, 
              portaavionesJ2, acorazadoJ2, destructorJ2, cruceroJ2, fragataJ2, submarinoJ2);
          
          
          //Reiniciamos al ganador y los dos turnos de los jugadores:
          hayGanador = false;
          
          turnoJ1 = false;
          
          turnoJ2 = false;
          
          
          //Reiniciado el juego devolvemos a false el booleano que inidice que debemos reiniciarlo:
          reiniciarJuego = false;
          
          
          //Y ponemos en verdadero el booleano que activa el proceso de montaje del jeugo (ubicación de barcos y decisión del jugador que abre el primer turno):
          inicioJuego = true;
          
        }
        
        
        
        //Aquí inicia el proceso de ubicar los barcos en sus correspondientes tableros y después se decide quién inicia el primer turno.
        if (inicioJuego) {
          
          imprimeTableroJ1(tablero1a, tablero1b);
          
          asignaPosicionBarcosJ1(tablero1a, tablero1b, portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1);
          
          
          System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); //Tengo que averiguar si hay alguna forma de borrar la pantalla
          
          
          imprimeTableroJ2(tablero2a, tablero2b);
          
          asignaPosicionBarcosJ2(tablero2a, tablero2b, portaavionesJ2, acorazadoJ2, destructorJ2, cruceroJ2, fragataJ2, submarinoJ2);
          
          
          //Una vez asignados los barcos en su sitio se decide quién empieza el juego:
          tiradaAzar = tiradaAleatoria();
          
                    
          if (tiradaAzar<50) {
            
            turnoJ1 = true;
            
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); //"Borramos pantalla"
            System.out.println("\n\nComienza el Jugador 1.");
            
          } else {
            
            turnoJ2 = true;
            
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); //"Borramos pantalla"
            System.out.println("\n\nComienza el Jugador 2.");
            
          }
          
          //Acabada la fase de inicio de juego se van alternando los turnos hasta decidir un ganador
          inicioJuego = false;
          
          System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); //"Borramos pantalla"
          System.out.println("\n\nEl juego comienza en 5 segundos.");
          
          esperaSegundos(5);
                    
        }
        
        
        
        
        //Turnos de juego:
        
        //JUGADOR 1:
        while (turnoJ1) {
          
          
          //El jugador 1 dispara:
          System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); //"Borramos pantalla"
          System.out.println("\n\nTurno del Jugador 1. Dipara:");
          
          imprimeTableroJ1(tablero1a, tablero1b);
          
          disparoJ1(tablero2a, tablero1b, portaavionesJ2, acorazadoJ2, destructorJ2, cruceroJ2, fragataJ2, submarinoJ2);
          
          
          //Comprobamos si el jugador 1 ha ganado:
          if (portaavionesJ2.hundidoBarco && acorazadoJ2.hundidoBarco && destructorJ2.hundidoBarco && 
              cruceroJ2.hundidoBarco && fragataJ2.hundidoBarco && submarinoJ2.hundidoBarco) {
            
            
            //Si ha ganado le felicitamos:
            System.out.println("\n\nJUGADOR 1 HA GANADO. ¡¡ENHORABUENA!!");
            
            
            //Decimos que hay ganador para no volver a entrar en el bucle, salvo que el jugador decida reiniciar la partida:
            hayGanador = true;
            
            
            //Llamamos al método preguntar reinicio para comprobar si desea volver a jugar:
            if (preguntaReinicio()) {
              
              //Si el jugador desea jugar volvemos a falso la variable "hayGanador", de lo contrario no entraría en el bucle y se cerraría el programa:
              hayGanador = false;
              
              
              //Si el jugador ha ganado debemos ponerlo en false para que no se repita su bucle:
              turnoJ1 = false;
              
              
              //Y ponemos en verdadero el booleano de reiniciarJuego:
              reiniciarJuego = true;
              
              System.out.println("\n\nEl juego se reiniciará en breve.");
              
              esperaSegundos(5);
              
            }
            
            
          } else {
            
            turnoJ1 = false;
            
            turnoJ2 = true;
            
            
            //Le damos 3 segundos al jugador para que pueda leer el resultado de su disparo.
            esperaSegundos(3);
            
            
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); //"Borramos pantalla"
            
            //Si se ha terminado el juego y se desea reiniciar no volveremos a indicar el cambio de turno:
              
            System.out.println("\n\nTurno del jugador 2 comienza en 5 segundos.");
            
            esperaSegundos(5);
            
          }
        
        }
        
        //JUGADOR 2:
        while (turnoJ2) {
          
          
        //El jugador 2 dispara:
          System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); //"Borramos pantalla"
          System.out.println("\n\nTurno del Jugador 2. Dipara:");
          
          
          imprimeTableroJ2(tablero2a, tablero2b);
          
          disparoJ2(tablero1a, tablero2b, portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1);
          
          
          //Comprobamos si el jugador 1 ha ganado:
          if (portaavionesJ1.hundidoBarco && acorazadoJ1.hundidoBarco && destructorJ1.hundidoBarco && 
              cruceroJ1.hundidoBarco && fragataJ1.hundidoBarco && submarinoJ1.hundidoBarco) {
            
            
            //Si ha ganado le felicitamos:
            System.out.println("\n\nJUGADOR 2 HA GANADO. ¡¡ENHORABUENA!!");
            
            
            //Decimos que hay ganador para no volver a entrar en el bucle, salvo que el jugador decida reiniciar la partida:
            hayGanador = true;
            
            
            //Llamamos al método preguntar reinicio para comprobar si desea volver a jugar:
            if (preguntaReinicio()) {
              
              //Si el jugador desea jugar volvemos a falso la variable "hayGanador", de lo contrario no entraría en el bucle y se cerraría el programa:
              hayGanador = false;
              
              
              //Si el jugador ha ganado debemos ponerlo en false para que no se repita su bucle:
              turnoJ2 = false;
              
              
              //Y ponemos en verdadero el booleano de reiniciarJuego:
              reiniciarJuego = true;
              
              
              System.out.println("\n\nEl juego se reiniciará en breve.");
              
              esperaSegundos(5);
              
            }
            
            
          } else {
            
            turnoJ1 = true;
            
            turnoJ2 = false;
            
            
            //Le damos 3 segundos al jugador para que pueda leer el resultado de su disparo.
            esperaSegundos(3);
            
            
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); //"Borramos pantalla"
            
            //Si se ha terminado el juego y se desea reiniciar no volveremos a indicar el cambio de turno:
              
            System.out.println("\n\nTurno del jugador 1 comienza en 5 segundos.");
            
            esperaSegundos(5);
            
          }
          
        }
        
      }
      
    }
    
  }
  
  
  
  
  
  //#####################################     Métodos     #####################################\\
  
  
  
  
  
  /**
   * Este método nos imprime un sencillo logotipo del juego hecho con un ASCII art.
   */
  public static void mensajeInicio() {
    
    System.out.print("\n                $$$$$$$$$$$$$");
    System.out.print("\n           $$$$$$$    |    $$$$$$$");
    System.out.print("\n       $$$$$$         |        $$$$$$$");
    System.out.print("\n     $$$$             |             $$$$");
    System.out.print("\n    $$$               |               $$$");
    System.out.print("\n   $$     25___       |                 $$");
    System.out.print("\n  $$      20___       |                  $$");
    System.out.print("\n $$       15___       |                   $$");
    System.out.print("\n $$       10___       |                   $$");
    System.out.print("\n$$         5___       |                    $$");
    System.out.print("\n$$____________________|____________________$$");
    System.out.print("\n$$         5___       |                    $$");
    System.out.print("\n$$        10___       |                    $$");
    System.out.print("\n $$       15___       |    _    _ _    _ _$$ _ _____ _____ _____              ");
    System.out.print("\n $$       20___       |   | |  | | |  | | \\ | |  __ \\_   _|  __ \\             ");
    System.out.print("\n  $$      25___       |   | |__| | |  | |  \\| | |  | || | | |__) |            ");
    System.out.print("\n   $$                 |   |  __  | |  | | . ` | |  | || | |  _  /             ");
    System.out.print("\n    $$$               |   | |  | | |__| | |\\  | |__| || |_| | \\ \\             ");
    System.out.print("\n     $$$$             |   |_|  |_|\\____/|_| \\_|_____/_____|_|__\\_\\_____       ");
    System.out.print("\n       $$$$$$         |   | |        /\\     |  ____| |    / __ \\__   __|/\\    ");
    System.out.print("\n           $$$$$$$    |   | |       /  \\    | |__  | |   | |  | | | |  /  \\   ");
    System.out.print("\n                $$$$$$$$$$| |      / /\\ \\   |  __| | |   | |  | | | | / /\\ \\  ");
    System.out.print("\n                          | |____ / ____ \\  | |    | |___| |__| | | |/ ____ \\ ");
    System.out.print("\n                          |______/_/    \\_\\ |_|    |______\\____/  |_/_/    \\_\\");
    
  }
  
  
  /**
   * Este método se encarga de resetear los tableros de juego.
   * 
   * @param tablero1a
   * @param tablero1b
   */
  public static void reiniciaTableros(String tablero1a [][], String tablero1b [][], String tablero2a [][], String tablero2b [][]) {
    
    for (int i=0; i<=11; i++) {
      
      for (int j=0; j<=11; j++) {
        
        tablero1a[i][j] = "_|";
        
      }
      
    }
    
    
    for (int i=0; i<=11; i++) {
      
      for (int j=0; j<=11; j++) {
        
        tablero1b[i][j] = "_|";
        
      }
      
    }
    
    
for (int i=0; i<=11; i++) {
      
      for (int j=0; j<=11; j++) {
        
        tablero2a[i][j] = "_|";
        
      }
      
    }
    
    
    for (int i=0; i<=11; i++) {
      
      for (int j=0; j<=11; j++) {
        
        tablero2b[i][j] = "_|";
        
      }
      
    }
    
  }
  
  
  /**
   * Este método se encarga de imprimir los dos tableros correspondientes al jugador 1:
   * 
   * <ul>
   * <li>En uno, "Tablero Jugador 1", tenemos la posición de los barcos del jugador 1 y los impactos que el jugador 2 ha hecho en su tablero.</li>
   * <li>En el otro, "Impactos del Jugador 1", tenemos los impactos que el jugador 1 ha realizado en el tablero contrario.</li>
   * </ul>
   * 
   * @param tablero1a
   * @param tablero1b
   */
  public static void imprimeTableroJ1(String tablero1a [][], String tablero1b[][]) {
    
    //Cabecera del tablero:
    
    System.out.print("\n\n\n\n Tablero Jugador 1                               Impactos del Jugador 1");
    
    System.out.print("\n\n |1|2|3|4|5|6|7|8|9|10                           |1|2|3|4|5|6|7|8|9|10");
    
    
    
    //Impresión del tablero:
    
    for (int i=1; i<11; i++) {
      
      
      //Imprimimos el margen izquierdo del primer tablero, la letra correspondiente a la fila actual [A-J]:
      
      switch (i) {
          
        case 1: System.out.print("\nA|");
          break;
        case 2: System.out.print("\nB|");
          break;
        case 3: System.out.print("\nC|");
          break;
        case 4: System.out.print("\nD|");
          break;
        case 5: System.out.print("\nE|");
          break;
        case 6: System.out.print("\nF|");
          break;
        case 7: System.out.print("\nG|");
          break;
        case 8: System.out.print("\nH|");
          break;
        case 9: System.out.print("\nI|");
          break;
        case 10: System.out.print("\nJ|");
          break;      
      }
      
      
      //Comenzamos a imprimir las filas del array del primer tablero tablero:
      
      for (int j=1; j<27; j++) {
        
        
        /*
         * Como no nos interesa imprimir los márgenes del array (fila y columna 0 y fila y columna 11)
         * vamos a imprimir desde la columna 1 y la fila 1, hasta la columna 10 y fila 10.
         * 
         * De esta forma dejamos un margen en blanco que nos servirá para comprobar que la posición
         * en la que el usuario desea ubicar un barco es correcta y no toca con otro barco.
         */
        
        if (j>0 && j<11) {
          System.out.print(tablero1a[i][j]);
        }
        
        
        /*
         * Cuando la variable de control "j" valga 10 significa que habremos imprimido la décima y última columna imprimible del tablero
         * y, por tanto, cerramos la fila del primer tablero con su margen derecho, es decir, con la letra correspondiente a su fila [A-J],
         * además introducimos 25 espacios en blanco y de nuevo la letra correspondiente que será el margen izquierdo del segundo tablero, 
         * este nuevo tablero mostrará en pantalla los impactos que ha hecho el jugador actal en el tablero contrario.
         */
        
        if (j==10) {
          
          switch (i) {
          
          case 1: System.out.print("A                         A|");
            break;
          case 2: System.out.print("B                         B|");
            break;
          case 3: System.out.print("C                         C|");
            break;
          case 4: System.out.print("D                         D|");
            break;
          case 5: System.out.print("E                         E|");
            break;
          case 6: System.out.print("F                         F|");
            break;
          case 7: System.out.print("G                         G|");
            break;
          case 8: System.out.print("H                         H|");
            break;
          case 9: System.out.print("I                         I|");
            break;
          case 10: System.out.print("J                         J|");
            break;
        
          }
          
        }
        
        
        /*
         * Una vez se ha imprimido el margen izquierdo del tablero1b toca imprimir el propio tablero.
         * 
         * Para realizar esta operación imprimiremos la fila actual hasta la columna 10 correspondiendo a las coordenadas: [i][j-(10)]
         * como la variable de control "j" comienza valiendo 11, no podemos empezar imprimiendo la columna 11, por tanto, lo que vamos
         * a imprimir será el equivalente a la posición del valor "j" menos el mismo valor columnas que posee el tablero (10) ejemplo: [1][11-(10)]
         * de esta forma nos aseguramos que imprimiremos el tablero2 desde la columna 1 hasta la columna 10 del mismo.
         */     
        
        if (j>10 && j<21) {
          System.out.print(tablero1b[i][j-(10)]);
        }
        
        
        //Finalmente, cuando "j" valga 21 habremos impreso una fila completa del tablero2 y solo nos queda añadir la letra correspondiente a su fila [A-Z]:
        
        if (j==21) {
          
          switch (i) {
          
          case 1: System.out.print("A");
            break;
          case 2: System.out.print("B");
            break;
          case 3: System.out.print("C");
            break;
          case 4: System.out.print("D");
            break;
          case 5: System.out.print("E");
            break;
          case 6: System.out.print("F");
            break;
          case 7: System.out.print("G");
            break;
          case 8: System.out.print("H");
            break;
          case 9: System.out.print("I");
            break;
          case 10: System.out.print("J");
            break;
        
          }
          
        }
        
      }
      
    }
    
    
    //Impresos ambos tableros, añadimos como pie de tablero las coordenadas numéricas:
    
    System.out.print("\n |1|2|3|4|5|6|7|8|9|10                           |1|2|3|4|5|6|7|8|9|10");
    
  }
  
  
  /**
   * Este método se encarga de imprimir los dos tableros correspondientes al jugador 2:
   * 
   * <ul>
   * <li>En uno, "Tablero Jugador 2", tenemos la posición de los barcos del jugador 2 y los impactos que el jugador 1 ha hecho en su tablero.</li>
   * <li>En el otro, "Impactos del Jugador 2", tenemos los impactos que el jugador 2 ha realizado en el tablero contrario.</li>
   * </ul>
   * 
   * @param tablero2a
   * @param tablero2b
   */
  public static void imprimeTableroJ2(String tablero2a [][], String tablero2b[][]) {
    
    //Cabecera del tablero:
    
    System.out.print("\n\n\n\n Tablero Jugador 2                               Impactos del Jugador 2");
    
    System.out.print("\n\n |1|2|3|4|5|6|7|8|9|10                           |1|2|3|4|5|6|7|8|9|10");
    
    
    
    //Impresión del tablero:
    
    for (int i=1; i<11; i++) {
      
      
      //Imprimimos el margen izquierdo del primer tablero, la letra correspondiente a la fila actual [A-J]:
      
      switch (i) {
          
        case 1: System.out.print("\nA|");
          break;
        case 2: System.out.print("\nB|");
          break;
        case 3: System.out.print("\nC|");
          break;
        case 4: System.out.print("\nD|");
          break;
        case 5: System.out.print("\nE|");
          break;
        case 6: System.out.print("\nF|");
          break;
        case 7: System.out.print("\nG|");
          break;
        case 8: System.out.print("\nH|");
          break;
        case 9: System.out.print("\nI|");
          break;
        case 10: System.out.print("\nJ|");
          break;      
      }
      
      
      //Comenzamos a imprimir las filas del array del primer tablero tablero:
      
      for (int j=1; j<27; j++) {
        
        
        /*
         * Como no nos interesa imprimir los márgenes del array (fila y columna 0 y fila y columna 11)
         * vamos a imprimir desde la columna 1 y la fila 1, hasta la columna 10 y fila 10.
         * 
         * De esta forma dejamos un margen en blanco que nos servirá para comprobar que la posición
         * en la que el usuario desea ubicar un barco es correcta y no toca con otro barco.
         */
        
        if (j>0 && j<11) {
          System.out.print(tablero2a[i][j]);
        }
        
        
        /*
         * Cuando la variable de control "j" valga 10 significa que habremos imprimido la décima y última columna imprimible del tablero
         * y, por tanto, cerramos la fila del primer tablero con su margen derecho, es decir, con la letra correspondiente a su fila [A-J],
         * además introducimos 25 espacios en blanco y de nuevo la letra correspondiente que será el margen izquierdo del segundo tablero, 
         * este nuevo tablero mostrará en pantalla los impactos que ha hecho el jugador actal en el tablero contrario.
         */
        
        if (j==10) {
          
          switch (i) {
          
          case 1: System.out.print("A                         A|");
            break;
          case 2: System.out.print("B                         B|");
            break;
          case 3: System.out.print("C                         C|");
            break;
          case 4: System.out.print("D                         D|");
            break;
          case 5: System.out.print("E                         E|");
            break;
          case 6: System.out.print("F                         F|");
            break;
          case 7: System.out.print("G                         G|");
            break;
          case 8: System.out.print("H                         H|");
            break;
          case 9: System.out.print("I                         I|");
            break;
          case 10: System.out.print("J                         J|");
            break;
        
          }
          
        }
        
        
        /*
         * Una vez se ha imprimido el margen izquierdo del tablero2b toca imprimir el propio tablero.
         * 
         * Para realizar esta operación imprimiremos la fila actual hasta la columna 10 correspondiendo a las coordenadas: [i][j-(10)]
         * como la variable de control "j" comienza valiendo 11, no podemos empezar imprimiendo la columna 11, por tanto, lo que vamos
         * a imprimir será el equivalente a la posición del valor "j" menos el mismo valor columnas que posee el tablero (10) ejemplo: [1][11-(10)]
         * de esta forma nos aseguramos que imprimiremos el tablero2 desde la columna 1 hasta la columna 10 del mismo.
         */ 
        
        if (j>10 && j<21) {
          System.out.print(tablero2b[i][j-(10)]);
        }
        
        
        //Finalmente, cuando "j" valga 21 habremos impreso una fila completa del tablero2 y solo nos queda añadir la letra correspondiente a su fila [A-Z]:
        
        if (j==21) {
          
          switch (i) {
          
          case 1: System.out.print("A");
            break;
          case 2: System.out.print("B");
            break;
          case 3: System.out.print("C");
            break;
          case 4: System.out.print("D");
            break;
          case 5: System.out.print("E");
            break;
          case 6: System.out.print("F");
            break;
          case 7: System.out.print("G");
            break;
          case 8: System.out.print("H");
            break;
          case 9: System.out.print("I");
            break;
          case 10: System.out.print("J");
            break;
        
          }
          
        }
        
      }
      
    }
    
    
    //Impresos ambos tableros, añadimos como pie de tablero las coordenadas numéricas:
    
    System.out.print("\n |1|2|3|4|5|6|7|8|9|10                           |1|2|3|4|5|6|7|8|9|10");
    
  }
  
  
  /**
   * Este método comprueba si la letra que ha eligido el usuario es correcta dentro del rango [A-Z] y retornará un booleano.
   * 
   * @param fila
   * @return
   */
  public static boolean compruebaFila(String fila) {
    
    String cadena = "ABCDEFGHIJ";
    
    boolean resultado=false;
    
    for (int i=0; i<cadena.length(); i++) {
      
      if (fila.equals(cadena.substring(i, i+1))) {
       
        resultado=true;
        
        break;
                
      } else {
        
        resultado=false;
        
      }
      
    }
    
    return resultado;
    
  }
  
  
  
  /**
   * Este método comprueba si el número que ha eligido el usuario es correcto dentro del rango [1-10] y retornará un booleano.
   * 
   * @param columna
   * @return
   */
  public static boolean compruebaColumna(int columna) {
    
    if (columna >= 1 && columna <= 10) {
      
      return true;
      
    }
    
    return false;
    
  }
  
  /**
   * Reasigna el valor String de la fila a un valor entero para la coordenada de la fila.
   * 
   * @return
   */
  public static int transformaFila(String fila) {
    
    int numeroFila=0;
    
    
    switch (fila) {
    
      case "A":        
        numeroFila = 1;
        break;      
      
      case "B":        
        numeroFila = 2;
        break;
        
      case "C":        
        numeroFila = 3;
        break;
        
      case "D":        
        numeroFila = 4;
        break;
        
      case "E":        
        numeroFila = 5;
        break;
            
      case "F":        
        numeroFila = 6;
        break;      
      
      case "G":        
        numeroFila = 7;
        break;
        
      case "H":        
        numeroFila = 8;
        break;
        
      case "I":        
        numeroFila = 9;
        break;
        
      case "J":        
        numeroFila = 10;
        break;    
    
    }
    
    return numeroFila;
    
  }
  
  
  /**
   * Método encargado de pedir la coordenada de la fila al jugador hasta que ésta sea correcta. Para ello hace uso del método "compruebaFila()".
   * 
   * Una vez verificada la coordenada retorna el String de la misma.
   * 
   * @return
   */
  public static String pideCoordenadaFila() {
    
    String fila="";
    
    Scanner s = new Scanner(System.in);
    
    /*
     * Pide la coordenada fila, mientras la misma no sea válida volverá a pedirla.
     * 
     * Una vez se entregue un valor aceptado retornará dicho valor.
     */
    do {
      
      System.out.print("\n\nInserta el valor de la fila: ");
      fila = s.nextLine().toUpperCase();
      
    } while (!compruebaFila(fila));
    
    
    return fila;
    
  }
  
  
  /**
   * Método encargado de pedir la coordenada de la columna al jugador hasta que ésta sea correcta. Para ello hace uso del método "compruebaColumna()".
   * 
   * Una vez verificada la coordenada retorna el entero de la misma.
   * 
   * @return
   */
  public static int pideCoordenadaColumna() {
    
    int columna=0;
    
    Scanner s = new Scanner(System.in);
    
    /*
     * Pide la coordenada columna, mientras la misma no sea válida volverá a pedirla.
     * 
     * Una vez se entregue un valor aceptado retornará dicho valor.
     */
    do {
      
      System.out.print("\n\nInserta el valor de la columna: ");
      columna = s.nextInt();
      
    } while (!compruebaColumna(columna));
    
    
    return columna;
    
  }
  
  
  /**
   * Este método comprueba si las coordenadas que pasamos como posición inicial del barco son válidas para insertar el barco en el tablero.
   * 
   * En caso de que no sean aptas volverá a pedir las coordenadas.
   * 
   * @param barco
   * @param tablero
   * @param fila
   * @param columna
   * @return
   */
  public static boolean compruebaPosicionBarco(Barco barco, String [][] tablero, int fila, int columna) {
    
    String comprueba="";
    
    boolean salida=false;
    
    
    /*
     * Si desborda uno de los ejes (fila o columna) activamos la posición invertida del barco.
     * 
     * Este booleano permitirá que el barco sea leído desde su posición inicial en sentido inverso, es decir, de derecha a izquierda y de abajo a arriba.
     */
    if (!barco.getVerticalidad() && barco.getCasillas()-1+columna>10) {
      
      barco.setPosicionInvertida();
      
    } else if (barco.getVerticalidad() && barco.getCasillas()-1+fila>10) {
      
      barco.setPosicionInvertida();
      
    }
    
    
    /*
     * Aquí iniciamos la comprobación en función de:
     * 
     *  -Si el barco está en posición horizontal o vertical.
     *  -Si el barco debe ser leído de forma estándar o a la inversa.
     * 
     * Lo que se pretende en este punto es comprobar que a la hora de ubicar en el tablero el barco actual
     * no haya otro barco justo al lado o en la esquina con la que pueda colisionar y, además, evitar que
     * se pueda sobreescribir un barco grande sobre uno más pequeño.
     * 
     * Por tanto este doble bucle concatenará el valor del tablero actual usando las coordenadas que los bucles 
     * vayan asignando con el objetido de leer las casillas al rededor y en sobre las cuales queremos insertar el barco.
     */
    if (!barco.getVerticalidad() && !barco.posicionInvertida) {             //Hace la comprobación si el barco está horizontal y NO se lee a la inversa (de izquierda a derecha):
      
      for (int i=fila-1; i<=fila+1; i++) {
        
        for (int k=columna-1; k<=(columna+barco.casillasActuales); k++) {      
          
          comprueba += tablero[i][k];
          
        }
        
      }
      
    } else if(!barco.getVerticalidad() && barco.posicionInvertida) {        //Hace la comprobación si el barco está horizontal y se lee a la inversa (de derecha a izquierda):
      
      for (int i=fila-1; i<=fila+1; i++) {
        
        for (int k=columna+1; k>=(columna-barco.casillasActuales); k--) {
          
          comprueba += tablero[i][k];
          
        }
        
      } 
      
    } else if (barco.getVerticalidad() && !barco.posicionInvertida) {       //Hace la comprobación si el barco está vertical y NO se lee a la inversa (de arriba a abajo):
      
      for (int k=columna-1; k<=columna+1; k++) {
        
        for (int i=fila-1; i<=(fila+barco.casillasActuales); i++) {
          
          comprueba += tablero[i][k];
          
        }
        
      } 
      
    } else {                                                                //Hace la comprobación si el barco está vertical y se lee a la inversa (de abajo a arriba):
      
      for (int k=columna-1; k<=columna+1; k++) {
        
        for (int i=fila+1; i>=(fila-barco.casillasActuales); i--) {
          
          comprueba += tablero[i][k];
          
        }
        
      } 
      
    }
    
    
    
    /*
     * Llegado a este punto solo falta comprobar que la cadena "comprueba" no haya concatenado ninguna letra "B",
     * de lo contrario implica que estamos tocando o vamos a sobreescribir otro barco y, por tanto, habría que
     * volver a pedir las coordenadas. En este caso rtornaríamos un "false".
     * 
     * Si todo ha salido bien y no hay ninguna letra "B" en la cadena la posición es correcta y retornamos un "true".
     */    
    for (int i=0; i<comprueba.length()-1; i++) {
      
      if (!comprueba.substring(i, i+1).equals("B")) {
        
        salida=true;
        
      } else {
        
        salida=false;        
        break;
        
      }
      
    }
    
    if (!salida) {
      
      System.out.print("\n\nCoordenadas inválidas, no puedes poner un barco tocando a otro barco ni tampoco sobreescribirlo");
      
    }
    
    return salida;
    
  }
  
  
  
  /**
   * En caso de que las coordenadas sean correctas el barco se guarda en el array del tablero correspondiente. Este método necesita hacer uso
   * del método "compruebaPosicion()".
   * 
   * @param barco
   * @param tablero
   * @param fila
   * @param columna
   */
  public static void insertaBarco(Barco barco, String tablero [][], int fila, int columna) {
    
    if (compruebaPosicionBarco(barco, tablero, fila, columna)) {
      
      if (!barco.getVerticalidad() && !barco.posicionInvertida) {             //Inserta el barco en horizontal de izquierda a derecha
        
        for (int i=fila; i<=fila; i++) {
          
          for (int k=columna; k<(columna+barco.casillasActuales); k++) {      
            
            tablero[i][k] ="B|";
            
          }
          
        }
        
      } else if(!barco.getVerticalidad() && barco.posicionInvertida) {        //Inserta el barco en horizontal de derecha a izquierda
        
        for (int i=fila; i<=fila; i++) {
          
          for (int k=columna; k>(columna-barco.casillasActuales); k--) {
            
            tablero[i][k] ="B|";
            
          }
          
        } 
        
      } else if (barco.getVerticalidad() && !barco.posicionInvertida) {       //Inserta el barco en vertical de izquierda a derecha
        
        for (int k=columna; k<=columna; k++) {
          
          for (int i=fila; i<(fila+barco.casillasActuales); i++) {
            
            tablero[i][k] ="B|";
            
          }
          
        } 
        
      } else {                                                                //Inserta el barco en vertical de derecha a izquierda
        
        for (int k=columna; k<=columna; k++) {
          
          for (int i=fila; i>(fila-barco.casillasActuales); i--) {
            
            tablero[i][k] ="B|";
            
          }
          
        } 
        
      }
      
    }
    
  }
  
  
  
  /**
   * A través de este método el Jugador 1 podrá ir introduciendo sus barcos en el tablero de juego. Este método hace uso de los siguientes métodos:
   * 
   * <ul>
   * <li>pideCoordenadaFila()</li>
   * <li>pideCoordenadaColumna()</li>
   * <li>transformaFila()</li>
   * <li>insertaBarco()</li>
   * <li>imprimeTableroJ1()</li>
   * </ul>
   * 
   * @param tablero1a
   * @param tablero1b
   * @param portaavionesJ1
   * @param acorazadoJ1
   * @param destructorJ1
   * @param cruceroJ1
   * @param fragataJ1
   * @param submarinoJ1
   */
  public static void asignaPosicionBarcosJ1(String tablero1a[][], String tablero1b[][], 
      Barco portaavionesJ1, Barco acorazadoJ1, Barco destructorJ1, Barco cruceroJ1, Barco fragataJ1, Barco submarinoJ1) {
    
    //Scanner:
    Scanner s = new Scanner(System.in);
    
    //Variables:
    int fila;
    
    int columna;
    
    String respuesta = "";
    
    System.out.print("\n\nVamos a ubicar los barcos en el tablero.");
    
    

    
    //########################     PORTAAVIONES     ########################\\
    
    System.out.print("\n\nTurno del " + portaavionesJ1.nombreBarco + " que posee " + portaavionesJ1.getCasillas() + " casillas.");
    
    
    //Pedimos al usuario si desea posicionar el barco en vertical, mientras la respuesta no sea correcta volveremos a preguntar:
    do {
      
      System.out.print("\n\n¿Desea que el barco se inserte en el tablero en posición vertical? (S/N)");
      respuesta = s.nextLine().toUpperCase();
      
    } while(!(respuesta.contentEquals("S") || respuesta.contentEquals("N")));
    
    
    //En caso de que sí reseteamos el valor de respuesta y llamamos al método "getVerticalidad()" del objeto:
    if (respuesta.equals("S")) {
      
      respuesta = "";
      
      portaavionesJ1.setVerticalidad();
      
    }
    
    
    /*
     * A continuación pedimos las coordenadas en las que se va a ubicar el barco, si las coordenadas se salen del tablero
     * o la posición del barco es incorrecta volveremos a pedirlas:
     */
    do {
      
      fila = transformaFila(pideCoordenadaFila());
      
      columna = pideCoordenadaColumna();
      
    } while (!compruebaPosicionBarco(portaavionesJ1, tablero1a, fila, columna));
    
    
    //Si todo ha salido bien llamamos a la función "insertaBarco()" que guardará el barco en el array tablero correspondiente:
    insertaBarco(portaavionesJ1, tablero1a, fila, columna);
    
    
    //Guardamos las coordenadas de la casilla inicial:
    portaavionesJ1.setCasillaInicial(fila, columna);
    
    
    //Volvemos a imprimir al tablero para mostrar al jugador el resultado:
    imprimeTableroJ1(tablero1a, tablero1b);
    
    
    
    
    //########################     ACORAZADO     ########################\\
    
    System.out.print("\n\nTurno del " + acorazadoJ1.nombreBarco + " que posee " + acorazadoJ1.getCasillas() + " casillas.");
    
    
    //Pedimos al usuario si desea posicionar el barco en vertical, mientras la respuesta no sea correcta volveremos a preguntar:
    do {
      
      System.out.print("\n\n¿Desea que el barco se inserte en el tablero en posición vertical? (S/N)");
      respuesta = s.nextLine().toUpperCase();
      
    } while(!(respuesta.contentEquals("S") || respuesta.contentEquals("N")));
    
    
    //En caso de que sí reseteamos el valor de respuesta y llamamos al método "getVerticalidad()" del objeto:
    if (respuesta.equals("S")) {
      
      respuesta = "";
      
      acorazadoJ1.setVerticalidad();
      
    }
    
    
    /*
     * A continuación pedimos las coordenadas en las que se va a ubicar el barco, si las coordenadas se salen del tablero
     * o la posición del barco es incorrecta volveremos a pedirlas:
     */
    do {
      
      fila = transformaFila(pideCoordenadaFila());
      
      columna = pideCoordenadaColumna();
      
    } while (!compruebaPosicionBarco(acorazadoJ1, tablero1a, fila, columna));
    
    
    //Si todo ha salido bien llamamos a la función "insertaBarco()" que guardará el barco en el array tablero correspondiente:
    insertaBarco(acorazadoJ1, tablero1a, fila, columna);
    
    
    //Guardamos las coordenadas de la casilla inicial:
    acorazadoJ1.setCasillaInicial(fila, columna);
    
    
    //Volvemos a imprimir al tablero para mostrar al jugador el resultado:
    imprimeTableroJ1(tablero1a, tablero1b);
    
    
    
    
    //########################     DESTRUCTOR     ########################\\
    
    System.out.print("\n\nTurno del " + destructorJ1.nombreBarco + " que posee " + destructorJ1.getCasillas() + " casillas.");
    
    
    //Pedimos al usuario si desea posicionar el barco en vertical, mientras la respuesta no sea correcta volveremos a preguntar:
    do {
      
      System.out.print("\n\n¿Desea que el barco se inserte en el tablero en posición vertical? (S/N)");
      respuesta = s.nextLine().toUpperCase();
      
    } while(!(respuesta.contentEquals("S") || respuesta.contentEquals("N")));
    
    
    //En caso de que sí reseteamos el valor de respuesta y llamamos al método "getVerticalidad()" del objeto:
    if (respuesta.equals("S")) {
      
      respuesta = "";
      
      destructorJ1.setVerticalidad();
      
    }
    
    
    /*
     * A continuación pedimos las coordenadas en las que se va a ubicar el barco, si las coordenadas se salen del tablero
     * o la posición del barco es incorrecta volveremos a pedirlas:
     */
    do {
      
      fila = transformaFila(pideCoordenadaFila());
      
      columna = pideCoordenadaColumna();
      
    } while (!compruebaPosicionBarco(destructorJ1, tablero1a, fila, columna));
    
    
    //Si todo ha salido bien llamamos a la función "insertaBarco()" que guardará el barco en el array tablero correspondiente:
    insertaBarco(destructorJ1, tablero1a, fila, columna);
    
    
    //Guardamos las coordenadas de la casilla inicial:
    destructorJ1.setCasillaInicial(fila, columna);
    
    
    //Volvemos a imprimir al tablero para mostrar al jugador el resultado:
    imprimeTableroJ1(tablero1a, tablero1b);
    
    
    

    //########################     CRUCERO     ########################\\
    
    System.out.print("\n\nTurno del " + cruceroJ1.nombreBarco + " que posee " + cruceroJ1.getCasillas() + " casillas.");
    
    
    //Pedimos al usuario si desea posicionar el barco en vertical, mientras la respuesta no sea correcta volveremos a preguntar:
    do {
      
      System.out.print("\n\n¿Desea que el barco se inserte en el tablero en posición vertical? (S/N)");
      respuesta = s.nextLine().toUpperCase();
      
    } while(!(respuesta.contentEquals("S") || respuesta.contentEquals("N")));
    
    
    //En caso de que sí reseteamos el valor de respuesta y llamamos al método "getVerticalidad()" del objeto:
    if (respuesta.equals("S")) {
      
      respuesta = "";
      
      cruceroJ1.setVerticalidad();
      
    }
    
    
    /*
     * A continuación pedimos las coordenadas en las que se va a ubicar el barco, si las coordenadas se salen del tablero
     * o la posición del barco es incorrecta volveremos a pedirlas:
     */
    do {
      
      fila = transformaFila(pideCoordenadaFila());
      
      columna = pideCoordenadaColumna();
      
    } while (!compruebaPosicionBarco(cruceroJ1, tablero1a, fila, columna));
    
    
    //Si todo ha salido bien llamamos a la función "insertaBarco()" que guardará el barco en el array tablero correspondiente:
    insertaBarco(cruceroJ1, tablero1a, fila, columna);
    
    
    //Guardamos las coordenadas de la casilla inicial:
    cruceroJ1.setCasillaInicial(fila, columna);
    
    
    //Volvemos a imprimir al tablero para mostrar al jugador el resultado:
    imprimeTableroJ1(tablero1a, tablero1b);
    
    
    
    
    //########################     FRAGATA     ########################\\
    
    System.out.print("\n\nTurno de la " + fragataJ1.nombreBarco + " que posee " + fragataJ1.getCasillas() + " casillas.");
    
    
    //Pedimos al usuario si desea posicionar el barco en vertical, mientras la respuesta no sea correcta volveremos a preguntar:
    do {
      
      System.out.print("\n\n¿Desea que el barco se inserte en el tablero en posición vertical? (S/N)");
      respuesta = s.nextLine().toUpperCase();
      
    } while(!(respuesta.contentEquals("S") || respuesta.contentEquals("N")));
    
    
    //En caso de que sí reseteamos el valor de respuesta y llamamos al método "getVerticalidad()" del objeto:
    if (respuesta.equals("S")) {
      
      respuesta = "";
      
      fragataJ1.setVerticalidad();
      
    }
    
    
    /*
     * A continuación pedimos las coordenadas en las que se va a ubicar el barco, si las coordenadas se salen del tablero
     * o la posición del barco es incorrecta volveremos a pedirlas:
     */
    do {
      
      fila = transformaFila(pideCoordenadaFila());
      
      columna = pideCoordenadaColumna();
      
    } while (!compruebaPosicionBarco(fragataJ1, tablero1a, fila, columna));
    
    
    //Si todo ha salido bien llamamos a la función "insertaBarco()" que guardará el barco en el array tablero correspondiente:
    insertaBarco(fragataJ1, tablero1a, fila, columna);
    
    
    //Guardamos las coordenadas de la casilla inicial:
    fragataJ1.setCasillaInicial(fila, columna);
    
    
    //Volvemos a imprimir al tablero para mostrar al jugador el resultado:
    imprimeTableroJ1(tablero1a, tablero1b);
    
    
    

    //########################     SUBMARINO     ########################\\
    
    System.out.print("\n\nTurno del " + submarinoJ1.nombreBarco + " que posee " + submarinoJ1.getCasillas() + " casillas.");
    
    
    //Pedimos al usuario si desea posicionar el barco en vertical, mientras la respuesta no sea correcta volveremos a preguntar:
    do {
      
      System.out.print("\n\n¿Desea que el barco se inserte en el tablero en posición vertical? (S/N)");
      respuesta = s.nextLine().toUpperCase();
      
    } while(!(respuesta.contentEquals("S") || respuesta.contentEquals("N")));
    
    
    //En caso de que sí reseteamos el valor de respuesta y llamamos al método "getVerticalidad()" del objeto:
    if (respuesta.equals("S")) {
      
      respuesta = "";
      
      submarinoJ1.setVerticalidad();
      
    }
    
    
    /*
     * A continuación pedimos las coordenadas en las que se va a ubicar el barco, si las coordenadas se salen del tablero
     * o la posición del barco es incorrecta volveremos a pedirlas:
     */
    do {
      
      fila = transformaFila(pideCoordenadaFila());
      
      columna = pideCoordenadaColumna();
      
    } while (!compruebaPosicionBarco(submarinoJ1, tablero1a, fila, columna));
    
    
    //Si todo ha salido bien llamamos a la función "insertaBarco()" que guardará el barco en el array tablero correspondiente:
    insertaBarco(submarinoJ1, tablero1a, fila, columna);
    
    
    //Guardamos las coordenadas de la casilla inicial:
    submarinoJ1.setCasillaInicial(fila, columna);
    
    
    //Volvemos a imprimir al tablero para mostrar al jugador el resultado:
    imprimeTableroJ1(tablero1a, tablero1b);
    
    
  }
  
  
  
  
  /**
   * A través de este método el Jugador 2 podrá ir introduciendo sus barcos en el tablero de juego. Este método hace uso de los siguientes métodos:
   * 
   * <ul>
   * <li>pideCoordenadaFila()</li>
   * <li>pideCoordenadaColumna()</li>
   * <li>transformaFila()</li>
   * <li>insertaBarco()</li>
   * <li>imprimeTableroJ1()</li>
   * </ul>
   * 
   * @param tablero2a
   * @param tablero2b
   * @param portaavionesJ2
   * @param acorazadoJ2
   * @param destructorJ2
   * @param cruceroJ2
   * @param fragataJ2
   * @param submarinoJ2
   */
  public static void asignaPosicionBarcosJ2(String tablero2a[][], String tablero2b[][], 
      Barco portaavionesJ2, Barco acorazadoJ2, Barco destructorJ2, Barco cruceroJ2, Barco fragataJ2, Barco submarinoJ2) {
    
    //Scanner:
    Scanner s = new Scanner(System.in);
    
    
    //Variables:
    int fila;
    
    int columna;
    
    String respuesta = "";
    
    System.out.print("\n\nVamos a ubicar los barcos en el tablero.");
    
    
    
    
    //########################     PORTAAVIONES     ########################\\
    
    System.out.print("\n\nTurno del " + portaavionesJ2.nombreBarco + " que posee " + portaavionesJ2.getCasillas() + " casillas.");
    
    
    //Pedimos al usuario si desea posicionar el barco en vertical, mientras la respuesta no sea correcta volveremos a preguntar:
    do {
      
      System.out.print("\n\n¿Desea que el barco se inserte en el tablero en posición vertical? (S/N)");
      respuesta = s.nextLine().toUpperCase();
      
    } while(!(respuesta.contentEquals("S") || respuesta.contentEquals("N")));
    
    
    //En caso de que sí reseteamos el valor de respuesta y llamamos al método "getVerticalidad()" del objeto:
    if (respuesta.equals("S")) {
      
      respuesta = "";
      
      portaavionesJ2.setVerticalidad();
      
    }
    
    
    /*
     * A continuación pedimos las coordenadas en las que se va a ubicar el barco, si las coordenadas se salen del tablero
     * o la posición del barco es incorrecta volveremos a pedirlas:
     */
    do {
      
      fila = transformaFila(pideCoordenadaFila());
      
      columna = pideCoordenadaColumna();
      
    } while (!compruebaPosicionBarco(portaavionesJ2, tablero2a, fila, columna));
    
    
    //Si todo ha salido bien llamamos a la función "insertaBarco()" que guardará el barco en el array tablero correspondiente:
    insertaBarco(portaavionesJ2, tablero2a, fila, columna);
    
    
    //Guardamos las coordenadas de la casilla inicial:
    portaavionesJ2.setCasillaInicial(fila, columna);
    
    
    //Volvemos a imprimir al tablero para mostrar al jugador el resultado:
    imprimeTableroJ2(tablero2a, tablero2b);
    
    
    
        
    //########################     ACORAZADO     ########################\\
    
    System.out.print("\n\nTurno del " + acorazadoJ2.nombreBarco + " que posee " + acorazadoJ2.getCasillas() + " casillas.");
    
    
    //Pedimos al usuario si desea posicionar el barco en vertical, mientras la respuesta no sea correcta volveremos a preguntar:
    do {
      
      System.out.print("\n\n¿Desea que el barco se inserte en el tablero en posición vertical? (S/N)");
      respuesta = s.nextLine().toUpperCase();
      
    } while(!(respuesta.contentEquals("S") || respuesta.contentEquals("N")));
    
    
    //En caso de que sí reseteamos el valor de respuesta y llamamos al método "getVerticalidad()" del objeto:
    if (respuesta.equals("S")) {
      
      respuesta = "";
      
      acorazadoJ2.setVerticalidad();
      
    }
    
    
    /*
     * A continuación pedimos las coordenadas en las que se va a ubicar el barco, si las coordenadas se salen del tablero
     * o la posición del barco es incorrecta volveremos a pedirlas:
     */
    do {
      
      fila = transformaFila(pideCoordenadaFila());
      
      columna = pideCoordenadaColumna();
      
    } while (!compruebaPosicionBarco(acorazadoJ2, tablero2a, fila, columna));
    
    
    //Si todo ha salido bien llamamos a la función "insertaBarco()" que guardará el barco en el array tablero correspondiente:
    insertaBarco(acorazadoJ2, tablero2a, fila, columna);
    
    
    //Guardamos las coordenadas de la casilla inicial:
    acorazadoJ2.setCasillaInicial(fila, columna);
    
    
    //Volvemos a imprimir al tablero para mostrar al jugador el resultado:
    imprimeTableroJ2(tablero2a, tablero2b);
    
    
    
    
    //########################     DESTRUCTOR     ########################\\
    
    System.out.print("\n\nTurno del " + destructorJ2.nombreBarco + " que posee " + destructorJ2.getCasillas() + " casillas.");
    
    
    //Pedimos al usuario si desea posicionar el barco en vertical, mientras la respuesta no sea correcta volveremos a preguntar:
    do {
      
      System.out.print("\n\n¿Desea que el barco se inserte en el tablero en posición vertical? (S/N)");
      respuesta = s.nextLine().toUpperCase();
      
    } while(!(respuesta.contentEquals("S") || respuesta.contentEquals("N")));
    
    
    //En caso de que sí reseteamos el valor de respuesta y llamamos al método "getVerticalidad()" del objeto:
    if (respuesta.equals("S")) {
      
      respuesta = "";
      
      destructorJ2.setVerticalidad();
      
    }
    
    
    /*
     * A continuación pedimos las coordenadas en las que se va a ubicar el barco, si las coordenadas se salen del tablero
     * o la posición del barco es incorrecta volveremos a pedirlas:
     */
    do {
      
      fila = transformaFila(pideCoordenadaFila());
      
      columna = pideCoordenadaColumna();
      
    } while (!compruebaPosicionBarco(destructorJ2, tablero2a, fila, columna));
    
    
    //Si todo ha salido bien llamamos a la función "insertaBarco()" que guardará el barco en el array tablero correspondiente:
    insertaBarco(destructorJ2, tablero2a, fila, columna);
    
    
    //Guardamos las coordenadas de la casilla inicial:
    destructorJ2.setCasillaInicial(fila, columna);
    
    
    //Volvemos a imprimir al tablero para mostrar al jugador el resultado:
    imprimeTableroJ2(tablero2a, tablero2b);
    
    
    
    //########################     CRUCERO     ########################\\
    
    System.out.print("\n\nTurno del " + cruceroJ2.nombreBarco + " que posee " + cruceroJ2.getCasillas() + " casillas.");
    
    
    //Pedimos al usuario si desea posicionar el barco en vertical, mientras la respuesta no sea correcta volveremos a preguntar:
    do {
      
      System.out.print("\n\n¿Desea que el barco se inserte en el tablero en posición vertical? (S/N)");
      respuesta = s.nextLine().toUpperCase();
      
    } while(!(respuesta.contentEquals("S") || respuesta.contentEquals("N")));
    
    
    //En caso de que sí reseteamos el valor de respuesta y llamamos al método "getVerticalidad()" del objeto:
    if (respuesta.equals("S")) {
      
      respuesta = "";
      
      cruceroJ2.setVerticalidad();
      
    }
    
    
    /*
     * A continuación pedimos las coordenadas en las que se va a ubicar el barco, si las coordenadas se salen del tablero
     * o la posición del barco es incorrecta volveremos a pedirlas:
     */
    do {
      
      fila = transformaFila(pideCoordenadaFila());
      
      columna = pideCoordenadaColumna();
      
    } while (!compruebaPosicionBarco(cruceroJ2, tablero2a, fila, columna));
    
    
    //Si todo ha salido bien llamamos a la función "insertaBarco()" que guardará el barco en el array tablero correspondiente:
    insertaBarco(cruceroJ2, tablero2a, fila, columna);
    
    
    //Guardamos las coordenadas de la casilla inicial:
    cruceroJ2.setCasillaInicial(fila, columna);
    
    
    //Volvemos a imprimir al tablero para mostrar al jugador el resultado:
    imprimeTableroJ2(tablero2a, tablero2b);
    
    
    
    
    //########################     FRAGATA     ########################\\
    
    System.out.print("\n\nTurno de la " + fragataJ2.nombreBarco + " que posee " + fragataJ2.getCasillas() + " casillas.");
    
    
    //Pedimos al usuario si desea posicionar el barco en vertical, mientras la respuesta no sea correcta volveremos a preguntar:
    do {
      
      System.out.print("\n\n¿Desea que el barco se inserte en el tablero en posición vertical? (S/N)");
      respuesta = s.nextLine().toUpperCase();
      
    } while(!(respuesta.contentEquals("S") || respuesta.contentEquals("N")));
    
    
    //En caso de que sí reseteamos el valor de respuesta y llamamos al método "getVerticalidad()" del objeto:
    if (respuesta.equals("S")) {
      
      respuesta = "";
      
      fragataJ2.setVerticalidad();
      
    }
    
    
    /*
     * A continuación pedimos las coordenadas en las que se va a ubicar el barco, si las coordenadas se salen del tablero
     * o la posición del barco es incorrecta volveremos a pedirlas:
     */
    do {
      
      fila = transformaFila(pideCoordenadaFila());
      
      columna = pideCoordenadaColumna();
      
    } while (!compruebaPosicionBarco(fragataJ2, tablero2a, fila, columna));
    
    
    //Si todo ha salido bien llamamos a la función "insertaBarco()" que guardará el barco en el array tablero correspondiente:
    insertaBarco(fragataJ2, tablero2a, fila, columna);
    
    
    //Guardamos las coordenadas de la casilla inicial:
    fragataJ2.setCasillaInicial(fila, columna);
    
    
    //Volvemos a imprimir al tablero para mostrar al jugador el resultado:
    imprimeTableroJ2(tablero2a, tablero2b);
    
    
    

    //########################     SUBMARINO     ########################\\
    
    System.out.print("\n\nTurno del " + submarinoJ2.nombreBarco + " que posee " + submarinoJ2.getCasillas() + " casillas.");
    
    
    //Pedimos al usuario si desea posicionar el barco en vertical, mientras la respuesta no sea correcta volveremos a preguntar:
    do {
      
      System.out.print("\n\n¿Desea que el barco se inserte en el tablero en posición vertical? (S/N)");
      respuesta = s.nextLine().toUpperCase();
      
    } while(!(respuesta.contentEquals("S") || respuesta.contentEquals("N")));
    
    
    //En caso de que sí reseteamos el valor de respuesta y llamamos al método "getVerticalidad()" del objeto:
    if (respuesta.equals("S")) {
      
      respuesta = "";
      
      submarinoJ2.setVerticalidad();
      
    }
    
    
    /*
     * A continuación pedimos las coordenadas en las que se va a ubicar el barco, si las coordenadas se salen del tablero
     * o la posición del barco es incorrecta volveremos a pedirlas:
     */
    do {
      
      fila = transformaFila(pideCoordenadaFila());
      
      columna = pideCoordenadaColumna();
      
    } while (!compruebaPosicionBarco(submarinoJ2, tablero2a, fila, columna));
    
    
    //Si todo ha salido bien llamamos a la función "insertaBarco()" que guardará el barco en el array tablero correspondiente:
    insertaBarco(submarinoJ2, tablero2a, fila, columna);
    
    
    //Guardamos las coordenadas de la casilla inicial:
    submarinoJ2.setCasillaInicial(fila, columna);
    
    
    //Volvemos a imprimir al tablero para mostrar al jugador el resultado:
    imprimeTableroJ2(tablero2a, tablero2b);
    
    
  }
  
  
  /**
   * Valida las coordenadas insertadas para lanzar un disparo. De esta forma evita disparar donde previamente se haya disparado.
   * 
   * @param tablero
   * @param fila
   * @param columna
   * @return
   */
  public static boolean validaDisparo(String tablero[][], int fila, int columna) {
    
    if (tablero[fila][columna]=="_|" || tablero[fila][columna]=="B|") {
      
      return true;
      
    } else {
      
      return false;
      
    }
    
  }
  
  
  /**
   * Método disparo para el Jugador 1.
   * 
   * Este método agrupa una serie de métodos, a continuación descritos, 
   * y gestiona el proceso de disparo. Primero se piden las coordenadas y se verifican tanto las coordenadas como el disparo.
   * <br>
   * 
   * A continuación se sobreescribe en el tablero enemigo el impacto, en caso de haber una casilla de barco se sobreescribe "X|" y en caso de no haberla
   * se sobreescribe un "O|", del mismo modo se hace una copia en las mismas coordenadas en el tablero b del jugador para ver dónde ha disparado y con qué resultado.
   * <br>
   * 
   * En caso de haber impactado, es decir, en caso de haber encontrado en el tablero del jugador contrario una casilla con el String "B|" además de sobreescribir, 
   * como hemos mencionado anteriormente tanto el tablero principal del enemigo como el tablero secundario del jugador activo, debemos verificar cuál de los barcos
   * enemigos hemos impacto y restarle una de sus casillas actuales, de tal forma que, cuando ese barco llegue a poseer cero casillas actuales active el booleano de hundido.
   * <br>
   * 
   * Para el correcto funcionamiento de este método es necesario hacer uso de los siguientes métodos de la clase HundirLaFlota:
   * 
   * <ul>
   * <li>pideCoordenadaFila()</li>
   * <li>pideCoordenadaColumna()</li>
   * <li>transformaFila()</li>
   * <li>validaDisparo()</li>
   * </ul>
   * 
   * 
   * Además es necesario hacer uso de los siguientes métodos de la clase Barco:
   * 
   * <ul>
   * <li>compruebaImpacto()</li>
   * <li>tocado()</li>
   * <li>mensajeHundido()</li>
   * <li>setHaSalidoMensajeHundido()</li>
   * </ul>
   * 
   * @param tablero2a
   * @param tablero1b
   * @param portaavionesJ2
   * @param acorazadoJ2
   * @param destructorJ2
   * @param cruceroJ2
   * @param fragataJ2
   * @param submarinoJ2
   */
  public static void disparoJ1(String tablero2a[][], String tablero1b[][],
      Barco portaavionesJ2, Barco acorazadoJ2, Barco destructorJ2, Barco cruceroJ2, Barco fragataJ2, Barco submarinoJ2) {
    
    int fila;
    
    int columna;
    
    
    /*
     * Solicitamos las coordenadas de disparo y lo validamos, si las coordenadas son erróneas o 
     * el disparo se realiza sobre otro disparo anterior volvemos a solicitarlas.
     */
    
    System.out.print("\n\nIndique las coordenadas del disparo:");
    
    do {
      
      fila = transformaFila(pideCoordenadaFila());
    
      columna = pideCoordenadaColumna();
      
    } while(!validaDisparo(tablero2a, fila, columna));
    
    
    /*
     * Una vez hemos validado que las coordenadas son correctas comprobamos si:
     * 
     *  -El disparo a impactado en agua: En ese caso sobreescribimos en el tablero del jugador enemigo el String "O|"
     *                                   para mostrarle la ubicación del disparo. Igualmente guardamos en las
     *                                   mismas coordenadas pero del tablero b del jugador activo el mismo String.
     *                                   
     *  -El disparo a tocado un barco:   En ese caso sobreescribimos en el tablero del jugador enemigo el String "X|"
     *                                   para mostrarle la ubicación del disparo. Igualmente guardamos en las
     *                                   mismas coordenadas pero del tablero b del jugador activo el mismo String.
     *  
     *  Además, en caso de haber impactado sobre un barco enemigo debemos verificar cual de todos ellos es el barco impactado
     *  con la finalidad de reducir el total de sus casillas en uno, con el objetivo de que cuando sus casillas actuales lleguen
     *  a ser cero el barco activará el booleano de que está hundido. Para esto último es necesario usar los métodos correspondientes
     *  de la clase Barco.
     */
    
    if (tablero2a[fila][columna]=="_|") {
      
      System.out.print("\n\nAGUA");
      
      tablero2a [fila][columna] = "O|";
      
      tablero1b [fila][columna] = "O|";
      
    } else if(tablero2a[fila][columna]=="B|") {
      
      System.out.print("\n\nTOCADO");
      
      tablero2a [fila][columna] = "X|";
      
      tablero1b [fila][columna] = "X|";
      
      if (portaavionesJ2.compruebaImpacto(fila, columna)) {
        
        portaavionesJ2.tocado();        
        
        if (portaavionesJ2.hundidoBarco && !portaavionesJ2.haSalidoMensajeHundido) {      //Si lo hundimos:
          
          portaavionesJ2.mensajeHundido();                                                //Imprimos el mensaje de que ha sido hundido.
          
          portaavionesJ2.setHaSalidoMensajeHundido();                                     //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
          
        }
        
      } else if (acorazadoJ2.compruebaImpacto(fila, columna)) { 
        
        acorazadoJ2.tocado();
        
        if (acorazadoJ2.hundidoBarco && !acorazadoJ2.haSalidoMensajeHundido) {            //Si lo hundimos:
          
          acorazadoJ2.mensajeHundido();                                                   //Imprimos el mensaje de que ha sido hundido.
          
          acorazadoJ2.setHaSalidoMensajeHundido();                                        //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
          
        }
        
      } else if (destructorJ2.compruebaImpacto(fila, columna)) {
        
        destructorJ2.tocado();
        
        if (destructorJ2.hundidoBarco && !destructorJ2.haSalidoMensajeHundido) {          //Si lo hundimos:
          
          destructorJ2.mensajeHundido();                                                  //Imprimos el mensaje de que ha sido hundido.
          
          destructorJ2.setHaSalidoMensajeHundido();                                       //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
          
        }
        
      } else if (cruceroJ2.compruebaImpacto(fila, columna)) {
        
        cruceroJ2.tocado();
        
        if (cruceroJ2.hundidoBarco && !cruceroJ2.haSalidoMensajeHundido) {                //Si lo hundimos:
          
          cruceroJ2.mensajeHundido();                                                     //Imprimos el mensaje de que ha sido hundido.
          
          cruceroJ2.setHaSalidoMensajeHundido();                                          //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
          
        }
        
      } else if (fragataJ2.compruebaImpacto(fila, columna)) {
        
        fragataJ2.tocado();
        
        if (fragataJ2.hundidoBarco && !fragataJ2.haSalidoMensajeHundido) {                //Si lo hundimos:
          
          fragataJ2.mensajeHundido();                                                     //Imprimos el mensaje de que ha sido hundido.
          
          fragataJ2.setHaSalidoMensajeHundido();                                          //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
          
        }
        
      } else if (submarinoJ2.compruebaImpacto(fila, columna)) {
        
        submarinoJ2.tocado();
        
        if (submarinoJ2.hundidoBarco && !submarinoJ2.haSalidoMensajeHundido) {            //Si lo hundimos:
          
          submarinoJ2.mensajeHundido();                                                   //Imprimos el mensaje de que ha sido hundido.
          
          submarinoJ2.setHaSalidoMensajeHundido();                                        //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
          
        }
        
      }
      
    }    
    
  }
    
  
  /**
   * Método disparo para el Jugador 2.
   * 
   * Este método agrupa una serie de métodos, a continuación descritos, 
   * y gestiona el proceso de disparo. Primero se piden las coordenadas y se verifican tanto las coordenadas como el disparo.
   * <br>
   * 
   * A continuación se sobreescribe en el tablero enemigo el impacto, en caso de haber una casilla de barco se sobreescribe "X|" y en caso de no haberla
   * se sobreescribe un "O|", del mismo modo se hace una copia en las mismas coordenadas en el tablero b del jugador para ver dónde ha disparado y con qué resultado.
   * <br>
   * 
   * En caso de haber impactado, es decir, en caso de haber encontrado en el tablero del jugador contrario una casilla con el String "B|" además de sobreescribir, 
   * como hemos mencionado anteriormente tanto el tablero principal del enemigo como el tablero secundario del jugador activo, debemos verificar cuál de los barcos
   * enemigos hemos impacto y restarle una de sus casillas actuales, de tal forma que, cuando ese barco llegue a poseer cero casillas actuales active el booleano de hundido.
   * <br>
   * 
   * Para el correcto funcionamiento de este método es necesario hacer uso de los siguientes métodos de la clase HundirLaFlota:
   * 
   * <ul>
   * <li>pideCoordenadaFila()</li>
   * <li>pideCoordenadaColumna()</li>
   * <li>transformaFila()</li>
   * <li>validaDisparo()</li>
   * </ul>
   * 
   * 
   * Además es necesario hacer uso de los siguientes métodos de la clase Barco:
   * 
   * <ul>
   * <li>compruebaImpacto()</li>
   * <li>tocado()</li>
   * <li>mensajeHundido()</li>
   * <li>setHaSalidoMensajeHundido()</li>
   * </ul>
   * 
   * @param tablero1a
   * @param tablero2b
   * @param portaavionesJ1
   * @param acorazadoJ1
   * @param destructorJ1
   * @param cruceroJ1
   * @param fragataJ1
   * @param submarinoJ1
   */
  public static void disparoJ2(String tablero1a[][], String tablero2b[][],
      Barco portaavionesJ1, Barco acorazadoJ1, Barco destructorJ1, Barco cruceroJ1, Barco fragataJ1, Barco submarinoJ1) {
    
    int fila;
    
    int columna;
    
    
    /*
     * Solicitamos las coordenadas de disparo y lo validamos, si las coordenadas son erróneas o 
     * el disparo se realiza sobre otro disparo anterior volvemos a solicitarlas.
     */
    
    System.out.print("\n\nIndique las coordenadas del disparo:");
    
    do {
      
      fila = transformaFila(pideCoordenadaFila());
    
      columna = pideCoordenadaColumna();
      
    } while(!validaDisparo(tablero1a, fila, columna));
    
    
    /*
     * Una vez hemos validado que las coordenadas son correctas comprobamos si:
     * 
     *  -El disparo a impactado en agua: En ese caso sobreescribimos en el tablero del jugador enemigo el String "O|"
     *                                   para mostrarle la ubicación del disparo. Igualmente guardamos en las
     *                                   mismas coordenadas pero del tablero b del jugador activo el mismo String.
     *                                   
     *  -El disparo a tocado un barco:   En ese caso sobreescribimos en el tablero del jugador enemigo el String "X|"
     *                                   para mostrarle la ubicación del disparo. Igualmente guardamos en las
     *                                   mismas coordenadas pero del tablero b del jugador activo el mismo String.
     *  
     *  Además, en caso de haber impactado sobre un barco enemigo debemos verificar cual de todos ellos es el barco impactado
     *  con la finalidad de reducir el total de sus casillas en uno, con el objetivo de que cuando sus casillas actuales lleguen
     *  a ser cero el barco activará el booleano de que está hundido. Para esto último es necesario usar los métodos correspondientes
     *  de la clase Barco.
     */
    
    if (tablero1a[fila][columna]=="_|") {
      
      System.out.print("\n\nAGUA");
      
      tablero1a [fila][columna] = "O|";
      
      tablero2b [fila][columna] = "O|";
      
    } else if(tablero1a[fila][columna]=="B|") {
      
      System.out.print("\n\nTOCADO");
      
      tablero1a [fila][columna] = "X|";
      
      tablero2b [fila][columna] = "X|";
      
      if (portaavionesJ1.compruebaImpacto(fila, columna)) {
        
        portaavionesJ1.tocado();
        
        if (portaavionesJ1.hundidoBarco && !portaavionesJ1.haSalidoMensajeHundido) {      //Si lo hundimos:
          
          portaavionesJ1.mensajeHundido();                                                //Imprimos el mensaje de que ha sido hundido.
          
          portaavionesJ1.setHaSalidoMensajeHundido();                                     //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
          
        }
        
      } else if (acorazadoJ1.compruebaImpacto(fila, columna)) {
        
        acorazadoJ1.tocado();
        
        if (acorazadoJ1.hundidoBarco && !acorazadoJ1.haSalidoMensajeHundido) {            //Si lo hundimos:
          
          acorazadoJ1.mensajeHundido();                                                   //Imprimos el mensaje de que ha sido hundido.
          
          acorazadoJ1.setHaSalidoMensajeHundido();                                        //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
          
        }
        
      } else if (destructorJ1.compruebaImpacto(fila, columna)) {
        
        destructorJ1.tocado();
        
        if (destructorJ1.hundidoBarco && !destructorJ1.haSalidoMensajeHundido) {          //Si lo hundimos:
          
          destructorJ1.mensajeHundido();                                                  //Imprimos el mensaje de que ha sido hundido.
          
          destructorJ1.setHaSalidoMensajeHundido();                                       //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
          
        }
        
      } else if (cruceroJ1.compruebaImpacto(fila, columna)) {
        
        cruceroJ1.tocado();
        
        if (cruceroJ1.hundidoBarco && !cruceroJ1.haSalidoMensajeHundido) {                //Si lo hundimos:
          
          cruceroJ1.mensajeHundido();                                                     //Imprimos el mensaje de que ha sido hundido.
          
          cruceroJ1.setHaSalidoMensajeHundido();                                          //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
          
        }
        
      } else if (fragataJ1.compruebaImpacto(fila, columna)) {
        
        fragataJ1.tocado();
        
        if (fragataJ1.hundidoBarco && !fragataJ1.haSalidoMensajeHundido) {                //Si lo hundimos:
          
          fragataJ1.mensajeHundido();                                                     //Imprimos el mensaje de que ha sido hundido.
          
          fragataJ1.setHaSalidoMensajeHundido();                                          //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
          
        }
        
      } else if (submarinoJ1.compruebaImpacto(fila, columna)) {
        
        submarinoJ1.tocado();
        
        if (submarinoJ1.hundidoBarco && !submarinoJ1.haSalidoMensajeHundido) {            //Si lo hundimos:
          
          submarinoJ1.mensajeHundido();                                                   //Imprimos el mensaje de que ha sido hundido.
          
          submarinoJ1.setHaSalidoMensajeHundido();                                        //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
          
        }
        
      }
      
    }    
    
  }
    
  
  /**
   * Devuelve un entero entre [1-100].
   * 
   * @return
   */
  public static int tiradaAleatoria() {
    
    int salida=0;
    
    
    do {
      
      salida = (int)(Math.random()*100+1);
      
    } while(salida==50);
    
    
    return salida;
    
  }
  
  
  /**
   * Preguntamos al jugador si desea volver a jugar y retornamos un boolean.
   * 
   * @return
   */
  public static boolean preguntaReinicio() {
    
    //Scanner:
    Scanner s = new Scanner(System.in);
    
    //Variable respuesta:
    String respuesta = "";
    
    do {
      
      System.out.println("\n\n¿Desea jugar otra partida?");
      respuesta = s.nextLine().toUpperCase();
      
      
    } while(!(respuesta.equals("S") || respuesta.equals("N")));
    
    
    if (respuesta.equals("S")) {
      
      return true;
      
    } else {
      
      return false;
      
    }
    
  }
  
  
  /**
   * Este método es llamado cuando el juego ha finalizado y el usuario desea reiniciar la partida para volver a jugar.<br><br>
   * 
   * La función de este método es reiniciar los tableros para borrar los impactos realizados y devolver a un valor por defecto a todos los barcos.<br><br>
   * 
   * Para el correcto funcionamiento de este método es necesario el método de la clase HundirLaFlota:
   * <ul>
   * <li>reiniciarTablero()</li>
   * </ul>
   * 
   * Y de la clase Barco, el método:
   * <ul>
   * <li>setReinicia()</li>
   * </ul>
   * 
   * @param tablero1a
   * @param tablero1b
   * @param tablero2a
   * @param tablero2b
   * @param portaavionesJ1
   * @param acorazadoJ1
   * @param destructorJ1
   * @param cruceroJ1
   * @param fragataJ1
   * @param submarinoJ1
   * @param portaavionesJ2
   * @param acorazadoJ2
   * @param destructorJ2
   * @param cruceroJ2
   * @param fragataJ2
   * @param submarinoJ2
   */
  public static void reiniciarJuego(String tablero1a [][], String tablero1b [][], String tablero2a [][], String tablero2b [][], 
      Barco portaavionesJ1, Barco acorazadoJ1, Barco destructorJ1, Barco cruceroJ1, Barco fragataJ1, Barco submarinoJ1, 
      Barco portaavionesJ2, Barco acorazadoJ2, Barco destructorJ2, Barco cruceroJ2, Barco fragataJ2, Barco submarinoJ2) {
    
    
    //Reiniciamos tableros:
    reiniciaTableros(tablero1a, tablero1b, tablero2a, tablero2b);
    
        
    //Reseteamos a los valores por defecto d elos barcos del Jugador 1:
    portaavionesJ1.setReinicia();
    
    acorazadoJ1.setReinicia();
    
    destructorJ1.setReinicia();
    
    cruceroJ1.setReinicia();
    
    fragataJ1.setReinicia();
    
    submarinoJ1.setReinicia();
    
    
    //Reseteamos a los valores por defecto d elos barcos del Jugador 2:
    portaavionesJ2.setReinicia();
    
    acorazadoJ2.setReinicia();
    
    destructorJ2.setReinicia();
    
    cruceroJ2.setReinicia();
    
    fragataJ2.setReinicia();
    
    submarinoJ2.setReinicia();
    
  }
  
  
  /**
   * Hace esperar al cambio de turno.
   * 
   * @param segundos
   */
  public static void esperaSegundos(int segundos) {
    try {
      
      Thread.sleep(segundos*1000);
      
    } catch (Exception E) {}
  }
    
}
