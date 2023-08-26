package br.com.bank.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.quality.Strictness;

import br.com.coruja.model.Banco;
import br.com.coruja.model.Conta;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(Lifecycle.PER_CLASS)
class SistemaBancarioTest {

    //@InjectMocks
    //private SistemaBancario sistemaBancario;
    //@Mock
    //private Bacen bacen;

    private Banco banco;

    @BeforeAll
    public void setUp() { banco = new Banco("banco1"); }

    @Test
    public void deve_adicionar_conta() {
        
        Conta conta0 = new Conta("00000000000");
        Conta conta1 = new Conta("11111111111");
        Conta conta2 = new Conta("22222222222");
                
        banco.adicionarConta(conta0);
        banco.adicionarConta(conta1);
        banco.adicionarConta(conta2);

        assertEquals(3, banco.getTotalDeContas());
    }

    @Test
    public void deve_pesquisar_conta_por_cpf_existente() {
        banco.adicionarConta(new Conta("12345678910"));
        Optional<Conta> conta = banco.pesquisarContaDoCliente("12345678910");
        assertTrue(conta.isPresent());
        assertEquals("12345678910", conta.get().getCpf());
    }

    @Test
    public void nao_deve_pesquisar_conta_por_cpf_se_cliente_nao_existe() {
        banco.adicionarConta(new Conta("12345678910"));
        Optional<Conta> conta = banco.pesquisarContaDoCliente("9876543211");
        assertFalse(conta.isPresent());
    }

    @Test
    public void deve_pesquisar_conta() {
        Conta conta1 = new Conta("33333333333");
        banco.adicionarConta(conta1);
        assertEquals(Optional.of(conta1), banco.pesquisarContaDoCliente(conta1.getCpf()));
    }

    @Test
    public void deve_listar_contas_alta_renda() {
        Conta conta1 = new Conta("11111111111");
        conta1.addSaldo(10000);

        Conta conta2 = new Conta("22222222222");
        conta2.addSaldo(9999);

        banco.adicionarConta(conta1);
        banco.adicionarConta(conta2);

        List<Conta> contasAltaRenda = banco.listarContasAltaRenda();
       
        assertEquals(1, contasAltaRenda.size());
    }

}