package br.com.vivo.telefonica;

import br.com.vivo.telefonica.matrix.MatrixMain;
import junit.framework.TestCase;

/**
 * 
 * @author Jorge Sevilla
 * @version 1.0
 * @since 2021
 * 
 */

public class MatrixTest extends TestCase{
	private MatrixMain matrixMain;
	
	public void initTestMatrix() {
		matrixMain = new MatrixMain();
	}
	
	public void number() {
		initTestMatrix();
		assertTrue(MatrixMain.number(1));
	}

}
