package others.cat.species;

import others.cat.BigCat;

class Lynx extends BigCat {
    public static void main(String[] args) {
        Lynx cat = new Lynx();
        System.out.println(cat.hasFur); // we can access only through the instance
    }
}
