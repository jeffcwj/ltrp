package lt.ltrp.data;

/**
 * @author Bebras
 *         2015.11.13.
 */
public class Color extends net.gtaun.shoebill.data.Color {

    public static final net.gtaun.shoebill.data.Color LIGHTRED = new net.gtaun.shoebill.data.Color(0xFF, 0x63, 0x47);
    public static final net.gtaun.shoebill.data.Color NEWS = new net.gtaun.shoebill.data.Color(0xFF, 0xA5, 0x00);
    public static final net.gtaun.shoebill.data.Color ACTION = new net.gtaun.shoebill.data.Color(0xC2, 0xA2, 0xDA); //C2 A2 DA AA
    public static final net.gtaun.shoebill.data.Color SMS_SENT = new net.gtaun.shoebill.data.Color(0x00, 0x00, 0x00);
    public static final net.gtaun.shoebill.data.Color SMS_RECEIVED = new net.gtaun.shoebill.data.Color(0xF5, 0xDE, 0xB3);  //0xF5DEB3AA
    public static final net.gtaun.shoebill.data.Color MEGAPHONE = new net.gtaun.shoebill.data.Color(0x42, 0x61, 0xCC); //0x4261CCFF
    public static final net.gtaun.shoebill.data.Color POLICE = new net.gtaun.shoebill.data.Color(0x35, 0xA5, 0xCA); // 0x35A5CAFF

    public Color(int value) {
        super(value);
    }

    public Color(int r, int g, int b) {
        super(r, g, b);
    }

    public Color(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    public Color(net.gtaun.shoebill.data.Color color) {
        super(color);
    }

    public Color() {
    }
}