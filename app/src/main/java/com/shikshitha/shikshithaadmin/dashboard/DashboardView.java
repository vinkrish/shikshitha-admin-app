package com.shikshitha.shikshithaadmin.dashboard;

import com.shikshitha.shikshithaadmin.model.Clas;
import com.shikshitha.shikshithaadmin.model.School;
import com.shikshitha.shikshithaadmin.model.Section;
import com.shikshitha.shikshithaadmin.model.Student;
import com.shikshitha.shikshithaadmin.model.Teacher;

import java.util.List;

/**
 * Created by Vinay on 04-08-2017.
 */

interface DashboardView {
    void showProgress();

    void hideProgess();

    void showError(String message);

    void showSchool(List<School> schoolList);

    void showClass(List<Clas> clasList);

    void showSection(List<Section> sectionList);

    void showStudent(List<Student> studentList);

    void showTeacher(List<Teacher> teacherList);
}
