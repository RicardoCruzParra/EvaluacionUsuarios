package com.evaluacion.usuarios.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorPatrones{

    private static final String PASSWORD_REGEX = "^(?=.*[0-9]{2})(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,16}$";
    private static final String EMAIL_REGEX = "(\\W|^)[\\w.\\-]{0,25}@(dominio)\\.cl(\\W|$)";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean emailValidator(String email)
    {
        if (email == null)
        {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean passwordValidator(String password)
    {
        if (password == null)
        {
            return false;
        }
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }
}
