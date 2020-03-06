import java.io.*;
import java.util.*;
public class MarblePaths {
    static class TreeNode {
        List<TreeNode> children;
        int value;
        int nodeName;
        int parent;
        public TreeNode(int nodeName, int value)
        {
            children = new ArrayList<>();
            this.nodeName=nodeName;
            this.value=value;
        }
        public void addChild(TreeNode node)
        {
            children.add(node);
        }
        public TreeNode getChild(int i)
        {
            return children.get(i);
        }
    }
    static int answers = 0;
    static int numMarbles = 0;
    public static void main(String[] args) {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer currentLine = new StringTokenizer(br.readLine());
            int numVertices = Integer.parseInt(currentLine.nextToken());
            int numMarblesOnPath = Integer.parseInt(currentLine.nextToken());
            numMarbles = numMarblesOnPath;
            TreeNode[] nodes = new TreeNode[numVertices];
            currentLine = new StringTokenizer(br.readLine());
            for (int i=0; i<nodes.length; i++)
            {
                int marbleValue = Integer.parseInt(currentLine.nextToken());
                nodes[i]=new TreeNode(i,marbleValue);
            }
            Map<Integer,List<Integer>> edges = new HashMap<>();
            for (int i=0; i<numVertices-1; i++)
            {
                currentLine = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(currentLine.nextToken())-1;
                int b = Integer.parseInt(currentLine.nextToken())-1;
                if (edges.get(a)==null)
                    edges.put(a,new ArrayList<>());
                if (edges.get(b)==null)
                    edges.put(b,new ArrayList<>());
                edges.get(a).add(b);
                edges.get(b).add(a);
            }
            Stack<TreeNode> treeNodeStack = new Stack<>();
            nodes[0].parent=-1;
            treeNodeStack.push(nodes[0]);
            while (! treeNodeStack.isEmpty())
            {
                TreeNode currentNode = treeNodeStack.pop();
                List<Integer> children = edges.get(currentNode.nodeName);
                if (children!=null) {
                    for (int i = 0; i < children.size(); i++) {
                        if (!(children.get(i) == currentNode.parent)) {
                            TreeNode child = nodes[children.get(i)];
                            treeNodeStack.push(nodes[children.get(i)]);
                            currentNode.addChild(child);
                            child.parent = currentNode.nodeName;
                        }
                    }
                }
            }
            traverseTree(nodes[0],0, -1);
            System.out.println(answers);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void traverseTree(TreeNode node, int numConsecutiveMarbles, int prevValue)
    {
        if (prevValue==1 && node.value==1)
            numConsecutiveMarbles+=1;
        else
        {
            if (prevValue==-1 && node.value==1)
            numConsecutiveMarbles=1;
        }
        if (numConsecutiveMarbles>numMarbles)
            return;
        if (node.children.size()==0) {
            answers++;
        }
        for (int i=0; i<node.children.size(); i++)
        {
            traverseTree(node.getChild(i), numConsecutiveMarbles, node.value);
        }
    }
}
