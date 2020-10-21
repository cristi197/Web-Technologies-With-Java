package springcontextexample.ex3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springcontextexample.ex3.domain.Cat;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        Cat cat = new Cat(2,"female");
        Cat anotherCat = new Cat(3,"male");

        context.registerBean("cat1",Cat.class, () -> cat);
        context.registerBean("cat2",Cat.class, () -> anotherCat);

        context.refresh();

        Cat cat1 = context.getBean("cat1",Cat.class);
        Cat cat2 = context.getBean("cat2",Cat.class);

        System.out.println(cat1.getAge()+ " " + cat1.getGender());
        System.out.println(cat2.getAge()+ " " + cat2.getGender());
    }
}
