package service;

import model.Note;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.File;
import java.io.IOException;

public class PDFExporter {

    public static void exportToPDF(Note note, File saveFile) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);

        contentStream.beginText();
        contentStream.setLeading(20f);
        contentStream.newLineAtOffset(50, 750);

        contentStream.showText("Title: " + note.getTitle());
        contentStream.newLine();
        contentStream.newLine();

        String[] lines = note.getContent().split("\n");
        for (String line : lines) {
            contentStream.showText(line);
            contentStream.newLine();
        }

        contentStream.endText();
        contentStream.close();

        document.save(saveFile);
        document.close();
    }
}
