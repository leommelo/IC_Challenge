package org.example;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

import org.apache.commons.compress.archivers.zip.*;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ZipCsvModifier {

    public static void modifySpecificCsvInZip(String zipFilePath, String csvFileName) throws IOException {
        // Garantir que o diretório de saída exista
        new File("output").mkdirs();

        // Abrir o arquivo ZIP original
        File file = new File(zipFilePath);
        ZipFile zipFile = new ZipFile(file);

        // Arquivo temporário para armazenar o conteúdo modificado
        File tempZipFile = new File("output/Update_" + file.getName());

        try (FileOutputStream fos = new FileOutputStream(tempZipFile);
             ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(fos)) {

            Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
            while (entries.hasMoreElements()) {
                ZipArchiveEntry entry = entries.nextElement();

                // Se for o CSV alvo, modificá-lo
                if (entry.getName().equals(csvFileName)) {
                    try (InputStream inputStream = zipFile.getInputStream(entry);
                         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                         ByteArrayOutputStream modifiedCsvBytes = new ByteArrayOutputStream()) {

                        // Ler primeira linha
                        String firstLine = reader.readLine();
                        // Dividir por ponto e vírgula
                        String[] columns = firstLine.split(";");

                        // Verificar se há colunas suficientes
                        if (columns.length >= 5) {
                            // Modificar colunas 4 e 5 (índice 3 e 4)
                            columns[3] = "Seg. Odontológica";
                            columns[4] = "Seg. Ambulatorial";
                        }

                        // Reconstruir a linha com ponto e vírgula
                        String modifiedFirstLine = String.join(";", columns) + "\n";
                        modifiedCsvBytes.write(modifiedFirstLine.getBytes(StandardCharsets.UTF_8));

                        // Copiar linhas restantes
                        String line;
                        while ((line = reader.readLine()) != null) {
                            modifiedCsvBytes.write((line + "\n").getBytes(StandardCharsets.UTF_8));
                        }

                        // Criar nova entrada ZIP para CSV modificado
                        ZipArchiveEntry zipEntry = new ZipArchiveEntry(csvFileName);
                        zipOut.putArchiveEntry(zipEntry);
                        zipOut.write(modifiedCsvBytes.toByteArray());
                        zipOut.closeArchiveEntry();

                        System.out.println("CSV modificado com sucesso: " + csvFileName);
                    }
                } else {
                    // Copiar outros arquivos como estão
                    ZipArchiveEntry zipEntry = new ZipArchiveEntry(entry);
                    zipOut.putArchiveEntry(zipEntry);
                    try (InputStream inputStream = zipFile.getInputStream(entry)) {
                        IOUtils.copy(inputStream, zipOut);
                    }
                    zipOut.closeArchiveEntry();
                }
            }
        }

        System.out.println("ZIP modificado criado: " + tempZipFile.getName());
    }
}