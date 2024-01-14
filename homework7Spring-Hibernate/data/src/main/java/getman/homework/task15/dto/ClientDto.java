package getman.homework.task15.dto;


import java.io.Serializable;

public class ClientDto implements Serializable {
    private String id;
    private String name;
    private String surname;


    private UserDto userDto;


    public ClientDto(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public ClientDto setUserDto(UserDto userDto) {
        this.userDto = userDto;
        return this;
    }
}
