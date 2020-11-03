package grafika;

import main.Main;

import java.awt.event.*;

public class Input {

	public int xPele = 0, yPele = 0;
	public boolean peleClick = false;
	public static int[] pogas = new int[] {};

	public Input() {

		GraphicsManager.grafika.ekrans.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("Input: window open.");

			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Input: window closing.");
				//Main.running = false;
			}

			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("Input: window closed.");
				Main.running = false;
			}

			@Override
			public void windowIconified(WindowEvent e) {
				Main.minimized = true;
				pogas = new int[] {};
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				Main.minimized = false;
			}

			@Override
			public void windowActivated(WindowEvent e) {
				Main.windowActive = true;
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				Main.windowActive = false;
				pogas=new int[] {};
			}

		});

		GraphicsManager.grafika.ekrans.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				int poga = e.getKeyCode();
				boolean jauns = true;

				for (int i=0; i < pogas.length; i++) { //p�rbauda vai piespiest� poga ir jauna
					if (poga == pogas[i]) {
						jauns = false;
						break;
					}
				}

				if (jauns) {
					int[] pogasTemp = new int[pogas.length + 1];

					for (int i=0; i < pogas.length; i++) {
						pogasTemp[i] = pogas[i];
					}
					pogasTemp[pogas.length] = poga;

					pogas = pogasTemp;
				}


			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (pogas.length > 1) {
					int poga = e.getKeyCode();
					int[] pogasTemp = new int[pogas.length - 1];

					for (int i=0, j=0; i < pogas.length; i++) {
						if (poga != pogas[i]) {
							pogasTemp[j] = pogas[i];
							j++;
						}
					}

					pogas = pogasTemp;
				} else pogas = new int[] {};
			}

		});

		GraphicsManager.grafika.ekrans.getContentPane().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				peleClick = true;

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				peleClick = false;

			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

		});

		GraphicsManager.grafika.ekrans.getContentPane().addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				xPele = e.getX();
				yPele = e.getY();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				xPele = e.getX();
				yPele = e.getY();
			}

		});

		GraphicsManager.grafika.ekrans.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {}
		});


	}

	//idejas keyboard debounce ievie�anai
	//private static int[][] debounceList = new int[][]{};
	//private static int debounceTimer=5;

	public static void keyboardActionCheck(){
		for (int i=0; i < pogas.length; i++) { //iziet cauri vis�m nospiestaj�m keyboard pog�m
			//dati.drawManagerList.get(thread.dati.modeCurrent).inputActions.keyboardActions(pogas[i], thread);
		}
	}

}
