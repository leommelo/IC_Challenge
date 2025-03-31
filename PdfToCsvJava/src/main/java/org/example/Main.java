package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.example.ZipCsvModifier.modifySpecificCsvInZip;

public class Main {
    public static void main(String[] args) {
        //Baixar e zipar arquivos
        WebScrapingDownload.main(args);

        String zipFilePath = "input/PdfFiles.zip"; // Caminho do ZIP
        String targetPdfName = "Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf"; // Nome do PDF específico

        try {
            // Extrair apenas o PDF específico
            InputStream pdfStream = ZipExtractor.extractSpecificPdf(zipFilePath, targetPdfName);

            if (pdfStream != null) {
                // Ler o PDF
                String pdfText = PdfReader.readPdf(pdfStream);


                if (pdfText != null) {
                    //Extrai todas as linhas da tabela
                    List<Procedimento> procedimentos = PdfTable.extractProcedimentos(pdfText);
                    System.out.println("Procedimentos extraídos: " + procedimentos.size());
                    //Salva em CSV
                    ProcedimentoCSVExporter.exportarParaCSV(procedimentos, "output/procedimentos.csv");
                    try {
                        //"Zipa" o CSV
                        ZipExtractor.zipFile("output/procedimentos.csv", "Teste_Leonardo_Marques.zip");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        modifySpecificCsvInZip("output/Teste_Leonardo_Marques.zip", "procedimentos.csv");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Nenhum PDF correspondente foi encontrado no ZIP.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
