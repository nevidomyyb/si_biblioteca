package com.pedro.service;

import com.pedro.dao.LivroDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LivroServiceTest {

    private LivroDAO livroDAOMock;
    private LivroService livroService;

    @BeforeEach
    public void setUp() {
        livroDAOMock = mock(LivroDAO.class);

        // Criamos uma instância da service com o mock manualmente
        livroService = new LivroService() {
            {
                this.livroDAO = livroDAOMock;
            }
        };
    }

    @Test
    public void testObterQuantidadeDeLocacoesPorLivro_quandoExistemLocacoes() {
        int livroId = 1;

        when(livroDAOMock.contarLocacoesPorLivro(livroId)).thenReturn(5);

        int resultado = livroService.obterQuantidadeDeLocacoesPorLivro(livroId);

        assertEquals(5, resultado);
    }

    @Test
    public void testObterQuantidadeDeLocacoesPorLivro_quandoNaoExistemLocacoes() {
        int livroId = 2;

        when(livroDAOMock.contarLocacoesPorLivro(livroId)).thenReturn(0);

        int resultado = livroService.obterQuantidadeDeLocacoesPorLivro(livroId);

        assertEquals(0, resultado);
    }
}
