package com.xpworks.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.os.Environment;

public class FileService {
	
	private Context context;

	public FileService(Context context) {
		super();
		this.context = context;
	}
	/**
	 * 保存文件
	 * @param filename 文件名称
	 * @param filecontent 文件内同
	 * @throws Exception
	 */
	public void save(String filename,String filecontent) throws Exception{
		//采用私有模式创建的问题件只能应用到本应用，其他应用无法访问；另外采用私有模式创建的文件，写入时采用覆盖方式
		//操作模式：Context.MODE_PRIVATE，Context.MODE_APPEND，MODE_WORLD_READABLE，MODE_WORLD_WRITEABLE
		//如果希望文件被其他应用读和写，可以传入：openFileOutput("itcast.txt", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);

		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
		//向文件中写数据
		outStream.write(filecontent.getBytes());
		outStream.close();
	}
	
	/**
	 * 保存文件到SD card
	 * @param filename 文件名称
	 * @param filecontent 文件内容
	 * @throws Exception 抛出例外
	 */
	public void saveToSDCard(String filename,String content) throws Exception{
		//File file = new File(new File("/mnt/sdcard"), filename);//尽量不要直接写SDcard的绝对路径
		File file = new File(Environment.getExternalStorageDirectory(),filename);
		FileOutputStream outStream = new FileOutputStream(file);
		outStream.write(content.getBytes());
		outStream.close();
		
	}
	/**
	 * 读取文件内容
	 * @param filename 文件名称
	 * @return 文件内容
	 * @throws Exception
	 */
	public String read(String filename) throws Exception{
		FileInputStream inputStream =context.openFileInput(filename);//输入流
		ByteArrayOutputStream outStream =new ByteArrayOutputStream();//向内存存储的流
		
		byte[] buffer = new byte[1024];
		int len=0;		
		
		while((len=inputStream.read(buffer))!=-1){
			outStream.write(buffer,0,len);
		}
		
		byte [] data = outStream.toByteArray();
		return new String(data);
	}

}
