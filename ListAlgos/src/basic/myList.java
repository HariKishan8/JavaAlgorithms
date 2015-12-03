package basic;

public class myList {
	
	Node head;
	
	public myList(){
		head = null;
	}
	
	public void insert(int d){
		Node newNode = new Node(d);
		if(head == null){
			head = newNode;
			return;
		}
		Node curr = head;
		while(curr.next != null){
			curr = curr.next;
		}
		curr.next = newNode;
	}
	
	public void remove(int d){
		if (head == null)
			return;
		Node curr = head;
		Node removedNode;
		if(head.data == d){
			removedNode = head;
			head = head.next; 
			removedNode.next = null;
			return;
		}
			
		while(curr.next.data != d)
			curr = curr.next;
		removedNode = curr.next;
		curr.next = curr.next.next;
		removedNode.next = null;
	}
	
	public void print(){
		if(head == null)
			return;
		Node curr = head;
		while(curr != null){
			System.out.println(curr.data);
			curr = curr.next;
		}
	}
	
	public int size(){
		Node curr = this.head;
		if (curr == null)
			return 0;
		int count= 0;
		while(curr!= null){
			curr = curr.next;
			count++;
		}
		return count;
		
	}
	
	public void findlastKth(int k){
		Node slow = head;
		Node fast = head;
		int count = 1;
		while ((fast.next != null) && (count < k)){
			fast = fast.next;
			count++;
		}
		if(fast.next == null && count<k){
			System.out.println("Size less than " + k);
			return;
		}		
//		fast is k nodes ahead of slow
		while(fast.next != null){
			slow = slow.next;
			fast = fast.next;
		}
		System.out.println(k+ "th Node from last: " + slow.data);		
	}
	
	public void insertFirst(int d){
		Node node= new Node(d);
		node.next = head;
		head = node;
	}
	
	public static int addBack(Node l1, Node l2, myList res){
		if(l1 == null && l2 == null)
			return 0;
		int sum = 0,carry = 0;
		carry = addBack(l1.next, l2.next, res);
		
		sum = l1.data + l2.data + carry;
		carry = sum/10;
		sum = sum%10;
		
		res.insertFirst(sum);
		
		
		return carry;
	}
	
	public static myList addBack(myList l1, myList l2){
		myList res = new myList();
		
		int diff = 0;
		int a = l1.size();
		int b = l2.size();
			
		if(a > b)
			diff = a-b;
		else 
			diff = b - a;
		
		while(diff!=0){
			if(a>b)
				l2.insertFirst(0);
			else
				l1.insertFirst(0);
			diff--;
		}
				
		Node c1 = l1.head;
		Node c2 = l2.head;
		
		int carry = addBack(c1, c2, res);
		
		if(carry != 0)
			res.insertFirst(carry);
		return res;
		
	}
	
	public static myList add(myList l1, myList l2){
		myList res = new myList();
		int carry = 0, sum = 0;
		Node c1 = l1.head;
		Node c2 = l2.head;
		
		while(c1!= null && c2!= null){
			sum = c1.data + c2.data + carry;
			carry = sum/10;
			sum = sum%10;
			
			res.insert(sum);
			c1 = c1.next;
			c2 = c2.next;
		}
		
		if(c1!= null && c2 == null){
			while(c1!= null){
				sum = c1.data + carry;
				carry = sum/10;
				sum = sum%10;
				
				res.insert(sum);
				c1= c1.next;
			}
		}
		
		else if(c1 == null && c2 != null){
			while(c2!= null){
				sum = c2.data + carry;
				carry = sum/10;
				sum = sum%10;
				
				res.insert(sum);
				c2= c2.next;
			}
		}
		else{
//			both are null just add the carry
			res.insert(carry);			
		}
		
		return res;
	}
	
	
	public static void main(String[] args) {
		
//		myList myL = new myList();
//		
//		myL.insert(3);
//		myL.insert(4);
//		myL.insert(3);
//		myL.insert(5);
//		myL.insert(1);
//		myL.insert(4);
//		myL.print();
//
//		System.out.println();
//		myL.findlastKth(4);
		
//		Store two integers 
		myList n1 = new myList();
		myList n2 = new myList();
		
//		insert integer from lsb to msb for add to be easy
		int a = 1234;
		int b = 21234;
		int n = 10;
		
		while(a != 0){
			int d = a%n;
			a = a/n;
			n1.insertFirst(d);
		}
		
		n = 10;
		while(b != 0){
			int d = b%n;
			b = b/n;
			n2.insertFirst(d);
		}
		
//		add(n1, n2).print();
		addBack(n1, n2).print();
		
	}
}
