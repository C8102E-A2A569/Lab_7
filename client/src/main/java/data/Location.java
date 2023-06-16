package data;

import exceptions.InputDataException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Location data-class
 */
public class Location implements Validatable, Serializable {
    private static final long serialVersionUID = 123456789L;
    private int x;
    private Long y; //Поле не может быть null
    private Integer z; //Поле не может быть null
    private String name; //Строка не может быть пустой, Поле может быть null


    public Location(int x, Long y, Integer z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    /**
     * @param br Buffered reader to read data
     * @param bw Buffered writer to write data
     * @throws InputDataException
     * @throws IOException
     */
    public Location(BufferedReader br, BufferedWriter bw) throws InputDataException, IOException {
        while (true) {
            if (bw != null) {
                bw.write("Enter x coordinate: ");
                bw.flush();
            }
            try {
                x = Integer.parseInt(br.readLine());
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
                bw.write("Enter y coordinate: ");
                bw.flush();
            }
            try {
                y = Long.parseLong(br.readLine());
                break;
            } catch (NumberFormatException ex) {
                if (bw == null) {
                    throw new InputDataException("Not valid y coordinate!");
                }
                bw.write("Not valid data! Enter again!\n");
                bw.flush();
            }
        }
        while (true) {
            if (bw != null) {
                bw.write("Enter z coordinate: ");
                bw.flush();
            }
            try {
                z = Integer.parseInt(br.readLine());
                break;
            } catch (NumberFormatException ex) {
                if (bw == null) {
                    throw new InputDataException("Not valid z coordinate!");
                }
                bw.write("Not valid data! Enter again!\n");
                bw.flush();
            }
        }
        if (bw != null) {
            bw.write("Enter location's name: ");
            bw.flush();
        }
        name = br.readLine();
        if (name.isEmpty()) {
            name = null;
        }
    }

    public int getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ": {" + x + ";" + y.toString() + ";" + z.toString() + "}";
    }

    @Override
    public boolean validate() {
        return (y != null && z != null && (name == null || !name.isEmpty()));
    }
}
