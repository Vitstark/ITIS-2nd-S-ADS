import homework1.Stack;

public class Main {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 1; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        System.out.println(stack.peek() + stack.pop() + " " + stack.isEmpty());
        System.out.println(stack);
    }
}
