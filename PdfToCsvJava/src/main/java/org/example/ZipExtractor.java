package org.example;

import org.apache.commons.compress.archivers.zip.*;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;

import java.util.Enumeration;

public class ZipExtractor {

    // Método para extrair um PDF específico do ZIP
    public static InputStream extractSpecificPdf(String zipFilePath, String targetPdfName) throws IOException {
        File file = new File(zipFilePath);
        ZipFile zipFile = new ZipFile(file);

        Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
        while (entries.hasMoreElements()) {
            ZipArchiveEntry entry = entries.nextElement();
            if (entry.getName().equals(targetPdfName)) { // Verifica se é o arquivo desejado
                System.out.println("Arquivo encontrado: " + entry.getName());
                return zipFile.getInputStream(entry);
            }
        }

        System.out.println("Arquivo não encontrado: " + targetPdfName);
        return null; // Retorna null se o arquivo não for encontrado
    }

    // 🚀 Novo método para zipar um arquivo
    public static void zipFile(String sourceFilePath, String zipFileName) throws IOException {

        String zipFilePath = "output/" + zipFileName;
        File sourceFile = new File(sourceFilePath);
        if (!sourceFile.exists()) {
            System.out.println("O arquivo de origem não existe: " + sourceFilePath);
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(fos);
             FileInputStream fis = new FileInputStream(sourceFile)) {

            ZipArchiveEntry zipEntry = new ZipArchiveEntry(sourceFile, sourceFile.getName());
            zipOut.putArchiveEntry(zipEntry);
            IOUtils.copy(fis, zipOut); // Usa IOUtils para copiar os bytes do arquivo

            zipOut.closeArchiveEntry();
            System.out.println("Arquivo compactado com sucesso: " + zipFileName);
        }
    }


}
