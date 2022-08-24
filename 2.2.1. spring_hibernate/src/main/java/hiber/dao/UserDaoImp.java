package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   private SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void addUser(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   public User findUserByCar(String model, int series) {
      TypedQuery<User> query=sessionFactory.getCurrentSession()
              .createQuery("select U from User U inner join U.car as C " +
                      "with C.series = :paramSeries and C.model = :paramModel ");
      query.setParameter("paramModel", model);
      query.setParameter("paramSeries", series);
      return query.getSingleResult();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
