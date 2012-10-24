/*
�@��:Jeffreyshe
²��y�z:��MyArray��unit test
��s���:2012/10/24
*/
import static org.junit.Assert.*;
import org.junit.*;

public class ArrayTest {
	private MyArray array;
	private static final double DELTA = 0.000001;  //double���A��expected�Mresult�ۮt�h�ֵ����۵�
	
	/*test�}�l�e�|���檺�禡*/
	@Before
	public void setUp()
	{
		array=new MyArray("1,2,3,4,5,6,7,8,9,10");
	}
	
	/*test������檺�禡*/
	@After
	public void tearDown()
	{
		array=null;
	}
	
	/*����array�����̤j��*/
	@Test
	public void testMaximum()
	{
		double expected=10;
		double result=array.getMaximumValue();
		assertEquals(expected, result,DELTA);
	}
	
	/*����array�����̤p��*/
	@Test
	public void testMinimum()
	{
		double expected=1;
		double result=array.getMinimumValue();
		assertEquals(expected, result,DELTA);
	}
	
	/*����array����������*/
	@Test
	public void testMean()
	{
		double expected=5.5;
		double result=array.getMean();
		assertEquals(expected, result,DELTA);
	}
	
	/*����array���������*/
	@Test
	public void testMedian()
	{
		double expected=5.5;
		double result=array.getMedian();
		assertEquals(expected, result,DELTA);
	}
	
	/*����array���ܲ���*/
	@Test
	public void testVariance()
	{
		double expected=8.25;
		double result=array.getVariance();
		assertEquals(expected, result,DELTA);
	}
	
	/*����array���зǮt*/
	@Test
	public void testStandardDeviation()
	{
		double expected=2.872281;
		double result=array.getStandardDeviation();
		assertEquals(expected, result,DELTA);
	}
}
