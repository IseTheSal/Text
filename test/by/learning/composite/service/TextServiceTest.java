package by.learning.composite.service;

import by.learning.composite.component.TextComponent;
import by.learning.composite.reader.CustomFileReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Set;

public class TextServiceTest {

    TextService textService = new TextService();
    final String DEFAULT_PATH = "data\\text.txt";
    final String DEFAULT_TEXT = "Hello. How are you?\tHi. I am fine. Thanks. \tBye.\tOk, Bye.";
    final int WORDS_AMOUNT = 30;
    final String EXPECTED_SENTENCE_WITH_LONGEST_WORDS = "[The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c)," +
            " as opposed to using (Content here), content here's, making it look like readable English?]";
    final String EXPECTED_SENTENCE_BY_WORD_AMOUNT = "It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently "+
            "with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!";


    @Test
    public void testCountSimilarWords() {
        CustomFileReader customFileReader = new CustomFileReader();
        String text = customFileReader.readFile(DEFAULT_PATH);
        try {
            HashMap<String, Integer> allSimilarWords = textService.findAllSimilarWords(text);
        } catch (IllegalAccessException e) {
            Assert.fail();
        }
    }

    @Test
    public void testFindSentencesWithLongestWords() {
        CustomFileReader customFileReader = new CustomFileReader();
        String text = customFileReader.readFile(DEFAULT_PATH);
        String expected = EXPECTED_SENTENCE_WITH_LONGEST_WORDS;
        Set<TextComponent> sentencesWithLongestWords = textService.findSentencesWithLongestWords(text);
        String actual = sentencesWithLongestWords.toString();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testRemoveSentenceByWordAmount() {
        CustomFileReader customFileReader = new CustomFileReader();
        String text = customFileReader.readFile(DEFAULT_PATH);
        String expected = EXPECTED_SENTENCE_BY_WORD_AMOUNT;
        String actual = textService.removeSentenceByWordAmount(text, WORDS_AMOUNT);
        Assert.assertEquals(actual, expected);
    }
}