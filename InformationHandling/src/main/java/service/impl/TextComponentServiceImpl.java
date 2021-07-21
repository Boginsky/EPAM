package service.impl;

import comparator.SentencesCountComparator;
import entity.Component;
import exception.CustomException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ComponentService;

import java.util.ArrayList;
import java.util.List;

import static entity.ComponentType.*;

public class TextComponentServiceImpl implements ComponentService {
    private static Logger logger = LogManager.getLogger();
    private static final String VOWEL_REGEX = "[AEIOUaeiou]";
    private static final String CONSONANT_REGEX = "[[^AEIOUaeiou]&&A-Za-z]";
    private static final String PUNCTUATION = "\\p{Punct}";

    @Override
    public long countVowels(Component component) throws CustomException {
        if (component.getType() != SENTENCE) {
            logger.error("Input data type is not correct" + component.getType());
            throw new CustomException("Input data type is not correct" + component.getType());
        }
        long i = component.getAllComponents().stream()
                .flatMap(w -> w.getAllComponents().stream())
                .filter(s -> s.toString().matches(VOWEL_REGEX))
                .count();
        logger.info("Has been counted Vowels. result = " + i);
        return i;
    }

    @Override
    public long countConsonants(Component component) throws CustomException {
        if (component.getType() != SENTENCE) {
            logger.error("Input data type is not correct" + component.getType());
            throw new CustomException("Input data type is not correct" + component.getType());
        }
        long i = component.getAllComponents().stream()
                .flatMap(w -> w.getAllComponents().stream())
                .filter(s -> s.toString().matches(CONSONANT_REGEX))
                .count();
        logger.info("Has been counted Consonants. result = " + i);
        return i;
    }

    @Override
    public List<Component> findSentencesOfMaxWord(Component component) throws CustomException {
        if (component.getType() != TEXT) {
            logger.error("Input data type is not correct" + component.getType());
            throw new CustomException("Input data type is not correct" + component.getType());
        }
        int maxWordSize = 0;
        maxWordSize = findMaxWordSizeInText(component);
        logger.info("Has been found maxWordSize in text. result = " + maxWordSize);
        List<Component> list = new ArrayList<>();
        for (Component paragraph : component.getAllComponents()) {
            for (Component sentence : paragraph.getAllComponents()) {
                int tmpMaxWordSize = findMaxWordSizeInSentence(sentence);
                if (tmpMaxWordSize == maxWordSize) {
                    list.add(sentence);
                }
            }
        }
        logger.info("Has been found sentences with max wordSize. Count = " + list.size());
        return list;
    }

    @Override
    public void removeSentencesWithMinWords(Component component, int minWords) throws CustomException {
        if (component.getType() != TEXT) {
            logger.error("Input data type is not correct" + component.getType());
            throw new CustomException("Input data type is not correct" + component.getType());
        }
        int count = 0;
        List<Component> paragraphs = component.getAllComponents();
        for (Component paragraph : paragraphs) {
            List<Component> words = paragraph.getAllComponents().get(0).getAllComponents();
            int tmpStartSize = words.size();
            if (words.size() < minWords) {
                words.removeIf(s -> s.getAllComponents().size() < minWords);
                count++;
            }
        }
        logger.info(String.format("Has been removed %d sentences in the text.", count));
    }

    @Override
    public void sortParagraphsBySentences(Component component) throws CustomException {
        if (component.getType() != TEXT) {
            logger.error("Input data type is not correct" + component.getType());
            throw new CustomException("Input data type is not correct" + component.getType());
        }
        List<Component> paragraphs = component.getAllComponents();
        paragraphs.sort(new SentencesCountComparator());
    }

    @Override
    public int countEqualWords(Component component, String wordToSearch) throws CustomException {
        if (component.getType() != TEXT) {
            logger.error("Input data type is not correct" + component.getType());
            throw new CustomException("Input data type is not correct" + component.getType());
        }
        int count = 0;
        List<Component> paragraphs = component.getAllComponents();
        for (Component paragraph : paragraphs) {
            List<Component> sentences = paragraph.getAllComponents();
            for (Component sentence : sentences) {
                List<Component> words = sentence.getAllComponents();
                for (Component word : words) {
                    List<Component> nodes = word.getAllComponents();
                    StringBuilder sb = new StringBuilder();
                    String collectedWord = "";
                    for (Component node : nodes) {
                        if (node.getType() == SYMBOL_NODE) {
                            sb.append(node.toString());
                        }
                    }
                    collectedWord = sb.toString().toLowerCase();
                    if (collectedWord.equals(wordToSearch.toLowerCase())) {
                        count++;
                    }
                }
            }
        }
        logger.info(String.format("Has been counted equal words. Search word = '%s' , count = %d", wordToSearch, count));
        return count;
    }

    private int findMaxWordSizeInText(Component component) {
        int maxWordSize = 0;
        for (Component paragraph : component.getAllComponents()) {
            for (Component sentence : paragraph.getAllComponents()) {
                int tmp = findMaxWordSizeInSentence(sentence);
                if (maxWordSize < tmp) {
                    maxWordSize = tmp;
                }
            }
        }
        return maxWordSize;
    }

    private int findMaxWordSizeInSentence(Component component) {
        int maxWordSize = 0;
        for (Component word : component.getAllComponents()) {
            int tmp = 0;
            for (Component leaf : word.getAllComponents()) {
                if (leaf.getType() != PUNCTUATION_NODE) {
                    tmp++;
                }
                if (maxWordSize < tmp) {
                    maxWordSize = tmp;
                }
            }
        }
        return maxWordSize;
    }
}
