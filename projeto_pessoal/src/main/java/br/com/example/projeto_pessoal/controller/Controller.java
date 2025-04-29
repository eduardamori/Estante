package br.com.example.projeto_pessoal.controller;

import br.com.example.projeto_pessoal.models.Livros;
import br.com.example.projeto_pessoal.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.example.projeto_pessoal.service.Service.obterDataHoraAtual;

@RestController
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    private Service service;

    @PostMapping("/")
    public ResponseEntity<?> cadastrar(@RequestBody Livros livro) {
        System.out.println(obterDataHoraAtual() + "Requisição para cadastro do livro recebida");
        return service.cadastrar(livro);
    }

    @GetMapping("/")
    public ResponseEntity<?> mostrar() {
        System.out.println(obterDataHoraAtual() + "Requisição para listar os livros da estante recebida");
        return service.mostrar();
    }

    @GetMapping("qtdPaginas/{qtdPaginas}")
    public ResponseEntity<?> filtrarQtdPaginas(@PathVariable Integer qtdPaginas) {
        System.out.println(obterDataHoraAtual() + "Requisição para exibir livros com quantidade de páginas maior que " + qtdPaginas + " recebida");
        return service.filtrarQtdPaginas(qtdPaginas);
    }

    @GetMapping("/filtrarNome/{nome}")
    public ResponseEntity<?> filtrarNome(@PathVariable String nome) {
        System.out.println(String.format(obterDataHoraAtual() + "Requisição para mostrar o livro de nome '%s' recebida", nome));
        return service.filtrarNome(nome);
    }

    @GetMapping("/filtrarNomeAutor/{nomeAutor}")
    public ResponseEntity<?> filtrarNomeAutor(@PathVariable String nomeAutor) {
        System.out.println(String.format(obterDataHoraAtual() + "Requisição para mostrar todos os livro do autor(a) '%s' recebida", nomeAutor));
        return service.filtrarNomeAutor(nomeAutor);
    }

    @GetMapping("/filtrarCodigo/{codigo}")
    public ResponseEntity<?> filtrarCodigo(@PathVariable Integer codigo) {
        System.out.println(String.format(obterDataHoraAtual() + "Requisição para mostrar o livro de código '%d' recebida", codigo));
        return service.filtrarCodigo(codigo);
    }

    @GetMapping("/ordenarQtdPaginasCresc")
    public ResponseEntity<?> ordenarQtdPaginasCresc() {
        System.out.println(obterDataHoraAtual() + "Requisição para mostrar os livros em ordem crescente, de acordo com a quantidade de páginas, recebida");
        return service.ordenarQtdPaginasCresc();
    }

    @GetMapping("/ordenarQtdPaginasDesc")
    public ResponseEntity<?> ordenarQtdPaginasDesc() {
        System.out.println(obterDataHoraAtual() + "Requisição para mostrar os livros em ordem decrescente, de acordo com a quantidade de páginas, recebida");
        return service.ordenarQtdPaginasDesc();
    }

    @GetMapping("/filtrarGenero/{genero}")
    public ResponseEntity<?> filtrarGenero(@PathVariable String genero) {
        System.out.println(String.format(obterDataHoraAtual() + "Requisição para mostrar todos os livro de gênero '%s' recebida", genero));
        return service.filtrarGenero(genero);
    }

    @GetMapping("/ordenarPrecoCresc")
    public ResponseEntity<?> ordenarPrecoCresc() {
        System.out.println(obterDataHoraAtual() + "Requisição para mostrar os livros em ordem crescente, de acordo com o preço, recebida");
        return service.ordenarPrecoCresc();
    }

    @GetMapping("/ordenarPrecoDesc")
    public ResponseEntity<?> ordenarPrecoDesc() {
        System.out.println(obterDataHoraAtual() + "Requisição para mostrar os livros em ordem decrescente, de acordo com o preço, recebida");
        return service.ordenarPrecoDesc();
    }

    @PutMapping("/")
    public ResponseEntity<?> alterar(@RequestBody Livros livro) {
        System.out.println(obterDataHoraAtual() + "Requisição para alterar o livro de código " + livro.getCodigo() + " recebida");
        return service.alterar(livro);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> remover(@PathVariable Integer codigo) {
        System.out.println(obterDataHoraAtual() + "Requisição para remover o livro de código " + codigo + " recebida");
        return service.remover(codigo);
    }
}
