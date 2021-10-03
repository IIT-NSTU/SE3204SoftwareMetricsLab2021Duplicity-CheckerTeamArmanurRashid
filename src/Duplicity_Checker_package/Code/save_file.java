package Duplicity_Checker_package.Code;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class save_file {

    //save file as docx
    public void save_as_docx(File file_name, String text) {
        try {
            XWPFDocument docx = new XWPFDocument();
            FileOutputStream out = new FileOutputStream(file_name + ".docx");
            XWPFParagraph n = docx.createParagraph();
            XWPFRun run = n.createRun();
            run.setText(text);
            docx.write(out);
            out.close();
            JOptionPane.showMessageDialog(null, "File saved in " + file_name + ".docx", "File saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    //save file as pdf
    public void save_as_pdf(File file_name, String text) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file_name+ ".pdf"));
            document.open();
            document.add(new Paragraph(text));
            document.close();
            writer.close();
            JOptionPane.showMessageDialog(null, "File saved in " + file_name + ".pdf", "File saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}