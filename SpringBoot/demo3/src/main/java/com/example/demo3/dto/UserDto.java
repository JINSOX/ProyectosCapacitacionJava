package com.example.demo3.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public class UserDto {

    private Integer id;

    @JsonAlias("name")
    @NotEmpty(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @JsonAlias("sex")
    @NotEmpty(message = "El sexo es obligatorio")
    @Pattern(regexp = "^(male|female|other)$", message = "El sexo debe ser 'male', 'female' o 'other'")
    private String sexo;

    @JsonAlias("country")
    @NotEmpty(message = "El país es obligatorio")
    private String pais;

    @JsonAlias("avatar")
    @NotEmpty(message = "El perfil es obligatorio")
    @URL(message = "El perfil debe ser una URL válida")
    private String perfil;

    @NotEmpty(message = "El nombre de la tarjeta es obligatorio")
    private String cardname;

    @NotNull(message = "El CVV es obligatorio")
    @Min(value = 100, message = "El CVV debe tener al menos 3 dígitos")
    @Max(value = 9999, message = "El CVV no puede tener más de 4 dígitos")
    private Integer cardcvv;

    public UserDto() {
    }

    public UserDto(Integer id, String nombre, String sexo, String pais, String avatar, String cardname, Integer cardcvv) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.pais = pais;
        this.perfil = avatar;
        this.cardname = cardname;
        this.cardcvv = cardcvv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public void setCardcvv(Integer cardcvv) {
        this.cardcvv = cardcvv;
    }

    @JsonGetter("cardname")
    public String getMaskedCardname() {
        return maskSensitiveData(cardname);
    }

    @JsonGetter("cardcvv")
    public String getMaskedCardcvv() {
        return maskNumber(cardcvv);
    }

    public static String maskNumber(Integer number) {
        return number.toString().replaceAll("[0-9]", "9");
    }

    public static String maskSensitiveData(String data) {
        return data.replaceAll(".", "*");
    }
}
