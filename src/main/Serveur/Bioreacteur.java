package src.main.Serveur;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Bioreacteur {

    private ArrayList<Float> floatList = new ArrayList<>();
    private ArrayList<String> stringList = new ArrayList<>();

    private LireCol lireCol;
    private LireLigne lireLigne;
    public Bioreacteur() {


    }

    private ArrayList get_info(String message){
        int row=Integer.parseInt(message);
        ArrayList get_ligne=lireLigne.lire_file("src/main/ressources/2022-10-03-Act2-1.xlsx",row,0);
        return get_ligne;
    }



}




