package vn.edu.fpt.snakegameapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

public class SnakeView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Paint paint;

    private int screenWidth;
    private int screenHeight;

    private int blockSize = 30;
    private int numBlocksWidth;
    private int numBlocksHeight;

    private ArrayList<Coordinate> snake;
    private Coordinate food;

    private enum Direction {UP, DOWN, LEFT, RIGHT}
    private Direction direction = Direction.RIGHT;

    private long nextFrameTime;
    private final long FPS = 10;
    private final long MILLIS_PER_SECOND = 1000;

    public SnakeView(Context context, int screenWidth, int screenHeight) {
        super(context);

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        surfaceHolder = getHolder();
        paint = new Paint();

        numBlocksWidth = screenWidth / blockSize;
        numBlocksHeight = screenHeight / blockSize;

        startGame();
    }

    public void startGame() {
        snake = new ArrayList<>();
        snake.add(new Coordinate(numBlocksWidth / 2, numBlocksHeight / 2));

        spawnFood();

        direction = Direction.RIGHT;
        nextFrameTime = System.currentTimeMillis();
        isPlaying = true;
    }

    public void spawnFood() {
        Random random = new Random();
        int x = random.nextInt(numBlocksWidth - 1) + 1;
        int y = random.nextInt(numBlocksHeight - 1) + 1;
        food = new Coordinate(x, y);
    }

    @Override
    public void run() {
        while (isPlaying) {
            if (updateRequired()) {
                updateGame();
                drawGame();
            }
        }
    }

    public boolean updateRequired() {
        if (nextFrameTime <= System.currentTimeMillis()) {
            nextFrameTime = System.currentTimeMillis() + MILLIS_PER_SECOND / FPS;
            return true;
        }
        return false;
    }

    public void updateGame() {
        // Di chuyển thân rắn
        for (int i = snake.size() - 1; i > 0; i--) {
            snake.get(i).x = snake.get(i - 1).x;
            snake.get(i).y = snake.get(i - 1).y;
        }

        // Di chuyển đầu rắn
        Coordinate head = snake.get(0);
        switch (direction) {
            case UP:
                head.y--;
                break;
            case DOWN:
                head.y++;
                break;
            case LEFT:
                head.x--;
                break;
            case RIGHT:
                head.x++;
                break;
        }

        // Kiểm tra va chạm tường
        if (head.x < 0 || head.x >= numBlocksWidth || head.y < 0 || head.y >= numBlocksHeight) {
            gameOver();
            return;
        }

        // Kiểm tra va chạm với thân
        for (int i = 1; i < snake.size(); i++) {
            if (head.x == snake.get(i).x && head.y == snake.get(i).y) {
                gameOver();
                return;
            }
        }

        // Kiểm tra ăn thức ăn
        if (head.x == food.x && head.y == food.y) {
            snake.add(new Coordinate(-1, -1)); // Thêm phần tử mới vào đuôi
            spawnFood();
        }
    }

    public void drawGame() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            paint.setColor(Color.GREEN);
            for (Coordinate c : snake) {
                canvas.drawRect(c.x * blockSize, c.y * blockSize, (c.x * blockSize) + blockSize, (c.y * blockSize) + blockSize, paint);
            }

            paint.setColor(Color.RED);
            canvas.drawRect(food.x * blockSize, food.y * blockSize, (food.x * blockSize) + blockSize, (food.y * blockSize) + blockSize, paint);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void gameOver() {
        isPlaying = false;
        // Hiển thị thông báo hoặc xử lý khi game kết thúc
        startGame(); // Bắt đầu lại trò chơi
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                float x = event.getX();
                float y = event.getY();

                if (direction == Direction.UP || direction == Direction.DOWN) {
                    if (x >= screenWidth / 2) {
                        direction = Direction.RIGHT;
                    } else {
                        direction = Direction.LEFT;
                    }
                } else {
                    if (y >= screenHeight / 2) {
                        direction = Direction.DOWN;
                    } else {
                        direction = Direction.UP;
                    }
                }
        }
        return true;
    }

    public void pause() {
        isPlaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // Xử lý ngoại lệ
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    private class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
