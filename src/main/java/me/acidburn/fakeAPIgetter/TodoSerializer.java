package me.acidburn.fakeAPIgetter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.ArrayList;

public class TodoSerializer {
    private ObjectMapper om;
    ArrayList<Todo> todos;

    public ArrayList<Todo> getTodos() {
        return todos;
    }

    public TodoSerializer(String resp) throws JsonProcessingException {
        om = new ObjectMapper();
        todos = om.readValue(resp, new TypeReference<ArrayList<Todo>>(){});

    }



}
