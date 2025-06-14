package com.pedro.service;

import com.pedro.dao.ProfessorDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorServiceTest {

    private ProfessorDAO professorDAOMock;
    private ProfessorService professorService;

    @BeforeEach
    void setUp() {
        professorDAOMock = Mockito.mock(ProfessorDAO.class);
        professorService = new ProfessorService();

        // Injetar o mock manualmente (caso o DAO não esteja exposto no construtor)
        // Aqui usamos reflection para alterar o campo privado
        try {
            java.lang.reflect.Field field = ProfessorService.class.getDeclaredField("professorDAO");
            field.setAccessible(true);
            field.set(professorService, professorDAOMock);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testObterProfessorComMaisLocacoes_quandoExisteProfessor() {
        // Arrange
        String resultadoEsperado = "Prof. Maria Silva - 8 locações";
        Mockito.when(professorDAOMock.buscarProfessorComMaisLocacoes())
                .thenReturn(resultadoEsperado);

        // Act
        String resultado = professorService.obterProfessorComMaisLocacoes();

        // Assert
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    void testObterProfessorComMaisLocacoes_quandoNaoExisteProfessor() {
        // Arrange
        Mockito.when(professorDAOMock.buscarProfessorComMaisLocacoes())
                .thenReturn("");

        // Act
        String resultado = professorService.obterProfessorComMaisLocacoes();

        // Assert
        assertEquals("[!] Nenhum professor encontrado com locações.", resultado);
    }

    @Test
    void testObterProfessorComMaisLocacoes_quandoDAO_retornaNull() {
        // Arrange
        Mockito.when(professorDAOMock.buscarProfessorComMaisLocacoes())
                .thenReturn(null);

        // Act
        String resultado = professorService.obterProfessorComMaisLocacoes();

        // Assert
        assertEquals("[!] Nenhum professor encontrado com locações.", resultado);
    }
}
