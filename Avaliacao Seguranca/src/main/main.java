package main;

import dao.PacienteDao;
import dao.SenhaDao;
import dao.ValoresReferenciaDao;
import dao.CalcioDao;
import entidades.Paciente;
import entidades.Senha;
import entidades.ValoresReferencia;
import entidades.calcio;
import utils.Crypto;
import utils.Hashing;

import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Cadastro: ");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.println();

        Paciente usuario = new Paciente(nome, cpf, login, senha);
        PacienteDao pacienteDao = new PacienteDao();
        pacienteDao.adiciona(usuario);
        System.out.println("Conta adicionada com sucesso!");

        String userSecretKey = Crypto.generateKey();
        System.out.println("userSecretKey: " + userSecretKey);

        String chaveUserCrypto = Crypto.encrypt(userSecretKey, senha);
        SenhaDao senhaDaoCadastra = new SenhaDao();
        Senha senhaObj = new Senha(chaveUserCrypto);
        senhaDaoCadastra.cadastrar(senhaObj);
        System.out.println("Chave secreta criptografada: " + senhaObj.getKey());

        int resultadoDoExame = 50;
        System.out.println("Resultado do exame: " + resultadoDoExame);

        String resultadoCriptografado = Crypto.encrypt(Integer.toString(resultadoDoExame), userSecretKey);
        System.out.println("Resultado do exame criptografado: " + resultadoCriptografado);

        calcio calcio = new calcio(null, resultadoCriptografado);
        CalcioDao calcioDao = new CalcioDao();
        calcioDao.adiciona(calcio);

        boolean logado = false;
        while (!logado) {
            System.out.print("Login: ");
            login = sc.nextLine();
            System.out.print("Senha: ");
            senha = sc.nextLine();
            logado = logarPaciente(login, senha);
            if (!logado) {
                System.out.println("Login ou senha incorretos. Tente novamente.");
            }
        }

        System.out.println();
        System.out.println("Usuario logado!");
        sc.close();
    }

    public static boolean logarPaciente(String login, String senha) {
        PacienteDao pacienteDao = new PacienteDao();
        Paciente paciente = pacienteDao.buscaPorLogin(login);
        if (paciente != null) {
            String senhaHash = Hashing.hashSha128(senha);
            return senhaHash.equals(paciente.getSenha());
        }
        return false;
    }
}
