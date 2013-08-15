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
	 * �����ļ�
	 * @param filename �ļ�����
	 * @param filecontent �ļ���ͬ
	 * @throws Exception
	 */
	public void save(String filename,String filecontent) throws Exception{
		//����˽��ģʽ�����������ֻ��Ӧ�õ���Ӧ�ã�����Ӧ���޷����ʣ��������˽��ģʽ�������ļ���д��ʱ���ø��Ƿ�ʽ
		FileOutputStream outStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
		//���ļ���д����
		outStream.write(filecontent.getBytes());
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
