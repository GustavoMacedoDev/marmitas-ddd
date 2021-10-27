package br.com.macedo.sistemas;

import br.com.macedo.sistemas.domain.aggregate.Categoria;
import br.com.macedo.sistemas.domain.repository.CategoriaRepository;
import br.com.macedo.sistemas.service.implementation.CategoriaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class CategoriaServiceTest {

    @TestConfiguration
    static class CategoriaServiceTestConfiguration {

        @Bean
        public CategoriaServiceImpl categoriaService() {
           return new CategoriaServiceImpl();
        }
    }

    @Autowired
    public CategoriaServiceImpl categoriaService;

    @MockBean
    CategoriaRepository categoriaRepository;

    @Before
    public void setup() {
        Categoria categoria = new Categoria(1, "TORTA");

        //CategoriaService mock = Mockito.mock(CategoriaService.class);

        Mockito.when(categoriaRepository.findById(categoria.getId()))
                .thenReturn(Optional.of(categoria));
    }

    @Test
    public void buscaCategoriaTest() {

        Integer idCategoriaBuscado = 1;

        Categoria categoriaRecebido = categoriaService.find(idCategoriaBuscado);


        Assertions.assertEquals(categoriaRecebido.getId(), idCategoriaBuscado);
    }
}
