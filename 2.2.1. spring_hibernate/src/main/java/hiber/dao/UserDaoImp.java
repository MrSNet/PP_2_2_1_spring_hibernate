package hiber.dao;

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
      String hqlUser = """
               select user
               from User user
               inner join user.userCar car where car.model = :paramModel and car.series = :paramSeries""";

      TypedQuery<User> queryUser=sessionFactory.getCurrentSession().createQuery(hqlUser);
      queryUser.setParameter("paramModel", model);
      queryUser.setParameter("paramSeries", series);

      return queryUser.getSingleResult();

   }

}
