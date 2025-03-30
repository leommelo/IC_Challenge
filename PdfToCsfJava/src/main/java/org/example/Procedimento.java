package org.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.*;
import java.util.*;

public class Procedimento {
    private String procedimento;
    private String rn;
    private String vigencia;
    private boolean od;
    private boolean amb;
    private boolean hco;
    private boolean hso;
    private boolean ref;
    private boolean pac;
    private String dut;
    private String subgrupo;
    private String grupo;
    private String capitulo;

    public Procedimento() {
        procedimento = "";
        rn = "";
        vigencia = "";
        od = false;
        amb = false;
        hco = false;
        hso = false;
        ref = false;
        pac = false;
        dut = "";
        subgrupo = "";
        grupo = "";
        capitulo = "";
    }



    // Getters
    public String getProcedimento() {
        return procedimento;
    }

    public String getRn() {
        return rn;
    }

    public String getVigencia() {
        return vigencia;
    }

    public boolean isOd() {
        return od;
    }

    public boolean isAmb() {
        return amb;
    }

    public boolean isHco() {
        return hco;
    }

    public boolean isHso() {
        return hso;
    }

    public boolean isRef() {
        return ref;
    }

    public boolean isPac() {
        return pac;
    }

    public String getDut() {
        return dut;
    }

    public String getSubgrupo() {
        return subgrupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getCapitulo() {
        return capitulo;
    }

    // Setters
    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public void setOd(boolean od) {
        this.od = od;
    }

    public void setAmb(boolean amb) {
        this.amb = amb;
    }

    public void setHco(boolean hco) {
        this.hco = hco;
    }

    public void setHso(boolean hso) {
        this.hso = hso;
    }

    public void setRef(boolean ref) {
        this.ref = ref;
    }

    public void setPac(boolean pac) {
        this.pac = pac;
    }

    public void setDut(String dut) {
        this.dut = dut;
    }

    public void setSubgrupo(String subgrupo) {
        this.subgrupo = subgrupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }

    public void parseLinha(String linha) {

        linha = linha.trim();

        // Verificar flags primeiro
        setOd(linha.contains("OD"));
        setAmb(linha.contains("AMB"));
        setHco(linha.contains("HCO"));
        setHso(linha.contains("HSO"));
        setRef(linha.contains("REF"));
        setPac(linha.contains(" PAC "));



        // Tentar parsear RN (opcional)
        Pattern rnPattern = Pattern.compile("(\\d{3}/\\d{4})");
        Matcher rnMatcher = rnPattern.matcher(linha);
        if (rnMatcher.find()) {
            setRn(rnMatcher.group(1));
            linha = linha.replace(getRn(), "").trim();
        }

        // Tentar parsear Vigencia (opcional)
        Pattern dataPattern = Pattern.compile("(\\d{2}/\\d{2}/\\d{4})");
        Matcher dataMatcher = dataPattern.matcher(linha);
        if (dataMatcher.find()) {
            setVigencia(dataMatcher.group(1));
            linha = linha.replace(getVigencia(), "").trim();
        }

        // Tentar parsear DUT (opcional)
        Pattern dutPattern = Pattern.compile("(\\d+)");
        Matcher dutMatcher = dutPattern.matcher(linha);
        if (dutMatcher.find()) {
            setDut(dutMatcher.group(1));
            linha = linha.replace(getDut(), "").trim();
        }

        // Extrair procedimento como o texto inicial antes de qualquer dado numérico
        String[] partesLinha = linha.split(" OD | AMB | HCO | HSO | REF | PAC ");
        if (partesLinha.length > 0 && !partesLinha[0].trim().isEmpty()) {
            setProcedimento(partesLinha[0].trim());
            linha = String.join(" ", java.util.Arrays.copyOfRange(partesLinha, 1, partesLinha.length)).trim();
        } else {
            // Se não conseguir extrair procedimento, usar toda a linha sem flags
            setProcedimento(linha.trim());
        }

        // Remover flags da linha original para extração do procedimento
        linha = linha.replaceAll("OD |AMB |HCO |HSO |REF |PAC ", "").trim();


        try {
            grupoFinder(linha);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void grupoFinder(String line) throws IOException {
        // Ler os grupos do arquivo
        List<String> grupos = Files.readAllLines(Paths.get("input/grupos.txt"));

        // Variáveis para armazenar o primeiro grupo encontrado e sua posição
        String grupoEncontrado = null;
        int menorPosicao = line.length();

        // Percorre todos os grupos e verifica qual ocorre primeiro na string
        for (String grupo : grupos) {
            int posicao = line.indexOf(grupo);
            if (posicao != -1 && posicao < menorPosicao) {
                grupoEncontrado = grupo;
                menorPosicao = posicao;
                if (grupoEncontrado == "PROCEDIMENTOS GERAIS SISTEMA MÚSCULO-ESQUELÉTICO E ARTICULAÇÕES"){
                    grupoEncontrado = "SISTEMA MÚSCULO-ESQUELÉTICO E ARTICULAÇÕES";
                }
            }
        }

        // Se encontrou um grupo, dividir a string
        if (grupoEncontrado != null) {

            String[] partes = line.split(grupoEncontrado, 2);
            setGrupo(grupoEncontrado);
            setSubgrupo(partes[0].trim());
            setCapitulo(partes[1].trim());
        } else {
            System.out.println("Nenhum grupo encontrado na string.");
        }
    }

    // Getters e setters (omitidos por brevidade, mas seria importante incluí-los)
    // Método toString para debug
    @Override
    public String toString() {
        return "Procedimento{" +
                "procedimento='" + procedimento + '\'' +
                ", rn='" + rn + '\'' +
                ", vigencia=" + vigencia +
                ", od=" + od +
                ", amb=" + amb +
                ", hco=" + hco +
                ", hso=" + hso +
                ", ref=" + ref +
                ", pac=" + pac +
                ", dut='" + dut + '\'' +
                ", subgrupo='" + subgrupo + '\'' +
                ", grupo='" + grupo + '\'' +
                ", capitulo='" + capitulo + '\'' +
                '}';
    }
}

