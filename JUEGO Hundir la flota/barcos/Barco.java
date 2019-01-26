/**
 * Clase Barco:
 * 
 * Esta clase responde a la necesidad de crear una serie de objetos que harán la función de barcos en el juego de "Hundir la flota".
 * 
 * La clase se compone de los siguientes métodos:
 * 
 * <ul>
 * <li>El constructor "Barco" que acepta por parámetros: el nombre del tipo de barco, las casillas que lo componen y el jugador al que pertenece. </li>
 * <li></li>
 * </ul>
 * 
 * 
 * @author Francisco Javier González Sabariego
 * 
 * @version 1.0
 * 
 * 18/01/2019
 */

package barcos;


public class Barco {
  
  String nombreBarco;
  
  protected int casillasBarco;
  
  protected boolean verticalBarco=false;
  
  protected boolean posicionInvertida=false;
  
  protected boolean hundidoBarco;
  
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
    
    casillasBarco=casillas;
    
    hundidoBarco=false;
    
    numeroJugador=jugador;
    
  }
  
  
  /**
   * Devuelve el número de casillas del barco.
   * 
   * @return
   */
  public int getCasillas() {
    
    return this.casillasBarco;
    
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
    
    this.casillasBarco-=1;
    
    if (this.casillasBarco==0) {
      
      this.hundidoBarco=true;
      
    }
    
  }

  
  public String mensajeTocadoHundido() {
    
    if (this.hundidoBarco) {
      
      return "El " + this.nombreBarco + " del Jugador " + this.numeroJugador + " está hundido.";
      
    }
    
    return "Tocado.";
    
  }
  
  
  
}
