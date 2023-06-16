package data;

import java.util.HashMap;
import java.util.Map;

public class EnumParser {
    public static Map<String, FormOfEducation> textToFormOfEducation = new HashMap<>() {{
        put("DISTANCE_EDUCATION", FormOfEducation.DISTANCE_EDUCATION);
        put("FULL_TIME_EDUCATION", FormOfEducation.FULL_TIME_EDUCATION);
        put("EVENING_CLASSES", FormOfEducation.EVENING_CLASSES);
    }};

    public static Map<FormOfEducation, String> formOfEducationToText = new HashMap<>() {{
        put(FormOfEducation.DISTANCE_EDUCATION, "DISTANCE_EDUCATION");
        put(FormOfEducation.FULL_TIME_EDUCATION, "FULL_TIME_EDUCATION");
        put(FormOfEducation.EVENING_CLASSES, "EVENING_CLASSES");
    }};

    public static Map<String, Semester> textToSemester = new HashMap<>() {{
        put("FIRST", Semester.FIRST);
        put("THIRD", Semester.THIRD);
        put("FOURTH", Semester.FOURTH);
        put("SIXTH", Semester.SIXTH);
    }};

    public static Map<Semester, String> semesterToText = new HashMap<>() {{
        put(Semester.FIRST, "FIRST");
        put(Semester.THIRD, "THIRD");
        put(Semester.FOURTH, "FOURTH");
        put(Semester.SIXTH, "SIXTH");
    }};

}
