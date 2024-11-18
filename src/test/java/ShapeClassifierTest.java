import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;


public class ShapeClassifierTest {
    private ShapeClassifier classifier;

    @BeforeEach
    void setUp() {
        classifier = new ShapeClassifier();
    }
    @Test
    @DisplayName("Test Line shape")
    void testLine() {
        assertEquals("Yes", classifier.evaluateGuess("Line,Small,Yes,5"));
    }

    @Test
    @DisplayName("Test Circle shape")
    void testCircle() {
        assertEquals("Yes", classifier.evaluateGuess("Circle,Small,No,1"));
    }

    @Test
    @DisplayName("Test Ellipse shape")
    void testEllipse() {
        assertEquals("Yes", classifier.evaluateGuess("Ellipse,Small,No,2,1"));
    }

    @Test
    @DisplayName("Test Equilateral Triangle")
    void testEquilateralTriangle() {
        assertEquals("Yes", classifier.evaluateGuess("Equilateral,Small,No,2,2,2"));
    }

    @Test
    @DisplayName("Test Isosceles Triangle")
    void testIsoscelesTriangle() {
        assertEquals("Yes", classifier.evaluateGuess("Isosceles,Small,No,2,2,3"));
    }

    @Test
    @DisplayName("Test Scalene Triangle")
    void testScaleneTriangle() {
        assertEquals("Yes", classifier.evaluateGuess("Scalene,Small,No,2,3,4"));
    }

    @Test
    @DisplayName("Test Rectangle")
    void testRectangle() {
        assertEquals("Yes", classifier.evaluateGuess("Rectangle,Small,Yes,2,3,2,3"));
    }

    @Test
    @DisplayName("Test Square")
    void testSquare() {
        assertEquals("Yes", classifier.evaluateGuess("Square,Small,Yes,2,2,2,2"));
    }

    @Test
    @DisplayName("Test incorrect shape guess")
    void testIncorrectShapeGuess() {
        assertTrue(classifier.evaluateGuess("Circle,Small,Yes,2,2").startsWith("No: Suggestion="));
    }

    @Test
    @DisplayName("Test incorrect size guess")
    void testIncorrectSizeGuess() {
        assertEquals("Yes: Wrong Size", classifier.evaluateGuess("Circle,Large,No,1"));
    }

    @Test
    @DisplayName("Test incorrect even/odd guess")
    void testIncorrectEvenOddGuess() {
        assertEquals("Yes: Wrong Even/Odd", classifier.evaluateGuess("Square,Small,No,2,2,2,2"));
    }

    @Test
    @DisplayName("Test large shape")
    void testLargeShape() {
        assertEquals("Yes", classifier.evaluateGuess("Square,Large,Yes,30,30,30,30"));
    }

    @Test
    @DisplayName("Test invalid triangle")
    void testInvalidTriangle() {
        assertTrue(classifier.evaluateGuess("Equilateral,Small,Yes,1,1,10").contains("Not A Triangle"));
    }

    @Test
    @DisplayName("Test bad guess limit")
    void testBadGuessLimit() {
        classifier.evaluateGuess("Circle,Small,Yes,2,2");
        classifier.evaluateGuess("Square,Small,Yes,2,2");
        assertThrows(RuntimeException.class, () -> classifier.evaluateGuess("Rectangle,Small,Yes,2,2"));
    }

    @Test
    @DisplayName("Test invalid input")
    void testInvalidInput() {
        assertEquals("No", classifier.evaluateGuess("InvalidShape,Small,Yes"));
    }

    @Test
    @DisplayName("Test parameter bounds")
    void testParameterBounds() {
        assertEquals("Yes", classifier.evaluateGuess("Square,Large,Yes,4096,4096,4096,4096"));
        assertEquals("Yes", classifier.evaluateGuess("Square,Small,Yes,0,0,0,0"));
    }

    @Test
    @DisplayName("Test Circle perimeter calculation")
    void testCirclePerimeterCalculation() {
        String result = classifier.evaluateGuess("Circle,Large,Yes,16");
        assertTrue(result.startsWith("Yes"));
    }

    @Test
    @DisplayName("Test Ellipse perimeter calculation")
    void testEllipsePerimeterCalculation() {
        String result = classifier.evaluateGuess("Ellipse,Large,Yes,20,10");
        assertTrue(result.startsWith("Yes"));
    }

    @Test
    @DisplayName("Test Triangle perimeter calculation")
    void testTrianglePerimeterCalculation() {
        String result = classifier.evaluateGuess("Scalene,Large,Yes,30,40,50");
        assertTrue(result.startsWith("Yes"));
    }

    @Test
    @DisplayName("Test Rectangle perimeter calculation")
    void testRectanglePerimeterCalculation() {
        String result = classifier.evaluateGuess("Rectangle,Large,Yes,30,40,30,40");
        assertTrue(result.startsWith("Yes"));
    }

    @Test
    @DisplayName("Test Square perimeter calculation")
    void testSquarePerimeterCalculation() {
        String result = classifier.evaluateGuess("Square,Large,Yes,25,25,25,25");
        assertTrue(result.startsWith("Yes"));
    }
}