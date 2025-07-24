package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String str = """
        {
            "address": {
                "street": "123 Main St",
                "city": "New York",
                "state": "NY",
                "zip": "10001"
            },
            "phoneNumbers": [
                "123-456-7890",
                "987-654-3210"
            ]
        }
        """;

        String nnn = """
        {
            "college": "Dsce",
            "code": 1111,
            "Student": [
                {
                    "name": "ravi",
                    "branch": "Cse",
                    "inner": {
                       "color" : "green"
                    }
                },
                {
                    "name": "rahul",
                    "branch": "Cse"
                },
                {
                    "name": "ram",
                    "branch": "mc"
                }
            ]
        }
        """;

        JSONObject newob = new JSONObject(nnn);

        JSONObject jsonObject = new JSONObject(str);

        jsonObject.put("department", "CSE");
        JSONObject address = jsonObject.getJSONObject("address");
        address.put("department", "cse");
/* clae
        jsonObject.keySet().forEach(keyStr -> {
            Object keyValue = jsonObject.get(keyStr);
            System.out.println("key: " + keyStr + " value: " + keyValue);
        });
        System.err.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7");
        */

        // Handling the new field addition
        JSONObject nn = new JSONObject(nnn);
        JSONArray students = nn.getJSONArray("Student");
        for (int i = 0; i < students.length(); i++) {
            JSONObject student = students.getJSONObject(i);
            String branch = student.getString("branch");
            if (branch.equals("Cse")) {
                student.put("campus", "selected");
            }
        }
        //System.out.println("Without changes");
        //printJson(newob);
        System.out.println("When applied changes");
        System.out.println(nn.toString(4));
        //System.out.println("{");
        //printJson(nn);
        //System.out.println("}");
        //printJson(jsonObject);
    }

    public static void printJson(JSONObject jsonObject) {
        System.out.println("{");
        int count = 0;
        int totalKeys = jsonObject.keySet().size();
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {        
                System.out.println('"' +key +'"'+":");
                printJson((JSONObject) value);
            } else if (value instanceof JSONArray) {
                //System.out.println("[");
                System.out.println('"' +key +'"'+":");
                System.out.println("[");
                JSONArray array = (JSONArray) value;
                for (int i = 0; i < array.length(); i++) {
                    Object element = array.get(i);
                    if (element instanceof JSONObject) {
                        printJson((JSONObject) element);
                    } else {
                        System.out.println("  " +'"' +element +'"'+ (i < array.length() - 1 ? "," : ""));
                    }
                }
                System.out.println("]");
            } else {
                System.out.println('"'+key+ '"'+ ": " + '"'+value+'"'+ (count < totalKeys - 1 ? "," : ""));
            }
            count++;
        }
        System.out.println("}" + (count < totalKeys - 1 ? "," : ""));
    }
}
