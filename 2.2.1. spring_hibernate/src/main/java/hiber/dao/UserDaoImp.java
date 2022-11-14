package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public User findUserByCar(String model, int series) {
      String hqlCar = "from Car where model = :paramModel and series = :paramSeries";
      String hqlUser = "from User where id = :paramId";
      TypedQuery<Car> queryCar=sessionFactory.getCurrentSession().createQuery(hqlCar);
      queryCar.setParameter("paramModel", model);
      queryCar.setParameter("paramSeries", series);

      TypedQuery<User> queryUser=sessionFactory.getCurrentSession().createQuery(hqlUser);
      queryUser.setParameter("paramId", queryCar.getSingleResult().getId());
      return queryUser.getSingleResult();
   }

}
