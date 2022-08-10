package classes;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Subtitles {
    private Map<Integer, SubtitleElement> subtitles;

    public Subtitles(){
        this.subtitles = new TreeMap<>();
    }

    public int loadSubtitles(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream).useDelimiter(System.lineSeparator()+System.lineSeparator());
        int numSubtitlePartsRead = 0;
        while (scanner.hasNext()){
            String subtitlePart = scanner.next();
            SubtitleElement subtitleElement = new SubtitleElement(subtitlePart);
            subtitles.put(subtitleElement.getNumber(), subtitleElement);
            numSubtitlePartsRead++;
        }
        return numSubtitlePartsRead;

    }

    public void print(){
        String output = subtitles.values()
                .stream()
                .map(SubtitleElement::toString)
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(output);
    }
    public void shift(int ms){
        subtitles.values()
                .forEach(subtitleElement -> subtitleElement.shift(ms));
    }
}
