package Client;


class Data {

    String[] time;
    float[] temp;
    float[] oxy;
    float[] ph;

    public void Data(String[] time, float[] temp, float[] oxy, float[] ph) {
        this.time = time;
        this.temp = temp;
        this.oxy = oxy;
        this.ph = ph;
    }
}