package Client;

import java.util.ArrayList;

class Automate implements IAutomate {

    private int port = 6666;
    private ClientTCP monClientTCP;
    private Data[] datas = new Data[5819];


    public Automate() {
        monClientTCP = new ClientTCP("localhost", port);
        }


    private void load() {
        int ligne = 0;

    }


    private void getData(int ligne) {
        String[] msgServer = monClientTCP.transmettreChaine(String.valueOf(ligne));
        Data data = new Data(msgServer);
        datas[ligne] = data;
    }

    @Override
    public boolean connexionBR(){
        return monClientTCP.connecterAuServeur();
    }

    @Override
    public void deconnexionBR() {
        monClientTCP.deconnecterDuServeur();
    }


    public static void main(String[] args) {
        Automate auto = new Automate();
        Data data = auto.datas[0];
        System.out.println(String.valueOf(data.ph));
    }
        }
