package hw7;

public class ClassTest {
    // 1. Создать класс, который может выполнять «тесты», в качестве тестов выступают
    // классы с наборами методов с аннотациями @Test.
    //
    //   Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite,
    //   если такой имеется, далее запущены методы с аннотациями @Test, а по завершению
    //   всех тестов – метод с аннотацией @AfterSuite.
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before suite");
    }

    @Test(priority = 2)
    public void test1() {
        System.out.println(2);
    }

    @Test(priority = 3)
    public void test2() {
        System.out.println(3);
    }

    @Test(priority = 4)
    public void test3() {
        System.out.println(4);
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After suite");
    }

}
