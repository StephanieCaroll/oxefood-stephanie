package br.com.ifpe.oxefood.modelo.cupomDesconto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CupomDescontoService {
    
    @Autowired 
   private CupomDescontoRepository repository;

   @Transactional 
   public CupomDesconto save(CupomDesconto cupomDesconto) { 

       cupomDesconto.setHabilitado(Boolean.TRUE);
       return repository.save(cupomDesconto);
       
   }
   
   public List<CupomDesconto> listarTodos() {
  
    return repository.findAll();
}

public CupomDesconto obterPorID(Long id) {

    return repository.findById(id).get();
}

@Transactional
public void update(Long id, CupomDesconto CupomDescontoAlterado) {

   CupomDesconto cupomDesconto = repository.findById(id).get(); 
   cupomDesconto.setCodigoDesconto(CupomDescontoAlterado.getCodigoDesconto());
   cupomDesconto.setPercentualDesconto(CupomDescontoAlterado.getPercentualDesconto());
   cupomDesconto.setValorDesconto(CupomDescontoAlterado.getValorDesconto());
   cupomDesconto.setValorMinimoPedidoPermitido(CupomDescontoAlterado.getValorMinimoPedidoPermitido());
   cupomDesconto.setQuantidadeMaximaUso(CupomDescontoAlterado.getQuantidadeMaximaUso());
   cupomDesconto.setInicioVigencia(CupomDescontoAlterado.getInicioVigencia());
   cupomDesconto.setFimVigencia(CupomDescontoAlterado.getFimVigencia());

   repository.save(cupomDesconto); 
}

@Transactional //Toda vez que for mexer no banco utilizar o transactional
public void delete(Long id) {

    CupomDesconto cupomDesconto = repository.findById(id).get();
    cupomDesconto.setHabilitado(Boolean.FALSE);

    repository.save(cupomDesconto); //Na verdade está sendo alterado se realmente fosse deletado em vez de save seria o delete
}


}
