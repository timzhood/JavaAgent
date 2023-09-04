package chall;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Path filePath = Paths.get("c:", "temp", "Checkstyle-TimZ.xml");
        assert filePath.toFile().isFile();

        Map<String, Integer> frequencies = new HashMap<>();

        for (String line : Files.readAllLines(filePath))
        {
            for (String word : line.split("[^a-zA-Z]+"))
            {
                String trimmedWord = word.trim();

                if (!trimmedWord.isEmpty())
                {
                    if (!frequencies.containsKey(trimmedWord))
                    {
                        frequencies.put(trimmedWord, 1);
                    }
                    else
                    {
                        frequencies.put(trimmedWord, frequencies.get(trimmedWord) + 1);
                    }
                }
            }
        }

        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(frequencies.entrySet());

        wordList.sort(new Comparator<>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return o2.getValue() - o1.getValue();
            }
        });

        for (Map.Entry<String, Integer> word : wordList)
        {
            System.out.printf("%s=%d%n", word.getKey(), word.getValue());
        }
    }

}
