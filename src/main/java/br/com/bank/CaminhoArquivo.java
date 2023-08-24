package br.com.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CaminhoArquivo {

    private Path diretorio;

    private Path arquivo;

    private CaminhoArquivo(Path diretorio, Path arquivo) {
        super();
        this.diretorio = diretorio;
        this.arquivo = arquivo;
    }

    public Path getDiretorio() {
        return diretorio;
    }

    public Path getArquivo() {
        return arquivo;
    }

    public static CaminhoArquivo getInstance(Integer id) {
        
        final String diretorioBase = "/tmp/";
        final String barra = "/";
        
        Path diretorio;
        Path arquivo;

        if (id != Integer.MIN_VALUE) {
            Long subDiretorio;
            subDiretorio =  Math.round(Math.ceil((double)id / 1000));
            diretorio = Paths.get(diretorioBase, subDiretorio.toString(), barra);
            arquivo = Paths.get(diretorioBase, subDiretorio.toString(), barra, id.toString());
        }
        else {
            Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
            String strDate = dateFormat.format(date);  
            diretorio = Paths.get(diretorioBase, strDate, barra);
            arquivo = Paths.get(diretorioBase, strDate, barra, strDate);
        }

        return new CaminhoArquivo(diretorio, arquivo);
    }

    public static CaminhoArquivo getInstance() {
        return getInstance(Integer.MIN_VALUE);
    }
}