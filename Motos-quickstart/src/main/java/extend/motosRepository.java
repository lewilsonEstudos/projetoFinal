package extend;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.modelMotos;

@ApplicationScoped
public class motosRepository implements PanacheRepository<modelMotos>{

}
