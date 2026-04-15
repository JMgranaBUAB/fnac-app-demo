package com.bcp1IO.fnac.exception;

public class ObjectNotFoundException extends RuntimeException{

//    public ObjectNotFoundException(String message){
//
//        super(message);
//
//    }

    public ObjectNotFoundException(String objectName, int id){

        super("No se puede encontrar en la base de datos el objeto : " + objectName + ", con el id : " + id);

    }

}
