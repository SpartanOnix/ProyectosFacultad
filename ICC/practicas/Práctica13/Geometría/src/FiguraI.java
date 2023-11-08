public interface FiguraI {
	
	public static final double PI = 3.1416;

	/**
	 * Rota una figura 90 grados.
	 */
	public void rotar90();

	/**
	 * Rota una figura 180 grados.
	 */
	public void rotar180();

	/**
	 * Mueve una figura deltaX en X y deltaY en Y.
	 * @param deltaX Cuanto se mueve en X.
	 * @param deltaY Cuanto se mueve en Y.
	 */
	public void mover(double deltaX, double deltaY);

	/**
	 * Nos dice si una figura es igual a otra.
	 * @param f La figura a comparar.
	 * @return true si son iguales.
	 */
	public boolean equals(FiguraI f);
}