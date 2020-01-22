package com.rafael.desafioSpring.validator;

import java.util.Calendar;
import java.util.Date;

public class DataEventoValidator {

    public static Boolean validarDataHora(Date horaInicio, Date horaFim){
        
        Long timeInicio = horaInicio.getTime();
        Long timeFim = horaFim.getTime();

        if(timeFim - timeInicio <= 0) return false;
        if(timeInicio < new Date().getTime() + 86400000) return false;

        Calendar inicio = Calendar.getInstance();
        Calendar fim = Calendar.getInstance();

        inicio.setTime(horaInicio);
        fim.setTime(horaFim);

        if(inicio.get(Calendar.DAY_OF_MONTH) != fim.get(Calendar.DAY_OF_MONTH)) return false;
        if(inicio.get(Calendar.MONTH) != fim.get(Calendar.MONTH)) return false;
        if(inicio.get(Calendar.YEAR) != fim.get(Calendar.YEAR)) return false;

        return true;

    }  

    public static Boolean validarFormatoData(String dataString){

        for(int i = 0; i < 100; i++){
            System.out.println(dataString);
        }

        return true;

    }

}