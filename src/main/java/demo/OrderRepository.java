package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
//@Transactional
public class OrderRepository {
    @Autowired
    UserRepository userRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveorderdetail() {
        Order order = new Order("brush1111", 2);
        entityManager.persist(order);
        try {
            userRepository.saveuserorderdetail();
        } catch (Exception e) {
            System.out.println("error===" + e.getMessage());
        }
    }
}
