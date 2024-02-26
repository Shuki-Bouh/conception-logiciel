package src.main.Serveur;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LireLigne implements ILireExcel{

    @Override
    public ArrayList lire_file(String path, int begRow, int begCol) {
        // Ligne 20 en excel est de 19 en entrée et la colonne 1 sur excel est de 0 ici
        ArrayList<String> stringList = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(path)) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            // Check if param1 is provided, if not, set a default value of 10
            int Row = begRow;
            int startRow;
            if (Row <= 6) {
                startRow = 7;
            }
            else {
                startRow=Row;
            }

            int endRow=startRow;
            int startColumn = begCol;
            int endColumn = begCol+3;

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
}
