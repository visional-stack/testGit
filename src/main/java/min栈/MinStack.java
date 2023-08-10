package min栈;

import org.junit.Test;

import java.util.Stack;


/**
测试用例：
1、
2、栈为空时取数据
 */
public class MinStack {
    Stack<Integer> minStack;
    Stack<Integer> dataStack;
    /** initialize your data structure here. */
    public MinStack() {
        minStack=new Stack<Integer>();
        dataStack=new Stack<Integer>();
    }

    public void push(int x) {
        if(minStack.isEmpty()||x<=minStack.peek()){
            minStack.push(x);
        }else{
            minStack.push(minStack.peek()) ;
        }

        dataStack.push(x);
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
       return dataStack.peek();
    }

    public int min() {
       return minStack.peek();
    }
   @Test
    public void test1(){

       MinStack minStack = new MinStack();
       minStack.push(-2);
       minStack.push(0);
       minStack.push(-1);
       System.out.println(minStack.min());
       System.out.println(minStack.top());
       minStack.pop();
       minStack.pop();
       System.out.println(minStack.min());
       System.out.println(minStack.top());
       System.out.println("修改dev4");



   }
}
