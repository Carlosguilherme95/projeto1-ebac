package carlosguilhermeatividade;
import carlosguilhermeatividade.dao.ClienteDao;
import carlosguilhermeatividade.dao.IclienteDao;
import carlosguilhermeatividade.domain.Cliente;
import javax.swing.*;
import java.util.Collection;

public class app {
    private static IclienteDao iclienteDao;

    public static void main(String args[]) {
        iclienteDao = new ClienteDao();

        int opcao;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração, 5 para sair",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE));

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    consultarCliente();
                    break;
                case 3:
                    excluirCliente();
                    break;
                case 4:
                    alterarCliente();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saindo do programa...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static void cadastrarCliente() {
        String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
        Long cpf = Long.parseLong(JOptionPane.showInputDialog("Digite o CPF do cliente:"));
        Long tel = Long.parseLong(JOptionPane.showInputDialog("Digite o tel do cliente:"));
        String end = JOptionPane.showInputDialog("Digite o end do cliente:");
        Integer numero = Integer.parseInt(JOptionPane.showInputDialog("Digite o número do cliente:"));
        String cidade = JOptionPane.showInputDialog("Digite a cidade do cliente:");
        String estado = JOptionPane.showInputDialog("Digite o estado do cliente:");

        Cliente cliente = new Cliente(nome, cpf, tel, end, numero, cidade, estado);

        if (iclienteDao.cadastrar(cliente)) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "CPF já cadastrado. Não é possível cadastrar o cliente.");
        }
    }

    private static void consultarCliente() {
        Long cpf = Long.parseLong(JOptionPane.showInputDialog("Digite o CPF do cliente para consulta:"));

        Cliente cliente = iclienteDao.consultar(cpf);

        if (cliente != null) {
            JOptionPane.showMessageDialog(null, "Cliente encontrado:\n" + cliente);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
        }
    }

    private static void excluirCliente() {
        Long cpf = Long.parseLong(JOptionPane.showInputDialog("Digite o CPF do cliente para exclusão:"));

        iclienteDao.excluir(cpf);
        JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso (se existir).");
    }

    private static void alterarCliente() {
        Long cpf = Long.parseLong(JOptionPane.showInputDialog("Digite o CPF do cliente para alteração:"));

        Cliente cliente = iclienteDao.consultar(cpf);

        if (cliente != null) {
            String novoNome = JOptionPane.showInputDialog("Digite o novo nome do cliente:");
            cliente.setNome(novoNome);

            iclienteDao.alterar(cliente);
            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
        }
    }
}