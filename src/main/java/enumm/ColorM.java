package enumm;

// enum in Java
enum Color {
    RED, BLACK, GREEN; // list of constants
}

//class Color { // internally implemented by using Class
//    public static final Color RED = new Color();
//    public static final Color BLACK = new Color();
//    public static final Color GREEN = new Color();
//}

public class ColorM {
    Color color;

    public ColorM(Color color) {
        this.color = color;
    }

    //prints a line about Color by switch
    public void colorIsLike() {
        switch (color) {
            case BLACK:
                System.out.println("A Black car");
                break;
            case RED:
                System.out.println("A Red lamp");
                break;
            case GREEN:
                System.out.println("A green window");
                break;
            default:
                System.out.println("By default");
                break;
        }
    }

    public static void main(String[] args) {
        Color color = Color.BLACK;
        System.out.println(color);

        String str = "RED";
        ColorM colorM = new ColorM(Color.valueOf(str));
        colorM.colorIsLike();
    }
}
