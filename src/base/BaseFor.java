package base;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.PathKit;


public class BaseFor {
		public static void main(String[] args) throws IOException {
			String[] arrStrings={"*","* *","* * *","* * * *"};
			for (int i = 0; i < arrStrings.length; i++) {
				System.out.println(arrStrings[i]);
			}
			for (String string : arrStrings) {
				System.out.println(string);
			}
			Map<Integer, Integer> map=new HashMap<Integer, Integer>();
			/*String newPath ="C:/Users/Administrator.T74QDUU38NVE3NY.000/Desktop/11.jpg";
			File file=new File(newPath);
			FileInputStream fis=new FileInputStream(file);
			File outFile=new File("C:/Users/Administrator.T74QDUU38NVE3NY.000/Desktop/test/11.jpg");
			if (!outFile.exists()) {
				outFile.createNewFile();
			}
			FileOutputStream fos=new FileOutputStream(outFile);
			byte[] in=new byte[1024];
			int len=-1;
			while (fis.read(in)!=len) {
				fos.write(in);
			}*/
			
			
//			String temp="";
//			for (int i = 0;(temp=br.readLine())!=null; i++) {
//				StringBuffer sb=new StringBuffer().append(temp);
//				System.out.println(sb);
//			}
//			temp=br.readLine();
			
//			String string="5646465.jpg";
//			int s=string.lastIndexOf(".");
//			String string2=string.substring(s+1);
//			System.out.println(string2);
			File upfile =new File(PathKit.getWebRootPath()+"/WEB-INF/upload/"+System.currentTimeMillis()+".jpg");
			if (!upfile.exists()) {
				upfile.createNewFile();
				System.out.println("创建完成");
			}
		}
	}
