package br.com.example.projeto_pessoal.repository;

import br.com.example.projeto_pessoal.models.Livros;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<Livros, Integer>{

    @Query(value = "SELECT * FROM livros WHERE qtd_paginas >= :qtdPaginas", nativeQuery = true)
    List<Livros> filtrarQtdPaginas(Integer qtdPaginas);

    @Query(value = "SELECT * FROM livros WHERE nome LIKE %:nome%", nativeQuery = true)
    List<Livros> filtrarNome(String  nome);

    @Query(value = "SELECT count(*) FROM livros WHERE nome LIKE %:nome%", nativeQuery = true)
    Integer qtdRetorno(String nome);

    @Query(value = "SELECT * FROM livros WHERE nome_autor LIKE %:nomeAutor%", nativeQuery = true)
    List<Livros> filtrarNomeAutor(String nomeAutor);

    @Query(value = "SELECT count(*) FROM livros WHERE nome_autor LIKE %:nomeAutor%", nativeQuery = true)
    Integer qtdLivrosPorAutor(String nomeAutor);

    Integer countByCodigo(Integer codigo);

    Livros findByCodigo(Integer codigo);

    @Query(value = "SELECT * FROM livros ORDER BY qtd_paginas", nativeQuery = true)
    List<Livros> ordenarQtdPaginasCresc();

    @Query(value = "SELECT * FROM livros ORDER BY qtd_paginas desc", nativeQuery = true)
    List<Livros> ordenarQtdPaginasDesc();

    @Query(value = "SELECT * FROM livros WHERE genero = :genero", nativeQuery = true)
    List<Livros> filtrarGenero(String genero);

    @Query(value = "SELECT count(*) FROM livros WHERE genero = :genero", nativeQuery = true)
    Integer qtdGenero(String genero);

    @Query(value = "SELECT * FROM livros ORDER BY preco", nativeQuery = true)
    List<Livros> ordenarPrecoCresc();

    @Query(value = "SELECT * FROM livros ORDER BY preco desc", nativeQuery = true)
    List<Livros> ordenarPrecoDesc();
}
