import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1> Validador de datas</h1>
 * Projeto que valida datas:<br>
 * no padrão brasileiro: DD/MM/YYYY<br>
 * e no padrão internacional: YYYY/MM/DD.
 * <p>
 * <b>Note:</b> Desenvolvido na linguagem Java.
 *
 * @author  Marcos Ferreira Shirafuchi
 * @version 1.0
 * @since   30/10/2024
 */
public class ValidadorDeData {
    // Regex para o padrão brasileiro DD/MM/AAAA
    private static final String PADRAO_BRASILEIRO = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$|^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$|^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.(\\d{4})$";

    // Regex para o padrão internacional AAAA-MM-DD
    private static final String PADRAO_INTERNACIONAL = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$|^(\\d{4})/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$|^(\\d{4})\\.(0[1-9]|1[0-9]|3[01])\\.(0[1-9]|1[0-2])$";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite uma data: ");
        String data = scanner.nextLine();

        if (isDataValida(data)) {
            System.out.println("A data é válida.");
        } else {
            System.out.println("A data é inválida.");
        }

        scanner.close();
    }

    private static boolean isDataValida(String data) {
        return isDataBrasileiraValida(data) || isDataInternacionalValida(data);
    }

    private static boolean isDataBrasileiraValida(String data) {
        Pattern pattern = Pattern.compile(PADRAO_BRASILEIRO);
        Matcher matcher = pattern.matcher(data);

        if (matcher.matches()) {
            // Extraindo o dia, mês e ano para validar
            String[] partes = data.split("[-/.]");
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int ano = Integer.parseInt(partes[2]);

            return validarData(dia, mes, ano);
        }
        return false;
    }

    private static boolean isDataInternacionalValida(String data) {
        Pattern pattern = Pattern.compile(PADRAO_INTERNACIONAL);
        Matcher matcher = pattern.matcher(data);

        if (matcher.matches()) {
            // Extraindo o ano, mês e dia para validar
            String[] partes = data.split("[-/.]");
            int ano = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int dia = Integer.parseInt(partes[2]);

            return validarData(dia, mes, ano);
        }
        return false;
    }

    private static boolean validarData(int dia, int mes, int ano) {
        if (ano < 1 || mes < 1 || mes > 12 || dia < 1 || dia > 31) {
            return false;
        }

        // Verificando os dias de cada mês
        switch (mes) {
            case 4: case 6: case 9: case 11:
                return dia <= 30;
            case 2:
                return dia <= (isAnoBissexto(ano) ? 29 : 28);
            default:
                return true;
        }
    }

    private static boolean isAnoBissexto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }
}
