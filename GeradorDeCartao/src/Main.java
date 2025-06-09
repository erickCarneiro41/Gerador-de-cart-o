public class Main {
    public static void main(String[] args) {
        GeradorCartao gerador = new GeradorCartao();

        for (int opcao = 1; opcao <= 3; opcao++) {
            try {
                Integer prefixo = gerador.prefixo(opcao);
                String numeroCartao = gerador.numeroCartao(prefixo);
                String numeroFormatado = GeradorCartao.formatarNumeroCartao(numeroCartao);
                String validade = gerador.gerarValidade().toString();
                String cvv = gerador.cvv(opcao);

                System.out.println("");
                System.out.println("Operadora: " + (opcao == 1 ? "Visa" : opcao == 2 ? "Mastercard" : "American Express"));
                System.out.println("Número do Cartão: " + numeroFormatado);
                System.out.println("Validade: " + validade);
                System.out.println("CVV: " + cvv);
                System.out.println("------------------------------");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
