package br.com.bank.service;

import br.com.bank.CaminhoArquivo;
import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;
import br.com.bank.model.Conta;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SistemaBancarioTest {

    //@InjectMocks
    //private SistemaBancario sistemaBancario;
    //@Mock
    //private Bacen bacen;

    @Test
    public void deve_adicionar_conta() {
        Banco banco1 = new Banco("banco1");

        Conta conta0 = new Conta("00000000000");
        Conta conta1 = new Conta("11111111111");
        Conta conta2 = new Conta("22222222222");
                
        banco1.adicionarConta(conta0);
        banco1.adicionarConta(conta1);
        banco1.adicionarConta(conta2);

        assertEquals(conta0, banco1.pesquisarContaDoCliente(conta0.getCpf()));
        assertEquals(conta1, banco1.pesquisarContaDoCliente(conta1.getCpf()));
        assertEquals(conta2, banco1.pesquisarContaDoCliente(conta2.getCpf()));
    }

    @Test
    public void deve_pesquisar_conta() {
        Banco banco1 = new Banco("banco1");
        Conta conta1 = new Conta("33333333333");
        banco1.adicionarConta(conta1);
        assertEquals(conta1, banco1.pesquisarContaDoCliente(conta1.getCpf()));
    }

    @Test
    public void deve_listar_contas_alta_renda() {
        Banco banco1 = new Banco("banco1");
        Conta conta1 = new Conta("11111111111");
        conta1.addSaldo(10000);

        Conta conta2 = new Conta("22222222222");
        conta2.addSaldo(99999);

        banco1.adicionarConta(conta1);
        banco1.adicionarConta(conta2);
        List<Conta> contas1 = new ArrayList<>();
        contas1.add(conta1);
       
        assertIterableEquals(contas1, banco1.listarContasAltaRenda());

        contas1.add(conta2);
        
        assertIterableEquals(contas1, banco1.listarContasAltaRenda());
    }

}