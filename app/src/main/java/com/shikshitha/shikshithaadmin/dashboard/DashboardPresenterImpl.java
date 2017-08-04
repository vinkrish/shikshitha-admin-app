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

class DashboardPresenterImpl implements DashboardPresenter, DashboardInteractor.OnFinishedListener {
    private DashboardView mView;
    private DashboardInteractor mInteractor;

    DashboardPresenterImpl(DashboardView view, DashboardInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void getSchoolList() {
        if (mView != null) {
            mView.showProgress();
            mInteractor.getSchoolList(this);
        }
    }

    @Override
    public void getClassList(long schoolId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.getClassList(schoolId, this);
        }
    }

    @Override
    public void getSectionList(long classId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.getSectionList(classId, this);
        }
    }

    @Override
    public void getStudentList(long sectionId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.getStudentList(sectionId, this);
        }
    }

    @Override
    public void getTeacherList(long schoolId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.getTeacherList(schoolId, this);
        }
    }

    @Override
    public void sendHomeworkSMS(long schoolId, String homeworkDate) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendHomeworkSMS(schoolId, homeworkDate, this);
        }
    }

    @Override
    public void sendSchoolStudentsPswd(long schoolId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendSchoolStudentsPswd(schoolId, this);
        }
    }

    @Override
    public void sendClassStudentsPswd(long classId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendClassStudentsPswd(classId, this);
        }
    }

    @Override
    public void sendSectionStudentsPswd(long sectionId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendSectionStudentsPswd(sectionId, this);
        }
    }

    @Override
    public void sendStudentPswd(long studentId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendStudentPswd(studentId, this);
        }
    }

    @Override
    public void sendSchoolTeachersPswd(long schoolId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendSchoolTeachersPswd(schoolId, this);
        }
    }

    @Override
    public void sendTeacherPswd(long teacherId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.sendTeacherPswd(teacherId, this);
        }
    }

    @Override
    public void updateSchoolStudentsPswd(long schoolId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.updateSchoolStudentsPswd(schoolId, this);
        }
    }

    @Override
    public void updateClassStudentsPswd(long classId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.updateClassStudentsPswd(classId, this);
        }
    }

    @Override
    public void updateSectionStudentsPswd(long sectionId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.updateSectionStudentsPswd(sectionId, this);
        }
    }

    @Override
    public void updateStudentPswd(long studentId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.updateStudentPswd(studentId, this);
        }
    }

    @Override
    public void updateSchoolTeachersPswd(long schoolId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.updateSchoolTeachersPswd(schoolId, this);
        }
    }

    @Override
    public void updateTeacherPswd(long teacherId) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.updateTeacherPswd(teacherId, this);
        }
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void onError(String message) {
        if (mView != null) {
            mView.hideProgess();
            mView.showError(message);
        }
    }

    @Override
    public void onSchoolReceived(List<School> schoolList) {
        if (mView != null) {
            mView.showSchool(schoolList);
            mView.hideProgess();
        }
    }

    @Override
    public void onClasReceived(List<Clas> clasList) {
        if (mView != null) {
            mView.showClass(clasList);
            mView.hideProgess();
        }
    }

    @Override
    public void onSectionReceived(List<Section> sectionList) {
        if (mView != null) {
            mView.showSection(sectionList);
            mView.hideProgess();
        }
    }

    @Override
    public void onStudentReceived(List<Student> studentList) {
        if (mView != null) {
            mView.showStudent(studentList);
            mView.hideProgess();
        }
    }

    @Override
    public void onTeacherReceived(List<Teacher> teacherList) {
        if (mView != null) {
            mView.showTeacher(teacherList);
            mView.hideProgess();
        }
    }

    @Override
    public void onSuccess() {
        if (mView != null) {
            mView.hideProgess();
        }
    }
}
