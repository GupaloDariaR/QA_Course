package Lesson_4;

public interface CalculateFigure {
    default double calculatePerimeter(int[] figureParameters) {
        double perimeter = 0;
        for (int parameter : figureParameters) {
            perimeter += parameter;
        }
        return perimeter;
    }

    double calculateArea();
}
