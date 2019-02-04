
package barcos;

/**
 * Esta clase responde a la necesidad de crear una IA para el modo de un jugavor del juego Hundir la flota.<br><br>
 * 
 * Entre sus muchos métodos cuénta con los siguientes:
 * <ul>
 * <li>IA()</li>
 * <li>setNumDisparos()</li>
 * <li>setReiniciaNumDisparos()</li>
 * <li>getNumDisparos()</li>
 * <li>setHeDetectadoBarco()</li>
 * <li>setDesactivaDetectadoBarco()</li>
 * <li>getHeDetectadoBarco()</li>
 * <li>setCoordImpacto()</li>
 * <li>setPaso1()</li>
 * <li>setPaso2()</li>
 * <li>setPaso3()</li>
 * <li>reiniciaTableroIA()</li>
 * <li>reiniciaIA()</li>
 * <li>mapeaImpacto()</li>
 * <li>buscaNumeroMasAlto()</li>
 * <li>sonar()</li>
 * <li>rodeaConAgua()</li>
 * <li>hundirBarco()</li>
 * <li>compruebaBarcoImpactadoIA()</li>
 * <li>compruebaPosicionBarcoIA()</li>
 * <li>mensajeCoordenadasIA()</li>
 * </ul>
 * 
 * @author Francisco Javier González Sabariego
 * @version 1.1 || Fecha: 04/02/2019
 * 
 *    Versión 1.0 (Fecha: 02/02/2019): Creación de la IA.
 *    Versión 1.1 (Fecha: 04/02/2019): Añadido método por el cual la IA imprime las coordenadas de su disparo en pantalla.
 *
 */
public class IA {
  
  String nombreIA = "";                                       //Nombre de la IA, se le pasa como parámetro al constructor
  
  protected int numeroDisparos = 0;                           //Contador de disparos que ha realizado la IA
  
  protected boolean heDetectadoBarco = false;  
  
  protected boolean [] compruebaDireccion = {true, true, true, true};  
  
  protected boolean [] sigueDisparando = {false, false, false, false};
  
  int [][] coordImpacto = new int [1][2];
  
  int [][] coordPuntero = new int [1][2];
  
  protected boolean paso1 = false;
  
  protected boolean paso2 = false;
  
  
  
  public IA(String nombre) {
    
    nombreIA = nombre;
    
  }
  
  
  /**
   * Actualiza el numero de disparos cada vez que la IA realiza uno.
   * 
   */
  public void setNumDisparos() {
    
    numeroDisparos += 1;
    
  }
  
  
  /**
   * Reiniciamos el numero de disparos.
   * 
   */
  public void setReiniciaNumDisparos() {
    
    numeroDisparos = 0;
    
  }
  
  
  /**
   * Retorna el valor actual de números de disparos de la IA.
   * 
   * @return   Número entero de disparos hechos por la IA.
   */
  public int getNumDisparos() {
    
    return numeroDisparos;
    
  }
  
  
  /**
   * Activa el modo "barco detectado".
   * 
   */
  public void setHeDetectadoBarco() {
    
    this.heDetectadoBarco = true;
    
  }
  
  
  /**
   * Desactiva el modo "barco detectado".
   * 
   */
  public void setDesactivaDetectadoBarco() {
    
    //Desactivamos el modo "barco detectado":
    this.heDetectadoBarco = false;
    
    //Reiniciamos los pasos
    paso1 = false;
    
    paso2 = false;
    
    //Reiniciamos los array booleanos de direcciones:
    for (int i=0; i<4; i++) {
      
      compruebaDireccion[i] = true;
      
      sigueDisparando[i] = false;
      
    }
    
  }
  
  
  /**
   * Retorna el valor actual del modo "barco detectado".
   * 
   * @return   Verdadero o falso (si la IA  ha impactado en un barco y, por tanto, lo ha detectado).
   */
  public boolean getHedetectadoBarco() {
    
    return heDetectadoBarco;
    
  }
  
  
  /**
   * Almacena las coordenadas inciales donde ha encontrado el barco al ser impactado.
   * 
   * Y hace una copia en las coordenadas del puntero.
   * 
   * @param fila      Número fila (entero).
   * @param columna   Número columna (entero).
   */
  public void setCoordImpacto(int fila, int columna) {
    
    coordImpacto[0][0] = fila;
    
    coordImpacto[0][1] = columna;
    
    coordPuntero[0][0] = fila;
    
    coordPuntero[0][1] = columna;
    
  }
  
  
  /**
   * Activamos el paso 1 para hundir el barco.
   * 
   */
  public void setPaso1() {
    
    paso1 = true;
    
  }
  
  
  /**
   * Activamos el paso 2 para hundir el barco. Ponemos paso1 en falso y paso2 en verdadero.
   * 
   */
  public void setPaso2() {
    
    paso1 = false;
    
    paso2 = true;
    
  }
  
  /**
   * Activamos el paso 3 para hundir el barco. Ponemos paso2 en falso.
   * 
   */
  public void setPaso3() {
    
    paso2 = false;
    
  }
  
  
  /**
   * Este método reinicia el tablero que usará la IA para tratar de detectar barcos.<br><br>
   * 
   * De inicio, cada una de las casillas que forman este tablero (desde la posición [1][1] 
   * hasta la posición [10][10]) valen por sí mismas 1 punto, más un punto adicional por 
   * todas aquellas casillas que las rodean (las de las esquinas inclusive) y que no valgan 
   * ni 0 ni -1.<br><br>
   * 
   * De esta forma obtenemos que, a la fuerza, las esquinas deben valer 4 (un punto por si mismas 
   * y otro adicional por cada una de las 3 casillas que hacen contacto con ella 1+1+1+1), las de 
   * los márgenes valen 6 y las centrales 9, quedando un resultado como este:<br><br>
   * 
   * 000000000000<br>
   * 046666666640<br>
   * 069999999960<br>
   * 069999999960<br>
   * 069999999960<br>
   * 069999999960<br>
   * 069999999960<br>
   * 069999999960<br>
   * 069999999960<br>
   * 069999999960<br>
   * 046666666640<br>
   * 000000000000 <br><br>
   * 
   * Recuerde que, tanto los tableros de los jugadores humanos como los de la máquina son de 12x12, 
   * es decir, que los 0 que rodean las casillas con valor inicial > 0 son márgenes inexistentes para 
   * el usuario, pero necesarios para transferir las coordenadas correctamente y con comodidad del 
   * tablero1a [] (principal del jugador humano) al tablero2c [] (el que nos ocupa).<br><br>
   * 
   * Como se puede apreciar, obviando el margen de ceros, el tablero contiene 100 casillas con un valor 
   * inicial equivalente a la lógica descrita anteriormente. ¿Cuál será el procedimiento que usará la IA 
   * para deducir la posible ubicación de los barcos?<br><br>
   * 
   * Una vez iniciada la partida cada vez que la IA impacte en la coordenada [x][y] del tablero1a [] la IA 
   * guardará en las mismas coordenadas del tablero2c [] con un 0 (en caso de haber agua) o un -1 en caso de 
   * casilla con barco y llamará a la función mapeaImpacto(), esta función se encarga de hacer un barrido 
   * en las casillas de alrededor de la casilla cuyas coordenadas hemos pasado (incluyendo las esquinas), 
   * con la finalidad de reducir en un punto su valor actual, salvo si esas casillas valen 0 ó -1.<br><br>
   * 
   * Pongamos un ejemplo de un impacto en el centro del tablero [5][5]:<br><br>
   * 
   * 
   * 000000000000<br>
   * 046666666640<br>
   * 069999999960<br>
   * 069999999960<br>
   * 069988899960<br>
   * 069980899960 <- Vea el cero en la casilla [5][5] como está rodeado de números 8.<br>
   * 069988899960<br>
   * 069999999960<br>
   * 069999999960<br>
   * 069999999960<br>
   * 046666666640<br>
   * 000000000000 <br><br>
   * 
   * Tras varios impactos en el tablero (20, 30 ó 40) el mapeado que tendrá la IA a su disposición 
   * será muy diferente del mapeado inicial (en blanco) de forma tal que los números más altos 
   * representarán los "puntos calientes" a disparar pues, por lógica, representarán las zonas 
   * menos afectadas por impactos cercanos, es decir, refleja los "claros" en el mapa, y es 
   * donde probablemente se pueda ocultar un barco aún no destruído.<br><br>
   * 
   * Ejemplo de mapeado tras 40 impactos aleatorios (y sin barcos):<br><br>
   * 
   * 0340303553<br>
   * 0078600670<br>
   * 2578640065<br>
   * 0076003055<br>
   * 2405050004<br>
   * 0060655302<br>
   * 0578700600<br>
   * 0578865440<br>
   * 3007880002<br>
   * 0330553000<br><br>
   * 
   * 
   * @param tablero2c   Tablero secundario de la IA.
   */
  public void reiniciaTableroIA(int tablero2c[][]) {
    
    //Con este doble bucle vamos a crear el sistema de mapeo de la IA:
    for (int i=0; i<=11; i++) {
      
      for (int j=0; j<=11; j++) {
        
        
        /*
         * Con este doble bucle pretendemos crear un mapa del tablero en el que cada casilla valg un punto por si misma y otro punto
         * por cada una de las casillas que reodeen a esta y que no valgan 0 o -1.
         * 
         * Por tanto, las esquinas valdrán 4, los márgenes del mapa valdrán 6 y el resto de casillas 9, quedando algo así:
         * 
         * 000000000000
         * 046666666640
         * 069999999960
         * 069999999960
         * 069999999960
         * 069999999960
         * 069999999960
         * 069999999960
         * 069999999960
         * 069999999960
         * 046666666640
         * 000000000000
         * 
         * Recuerda que el array imprimible para el jugador es de 10x10, pero realmente trabajamos con arrays de 12x12
         * 
         */
        
        if (i==0 || i>10) {
          
          tablero2c[i][j] = 0;
          
        } else if(j==0 || j>10) {
          
          tablero2c[i][j] = 0;
          
        } else if ((i==1 && j==1) || (i==1 && j==10) || (i==10 && j==1) || (i==10 && j==10)){
          
          tablero2c[i][j] = 4;
          
        } else if (i==1 || i==10 || j==1 || j==10) {
          
          tablero2c[i][j] = 6;
          
        } else {
          
          tablero2c[i][j] = 9;
          
        }
        
      }
      
    }
    
  }
  
  
  /**
   * Terminado el juego en modo 1 jugador, al reiniciarse este método resetea 
   * el estado de la IA al punto de partida.
   */
  public void reiniciaIA() {
    
    setDesactivaDetectadoBarco();
    
    setReiniciaNumDisparos();
    
  }
  
  
  /**
   * Una vez validadas las coordenadas del impacto este método se encarga de 
   * sobreescribir en el array del mapeado (tablero2c []) con un 0 ó -1 mientras
   * no haya previamente un 0 ó -1.<br><br>
   * 
   * Y, además, tomando como referencia esas coordenadas hace un barrido en las casillas 
   * que hay alrededor de esa coordenada (incluyendo las de las esquinas) reduciendo 
   * el valor de estas un punto de su valor actual. (Véase el método reiniciaTablero()).<br><br>
   * 
   * Sin embargo, a la hora de validar las coordenadas se comprueba que no haya 
   * un 0 ó -1, en caso contrario se vuelven a pedir coordenadas, entonces
   * ¿entonces, porqué es necesario este método? Por que cuando hundimos un barco, 
   * dado que no puede haber dos barcos juntos uno al lado del otro, 
   * todo lo que hay alrededor del barco hundido debe ser agua, es decir, 
   * debe valer 0, y para evitar que la IA dispare a una casilla donde por lógica 
   * debe haber un 0 (agua) se llama a este método dentro del método rodeaConAgua().<br><br>
   * 
   * Además, y siguiendo la lógica de puntuación de las casillas, esas casillas que 
   * pasan a valer 0 afectan reduciendo en un punto todas aquellas casillas numeradas 
   * que hagan contacto con las casillas recién convertidas a 0 (por ser agua con una 
   * fiabilidad del 100%).<br><br>
   * 
   * @param tablero2c   Tablero secundario de la IA.
   * @param fila        Número fila (entero).
   * @param columna     Número columna (entero).
   */
  public void mapeaImpacto(int tablero2c[][], int fila, int columna) {
    
    
    if (!(tablero2c[fila][columna]==0 || tablero2c[fila][columna]==-1)) {
      
      tablero2c[fila][columna]=0;
      
    }
    
    
    
    for (int i=fila-1; i<=fila+1; i++) {
      
      for(int j=columna-1; j<=columna+1; j++){
        
        if (!(i<1 || i>10 || j<1 || j>10) && !(tablero2c[i][j]==0 || tablero2c[i][j]==-1)) {
          
          tablero2c[i][j]-=1;
          
        }
        
      }
      
    }
    
  }
  
  
  /**
   * Este método se encarga de rastrear el número con el valor más alto almacenado 
   * en el array del mapeado de la IA, el array tablero2c [].
   * 
   * @param tablero2c   Tablero secundario de la IA.
   * @return            Número (entero) más alto encontrado en el tablero secundario de la IA.
   */
  public int buscaNumeroMasAlto(int tablero2c[][]) {
    
    int numeroMasAlto=0;
    
    
    for (int i=1; i<11; i++) {
      
      for (int j=1; j<11; j++) {        
        
        if (tablero2c[i][j]>numeroMasAlto) {
          
          numeroMasAlto=tablero2c[i][j];
          
        }        
        
      }      
      
    }  
    
    return numeroMasAlto;    
    
  }  
  
  
  /**
   * Cuando este método es llamado inicia un procedimiento por el cuál:<br><br>
   * 
   *  <ol>
   *  <li>Se llama al método buscaNumeroMasAlto() el cuál se encargará de leer todas las posiciones del array 
   *  con el fin de hallar el valor más alto almacenado, terminado el proceso retorna el valor.</li>
   *  <li>Una vez tenemos el número con el valor más alto, hacemos una segunda pasada con la finalidad de
   *  encontrar cuántas veces se repite ése número con el valor más alto y, cada vez que lo encuentre, 
   *  almacenar en el array coordenadas[] las posiciones de cada uno de estos números.</li>
   *  </ol>
   *  
   *  Al final, acabado el proceso, retornará el valor de cuántas veces se ha repetido el número.<br><br>
   *  
   *  El objetivo de este método es que una vez se active, se inicie un procedimiento por el cuál 
   *  se guarden las coordenadas del número con el valor más alto repartido en el mapeado de la IA 
   *  y el valor que retorna se usará como índice de ese array para lanzar, de forma aleatoria, 
   *  un disparo a esas coordenadas.<br><br>
   *  
   *  ¿Para qué es necesario? De la misma forma que un jugador humano ve en su tablero secundario 
   *  los impactos que le ha hecho al enemigo y los claros que quedan en el mapa, donde muy probablemente 
   *  se encuentren los barcos enemigos aún no revelados, el objeto de esto es que la máquina encuentre 
   *  esos mismos claros haciendo uso de la lógica explicada en reiniciaTablero().<br><br>
   * 
   * @param tablero2c       Tablero secundario de la IA.
   * @param coordenadas     Coordenadas almacenadas donde puede ocultar un barco (tras haber realizado el método sonar()) 
   * @return                Número (entero) con la cantidad de veces que ha encontrado el número más alto en el tablero secundario de la IA.
   */
  public int sonar(int tablero2c[][], int coordenadas[][]) {
    
    int totalValorMasAlto = 0;
    
    int numeroMasAlto = buscaNumeroMasAlto(tablero2c);
    
    for (int i=1; i<11; i++) {
      
      for (int j=1; j<11; j++) {        
        
        //Si el valor contenido en tablero2c en las coordenadas [i][j] es igual al valor más alto:
        if (tablero2c[i][j]==numeroMasAlto) {
          
          coordenadas[totalValorMasAlto][0]=i;          //Almacenamos en coordenadas la fila
          
          coordenadas[totalValorMasAlto][1]=j;          //Almacenamos en coordenadas la columna
          
          totalValorMasAlto+=1;                         //Sumamos en +1 el contador de total números más altos
          
        }        
        
      }      
      
    }
    
    return totalValorMasAlto;
    
  }
  
  
  /**
   * Una vez hundido un barco, llamamos a esta función, encargada de rodear de ceros 
   * la posición donde estaba el barco que acaba de hundir. Con ello conseguimos que 
   * la IA no dispare en casillas donde es obvio que hay agua no reveleada en el mapa.<br><br>
   * 
   * Este método necesita hacer uso del método mapeaImpacto().
   * 
   * @param tablero2c     Tablero secundario de la IA.
   * @param barco         Instancia de la clase Barco.
   */
  public void rodeaConAgua(int tablero2c[][], Barco barco) {
    
    int fila = barco.casillaInicial[0][0];
    
    int columna = barco.casillaInicial[0][1];
    
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
        
        for (int k=columna-1; k<=(columna+barco.casillasIniciales); k++) { 
          
          /*
           * Si i no vale menos que 1 ó más que 10 ó k tampoco vale menos que 1 y más que 10
           * Y la posición a mapear de origen no vale cero entonces entra y mapea la coordenada.
           * 
           * Con esto trato de evitar que se salga de los límites del arry y de que no vuelva a 
           * reducir las casillas de alrededor de una coordenada que ya posee un cero y ha sido, 
           * por tanto, mapeada anteriormente, lo que desajustaría los valores según las casillas
           * de alrededor.
           * 
           */
          if (!(i<1 || i>10 || k<1 || k>10) && tablero2c[i][k]!=0) {
            
            mapeaImpacto(tablero2c, i, k);
            
          }
          
        }
        
      }
      
    } else if(!barco.getVerticalidad() && barco.posicionInvertida) {        //Hace la comprobación si el barco está horizontal y se lee a la inversa (de derecha a izquierda):
      
      for (int i=fila-1; i<=fila+1; i++) {
        
        for (int k=columna+1; k>=(columna-barco.casillasIniciales); k--) {
          
          /*
           * Si i no vale menos que 1 ó más que 10 ó k tampoco vale menos que 1 y más que 10
           * Y la posición a mapear de origen no vale cero entonces entra y mapea la coordenada.
           * 
           * Con esto trato de evitar que se salga de los límites del arry y de que no vuelva a 
           * reducir las casillas de alrededor de una coordenada que ya posee un cero y ha sido, 
           * por tanto, mapeada anteriormente, lo que desajustaría los valores según las casillas
           * de alrededor.
           * 
           */
          if (!(i<1 || i>10 || k<1 || k>10) && tablero2c[i][k]!=0) {
            
            mapeaImpacto(tablero2c, i, k);
            
          }
          
        }
        
      } 
      
    } else if (barco.getVerticalidad() && !barco.posicionInvertida) {       //Hace la comprobación si el barco está vertical y NO se lee a la inversa (de arriba a abajo):
      
      for (int k=columna-1; k<=columna+1; k++) {
        
        for (int i=fila-1; i<=(fila+barco.casillasIniciales); i++) {
          
          /*
           * Si i no vale menos que 1 ó más que 10 ó k tampoco vale menos que 1 ó más que 10
           * Y la posición a mapear de origen no vale cero entonces entra y mapea la coordenada.
           * 
           * Con esto trato de evitar que se salga de los límites del arry y de que no vuelva a 
           * reducir las casillas de alrededor de una coordenada que ya posee un cero y ha sido, 
           * por tanto, mapeada anteriormente, lo que desajustaría los valores según las casillas
           * de alrededor.
           * 
           */
          if (!(i<1 || i>10 || k<1 || k>10) && tablero2c[i][k]!=0) {
            
            mapeaImpacto(tablero2c, i, k);
            
          }
          
        }
        
      } 
      
    } else {                                                                //Hace la comprobación si el barco está vertical y se lee a la inversa (de abajo a arriba):
      
      for (int k=columna-1; k<=columna+1; k++) {
        
        for (int i=fila+1; i>=(fila-barco.casillasIniciales); i--) {
          
          /*
           * Si i no vale menos que 1 ó más que 10 ó k tampoco vale menos que 1 y más que 10
           * Y la posición a mapear de origen no vale cero entonces entra y mapea la coordenada.
           * 
           * Con esto trato de evitar que se salga de los límites del arry y de que no vuelva a 
           * reducir las casillas de alrededor de una coordenada que ya posee un cero y ha sido, 
           * por tanto, mapeada anteriormente, lo que desajustaría los valores según las casillas
           * de alrededor.
           * 
           */
          if (!(i<1 || i>10 || k<1 || k>10) && tablero2c[i][k]!=0) {
            
            mapeaImpacto(tablero2c, i, k);
            
          }
          
        }
        
      } 
      
    }    
    
  }
  
  
  
  /**
   * Este es el método que se emplea una vez que el método getHeDetectadoBarco() retorna un verdadero.<br><br>
   * 
   * Dentro de este procedimiento se define  los pasos a seguir para detectar la posición del barco 
   * (horizontal y vertical) y la metodología a seguir para hundirlo de la forma más rápida posible.<br><br>
   * 
   * Para ello sigue 3 pasos claramente diferenciados:
   * 
   * <ol>
   * <li>Paso 1: Este paso consiste en detectar qué casillas hay libres y disponibles para disparar, en los 4 
   * ejes cardinales a partir de las coordenadas del impacto que ha iniciado  el método de hundirBarco(). 
   * Para ello, comprueba que ka casilla inmediatamente de arriba, a la drecha, abajo y a la izquierda ni se salgan 
   * del tablero ni posean una casilla revelada, anteriormente, como agua, en ese caso se descartan poniendo que 
   * esa dirección es inválida (false).</li>
   * <li>Paso 2: En este punto, una vez se han comprobado las 4 direcciones, se incia una tirada aleatoria para impactar 
   * de entre esas 4 direcciones, las que sean válidas, a una en concreto. Si no se detecta casilla de barco se invalida 
   * esa dirección para no repetir y, si se encontrase un barco, al deducir si está en posición vertical u horizontal 
   * se invalidarían las dos coordenadas del eje opuesto. Por tanto, el objetivo de este paso es encontrar la segunda 
   * casilla de barco con el objetivo de concluir en qué eje se encuentra y, a aprtir de ahí, continuar disparando.
   * En este paso, habremos destruído 2 casillas de barco y, dado que el submarino posee 2 casillas debemos realizar la 
   * comprobación de si hay algún barco que acabamos de hundir. De lo contrario pasamos al paso 3.</li>
   * <li>Paso 3: Ahora sabemos la ubicación del barco y su posición en uno de los dos ejes, solo nos queda hundirlo.
   * Para esto solo debemos continuar disparando en la dirección exitosa del segundo disparo, pero antes debemos comprobar 
   * si en la casilla que hay justo a continuación hay agua previamente explorada, o si nos salimos del mapa, en ese caso 
   * invalidamos la dirección en la que vamos, devolvemos los valores de las coordenadas iniciales del primer impacto en 
   * el barco, e invertimos la dirección en el mismo eje, es decir, que si estábamos yendo hacia la izquierda ahora dispararemos 
   * hacia la derecha.</li>
   * </ol>
   * 
   * Una vez hundido el barco se resetea el sistema de detección, direccionamiento y hundimiento de barcos, es decir, reseteamos 
   * las variables que impiden que entremos de nuevo en este método hasta el descubrimiento de otro barco.<br><br>
   * 
   * 
   * @param tablero2c        Tablero secundario de la IA.
   * @param tablero1a        Tablero principal del jugador 1.
   * @param portaavionesJ1   Instancia de la clase Barco, "portaaviones" del jugador 1.
   * @param acorazadoJ1      Instancia de la clase Barco, "acorazado" del jugador 1.
   * @param destructorJ1     Instancia de la clase Barco, "destructor" del jugador 1.
   * @param cruceroJ1        Instancia de la clase Barco, "crucero" del jugador 1.
   * @param fragataJ1        Instancia de la clase Barco, "fragata" del jugador 1.
   * @param submarinoJ1      Instancia de la clase Barco, "submarino" del jugador 1.
   */
  public void hundirBarco(int tablero2c[][], String tablero1a [][], Barco portaavionesJ1, Barco acorazadoJ1, Barco destructorJ1, Barco cruceroJ1, Barco fragataJ1, Barco submarinoJ1) {
    
    
    int filaImpacto = coordImpacto[0][0];
    
    int columnaImpacto = coordImpacto[0][1];
    
    //coordPuntero[0][0] es la posición de fila del puntero
    
    //coordPuntero[0][1] es la posición de columna del puntero
    
    int indiceDireccion;
    
    
    
    //#########   Paso 1   #########\\
    
    if (paso1) {
      
      /*
       * Comprobamos que, desde la coordenada del impacto, la casilla de arriba, derecha, abajo e izquierdan 
       * puedan ser disparadas, en caso contrario porque ya haya agua o se acabe el tablero damos esa dirección 
       * como falsa.
       */
      for (int i=0; i<4; i++) {
        
        switch (i) {
          
          case 0:   //Arriba
            
            if ((filaImpacto-1)<1 || tablero2c[filaImpacto-1][columnaImpacto]==0) {
              
              compruebaDireccion[0] = false;
              
            }
            break;
            
          case 1:   //Derecha
            
            if ((columnaImpacto+1)>10 || tablero2c[filaImpacto][columnaImpacto+1]==0) {
              
              compruebaDireccion[1] = false;
              
            }
            break;
            
          case 2:   //Abajo
            
            if ((filaImpacto+1)>10 || tablero2c[filaImpacto+1][columnaImpacto]==0) {
              
              compruebaDireccion[2] = false;
              
            }
            break;
            
          default:   //Izquierda
            
            if ((columnaImpacto-1)<1 || tablero2c[filaImpacto][columnaImpacto-1]==0) {
              
              compruebaDireccion[3] = false;
              
          }
            
        }
        
      }
      
      //Acabada la comprobación del paso 1, saltamos al paso 2.
      setPaso2();
      
    }
    
    
    
    //#########   Paso 2   #########\\
    
    if (paso2) {
      
      /*
       * Ahora que sabemos en cuántas de las 4 direcciones posibles podemos ir, generamos aleatoriamente 
       * en qué dirección quiere probar suerte la IA.
       */
      do {
        
        indiceDireccion = (int)(Math.random()*4);
        
      } while(!compruebaDireccion[indiceDireccion]);
      
      
      /*
       * Una vez hemos obtenido una dirección posible a la que desea disparar la máquina se sigue el siguiente procedimiento:
       * 
       *  ->Si el disparo impacta en agua:
       *  
       *      -Invalidamos esa dirección para no volver a repetirla en el siguiente disparo.
       *      
       *      -Sobreescribimos en el tablero del jugador humano la ubicación del disparo.
       *      
       *      -Sobreescribimos en el tablero de la IA la ubicación del disparo. 
       *      
       *      -Y mapeamos el impacto para alterar el mapa de la IA (tablero2c[][]).
       * 
       * 
       * ->Si el disparo impacta en un barco:
       *  
       *      -Invalidamos las direcciones del eje opuesto, es decir, si impacta en el eje vertical 
       *          ponemos en falso las del eje horizontal y viceversa.
       *      
       *      -Sobreescribimos en el tablero del jugador humano la ubicación del disparo.
       *      
       *      -Sobreescribimos en el tablero de la IA la ubicación del disparo. 
       *      
       *      -Almacenamos las nuevas coordenadas, las actuales del disparo, en las coordPuntero para tomarlas como referencia para 
       *          el siguiente disparo.
       *      
       *      -Mapeamos el impacto para alterar el mapa de la IA (tablero2c[][]).
       *      
       *      -Activamos el booleano de "sigue disparando", para que la IA sepa que el siguiente disparo debe ir en esa dirección
       *          siendo 0: arriba || 1: derecha || 2: abajo || 3: izquierda.
       *          
       *      -Comprobamos si hemos hundido algún barco (en este paso el único que podemos hundir es el submarino).
       *      
       *      -Y activamos el paso 3.
       * 
       */
      switch (indiceDireccion) {
      
        case 0:   //Arriba
          
          if (tablero1a[coordPuntero[0][0]-1][coordPuntero[0][1]] == "_|") {
            
            compruebaDireccion[0] = false;
            
            tablero1a[coordPuntero[0][0]-1][coordPuntero[0][1]] = "O|";
            
            tablero2c[coordPuntero[0][0]-1][coordPuntero[0][1]] = 0;
            
            mensajeCoordenadasIA(coordPuntero[0][0]-1, coordPuntero[0][1]);
            
            System.out.print("\n\nAGUA");
            
            mapeaImpacto(tablero2c, (coordPuntero[0][0]-1), coordPuntero[0][1]);     
            
            this.setNumDisparos();
            
          } else {
            
            compruebaDireccion[1] = false;
            
            compruebaDireccion[3] = false;
            
            tablero1a[coordPuntero[0][0]-1][coordPuntero[0][1]] = "X|";
            
            tablero2c[coordPuntero[0][0]-1][coordPuntero[0][1]] = -1;
            
            mensajeCoordenadasIA(coordPuntero[0][0]-1, coordPuntero[0][1]);
            
            System.out.print("\n\nTOCADO");
            
            coordPuntero[0][0]-=1;                                         //Sobreescribimos la nueva coordenada en el puntero, como referencia para el siguiente disparo.
            
            this.setNumDisparos();
            
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]);            
            
            sigueDisparando[0] = true;
            
            //Añado aquí una comprobación porque el submarino tiene 2 casillas y este sería el segundo impacto:
            compruebaBarcoImpactadoIA(portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1, coordPuntero[0][0], coordPuntero[0][1], tablero2c);
            
            setPaso3();
            
          }
          
          break;
          
        case 1:   //Derecha
            
          if (tablero1a[coordPuntero[0][0]][coordPuntero[0][1]+1] == "_|") {
            
            compruebaDireccion[1] = false;
            
            tablero1a[coordPuntero[0][0]][coordPuntero[0][1]+1] = "O|";
            
            tablero2c[coordPuntero[0][0]][coordPuntero[0][1]+1] = 0;
            
            mensajeCoordenadasIA(coordPuntero[0][0], coordPuntero[0][1]+1);
            
            System.out.print("\n\nAGUA");
            
            this.setNumDisparos();
            
            mapeaImpacto(tablero2c, coordPuntero[0][0], (coordPuntero[0][1]+1));            
            
          } else {
            
            compruebaDireccion[0] = false;
            
            compruebaDireccion[2] = false;
            
            tablero1a[coordPuntero[0][0]][coordPuntero[0][1]+1] = "X|";
            
            tablero2c[coordPuntero[0][0]][coordPuntero[0][1]+1] = -1;
            
            mensajeCoordenadasIA(coordPuntero[0][0], coordPuntero[0][1]+1);
            
            System.out.print("\n\nTOCADO");
            
            coordPuntero[0][1]+=1;                                         //Sobreescribimos la nueva coordenada en el puntero, como referencia para el siguiente disparo.
            
            this.setNumDisparos();
            
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]); 
            
            sigueDisparando[1] = true;
            
            //Añado aquí una comprobación porque el submarino tiene 2 casillas y este sería el segundo impacto:
            compruebaBarcoImpactadoIA(portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1, coordPuntero[0][0], coordPuntero[0][1], tablero2c);
            
            setPaso3();
            
          }
          
          break;
          
        case 2:   //Abajo
          
          if (tablero1a[coordPuntero[0][0]+1][coordPuntero[0][1]] == "_|") {
            
            compruebaDireccion[2] = false;
            
            tablero1a[coordPuntero[0][0]+1][coordPuntero[0][1]] = "O|";
            
            tablero2c[coordPuntero[0][0]+1][coordPuntero[0][1]] = 0;
            
            mensajeCoordenadasIA(coordPuntero[0][0]+1, coordPuntero[0][1]);
            
            System.out.print("\n\nAGUA");
            
            this.setNumDisparos();
            
            mapeaImpacto(tablero2c, (coordPuntero[0][0]+1), coordPuntero[0][1]);            
            
          } else {
            
            compruebaDireccion[1] = false;
            
            compruebaDireccion[3] = false;
            
            tablero1a[coordPuntero[0][0]+1][coordPuntero[0][1]] = "X|";
            
            tablero2c[coordPuntero[0][0]+1][coordPuntero[0][1]] = -1;
            
            mensajeCoordenadasIA(coordPuntero[0][0]+1, coordPuntero[0][1]);
            
            System.out.print("\n\nTOCADO");
            
            coordPuntero[0][0]+=1;                                         //Sobreescribimos la nueva coordenada en el puntero, como referencia para el siguiente disparo.
            
            this.setNumDisparos();
            
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]);            
            
            sigueDisparando[2] = true;
            
            //Añado aquí una comprobación porque el submarino tiene 2 casillas y este sería el segundo impacto:
            compruebaBarcoImpactadoIA(portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1, coordPuntero[0][0], coordPuntero[0][1], tablero2c);
            
            setPaso3();
            
          }

          break;
          
        default:   //Izquierda
          
          if (tablero1a[coordPuntero[0][0]][coordPuntero[0][1]-1] == "_|") {
            
            compruebaDireccion[3] = false;
            
            tablero1a[coordPuntero[0][0]][coordPuntero[0][1]-1] = "O|";
            
            tablero2c[coordPuntero[0][0]][coordPuntero[0][1]-1] = 0;
            
            mensajeCoordenadasIA(coordPuntero[0][0], coordPuntero[0][1]-1);
            
            System.out.print("\n\nAGUA");
            
            this.setNumDisparos();
            
            mapeaImpacto(tablero2c, coordPuntero[0][0], (coordPuntero[0][1]-1));            
            
          } else {
            
            compruebaDireccion[0] = false;
            
            compruebaDireccion[2] = false;
            
            tablero1a[coordPuntero[0][0]][coordPuntero[0][1]-1] = "X|";
            
            tablero2c[coordPuntero[0][0]][coordPuntero[0][1]-1] = -1;
            
            mensajeCoordenadasIA(coordPuntero[0][0], coordPuntero[0][1]-1);
            
            System.out.print("\n\nTOCADO");
            
            coordPuntero[0][1]-=1;                                         //Sobreescribimos la nueva coordenada en el puntero, como referencia para el siguiente disparo.
            
            this.setNumDisparos();
            
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]); 
            
            sigueDisparando[3] = true;
            
            //Añado aquí una comprobación porque el submarino tiene 2 casillas y este sería el segundo impacto:
            compruebaBarcoImpactadoIA(portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1, coordPuntero[0][0], coordPuntero[0][1], tablero2c);
            
            setPaso3();
            
          }
          
      }
      
      
      
    //#########   Paso 3   #########\\
      
    } else {
      
      /*
       * Aquí comienza el tercer y último paso que sigue la IA para hundir los barcos del jugador.
       * 
       * Continúa disparando en la dirección que le hemos pasado con el booleano "sigueDisparando[]" pero
       * antes de hacer sl siguiente disparo el juego debe tener en cuenta tres posibles situaciones:
       * 
       *  ->Situación 1:
       *        -La siguiente casilla en esa dirección es agua (está explorada con anterioridad) o se sale
       *        del tablero. En ese caso invierte su dirección, iguala la coordPuntero a la coordImpacto 
       *        y lanza el siguiente disparo en el mismo eje pero en sentido inverso.
       * 
       * 
       *  ->Situación 2:
       *        -Lanza el disparo y se encuentra con agua, y como en la situación 1: invierte su dirección. 
       *        Iguala la coordPuntero a la coordImpacto y lanzará el siguiente disparo en el mismo eje pero 
       *        en sentido inverso.
       * 
       * 
       *  ->Situación 3:
       *        -Lanza el disparo e impacta de nuevo en el barco, en ese punto debemos comprobar si lo hemos 
       *        hundido, en caso contrario seguiremos con este patrón hasta acabar hundiéndolo. Cuando esto
       *        ocurra reiniciaremos este sistema de detección y destrucción de barcos. 
       * 
       * 
       * 
       */
      
      //Seguimos hacia arriba:
      if (sigueDisparando[0]) {
        
        //Situación 1:
        if (tablero2c[coordPuntero[0][0]-1][coordPuntero[0][1]] == 0 || (coordPuntero[0][0]-1)<1) {
          
          //Cambiamos a abajo
          sigueDisparando[0] = false;     //Invalidamos seguir esta dirección
          
          sigueDisparando[2] = true;      //Activamos la dirección inversa
          
          //Reseteamos coordenada puntero
          coordPuntero[0][0] = filaImpacto;             //Igualamos coordenadas de puntero con las coordenadas originales del disparo
          
          
          //Disparamos en la dirección nueva (inversa a la anterior):          
          if (tablero1a[coordPuntero[0][0]+1][coordPuntero[0][1]] == "B|") {
            
            tablero1a[coordPuntero[0][0]+1][coordPuntero[0][1]] = "X|";
            
            tablero2c[coordPuntero[0][0]+1][coordPuntero[0][1]] = -1;
            
            mensajeCoordenadasIA(coordPuntero[0][0]+1, coordPuntero[0][1]);
            
            System.out.print("\n\nTOCADO");
            
            coordPuntero[0][0]+=1;                                         //Sobreescribimos la nueva coordenada en el puntero, como referencia para el siguiente disparo.
            
            this.setNumDisparos();
            
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]);
            
            //Comprobamos si el barco se ha hundido:
            compruebaBarcoImpactadoIA(portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1, coordPuntero[0][0], coordPuntero[0][1], tablero2c);
            
          }          
          
          //Situaciones 2 y 3:
        } else {
          
          //Situación 2 (encontramos agua):
          if (tablero1a[coordPuntero[0][0]-1][coordPuntero[0][1]] == "_|") {
            
            //Modificamos tableros:
            tablero1a[coordPuntero[0][0]-1][coordPuntero[0][1]] = "O|";
            
            tablero2c[coordPuntero[0][0]-1][coordPuntero[0][1]] = 0;   
            
            mensajeCoordenadasIA(coordPuntero[0][0]-1, coordPuntero[0][1]);
            
            System.out.print("\n\nAGUA");
            
            this.setNumDisparos();
            
            //Mapeamos el impacto:
            mapeaImpacto(tablero2c, coordPuntero[0][0]-1, coordPuntero[0][1]);            
            
            //Invertimos la dirección del disparo y reseteamos al valor original del disparo las coordenadas del puntero
            sigueDisparando[0] = false;     //Invalidamos seguir esta dirección
            
            sigueDisparando[2] = true;      //Activamos la dirección inversa
            
            coordPuntero[0][0] = filaImpacto;             //Igualamos coordenadas de puntero con las coordenadas originales del disparo
            
            
            //Situación 3 (impacto en barco):
          } else {
            
          //Modificamos tableros:
            tablero1a[coordPuntero[0][0]-1][coordPuntero[0][1]] = "X|";
            
            tablero2c[coordPuntero[0][0]-1][coordPuntero[0][1]] = -1;
            
            mensajeCoordenadasIA(coordPuntero[0][0]-1, coordPuntero[0][1]);
            
            System.out.print("\n\nTOCADO");
            
            //Actualizamos coordenada del puntero
            coordPuntero[0][0] -= 1;               //Actualizamos el valor de las coordenadas del puntero
            
            this.setNumDisparos();
            
            //Mapeamos el impacto:
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]);
            
            
            //Comprobamos si el barco se ha hundido:
            compruebaBarcoImpactadoIA(portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1, coordPuntero[0][0], coordPuntero[0][1], tablero2c);
            
          }          
          
        }
        
        //Seguimos hacia la derecha:
      } else if (sigueDisparando[1]) {
        
        //Situación 1:
        if (tablero2c[coordPuntero[0][0]][coordPuntero[0][1]+1] == 0 || (coordPuntero[0][1]+1)>10) {
          
          //Cambiamos a la izquierda:
          sigueDisparando[1] = false;     //Invalidamos seguir esta dirección
          
          sigueDisparando[3] = true;      //Activamos la dirección inversa
          
          //Reseteamos coordenada puntero
          coordPuntero[0][1] = columnaImpacto;             //Igualamos coordenadas de puntero con las coordenadas originales del disparo
          
          
          //Disparamos en la dirección nueva (inversa a la anterior):          
          if (tablero1a[coordPuntero[0][0]][coordPuntero[0][1]-1] == "B|") {
            
            tablero1a[coordPuntero[0][0]][coordPuntero[0][1]-1] = "X|";
            
            tablero2c[coordPuntero[0][0]][coordPuntero[0][1]-1] = -1;
            
            mensajeCoordenadasIA(coordPuntero[0][0], coordPuntero[0][1]-1);
            
            System.out.print("\n\nTOCADO");
            
            coordPuntero[0][1]-=1;                                         //Sobreescribimos la nueva coordenada en el puntero, como referencia para el siguiente disparo.
            
            this.setNumDisparos();
            
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]);
            
            //Comprobamos si el barco se ha hundido:
            compruebaBarcoImpactadoIA(portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1, coordPuntero[0][0], coordPuntero[0][1], tablero2c);
            
          }          
          
          //Situaciones 2 y 3:
        } else {
          
          //Situación 2 (encontramos agua):
          if (tablero1a[coordPuntero[0][0]][coordPuntero[0][1]+1] == "_|") {
            
            //Modificamos tableros:
            tablero1a[coordPuntero[0][0]][coordPuntero[0][1]+1] = "O|";
            
            tablero2c[coordPuntero[0][0]][coordPuntero[0][1]+1] = 0;
            
            mensajeCoordenadasIA(coordPuntero[0][0], coordPuntero[0][1]+1);
            
            System.out.print("\n\nAGUA");
            
            this.setNumDisparos();
            
            //Mapeamos el impacto:
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]+1);
            
            //Invertimos la dirección del disparo y reseteamos al valor original del disparo las coordenadas del puntero
            sigueDisparando[1] = false;     //Invalidamos seguir esta dirección
            
            sigueDisparando[3] = true;      //Activamos la dirección inversa
            
            coordPuntero[0][1] = columnaImpacto;             //Igualamos coordenadas de puntero con las coordenadas originales del disparo
            
            
            //Situación 3 (impacto en barco):
          } else {
            
          //Modificamos tableros:
            tablero1a[coordPuntero[0][0]][coordPuntero[0][1]+1] = "X|";
            
            tablero2c[coordPuntero[0][0]][coordPuntero[0][1]+1] = -1;
            
            mensajeCoordenadasIA(coordPuntero[0][0], coordPuntero[0][1]+1);
            
            System.out.print("\n\nTOCADO");
            
            //Actualizamos coordenada del puntero
            coordPuntero[0][1] += 1;               //Igualamos coordenadas de puntero con las coordenadas originales del disparo
            
            this.setNumDisparos();
            
            //Mapeamos el impacto:
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]);
            
            
            //Comprobamos si el barco se ha hundido:
            compruebaBarcoImpactadoIA(portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1, coordPuntero[0][0], coordPuntero[0][1], tablero2c);
            
          }
          
        }
        
        //Seguimos hacia abajo:
      } else if (sigueDisparando[2]) {
        
      //Situación 1:
        if (tablero2c[coordPuntero[0][0]+1][coordPuntero[0][1]] == 0 || (coordPuntero[0][0]+1)>10) {
          
          //Cambiamos a arriba
          sigueDisparando[2] = false;     //Invalidamos seguir esta dirección
          
          sigueDisparando[0] = true;      //Activamos la dirección inversa
          
          //Reseteamos coordenada puntero
          coordPuntero[0][0] = filaImpacto;             //Igualamos coordenadas de puntero con las coordenadas originales del disparo
          
          
          //Disparamos en la dirección nueva (inversa a la anterior):          
          if (tablero1a[coordPuntero[0][0]-1][coordPuntero[0][1]] == "B|") {
            
            tablero1a[coordPuntero[0][0]-1][coordPuntero[0][1]] = "X|";
            
            tablero2c[coordPuntero[0][0]-1][coordPuntero[0][1]] = -1;
            
            mensajeCoordenadasIA(coordPuntero[0][0]-1, coordPuntero[0][1]);
            
            System.out.print("\n\nTOCADO");
            
            coordPuntero[0][0]-=1;                                         //Sobreescribimos la nueva coordenada en el puntero, como referencia para el siguiente disparo.
            
            this.setNumDisparos();
            
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]);
            
            //Comprobamos si el barco se ha hundido:
            compruebaBarcoImpactadoIA(portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1, coordPuntero[0][0], coordPuntero[0][1], tablero2c);
            
          }          
          
          //Situaciones 2 y 3:
        } else {
          
          //Situación 2 (encontramos agua):
          if (tablero1a[coordPuntero[0][0]+1][coordPuntero[0][1]] == "_|") {
            
            //Modificamos tableros:
            tablero1a[coordPuntero[0][0]+1][coordPuntero[0][1]] = "O|";
            
            tablero2c[coordPuntero[0][0]+1][coordPuntero[0][1]] = 0;   
            
            mensajeCoordenadasIA(coordPuntero[0][0]+1, coordPuntero[0][1]);
            
            System.out.print("\n\nAGUA");
            
            this.setNumDisparos();
            
            //Mapeamos el impacto:
            mapeaImpacto(tablero2c, coordPuntero[0][0]+1, coordPuntero[0][1]);            
            
            //Invertimos la dirección del disparo y reseteamos al valor original del disparo las coordenadas del puntero
            sigueDisparando[2] = false;     //Invalidamos seguir esta dirección
            
            sigueDisparando[0] = true;      //Activamos la dirección inversa
            
            coordPuntero[0][0] = filaImpacto;             //Igualamos coordenadas de puntero con las coordenadas originales del disparo
            
            
            //Situación 3 (impacto en barco):
          } else {
            
          //Modificamos tableros:
            tablero1a[coordPuntero[0][0]+1][coordPuntero[0][1]] = "X|";
            
            tablero2c[coordPuntero[0][0]+1][coordPuntero[0][1]] = -1;
            
            mensajeCoordenadasIA(coordPuntero[0][0]+1, coordPuntero[0][1]);
            
            System.out.print("\n\nTOCADO");
            
            //Actualizamos coordenada del puntero
            coordPuntero[0][0] += 1;               //Actualizamos el valor de las coordenadas del puntero
            
            this.setNumDisparos();
            
            //Mapeamos el impacto:
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]);
            
            
            //Comprobamos si el barco se ha hundido:
            compruebaBarcoImpactadoIA(portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1, coordPuntero[0][0], coordPuntero[0][1], tablero2c);
            
          }          
          
        }
        
        //Seguimos hacia la izquierda
      } else {
        
        //Situación 1:
        if (tablero2c[coordPuntero[0][0]][coordPuntero[0][1]-1] == 0 || (coordPuntero[0][1]-1)<1) {
          
          //Cambiamos a la derecha:
          sigueDisparando[3] = false;     //Invalidamos seguir esta dirección
          
          sigueDisparando[1] = true;      //Activamos la dirección inversa
          
          //Reseteamos coordenada puntero
          coordPuntero[0][1] = columnaImpacto;             //Igualamos coordenadas de puntero con las coordenadas originales del disparo
          
          
          //Disparamos en la dirección nueva (inversa a la anterior):          
          if (tablero1a[coordPuntero[0][0]][coordPuntero[0][1]+1] == "B|") {
            
            tablero1a[coordPuntero[0][0]][coordPuntero[0][1]+1] = "X|";
            
            tablero2c[coordPuntero[0][0]][coordPuntero[0][1]+1] = -1;
            
            mensajeCoordenadasIA(coordPuntero[0][0], coordPuntero[0][1]+1);
            
            System.out.print("\n\nTOCADO");
            
            coordPuntero[0][1]+=1;                                         //Sobreescribimos la nueva coordenada en el puntero, como referencia para el siguiente disparo.
            
            this.setNumDisparos();
            
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]);
            
            //Comprobamos si el barco se ha hundido:
            compruebaBarcoImpactadoIA(portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1, coordPuntero[0][0], coordPuntero[0][1], tablero2c);
            
          }          
          
          //Situaciones 2 y 3:
        } else {
          
          //Situación 2 (encontramos agua):
          if (tablero1a[coordPuntero[0][0]][coordPuntero[0][1]-1] == "_|") {
            
            //Modificamos tableros:
            tablero1a[coordPuntero[0][0]][coordPuntero[0][1]-1] = "O|";
            
            tablero2c[coordPuntero[0][0]][coordPuntero[0][1]-1] = 0;
            
            mensajeCoordenadasIA(coordPuntero[0][0], coordPuntero[0][1]-1);
            
            System.out.print("\n\nAGUA");
            
            this.setNumDisparos();
            
            //Mapeamos el impacto:
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]-1);
            
            //Invertimos la dirección del disparo y reseteamos al valor original del disparo las coordenadas del puntero
            sigueDisparando[3] = false;     //Invalidamos seguir esta dirección
            
            sigueDisparando[1] = true;      //Activamos la dirección inversa
            
            coordPuntero[0][1] = columnaImpacto;             //Igualamos coordenadas de puntero con las coordenadas originales del disparo
            
            
            //Situación 3 (impacto en barco):
          } else {
            
            //Modificamos tableros:
            tablero1a[coordPuntero[0][0]][coordPuntero[0][1]-1] = "X|";
            
            tablero2c[coordPuntero[0][0]][coordPuntero[0][1]-1] = -1;
            
            mensajeCoordenadasIA(coordPuntero[0][0], coordPuntero[0][1]-1);
            
            System.out.print("\n\nTOCADO");
            
            //Actualizamos coordenada del puntero
            coordPuntero[0][1] -= 1;               //Igualamos coordenadas de puntero con las coordenadas originales del disparo
            
            this.setNumDisparos();
            
            //Mapeamos el impacto:
            mapeaImpacto(tablero2c, coordPuntero[0][0], coordPuntero[0][1]);
            
            
            //Comprobamos si el barco se ha hundido:
            compruebaBarcoImpactadoIA(portaavionesJ1, acorazadoJ1, destructorJ1, cruceroJ1, fragataJ1, submarinoJ1, coordPuntero[0][0], coordPuntero[0][1], tablero2c);
            
          }
          
        }
        
      }
      
    }
    
  }
  
  
  /**
   * Método que emplea la IA del juego para determinar cuál de los barcos ha impactado
   * y si se ha hundido (para reiniciar el sistema de combate).
   * 
   * @param portaavionesJ1   Instancia de la clase Barco, "portaaviones" del jugador 1.
   * @param acorazadoJ1      Instancia de la clase Barco, "acorazado" del jugador 1.
   * @param destructorJ1     Instancia de la clase Barco, "destructor" del jugador 1.
   * @param cruceroJ1        Instancia de la clase Barco, "crucero" del jugador 1.
   * @param fragataJ1        Instancia de la clase Barco, "fragata" del jugador 1.
   * @param submarinoJ1      Instancia de la clase Barco, "submarino" del jugador 1.
   * @param fila             Número (entero) de fila.
   * @param columna          Número (entero) de columna.
   * @param tablero2c        Tablero secundario de la IA.
   */
  public void compruebaBarcoImpactadoIA(Barco portaavionesJ1, Barco acorazadoJ1, Barco destructorJ1,
      Barco cruceroJ1, Barco fragataJ1, Barco submarinoJ1, int fila, int columna, int tablero2c[][]) {
    
    if (portaavionesJ1.compruebaImpacto(fila, columna)) {
      
      portaavionesJ1.tocado();
      
      if (portaavionesJ1.hundidoBarco && !portaavionesJ1.haSalidoMensajeHundido) {      //Si lo hundimos:
        
        System.out.print("\n\nSkynet ha hundido tu " + portaavionesJ1.nombreBarco);      //Imprimos el mensaje de que ha sido hundido.
        
        portaavionesJ1.setHaSalidoMensajeHundido();                                     //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
        
        setDesactivaDetectadoBarco();                                                   //Desactiva y reinicia el proceso de detección, direccionamiento y hundimiento de barcos.
        
        rodeaConAgua(tablero2c, portaavionesJ1);
        
      }
      
    } else if (acorazadoJ1.compruebaImpacto(fila, columna)) {
      
      acorazadoJ1.tocado();
      
      if (acorazadoJ1.hundidoBarco && !acorazadoJ1.haSalidoMensajeHundido) {            //Si lo hundimos:
        
        System.out.print("\n\nSkynet ha hundido tu " + acorazadoJ1.nombreBarco);         //Imprimos el mensaje de que ha sido hundido.
        
        acorazadoJ1.setHaSalidoMensajeHundido();                                        //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
        
        setDesactivaDetectadoBarco();                                                   //Desactiva y reinicia el proceso de detección, direccionamiento y hundimiento de barcos.
        
        rodeaConAgua(tablero2c, acorazadoJ1);
        
      }
      
    } else if (destructorJ1.compruebaImpacto(fila, columna)) {
      
      destructorJ1.tocado();
      
      if (destructorJ1.hundidoBarco && !destructorJ1.haSalidoMensajeHundido) {          //Si lo hundimos:
        
        System.out.print("\n\nSkynet ha hundido tu " + destructorJ1.nombreBarco);        //Imprimos el mensaje de que ha sido hundido.
        
        destructorJ1.setHaSalidoMensajeHundido();                                       //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
        
        setDesactivaDetectadoBarco();                                                   //Desactiva y reinicia el proceso de detección, direccionamiento y hundimiento de barcos.
        
        rodeaConAgua(tablero2c, destructorJ1);
        
      }
      
    } else if (cruceroJ1.compruebaImpacto(fila, columna)) {
      
      cruceroJ1.tocado();
      
      if (cruceroJ1.hundidoBarco && !cruceroJ1.haSalidoMensajeHundido) {                //Si lo hundimos:
        
        System.out.print("\n\nSkynet ha hundido tu " + cruceroJ1.nombreBarco);           //Imprimos el mensaje de que ha sido hundido.
        
        cruceroJ1.setHaSalidoMensajeHundido();                                          //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
        
        setDesactivaDetectadoBarco();                                                   //Desactiva y reinicia el proceso de detección, direccionamiento y hundimiento de barcos.
        
        rodeaConAgua(tablero2c, cruceroJ1);
        
      }
      
    } else if (fragataJ1.compruebaImpacto(fila, columna)) {
      
      fragataJ1.tocado();
      
      if (fragataJ1.hundidoBarco && !fragataJ1.haSalidoMensajeHundido) {                //Si lo hundimos:
        
        System.out.print("\n\nSkynet ha hundido tu " + fragataJ1.nombreBarco);           //Imprimos el mensaje de que ha sido hundido.
        
        fragataJ1.setHaSalidoMensajeHundido();                                          //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
        
        setDesactivaDetectadoBarco();                                                   //Desactiva y reinicia el proceso de detección, direccionamiento y hundimiento de barcos.
        
        rodeaConAgua(tablero2c, fragataJ1);
        
      }
      
    } else if (submarinoJ1.compruebaImpacto(fila, columna)) {
      
      submarinoJ1.tocado();
      
      if (submarinoJ1.hundidoBarco && !submarinoJ1.haSalidoMensajeHundido) {            //Si lo hundimos:
        
        System.out.print("\n\nSkynet ha hundido tu " + submarinoJ1.nombreBarco);         //Imprimos el mensaje de que ha sido hundido.
        
        submarinoJ1.setHaSalidoMensajeHundido();                                        //Y activamos el booleano de que este mensaje ya ha salido para que no se repita.
        
        setDesactivaDetectadoBarco();                                                   //Desactiva y reinicia el proceso de detección, direccionamiento y hundimiento de barcos.
        
        rodeaConAgua(tablero2c, submarinoJ1);
        
      }
      
    }
    
  }
  
  
  /**
   * Este método comprueba si las coordenadas que pasamos como posición inicial del barco son válidas para insertar el barco en el tablero.
   * 
   * En caso de que no sean aptas volverá a pedir las coordenadas.
   * 
   * @param barco     Instancia de la clase Barco.
   * @param tablero   Tablero donde se desea comprobar la posición.
   * @param fila      Número (entero) de fila.
   * @param columna   Número (entero) de columna.
   * @return          Verdadero o falso.
   */
  public boolean compruebaPosicionBarcoIA(Barco barco, String [][] tablero, int fila, int columna) {
    
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
     * Dado que una vez se asigna la posición invertida se queda fija en ella, aunque luego las coordenadas sean incorrectas,
     * para evitar que se salga del array por el lado izquierdo o arriba (causando un error de índice) en caso de volver a meter
     * las coordenadas debemos comprobar si no se desborda arriba o a la izquierda y, de ocurrir, debemos revertir la inversión.
     */
    if (barco.getPosicionInvertida() && !barco.getVerticalidad() && columna-barco.getCasillas()-1<1) {
      
      barco.setEliminaPosicionInvertida();
      
    } else if (barco.getPosicionInvertida() && barco.getVerticalidad() && fila-barco.getCasillas()-1<1) {
      
      barco.setEliminaPosicionInvertida();
      
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
        
        for (int k=columna-1; k<=(columna+barco.casillasIniciales); k++) {      
          
          comprueba += tablero[i][k];
          
        }
        
      }
      
    } else if(!barco.getVerticalidad() && barco.posicionInvertida) {        //Hace la comprobación si el barco está horizontal y se lee a la inversa (de derecha a izquierda):
      
      for (int i=fila-1; i<=fila+1; i++) {
        
        for (int k=columna+1; k>=(columna-barco.casillasIniciales); k--) {
          
          comprueba += tablero[i][k];
          
        }
        
      } 
      
    } else if (barco.getVerticalidad() && !barco.posicionInvertida) {       //Hace la comprobación si el barco está vertical y NO se lee a la inversa (de arriba a abajo):
      
      for (int k=columna-1; k<=columna+1; k++) {
        
        for (int i=fila-1; i<=(fila+barco.casillasIniciales); i++) {
          
          comprueba += tablero[i][k];
          
        }
        
      } 
      
    } else {                                                                //Hace la comprobación si el barco está vertical y se lee a la inversa (de abajo a arriba):
      
      for (int k=columna-1; k<=columna+1; k++) {
        
        for (int i=fila+1; i>=(fila-barco.casillasIniciales); i--) {
          
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
    
    return salida;
    
  }
  
  
  /**
   * Cuando la IA dispara llama a esta función para imprimir por pantalla 
   * las coordenadas a las que va dirigido el disparo.
   * 
   * @param fila      Número (entero) de fila.
   * @param columna   Número (entero) de columna.
   */
  public void mensajeCoordenadasIA(int fila, int columna) {
    
    String salidaFila;
    
    switch (fila) {
    
      case 1:
        salidaFila = "A";
        break;
        
      case 2:
        salidaFila = "B";
        break;
        
      case 3:
        salidaFila = "C";
        break;
        
      case 4:
        salidaFila = "D";
        break;
        
      case 5:
        salidaFila = "E";
        break;
        
      case 6:
        salidaFila = "F";
        break;
        
      case 7:
        salidaFila = "G";
        break;
        
      case 8:
        salidaFila = "H";
        break;
        
      case 9:
        salidaFila = "I";
        break;
        
      default:
        salidaFila = "J";
    }
    
    
    System.out.print("\n\nSkynet dispara a las coordenadas: " + salidaFila + " - " + columna);
    
  }
  
  
}
