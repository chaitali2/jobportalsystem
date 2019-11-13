package demo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ItemDao {

    @PersistenceContext
    EntityManager entityManager;

    public String getItems() {
        Item item = entityManager.find(Item.class, 1);
        return null;
    }
    @Transactional(readOnly = false)
    public void addItem(Item item) {
        entityManager.persist(item);
    }

    @Transactional(readOnly = false)
    public  void removeItem() throws Exception {
        Item item=entityManager.find(Item.class,42l);
        entityManager.remove(item);
        throw new Exception();
    }
}
