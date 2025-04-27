package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;
import pro1.apiDataModel.SpecializationsList;

import java.util.Comparator;
import java.util.stream.Collectors;

public class Main7 {
    public static String specializationDeadlines(int year)
    {
        String json = Api.getSpecializations(year);
        SpecializationsList specialization = new Gson().fromJson(json, SpecializationsList.class);

        Comparator<String> comparator = Comparator
                .comparing((String s) -> Integer.parseInt(s.split("\\.")[2]))
                .thenComparing(s -> Integer.parseInt(s.split("\\.")[1]))
                .thenComparing(s -> Integer.parseInt(s.split("\\.")[0]));

        return specialization.specializations.stream()
                .map(s -> s.eprDeadlinePrihlaska.value)
                .distinct()
                .sorted(comparator)
                .collect(Collectors.joining(","));
    }
}
