package br.com.vivo.telefonica;

import junit.framework.TestCase;

/**
 * <h1>Matrix Json</h1>
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
