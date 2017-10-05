package com.shikshitha.shikshithaadmin.dashboard;

/**
 * Created by Vinay on 04-08-2017.
 */

interface DashboardPresenter {
    void getSchoolList();

    void getClassList(long schoolId);

    void getSectionList(long classId);

    void getStudentList(long sectionId);

    void getTeacherList(long schoolId);

    void sendHomeworkSMS(long schoolId, String homeworkDate);

    void sendSchoolStudentsPswd(long schoolId);

    void sendUnloggedStdsPswd(long schoolId);

    void sendClassStudentsPswd(long classId);

    void sendSectionStudentsPswd(long sectionId);

    void sendStudentPswd(long studentId);

    void sendSchoolTeachersPswd(long schoolId);

    void sendUnloggedTchrsPswd(long schoolId);

    void sendTeacherPswd(long teacherId);

    void updateSchoolStudentsPswd(long schoolId);

    void updateClassStudentsPswd(long classId);

    void updateSectionStudentsPswd(long sectionId);

    void updateStudentPswd(long studentId);

    void updateSchoolTeachersPswd(long schoolId);

    void updateTeacherPswd(long teacherId);

    void onDestroy();

}
