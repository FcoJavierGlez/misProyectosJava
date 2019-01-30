/** Tres en raya v2.1
*
*	Este programa fue creado el martes 6 de Noviembre del año 2018 por:
*       
*       v1.0: (6 de noviembre de 2018)  "Código original"
*       v1.1: (8 de noviembre de 2018)  "Corrección de bugs"
*       v2.0: (10 de noviembre de 2018) "Creación del modo 1 jugador e implementación del primer EasterEgg"
*       v2.1: (12 de noviembre de 2018) "Ligera mejora de la toma de decisiones de la IA e implementación de un ->
*                                        segundo EasterEgg""
*       v2.2: (13 de noviembre de 2018) "Corrección de bugs en la detección de evitar movimiento ganador"
*
*	@author González Sabariego, Francisco Javier
*
*/
import java.util.*;

public class TresRaya{
	public static void main(String[] args){
		//Creamos objetos Scanner
    Scanner s = new Scanner(System.in);

    //Variables
    int tiradaJ1;                               //Tirada para saber de quién es el turno
    int tiradaJ2;                               //Tirada para saber de quién es el turno
    int azar;                                   //Variable para decidir posición de la IA cuando las fichas son 3
    boolean turnoJ1=false;                      //Indica el turno actual del jugador 1
    boolean turnoJ2=false;                      //Indica el turno actual del jugador 2
    boolean jugador1Gana=false;                 //Indica si ha ganado el jugador 1
    boolean jugador2Gana=false;                 //Indica si ha ganado el jugador 2
    boolean finDeJuego=false;                   //Indica el final del juego
    int victoriasJ1=0;                          //Cuenta el número de victorias del Jugador 1
    int victoriasJ2=0;                          //Cuenta el número de victorias del Jugador 2
    int numeroMenu;                             //Elegir la opción del menu
    int partidaAlMejorDe=0;                     //Cuando un jugador sobrepasa en número de victorias a la mitad de este valor gana
    int contadorFichas=0;                       //Cada ficha que se coloca incrementa en 1, de forma que en caso de empate a las 9 fichas acaba la partida
    String respuesta="S";                       //Respuesta S/N para la pregunta de seguir jugando, por defecto es "S" para poder inciar el bucle
    int modoDeJuego;                            //Esta variable sirve para activar el modo 1 ó 2 jugadores
    int ejeY=0;                                 //Coordenada que inserta el jugador del eje Y
    int ejeX=0;                                 //Coordenada que inserta el jugador del eje X
    String prueba="";                           //Esta variable comprueba que se hayan alineado 3 "O" o "X" para declarar un vencedor
    String [][] tablero = new String [3][3];    //Array bidimensional para pintar el tablero

    tablero [0][0] = " ";
    tablero [0][1] = " ";
    tablero [0][2] = " ";
    tablero [1][0] = " ";
    tablero [1][1] = " ";
    tablero [1][2] = " ";
    tablero [2][0] = " ";
    tablero [2][1] = " ";
    tablero [2][2] = " ";
    
    System.out.print("BIENVENIDO AL JUEGO DEL TRES EN RAYA:");
    
    //Primero damos a elegir en qué modo se desea jugar 1 ó 2 jugador/es:
    System.out.print("\n\n\nDígame el modo de juego al que desea jugar: ");
    System.out.print("\n(1) Modo 1 jugador: Jugador vs IA.");
    System.out.println("\n(2) Modo 2 jugadores: Jugador 1 vs Jugador 2.");
    modoDeJuego = s.nextInt();
    
    while (modoDeJuego<1 || modoDeJuego >2) {
      System.out.print("\nSELECCIÓN INCORRECTA.");
      System.out.print("\n\n\nDígame el modo de juego al que desea jugar: ");
      System.out.print("\n(1) Modo 1 jugador: Jugador vs IA.");
      System.out.println("\n(2) Modo 2 jugadores: Jugador 1 vs Jugador 2.");
      modoDeJuego = s.nextInt();
    }
    
    //####################################################################################
    
    //Modo 1 jugador:
    if (modoDeJuego==1) {
      System.out.print("\n¿Al mejor de cuántas partidas quiere jugar? Elija una opción insertando el número del paréntesis:");
      System.out.print("\n(1) Al mejor de 3. Gana el primero en obtener 2 victorias.");
      System.out.print("\n(2) Al mejor de 5. Gana el primero en obtener 3 victorias.");
      System.out.print("\n(3) Al mejor de 7. Gana el primero en obtener 4 victorias.");
      System.out.println("\n(4) Al mejor de 9. Gana el primero en obtener 5 victorias.");
      numeroMenu=s.nextInt();

      if (numeroMenu<1 || numeroMenu>4) {
        while (numeroMenu<1 || numeroMenu>4) {
          System.out.print("\n¿Al mejor de cuantas partidas quiere jugar? Elija una opción insertando el número del paréntesis:");
          System.out.print("\n(1) Al mejor de 3. Gana el primero en obtener 2 victorias.");
          System.out.print("\n(2) Al mejor de 5. Gana el primero en obtener 3 victorias.");
          System.out.print("\n(3) Al mejor de 7. Gana el primero en obtener 4 victorias.");
          System.out.println("\n(4) Al mejor de 9. Gana el primero en obtener 5 victorias.");
          numeroMenu=s.nextInt();
        }
      }
      if (numeroMenu==1) {          //Elección del menú para declarar victorioso al mejor de x partidas
        partidaAlMejorDe = 3;
      } else if (numeroMenu==2) {
          partidaAlMejorDe = 5;
      } else if (numeroMenu==3) {
          partidaAlMejorDe = 7;
      } else if (numeroMenu==4) {
          partidaAlMejorDe = 9;
      }
      
      //Tirada aleatoria para declarar el inicio de uno de los dos jugadores
      System.out.print("Voy a realizar una tirada aleatoria para cada jugador, el que saque más puntuación comienza la partida: ");
      tiradaJ1 = (int)(Math.random()*100+1);
      tiradaJ2 = (int)(Math.random()*100+1);

      if (tiradaJ1==tiradaJ2) {
        while (tiradaJ1==tiradaJ2) {
          tiradaJ1 = (int)(Math.random()*100+1);
          tiradaJ2 = (int)(Math.random()*100+1);
        }
      }

      System.out.print("\n\nEl Jugador 1 obtiene una tirada de: " + tiradaJ1 + " y el Jugador 2 obtiene una tirada de: " + tiradaJ2);

      if (tiradaJ1>tiradaJ2) {
        turnoJ1=true;
        System.out.print("\n\nComienza el Jugador 1");
      } else {
          turnoJ2=true;
          System.out.print("\n\nComienza el Jugador 2");
      }

      System.out.print("\n\nLa partida comenzará en 3 segundos.");

      try {
        Thread.sleep(3000);
      } catch (Exception e){}


      


      while (!finDeJuego) {                                                    //bucle de la partida
        
        //Turno del jugador 1
        while (turnoJ1 && !finDeJuego) {
          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("TURNO DEL JUGADOR 1");
          System.out.print("\n\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
          System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
          System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
          System.out.printf("\n  1 2 3");
          System.out.print("\n\nIntroduce la coordenada Y (eje vertical):");
          ejeY = s.nextInt();
          System.out.print("\nIntroduce la coordenada X (eje horizontal):");
          ejeX = s.nextInt();
          
          while ((ejeY<1 || ejeY>3) || (ejeX<1 || ejeX>3) || ((tablero[(ejeY-1)][(ejeX-1)])!=(" "))) {
            if ((ejeY<1 || ejeY>3) || (ejeX<1 || ejeX>3)) {
              System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
              System.out.print("TURNO DEL JUGADOR 1");
              System.out.print("\n\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
              System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
              System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
              System.out.printf("\n  1 2 3");
              System.out.print("\n\nCOORDENADAS INCORRECTAS. No puede insertar un valor inferior a 1 o superior a 3. Por favor vuelva a insertar las coordenadas:");
              System.out.print("\n\nIntroduce la coordenada Y (eje vertical):");
              ejeY = s.nextInt();
              System.out.print("\nIntroduce la coordenada X (eje horizontal):");
              ejeX = s.nextInt();
            } else if ((tablero[(ejeY-1)][(ejeX-1)])!=(" ")) {
              System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
              System.out.print("TURNO DEL JUGADOR 1");
              System.out.print("\n\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
              System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
              System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
              System.out.printf("\n  1 2 3");
              System.out.print("\n\nCASILLA OCUPADA. Vuelva a introducir las coordenadas: ");
              System.out.print("\n\nIntroduce la coordenada Y (eje vertical):");
              ejeY = s.nextInt();
              System.out.print("\nIntroduce la coordenada X (eje horizontal):");
              ejeX = s.nextInt();
            }
          }
          
          if ((tablero[(ejeY-1)][(ejeX-1)])!=(" ")) {
            while ((tablero[(ejeY-1)][(ejeX-1)])!=(" ")) {
              System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
              System.out.print("\n\n\n\nTURNO DEL JUGADOR 1");
              System.out.print("\nLo siento la casilla está ocupada por una ficha, vuelva a introducir las coordenadas: ");
              System.out.print("\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
              System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
              System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
              System.out.printf("\n  1 2 3");
              System.out.print("\nIntroduce la coordenada Y (eje vertical):");
              ejeY = s.nextInt();
              System.out.print("\nIntroduce la coordenada X (eje horizontal):");
              ejeX = s.nextInt();
            }
          }
          if ((tablero[(ejeY-1)][(ejeX-1)]).equals(" ")) {
            tablero[(ejeY-1)][(ejeX-1)]="O";
          }

          contadorFichas+=1;

          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
          System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
          System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
          System.out.printf("\n  1 2 3");

          if ((prueba+=tablero[0][0]+tablero[0][1]+tablero[0][2]).equals("OOO")) {      //Comprobación de si ha ganado el Jugador 1
            System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
            jugador1Gana=true;
            victoriasJ1+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[1][0]+tablero[1][1]+tablero[1][2]).equals("OOO")) {
            System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
            jugador1Gana=true;
            victoriasJ1+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[2][0]+tablero[2][1]+tablero[2][2]).equals("OOO")) {
            System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
            jugador1Gana=true;
            victoriasJ1+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[0][0]+tablero[1][0]+tablero[2][0]).equals("OOO")) {
              System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
              jugador1Gana=true;
              victoriasJ1+=1;
              prueba="";
          } else {
                prueba="";
          }
          if ((prueba+=tablero[0][1]+tablero[1][1]+tablero[2][1]).equals("OOO")) {
              System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
              jugador1Gana=true;
              victoriasJ1+=1;
              prueba="";
          } else {
                prueba="";
          }
          if ((prueba+=tablero[0][2]+tablero[1][2]+tablero[2][2]).equals("OOO")) {
              System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
              jugador1Gana=true;
              victoriasJ1+=1;
              prueba="";
          } else {
                prueba="";
          }
          if ((prueba+=tablero[0][0]+tablero[1][1]+tablero[2][2]).equals("OOO")) {
            System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
            jugador1Gana=true;
            victoriasJ1+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[0][2]+tablero[1][1]+tablero[2][0]).equals("OOO")) {
            System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
            jugador1Gana=true;
            victoriasJ1+=1;
            prueba="";
          } else {
              prueba="";
          }

          if (contadorFichas<9 && !jugador1Gana) {                            //Si Jugador 1 no ha ganado y el número de fichas es inferior a 9 pasamos turno a Jugador 2
            turnoJ1=false;
            turnoJ2=true;
          } else if (contadorFichas==9 && !jugador1Gana) {
           System.out.print("\n\n\nEMPATE. NINGUNO HA GANADO.");
           System.out.print("\n\n\n¿Desea seguir jugando? (S/N): ");
              s.nextLine();
              respuesta = s.nextLine().toUpperCase();
              turnoJ1=false;
              turnoJ2=true;
              contadorFichas=0;
              for (int i=0; i<3; i++){
                for (int j=0; j<3; j++) {
                  tablero [i][j] = " ";
                }
              }
            }
          if (jugador1Gana && victoriasJ1>(partidaAlMejorDe/2)) {             //Si Jugador 1 gana y su contador de victorias es superior a la mitad de "al mejor de" entonces acaba la partida
            finDeJuego=true;        
          } else if (jugador1Gana && !finDeJuego) {
            System.out.print("\n\n\n¿Desea seguir jugando? (S/N): ");
            s.nextLine();
            respuesta = s.nextLine().toUpperCase();
            jugador1Gana=false;
            turnoJ1=true;
            turnoJ2=false;
            contadorFichas=0;
            for (int i=0; i<3; i++){
              for (int j=0; j<3; j++) {
                tablero [i][j] = " ";
              }
            }
          }
          
          //En caso de responder de forma errónea se vuelve a pedir la respuesta
          while (!(respuesta.equals("S") || respuesta.equals("N") || respuesta.equals("HOLA JOSHUA") || respuesta.equals("AUTOR"))) {
            System.out.print("\n\n\nRespuesta incorrecta.");
            System.out.print("\n¿Desea seguir jugando? (S/N): ");
            respuesta = s.nextLine().toUpperCase();
          }
          if (respuesta.equals("HOLA JOSHUA")) {         //EASTEREGG!! Película "Juegos de Guerra" (1983).
            try {
              Thread.sleep(1000);
            } catch (Exception e){}
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.print("SALUDOS PROFESOR FALKEN.");
            try {
              Thread.sleep(5000);
            } catch (Exception e){}
            System.out.print("\n\nEXTRAÑO JUEGO. EL ÚNICO MOVIMIENTO PARA GANAR ES NO JUGAR.");
            try {
              Thread.sleep(8000);
            } catch (Exception e){}
            System.out.print("\n\n¿LE GUSTARÍA UNA PARTIDITA DE AJEDREZ?");
            try {
              Thread.sleep(5000);
            } catch (Exception e){}
            System.out.print("\n\n\n\n\n¡¡Felicidades has encontrado el EasterEgg!!");
            try {
              Thread.sleep(5000);
            } catch (Exception e){}
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.print("¿Le gustaría retomar el juego? (S/N)");
            respuesta = s.nextLine().toUpperCase();
            while (!(respuesta.equals("S") || respuesta.equals("N"))) {
              System.out.print("\n\n\nRespuesta incorrecta.");
              System.out.print("\n¿Desea retomar el juego? (S/N)");
              respuesta = s.nextLine().toUpperCase();
            }
          }
          
          if (respuesta.equals("AUTOR")) {  //EASTEREGG!! Firma del autor, homenaje al EasterEgg de Adventure de Atari 2600.
          try {
            Thread.sleep(1000);
          } catch (Exception e){}
          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("\n                              C");
          System.out.print("\n                              r   b");
          System.out.print("\n                              e   y");
          System.out.print("\n                              a   .");
          System.out.print("\n                              t   .");
          System.out.print("\n                              e   |");
          System.out.print("\n                              d   .");
          System.out.print("\n                              F.  |");
          System.out.print("\n                              J   .");
          System.out.print("\n                              a   G");
          System.out.print("\n                              v   l");
          System.out.print("\n                              i   e");
          System.out.print("\n                              e   z");
          System.out.print("\n                              r   S");
          System.out.print("\n                              .   a");
          System.out.print("\n                              .   b");
          System.out.print("\n                              |   a");
          System.out.print("\n                              .   r");
          System.out.print("\n                              |   i");
          System.out.print("\n                              |   e");
          System.out.print("\n                              .   g");
          System.out.print("\n                              .   o");
          System.out.print("\n\n\n¡¡Felicidades has encontrado el EasterEgg!!");
          try {
            Thread.sleep(5000);
          } catch (Exception e){}
          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("¿Le gustaría retomar el juego? (S/N)");
          respuesta = s.nextLine().toUpperCase();
          while (!(respuesta.equals("S") || respuesta.equals("N"))) {
            System.out.print("\n\n\nRespuesta incorrecta.");
            System.out.print("\n¿Desea retomar el juego? (S/N)");
            respuesta = s.nextLine().toUpperCase();
          }
        }
          
          if (respuesta.equals("N")) {
           finDeJuego=true;
          }
        }
        
        //Turno de la IA:
        while (turnoJ2 && !finDeJuego) {
          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("TURNO DE JOSHUA");
          System.out.print("\n\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
          System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
          System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
          System.out.printf("\n  1 2 3");
          System.out.print("\n\nPOR FAVOR ESPERE. TURNO DE JOSHUA");
          
          //Para dar la impresión de que la IA está pensando le doy 3 segundos de tiempo
          try {
            Thread.sleep(3000);
          } catch (Exception e){}
          
          /*Aqui comienza el trabajo de la IA:
           * 
           * Si el contador de fichas es 0 la IA está iniciando la partida y, por ventaja estratégica
           * optará por introducir su primera ficha en el centro del tablero.
           */
          if (contadorFichas==0) {
            tablero[1][1]="X";
            turnoJ2=false;
          }
          
          //Evitar movimiento ganador:
          if ((contadorFichas==3 && turnoJ2) && ((prueba+=tablero[0][0]+tablero[1][1]+tablero[2][2]).equals("OOX"))) {
            azar=(int)(Math.random()*2);
            if (tablero[2][0]==" " && azar==0) {
              tablero[2][0]="X";
              turnoJ2=false;
              prueba="";
            }
            if (tablero[0][2]==" " && azar==1) {
              tablero[0][2]="X";
              turnoJ2=false;
              prueba="";
            }
          } else {
            prueba="";
          }
          if ((contadorFichas==3 && turnoJ2) && ((prueba+=tablero[0][0]+tablero[1][1]+tablero[2][2]).equals("XOO"))) {
            azar=(int)(Math.random()*2);
            if (tablero[2][0]==" " && azar==0) {
              tablero[2][0]="X";
              turnoJ2=false;
              prueba="";
            }
            if (tablero[0][2]==" " && azar==1) {
              tablero[0][2]="X";
              turnoJ2=false;
              prueba="";
            }
          } else {
            prueba="";
          }
          if ((contadorFichas==3 && turnoJ2) && ((prueba+=tablero[2][0]+tablero[1][1]+tablero[0][2]).equals("OOX"))) {
            azar=(int)(Math.random()*2);
            if (tablero[0][0]==" " && azar==0) {
              tablero[0][0]="X";
              turnoJ2=false;
              prueba="";
            }
            if (tablero[2][2]==" " && azar==1) {
              tablero[2][2]="X";
              turnoJ2=false;
              prueba="";
            }
          }  else {
            prueba="";
          }
          if ((contadorFichas==3 && turnoJ2) && ((prueba+=tablero[2][0]+tablero[1][1]+tablero[0][2]).equals("XOO"))) {
            azar=(int)(Math.random()*2);
            if (tablero[0][0]==" " && azar==0) {
              tablero[0][0]="X";
              turnoJ2=false;
              prueba="";
            }
            if (tablero[2][2]==" " && azar==1) {
              tablero[2][2]="X";
              turnoJ2=false;
              prueba="";
            }
          }  else {
            prueba="";
          }
          
          
          /*En caso de no iniciar la partida o de llevar varios turnos en marcha se inicia esta secuencia:
           * 
           * Con este código la máquina primero comprueba si tiene posibilidad de ganar.
           *    En caso de poder ganar inserta ficha en la casilla libre y gana.
           * 
           * En caso de no poder ganar hará la comprobación de si el jugador humano puede ganarle.
           *    En caso de que el jugador humano pueda ganar la máquina introducirá ficha para impedirlo.
           * 
           * Si ninguno de estos casos es posible la máquina pondrá ficha en una casilla al azar mientras
           * que esta casilla al azar no está ocupada por otra ficha, en ese caso generaría otra tirada.
           */
          
          if (turnoJ2 && (prueba+=tablero[2][0]+tablero[1][0]+tablero[0][0]).equals("XX ")) {  //Esquina superior izquierda libre
            tablero[0][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][2]+tablero[1][1]+tablero[0][0]).equals("XX ")) {
            tablero[0][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][2]+tablero[0][1]+tablero[0][0]).equals("XX ")) {
            tablero[0][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][0]+tablero[0][1]+tablero[0][2]).equals("XX ")) {  //Esquina superior derecha libre
            tablero[0][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][0]+tablero[1][1]+tablero[0][2]).equals("XX ")) {
            tablero[0][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][2]+tablero[1][2]+tablero[0][2]).equals("XX ")) {
            tablero[0][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][0]+tablero[2][1]+tablero[2][2]).equals("XX ")) {  //Esquina inferior derecha libre
            tablero[2][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][0]+tablero[1][1]+tablero[2][2]).equals("XX ")) {
            tablero[2][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][2]+tablero[1][2]+tablero[2][2]).equals("XX ")) {
            tablero[2][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][0]+tablero[1][0]+tablero[2][0]).equals("XX ")) {  //Esquina inferior izquierda libre
            tablero[2][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][2]+tablero[1][1]+tablero[2][0]).equals("XX ")) {
            tablero[2][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][2]+tablero[2][1]+tablero[2][0]).equals("XX ")) {
            tablero[2][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][1]+tablero[1][1]+tablero[2][1]).equals("X X")) {  //Casilla del centro libre
            tablero[1][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[1][0]+tablero[1][1]+tablero[1][2]).equals("X X")) {
            tablero[1][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][0]+tablero[1][1]+tablero[2][2]).equals("X X")) {
            tablero[1][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][0]+tablero[1][1]+tablero[0][2]).equals("X X")) {
            tablero[1][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][0]+tablero[0][1]+tablero[0][2]).equals("X X")) {  //Casilla superior central libre
            tablero[0][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][1]+tablero[1][1]+tablero[0][1]).equals("XX ")) {
            tablero[0][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][2]+tablero[1][2]+tablero[2][2]).equals("X X")) {  //Casilla lateral derecha central libre
            tablero[1][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[1][0]+tablero[1][1]+tablero[1][2]).equals("XX ")) {
            tablero[1][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][0]+tablero[2][1]+tablero[2][2]).equals("X X")) {  //Casilla inferior central libre
            tablero[2][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][1]+tablero[1][1]+tablero[2][1]).equals("XX ")) {
            tablero[2][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][0]+tablero[1][0]+tablero[2][0]).equals("X X")) {  //Casilla lateral izquierda central libre
            tablero[1][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[1][2]+tablero[1][1]+tablero[1][0]).equals("XX ")) {
            tablero[1][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          
          
          
          if (turnoJ2 && (prueba+=tablero[2][0]+tablero[1][0]+tablero[0][0]).equals("OO ")) {  //Esquina superior izquierda libre
            tablero[0][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][2]+tablero[1][1]+tablero[0][0]).equals("OO ")) {
            tablero[0][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][2]+tablero[0][1]+tablero[0][0]).equals("OO ")) {
            tablero[0][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][0]+tablero[0][1]+tablero[0][2]).equals("OO ")) {  //Esquina superior derecha libre
            tablero[0][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][0]+tablero[1][1]+tablero[0][2]).equals("OO ")) {
            tablero[0][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][2]+tablero[1][2]+tablero[0][2]).equals("OO ")) {
            tablero[0][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][0]+tablero[2][1]+tablero[2][2]).equals("OO ")) {  //Esquina inferior derecha libre
            tablero[2][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][0]+tablero[1][1]+tablero[2][2]).equals("OO ")) {
            tablero[2][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][2]+tablero[1][2]+tablero[2][2]).equals("OO ")) {
            tablero[2][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][0]+tablero[1][0]+tablero[2][0]).equals("OO ")) {  //Esquina inferior izquierda libre
            tablero[2][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][2]+tablero[1][1]+tablero[2][0]).equals("OO ")) {
            tablero[2][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][2]+tablero[2][1]+tablero[2][0]).equals("OO ")) {
            tablero[2][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][1]+tablero[1][1]+tablero[2][1]).equals("O O")) {  //Casilla del centro libre
            tablero[1][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[1][0]+tablero[1][1]+tablero[1][2]).equals("O O")) {
            tablero[1][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][0]+tablero[1][1]+tablero[2][2]).equals("O O")) {
            tablero[1][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][0]+tablero[1][1]+tablero[0][2]).equals("O O")) {
            tablero[1][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][0]+tablero[0][1]+tablero[0][2]).equals("O O")) {  //Casilla superior central libre
            tablero[0][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][1]+tablero[1][1]+tablero[0][1]).equals("OO ")) {
            tablero[0][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][2]+tablero[1][2]+tablero[2][2]).equals("O O")) {  //Casilla lateral derecha central libre
            tablero[1][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[1][0]+tablero[1][1]+tablero[1][2]).equals("OO ")) {
            tablero[1][2]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[2][0]+tablero[2][1]+tablero[2][2]).equals("O O")) {  //Casilla inferior central libre
            tablero[2][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][1]+tablero[1][1]+tablero[2][1]).equals("OO ")) {
            tablero[2][1]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[0][0]+tablero[1][0]+tablero[2][0]).equals("O O")) {  //Casilla lateral izquierda central libre
            tablero[1][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          if (turnoJ2 && (prueba+=tablero[1][2]+tablero[1][1]+tablero[1][0]).equals("OO ")) {
            tablero[1][0]="X";
            turnoJ2=false;
            prueba="";
          } else {
            prueba="";
          }
          
          if (turnoJ2) {
            ejeY=(int)(Math.random()*3+1);
            ejeX=(int)(Math.random()*3+1);
            while (tablero[(ejeY-1)][(ejeX-1)]!=(" ")) {
              ejeY=(int)(Math.random()*3+1);
              ejeX=(int)(Math.random()*3+1);
            }
          }
          
          if (turnoJ2 && (tablero[(ejeY-1)][(ejeX-1)]).equals(" ")) {
            tablero[(ejeY-1)][(ejeX-1)]="X";
          }

          contadorFichas+=1;

          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
          System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
          System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
          System.out.printf("\n  1 2 3");

          if ((prueba+=tablero[0][0]+tablero[0][1]+tablero[0][2]).equals("XXX")) {      //Comprobación de si ha ganado el Jugador 2
            System.out.print("\n\n¡¡Joshua ha ganado!!");
            jugador2Gana=true;
            victoriasJ2+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[1][0]+tablero[1][1]+tablero[1][2]).equals("XXX")) {
            System.out.print("\n\n¡¡Joshua ha ganado!!");
            jugador2Gana=true;
            victoriasJ2+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[2][0]+tablero[2][1]+tablero[2][2]).equals("XXX")) {
            System.out.print("\n\n¡¡Joshua ha ganado!!");
            jugador2Gana=true;
            victoriasJ2+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[0][0]+tablero[1][0]+tablero[2][0]).equals("XXX")) {
              System.out.print("\n\n¡¡Joshua ha ganado!!");
              jugador2Gana=true;
              victoriasJ2+=1;
              prueba="";
          } else {
                prueba="";
          }
          if ((prueba+=tablero[0][1]+tablero[1][1]+tablero[2][1]).equals("XXX")) {
              System.out.print("\n\n¡¡Joshua ha ganado!!");
              jugador2Gana=true;
              victoriasJ2+=1;
              prueba="";
          } else {
                prueba="";
          }
          if ((prueba+=tablero[0][2]+tablero[1][2]+tablero[2][2]).equals("XXX")) {
              System.out.print("\n\n¡¡Joshua ha ganado!!");
              jugador2Gana=true;
              victoriasJ2+=1;
              prueba="";
          } else {
                prueba="";
          }
          if ((prueba+=tablero[0][0]+tablero[1][1]+tablero[2][2]).equals("XXX")) {
            System.out.print("\n\n¡¡Joshua ha ganado!!");
            jugador2Gana=true;
            victoriasJ2+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[0][2]+tablero[1][1]+tablero[2][0]).equals("XXX")) {
            System.out.print("\n\n¡¡Joshua ha ganado!!");
            jugador2Gana=true;
            victoriasJ2+=1;
            prueba="";
          } else {
              prueba="";
          }

          if (contadorFichas<9 && !jugador2Gana) {                            //Si Jugador 2 no ha ganado y el número de fichas es inferior a 9 pasamos turno a Jugador 1
            turnoJ1=true;
            turnoJ2=false;
          } else if (contadorFichas==9 && !jugador2Gana) {
           System.out.print("\n\n\nEMPATE. NINGUNO HA GANADO.");
           System.out.print("\n\n\n¿Desea seguir jugando? (S/N): ");
           s.nextLine();
              respuesta = s.nextLine().toUpperCase();
              turnoJ1=true;
              turnoJ2=false;
              contadorFichas=0;
              for (int i=0; i<3; i++){
                for (int j=0; j<3; j++) {
                  tablero [i][j] = " ";
                }
              }
            }
          if (jugador2Gana && victoriasJ2>(partidaAlMejorDe/2)) {             //Si Jugador 2 gana y su contador de victorias es superior a la mitad de "al mejor de" entonces acaba la partida
            finDeJuego=true;        
          } else if (jugador2Gana && !finDeJuego) {
            System.out.print("\n\n\n¿Desea seguir jugando? (S/N): ");
            s.nextLine();
            respuesta = s.nextLine().toUpperCase();
            jugador2Gana=false;
            turnoJ1=false;
            turnoJ2=true;
            contadorFichas=0;
            for (int i=0; i<3; i++){
              for (int j=0; j<3; j++) {
                tablero [i][j] = " ";
              }
            }
          }
          
        //En caso de responder de forma errónea se vuelve a pedir la respuesta
          while (!(respuesta.equals("S") || respuesta.equals("N") || respuesta.equals("HOLA JOSHUA") || respuesta.equals("AUTOR"))) {
            System.out.print("\n\n\nRespuesta incorrecta.");
            System.out.print("\n¿Desea seguir jugando? (S/N): ");
            respuesta = s.nextLine().toUpperCase();
          }
          if (respuesta.equals("HOLA JOSHUA")) {         //EASTEREGG!! Película "Juegos de Guerra" (1983).
            try {
              Thread.sleep(1000);
            } catch (Exception e){}
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.print("SALUDOS PROFESOR FALKEN.");
            try {
              Thread.sleep(5000);
            } catch (Exception e){}
            System.out.print("\n\nEXTRAÑO JUEGO. EL ÚNICO MOVIMIENTO PARA GANAR ES NO JUGAR.");
            try {
              Thread.sleep(8000);
            } catch (Exception e){}
            System.out.print("\n\n¿LE GUSTARÍA UNA PARTIDITA DE AJEDREZ?");
            try {
              Thread.sleep(5000);
            } catch (Exception e){}
            System.out.print("\n\n\n\n\n¡¡Felicidades has encontrado el EasterEgg!!");
            try {
              Thread.sleep(5000);
            } catch (Exception e){}
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.print("¿Le gustaría retomar el juego? (S/N)");
            respuesta = s.nextLine().toUpperCase();
            while (!(respuesta.equals("S") || respuesta.equals("N"))) {
              System.out.print("\n\n\nRespuesta incorrecta.");
              System.out.print("\n¿Desea retomar el juego? (S/N)");
              respuesta = s.nextLine().toUpperCase();
            }
          }
          
          if (respuesta.equals("AUTOR")) {  //EASTEREGG!! Firma del autor, homenaje al EasterEgg de Adventure de Atari 2600.
          try {
            Thread.sleep(1000);
          } catch (Exception e){}
          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("\n                              C");
          System.out.print("\n                              r   b");
          System.out.print("\n                              e   y");
          System.out.print("\n                              a   .");
          System.out.print("\n                              t   .");
          System.out.print("\n                              e   |");
          System.out.print("\n                              d   .");
          System.out.print("\n                              F.  |");
          System.out.print("\n                              J   .");
          System.out.print("\n                              a   G");
          System.out.print("\n                              v   l");
          System.out.print("\n                              i   e");
          System.out.print("\n                              e   z");
          System.out.print("\n                              r   S");
          System.out.print("\n                              .   a");
          System.out.print("\n                              .   b");
          System.out.print("\n                              |   a");
          System.out.print("\n                              .   r");
          System.out.print("\n                              |   i");
          System.out.print("\n                              |   e");
          System.out.print("\n                              .   g");
          System.out.print("\n                              .   o");
          System.out.print("\n\n\n¡¡Felicidades has encontrado el EasterEgg!!");
          try {
            Thread.sleep(5000);
          } catch (Exception e){}
          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("¿Le gustaría retomar el juego? (S/N)");
          respuesta = s.nextLine().toUpperCase();
          while (!(respuesta.equals("S") || respuesta.equals("N"))) {
            System.out.print("\n\n\nRespuesta incorrecta.");
            System.out.print("\n¿Desea retomar el juego? (S/N)");
            respuesta = s.nextLine().toUpperCase();
          }
        }
          
          if (respuesta.equals("N")) {
           finDeJuego=true;
          }
        }
        if (finDeJuego && victoriasJ1>victoriasJ2) {
         System.out.print("\n\n\n\n\nFIN DE PARTIDA. GANADOR: JUGADOR 1.");
        } else if (finDeJuego && victoriasJ2>victoriasJ1)
         System.out.print("\n\n\n\n\nFIN DE PARTIDA. GANADOR: JOSHUA.");
      }
    }
    
    //########################################################################################
    
    //Modo 2 jugadores:
    if (modoDeJuego==2) {
      System.out.print("\n¿Al mejor de cuantas partidas quiere jugar? Elija una opción insertando el número del paréntesis:");
      System.out.print("\n(1) Al mejor de 3. Gana el primero en obtener 2 victorias.");
      System.out.print("\n(2) Al mejor de 5. Gana el primero en obtener 3 victorias.");
      System.out.print("\n(3) Al mejor de 7. Gana el primero en obtener 4 victorias.");
      System.out.println("\n(4) Al mejor de 9. Gana el primero en obtener 5 victorias.");
      numeroMenu=s.nextInt();
      
      
      while (numeroMenu<1 || numeroMenu>4) {
        System.out.print("\n¿Al mejor de cuantas partidas quiere jugar? Elija una opción insertando el número del paréntesis:");
        System.out.print("\n(1) Al mejor de 3. Gana el primero en obtener 2 victorias.");
        System.out.print("\n(2) Al mejor de 5. Gana el primero en obtener 3 victorias.");
        System.out.print("\n(3) Al mejor de 7. Gana el primero en obtener 4 victorias.");
        System.out.println("\n(4) Al mejor de 9. Gana el primero en obtener 5 victorias.");
        numeroMenu=s.nextInt();
      }
      
      
      if (numeroMenu==1) {          //Elección del menú para declarar victorioso al mejor de x partidas
        partidaAlMejorDe = 3;
      } else if (numeroMenu==2) {
          partidaAlMejorDe = 5;
      } else if (numeroMenu==3) {
          partidaAlMejorDe = 7;
      } else if (numeroMenu==4) {
          partidaAlMejorDe = 9;
      }
      
      //Tirada aleatoria para declarar el inicio de uno de los dos jugadores
      System.out.print("Voy a realizar una tirada aleatoria para cada jugador, el que saque más puntuación comienza la partida: ");
      tiradaJ1 = (int)(Math.random()*100+1);
      tiradaJ2 = (int)(Math.random()*100+1);
      
      
      while (tiradaJ1==tiradaJ2) {
        tiradaJ1 = (int)(Math.random()*100+1);
        tiradaJ2 = (int)(Math.random()*100+1);
      }
      
      
      System.out.print("\n\nEl Jugador 1 obtiene una tirada de: " + tiradaJ1 + " y el Jugador 2 obtiene una tirada de: " + tiradaJ2);
      
      if (tiradaJ1>tiradaJ2) {
        turnoJ1=true;
        System.out.print("\n\nCOMIENZA EL JUGADOR 1");
      } else {
          turnoJ2=true;
          System.out.print("\n\nCOMIENZA EL JUGADOR 2");
      }
      
      System.out.print("\n\nLa partida comenzará en 3 segundos.");
      
      try {
        Thread.sleep(3000);
      } catch (Exception e){}
      
      
      while (!finDeJuego) {                                                    //bucle de la partida
        
        //Turno Jugador 1:
        while (turnoJ1 && !finDeJuego) {
          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("TURNO DEL JUGADOR 1");
          System.out.print("\n\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
          System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
          System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
          System.out.printf("\n  1 2 3");
          System.out.print("\n\nIntroduce la coordenada Y (eje vertical):");
          ejeY = s.nextInt();
          System.out.print("\nIntroduce la coordenada X (eje horizontal):");
          ejeX = s.nextInt();
          
          while ((ejeY<1 || ejeY>3) || (ejeX<1 || ejeX>3) || ((tablero[(ejeY-1)][(ejeX-1)])!=(" "))) {
            if ((ejeY<1 || ejeY>3) || (ejeX<1 || ejeX>3)) {
              System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
              System.out.print("TURNO DEL JUGADOR 1");
              System.out.print("\n\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
              System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
              System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
              System.out.printf("\n  1 2 3");
              System.out.print("\n\nCOORDENADAS INCORRECTAS. No puede insertar un valor inferior a 1 o superior a 3. Por favor vuelva a insertar las coordenadas:");
              System.out.print("\n\nIntroduce la coordenada Y (eje vertical):");
              ejeY = s.nextInt();
              System.out.print("\nIntroduce la coordenada X (eje horizontal):");
              ejeX = s.nextInt();
            } else if ((tablero[(ejeY-1)][(ejeX-1)])!=(" ")) {
              System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
              System.out.print("TURNO DEL JUGADOR 1");
              System.out.print("\n\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
              System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
              System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
              System.out.printf("\n  1 2 3");
              System.out.print("\n\nCASILLA OCUPADA. Vuelva a introducir las coordenadas: ");
              System.out.print("\n\nIntroduce la coordenada Y (eje vertical):");
              ejeY = s.nextInt();
              System.out.print("\nIntroduce la coordenada X (eje horizontal):");
              ejeX = s.nextInt();
            }
          }
          
          if ((tablero[(ejeY-1)][(ejeX-1)]).equals(" ")) {
            tablero[(ejeY-1)][(ejeX-1)]="O";
          }

          contadorFichas+=1;

          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
          System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
          System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
          System.out.printf("\n  1 2 3");

          if ((prueba+=tablero[0][0]+tablero[0][1]+tablero[0][2]).equals("OOO")) {      //Comprobación de si ha ganado el Jugador 1
            System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
            jugador1Gana=true;
            victoriasJ1+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[1][0]+tablero[1][1]+tablero[1][2]).equals("OOO")) {
            System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
            jugador1Gana=true;
            victoriasJ1+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[2][0]+tablero[2][1]+tablero[2][2]).equals("OOO")) {
            System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
            jugador1Gana=true;
            victoriasJ1+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[0][0]+tablero[1][0]+tablero[2][0]).equals("OOO")) {
              System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
              jugador1Gana=true;
              victoriasJ1+=1;
              prueba="";
          } else {
                prueba="";
          }
          if ((prueba+=tablero[0][1]+tablero[1][1]+tablero[2][1]).equals("OOO")) {
              System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
              jugador1Gana=true;
              victoriasJ1+=1;
              prueba="";
          } else {
                prueba="";
          }
          if ((prueba+=tablero[0][2]+tablero[1][2]+tablero[2][2]).equals("OOO")) {
              System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
              jugador1Gana=true;
              victoriasJ1+=1;
              prueba="";
          } else {
                prueba="";
          }
          if ((prueba+=tablero[0][0]+tablero[1][1]+tablero[2][2]).equals("OOO")) {
            System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
            jugador1Gana=true;
            victoriasJ1+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[0][2]+tablero[1][1]+tablero[2][0]).equals("OOO")) {
            System.out.print("\n\n¡¡Jugador 1 ha ganado!!");
            jugador1Gana=true;
            victoriasJ1+=1;
            prueba="";
          } else {
              prueba="";
          }

          if (contadorFichas<9 && !jugador1Gana) {                            //Si Jugador 1 no ha ganado y el número de fichas es inferior a 9 pasamos turno a Jugador 2
            turnoJ1=false;
            turnoJ2=true;
          } else if (contadorFichas==9 && !jugador1Gana) {
            System.out.print("\n\n\nEMPATE. NINGUNO HA GANADO.");
            System.out.print("\n\n\n¿Desea seguir jugando? (S/N)");
              s.nextLine();
              respuesta = s.nextLine().toUpperCase();
              turnoJ1=false;
              turnoJ2=true;
              contadorFichas=0;
              for (int i=0; i<3; i++){
                for (int j=0; j<3; j++) {
                  tablero [i][j] = " ";
                }
              }
            }
          if (jugador1Gana && victoriasJ1>(partidaAlMejorDe/2)) {             //Si Jugador 1 gana y su contador de victorias es superior a la mitad de "al mejor de" entonces acaba la partida
            finDeJuego=true;        
          } else if (jugador1Gana && !finDeJuego) {
            System.out.print("\n\n\n¿Desea seguir jugando? (S/N)");
            s.nextLine();
            respuesta = s.nextLine().toUpperCase();
            jugador1Gana=false;
            turnoJ1=true;
            turnoJ2=false;
            contadorFichas=0;
            for (int i=0; i<3; i++){
              for (int j=0; j<3; j++) {
                tablero [i][j] = " ";
              }
            }
          }
          
          while (!(respuesta.equals("S") || respuesta.equals("N") || respuesta.equals("AUTOR"))) {
            System.out.print("\n\n\nRespuesta incorrecta.");
            System.out.print("\n¿Desea seguir jugando? (S/N): ");
            respuesta = s.nextLine().toUpperCase();
          }
          if (respuesta.equals("N")) {
            finDeJuego=true;
          }
        }
        
        if (respuesta.equals("AUTOR")) {  //EASTEREGG!! Firma del autor, homenaje al EasterEgg de Adventure de Atari 2600.
          try {
            Thread.sleep(1000);
          } catch (Exception e){}
          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("\n                              C");
          System.out.print("\n                              r   b");
          System.out.print("\n                              e   y");
          System.out.print("\n                              a   .");
          System.out.print("\n                              t   .");
          System.out.print("\n                              e   |");
          System.out.print("\n                              d   .");
          System.out.print("\n                              F.  |");
          System.out.print("\n                              J   .");
          System.out.print("\n                              a   G");
          System.out.print("\n                              v   l");
          System.out.print("\n                              i   e");
          System.out.print("\n                              e   z");
          System.out.print("\n                              r   S");
          System.out.print("\n                              .   a");
          System.out.print("\n                              .   b");
          System.out.print("\n                              |   a");
          System.out.print("\n                              .   r");
          System.out.print("\n                              |   i");
          System.out.print("\n                              |   e");
          System.out.print("\n                              .   g");
          System.out.print("\n                              .   o");
          System.out.print("\n\n\n¡¡Felicidades has encontrado el EasterEgg!!");
          try {
            Thread.sleep(5000);
          } catch (Exception e){}
          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("¿Le gustaría retomar el juego? (S/N)");
          respuesta = s.nextLine().toUpperCase();
          while (!(respuesta.equals("S") || respuesta.equals("N"))) {
            System.out.print("\n\n\nRespuesta incorrecta.");
            System.out.print("\n¿Desea retomar el juego? (S/N)");
            respuesta = s.nextLine().toUpperCase();
          }
        }
        
        //Turno del jugador 2
        while (turnoJ2 && !finDeJuego) {
          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("TURNO DEL JUGADOR 2");
          System.out.print("\n\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
          System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
          System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
          System.out.printf("\n  1 2 3");
          System.out.print("\nIntroduce la coordenada Y (eje vertical):");
          ejeY = s.nextInt();
          System.out.print("\nIntroduce la coordenada X (eje horizontal):");
          ejeX = s.nextInt();
          
          while ((ejeY<1 || ejeY>3) || (ejeX<1 || ejeX>3) || ((tablero[(ejeY-1)][(ejeX-1)])!=(" "))) {
            if ((ejeY<1 || ejeY>3) || (ejeX<1 || ejeX>3)) {
              System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
              System.out.print("TURNO DEL JUGADOR 2");
              System.out.print("\n\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
              System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
              System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
              System.out.printf("\n  1 2 3");
              System.out.print("\n\nCOORDENADAS INCORRECTAS. No puede insertar un valor inferior a 1 o superior a 3. Por favor vuelva a insertar las coordenadas:");
              System.out.print("\n\nIntroduce la coordenada Y (eje vertical):");
              ejeY = s.nextInt();
              System.out.print("\nIntroduce la coordenada X (eje horizontal):");
              ejeX = s.nextInt();
            } else if ((tablero[(ejeY-1)][(ejeX-1)])!=(" ")) {
              System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
              System.out.print("TURNO DEL JUGADOR 2");
              System.out.print("\n\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
              System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
              System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
              System.out.printf("\n  1 2 3");
              System.out.print("\n\nCASILLA OCUPADA. Vuelva a introducir las coordenadas: ");
              System.out.print("\n\nIntroduce la coordenada Y (eje vertical):");
              ejeY = s.nextInt();
              System.out.print("\nIntroduce la coordenada X (eje horizontal):");
              ejeX = s.nextInt();
            }
          }
          
          if ((tablero[(ejeY-1)][(ejeX-1)]).equals(" ")) {
            tablero[(ejeY-1)][(ejeX-1)]="X";
          }

          contadorFichas+=1;

          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("\n1 " + tablero[0][0] + " " + tablero[0][1] + " " + tablero[0][2]);
          System.out.print("\n2 " + tablero[1][0] + " " + tablero[1][1] + " " + tablero[1][2]);
          System.out.print("\n3 " + tablero[2][0] + " " + tablero[2][1] + " " + tablero[2][2]);
          System.out.printf("\n  1 2 3");

          if ((prueba+=tablero[0][0]+tablero[0][1]+tablero[0][2]).equals("XXX")) {      //Comprobación de si ha ganado el Jugador 2
            System.out.print("\n\n¡¡Jugador 2 ha ganado!!");
            jugador2Gana=true;
            victoriasJ2+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[1][0]+tablero[1][1]+tablero[1][2]).equals("XXX")) {
            System.out.print("\n\n¡¡Jugador 2 ha ganado!!");
            jugador2Gana=true;
            victoriasJ2+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[2][0]+tablero[2][1]+tablero[2][2]).equals("XXX")) {
            System.out.print("\n\n¡¡Jugador 2 ha ganado!!");
            jugador2Gana=true;
            victoriasJ2+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[0][0]+tablero[1][0]+tablero[2][0]).equals("XXX")) {
              System.out.print("\n\n¡¡Jugador 2 ha ganado!!");
              jugador2Gana=true;
              victoriasJ2+=1;
              prueba="";
          } else {
                prueba="";
          }
          if ((prueba+=tablero[0][1]+tablero[1][1]+tablero[2][1]).equals("XXX")) {
              System.out.print("\n\n¡¡Jugador 2 ha ganado!!");
              jugador2Gana=true;
              victoriasJ2+=1;
              prueba="";
          } else {
                prueba="";
          }
          if ((prueba+=tablero[0][2]+tablero[1][2]+tablero[2][2]).equals("XXX")) {
              System.out.print("\n\n¡¡Jugador 2 ha ganado!!");
              jugador2Gana=true;
              victoriasJ2+=1;
              prueba="";
          } else {
                prueba="";
          }
          if ((prueba+=tablero[0][0]+tablero[1][1]+tablero[2][2]).equals("XXX")) {
            System.out.print("\n\n¡¡Jugador 2 ha ganado!!");
            jugador2Gana=true;
            victoriasJ2+=1;
            prueba="";
          } else {
              prueba="";
          }
          if ((prueba+=tablero[0][2]+tablero[1][1]+tablero[2][0]).equals("XXX")) {
            System.out.print("\n\n¡¡Jugador 2 ha ganado!!");
            jugador2Gana=true;
            victoriasJ2+=1;
            prueba="";
          } else {
              prueba="";
          }

          if (contadorFichas<9 && !jugador2Gana) {                            //Si Jugador 2 no ha ganado y el número de fichas es inferior a 9 pasamos turno a Jugador 1
            turnoJ1=true;
            turnoJ2=false;
          } else if (contadorFichas==9 && !jugador2Gana) {
            System.out.print("\n\n\nEMPATE. NINGUNO HA GANADO.");
            System.out.print("\n\n\n¿Desea seguir jugando? (S/N)");
            s.nextLine();
              respuesta = s.nextLine().toUpperCase();
              turnoJ1=true;
              turnoJ2=false;
              contadorFichas=0;
              for (int i=0; i<3; i++){
                for (int j=0; j<3; j++) {
                  tablero [i][j] = " ";
                }
              }
            }
          if (jugador2Gana && victoriasJ2>(partidaAlMejorDe/2)) {             //Si Jugador 2 gana y su contador de victorias es superior a la mitad de "al mejor de" entonces acaba la partida
            finDeJuego=true;        
          } else if (jugador2Gana && !finDeJuego) {
            System.out.print("\n\n\n¿Desea seguir jugando? (S/N)");
            s.nextLine();
            respuesta = s.nextLine().toUpperCase();
            jugador2Gana=false;
            turnoJ1=false;
            turnoJ2=true;
            contadorFichas=0;
            for (int i=0; i<3; i++){
              for (int j=0; j<3; j++) {
                tablero [i][j] = " ";
              }
            }
          }
          
          while (!(respuesta.equals("S") || respuesta.equals("N") || respuesta.equals("AUTOR"))) {
            System.out.print("\n\n\nRespuesta incorrecta.");
            System.out.print("\n¿Desea seguir jugando? (S/N): ");
            respuesta = s.nextLine().toUpperCase();
          }
          if (respuesta.equals("N")) {
            finDeJuego=true;
          }
        }
        
        if (respuesta.equals("AUTOR")) {  //EASTEREGG!! Firma del autor, homenaje al EasterEgg de Adventure de Atari 2600.
          try {
            Thread.sleep(1000);
          } catch (Exception e){}
          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("\n                              C");
          System.out.print("\n                              r   b");
          System.out.print("\n                              e   y");
          System.out.print("\n                              a   .");
          System.out.print("\n                              t   .");
          System.out.print("\n                              e   |");
          System.out.print("\n                              d   .");
          System.out.print("\n                              F.  |");
          System.out.print("\n                              J   .");
          System.out.print("\n                              a   G");
          System.out.print("\n                              v   l");
          System.out.print("\n                              i   e");
          System.out.print("\n                              e   z");
          System.out.print("\n                              r   S");
          System.out.print("\n                              .   a");
          System.out.print("\n                              .   b");
          System.out.print("\n                              |   a");
          System.out.print("\n                              .   r");
          System.out.print("\n                              |   i");
          System.out.print("\n                              |   e");
          System.out.print("\n                              .   g");
          System.out.print("\n                              .   o");
          System.out.print("\n\n\n��Felicidades has encontrado el EasterEgg!!");
          try {
            Thread.sleep(5000);
          } catch (Exception e){}
          System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
          System.out.print("¿Le gustaría retomar el juego? (S/N)");
          respuesta = s.nextLine().toUpperCase();
          while (!(respuesta.equals("S") || respuesta.equals("N"))) {
            System.out.print("\n\n\nRespuesta incorrecta.");
            System.out.print("\n¿Desea retomar el juego? (S/N)");
            respuesta = s.nextLine().toUpperCase();
          }
        }
        
        if (finDeJuego && victoriasJ1>victoriasJ2) {
          System.out.print("\n\n\n\n\nFIN DE PARTIDA. GANADOR: JUGADOR 1.");
        } else if (finDeJuego && victoriasJ2>victoriasJ1)
          System.out.print("\n\n\n\n\nFIN DE PARTIDA. GANADOR: JUGADOR 2.");
      }
    }    
  }
}