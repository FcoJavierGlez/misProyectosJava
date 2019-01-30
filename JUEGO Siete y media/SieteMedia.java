/**Juego de la 7 y media v1.2
*
*   Este programa fue creado el viernes 2 de Noviembre del año 2018 por:
*
* @author Francisco Javier González Sabariego
* 
*         v1.0: (2 de Noviembre del año 2018)
*         v1.1: (3 de Noviembre del año 2018) "reparación de bugs"
*         v1.2: (10 de Noviembre del año 2018) "implementación del sistema de apuesta mínima y máxima,
*                bienvenida inicial al juego y anuncio del turno jugador/banca."
*
*/
import java.util.*;

public class SieteMedia{
 public static void main(String[] args){
    //Scanner
    Scanner s = new Scanner(System.in);

    //Variables:
    int turno = 1;                                //La partida inicia en el primer turno, cada 10 turnos aumenta la apuesta mínima en +5
    boolean jugadorSeHaPasado = false;            //Si la putuación del jugador excede 7,5 pasa a verdedaro y el jugador pierde el turno
    boolean jugadorSeHaPlantado = false;          //Si el jugador decide plantarse con la puntuación actual
    int dineroJugador = 500;                      //Dinero del jugador a lo largo de la partida
    double puntuacionJugador = 0;                 //Valor de la suma de los puntos de la mano del jugador
    String respuesta;                             //Respuesta de sí o no que da el jugador al juego
    int apuesta = 0;                              //El valor de la apuesta del jugador
    int apuestaMinima = 20;                       //El valor de la apuesta mínima para poder jugar, incrementa en +5 cada 10 turnos
    boolean bancaSeHaPasado = false;              //Si la suma de puntos de la banca excede 7,5
    boolean bancaSeHaPlantado = false;            //Si la banca se planta con su puntuación actual (no debe exceder los 7,5)
    int dineroBanca = 500;                        //Dinero de la banca a lo largo de la partida
    double puntuacionBanca = 0;                   //Valor de la suma de puntos de la mano de la banca
    boolean jugadorGanaPartida = false;           //Si el jugador gana la partida al reducir el dinero de la banca a <=0
    boolean jugadorPierdePartida = false;         //Si el jugador pierde la partida por reducir su dinero a 0
    int azar;                                     //Factor aleatorio para extraer cartas del mazo
    double [] cartasPuntos = new double [40];     //Array donde se almacenan los puntos de las cartas
    cartasPuntos [0] = 1;
    cartasPuntos [1] = 2;
    cartasPuntos [2] = 3;
    cartasPuntos [3] = 4;
    cartasPuntos [4] = 5;
    cartasPuntos [5] = 6;
    cartasPuntos [6] = 7;
    cartasPuntos [7] = 0.5;
    cartasPuntos [8] = 0.5;
    cartasPuntos [9] = 0.5;
    cartasPuntos [10] = 1;
    cartasPuntos [11] = 2;
    cartasPuntos [12] = 3;
    cartasPuntos [13] = 4;
    cartasPuntos [14] = 5;
    cartasPuntos [15] = 6;
    cartasPuntos [16] = 7;
    cartasPuntos [17] = 0.5;
    cartasPuntos [18] = 0.5;
    cartasPuntos [19] = 0.5;
    cartasPuntos [20] = 1;
    cartasPuntos [21] = 2;
    cartasPuntos [22] = 3;
    cartasPuntos [23] = 4;
    cartasPuntos [24] = 5;
    cartasPuntos [25] = 6;
    cartasPuntos [26] = 7;
    cartasPuntos [27] = 0.5;
    cartasPuntos [28] = 0.5;
    cartasPuntos [29] = 0.5;
    cartasPuntos [30] = 1;
    cartasPuntos [31] = 2;
    cartasPuntos [32] = 3;
    cartasPuntos [33] = 4;
    cartasPuntos [34] = 5;
    cartasPuntos [35] = 6;
    cartasPuntos [36] = 7;
    cartasPuntos [37] = 0.5;
    cartasPuntos [38] = 0.5;
    cartasPuntos [39] = 0.5;

    boolean [] cartasDisponibles = new boolean [40];     //Array indica la disponibilidad de las cartas para evitar repetir cartas del mazo
    cartasDisponibles [0] = true;
    cartasDisponibles [1] = true;
    cartasDisponibles [2] = true;
    cartasDisponibles [3] = true;
    cartasDisponibles [4] = true;
    cartasDisponibles [5] = true;
    cartasDisponibles [6] = true;
    cartasDisponibles [7] = true;
    cartasDisponibles [8] = true;
    cartasDisponibles [9] = true;
    cartasDisponibles [10] = true;
    cartasDisponibles [11] = true;
    cartasDisponibles [12] = true;
    cartasDisponibles [13] = true;
    cartasDisponibles [14] = true;
    cartasDisponibles [15] = true;
    cartasDisponibles [16] = true;
    cartasDisponibles [17] = true;
    cartasDisponibles [18] = true;
    cartasDisponibles [19] = true;
    cartasDisponibles [20] = true;
    cartasDisponibles [21] = true;
    cartasDisponibles [22] = true;
    cartasDisponibles [23] = true;
    cartasDisponibles [24] = true;
    cartasDisponibles [25] = true;
    cartasDisponibles [26] = true;
    cartasDisponibles [27] = true;
    cartasDisponibles [28] = true;
    cartasDisponibles [29] = true;
    cartasDisponibles [30] = true;
    cartasDisponibles [31] = true;
    cartasDisponibles [32] = true;
    cartasDisponibles [33] = true;
    cartasDisponibles [34] = true;
    cartasDisponibles [35] = true;
    cartasDisponibles [36] = true;
    cartasDisponibles [37] = true;
    cartasDisponibles [38] = true;
    cartasDisponibles [39] = true;
    
    
    System.out.print("¡¡Bienvenido al juego de la Siete y Media!!");
    
    
    while (!jugadorGanaPartida && !jugadorPierdePartida){         //Inicia el juego. Fase de preparación:      
      Arrays.fill(cartasDisponibles, Boolean.TRUE);               //Se resetea la disponibilidad de las cartas. Se baraja el mazo.
      puntuacionJugador = 0;                                      //Se resetea la puntuación de la mano del jugador.
      puntuacionBanca = 0;                                        //Se resetea la puntuación de la mano de la banca.
      jugadorSeHaPasado = false;                                  //Se resetean los booleanos de banca y jugador plantados y pasados de puntuación.
      jugadorSeHaPlantado = false;                                //      ""
      bancaSeHaPasado = false;                                    //      ""
      bancaSeHaPlantado = false;                                  //      ""
      if (turno>10) {                                             //Si el turno es superior a 10 incrementa la apuesta mínima y pones a 1 el contador del turno
        apuestaMinima+=5;
        turno=1;
        System.out.print("\n\n\n\n\nLA APUESTA MÍNIMA ACABA DE SUBIR. AHORA LA APUESTA MÍNIMA ES DE: " + apuestaMinima);
      }
      
      
      if (dineroJugador>=apuestaMinima) {
        System.out.print("\n\n\nLa banca posee un saldo de: " + dineroBanca);
        System.out.print("\n\nUsted posee un saldo de: " + dineroJugador);
        System.out.println("\n\nHaga su apuesta, recuerde que la apuesta mínima está ahora mismo en: " + apuestaMinima + ". ¿Cuánto desea apostar?");
        apuesta = s.nextInt();
      } else if(dineroJugador<apuestaMinima) {
        System.out.print("\n\nLa banca posee un saldo de: " + dineroBanca);
        System.out.print("\n\nUsted posee un saldo de: " + dineroJugador);
        System.out.println("\nDado que posee un saldo inferior a la apuesta mínima: " + apuestaMinima + ". Por favor, apueste su saldo restante.");
        apuesta = s.nextInt();
      }
      
      
      while ((dineroJugador>=apuestaMinima && (apuesta<apuestaMinima || apuesta>dineroJugador)) || (dineroJugador<apuestaMinima && apuesta!=dineroJugador)) {
        if (dineroJugador>=apuestaMinima && apuesta>dineroJugador) {
          System.out.print("\n\nAPUESTA INCORRECTA. SU APUESTA HA EXCEDIDO SU SALDO ACTUAL.");
          System.out.println("\nHaga su apuesta, recuerde que la apuesta mínima está ahora mismo en: " + apuestaMinima + ". ¿Cuánto desea apostar?");
          apuesta = s.nextInt();
        }
        if (dineroJugador>=apuestaMinima && apuesta<apuestaMinima) {
          System.out.print("\n\nAPUESTA INCORRECTA. SU APUESTA ES INFERIOR A LA APUESTA MÍNIMA.");
          System.out.println("\nHaga su apuesta, recuerde que la apuesta mínima está ahora mismo en: " + apuestaMinima + ". ¿Cuánto desea apostar?");
          apuesta = s.nextInt();
        } else if (dineroJugador<apuestaMinima && apuesta!=dineroJugador) {
          System.out.print("\n\nAPUESTA INCORRECTA. SU APUESTA NO EQUIVALE A SU SALDO ACTUAL.");
          System.out.println("\nDado que posee un saldo inferior a la apuesta mínima: " + apuestaMinima + ". Por favor, apueste su saldo restante.");
          apuesta = s.nextInt();
        }
      }
      
      
      dineroJugador-=apuesta;
      
      
      System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      
      
      azar = (int)(Math.random()*39);
      System.out.println("\nTURNO DEL JUGADOR: Usted recibe una carta de " + cartasPuntos[azar] + " su puntuación asciende a: " + (puntuacionJugador+=cartasPuntos[azar]));
      System.out.println("\n¿Desea recibir otra carta? (S/N)");
      s.nextLine();
      respuesta = s.nextLine().toUpperCase();
      if (puntuacionJugador<=7.5 && respuesta.equals("N")) {
          System.out.print("\nUsted se planta con: " + (puntuacionJugador) + " puntos.");
          jugadorSeHaPlantado=true;
        }
      while (respuesta.equals("S") && (!jugadorSeHaPasado && !jugadorSeHaPlantado)) {   //Turno del jugador
        azar = (int)(Math.random()*39);                                                 //Se hace una tirada aleatoria para sacar una carta del mazo
        if (cartasDisponibles[azar]!=true) {                                            //En caso de ser la segunda carta hay que evitar que se repita la misma
          while (cartasDisponibles[azar]!=true) {                                       //Mientras salga la misma se repite selección en el array
            azar = (int)(Math.random()*39);
          }
        }
        System.out.println("\nTURNO DEL JUGADOR: Usted recibe una carta de " + cartasPuntos[azar] + " su puntuación asciende a: " + (puntuacionJugador+=cartasPuntos[azar]));
        if (puntuacionJugador<7.5) {                                                    //Mientras la putuación sea inferior a 7.5 el jugador puede volver a pedir carta
          System.out.println("\n¿Desea recibir otra carta? (S/N)");
          respuesta = s.nextLine().toUpperCase(); 
        } else if (puntuacionJugador==7.5) {                                            //Si alcanza los 7.5 no hay necesidad de pedir más
            System.out.println("\nEnhorabuena usted ha logrado una puntuación de 7.5");
            jugadorSeHaPlantado=true;
        } else if (puntuacionJugador>7.5) {                                             //Si se pasa de 7.5 pierde el turno, ajustamos el dinero y empezamos de nuevo
            System.out.println("\nLo siento, usted se ha pasado de 7.5 puntos en: " + (puntuacionJugador-7.5) + " puntos. La banca gana la apuesta.");
            jugadorSeHaPasado=true;
            dineroBanca+=apuesta;
        }
        if (puntuacionJugador<7.5 && respuesta.equals("N")) {
          System.out.print("\nUsted se planta con: " + (puntuacionJugador) + " puntos.");
          jugadorSeHaPlantado=true;
        }    
      }
        try{
            Thread.sleep(3000);
          } catch (Exception e){}
        
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        
      if (!jugadorSeHaPasado && (!bancaSeHaPasado || !bancaSeHaPlantado)) {     //Turno de la banca mientras el jugador se plante y no pierda
        while (!bancaSeHaPasado && !bancaSeHaPlantado) {                        //Mientras la banca no pierda o se plante se repite
          azar = (int)(Math.random()*39);                                       //Tirada para selección de carta
          if (cartasDisponibles[azar]!=true) {                                  //Si la carta elegida ya ha salido repetimos tirada
            while (cartasDisponibles[azar]!=true) {
              azar = (int)(Math.random()*39);
            }
          }
          
          
          System.out.print("\n\nTURNO DE LA BANCA: La banca recibe una carta de " + cartasPuntos[azar] + " la puntuación de la banca asciende a: " + (puntuacionBanca+=cartasPuntos[azar]));
          try{                                                //Damos 3 segundos de tiempo para dar la impresión humana de que la máquina "toma una decisión"
            Thread.sleep(3000);
          } catch (Exception e){}
          if (puntuacionBanca<puntuacionJugador) {            //Si la puntuación es inferior a la del jugador elige carta de nuevo
            azar = (int)(Math.random()*39);
            if (cartasDisponibles[azar]!=true) {
              while (cartasDisponibles[azar]!=true) {
                azar = (int)(Math.random()*39);
              }
            }
          System.out.print("\n\nTURNO DE LA BANCA: La banca recibe una carta de " + cartasPuntos[azar] + " la puntuación de la banca asciende a: " + (puntuacionBanca+=cartasPuntos[azar]));
            try{
              Thread.sleep(3000);
            } catch (Exception e){}
          } 
          if (puntuacionBanca>puntuacionJugador && puntuacionBanca<7.5) {           //Si la puntuación de la banca es superior al jugador e inferior a 7.5 gana
            System.out.print("\n\nLa banca se planta y gana con una puntuación de: " + puntuacionBanca);
            bancaSeHaPlantado=true;
            dineroBanca+=apuesta;
          } else if (puntuacionBanca==puntuacionJugador) {                          //Si la puntuación es empatada la banca gana
              System.out.println("\n\nLa banca ha logrado una puntuación de " + puntuacionBanca + " empatando con el jugador. La banca gana.");
              bancaSeHaPlantado=true;
              dineroBanca+=apuesta;
          } else if (puntuacionBanca==7.5) {                                        //Si la banca alcanza los 7.5 puntos gana
              System.out.println("\n\nLa banca ha logrado una puntuación de 7.5 y, por tanto, la banca gana.");
              bancaSeHaPlantado=true;
              dineroBanca+=apuesta;
          } else if (puntuacionBanca>7.5) {                                         //Si la banca sobrepasa los 7.5 pierde
              System.out.println("\n\nLa banca se ha pasado de 7.5 puntos en: " + (puntuacionBanca-7.5) + " puntos. El jugador gana la apuesta.");
              bancaSeHaPasado=true;
              dineroBanca-=apuesta;
              dineroJugador+=(apuesta*2);
          }
        }
      }
      if (dineroBanca<=0) {
        System.out.print("\n\nEnhorabuena usted ha ganado la partida.");
        jugadorGanaPartida=true;
      } else if (dineroJugador<=0) {
          System.out.print("\n\nLo siento usted ha perdido la pardida. Suerte la próxima vez.");
          jugadorPierdePartida=true;
      }
    turno++;
    }
 }
}