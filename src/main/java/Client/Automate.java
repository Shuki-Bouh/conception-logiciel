package Client;

import java.util.ArrayList;

class Automate implements IAutomate {

    private int port = 6666;
    private ClientTCP monClientTCP;
    private Data[] datas = new Data[5819];


    public Automate() {
        monClientTCP = new ClientTCP("localhost", port);
        }

    public void getData(int ligne) {
        String[] msgServer = monClientTCP.transmettreChaine(toString(ligne));
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
        }