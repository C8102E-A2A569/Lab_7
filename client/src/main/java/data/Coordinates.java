package data;

import exceptions.InputDataException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Coordinates data-class
 */
public class Coordinates implements Validatable, Serializable {
    private static final long serialVersionUID = 123456789L;
    private Long x; //Поле не может быть null
    private long y; //Максимальное значение поля: 30


    /**
     * @param br Buffered reader to read data
     * @param bw Buffered writer to write data
     * @throws InputDataException
     * @throws IOException
     */
    public Coordinates(BufferedReader br, BufferedWriter bw) throws InputDataException, IOException {
        while (true) {
            if (bw != null) {
                bw.write("Enter x coordinate: ");
                bw.flush();
            }
            try {
                x = Long.parseLong(br.readLine());
                break;
            } catch (NumberFormatException ex) {
                if (bw == null) {
                    throw new InputDataException("Not valid x coordinate!");
                }
                bw.write("Not valid data! Enter again!\n");
                bw.flush();
            }
        }
        while (true) {
            if (bw != null) {
                bw.write("Enter y (y <= 30) coordinate: ");
                bw.flush();
            }
            try {
                y = Long.parseLong(br.readLine());
                if (y > 30) {
                    if (bw == null) {
                        throw new InputDataException("Not valid y coordinate!");
                    }
                    bw.write("Not valid data! Enter again!\n");
                    continue;
                }
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Not valid data! Enter again!");
            }
        }
    }
    public Coordinates(Long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{" + x.toString() + ";" + y + "}";
    }

    @Override
    public boolean validate() {
        return (x != null && y <= 30);
    }
}
