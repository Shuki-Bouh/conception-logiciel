package Client;


import java.util.ArrayList;

class Data {

    String time;
    float temp;
    float oxy;
    float ph;

    public Data(String[] dataServer) {
        this.time = dataServer[0];
        this.temp = Float.parseFloat(dataServer[1]);
        this.oxy = Float.parseFloat(dataServer[2]);
        this.ph = Float.parseFloat(dataServer[3]);
    }
}