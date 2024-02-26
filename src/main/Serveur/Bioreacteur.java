package src.main.Serveur;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Bioreacteur {

    private ArrayList<Float> floatList = new ArrayList<>();
    private ArrayList<String> stringList = new ArrayList<>();
    public Bioreacteur() {
        lire("src/main/ressources/2022-10-03-Act2-1.xlsx",7,20,3);
        System.out.println("Autre opération");
        lire_str_ligne("src/main/ressources/2022-10-03-Act2-1.xlsx",20,0);

    }

    private ArrayList lire(String path,int startRow ,int endRow,int Column) {
        try (FileInputStream file = new FileInputStream(path)) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            // Check if param1 is provided, if not, set a default value of 10
            if (startRow <= 6) {
                startRow = 7;
            }

            // Check if param2 is provided, if not, set a default value of "default"
            if (endRow == 0 ) {
                endRow = 5819;
            }

            if (endRow>5819){
                endRow=5819;
            }

            int startColumn = Column;
            int endColumn = Column;

            for (int i = startRow; i <= endRow; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    for (int j = startColumn; j <= endColumn; j++) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            System.out.print(cell.toString() + "\t");
                            try {
                                float floatValue = Float.parseFloat(cell.toString());
                                floatList.add(floatValue);
                            } catch (NumberFormatException e) {
                                System.out.println("Une valeure dans le tableau n'est pas un float");
                            }
                        }
                        System.out.println();
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return floatList;
    }
    private ArrayList lire_str_ligne(String path,int Row,int Column ) {
        // Ligne 20 en excel est de 19 en entrée et la colonne 1 sur excel est de 0 ici
        try (FileInputStream file = new FileInputStream(path)) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            // Check if param1 is provided, if not, set a default value of 10
            int startRow;
            if (Row <= 6) {
                startRow = 7;
            }
            else {
                startRow=Row;
            }

            int endRow=startRow;
            int startColumn = Column;
            int endColumn = Column+3;

            for (int i = startRow; i <= endRow; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    for (int j = startColumn; j <= endColumn; j++) {
                        Cell cell = row.getCell(j);
                        if (cell != null) {
                            System.out.print(cell.toString() + "\t");
                            try {
                                stringList.add(cell.toString());
                            } catch (IllegalArgumentException e) {
                                System.out.println("Une valeure dans le tableau n'est pas un String");
                            }
                        }
                        System.out.println();
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();

        }
        return stringList;
    }
    private void organiser() {

    }

}




