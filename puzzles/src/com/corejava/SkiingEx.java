package com.corejava;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class SkiingEx {
	final static String FILE_NAME = "C:\\Temp\\input.txt";
	Map<String, String>[] map = null;
	String Path = null;
	int[][] noOfRowOrCol;
	int longestPathElementCount;
	Map<Integer, HashSet> finalMap[];
	String[] names;
	public void readFile(){
		StringBuilder contents = new StringBuilder();
		try {
			BufferedReader input = new BufferedReader(new FileReader(FILE_NAME));
			try {
				String line = null;
				if((line = input.readLine()) != null){
					int noOfCases = Integer.parseInt(line);
					noOfRowOrCol = new int[noOfCases][2];
					map = new Map[noOfCases];
					finalMap = new HashMap[noOfCases];
					names = new String[noOfCases];
				}
				int cases = -1;
				int row=0;
				while ((line = input.readLine()) != null) {
					if(line.matches("[a-zA-z\\-]+[\\s][0-9]+[\\s][0-9]+[\\s]*")){
						row=0;
						cases++;
//						System.err.println(line);
						StringTokenizer tokenizer = new StringTokenizer(line, " ");
						names[cases] = tokenizer.nextToken();
						while (tokenizer.hasMoreTokens()) {
							String abc = tokenizer.nextToken();
							if(tokenizer.hasMoreTokens())
								noOfRowOrCol[cases][0] = Integer.parseInt(abc);
							else
								noOfRowOrCol[cases][1] = Integer.parseInt(abc);
						}
						map[cases] = new TreeMap<String, String>();
						finalMap[cases] = new HashMap<Integer, HashSet>();
						continue;
					}
					StringTokenizer tokenizer = new StringTokenizer(line, " ");
					int column = 0;
					while (tokenizer.hasMoreTokens()) {
						String Key = row+"-"+column;
						map[cases].put(Key,tokenizer.nextToken());
						column++;
					}
					row++;
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public int[] getCell(String key){
		int[] cellValue = new int[2];
		StringTokenizer tokenizer = new StringTokenizer(key, "-");
//		System.err.println(key);
		while (tokenizer.hasMoreTokens()) {
				String dummy = tokenizer.nextToken();
				if(tokenizer.hasMoreTokens()){
					cellValue[0] = Integer.parseInt(dummy);
				}else{
					cellValue[1] = Integer.parseInt(dummy);
				}
		}
		return cellValue;
	
	}
	public void moveAbove(String key, String value, int noOfElements, String path,  int testCaseNum){
		int[] cellValue = getCell(key);
		int row = cellValue[0];
		int column = cellValue[1];
		if(!path.equals(""))
			path = path+"-"+value;
		else
			path = value;
		if(row>0){
			String aboveKey = (row-1)+"-"+column;
			String aboveValue = map[testCaseNum].get(aboveKey);
			if(Integer.parseInt(aboveValue)>=Integer.parseInt(value)){
				if(finalMap[testCaseNum].get(noOfElements)==null){
					HashSet<String> list =  new HashSet<String>();
					list.add(path);
					finalMap[testCaseNum].put(noOfElements, list);
				}else{
					finalMap[testCaseNum].get(noOfElements).add(path);
				}
				return;
			}
			noOfElements++;
			moveAbove(aboveKey, aboveValue, noOfElements, path, testCaseNum);
			moveLeft(aboveKey, aboveValue, noOfElements, path, testCaseNum);
			moveRight(aboveKey, aboveValue, noOfElements, path, testCaseNum);
		}else{
			if(finalMap[testCaseNum].get(noOfElements)==null){
				HashSet<String> list =  new HashSet<String>();
				list.add(path);
				finalMap[testCaseNum].put(noOfElements, list);
			}else{
				finalMap[testCaseNum].get(noOfElements).add(path);
			}
			return;
		}
	}
	public void moveBelow(String key, String value, int noOfElements, String path,  int testCaseNum){
		int[] cellValue = getCell(key);
		int row = cellValue[0];
		int column = cellValue[1];
		if(!path.equals(""))
			path = path+"-"+value;
		else
			path = value;
		if(row < (noOfRowOrCol[testCaseNum][0]-1)){
			String aboveKey = (row+1)+"-"+column;
			String aboveValue = map[testCaseNum].get(aboveKey);
			if(Integer.parseInt(aboveValue)>=Integer.parseInt(value)){
				if(finalMap[testCaseNum].get(noOfElements)==null){
					HashSet<String> list =  new HashSet<String>();
					list.add(path);
					finalMap[testCaseNum].put(noOfElements, list);
				}else{
					finalMap[testCaseNum].get(noOfElements).add(path);
				}
				return;
			}
			noOfElements++;
			moveBelow(aboveKey, aboveValue, noOfElements, path, testCaseNum);
			moveLeft(aboveKey, aboveValue, noOfElements, path, testCaseNum);
			moveRight(aboveKey, aboveValue, noOfElements, path, testCaseNum);
		}else{
			if(finalMap[testCaseNum].get(noOfElements)==null){
				HashSet<String> list =  new HashSet<String>();
				list.add(path);
				finalMap[testCaseNum].put(noOfElements, list);
			}else{
				finalMap[testCaseNum].get(noOfElements).add(path);
			}
			return;
		}
	};
	public void moveLeft(String key, String value, int noOfElements, String path,  int testCaseNum){
		int[] cellValue = getCell(key);
		int row = cellValue[0];
		int column = cellValue[1];
		if(!path.equals(""))
			path = path+"-"+value;
		else
			path = value;
		if(column >0){
			String aboveKey = (row)+"-"+(column-1);
			String aboveValue = map[testCaseNum].get(aboveKey);
			if(Integer.parseInt(aboveValue)>=Integer.parseInt(value)){
				if(finalMap[testCaseNum].get(noOfElements)==null){
					HashSet<String> list =  new HashSet<String>();
					list.add(path);
					finalMap[testCaseNum].put(noOfElements, list);
				}else{
					finalMap[testCaseNum].get(noOfElements).add(path);
				}
				return;
			}
			noOfElements++;
			moveAbove(aboveKey, aboveValue, noOfElements, path, testCaseNum);
			moveBelow(aboveKey, aboveValue, noOfElements, path, testCaseNum);
			moveLeft(aboveKey, aboveValue, noOfElements, path, testCaseNum);
		}else{
			if(finalMap[testCaseNum].get(noOfElements)==null){
				HashSet<String> list =  new HashSet<String>();
				list.add(path);
				finalMap[testCaseNum].put(noOfElements, list);
			}else{
				finalMap[testCaseNum].get(noOfElements).add(path);
			}
			return;
		}
	};
	public void moveRight(String key, String value, int noOfElements, String path,  int testCaseNum){
		int[] cellValue = getCell(key);
		int row = cellValue[0];
		int column = cellValue[1];
		if(!path.equals(""))
			path = path+"-"+value;
		else
			path = value;
		if(column < (noOfRowOrCol[testCaseNum][1]-1)){
			String aboveKey = (row)+"-"+(column+1);
			String aboveValue = map[testCaseNum].get(aboveKey);
			if(Integer.parseInt(aboveValue)>=Integer.parseInt(value)){
				if(finalMap[testCaseNum].get(noOfElements)==null){
					HashSet<String> list =  new HashSet<String>();
					list.add(path);
					finalMap[testCaseNum].put(noOfElements, list);
				}else{
					finalMap[testCaseNum].get(noOfElements).add(path);
				}
				return;
			}
			noOfElements++;
			moveAbove(aboveKey, aboveValue, noOfElements, path, testCaseNum);
			moveBelow(aboveKey, aboveValue, noOfElements, path, testCaseNum);
			moveRight(aboveKey, aboveValue, noOfElements, path, testCaseNum);
		}else{
			if(finalMap[testCaseNum].get(noOfElements)==null){
				HashSet<String> list =  new HashSet<String>();
				list.add(path);
				finalMap[testCaseNum].put(noOfElements, list);
			}else{
				finalMap[testCaseNum].get(noOfElements).add(path);
			}
			return;
		}
	};
	public void iterateMap(){
		for(int i=0; i<map.length;i++){
			if(map[i] !=null){
				Iterator<String> it = map[i].keySet().iterator();
				while(it.hasNext()){
					String keyName = it.next();
					String height = map[i].get(keyName);
//					System.err.println("key: "+keyName+"value: "+height);
					moveAbove(keyName, height, 1, "", i);
					moveBelow(keyName, height, 1, "", i);
					moveLeft(keyName, height, 1, "", i);
					moveRight(keyName, height, 1, "", i);
				}
				System.err.println(names[i]+" : "+finalMap[i].size()+" : "+finalMap[i].get(finalMap[i].size()));
			}
		}
	}
	public static void main(String[] args) {

		SkiingEx skiingEx = new SkiingEx();
		skiingEx.readFile();
		skiingEx.iterateMap();
	}
}
