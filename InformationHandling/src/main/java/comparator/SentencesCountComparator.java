package comparator;

import entity.Component;

import java.util.Comparator;

public class SentencesCountComparator implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        int countSentencesFirstParagraph = o1.getAllComponents().size();
        int countSentencesSecondParagraph = o2.getAllComponents().size();
        return Integer.compare(countSentencesFirstParagraph, countSentencesSecondParagraph);
    }
}