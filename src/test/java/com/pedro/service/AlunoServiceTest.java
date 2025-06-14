package com.pedro.service;


import com.pedro.dao.AlunoDAO;
import com.pedro.models.Aluno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AlunoServiceTest {

    private AlunoDAO alunoDAOMock;
    private AlunoService alunoService;

    @BeforeEach
    public void setup() {
        alunoDAOMock = mock(AlunoDAO.class);
        alunoService = new AlunoService() {
            {
                this.alunoDAO = alunoDAOMock; // Injeção do mock
            }
        };
    }

    @Test
    public void testConsultarAlunoPorLocacaoId_quandoAlunoExiste() {
        Aluno alunoMock = new Aluno();
        alunoMock.setNome("Carlos Adriano");
        alunoMock.setCpf("123.456.789-00");

        when(alunoDAOMock.obterAlunoPorIdLocacao(10)).thenReturn(alunoMock);

        Aluno alunoRetornado = alunoService.consultarAlunoPorLocacaoId(10);

        assertNotNull(alunoRetornado);
        assertEquals("Carlos Adriano", alunoRetornado.getNome());
        assertEquals("123.456.789-00", alunoRetornado.getCpf());
    }

    @Test
    public void testConsultarAlunoPorLocacaoId_quandoAlunoNaoExiste() {
        when(alunoDAOMock.obterAlunoPorIdLocacao(99)).thenReturn(null);

        Aluno alunoRetornado = alunoService.consultarAlunoPorLocacaoId(99);

        assertNull(alunoRetornado);
    }
}
