import org.junit.*;

public class MainClassTest {

//    @BeforeClass
//    public void beforeClassMethod() {
//
//    }
//
//    @Before
//    public void setUp() {
//
//    }

    @Test
    public void method1() {
        Assert.assertTrue("Value are not equals!",1 + 1 == 2);
    }

//    @Test
//    public void method2() {
//        Assert.assertNull();
//        Assert.assertNotNull();
//    }

    @Test
    public void method2() {
        Assert.assertFalse("Value are equals!",1 + 1 == 2);
    }

    @Test
//    @Ignore
    public void method3() {
        Assert.assertEquals(20,5 + 5);
//        Assert.assertNotEquals(10, 2 + 2);
    }

    @Test
    public void method4() {
        Assert.assertNotEquals(20, 5 + 5);
    }

//    @After
//    public void tearDown() {
//
//    }
//
//    @AfterClass
//    public void afterClassMethod() {
//
//    }
}
