import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {
    MyArrayList<String> list;

    @Before
    public void setUp() {
        list = new MyArrayList<>();
    }

    @After
    public void after() {
        list = null;
    }
    
    @Test
    public void insertTest() {
        // insertLast 테스트
        list.insertLast("apple");
        assertTrue(list.size() == 1);
        list.insertLast("orange");
        assertTrue(list.size() == 2);
        assertEquals("apple", list.peek(0));
        assertEquals("orange", list.peek(1));

        // 배열 확장 테스트
        list.insertLast("cherry");
        assertTrue(list.size() == 4);
        list.insertLast("pear");
        assertTrue(list.size() == 4);
        assertEquals("apple", list.peek(0));
        assertEquals("orange", list.peek(1));
        assertEquals("cherry", list.peek(2));
        assertEquals("pear", list.peek(3));

        // 추가 항목 삽입으로 배열 확장 테스트
        list.insert("grape", 1);
        assertTrue(list.size() == 8);
        list.insert("lemon", 4);
        assertTrue(list.size() == 8);
        list.insertLast("kiwi");
        assertEquals("apple", list.peek(0));
        assertEquals("grape", list.peek(1));
        assertEquals("orange", list.peek(2));
        assertEquals("cherry", list.peek(3));
        assertEquals("lemon", list.peek(4));
        assertEquals("pear", list.peek(5));
        assertEquals("kiwi", list.peek(6));
        assertEquals(null, list.peek(7));
    }

    @Test
    public void deleteTest() {
        list.insertLast("apple");
        list.insertLast("grape");
        list.insertLast("orange");
        list.insertLast("cherry");
        list.insertLast("lemon");
        list.insertLast("pear");
        list.insertLast("kiwi");

        // 삭제 테스트
        assertEquals("lemon", list.delete(4));
        assertEquals("apple", list.delete(0));
        assertEquals("grape", list.delete(0));
        assertEquals("kiwi", list.delete(3));

        assertEquals("orange", list.peek(0));
        assertEquals("cherry", list.peek(1));
        assertEquals("pear", list.peek(2));

        // 배열 축소 테스트
        list.delete(0); // 삭제 후 1/2로 축소
        assertTrue(list.size() == 4);
        assertEquals("cherry", list.peek(0));
        assertEquals("pear", list.peek(1));
    }

    @Test
    public void resizeTest() {
        // 크기 확장 테스트
        list.insertLast("apple");
        list.insertLast("orange");
        list.insertLast("grape");
        list.insertLast("cherry");
        list.insertLast("pear");
        assertEquals(5, list.size());

        // 배열 축소 테스트
        list.delete(1);
        list.delete(2);
        list.delete(2);
        assertEquals(2, list.size());
    }
}