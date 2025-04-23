package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;

import java.util.Comparator;
import java.util.HashMap;

public class Main6 {

    public static void main(String[] args) {
        System.out.println(idOfBestTeacher("KIKM",2024));
    }

    public static long idOfBestTeacher(String department, int year)
    {
        String json1 = Api.getActionsByDepartment(department, year);
        ActionsList actions = new Gson().fromJson(json1, ActionsList.class);

        HashMap<Long,Integer> map = new HashMap<>();

        actions.items.stream()
                .forEach(action -> {
                    int current = map.getOrDefault(action.teacherId,0);
                    map.put(action.teacherId,current + action.personsCount);
                });

        return map.entrySet().stream()
                .max(Comparator.comparing(h -> h.getValue()))
                .get()
                .getKey();

        // TODO 6.1 (navazuje na TODO 3):
        //  - Stáhni seznam akcí na katedře (jiná data nepoužívat)
        //  - Najdi učitele s nejvyšším "score" a vrať jeho ID
    }
}