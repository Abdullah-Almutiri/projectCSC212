public class BST <T extends Comparable<T>> {
	BSTNode<Contact > root, current;
	
	/** Creates a new instance of BST */
	public BST() {
		root = current = null;
	}
	
	public boolean IsEmpty() {
		return root == null;
	}
	
	public boolean Isfull() {
		return false;
	}
	
	public Contact retrieve () {
		return current.data;
	}
	
	public void printBST() {
		if(IsEmpty()) {
			System.out.println("The tree is empty!");
			return;
		}
		printInOrder(this.root);
	}
	
	public void printInOrder(BSTNode<Contact> p) {
		if (p != null) {
			printInOrder(p.left);
	        System.out.print(p.data);
	        printInOrder(p.right);
	    }
	}
	
	public boolean findkey(String tkey) {
		BSTNode<Contact> p = root, q = root;
				
		if(IsEmpty())
			return false;
		
		while(p != null) {
			q = p;
			if(tkey.compareToIgnoreCase(p.key)==0) {
				current = p;
				return true;
			}
			else if(tkey.compareToIgnoreCase(p.key)<0)
				p = p.left;
			else
				p = p.right;
		}
		
		current = q;
		return false;
	}
	
	public boolean findHelper(String s, int criteria) {
	    return find(s, criteria, this.root);
	}

	public boolean find(String s, int criteria, BSTNode<Contact> p) {
	    if (IsEmpty()) {
	        return false;
	    }

	    if (p != null) {
	        if (find(s, criteria, p.left)) {
	            return true;  // Stop the recursion if found in the left subtree
	        }

	        switch (criteria) {
	            case 2: {
	                if (p.getData().getPhoneNumber().equalsIgnoreCase(s)) {
	                    current = p;
	                    return true;
	                }
	                break;
	            }
	            case 3: {
	                if (p.getData().getEmail().equalsIgnoreCase(s)) {
	                    current = p;
	                    return true;
	                }
	                break;
	            }
	            case 4: {
	                if (p.getData().getAddress().equalsIgnoreCase(s)) {
	                    current = p;
	                    return true;
	                }
	                break;
	            }
	            case 5: {
	                if (p.getData().getBirthday().equalsIgnoreCase(s)) {
	                    current = p;
	                    return true;
	                }
	                break;
	            }
	        }

	        if (find(s, criteria, p.right)) {
	            return true;  // Stop the recursion if found in the right subtree
	        }
	    }
	    return false;
	}
	
	public void printFind(String s, int criteria) {
		if(criteria!=1 && findHelper(s, criteria)) {
			System.out.println(current.data);
			return;
		}
		else if(criteria==1 && findkey(s)) {
			System.out.println(current.data);
			return;
		}
		System.out.println("No contact found!");
	}
	
	public boolean insert(Contact val) {
		String k = val.getContactName();
		BSTNode<Contact> p, q = current;
		
		if(findkey(k)) {
			current = q;  // findkey() modified current
			return false; // key already in the BST
		}
		
		p = new BSTNode<Contact>(k, val);
		if (IsEmpty()) {
			root = current = p;
			return true;
		}
		else {
			// current is pointing to parent of the new key
			if (val.compareTo(current.getData())<0)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}

	private BSTNode<Contact> find_min(BSTNode<Contact> p){
		if(p == null)
			return null;
		
		while(p.left != null){
			p = p.left;
		}
		
		return p;
	}

	private BSTNode<Contact> remove_aux(String key, BSTNode<Contact> p,  BooleanWrapper flag) {
		BSTNode<Contact> q, child = null;
		if(p == null)
			return null;
		if(key.compareToIgnoreCase(p.key) < 0)
			p.left = remove_aux(key, p.left, flag); //go left
		else if(key.compareToIgnoreCase(p.key) > 0)
			p.right = remove_aux(key, p.right, flag); //go right
		else { // key is found
			flag.setValue(true);
			if (p.left != null && p.right != null){ //two children
				q = find_min(p.right);
				p.key = q.key;
				p.data = q.data;
				p.right = remove_aux(q.key, p.right, flag);
			}
			
			else {
				if (p.right == null) //one child
					child = p.left;
				else if (p.left == null) //one child
					child = p.right;
				return child;
			}
		}
		return p;
	}
	
	public boolean remove_key(String tkey){
		BooleanWrapper removed = new BooleanWrapper(false);
		BSTNode<Contact> p;
		p = remove_aux(tkey, root, removed);
		current = root = p;
		return removed.getValue();
	}
	
	public void printTree() {
	    printInOrder(root);
	}
}
