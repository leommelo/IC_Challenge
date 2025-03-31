package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.io.InputStream;

public class PdfReader {
    public static String readPdf(InputStream pdfInputStream) throws IOException {
        try (PDDocument document = PDDocument.load(pdfInputStream)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String pdfText = pdfStripper.getText(document);

            // Expressão regular para substituir "\n" por "QuebraLinha", exceto quando vier logo após "GERAIS", "HOSPITALARES",  "INVASIVOS", "CAPÍTULO", "TERAPÊUTICO" ou "suas alterações)"
            String text = pdfText.replaceAll("(?<=(GERAIS|HOSPITALARES|INVASIVOS|CAPÍTULO|TERAPÊUTICOS|suas alterações\\)))\\s*\\n\\s*", "QuebraLinha");

            text = text.replaceAll("(?<!GERAIS|HOSPITALARES|INVASIVOS|CAPÍTULO|TERAPÊUTICOS)\\s*\\n\\s*", " ");

            // Expressão para corrigir "\n" em "PROCESSOS GERAIS" em SUBGRUPOS
            text = text.replaceAll("PROCEDIMENTOS GERAISQuebraLinhaSISTEMA MÚSCULO-ESQUELÉTICO E ARTICULAÇÕES",
                    "PROCEDIMENTOS GERAIS SISTEMA MÚSCULO-ESQUELÉTICO E ARTICULAÇÕES");

            return text.replaceAll(
                    "(PROCEDIMENTOS CLÍNICOS AMBULATORIAIS E HOSPITALARES)\\s*QuebraLinha\\s*(PROCEDIMENTOS CLÍNICOS AMBULATORIAIS E HOSPITALARES)",
                    "$1 $2"
            );
        }
    }
}
