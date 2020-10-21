package br.com.xrpg.exceptions;

public class ErrorSalvamento extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ErrorSalvamento(String errorMsg) { super(errorMsg); }




    public ErrorSalvamento(String errorMsg,String motivoMsg) {
        super(formataMensagem(errorMsg, motivoMsg));

    }

    private static String formataMensagem(String errorMsg,String motivoMsg) {
        return "Houve erro durante a tentativa de salvamento. MOTIVO: " + motivoMsg;
    }
}