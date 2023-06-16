package exchanger;

import java.io.Serializable;

public class SerializableCommand implements Serializable {
    private static final long serialVersionUID = 123456789L;
    private final String name;
    private final String argument;
    private final SerializableStudyGroup studyGroup;
    private final String answer;
    private final String username;
    private final String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public SerializableCommand(
        String name,
        String argument,
        SerializableStudyGroup studyGroup,
        String answer,
        String username,
        String password
    ) {
        this.name = name;
        this.argument = argument;
        this.studyGroup = studyGroup;
        this.answer = answer;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getArgument() {
        return argument;
    }

    public SerializableStudyGroup getStudyGroup() {
        return studyGroup;
    }

    public String getAnswer() {
        return answer;
    }
}
