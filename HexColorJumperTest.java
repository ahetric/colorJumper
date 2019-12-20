import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HexColorJumperTest {


   private HexColorJumper tester;

   @Before
   public void setUp() {
      tester = new HexColorJumper();
   }

   
   @Test
   // yellow to magenta
   public void test1() {
      tester.setC1("#FFFF00");
      tester.setC2("#FF8080");
      String expected = "#FF00FF";
      String actual = tester.calculate(tester.getC1(), tester.getC2());
      Assert.assertEquals(expected, actual);
   }
   
   @Test
   // magenta to yellow
   public void test2() {
      tester.setC1("#FF00FF");
      tester.setC2("#FF8080");
      String expected = "#FFFF00";
      String actual = tester.calculate(tester.getC1(), tester.getC2());
      Assert.assertEquals(expected, actual);
   }
   
   @Test
   // white to black
   public void test3() {
      tester.setC1("#FFFFFF");
      tester.setC2("#7F7F7F");
      String expected = "#000000";
      String actual = tester.calculate(tester.getC1(), tester.getC2());
      Assert.assertEquals(expected, actual);
   }
   
   @Test
   // maroon to purple
   public void test4() {
      tester.setC1("#990000");
      tester.setC2("#990080");
      String expected = "#9900FF";
      String actual = tester.calculate(tester.getC1(), tester.getC2());
      Assert.assertEquals(expected, actual);
   }
   
   @Test
   // maroon to purple, test for negative sign
   public void test5() {
      tester.setC1("#FF0000");
      tester.setC2("#B3334D");
      String expected = "#67669A";
      String actual = tester.calculate(tester.getC1(), tester.getC2());
      Assert.assertEquals(expected, actual);
   }
   
   @Test
   // 
   public void test6() {
      tester.setC1("#99CC99");
      tester.setC2("#CCB380");
      String expected = "#FF9A67";
      String actual = tester.calculate(tester.getC1(), tester.getC2());
      Assert.assertEquals(expected, actual);
   }
   
   @Test
   // 
   public void test7() {
      tester.setC1("#993366");
      tester.setC2("#CC8080");
      String expected = "#FFCD9A";
      String actual = tester.calculate(tester.getC1(), tester.getC2());
      Assert.assertEquals(expected, actual);
   }
   
   @Test
   // 
   public void test8() {
      tester.setC1("#DCE7EE");
      tester.setC2("#99B1C1");
      String expected = "#567B94";
      String actual = tester.calculate(tester.getC1(), tester.getC2());
      Assert.assertEquals(expected, actual);
   }
}
