package eu.luminis.jmeter.assertions;

import org.apache.jmeter.assertions.AssertionResult;
import org.apache.jmeter.samplers.SampleResult;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BinaryContentAssertionTest {

    public static final byte[] CAFE_BABE = new byte[]{(byte) 0xca, (byte) 0xfe, (byte) 0xba, (byte) 0xbe};

    @Test
    public void responseContainsExpectedHexFormattedBytes() throws Exception {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonValue("0xfe 0xba");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertTrue(assertion.getResult(sampleResult));
    }

    @Test
    public void responseDoesNotContainExpectedHexFormattedBytes() throws Exception {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonValue("0x01 0x10");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertFalse(assertion.getResult(sampleResult));
    }

    @Test
    public void responseContainsExpectedStringFormattedBytes() throws Exception {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonValue("feba");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertTrue(assertion.getResult(sampleResult));
    }

    @Test
    public void responseDoesNotContainExpectedStringFormattedBytes() throws Exception {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonValue("0110");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertFalse(assertion.getResult(sampleResult));
    }

    @Test
    public void doesNotContainComparisonDoesIndeedNotContain() {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonType(BinaryContentAssertion.ComparisonType.NotContains);
        assertion.setComparisonValue("0xeb");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertTrue(assertion.getResult(sampleResult));
    }

    @Test
    public void doesNotContainComparisonDoesContain() {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonType(BinaryContentAssertion.ComparisonType.NotContains);
        assertion.setComparisonValue("0xbe");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertFalse(assertion.getResult(sampleResult));
    }

    @Test
    public void equalsComparisonIsIndeedEqual() {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonType(BinaryContentAssertion.ComparisonType.Equals);
        assertion.setComparisonValue("cafebabe");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertTrue(assertion.getResult(sampleResult));
    }

    @Test
    public void equalsComparisonIsNotEqual1() {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonType(BinaryContentAssertion.ComparisonType.Equals);
        assertion.setComparisonValue("cafebabebabe");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertFalse(assertion.getResult(sampleResult));
    }

    @Test
    public void equalsComparisonIsNotEqual2() {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonType(BinaryContentAssertion.ComparisonType.Equals);
        assertion.setComparisonValue("cafe");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertFalse(assertion.getResult(sampleResult));
    }

    @Test
    public void notEqualsComparisonIsIndeedNotEqual() {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonType(BinaryContentAssertion.ComparisonType.NotEquals);
        assertion.setComparisonValue("cafecafe");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertTrue(assertion.getResult(sampleResult));
    }

    @Test
    public void notEqualsComparisonIsEqualThough() {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonType(BinaryContentAssertion.ComparisonType.NotEquals);
        assertion.setComparisonValue("cafebabe");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertFalse(assertion.getResult(sampleResult));
    }

    @Test
    public void startWithComparisonIsTrue() {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonType(BinaryContentAssertion.ComparisonType.StartsWith);
        assertion.setComparisonValue("cafe");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertTrue(assertion.getResult(sampleResult));
    }

    @Test
    public void startWithComparisonIsEqual() {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonType(BinaryContentAssertion.ComparisonType.StartsWith);
        assertion.setComparisonValue("cafebabe");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertTrue(assertion.getResult(sampleResult));
    }

    @Test
    public void startWithComparisonIsFalse() {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonType(BinaryContentAssertion.ComparisonType.StartsWith);
        assertion.setComparisonValue("babe");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertFalse(assertion.getResult(sampleResult));
    }

    @Test
    public void startWithComparisonValueIsLonger() {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonType(BinaryContentAssertion.ComparisonType.StartsWith);
        assertion.setComparisonValue("cafebabe00");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertFalse(assertion.getResult(sampleResult));
    }

    @Test
    public void notStartsWithComparisonIsFalse() {
        BinaryContentAssertion assertion = new BinaryContentAssertion();
        assertion.setComparisonType(BinaryContentAssertion.ComparisonType.StartsWith);
        assertion.setComparisonValue("babe");

        SampleResult sampleResult = mock(SampleResult.class);
        when(sampleResult.getResponseData()).thenReturn(CAFE_BABE);

        assertFalse(assertion.getResult(sampleResult));
    }

    private void assertTrue(AssertionResult assertionResult) {
        Assert.assertFalse(assertionResult.isFailure());
        Assert.assertFalse(assertionResult.isError());
    }

    private void assertFalse(AssertionResult assertionResult) {
        Assert.assertTrue(assertionResult.isFailure());
        Assert.assertFalse(assertionResult.isError());
    }

}