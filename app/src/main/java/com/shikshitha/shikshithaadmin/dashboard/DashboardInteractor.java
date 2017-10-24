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

interface DashboardInteractor {
    interface OnFinishedListener {
        void onError(String message);

        void onSchoolReceived(List<School> schoolList);

        void onClasReceived(List<Clas> clasList);

        void onSectionReceived(List<Section> sectionList);

        void onStudentReceived(List<Student> studentList);

        void onTeacherReceived(List<Teacher> teacherList);

        void onSuccess();
    }

    void getSchoolList(DashboardInteractor.OnFinishedListener listener);

    void getClassList(long schoolId, DashboardInteractor.OnFinishedListener listener);

    void getSectionList(long classId, DashboardInteractor.OnFinishedListener listener);

    void getStudentList(long sectionId, DashboardInteractor.OnFinishedListener listener);

    void getTeacherList(long schoolId, DashboardInteractor.OnFinishedListener listener);

    void sendHomeworkSMS(long schoolId, String homeworkDate, DashboardInteractor.OnFinishedListener listener);

    void sendSchoolStudentsPswd(long schoolId, DashboardInteractor.OnFinishedListener listener);

    void sendUnloggedStudentsPswd(long schoolId, DashboardInteractor.OnFinishedListener listener);

    void sendClassStudentsPswd(long classId, DashboardInteractor.OnFinishedListener listener);

    void sendUnloggedClassPswd(long classId, DashboardInteractor.OnFinishedListener listener);

    void sendSectionStudentsPswd(long sectionId, DashboardInteractor.OnFinishedListener listener);

    void sendStudentPswd(long studentId, DashboardInteractor.OnFinishedListener listener);

    void sendSchoolTeachersPswd(long schoolId, DashboardInteractor.OnFinishedListener listener);

    void sendUnloggedTeachersPswd(long schoolId, DashboardInteractor.OnFinishedListener listener);

    void sendTeacherPswd(long teacherId, DashboardInteractor.OnFinishedListener listener);

    void updateSchoolStudentsPswd(long schoolId, DashboardInteractor.OnFinishedListener listener);

    void updateClassStudentsPswd(long classId, DashboardInteractor.OnFinishedListener listener);

    void updateSectionStudentsPswd(long sectionId, DashboardInteractor.OnFinishedListener listener);

    void updateStudentPswd(long studentId, DashboardInteractor.OnFinishedListener listener);

    void updateSchoolTeachersPswd(long schoolId, DashboardInteractor.OnFinishedListener listener);

    void updateTeacherPswd(long teacherId, DashboardInteractor.OnFinishedListener listener);
}
