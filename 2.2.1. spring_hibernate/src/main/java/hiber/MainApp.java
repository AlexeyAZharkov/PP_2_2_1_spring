package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.CarServiceImp;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      Car carVesta = new Car("Vesta", 33);
      Car carLogan = new Car("Logan", 33);
      Car carSX4 = new Car("SX4", 44);

      carService.add(carVesta);
      carService.add(carLogan);
      carService.add(carSX4);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", carVesta));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", carLogan));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", carSX4));

      List<User> users = userService.listUsers();
      System.out.println("Список введенных пользователей:");
      for (User user : users) {
         System.out.println(user);
      }
      System.out.println();
      System.out.println("Поиск пользователя по машине модели 'Logan' и серии '33':");
      System.out.println(userService.find("Logan", 33));

      context.close();
   }
}
