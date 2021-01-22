package br.com.vivo.telefonica.matrix;

/**
 * <h1>Dimention Matrix</h1>
 * 
 * @author Jorge Sevilla
 * @version 1.0
 * @since 2021
 * 
 */

public class MatrixMain {

	static int[] list;
	public static boolean number(int num) {
		for (int i = 0; i < list.length; i++) {
			if (list[i] == num) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// Main for test the functionality of the interaction cicle

		int[] array = { 1, 2, 2, 3, 6, 4, 4, 9, 5, 9, 2};
		list = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			int count = 0;
			for (int j = 0; j < list.length; j++) {
				if (array[i] == array[j]) {
					count++;
					if (number(array[i])) {
						list[i] = array[i];
					}
				}
			}
			if (list[i] != 0) {
				System.out.println(list[i] + " repeat: " + count);
			}
		}

	}

}
