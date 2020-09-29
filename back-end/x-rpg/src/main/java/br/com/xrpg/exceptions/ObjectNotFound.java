package br.com.xrpg.exceptions;

public class ObjectNotFound extends Exception{

    private static final long serialVersionUID = 1L;

    public ObjectNotFound(String errorMsg) { super(errorMsg); }
}
