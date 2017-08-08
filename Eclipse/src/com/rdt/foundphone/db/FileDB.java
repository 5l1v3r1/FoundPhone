package com.rdt.foundphone.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import com.rdt.foundphone.db.FileIO;
import android.os.Environment;

public class FileDB {
	
	public static String value_values;
	public static String value_log;
	public static String value_file;
	public static String value_command;
	public static int count_files;
	public static int count_sensor;
	public static int count_call;
	public static int count_contact;
	
	static {
		value_values = "0";
		
		value_log = "0";
		
		value_file = "0";
		count_files = 0;
		count_sensor = 0;
		count_call = 0;
		count_contact = 0;
		
		value_command = "0";
	}
/********************* HISTORY **********************/	
	/**
	 * сохранение лога изменений данных
	 */
	public static void saveLOG(FileIO files) {
		PrintWriter out = null;
		try {
			out = new PrintWriter( new FileOutputStream(
					Environment.getExternalStorageDirectory().getAbsolutePath() + Values.file_log, true));
			out.println(value_log);
			out.close();
		} catch (IOException ex) {
		} finally {
			if (out != null)
				out.close();
		}
	}
	/********************* SAVE SENSOR CONTACT LISTCALL **********************/		
	public static void saveDATA(int type , String data) {
		String typeDATA = "";
		//ЕСЛИ СОХРАНЯЕТСЯ СПИСОК СЕНСОРОВ
		if(type == 1){typeDATA = Values.file_sensors;}
		//ЕСЛИ СОХРАНЯЕТСЯ СПИСОК КОНТАКТОВ
		if(type == 2){typeDATA = Values.file_contacts;}
		//ЕСЛИ СОХРАНЯЕТСЯ СПИСОК ВЫЗОВОВ
		if(type == 3){typeDATA = Values.file_call_history;}
		PrintWriter out = null;
		try {
			out = new PrintWriter( new FileOutputStream(
					typeDATA, true));
			out.println(data);
			out.close();
		} catch (IOException ex) {
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	public static int getExistListSensors(FileIO files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					files.readInternalFile(Environment.getExternalStorageDirectory().getAbsolutePath() + Values.file_sensors)));
			   int i = 0;
			   while (in.readLine() != null){
			      i++; 
			   }
			   count_sensor = i;
		} catch (IOException ex) {
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
		return count_sensor;
	}
	/**
	 * int получить количество строк
	 * @return 
	 */
	public static int getExistListCall(FileIO files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					files.readInternalFile( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.file_call_history)));
			   int i = 0;
			   while (in.readLine() != null){
			      i++; 
			   }
			   count_call = i;
		} catch (IOException ex) {
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
		return count_call;
	}
	/**
	 * int получить количество строк
	 * @return 
	 */
	public static int getExistListContact(FileIO files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					files.readInternalFile(Environment.getExternalStorageDirectory().getAbsolutePath() + Values.file_contacts)));
			   int i = 0;
			   while (in.readLine() != null){
			      i++; 
			   }
			   count_contact = i;
		} catch (IOException ex) {
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
		return count_contact;
	}
	/**
	 * очистка списка
	 */
	public static void clearListCall(FileIO files) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					files.writeInternalFile(Environment.getExternalStorageDirectory().getAbsolutePath() + Values.file_call_history)));
			out.write("");
		} catch (IOException ex) {
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException ex) {
			}
		}
	}
	/**
	 * очистка списка
	 */
	public static void clearListSensors(FileIO files) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					files.writeInternalFile(Environment.getExternalStorageDirectory().getAbsolutePath() + Values.file_sensors)));
			out.write("");
		} catch (IOException ex) {
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException ex) {
			}
		}
	}
	/**
	 * очистка списка
	 */
	public static void clearListContact(FileIO files) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					files.writeInternalFile(Environment.getExternalStorageDirectory().getAbsolutePath() + Values.file_contacts)));
			out.write("");
		} catch (IOException ex) {
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException ex) {
			}
		}
	}

/************************* FILE  LIST *************************/
	/**
	 * int получить количество строк
	 * @return 
	 */
	public static int getCountFiles(FileIO files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					files.readInternalFile(Environment.getExternalStorageDirectory().getAbsolutePath() + Values.file_file)));
			   int i = 0;
			   while (in.readLine() != null){
			      i++; 
			   }
			  count_files = i;
		} catch (IOException ex) {
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
		return count_files;
	}
	
	/**
	 * загрузка списка файлов
	 */
	public static void loadFileList(FileIO files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(
					files.readInternalFile(Environment.getExternalStorageDirectory().getAbsolutePath() + Values.file_file)));
		/*	val1 = in.readLine();
			val2 = in.readLine();
			val3 = Integer.parseInt(in.readLine());
			val4 = Integer.parseInt(in.readLine());*/
		} catch (IOException ex) {
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
	}

	/**
	 * добавление файлов в список
	 */
	public static void addFileList(FileIO files) {
		PrintWriter out = null;
		try {
			out = new PrintWriter( new FileOutputStream( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.file_file, true));
			out.println(value_file);
			out.close();
		} catch (IOException ex) {
		} finally {
			if (out != null)
				out.close();
		}

	}
	/**
	 * очистка списка
	 */
	public static void clearFileList(FileIO files) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					files.writeInternalFile( Environment.getExternalStorageDirectory().getAbsolutePath() + Values.file_file)));
			out.write("");
		} catch (IOException ex) {
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException ex) {
			}
		}
	}
}
