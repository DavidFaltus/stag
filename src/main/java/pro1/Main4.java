package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;

public class Main4 {

    public static void main(String[] args) {
         printShortestEmails("KIKM",5);
    }

    public static void printShortestEmails(String department, int count)
    {
        String json2 = Api.getTeachersByDepartment(department);
        TeachersList teacher = new Gson().fromJson(json2, TeachersList.class);

        teacher.items.stream()
                .filter(teacher1 -> teacher1.email != null)
                .sorted(Comparator.comparing(teacher1 -> teacher1.email.length()))
                .limit(count)
                .forEach(teacher1 -> System.out.println(teacher1.email));
        // TODO 4.1: Vypiš do konzole "count" nejkratších učitelských emailových adres
    }
}