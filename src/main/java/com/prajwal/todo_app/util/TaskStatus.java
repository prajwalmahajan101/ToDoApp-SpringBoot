package com.prajwal.todo_app.util;

public enum TaskStatus {
    complete,
    in_progress,
    on_hold,
    not_started;

    public static TaskStatus convert(String s) throws CustomException {
        try{
            return TaskStatus.valueOf(s);
        }catch (IllegalArgumentException e){
            throw CustomException.invalidInput("Invalid Value for Status");
        }
    }


}
