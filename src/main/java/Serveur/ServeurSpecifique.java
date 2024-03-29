package Serveur;

import java.io.IOException;
import java.net.Socket;

/**
 * Processus de Transaction (anciennement src.main.test.Serveur.ServeurSpecifique)
 */
class ServeurSpecifique extends Thread {

	private Socket clientSocket;
	private ServeurTCP monServeurTCP;

	public ServeurSpecifique(Socket uneSocket, ServeurTCP unServeur) {
		super("ServeurThread");
		clientSocket = uneSocket;
		System.out.println("[ProcessusTransaction] CLIENT : " + clientSocket);
		monServeurTCP = unServeur;
	} 

	public void run() {        
		try {

			monServeurTCP.getProtocole().execute( monServeurTCP.getContexte() , clientSocket.getInputStream() , clientSocket.getOutputStream() );

			System.out.println("Processus transaction fait");
		} catch (IOException e) {
			System.err.println("[ProcessusTransaction] Exception : " + e );
			e.printStackTrace();
		}
	} 
}
