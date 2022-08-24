import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class KW {
    public static void main(String[] args) throws Exception {

        FileInputStream excellFile1 = new FileInputStream(new File("/Users/rtyshchenko/Downloads/untitled/src/main/resources/x1.xlsx"));
        FileInputStream excellFile2 = new FileInputStream(new File("/Users/rtyshchenko/Downloads/untitled/src/main/resources/x2.xlsx"));

        XSSFWorkbook workbook1 = new XSSFWorkbook(excellFile1);
        XSSFWorkbook workbook2 = new XSSFWorkbook(excellFile2);

        XSSFSheet sheet1 = workbook1.getSheetAt(0);
        XSSFSheet sheet2 = workbook2.getSheetAt(0);

        List<Pills> pillsSheet1 = new ArrayList<>();
        List<Pills> pillsSheet2 = new ArrayList<>();

        for (Row r : sheet1) {
            int number = (int) r.getCell(0).getNumericCellValue();
            String serija = r.getCell(1).toString();
            pillsSheet1.add(new Pills(number, serija));
        }

        for (Row f : sheet2) {
            int number = (int) f.getCell(0).getNumericCellValue();
            String serija = (f.getCell(1)).toString();
            pillsSheet2.add(new Pills(number, serija));
        }

        int count = 0;
        int countDif = 0;
        System.out.println("Списание товаров № 328 від 18 серпня 2022");
        for (Pills pill : pillsSheet2) {
            if (!pillsSheet1.stream().anyMatch(s -> s.equals(pill))) {
                System.out.println("Товар: (кількість), номер серії: " + pill);
                countDif += pill.number;
                count++;
            }
        }
        System.out.println("Кількість відмінностей у файлі: " + count);
        System.out.println("Кількість товарів у відмінностях у файлі: " + countDif);
        System.out.println();

        int countDif1 = 0;
        System.out.println("Залишки брак – ОРИГІНАЛ");
        int count2 = 0;
        for (Pills pill : pillsSheet1) {
            if (!pillsSheet2.stream().anyMatch(s -> s.equals(pill))) {
                System.out.println("Товар: (кількість), номер серії: " + pill);
                countDif1 += pill.number;
                count2++;
                count++;
            }
        }
        System.out.println("Кількість відмінностей у 2му файлі: " + count2);
        System.out.println("Загальна кількість відмінностей у 2х файлах: " + count);
        System.out.println("Кількість товарів у відмінностях у файлі: " + countDif1);
        System.out.println("Різниця у кількості віднінностей у 2х  файлах " + countDif1 + " - " + countDif + " = " + (countDif1 - countDif));
        excellFile1.close();
        excellFile2.close();
    }
}
