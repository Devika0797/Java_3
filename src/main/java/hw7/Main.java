package hw7;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {


    // К каждому тесту необходимо также добавить приоритеты (int числа от 1 до 10),
    // в соответствии с которыми будет выбираться порядок их выполнения, если приоритет
    // одинаковый, то порядок не имеет значения.

    public static void main(String[] args) {
        start(ClassTest.class);
    }

    // Для этого у него должен быть статический метод start(), которому в качестве
    // параметра передается или объект типа Class, или имя класса.
    public static void start(Class clazz) {

        Method[] declaredMethods = clazz.getDeclaredMethods();

        // Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном
        // экземпляре, иначе необходимо бросить RuntimeException при запуске «тестирования».
        int beforeSuite = 0;
        int afterSuite = 0;

        for(Method method : declaredMethods) {
            if(method.getAnnotation(BeforeSuite.class) != null)
                beforeSuite++;
            if(method.getAnnotation(AfterSuite.class) != null)
                afterSuite++;
        }
        if(beforeSuite > 1 | afterSuite > 1)
            throw new RuntimeException();


        // расстановка приоритетов
        List<Method> sortMethods = new ArrayList<>();
        Method methodBS = null;
        Method methodAS = null;

        for(Method method : declaredMethods) {
            if(method.getAnnotation(BeforeSuite.class) != null)
                methodBS = method;
            if(method.getAnnotation(Test.class) != null)
                sortMethods.add(method);
            if(method.getAnnotation(AfterSuite.class) != null)
                methodAS = method;
        }
        sortMethods.sort((meth1, meth2) -> {
            return meth2.getAnnotation(Test.class).priority() - meth1.getAnnotation(Test.class).priority();
        });
        if(methodBS != null) sortMethods.add(0,methodBS);
        if(methodAS != null) sortMethods.add(methodAS);



        // вызываем методы
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        try {
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            ClassTest classTest = (ClassTest) constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        for(Method method : sortMethods) {
            try {
                ClassTest classTest = new ClassTest();
                method.setAccessible(true);
                method.invoke(classTest);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }


}
