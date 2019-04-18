package io.renren;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MyLinkList<T extends Object> {
	private Node<T> head;
	private Node<T> last;
	

	public MyLinkList() {
	}
	
	public T getFirst() {
		if(head==null) {
			return null;
		}
		return head.getValue();
	}
	
//	public Iteration getIteration {
//		
//	}
	
	public T getLast() {
		if(last==null) {
			return null;
		}
		return last.getValue();
	}
	public void add(T value) {
		if(head==null){
			Node temp = new Node(value,null,null);
			head = temp;
			last = head;
		}else {
			if(last.getPrevious()==null) {
				last = new Node(value,null,head);
				head.setNext(last);
			}else {
				Node temp = new Node(value,null,last);
				last.setNext(temp);
				last = temp;

			}

		}
	}
	
	public Iterator<T> getIterator() {
		Iterator<T> iterator = new Iterator<T>(head);
		return iterator;
	}
	
	public void  reversal() {
		Node<T> temp_head = head;
		Node<T> temp_last = last;
		T tempValue = null;
		while(temp_head!=temp_last) {
			tempValue = temp_head.getValue();
			temp_head.setValue(temp_last.getValue());
			temp_last.setValue(tempValue);
			temp_head = temp_head.getNext();
			temp_last = temp_last.getPrevious();
		}
	}
	
	public T get(int i) {
		Node<T> temp = head;
		for (int j = 1; j < i; j++) {
			temp.hasNext();
			temp = temp.getNext();
		}
		return temp.getValue();
	}
	public int getSize() {
		if(head==null) {
			return 0;
		}
		int size = 1;
		Node temp = head;
		while(temp.hasNext()) {
			size++;
			temp = temp.getNext();
		}
		return size;
	}
	public static void main(String[] args) {
//		MyLinkList<String> list = new MyLinkList<String>();
//		list.add("1");
//		list.add("2");
//		list.add("3");
//		list.add("4");
//		list.add("5");
//		list.add("6");
//		list.add("7");
//		Iterator<String> iterator = list.getIterator();
//		System.out.println("原：");
//
//		while(iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
//		list.reversal();
//		System.out.println("reversal后：");
//
//		Iterator<String> iterator1 = list.getIterator();
//		while(iterator1.hasNext()) {
//			System.out.println(iterator1.next());
//		}

		Map<String, String> emptyMap = Collections.emptyMap();
		emptyMap.put("a", "c");
		if(emptyMap instanceof HashMap) {
			
		}
}
class Iterator<T> {
	private Node<T> node;

	private Node<T> curr;
	public Iterator(Node<T> head) {
		node = head;
	}
	public boolean hasNext() {
		if(node==null) {
			return false;
		}
		if(curr==null) {
			curr = node;
			return true;
		}
		boolean hasNext = node.hasNext();
		if(hasNext) {
			node = node.getNext();
		}
		return hasNext;
	}
	public T next() {
		T value = node.getValue();
		return value;
	}
}
class Node<T extends Object>{
	private Node<T> next;
	private Node<T> previous;
	private T value;
	public Node(T value,Node<T> next ,Node<T> previous) {
		this.setValue(value);
		this.setNext(next);
		this.setPrevious(previous);
	}
	
	public Node() {
		
	}
	
	public boolean hasNext() {
		if(next!=null) {
			return true;
		}
		return false;
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getPrevious() {
		return previous;
	}

	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}
}
}
