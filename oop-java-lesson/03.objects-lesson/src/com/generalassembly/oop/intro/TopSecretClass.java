package com.generalassembly.oop.intro;

public class TopSecretClass {
    protected String getTopSecretPassword() {
        return "pa55w0rd";
    }
}

class PasswordExposer extends TopSecretClass {
    @Override
    public String getTopSecretPassword() {
        return super.getTopSecretPassword();
    }

    public static void main(String[] args) {
        TopSecretClass tsc = new PasswordExposer();
        String password = tsc.getTopSecretPassword();
    }
}

