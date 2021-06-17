import hw6.Method;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayTest {
    Method method;

    @BeforeEach
    void init() {
        System.out.println("Создаем объект");
        method = new Method();
    }

    @AfterEach
    void destruct() {
        System.out.println("Подчистили ресурсы");
    }

    @Test
    @DisplayName("Входной массив без четверок")
    void testArrWithoutFour() {
        int[] arr = new int[]{2, 1, 3, 7};
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> method.arrIncludeFour(arr));
        assertEquals("Массив должен содержать хотя бы одну 4", runtimeException.getMessage());
    }

    @DisplayName("Проверяем написание чиселок после 4")
    @ParameterizedTest
    @MethodSource
    void testArrIncludeFour(int[] arr, int[] resArr) {
        assertArrayEquals(resArr, method.arrIncludeFour(arr), "Результат: " + resArr);
    }

    private static Stream<Arguments> testArrIncludeFour() {
        List<Arguments> list = new ArrayList<>();
        list.add(Arguments.arguments(new int[]{1, 3, 5, 4, 3, 8, 4, 6, 8, 3, 9, 1}, new int[]{6, 8, 3, 9, 1}));
        list.add(Arguments.arguments(new int[]{2, 4, 6}, new int[]{6}));
        list.add(Arguments.arguments(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}));
        list.add(Arguments.arguments(new int[]{4}, new int[]{}));
        return list.stream();
    }

    @DisplayName("Проверяем наличие чиселок 1 и 4")
    @ParameterizedTest
    @MethodSource
    void testArrOneFour(int[] arr, boolean bool) {
        assertEquals(bool, method.arrOneFour(arr), "Результат: " + bool);
    }

    private static Stream<Arguments> testArrOneFour() {
        List<Arguments> list = new ArrayList<>();
        list.add(Arguments.arguments(new int[]{1, 1, 1, 1, 4, 4, 4, 4}, true));
        list.add(Arguments.arguments(new int[]{1, 2, 3, 1}, false));
        list.add(Arguments.arguments(new int[]{4, 2, 3, 4}, false));
        list.add(Arguments.arguments(new int[]{1, 4, 4, 1, 1, 4, 3}, false));
        return list.stream();

    }


}
