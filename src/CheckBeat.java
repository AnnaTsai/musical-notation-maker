/*
Author:Anna
Description:Read All files in folder "song" then calculate beats and check.
Update date:2012/11/9 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckBeat 
{
	private static String[] Input_String = new String[10000];//��Ƴ̤j��:10000
	private static int InputIndex;
	/*Ū��song��Ƨ��U�Ҧ��ɮ�*/
	private static void ReadFile() throws IOException
	{
		File dir = new File("./song");//��e�ؿ���"song"�ؿ��U���۹���|
		String[] filenames = dir.list();//�C�X�Ҧ������U�ɦW
		for(int i=0;i<filenames.length;i++)
		{
			System.out.println(filenames[i]);
			FileReader fr = new FileReader("./song/"+filenames[i]);  
	        BufferedReader br=new BufferedReader(fr);  
	        String line;  
	        InputIndex = 0;
	        while((line = br.readLine()) != null)//Ū���C�@��
	        {  
	            Input_String[InputIndex] = line;//�s�JInput_String Array
	            InputIndex++;
	        }  
	        br.close();  
	        Process(); 
		}
	}
	/*�q�ɮפ��ΥX�C�@�p�`*/
	private static void Process()
	{
		String[] FirstRow = Input_String[0].split("/");
		String[] Section = new String[10];
		int sectionBeat = Integer.valueOf(FirstRow[0]).intValue();//�C�@���ɤ��W�w�C�@�p�`���ӭn�X��
		int standard = Integer.valueOf(FirstRow[1]).intValue();//�C�@���ɳW�w�X�����ŬO�@��
		int sectionId = 1;
		/*��X�p�`*/
		for(int i=1 ; i<InputIndex ; i++)
		{
			Section = Input_String[i].split("\\|");
			int howManySection;
			howManySection = Section.length;
			for(int j=1;j<=howManySection-1;j++)
			{
				float  Beat = Check(Section[j],standard);
				System.out.println(Beat+" "+sectionId);//�L�X�O���ɤ����@�p�`�M��X���`���
				/*
				 �i�H�b�o��check�C�@���ɨC�@�p�`�O�_���`����~ 
				if(sectionBeat == Beat)  
				{
					���T!!!
				}  
				*/
				sectionId++;
			}
		}
	}
	/*�p��C�@�p�`�`���*/
	private static float Check(String Section,int standerd)
	{
		Pattern pattern2;
        Matcher matcher2;
        int note = 0;//�o�O�X������
        float delay = 0;//�o�O�h�֩���
        float ansBeat = 0;//�̫ᨺ�@�p�`���`���
		System.out.println(Section);
		Pattern pattern = Pattern.compile("\\([#|b]??,.*?,.*?\\)");//(���ۤv������:match�̵u    p.s�A����|����S��r��)
        Matcher matcher = pattern.matcher(Section);
        while (matcher.find())
        {
            String Section1;
            Section1 = matcher.group();
            pattern2 = Pattern.compile("\\d+");
            matcher2 = pattern2.matcher(Section1);
            int hasSecond = 0;//�ݦ��S���������ܼ�
            while (matcher2.find())
            { 
                if(hasSecond == 0)
                {
                	note = Integer.valueOf(matcher2.group());
                }
                else
                {
                	delay = Integer.valueOf(matcher2.group());
                }
                hasSecond++;
            }
            float Rbeat = (float)note/standerd;//�`�窺�˼�
            float beat = 1/Rbeat;//�`��
            if(hasSecond == 1)
            {
            	ansBeat += beat+delay;//��C�@�p�`���`��[�_��
            }
            else
            {
            	ansBeat += beat;//�p�G������,�[�W�h
            }
        }
		return ansBeat;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		ReadFile(); 
	}

}
