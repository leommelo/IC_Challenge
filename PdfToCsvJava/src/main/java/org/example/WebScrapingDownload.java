package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WebScrapingDownload {
    public static void main(String[] args) {
        String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
        String downloadDir = "downloads";
        String inputDir = "input";
        String zipFileName = "PdfFiles.zip";

        try {
            // Criar diretórios se não existirem
            Files.createDirectories(Paths.get(downloadDir));
            Files.createDirectories(Paths.get(inputDir));

            // 1. Acessar a página e pegar os links dos PDFs
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href$=.pdf]");

            for (Element link : links) {
                String pdfUrl = link.absUrl("href");
                String fileName = pdfUrl.substring(pdfUrl.lastIndexOf("/") + 1);

                // Filtra apenas os PDFs "Anexo_I" e "Anexo_II"
                if (fileName.contains("Anexo_I") || fileName.contains("Anexo_II")) {
                    Path filePath = Paths.get(downloadDir, fileName);
                    System.out.println("Baixando: " + fileName);
                    downloadFile(pdfUrl, filePath);
                }
            }

            // Compactar usando ZipExtractor
            String zipPath = zipFileName;
            ZipExtractor.zipFiles(downloadDir, zipPath);
            System.out.println("ZIP criado: " + zipPath);

            // Mover ZIP para input/
            Files.move(Paths.get(zipPath), Paths.get(inputDir, zipPath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("ZIP movido para " + inputDir);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Função para baixar arquivos
    private static void downloadFile(String fileURL, Path savePath) throws IOException {
        try (InputStream in = new URL(fileURL).openStream()) {
            Files.copy(in, savePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

}
