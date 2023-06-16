package data;

import exceptions.InputDataException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Person data-class
 */
public class Person implements Validatable, Serializable {
    private static final long serialVersionUID = 123456789L;
    private String name; // Поле не может быть null, Строка не может быть пустой
    private double height; // Значение поля должно быть больше 0
    private double weight; // Значение поля должно быть больше 0
    private String passportID; // Длина строки не должна быть больше 31, Значение этого поля должно быть уникальным, Длина строки должна быть не меньше 8, Поле может быть null
    private Location location; // Поле может быть null

    /**
     * @param br Buffered reader to read data
     * @param bw Buffered writer to write data
     * @throws InputDataException
     * @throws IOException
     */
    public Person(BufferedReader br, BufferedWriter bw) throws InputDataException, IOException {
        while (true) {
            if (bw != null) {
                bw.write("Enter person's name: ");
                bw.flush();
            }
            name = br.readLine();
            if (!name.isEmpty()) {
                break;
            }
            if (bw == null) {
                throw new InputDataException("Name cannot be empty! Enter data again!");
            }
            bw.write("Name cannot be empty! Enter data again!\n");
            bw.flush();
        }
        while (true) {
            if (bw != null) {
                bw.write("Enter person's height (height > 0): ");
                bw.flush();
            }
            try {
                height = Double.parseDouble(br.readLine());
                if (height > 0) {
                    break;
                }
                if (bw == null) {
                    throw new InputDataException("Not valid height!");
                }
                bw.write("Not valid height! Enter data again!\n");
                bw.flush();
            } catch (NumberFormatException ex) {
                if (bw == null) {
                    throw new InputDataException("Not valid height!");
                }
                bw.write("Not valid height! Enter data again!\n");
                bw.flush();
            }
        }
        while (true) {
            if (bw != null) {
                bw.write("Enter person's weight (height > 0): ");
                bw.flush();
            }
            try {
                weight = Double.parseDouble(br.readLine());
                if (weight > 0) {
                    break;
                }
                if (bw == null) {
                    throw new InputDataException("Not valid weight!");
                }
                bw.write("Not valid weight! Enter data again!\n");
                bw.flush();
            } catch (NumberFormatException ex) {
                if (bw == null) {
                    throw new InputDataException("Not valid weight!");
                }
                bw.write("Not valid weight! Enter data again!\n");
                bw.flush();
            }
        }
        while (true) {
            if (bw != null) {
                bw.write("Enter person's passport id (8 <= length <= 31): ");
                bw.flush();
            }
            passportID = br.readLine();
            if (passportID.length() >= 8 && passportID.length() <= 31) {
                break;
            }
            if (bw == null) {
                throw new InputDataException("Not valid passport id!");
            }
            bw.write("Not valid passport id! Enter data again!\n");
            bw.flush();
        }
        location = new Location(br, bw);
    }

    public Person(String name, double height, double weight, String passportID, Location location) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Name: " + name + '\n' +
                "Height: " + height + '\n' +
                "Weight: " + weight + '\n' +
                "Passport ID: " + passportID + '\n' +
                "Location: " + location.toString();
    }

    @Override
    public boolean validate() {
        return name != null && !name.isEmpty() && height > 0 && weight > 0
                && (passportID == null || 8 <= passportID.length() && passportID.length() <= 31)
                && location != null && location.validate();
    }
}

