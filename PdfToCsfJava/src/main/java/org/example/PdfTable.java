package org.example;
import java.util.ArrayList;
import java.util.List;


public class PdfTable {
    public static List<Procedimento> extractProcedimentos(String pdfText) {
        List<Procedimento> procedimentos = new ArrayList<>();

        boolean tableStarted = false;
        boolean isFirstLine = true;



        for (String line : pdfText.split("QuebraLinha")) {
            if (line.contains("e suas alterações)")) { // Cabeçalho da tabela
                tableStarted = true;
                continue;
            }
            if (tableStarted && line.trim().isEmpty()) { // Fim da tabela
                break;
            }

            if (line.contains("Legenda:")) {
                tableStarted = false;
                continue;
            }

            if (tableStarted) {
                if (isFirstLine) {
                    isFirstLine = false; // Ignora a primeira linha
                    continue; // Pula para a próxima iteração
                }

                line.trim();
                String od = "X", amb = "X", hco = "X", hso = "X", ref = "X", pac = "X";
                Procedimento proc = new Procedimento();
                proc.parseLinha(line);
                procedimentos.add(proc);
            }
        }
        return procedimentos;
    }
}
