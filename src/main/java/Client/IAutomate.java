package Client;

/**
 * API représentant un automate lié à un compte client, permettant de se
 * connecter à une banque, et de pouvoir effectuer un retrait
 * 
 */
public interface IAutomate {

	boolean connexionBR();

	void deconnexionBR();
	
}