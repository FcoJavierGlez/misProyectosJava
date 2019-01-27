
package barcos;

/**
 * Clase Barco:
 * 
 * Esta clase responde a la necesidad de crear una serie de objetos que harán la función de barcos en el juego de "Hundir la flota".
 * 
 * La clase se compone de los siguientes métodos:
 * 
 * <ul>
 * <li>El constructor "Barco" que acepta por parámetros: el nombre del tipo de barco, las casillas que lo componen y el jugador al que pertenece. </li>
 * <li>getCasillas()</li>
 * <li>getVerticalidad()</li>
 * <li>setVerticalidad()</li>
 * <li>setPosicionInvertida()</li>
 * <li>setCasillaInicial()</li>
 * <li>tocado()</li>
 * </ul>
 * 
 * 
 * @author Francisco Javier González Sabariego
 * 
 * @version 1.0  // Fecha: 18/01/2019
 * 
 * 
 */
public class Barco {
  
  String nombreBarco;
  
  protected int casillasIniciales;
  
  protected int casillasActuales;
  
  protected boolean verticalBarco=false;
  
  protected boolean posicionInvertida=false;
  
  protected boolean hundidoBarco;
  
  protected boolean haSalidoMensajeHundido;
  
  int numeroJugador;
  
  
  //Necesito la posición del barco...  
  int [][] casillaInicial = new int [1][2];
  
  
  
  
  /**
   * Constructor de la clase Barco. Se le pasan como parámetros:
   * <ul>
   * <li>El nombre del barco, por ejemplo: "Portaaviones".</li>
   * <li>Las casillas que ocupa el barco, por ejemplo: 5.</li>
   * <li>El jugador al que pertenece el barco, por ejemplo: 1.</li>
   * </ul>
   * @param nombre
   * @param casillas
   * @param jugador
   */
  public Barco(String nombre, int casillas, int jugador) {
    
    nombreBarco=nombre;
    
    casillasIniciales=casillas;
    
    casillasActuales=casillasIniciales;
    
    hundidoBarco=false;
    
    numeroJugador=jugador;
    
    haSalidoMensajeHundido=false;
    
  }
  
  
  /**
   * Devuelve el número de casillas del barco.
   * 
   * @return
   */
  public int getCasillas() {
    
    return this.casillasActuales;
    
  }
  
  
  /**
   * Devuelve un booleano para mostrar la dirección del barco.
   * 
   * <ul>
   * <li>Si es "true" el barco se pintará vertical.</li>
   * <li>Si es "false" el barco se pintará horizontal.</li>
   * </ul>
   * @return
   */
  public boolean getVerticalidad() {
    
    return this.verticalBarco;
    
  }
  
  
  /**
   * Este método pone en posición vertical a la instancia seleccionada.
   * 
   */
  public void setVerticalidad() {
    
    this.verticalBarco=true;
    
  }
  
  
  /**
   * Este método asigna que el barco se lea en dirección invertida, es decir, en vez de tener en cuenta su casilla inicial para leer su posición
   * de izquierda a derecha o de arriba abajo, se usará para leerse de derecha a izquierda y de abajo a arriba.
   * 
   */
  public void setPosicionInvertida() {
    
    this.posicionInvertida=true;
    
  }
  
  
  /**
   * Almacena la que se considera la primera casilla del barco, la casilla inicial.
   * 
   * @param fila
   * @param columna
   */
  public void setCasillaInicial(int fila, int columna) {
    
    this.casillaInicial[0][0] = fila;
    
    this.casillaInicial[0][1] = columna;
    
  }
  
  
  /**
   * Cuando un barco detecte que ha sido impactado hará uso de este método, este método resta sus casillas en 1
   * y cuando su total de casillas llegue a ser 0 activará el booleano que indica que ese se ha hundido.
   */
  public void tocado() {
    
    this.casillasActuales-=1;
    
    if (this.casillasActuales==0) {
      
      this.hundidoBarco=true;
      
    }
    
  }
  
  
  /**
   * Comprueba si se ha impactado a una instanacia de la clase Barco.<br><br>
   * 
   * Introducimos como parámetros las coordenadas de lanzamiento y comprobamos si coincide con la ubicación del barco.<br><br>
   * 
   * Para ello, comprobamos la posición del barco (horizontal o vertical) y si a la hora de ser insertado en el tablero de juego
   * se ha insertado en sentido normal (izquierda - derecha || arriba-abajo) o a la inversa (dercha-izquierda || abajo-arriba).
   * Después, partiendo de las coordenadas de la casilla inicial (la que insertó el usuario como la primera casilla del barco en el tablero)
   * empezamos a contar con un doble bucle for incrementando o reduciendo o bien el valor del eje de las columnas (si está en horizontal)
   * o bien el eje de las filas (si el barco está en vertical), de tal forma que tras cada incremento o reducción se hace una comprobación
   * y si las coordenadas resultantes son iguales a las coordenadas del disparo ése es el barco que habrá sido tocado.<br><br>
   * 
   * En caso de impacto retornamos verdadero. En caso contrario falso.
   * 
   * @param fila
   * @param columna
   * @return
   */
  public boolean compruebaImpacto(int fila, int columna) {
    
    
    int filaInicial = this.casillaInicial[0][0];
    
    int columnaInicial = this.casillaInicial[0][1];
    
    boolean salida = false;
    
    
    if (!this.getVerticalidad() && !this.posicionInvertida) {             //Hace la comprobación del impacto si el barco está horizontal y se lee de izquierda a derecha:
      
      for (int i=filaInicial; i<=filaInicial; i++) {
        
        for (int k=columnaInicial; k<=(columnaInicial+this.casillasIniciales-1); k++) {      
          
          if (fila==i && columna==k) {
            
            return true;
            
          }
          
        }
        
      }
      
    } else if(!this.getVerticalidad() && this.posicionInvertida) {        //Hace la comprobación del impacto si el barco está horizontal y se lee de derecha a izquierda:
      
      for (int i=filaInicial; i<=filaInicial; i++) {
        
        for (int k=columnaInicial; k>=(columnaInicial-this.casillasIniciales-1); k--) {
          
          if (fila==i && columna==k) {
            
            return true;
            
          }
          
        }
        
      } 
      
    } else if (this.getVerticalidad() && !this.posicionInvertida) {       //Hace la comprobación del impacto si el barco está vertical y se lee de arriba a abajo:
      
      for (int k=columnaInicial; k<=columnaInicial; k++) {
        
        for (int i=filaInicial; i<=(filaInicial+this.casillasIniciales-1); i++) {
          
          if (fila==i && columna==k) {
            
            return true;
            
          }
          
        }
        
      } 
      
    } else {                                                              //Hace la comprobación del impacto si el barco está vertical y se lee de abajo a arriba:
      
      for (int k=columnaInicial; k<=columnaInicial; k++) {
        
        for (int i=filaInicial; i>=(filaInicial-this.casillasIniciales-1); i--) {
          
          if (fila==i && columna==k) {
            
            return true;
            
          }
          
        }
        
      } 
      
    }
    
    return false;
    
  }
  
  
  /**
   * Este método pone en verdadero el booleano que indica si ya ha salido el mensaje de que el barco está hundido.
   */
  public void setHaSalidoMensajeHundido() {
    
    this.haSalidoMensajeHundido=true;
    
  }
  
  
  /**
   * Muestra el mensaje de que el barco está hundido.
   */
  public void mensajeHundido() {
    
    if (this.hundidoBarco && this.nombreBarco!="Fragata") {
      
      System.out.print("\n\nEnhorabuena!! Has hundido el " + this.nombreBarco + " del Jugador " + this.numeroJugador + ".");
      
    } else {
      
      System.out.print("\n\nEnhorabuena!! Has hundido la " + this.nombreBarco + " del Jugador " + this.numeroJugador + ".");
      
    }
    
  }
  
  
  
}
