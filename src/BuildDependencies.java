import java.io.*;
import java.util.*;

public class BuildDependencies {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int numFiles = Integer.parseInt(br.readLine());
            Map<String, List> dependentFiles = new HashMap<>();
            Map<String, Boolean> visited = new HashMap<>();
            String[] files = new String[numFiles];
            for (int i = 0; i < numFiles; i++) {
                StringTokenizer currentLine = new StringTokenizer(br.readLine());
                String currentFile = currentLine.nextToken();
                currentFile = currentFile.substring(0, currentFile.length() - 1);
                files[i] = currentFile;
                visited.put(currentFile, false);
                while (currentLine.hasMoreTokens()) {
                    String file = currentLine.nextToken();
                    if (dependentFiles.get(file) == null)
                        dependentFiles.put(file, new ArrayList());
                    dependentFiles.get(file).add(currentFile);
                }
            }
            String changedFile = br.readLine();
            Stack<String> stack = new Stack<>();
            Stack<String> sort = new Stack<>();
            //buildTopologicalSort(changedFile,sort,dependentFiles, visited);
            stack.push(changedFile);
            while (!stack.isEmpty()) {
                String thisFile = stack.peek();
                List<String> dependentFileList = dependentFiles.get(thisFile); //A list of the files that are dependent on this file
                visited.put(thisFile, true);
                if (dependentFileList == null) {
                    stack.pop();
                    sort.push(thisFile);
                } else {
                    int counter = 0; //To ensure we visit all children before adding it to the topological sort
                    for (int i = 0; i <dependentFileList.size(); i++) {
                        if (!visited.get(dependentFileList.get(i))) {
                            stack.push(dependentFileList.get(i));
                            break;
                        } else counter++;
                    }
                    if (counter == dependentFileList.size()) {
                        stack.pop();
                        sort.push(thisFile);
                    }
                }
            }
            PrintWriter out = new PrintWriter(System.out);
            while (!sort.isEmpty())
                out.println(sort.pop());
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void buildTopologicalSort(String currentFile, Stack<String> sort, Map<String,List> dependentFiles, Map<String,Boolean> visited)
    {
        visited.put(currentFile,true);
        if (dependentFiles.get(currentFile)==null) {
            sort.push(currentFile);
            return;
        }
        List<String> files = dependentFiles.get(currentFile);
        for (int i=0; i<files.size(); i++)
        {
            if (!visited.get(files.get(i)))
                buildTopologicalSort(files.get(i),sort,dependentFiles,visited);
        }
        sort.push(currentFile);
    }
}
