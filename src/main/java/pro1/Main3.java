package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;

public class Main3 {

    public static void main(String[] args) {
        System.out.println(emailOfBestTeacher("KIKM",2024));
    }

    public static String emailOfBestTeacher(String department, int year)
    {
        // TODO 3.2:
        String json1 = Api.getActionsByDepartment(department,year);
        ActionsList actions = new Gson().fromJson(json1, ActionsList.class);

        String json2 = Api.getTeachersByDepartment(department);
        TeachersList teacher = new Gson().fromJson(json2, TeachersList.class);

        return teacher.items.stream().max(Comparator.comparing(t ->
                TeacherScore(t.id, actions))).get().email;
    }

    public static long TeacherScore(long teacherId, ActionsList departmentSchedule)
    {
        return  departmentSchedule.items.stream().filter(action ->
                action.teacherId == teacherId).mapToInt(action ->
                action.personsCount).sum();
        // TODO 3.1: Doplň pomocnou metodu - součet všech přihlášených studentů na akcích daného učitele
    }
}