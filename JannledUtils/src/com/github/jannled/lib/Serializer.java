package com.github.jannled.lib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Serializer
{
	public static byte[] serialize(Object object)
	{
		byte[] byteOut = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(bos);	 
			out.writeObject(object);
			out.flush();
			byteOut = bos.toByteArray();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return byteOut;
	}
	
	public static Object deserialize(byte[] object)
	{
		Object objectOut = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(object);
		ObjectInput in = null;
		try {
			in = new ObjectInputStream(bis);
			objectOut = in.readObject(); 
		} catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return objectOut;
	}
}
