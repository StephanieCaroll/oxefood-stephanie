package br.com.ifpe.oxefood.modelo.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service //Faz a classe ser um serviço
public class ClienteService {

    @Autowired //Instanciar um objeto de forma que eu possar usar os metodos e atributos dessa classe
   private ClienteRepository repository;

   @Transactional //Se um falhar as outras seram desfeitas e não seram gravadas no banco 
   public Cliente save(Cliente cliente) { //Recebe o objeto cliente que ele vai salvar no banco

       cliente.setHabilitado(Boolean.TRUE);
       return repository.save(cliente);
       
       //Ou voce pode fazer
       //Cliente c = repository.save(cliente);
       //return c;
   }


}
