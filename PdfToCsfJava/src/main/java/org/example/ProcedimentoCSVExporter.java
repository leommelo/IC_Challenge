package org.example;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProcedimentoCSVExporter {
    public static void exportarParaCSV(List<Procedimento> procedimentos, String caminhoArquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            // Escrever cabeçalho
            writer.write("Procedimento;RN;Vigencia;OD;AMB;HCO;HSO;REF;PAC;DUT;SUBGRUPO;GRUPO;CAPÍTULO");
            writer.newLine();

            // Escrever cada procedimento
            for (Procedimento proc : procedimentos) {
                writer.write(
                        escapeCsvField(proc.getProcedimento()) + ";" +
                                escapeCsvField(proc.getRn() != null ? proc.getRn() : "") + ";" +
                                escapeCsvField(proc.getVigencia() != null ? proc.getVigencia() : "") + ";" +
                                (proc.isOd() ? "SIM" : "X") + ";" +
                                (proc.isAmb() ? "SIM" : "X") + ";" +
                                (proc.isHco() ? "SIM" : "X") + ";" +
                                (proc.isHso() ? "SIM" : "X") + ";" +
                                (proc.isRef() ? "SIM" : "X") + ";" +
                                (proc.isPac() ? "SIM" : "X") + ";" +
                                escapeCsvField(proc.getDut() != null ? proc.getDut() : "") + ";" +
                                escapeCsvField(proc.getSubgrupo() != null ? proc.getSubgrupo() : "") + ";" +
                                escapeCsvField(proc.getGrupo() != null ? proc.getGrupo() : "") + ";" +
                                escapeCsvField(proc.getCapitulo() != null ? proc.getCapitulo() : "")
                );
                writer.newLine();
            }
        }
    }

    // Método para escapar campos CSV, lidando com vírgulas, aspas e caracteres especiais
    private static String escapeCsvField(String field) {
        if (field == null) return "";

        // Se o campo contém vírgula, aspas ou quebra de linha, deve ser envolto em aspas
        if (field.contains("\n")) {
            return field.replace("\n", "");
        }

        return field;
    }

}