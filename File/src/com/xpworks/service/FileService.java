package com.xpworks.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;

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
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
		//向文件中写数据
		outStream.write(filecontent.getBytes());
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
