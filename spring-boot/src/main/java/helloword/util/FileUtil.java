package helloword.util;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 文件处理工具类
 * @author Administrator
 *
 */
public class FileUtil {
	//记录web端正在合并的文件
	public static HashSet filePathSet = new HashSet();
	

   /** 
     * 删除文件 
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt 
     * @return boolean
     */ 
   public static void delFile(String filePathAndName) throws Exception { 
       String filePath = filePathAndName; 
       filePath = filePath.toString(); 
       File myDelFile = new File(filePath);
       myDelFile.delete(); 

   } 
   /** 
     * 删除文件夹 
     * @param folderPath String
     * @return boolean 
     */ 
   public static void delFolder(String folderPath) throws Exception { 
       delAllFile(folderPath); //删除完里面所有内容 
       String filePath = folderPath; 
       filePath = filePath.toString(); 
       File myFilePath = new File(filePath);
       myFilePath.delete(); //删除空文件夹 
   }
   /**
    * 删除某个时间前的文件
    * @param folderPath
    * @param time
    * @throws Exception
    */
   public static void delFolder(String folderPath,long time){ 
       String filePath = folderPath; 
       filePath = filePath.toString(); 
       File myFilePath = new File(filePath);
       myFilePath.delete();
   } 
   /** 
     * 删除文件夹里面的所有文件 
     * @param path String 文件夹路径 如 c:/fqf 
     */ 
   public static void delAllFile(String path) throws Exception { 
       File file = new File(path); 
       if (!file.exists()) { 
           return; 
       } 
       if (!file.isDirectory()) { 
           return; 
       } 
       String[] tempList = file.list(); 
       if(tempList != null){
	       File temp = null; 
	       for (int i = 0; i < tempList.length; i++) { 
	           if (path.endsWith(File.separator)) { 
	               temp = new File(path + tempList[i]); 
	           } 
	           else { 
	               temp = new File(path + File.separator + tempList[i]); 
	           } 
	           if (temp != null && temp.isFile()) { 
	               temp.delete(); 
	           } 
	           if (temp != null && temp.isDirectory()) { 
	               delAllFile(path+"/"+ tempList[i]);//先删除文件夹里面的文件 
	               delFolder(path+"/"+ tempList[i]);//再删除空文件夹 
	           } 
	       } 
       }
   } 
   
   /**
    * 删除指定时间之前的文件夹所有文件
    * @param path
    * @param time	时间戳
    * @throws Exception
    */
   public static void delAllFile(String path,long time){ 
       File file = new File(path); 
       if (!file.exists()) { 
           return; 
       } 
       if (!file.isDirectory()) { 
           return; 
       } 
       String[] tempList = file.list(); 
       if(tempList != null){
    	   File temp = null; 
           for (int i = 0; i < tempList.length; i++) { 
               if (path.endsWith(File.separator)) { 
                   temp = new File(path + tempList[i]); 
               } 
               else { 
                   temp = new File(path + File.separator + tempList[i]); 
               } 
               if (temp.isFile()) { 
            	   long fileTime = temp.lastModified();
            	   if(fileTime<time){
            		   temp.delete(); 
            	   }
               } 
               if (temp.isDirectory()) { 
            	   delAllFile(path+"/"+ tempList[i],time);//先删除文件夹里面的文件  
                   delFolder(path+"/"+ tempList[i],time);//再删除空文件夹 
               } 
           }
       }
   }

   /** 
     * 复制单个文件 
     * @param oldPath String 原文件路径 如：c:/fqf.txt 
     * @param newPath String 复制后路径 如：f:/fqf.txt 
     * @return boolean 
     */ 
   public static void copyFile(String oldPath, String newPath) throws Exception { 
       int bytesum = 0; 
       int byteread = 0; 
       File oldfile = new File(oldPath); 
       if (oldfile.exists()) { //文件存在时 
    	   //获取文件夹目录和文件名称
    	   String[] newpaths = newPath.split("/");
    	   String dirPath = "";
    	   String fileName = "";
    	   for(int i=0;i<newpaths.length;i++){
    		   if(i != newpaths.length -1){
    			   dirPath+=newpaths[i]+"/";
    		   }else{
    			   fileName=newpaths[i]; 
    		   }
    		   
    	   }
    	   File newfile = new File(dirPath); 
    	   if(!newfile.exists()){
    		   newfile.mkdirs();
    	   }
    	   newfile = new File(newPath); 
    	   if(!newfile.exists()){
    		   newfile.createNewFile();
    	   }
           InputStream inStream = new FileInputStream(oldPath); //读入原文件 
           FileOutputStream fs = new FileOutputStream(newPath); 
           byte[] buffer = new byte[1024]; 
           while ( (byteread = inStream.read(buffer)) != -1) { 
               bytesum += byteread; //字节数 文件大小 
               fs.write(buffer, 0, byteread); 
           } 
           inStream.close();
           fs.close();
       } 
   } 
   
   public static void copyFile(File oldfile, File newfile) throws Exception { 
       int bytesum = 0; 
       int byteread = 0; 
       if (oldfile.exists()) { //文件存在时 
           InputStream inStream = new FileInputStream(oldfile); //读入原文件 
           FileOutputStream fs = new FileOutputStream(newfile); 
           byte[] buffer = new byte[1024]; 
           while ( (byteread = inStream.read(buffer)) != -1) { 
               bytesum += byteread; //字节数 文件大小 
               fs.write(buffer, 0, byteread); 
           } 
           inStream.close();
           fs.close();
       } 
   } 

   /** 
     * 复制整个文件夹内容 
     * @param oldPath String 原文件路径 如：c:/fqf 
     * @param newPath String 复制后路径 如：f:/fqf/ff 
     * @return boolean 
     */ 
   public static void copyFolder(String oldPath, String newPath) throws Exception { 
       (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹 
       File a=new File(oldPath); 
       String[] file=a.list(); 
       if(file != null){
    	   File temp=null; 
           for (int i = 0; i < file.length; i++) { 
               if(oldPath.endsWith(File.separator)){ 
                   temp=new File(oldPath+file[i]); 
               }else{ 
                   temp=new File(oldPath+File.separator+file[i]); 
               } 
               if(temp.isFile()){ 
                   FileInputStream input = new FileInputStream(temp); 
                   FileOutputStream output = new FileOutputStream(newPath + "/" + 
                           (temp.getName()).toString()); 
                   byte[] b = new byte[1024 * 5]; 
                   int len; 
                   while ( (len = input.read(b)) != -1) { 
                       output.write(b, 0, len); 
                   } 
                   output.flush(); 
                   output.close(); 
                   input.close(); 
               } 
               if(temp.isDirectory()){//如果是子文件夹 
                   copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]); 
               } 
           } 
       }
       
   } 

   /** 
     * 移动文件到指定目录 
     * @param oldPath String 如：c:/fqf.txt 
     * @param newPath String 如：d:/fqf.txt 
     */ 
   public static void moveFile(String oldPath, String newPath) throws Exception { 
       copyFile(oldPath, newPath); 
       delFile(oldPath); 
   }
   
   public static void moveFile2(String oldPath, String newPath) throws Exception { 
       File file  = new File(oldPath);
       file.renameTo(new File(newPath));
   }

   /** 
     * 移动文件到指定目录 
     * @param oldPath String 如：c:/fqf.txt 
     * @param newPath String 如：d:/fqf.txt 
     */ 
   public static void moveFolder(String oldPath, String newPath) throws Exception { 
       copyFolder(oldPath, newPath); 
       delFolder(oldPath); 

   } 


	/**
	 * 根据文件名获取文件类型
	 * @param fileName
	 * @return
	 */
	public static String getFileType(String fileName){
		String fileType = "";
		int start = fileName.lastIndexOf(".");
		if(start != -1){
			fileType = fileName.substring(start, fileName.length());
		}
		return fileType;
	}
	
	/**
	 * 根据文件名获取文件类型（不带点的）
	 * @param fileName
	 * @return
	 */
	public static String getNOPointFileType(String fileName){
		String fileType = "";
		if(fileName != null){
			int start = fileName.lastIndexOf(".");
			if(start != -1){
				if(fileName != null && !"".equals(fileName)){
					fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
					fileType = fileType.substring(1,fileType.length());
				}
			}
		}
		return fileType;
	}
	/**
	 * 流写入文件
	 * @param input
	 */
	public static void writeFile(File file,InputStream input) throws Exception {
        if (!file.exists()) {
        	file.getParentFile().mkdirs();
        	file.createNewFile();
        }
		OutputStream os;
		os = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = input.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		input.close();
	}
	
	/**
	 * 流写入文件
	 * @param input
	 */
	public static void writePath(String path,InputStream input) throws Exception {
		File file = new File(path);
		writeFile(file,input);
	}
	/**
	 * 多文件合并
	 * @param destPath
	 * @param partList
	 */
	public static boolean mergeFiles(String destPath, ArrayList<String> partList) {
		FileChannel outChannel = null;
		String partPath = "";
		try {
			outChannel = new FileOutputStream(destPath).getChannel();
			for(int i=0;i<partList.size();i++){  
				partPath = partList.get(i);
				FileChannel fc = new FileInputStream(partPath).getChannel(); 
				ByteBuffer bb = ByteBuffer.allocate(1024 * 8);
				while(fc.read(bb) != -1){
					bb.flip();
					outChannel.write(bb);
					bb.clear();
				}
				fc.close();
				//删除临时文件
				File partFile = new File(partPath);
				partFile.delete();
			}
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		} finally {
			try {if (outChannel != null) {outChannel.close();}} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	/**
	 * 根据url下载文件
	 * @param url
	 * @param path
	 * @return 0:成功，1：非FileNotFoundException异常，2：FileNotFoundException异常
	 */
//	public static int downloadFromUrl(String url,String path) {
//		URL httpurl;
//		try {
//			httpurl = new URL(url);
//			File f = new File(path);
//	        //Warning: this method does not set a connection or read timeout and thus might block forever.
//	        //Use copyURLToFile(URL, File, int, int) with reasonable timeouts to prevent this.
//	        //FileUtils.copyURLToFile(httpurl, f);  //此方法可能会造成永久超时
//	        FileUtils.copyURLToFile(httpurl, f, 20000, 20000);//此方法设置了超时时间
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return 2;
//		}  catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return 1;
//		}
//		return 0;
//	}
	     
	/**
	 * 根据url获取文件名
	 * @param url
	 * @return
	 */
	public static String getFileNameFromUrl(String url){  
		String name = new Long(System.currentTimeMillis()).toString() + ".X";  
		int index = url.lastIndexOf("/");  
		if(index > 0){  
			name = url.substring(index + 1);  
			if(name.trim().length()>0){  
				return name;  
			}  
		}  
		return name;  
	}
	
	/**
	 * 根据路径获取文件名
	 * @param path
	 * @return
	 */
	public static String getFileName(String path){  
		String name = "";  
		int index = path.lastIndexOf("/");  
		if(index > 0){  
			name = path.substring(index + 1);  
			if(name.trim().length()>0){  
				return name;  
			}  
		}  
		return name;  
	}
	/**
	 * 获取文件大小
	 * @param str
	 * @return
	 */
	public static Long getFileSizeToStr(String str){
		Long lsize = null;
		Double size = null;
		try{
			str = str.replace(" ", "");//去掉所有空格
			if(!StringUtil.isEmpty(str)){
				String unit = str.substring(str.length()-1,str.length());
				String value = str.substring(0,str.length()-1);
				if("K".equalsIgnoreCase(unit)){
					size = Double.parseDouble(value) * 1024;
				}else if("M".equalsIgnoreCase(unit)){
					size = Double.parseDouble(value) * 1024 * 1024;
				}else if("G".equalsIgnoreCase(unit)){
					size = Double.parseDouble(value) * 1024 * 1024 * 1024;
				}
			}
		}catch(Exception e){
			
		}
		if(size != null){
			lsize = size.longValue();
		}
		if(lsize == null){
			lsize = 0L;
		}
		return lsize;
	}
	/**
	 * 获取图片规格
	 * @param str
	 * @return
	 */
	public static String getStandardToStr(String str){
		String standard = "";
		try{
			str = str.replace(" ", "");//去掉所有空格
			if(!StringUtil.isEmpty(str)){
				str = str.replace("&#215;", "*");
			}
			String regEx="[^0-9,*]";   
			Pattern p = Pattern.compile(regEx);   
			Matcher m = p.matcher(str);  
			try{
				standard = m.replaceAll("").trim();
			}catch (Exception e) {
			}
		}catch(Exception e){
			
		}
		return standard;
	}
	
	/**
	 * 创建文件
	 * @param path
	 * @throws Exception
	 */
	public static File createFile(String path) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
	    	file.getParentFile().mkdirs();
	    	file.createNewFile();
	    }
		return file;
	}
	
	/**
	 * 创建文件夹
	 * @param path
	 * @throws Exception
	 */
	public static void createDir(String path) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
	    	file.mkdirs();
	    }
	}
	
	/**
	 * 文件夹遍历
	 * @param path
	 * @param list
	 */
	public static void traverseFolder(String path, List<File> list) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        traverseFolder(file2.getAbsolutePath(), list);
                    } else {
                        list.add(file2);
                    }
                }
            }
        }
    }

	/**
	 * 写入一行
	 * @param path
	 * @param text
	 */
	public static void writerLine(String path, String text) throws Exception {
		File file = new File(path);
		if (file.exists()) {
			file = createFile(path);
		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			String stringBuffer = text +
					"\n";
			bw.write(stringBuffer);
		} finally {
			bw.close();
		}
	}

	/**
	 * 读取文件最后一行
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String readLastLine(String path) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			file = createFile(path);
		}
		String lastLine = "";
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String currentLine = "";
		while ((currentLine = bufferedReader.readLine()) != null) {
			lastLine = currentLine;
		}
		return lastLine;
	}
}
