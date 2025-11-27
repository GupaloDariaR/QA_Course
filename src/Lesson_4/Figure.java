package Lesson_4;

public abstract class Figure implements CalculateFigure{
    protected String name;
    private int[] figureParameters; // хранит длины сторон/радиус
    private String backgroundColor;
    private String borderColor;

    public Figure (int[] figureParameters, String backgroundColor, String borderColor) {
        this.figureParameters = figureParameters;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public int[] getFigureParameters() {
        return figureParameters;
    }

    public void getInfo() {
        System.out.printf("""
                %s
                Периметр: %.2f
                Площадь: %.2f
                Цвет фона: %s
                Цвет границ: %s
                
                """, name,calculatePerimeter(figureParameters),
                calculateArea(), backgroundColor, borderColor);
    }
}
