package projectpizza1_server.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectpizza1_server.demo.model.Pizza;
import projectpizza1_server.demo.repository.RepositoryPizza;
import projectpizza1_server.demo.serviceImpl.service.ServicePizza;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SeviceImlpPizza implements ServicePizza {

    @Autowired
    private RepositoryPizza repositoryPizza;

    @Override
    public List<Pizza> findAll() {
        return repositoryPizza.findAll();
    }

    @Override
    public Pizza findById(Long id) {
        return repositoryPizza.findById(id).get();
    }

    @Override
    public Pizza save(Pizza pizza) {
        return repositoryPizza.save(pizza);
    }

    @Override
    public void delete(Pizza pizza) {repositoryPizza.delete(pizza); }
}
