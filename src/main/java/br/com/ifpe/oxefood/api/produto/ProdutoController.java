package br.com.ifpe.oxefood.api.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.categoriaproduto.CategoriaProdutoService;
import br.com.ifpe.oxefood.modelo.produto.Produto;
import br.com.ifpe.oxefood.modelo.produto.ProdutoService;
import jakarta.validation.Valid;


@RestController //Faz a classe ser um controller
@RequestMapping("/api/produto")
@CrossOrigin //Utilizada para o controller receber requisições do React
public class ProdutoController {

   @Autowired //Instanciar no cliente service
   private ProdutoService produtoService;

   @Autowired
   private CategoriaProdutoService categoriaProdutoService;

   @PostMapping //Especificar que essa função vai receber requisições do tipo Post
   public ResponseEntity<Produto> save(@RequestBody @Valid ProdutoRequest request) {

      Produto produtoNovo = request.build();
       produtoNovo.setCategoria(categoriaProdutoService.obterPorID(request.getIdCategoria()));
       Produto produto = produtoService.save(produtoNovo);

       return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
   }

  @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto obterPorID(@PathVariable Long id) {
        return produtoService.obterPorID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody ProdutoRequest request) {

       produtoService.update(id, request.build());
       return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

       produtoService.delete(id);
       return ResponseEntity.ok().build();
   }


}
