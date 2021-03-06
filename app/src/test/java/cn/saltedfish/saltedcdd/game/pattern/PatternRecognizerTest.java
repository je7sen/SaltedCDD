package cn.saltedfish.saltedcdd.game.pattern;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.pattern.CardGroup;
import cn.saltedfish.saltedcdd.game.pattern.EPatternType;
import cn.saltedfish.saltedcdd.game.pattern.PatternRecognizer;

public class PatternRecognizerTest {
    public static List<Card> createCardList(String... cardDescArray)
    {
        ArrayList<Card> cards = new ArrayList<>();
        for (String cardDesc : cardDescArray)
        {
            Card card = Card.fromString(cardDesc);
            if (card != null)
            {
                cards.add(card);
            }
        }
        return cards;
    }

    @Test
    public void TestUnknownRecognition()
    {
        List<Card> cards = null;
        CardGroup group = null;

        cards = createCardList("♥10", "♥3");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.Unknown, group.getType());
    }

    @Test
    public void TestSingleRecognition()
    {
        List<Card> cards = null;
        CardGroup group = null;

        cards = createCardList("♠3");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.Single, group.getType());

        cards = createCardList("♥10");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.Single, group.getType());

        cards = createCardList("♥10", "♠10");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.Single, group.getType());
    }

    @Test
    public void TestPairRecognition()
    {
        List<Card> cards = null;
        CardGroup group = null;

        cards = createCardList("♥10", "♠10");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.Pair, group.getType());

        cards = createCardList("♥10", "♠9");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.Pair, group.getType());
    }

    @Test
    public void TestTripleRecognition()
    {
        List<Card> cards = null;
        CardGroup group = null;

        cards = createCardList("♥10", "♠10", "♦10");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.Triple, group.getType());

        cards = createCardList("♥10", "♠10", "♦10", "♣10");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.Triple, group.getType());

        cards = createCardList("♥10", "♠10", "♦9");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.Triple, group.getType());
    }

    @Test
    public void TestQuadrupleRecognition()
    {
        List<Card> cards = null;
        CardGroup group = null;

        cards = createCardList("♥10", "♠10", "♦10", "♣10");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.Quadruple, group.getType());

        cards = createCardList("♥10", "♠10", "♦10");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.Quadruple, group.getType());

        cards = createCardList("♥10", "♠10", "♦9", "♣10");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.Quadruple, group.getType());
    }

    @Test
    public void TestStraightRecognition() // 顺子
    {
        List<Card> cards = null;
        CardGroup group = null;

        cards = createCardList("♥3", "♠4", "♦5", "♠6", "♦7");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.Straight, group.getType());

        cards = createCardList("♥2", "♠A", "♦3", "♠4", "♦5");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.Straight, group.getType());

        cards = createCardList("♥3", "♠4", "♦5", "♠6", "♦8");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.Straight, group.getType());

        cards = createCardList("♥3", "♠4", "♦5", "♠6");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.Straight, group.getType());
    }

    @Test
    public void TestFlushRecognition() // 同花
    {
        List<Card> cards = null;
        CardGroup group = null;

        cards = createCardList("♥3", "♥4", "♥5", "♥8", "♥10");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.Flush, group.getType());

        cards = createCardList("♥3", "♥4", "♥5", "♥6", "♥7");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.Flush, group.getType());
    }

    @Test
    public void TestStraightFlushRecognition() // 同花顺
    {
        List<Card> cards = null;
        CardGroup group = null;

        cards = createCardList("♥3", "♥4", "♥5", "♥6", "♥7");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.StraightFlush, group.getType());

        cards = createCardList("♥3", "♥4", "♥5", "♥8", "♥10");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.StraightFlush, group.getType());

        cards = createCardList("♥3", "♥4", "♥5", "♣6", "♥7");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.StraightFlush, group.getType());
    }

    @Test
    public void TestFourOfAKindRecognition() // 四带一
    {
        List<Card> cards = null;
        CardGroup group = null;

        cards = createCardList("♥3", "♠3", "♦3", "♣3", "♥7");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.FourOfAKind, group.getType());

        cards = createCardList("♥3", "♥4", "♠4", "♦4", "♣4");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.FourOfAKind, group.getType());

        cards = createCardList("♥3", "♠3", "♠4", "♦4", "♣4");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.FourOfAKind, group.getType());

        cards = createCardList("♥3", "♥4", "♥5", "♣6", "♥7");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.FourOfAKind, group.getType());
    }

    @Test
    public void TestFullHouseRecognition() // 三带二
    {
        List<Card> cards = null;
        CardGroup group = null;

        cards = createCardList("♥3", "♠3", "♦3", "♠7", "♥7");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.FullHouse, group.getType());

        cards = createCardList("♥3", "♠3", "♠7", "♦7", "♣7");
        group = PatternRecognizer.recognize(cards);
        assertEquals(EPatternType.FullHouse, group.getType());

        cards = createCardList("♥3", "♠4", "♠4", "♦4", "♣4");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.FullHouse, group.getType());

        cards = createCardList("♥3", "♥4", "♥5", "♣6", "♥7");
        group = PatternRecognizer.recognize(cards);
        assertNotEquals(EPatternType.FullHouse, group.getType());
    }
}
