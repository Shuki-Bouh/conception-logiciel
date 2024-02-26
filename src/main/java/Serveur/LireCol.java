package Serveur;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class LireCol implements ILireExcel{
    @Override
    public ArrayList lire_file(String path, int begRow, int begCol) {
        ArrayList<Float> floatList = new ArrayList<>();
        try (FileInputStream file = new FileInputStream(path)) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            // Check if param1 is provided, if not, set a default value of 10
            if (begRow <= 6) {
                begRow = 7;
            }

            // Check if param2 is provided, if not, set a default value of "default"
            int endRow=begRow;

            int startColumn = begCol;
            int endColumn = begCol;

            for (int i = begRow; i <= endRow; i++) {
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
}
