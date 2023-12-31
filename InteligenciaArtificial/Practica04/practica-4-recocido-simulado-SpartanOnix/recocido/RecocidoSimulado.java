package recocido;

/**
Clase con los métodos necesarios para implementar el método
de recocido simulado junto con la solución a un problema particular.

@author Benjamin Torres
@author Verónica E. Arriola
@version 0.1
*/
public class RecocidoSimulado{
  
  /** Es la calificación que otorga la heurística a la solución actual. */
  private float valor;
  
  private float temperatura;
  private float decaimiento;
  
  /** Solución actual. */
  private Solucion sol;

  /**
  Inicializa los valores necesarios para realizar 
  recocido simulado durante un numero determinado de iteraciones
  @param inicial, instancia de la clase para el problema particular que
                  se quiere resolver.  Contine la propuesta inicial.
  @param temperatura, float con el valor actual 
  @param decaimiento, float que sera usado para hacer decaer el valor de temperatura
  */
  public RecocidoSimulado(Solucion inicial,
                          float temperaturaInicial,
                          float decaimiento){ //escoge los parametros necesarios para inicializar el algoritmo
    sol = inicial;
    temperatura = temperaturaInicial;
    this.decaimiento = decaimiento;
  }

  /**
  Función que calcula una nueva temperatura en base a
  la anterior y el decaemiento usado.
  @return nueva temperatura
  */
  public float nuevaTemperatura(){
    // TODO
    return 0;
  }

  /**
  Genera y devuelve la solución siguiente a partir de la solución
  actual. Dependiendo de su valor,
  si es mejor o peor que la que ya se tenía,
  y de la probabilidad de elegir una solución peor, puede devolver
  una solución nueva o quedarse con la que ya se tenía.
  @return Solución nueva
  */
  public Solucion seleccionarSiguienteSolucion(){
    Solucion candidata = new Solucion();
    // TODO
    return candidata;
  }

  /**
  Ejecuta el algoritmo con los parametros con los que fue inicializado
  devuelve una solucion.
  @param
  @return Solucion al problema
  */
  public Solucion ejecutar(){
    // TODO
    return sol;
  }
}
