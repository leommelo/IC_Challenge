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

    // Método para zipar todos os arquivos dentro de uma pasta
    public static void zipFiles(String sourceDir, String zipFilePath) throws IOException {
        File folder = new File(sourceDir);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Diretório de origem não existe: " + sourceDir);
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(fos)) {

            File[] files = folder.listFiles();
            if (files == null || files.length == 0) {
                System.out.println("Nenhum arquivo encontrado para compactação.");
                return;
            }

            for (File file : files) {
                if (file.isFile()) { // Apenas arquivos, ignora subpastas
                    try (FileInputStream fis = new FileInputStream(file)) {
                        ZipArchiveEntry zipEntry = new ZipArchiveEntry(file, file.getName());
                        zipOut.putArchiveEntry(zipEntry);
                        IOUtils.copy(fis, zipOut);
                        zipOut.closeArchiveEntry();
                    }
                }
            }

            System.out.println("Arquivos compactados com sucesso em: " + zipFilePath);
        }
    }



}
