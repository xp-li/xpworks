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
	 * �����ļ�
	 * @param filename �ļ�����
	 * @param filecontent �ļ���ͬ
	 * @throws Exception
	 */
	public void save(String filename,String filecontent) throws Exception{
		//����˽��ģʽ�����������ֻ��Ӧ�õ���Ӧ�ã�����Ӧ���޷����ʣ��������˽��ģʽ�������ļ���д��ʱ���ø��Ƿ�ʽ
		//����ģʽ��Context.MODE_PRIVATE��Context.MODE_APPEND��MODE_WORLD_READABLE��MODE_WORLD_WRITEABLE
		//���ϣ���ļ�������Ӧ�ö���д�����Դ��룺openFileOutput("itcast.txt", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);

		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
		//���ļ���д����
		outStream.write(filecontent.getBytes());
		outStream.close();
	}
	
	/**
	 * �����ļ���SD card
	 * @param filename �ļ�����
	 * @param filecontent �ļ�����
	 * @throws Exception �׳�����
	 */
	public void saveToSDCard(String filename,String content) throws Exception{
		//File file = new File(new File("/mnt/sdcard"), filename);//������Ҫֱ��дSDcard�ľ���·��
		File file = new File(Environment.getExternalStorageDirectory(),filename);
		FileOutputStream outStream = new FileOutputStream(file);
		outStream.write(content.getBytes());
		outStream.close();
		
	}
	/**
	 * ��ȡ�ļ�����
	 * @param filename �ļ�����
	 * @return �ļ�����
	 * @throws Exception
	 */
	public String read(String filename) throws Exception{
		FileInputStream inputStream =context.openFileInput(filename);//������
		ByteArrayOutputStream outStream =new ByteArrayOutputStream();//���ڴ�洢����
		
		byte[] buffer = new byte[1024];
		int len=0;		
		
		while((len=inputStream.read(buffer))!=-1){
			outStream.write(buffer,0,len);
		}
		
		byte [] data = outStream.toByteArray();
		return new String(data);
	}

}
