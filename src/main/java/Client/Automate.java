package Client;

class Automate implements IAutomate {

    private Data data;
    private int port = 6666;
    private ClientTCP monClientTCP;

    public void Automate() {
        monClientTCP = new ClientTCP("localhost", port);
        String[] time = ;
        float[]
        }

    private load() {
        data = new Data();

    }

    private int get_time(){
        return data.time;
        }

    private void get_oxy() {
        return data.oxy;
        }

    private void get_ph() {
        return data.ph;
        }

    private void get_temp(){
        return data.temp;
        }

    @Override
    private boolean connexionBR(){
        return monClientTCP.connexionAuServeur()
    }

    @Override
    public void deconnexionBR() {
        monClientTCP.deconnecterDuServeur();
    }
        }