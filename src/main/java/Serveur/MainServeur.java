package Serveur;

public class MainServeur {

	private static Bioreacteur bioreact;
	public static void main(String[] args) {
		bioreact=new Bioreacteur();
		ServeurTCP myServ = new ServeurTCP(new UnContexte() , new ProtocolePingPong() , 6666 );
		myServ.start();

		
	}
}
