package Entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String name;

    @Email(message = "Ingrese un email valido")
    private String email;

    @NotEmpty(message = "El sexo es obligatorio")
    @Pattern(regexp = "^(male|female|other)$", message = "El sexo debe ser 'male', 'female' o 'other'")
    private String sex;

    @NotEmpty(message = "El perfil es obligatorio")
    @URL(message = "El perfil debe ser una URL válida")
    private String avatar;

    @NotEmpty(message = "El numero de la tarjeta es obligatorio")
    private String cardNumber;

    @NotEmpty(message = "El CVV es obligatorio")
    @Min(value = 100, message = "El CVV debe tener al menos 3 dígitos")
    @Max(value = 9999, message = "El CVV no puede tener más de 4 dígitos")
    private String cardCvv;

    @JsonIgnore
    private Integer stattusCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }

    @JsonGetter("cardNumber")
    public String getMaskedCardNumber() {
        return maskSensitiveData(cardNumber);
    }

    @JsonGetter("cardCvv")
    public String getMaskedCardcvv() {
        return maskNumber(cardCvv);
    }

    public static String maskNumber(String number) {
        return number.replaceAll("[0-9]", "9");
    }

    public static String maskSensitiveData(String data) {
        return data.replaceAll("^(.{3})(.+)", "$1" + "*".repeat(data.length() - 2));
    }

    public Integer getStattusCode() {
        return stattusCode;
    }

    public void setStattusCode(Integer stattusCode) {
        this.stattusCode = stattusCode;
    }
}
