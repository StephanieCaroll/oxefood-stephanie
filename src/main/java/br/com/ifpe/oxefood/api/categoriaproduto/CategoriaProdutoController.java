package br.com.ifpe.oxefood.api.categoriaproduto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ifpe.oxefood.modelo.categoriaproduto.CategoriaProduto;
import br.com.ifpe.oxefood.modelo.categoriaproduto.CategoriaProdutoService;
 

public class CategoriaProdutoController {
    
 @Autowired //Instanciar no cliente service
   private CategoriaProdutoService categoriaProdutoService;

   @PostMapping //Especificar que essa função vai receber requisições do tipo Post
   public ResponseEntity<CategoriaProduto> save(@RequestBody CategoriaProdutoRequest request) {

       CategoriaProduto categoriaProduto = categoriaProdutoService.save(request.build());
       return new ResponseEntity<CategoriaProduto>(categoriaProduto, HttpStatus.CREATED);
   }

   @GetMapping
    public List<CategoriaProduto> listarTodos() {
        return categoriaProdutoService.listarTodos();
    }

    @GetMapping("/{id}")
    public CategoriaProduto obterPorID(@PathVariable Long id) {
        return categoriaProdutoService.obterPorID(id);
    }

    @PutMapping("/{id}") 
    public ResponseEntity<CategoriaProduto> update(@PathVariable("id") Long id, @RequestBody CategoriaProdutoRequest request) { //Recebe o id e os dados do cliente

       categoriaProdutoService.update(id, request.build()); //Objeto preenchido sera enviado para o service
       return ResponseEntity.ok().build();
 }

   @DeleteMapping("/{id}") // passar o id do cliente que eu quero remover
   public ResponseEntity<Void> delete(@PathVariable Long id) { //repassar o id para a função delete

       categoriaProdutoService.delete(id);
       return ResponseEntity.ok().build();
   }

}
