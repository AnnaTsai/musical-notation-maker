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
	private String FilePath;
	public Float TotalBeat;
	/*Ū��song��Ƨ��U�Ҧ��ɮ�*/
	
	public CheckBeat(String Path)
	{
		FilePath=Path;
		try
		{
			ReadFile();
		}
		catch(Exception e)	{}

	}
	
	private void ReadFile() throws IOException
	{
			FileReader fr = new FileReader(FilePath);  
	        BufferedReader br=new BufferedReader(fr);  
	        String Line;  
	        InputIndex = 0;
	        while((Line = br.readLine()) != null)//Ū���C�@��
	        {  
	            Input_String[InputIndex] = Line;//�s�JInput_String Array
	            InputIndex++;
	        }  
	        br.close();  
	        Process(); 

	}
	/*�q�ɮפ��ΥX�C�@�p�`*/
	private void Process()
	{
		String[] FirstRow = Input_String[0].split("/");
		String[] Section = new String[10];
		int CorrectSectionBeat = Integer.valueOf(FirstRow[0]).intValue();//�C�@���ɤ��W�w�C�@�p�`���ӭn�X��
		int Standard = Integer.valueOf(FirstRow[1]).intValue();//�C�@���ɳW�w�X�����ŬO�@��
		int SectionId = 1;
		float TotalBeat=0;
		/*��X�p�`*/
		for(int i=1 ; i<InputIndex ; i++)
		{
			Section = Input_String[i].split("\\|");
			int HowManySection;
			HowManySection = Section.length;
			for(int j=1;j<=HowManySection-1;j++)
			{
				float  Beat = ReturnSectionBeat(Section[j],Standard);
				TotalBeat=TotalBeat+Beat;
				//System.out.println("�`���:"+Beat+" "+"�p�`��:"+SectionId+" �зǸ`���:"+CorrectSectionBeat);//�L�X�O���ɤ����@�p�`�M��X���`���
				/*
				 �i�H�b�o��check�C�@���ɨC�@�p�`�O�_���`����~ 
				if(sectionBeat == Beat)  
				{
					���T!!!
				}  
				*/
				SectionId++;
			}
		}
		this.TotalBeat=new Float(TotalBeat);
	}
	/*�p��C�@�p�`�`���*/
	private float ReturnSectionBeat(String Section,int standerd)
	{
		Pattern Pattern2;
        Matcher Matcher2;
        int Note = 0;//�o�O�X������
        float Delay = 0;//�o�O�h�֩���
        float AnsBeat = 0;//�̫ᨺ�@�p�`���`���
		//System.out.println(Section);
		Pattern pattern = Pattern.compile("\\([#|b]??,.*?,.*?\\)");//(���ۤv������:match�̵u    p.s�A����|����S��r��)
        Matcher Matcher = pattern.matcher(Section);
        while (Matcher.find())
        {
            String Section1;
            Section1 = Matcher.group();
            Pattern2 = Pattern.compile("\\d+");
            Matcher2 = Pattern2.matcher(Section1);
            int HasSecond = 0;//�ݦ��S���������ܼ�
            while (Matcher2.find())
            { 
                if(HasSecond == 0)
                {
                	Note = Integer.valueOf(Matcher2.group());
                }
                else
                {
                	Delay = Integer.valueOf(Matcher2.group());
                }
                HasSecond++;
            }
            float Rbeat = (float)Note/standerd;//�`�窺�˼�
            float Beat = 1/Rbeat;//�`��
            if(HasSecond == 1)
            {
            	AnsBeat += Beat+Delay;//��C�@�p�`���`��[�_��
            }
            else
            {
            	AnsBeat += Beat;//�p�G������,�[�W�h
            }
        }
		return AnsBeat;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */

}
