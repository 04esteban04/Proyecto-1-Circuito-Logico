package Aplicacion;

/**
 * Interfaz base para poder crear cualquier conector lógico
 */
public interface interfazConector {
    /**
     * Método para establecer el valor de la primera entrada
     * @param entrada - Valor de la primera entrada
     */
    public void setPrimeraEntrada(int entrada);

    /**
     * Método para establecer el valor de la segunda entrada
     * @param entrada - Valor de la segunda entrada
     */
    public void setSegundaEntrada(int entrada);

    /**
     * Método para obtener la salida del conector
     * @return - Resultado de la operación lógica
     */
    public int getSalida();

}