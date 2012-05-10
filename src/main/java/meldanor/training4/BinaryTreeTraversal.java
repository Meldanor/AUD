package meldanor.training4;

import java.util.Iterator;

/** Provide traversals of binary trees.<p>

    Defines iterators and classes generating iterators
    for various traversal orders.

    @see BinaryTree
    @see aud.example.BinaryTreeTraversal
 */
public class BinaryTreeTraversal<T> {
  
  /** Base class for traversal.
      An instance creates iterators for a particular traversal.
   */
  public abstract class Traversal 
    implements Iterable<BinaryTree<T> > {
    BinaryTree<T> tree_;
    
    Traversal(BinaryTree<T> tree) { tree_=tree; }
 
    @Override public String toString() {
      String s="";
      for (BinaryTree<T> node : this) { 
	s+=node.getData().toString()+",";
      }
      return s.substring(0,s.length()-1);
    }
  }

  /** base class for stack-based pre-/in-/postorder traversal */
  public abstract class RecursiveTraversalIterator
    implements Iterator<BinaryTree<T> > {

    Stack<BinaryTree<T> > stack_ = new Stack<BinaryTree<T> >();
    
    RecursiveTraversalIterator(BinaryTree<T> tree) { 
      stack_.push(tree); 
    }
    
    @Override public boolean hasNext() { 
      return !stack_.is_empty(); 
    }       
    /** @throws UnsupportedOperationException (not implemented) */
    @Override public void remove() {
      throw new UnsupportedOperationException();
    }	
  }
  
  /** preorder iterator for {@link BinaryTree} */
  public class PreorderIterator
    extends RecursiveTraversalIterator {    
    PreorderIterator(BinaryTree<T> tree) { super(tree);  }
    
    @Override public BinaryTree<T> next() {
      BinaryTree<T> node=stack_.pop(); // may throw!
      if (node.getRight()!=null) stack_.push(node.getRight());      
      if (node.getLeft()!=null)  stack_.push(node.getLeft());
      return node;
    }	
  }

  /** helper: generates {@link PreorderIterator} */
  public class Preorder 
    extends Traversal implements Iterable<BinaryTree<T> > {
    Preorder(BinaryTree<T> tree) { super(tree); }    
    @Override public Iterator<BinaryTree<T> > iterator() {
      return new PreorderIterator(tree_);
    }
  }
  /** return instance of generator 
      @see BinaryTree#preorder
   */
  public Preorder preorder(BinaryTree<T> tree) {
    return new Preorder(tree);
  }

  
  /** inorder iterator for {@link BinaryTree} */
  public class InorderIterator
    extends RecursiveTraversalIterator {    
    InorderIterator(BinaryTree<T> tree) { 
      super(tree);
      descendLeft();
    }

    private void descendLeft() {
      BinaryTree<T> node=stack_.top();
      for (node=node.getLeft();node!=null;node=node.getLeft())
	stack_.push(node);
    }    
        
    @Override public BinaryTree<T> next() {
      BinaryTree<T> node=stack_.pop();
      if (node.getRight()!=null) {
	stack_.push(node.getRight());
	descendLeft();
      }

      return node;
    }	
  }

  /** helper: generates {@link InorderIterator} */
  public class Inorder 
    extends Traversal  implements Iterable<BinaryTree<T> >{    
    Inorder(BinaryTree<T> tree) { super(tree); }    
    @Override public Iterator<BinaryTree<T> > iterator() {
      return new InorderIterator(tree_);
    }    
  }

  /** return instance of generator 
      @see BinaryTree#inorder
  */
  public Inorder inorder(BinaryTree<T> tree) {
    return new Inorder(tree);
  }


  /** postorder iterator for {@link BinaryTree} */
  public class PostorderIterator
    extends RecursiveTraversalIterator {  
    final static int LEFT=0;
    final static int RIGHT=1;  
    final static int OUTPUT=2;

    /* Idea: 

       We keep a second stack (of same size), which keeps track of the
       current state.  Note: One single stack would be enough, but
       then we would have to worry about types (node or state).

       The state tells us, "where we are". Have alook at the recursive
       version. Then the state is defined as follows
       
       visit(node)
           // state == LEFT
         visit(node.left)
	   // state == RIGHT
         visit(node.right)
	   // state == OUTPUT
         output(node)
     */

    Stack<Integer> state_ = new Stack<Integer>();

    PostorderIterator(BinaryTree<T> tree) { 
      super(tree);      
      state_.push(LEFT);
    }
    
    private void descendLeft() {
      BinaryTree<T> node=stack_.top();
      for (node=node.getLeft();node!=null;node=node.getLeft()) {
	stack_.push(node);
	state_.push(RIGHT);
      }
    }

    @Override public boolean hasNext() {
      assert(stack_.is_empty()==state_.is_empty()); // additional check
      return super.hasNext();
    }
   
    @Override public BinaryTree<T> next() {
      int state;
      while ((state=state_.pop())!=OUTPUT) {	
	if (state==LEFT) {
	  // stack_.top() remains
	  state_.push(RIGHT);

	  descendLeft();
	}
	else {
	  // stack_.top() remains
	  state_.push(OUTPUT);

	  BinaryTree<T> node=stack_.top();
	  if (node.getRight()!=null) {
	    stack_.push(node.getRight());
	    state_.push(LEFT);
	  }	    
	}
      }      
      return stack_.pop(); // state == OUTPUT
    }	
  }

  /** helper: generates {@link PostorderIterator} */
  public class Postorder 
    extends Traversal  implements Iterable<BinaryTree<T> > {
    Postorder(BinaryTree<T> tree) { super(tree); }    
    @Override public Iterator<BinaryTree<T> > iterator() {
      return new PostorderIterator(tree_);
    }
  }

  /** return instance of generator 
      @see BinaryTree#postorder
  */
  public Postorder postorder(BinaryTree<T> tree) {
    return new Postorder(tree);
  }


  /** level-order iterator for {@link BinaryTree} */
  public class LevelorderIterator implements Iterator<BinaryTree<T> > {

    Queue<BinaryTree<T> > queue_ = new Queue<BinaryTree<T> >();
    
    LevelorderIterator(BinaryTree<T> tree) { 
      queue_.enqueue(tree); 
    }
    
    @Override public boolean hasNext() { 
      return !queue_.is_empty(); 
    }       
    /** @throws UnsupportedOperationException (not implemented) */
    @Override public void remove() {
      throw new UnsupportedOperationException();
    }	

    @Override public BinaryTree<T> next() {
      BinaryTree<T> node=queue_.dequeue(); // may throw!
      if (node.getLeft()!=null)  queue_.enqueue(node.getLeft());
      if (node.getRight()!=null) queue_.enqueue(node.getRight());      
      return node;
    }
  }        

  /** helper: generates {@link LevelorderIterator} */
  public class Levelorder 
    extends Traversal implements Iterable<BinaryTree<T> > {
    Levelorder(BinaryTree<T> tree) { super(tree); }    
    @Override public Iterator<BinaryTree<T> > iterator() {
      return new LevelorderIterator(tree_);
    }
  }
  /** return instance of generator 
      @see BinaryTree#levelorder
   */
  public Levelorder levelorder(BinaryTree<T> tree) {
    return new Levelorder(tree);
  }
 
}
