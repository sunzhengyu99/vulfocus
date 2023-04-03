package com.sunzy.vulfocus.common;

public class ErrorClass {
    public static final Exception ContainerNotOneException = new RuntimeException("One image has two containers for user!");
    public static final Exception FileExistsException = new RuntimeException("File already exists!");
    public static final Exception FileNotExistsException = new RuntimeException("File does not exists!");
    public static final Exception FileCannotRWException = new RuntimeException("File cannot read or write!");
}
