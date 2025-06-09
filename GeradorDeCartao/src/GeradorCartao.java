import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class GeradorCartao {

    private Random random = new Random();

    public Integer prefixo(Integer opcao) {
        if (opcao == null || opcao < 1 || opcao > 3) {
            throw new IllegalArgumentException("Operadora inválida. Use 1=Visa, 2=Mastercard, 3=Amex.");
        }

        if (opcao == 1) {
            return 4;
        }

        if (opcao == 2) {
            int min = 51;
            int max = 56;
            int numero = random.nextInt(max - min) + min;
            return numero;
        }

        if (opcao == 3) {
            int min = 34;
            int max = 38;
            int numero = random.nextInt(max - min) + min;
            return numero;
        }

        return null;
    }

    public String numeroCartao(Integer prefixo) {
        String prefixo1 = String.valueOf(prefixo);
        StringBuilder numero = new StringBuilder(prefixo1);
        int totalDigitos = 16;
        int digitosFaltando = totalDigitos - prefixo1.length() - 1;

        for (int i = 0; i < digitosFaltando; i++) {
            int digitoAleatorio = random.nextInt(10);
            numero.append(digitoAleatorio);
        }

        int digitoVerificador = calcularLuhn(numero.toString());
        numero.append(digitoVerificador);

        return numero.toString();
    }

    private int calcularLuhn(String numeroCartao) {
        int soma = 0;
        boolean multiplicarPorDois = false;

        for (int i = numeroCartao.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroCartao.charAt(i));
            if (multiplicarPorDois) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            soma += digito;
            multiplicarPorDois = !multiplicarPorDois;
        }

        int modulo = soma % 10;
        return (modulo == 0) ? 0 : 10 - modulo;
    }

    public LocalDate gerarValidade() {
        int anoAtual = LocalDate.now().getYear();
        int anoAleatorio = anoAtual + random.nextInt(6);

        int mesAleatorio = 1 + random.nextInt(12); // de 1 a 12
        int diaMaximo = LocalDate.of(anoAleatorio, mesAleatorio, 1).lengthOfMonth();
        int diaAleatorio = 1 + random.nextInt(diaMaximo);

        return LocalDate.of(anoAleatorio, mesAleatorio, diaAleatorio);
    }

    public String cvv(Integer opcao) {
        int tamanho = (opcao == 3) ? 4 : 3;
        StringBuilder numero = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            int numeroAleatorio = random.nextInt(10);
            numero.append(numeroAleatorio);
        }
        return numero.toString();
    }

    public static String formatarNumeroCartao(String numeroCartao) {
        StringBuilder formatado = new StringBuilder();
        for (int i = 0; i < numeroCartao.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                formatado.append(" ");
            }
            formatado.append(numeroCartao.charAt(i));
        }
        return formatado.toString();
    }


}
