package _algorithm_practice;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import java.util.TreeMap;



//霍夫曼编码的基本实现
//步骤：1，先获得每个字母出现频率
//步骤：2，利用优先级队列生成树
//步骤：3，输出编码
public class  MyHuffman{
	public static class Node{
		int freq;
		Node left;
		Node Right;
		int name=-1;//已经转换成数字了，不需要再用字母了,没有值的节点就是-1，和a节点的0值区分
		public Node(int val) {
			this.freq=val;
		}
		
		public void Name(int c) {
			this.name=c;
		}
		
		public String toString() {
			return (String)(this.name + " "+ this.freq);
		}
		
	}
	
	public static class MinFreqComparator implements Comparator<Node>{
		@Override
		public int compare(Node o1, Node o2) {
			return o1.freq-o2.freq;//返回frequency最小的
		}
	}
	
	public static PriorityQueue<Node> buildHuffmanTree(String str) {	
		PriorityQueue<Node> pQ = new PriorityQueue<>(new MinFreqComparator());
		int[] frequency = new int[27];
		for(int i=0; i<str.length() ; i++) {
			char cur =str.charAt(i);
			int curIndex =0;
			if(cur==' ') {
				curIndex= cur-6;//放到第26位上
			}else{
			    curIndex = cur-97;}
			frequency[curIndex]++;//对应位置频率加1
		}
		
		for(int i =0; i<frequency.length ;i++) {
			if(frequency[i]!=0) {
				//System.out.println(i + " times : "+ frequency[i]);
				Node n = new Node(frequency[i]);
				n.name=i;
				pQ.add(n);//每次生成一个加入节点
			}
		}
		//System.out.println(pQ);
		while(pQ.size()>1) {
			Node left = pQ.poll();
			Node right = pQ.poll();
			Node cur = new Node(left.freq + right.freq);
			cur.left = left;
			cur.Right = right;
			pQ.add(cur);	
			//System.out.println(pQ);
		}
		return pQ;
	}
	public static Node getHuffmanHead(String str) {
		str = str.toLowerCase();//转换小写
		PriorityQueue<Node> print = buildHuffmanTree(str);
		Node head = print.poll();
		return head;
	}
	public static void printCode(Node head, String res, HashMap<Node,String> HuffMap) {
		//这里不能用treemap,频率相同的他只存储一个
		if(head.left==null && head.Right==null) {
			System.out.println("alphabelt:  "+ head.name+"  frequency : "+head.freq + "  binarycode: " + res);
			HuffMap.put(head, res);
			return;
		}
		printCode(head.left, res+"0", HuffMap);
		printCode(head.Right, res+"1", HuffMap);
	}
	
	public static void decodeHuffman(TreeMap<Integer,String> HuffMap, Node head) {
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test  = "abcdef";
		Node n = getHuffmanHead(test);
		HashMap<Node, String> HuffMap = new HashMap<>();
		printCode(n, "", HuffMap);
		System.out.println(HuffMap);
		char a = 'a' + 1;
		System.out.println(a);
	}
}
