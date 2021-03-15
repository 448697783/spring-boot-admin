package io.renren;

import java.util.ArrayList;

public class Model{
		static ArrayList<String> list;
		static void read(ArrayList<String> list) {
			if(list == null) {
				list = new ArrayList<String>();
			}
			list.add("Hello");
		}
		
		public static void main(String[] args) {
			Model.read(list);
			System.out.println(list.size());
		}
	}