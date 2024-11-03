package vn.edu.fpt.fractalapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class FractalView extends View {
    private int level = 0;
    private Paint paint;
    private int color;

    public FractalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        color = 0xFF000000; // Mặc định là màu đen
        paint.setColor(color);
    }

    public void setLevel(int level) {
        this.level = level;
        invalidate(); // Yêu cầu vẽ lại
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        Point a = new Point(width / 2, 0);
        Point b = new Point(0, height);
        Point c = new Point(width, height);

        drawSierpinskiTriangle(canvas, level, a, b, c);
    }

    private void drawSierpinskiTriangle(Canvas canvas, int level, Point a, Point b, Point c) {
        if (level == 0) {
            Paint paintFill = new Paint(paint);
            paintFill.setStyle(Paint.Style.FILL);

            android.graphics.Path path = new android.graphics.Path();
            path.moveTo(a.x, a.y);
            path.lineTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.close();
            canvas.drawPath(path, paintFill);
        } else {
            Point ab = midpoint(a, b);
            Point bc = midpoint(b, c);
            Point ca = midpoint(c, a);

            drawSierpinskiTriangle(canvas, level - 1, a, ab, ca);
            drawSierpinskiTriangle(canvas, level - 1, ab, b, bc);
            drawSierpinskiTriangle(canvas, level - 1, ca, bc, c);
        }
    }

    private Point midpoint(Point p1, Point p2) {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }
}
