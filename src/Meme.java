import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Meme {
    static BufferedImage image;
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        while(true){
            String command = in.nextLine();
            if (Objects.equals(command, "HELP")){
                help();
            }
            else if (Objects.equals(command, "MEM")){
                mem();
            }
            else if (Objects.equals(command, "EXIT")){
                break;

            }
            else{
                System.out.println("Команда не распознана");
            }


        }
        in.close();
    }



    public static void help(){
        System.out.println("#######################");
        System.out.println("При вводе команды MEM программа сама вас направит, главное знать следующее:");
        System.out.println("Доступно 3 шрифта: PLAIN, ITALIC, BOLD");
        System.out.println("Доступно 3 положения надписи: Top, Center, Bottom");
        System.out.println("Доступно 3 цвета: White, Black, Red");
        System.out.println("Чтобы завершить программу введите слово EXIT");
        System.out.println("#######################");
    }

    public static void mem(){
        System.out.println("Укажите абсолютный путь у интересующей вас картинке:");
        String str = in.nextLine();
        try {
            image = ImageIO.read(new File(str));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Введите текст:");
        str = in.nextLine();

        System.out.println("Введите размер шрифта: ");

        float font = in.nextFloat();
        in.nextLine();

        System.out.println("Какой шрифт предпочитаете?");

        String gugli = in.nextLine();

        int type = Font.PLAIN;
        switch(gugli) {
            case "BOLD" -> {
                type = Font.BOLD;
            }
            case "ITALIC" -> {
                type = Font.ITALIC;
            }
            case "PLAIN" -> {
                type = Font.PLAIN;
            }
            default -> {
                System.out.println("Команда не распознана, установлен обычный шрифт");
                System.out.println("asdsfj");
            }
        }
        System.out.println("Где желаете разместить текст?");

        gugli = in.nextLine();
        int x = image.getWidth() / 2;
        int y;
        switch(gugli) {
            case "Center" -> {
                y = image.getHeight() / 2;
            }
            case "Top" -> {
                y = image.getHeight() / 4;
            }
            case "Bottom" -> {
                y = 3 * image.getHeight() / 4;
            }
            default -> {
                y = image.getHeight();
                System.out.println("Команда не распознана, выбраны стандартные координаты");
            }
        }
        System.out.println("Выберите цвет текста");

        Color c;
        gugli = in.nextLine();
        switch(gugli) {
            case "White" -> {
                c = Color.WHITE;
            }
            case "Black" -> {
                c = Color.BLACK;
            }
            case "Red" -> {
                c = Color.RED;
            }
            default -> {
                c = Color.BLACK;
                System.out.println("Команда не распознана, выбран стандартный цвет");
            }
        }


        Graphics g = image.createGraphics();
        g.setFont(g.getFont().deriveFont(type, font));
        g.setColor(c);
        g.drawString(str,x, y);
        g.dispose();
        try {
            ImageIO.write(image, "jpg", new File("image.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
 }
