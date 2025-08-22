import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Example {
    public static void main(String[] args) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("hello_world.pdf"));
            document.open();

            // Create red font
            Font redFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);

            // Add "Hello, World" in red
            Paragraph paragraph = new Paragraph("Hello, World", redFont);
            document.add(paragraph);

            document.close();
            System.out.println("PDF created successfully.");
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
