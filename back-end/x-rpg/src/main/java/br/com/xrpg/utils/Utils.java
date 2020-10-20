package br.com.xrpg.utils;

//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j //Log.Debug
public class Utils {

    public static String isFormataCEP(String cep) {
        cep = cep.substring(0, 5) + "-" + cep.substring(5, 8);
        return cep;
    }

    public static String validaCPF(String cpf) {
        if (cpf == null)
            return null;
        if (cpf.length() > 11)
            return null;
        cpf = StringUtils.leftPad(cpf, 11, '0');
        if (cpf.equals("00000000000"))
            return null;
        if (cpf.equals("11111111111"))
            return null;
        if (cpf.equals("22222222222"))
            return null;
        if (cpf.equals("33333333333"))
            return null;
        if (cpf.equals("44444444444"))
            return null;
        if (cpf.equals("55555555555"))
            return null;
        if (cpf.equals("66666666666"))
            return null;
        if (cpf.equals("77777777777"))
            return null;
        if (cpf.equals("88888888888"))
            return null;
        if (cpf.equals("99999999999"))
            return null;
        int soma = 0;
        for (int i = 0; i < 9; i++)
            soma += (10 - i) * (cpf.charAt(i) - '0');
        soma = 11 - (soma % 11);
        if (soma > 9)
            soma = 0;
        if (soma == (cpf.charAt(9) - '0')) {
            soma = 0;
            for (int i = 0; i < 10; i++)
                soma += (11 - i) * (cpf.charAt(i) - '0');
            soma = 11 - (soma % 11);
            if (soma > 9)
                soma = 0;
            if (soma == (cpf.charAt(10) - '0')) {
                return cpf;
            }
        }
        return null;
    }

    public static String apenasNumeros(String string) {
        return string.replaceAll("[^0-9]", "");
    }

    public static String validaEstado(String uf) {
        switch (uf) {
            case "AC":
                return uf;
            case "AL":
                return uf;
            case "AP":
                return uf;
            case "AM":
                return uf;
            case "BA":
                return uf;
            case "CE":
                return uf;
            case "DF":
                return uf;
            case "ES":
                return uf;
            case "GO":
                return uf;
            case "MA":
                return uf;
            case "MT":
                return uf;
            case "MS":
                return uf;
            case "MG":
                return uf;
            case "PA":
                return uf;
            case "PB":
                return uf;
            case "PR":
                return uf;
            case "PE":
                return uf;
            case "PI":
                return uf;
            case "RJ":
                return uf;
            case "RN":
                return uf;
            case "RS":
                return uf;
            case "RO":
                return uf;
            case "RR":
                return uf;
            case "SC":
                return uf;
            case "SP":
                return uf;
            case "SE":
                return uf;
            case "TO":
                return uf;
            default:
                return null;
        }
    }

    public static String getUFEstado(String uf) {
        switch (uf) {
            case "ACRE":
                return "AC";
            case "ALAGOAS":
                return "AL";
            case "AMAPA":
                return "AP";
            case "AMAZONAS":
                return "AM";
            case "BAHIA":
                return "BA";
            case "CEARA":
                return "CE";
            case "DISTRITO FEDERAL":
                return "DF";
            case "ESPIRITO SANTO":
                return "ES";
            case "GOIÁS 01":
                return "GO";
            case "GOIÁS 02":
                return "GO";
            case "GOIÁS 03":
                return "GO";
            case "GOIÁS 04":
                return "GO";
            case "GOIÁS - VALPARAISO":
                return "GO";
            case "GOIÁS - LUZIANIA":
                return "GO";
            case "MARANHÃO":
                return "MA";
            case "MATO GROSSO":
                return "MT";
            case "MATO GROSSO DO SUL":
                return "MS";
            case "MINAS GERAIS":
                return "MG";
            case "PARÁ":
                return "PA";
            case "PARAÍBA":
                return "PB";
            case "PARANÁ":
                return "PR";
            case "PERNAMBUCO":
                return "PE";
            case "PIAUI":
                return "PI";
            case "RIO DE JANEIRO":
                return "RJ";
            case "RIO GRANDE DO NORTE":
                return "RN";
            case "RIO GRANDE DO SUL":
                return "RS";
            case "RONDONIA":
                return "RO";
            case "RORAIMA":
                return "RR";
            case "SANTA CATARINA":
                return "SC";
            case "SÃO PAULO":
                return "SP";
            case "SERGIPE":
                return "SE";
            case "TOCANTINS":
                return "TO";
            default:
                return null;
        }
    }

    public static String getData() {
        LocalDateTime ldt = LocalDateTime.now();
        return ldt.toString();
    }

    public static Date getDataAtual() {
        Calendar calendar = GregorianCalendar.getInstance();
        return calendar.getTime();
    }

    public static Boolean isEmailValido(String email) {
        Pattern exclude = Pattern.compile("[^@\\-\\.\\w]|^[_@\\.\\-]|[\\._\\-]{2}|[@\\.]{2}|(@)[^@]*@");
        Pattern check = Pattern.compile("@[\\w\\-]+\\.");
        Pattern checkend = Pattern.compile("\\.[a-zA-Z]{2,}$");
        if (exclude.matcher(email).find() == true
                || check.matcher(email).find() == false
                || checkend.matcher(email).find() == false) {
            return false;
        } else {
            return true;
        }
    }

    public static String isFormataCpf(String cpf) {
        try {
            MaskFormatter mask = new MaskFormatter("###.###.###-##");
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(StringUtils.leftPad(cpf,11,"0"));
        } catch (ParseException ex) {
            log.error("Erro ao transformar cpf: "+cpf);
            return "";
        }
    }


    //METODOS CRIP
    public static String criptografarString(String value) {

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encoded = encoder.encode(value.getBytes());

        return new String(encoded);
    }

    public static String descriptografarString(String value) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(value);

        return new String(decoded);
    }

}
