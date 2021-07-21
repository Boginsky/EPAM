package service;

import entity.Component;
import exception.CustomException;

import java.util.List;

public interface ComponentService {
    long countVowels(Component component) throws CustomException;

    long countConsonants(Component component) throws CustomException;

    List<Component> findSentencesOfMaxWord(Component component) throws CustomException;

    void removeSentencesWithMinWords(Component component, int minWords) throws CustomException;

    void sortParagraphsBySentences(Component component) throws CustomException;

    int countEqualWords(Component component, String wordToSearchFor) throws CustomException;
}
