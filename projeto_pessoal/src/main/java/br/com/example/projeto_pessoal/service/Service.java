package br.com.example.projeto_pessoal.service;

import br.com.example.projeto_pessoal.models.Livros;
import br.com.example.projeto_pessoal.models.Mensagem;
import br.com.example.projeto_pessoal.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private Repository acao;

    @Autowired
    private Mensagem mensagem;

    public static String obterDataHoraAtual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss ");
        return LocalDateTime.now().format(formatter);
    }

    public ResponseEntity<?> cadastrar(Livros livro) {
        System.out.println(obterDataHoraAtual() + "Iniciando cadastramento de livro");
        if (livro.getNome().isEmpty()) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar o nome do livro");
            mensagem.setMensagem("Informe um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        if (livro.getQtdPaginas() <= 0) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar a quantidade de páginas do livro");
            mensagem.setMensagem("Informe quantidade de páginas válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        if (livro.getNomeAutor().isEmpty()) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar o nome do autor do livro");
            mensagem.setMensagem("Informe o nome do autor");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        if (livro.getGenero().isEmpty()) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar o genero do livro");
            mensagem.setMensagem("Informe o gênero do livro");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        if (livro.getPreco() < 0) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar o preço do livro");
            mensagem.setMensagem("Informe um valor válido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        System.out.println(obterDataHoraAtual() + "Livro cadastrado com sucesso!");
        return new ResponseEntity<>(acao.save(livro), HttpStatus.CREATED);
    }

    public ResponseEntity<?> mostrar() {
        System.out.println(obterDataHoraAtual() + "Lista exibida com sucesso!");
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> filtrarQtdPaginas(Integer qtdPaginas) {
        if (qtdPaginas <= 0) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar a quantidade de páginas do livro");
            mensagem.setMensagem("Informe uma quantidade de páginas válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        System.out.println(obterDataHoraAtual() + "Lista de livros com quantidade de páginas maior que " + qtdPaginas + " exibida com sucesso!");
        return new ResponseEntity<>(acao.filtrarQtdPaginas(qtdPaginas), HttpStatus.OK);
    }

    public ResponseEntity<?> filtrarNome(String nome) {
        if (acao.qtdRetorno(nome).equals(0)) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar o nome do livro, livro não encontrado");
            mensagem.setMensagem("Livro não encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        System.out.println(obterDataHoraAtual() + "Livro de nome '" + nome + "' exibido com sucesso!");
        return new ResponseEntity<>(acao.filtrarNome(nome), HttpStatus.OK);
    }

    public ResponseEntity<?> filtrarNomeAutor(String nomeAutor) {
        if (acao.qtdLivrosPorAutor(nomeAutor).equals(0)) {
            System.out.println(obterDataHoraAtual() + "Não foi encontrado nenhum livro desse autor(a)");
            mensagem.setMensagem("Autor não encontrado");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        System.out.println(obterDataHoraAtual() + "Livros do autor(a) '" + nomeAutor + "' exibidos com sucesso!");
        return new ResponseEntity<>(acao.filtrarNomeAutor(nomeAutor), HttpStatus.OK);
    }

    public ResponseEntity<?> filtrarCodigo(Integer codigo) {
        if (acao.countByCodigo(codigo).equals(0)) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar o número do código, não existe livro com código " + codigo);
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        System.out.println(obterDataHoraAtual() + "Livro de código " + codigo + " exibido com sucesso!");
        return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
    }

    public ResponseEntity<?> ordenarQtdPaginasCresc() {
        System.out.println(obterDataHoraAtual() + "Lista de ordem crescente de páginas exibida com sucesso!");
        return new ResponseEntity<>(acao.ordenarQtdPaginasCresc(), HttpStatus.OK);
    }

    public ResponseEntity<?> ordenarQtdPaginasDesc() {
        System.out.println(obterDataHoraAtual() + "Lista de ordem decrescente de páginas exibida com sucesso!");
        return new ResponseEntity<>(acao.ordenarQtdPaginasDesc(), HttpStatus.OK);
    }

    public ResponseEntity<?> filtrarGenero(String genero) {
        if (acao.qtdGenero(genero).equals(0)) {
            System.out.println(obterDataHoraAtual() + "Não foi encontrado nenhum livro com esse gênero");
            mensagem.setMensagem("Não foi encontrado nenhum livro com esse gênero");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        System.out.println(obterDataHoraAtual() + "Livros de gênero '" + genero + "' exibidos com sucesso!");
        return new ResponseEntity<>(acao.filtrarGenero(genero), HttpStatus.OK);
    }

    public ResponseEntity<?> ordenarPrecoCresc() {
        System.out.println(obterDataHoraAtual() + "Lista de ordem crescente de preço exibida com sucesso!");
        return new ResponseEntity<>(acao.ordenarPrecoCresc(), HttpStatus.OK);
    }

    public ResponseEntity<?> ordenarPrecoDesc() {
        System.out.println(obterDataHoraAtual() + "Lista de ordem decrescente de preço exibida com sucesso!");
        return new ResponseEntity<>(acao.ordenarPrecoDesc(), HttpStatus.OK);
    }

    public ResponseEntity<?> alterar(Livros livro) {
        System.out.println(obterDataHoraAtual() + "Iniciando alteração de livro");
        if (acao.countByCodigo(livro.getCodigo()).equals(0)) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar o código do livro");
            mensagem.setMensagem("Informe um código válido");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        if (livro.getNome().isEmpty()) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar o nome do livro");
            mensagem.setMensagem("Informe um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        if (livro.getQtdPaginas() <= 0) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar a quantidade de páginas do livro");
            mensagem.setMensagem("Informe quantidade de páginas válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        if (livro.getNomeAutor().isEmpty()) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar o nome do autor do livro");
            mensagem.setMensagem("Informe o nome do autor");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        if (livro.getGenero().isEmpty()) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar o genero do livro");
            mensagem.setMensagem("Informe o gênero do livro");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        if (livro.getPreco() < 0) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar o preço do livro");
            mensagem.setMensagem("Informe um valor válido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        System.out.println(obterDataHoraAtual() + "Livro alterado com sucesso!");
        return new ResponseEntity<>(acao.save(livro), HttpStatus.OK);
    }

    public ResponseEntity<?> remover(Integer codigo) {
        System.out.println(obterDataHoraAtual() + "Iniciando remoção de livro");
        if (acao.countByCodigo(codigo) == 0) {
            System.out.println(obterDataHoraAtual() + "Erro ao informar o código do livro");
            mensagem.setMensagem("Código não identificado");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        Livros livro = acao.findByCodigo(codigo);
        acao.delete(livro);

        System.out.println(obterDataHoraAtual() + "Livro removido com sucesso!");
        mensagem.setMensagem("Livro removido com sucesso!");
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }
}
