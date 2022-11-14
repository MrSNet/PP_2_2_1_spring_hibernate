package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user1.setUserCar(new Car("BMW", 1));
      user2.setUserCar(new Car("Lada", 2));
      user3.setUserCar(new Car("Haval", 3));
      user4.setUserCar(new Car("Tesla", 4));
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);



//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println(user.getUserCar().toString());
         System.out.println();
      }

      User user5 = userService.findUserByCar("Haval", 3);

      System.out.println("Id = "+user5.getId());
      System.out.println("First Name = "+user5.getFirstName());
      System.out.println("Last Name = "+user5.getLastName());
      System.out.println("Email = "+user5.getEmail());
      System.out.println(user5.getUserCar().toString());
      System.out.println();

      context.close();
   }
}
