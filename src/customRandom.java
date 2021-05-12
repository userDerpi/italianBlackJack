/**
 * @(#)Acaso.java
 *
 *
 * @author F. Sacco
 * @version 1.00 2018/4/12
 */

import java.util.Random;

public class customRandom {

    public static int random(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

}