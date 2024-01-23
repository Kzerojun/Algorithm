import java.util.*;

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        return calculate(nodeinfo);
    }
    
    static int[][] calculate(int[][] nodeinfo) {
        PriorityQueue<Point> pq = createPriorityQueue(nodeinfo);
        Node root = createTree(pq);
        int[][] result = createResult(root, nodeinfo.length);
        return result;
    }

    static PriorityQueue<Point> createPriorityQueue(int[][] nodeinfo) {
        PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.y == o2.y) {
                return o1.x - o2.x;
            }
            return o2.y - o1.y;
        });
        for(int i = 0; i < nodeinfo.length; i++) {
            pq.add(new Point(nodeinfo[i][0], nodeinfo[i][1], i+1));
        }
        return pq;
    }

    static Node createTree(PriorityQueue<Point> pq) {
        Point tmp = pq.poll();
        Node root = new Node(tmp.number, tmp.x, tmp.y);
        while(!pq.isEmpty()) {
            root.push(pq.poll());
        }
        return root;
    }

    static int[][] createResult(Node root, int length) {
        List<Integer> preResult = new ArrayList<>();
        List<Integer> postResult = new ArrayList<>();
        printPre(root, preResult);
        printPost(root, postResult);
        return new int[][] {preResult.stream().mapToInt(i -> i).toArray(), postResult.stream().mapToInt(i -> i).toArray()};
    }

    static void printPre(Node node, List<Integer> preResult) {
        if(node == null) return;
        preResult.add(node.number);
        printPre(node.left, preResult);
        printPre(node.right, preResult);
    }

    static void printPost(Node node, List<Integer> postResult) {
        if(node == null) return;
        printPost(node.left, postResult);
        printPost(node.right, postResult);
        postResult.add(node.number);
    }
}

class Node {
    int number, x, y;
    Node left, right;
    
    Node(int number, int x, int y) {
        this.number = number;
        this.x = x;
        this.y = y;
    }
    
    public void push(Point point) {
        if(this.x > point.x) {
            if(this.left == null) {
                this.left = new Node(point.number, point.x, point.y);
            } else {
                this.left.push(point);
            }
        } else if (this.x < point.x) {
            if(this.right == null) {
                this.right = new Node(point.number, point.x, point.y);
            } else {
                this.right.push(point);
            }
        }
    }
}

class Point {
    int x, y, number;
    
    Point(int x, int y, int number) {
        this.x = x;
        this.y = y;
        this.number = number;
    }
}
