package com.shikshitha.shikshithaadmin.dashboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.shikshitha.shikshithaadmin.R;
import com.shikshitha.shikshithaadmin.fragment.HomeFragment;
import com.shikshitha.shikshithaadmin.login.LoginActivity;
import com.shikshitha.shikshithaadmin.model.Clas;
import com.shikshitha.shikshithaadmin.model.School;
import com.shikshitha.shikshithaadmin.model.Section;
import com.shikshitha.shikshithaadmin.model.Student;
import com.shikshitha.shikshithaadmin.model.Teacher;
import com.shikshitha.shikshithaadmin.util.ElasticBeanstalkReceiver;
import com.shikshitha.shikshithaadmin.util.ReplaceFragment;
import com.shikshitha.shikshithaadmin.util.SharedPreferenceUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements DashboardView,
        AdapterView.OnItemSelectedListener{
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.school_spinner) Spinner schoolSpinner;
    @BindView(R.id.class_spinner) Spinner classSpinner;
    @BindView(R.id.section_spinner) Spinner sectionSpinner;
    @BindView(R.id.student_spinner) Spinner studentSpinner;
    @BindView(R.id.teacher_spinner) Spinner teacherSpinner;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    private DashboardPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.title_activity_dashboard);
        setSupportActionBar(toolbar);

        presenter = new DashboardPresenterImpl(this, new DashboardInteractorImpl());

        presenter.getSchoolList();

        //ReplaceFragment.replace(new HomeFragment(), getFragmentManager());

        ElasticBeanstalkReceiver alarm = new ElasticBeanstalkReceiver();
        alarm.setAlarm(getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu (Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.send_homework:
                presenter.sendHomeworkSMS(((School)schoolSpinner.getSelectedItem()).getId(), dateFormat.format(new Date()));
                return true;
            case R.id.send_students_pswd:
                presenter.sendSchoolStudentsPswd(((School)schoolSpinner.getSelectedItem()).getId());
                return true;
            case R.id.send_class_pswd:
                presenter.sendClassStudentsPswd(((Clas)classSpinner.getSelectedItem()).getId());
                return true;
            case R.id.send_section_pswd:
                presenter.sendSectionStudentsPswd(((Section)sectionSpinner.getSelectedItem()).getId());
                return true;
            case R.id.send_student_pswd:
                presenter.sendStudentPswd(((Student)studentSpinner.getSelectedItem()).getId());
                return true;
            case R.id.send_teachers_pswd:
                presenter.sendSchoolTeachersPswd(((School)schoolSpinner.getSelectedItem()).getId());
                return true;
            case R.id.send_teacher_pswd:
                presenter.sendTeacherPswd(((Teacher)teacherSpinner.getSelectedItem()).getId());
                return true;
            case R.id.update_students_pswd:
                presenter.updateSchoolStudentsPswd(((School)schoolSpinner.getSelectedItem()).getId());
                return true;
            case R.id.update_class_pswd:
                presenter.updateClassStudentsPswd(((Clas)classSpinner.getSelectedItem()).getId());
                return true;
            case R.id.update_section_pswd:
                presenter.updateSectionStudentsPswd(((Section)sectionSpinner.getSelectedItem()).getId());
                return true;
            case R.id.update_student_pswd:
                presenter.updateStudentPswd(((Student)studentSpinner.getSelectedItem()).getId());
                return true;
            case R.id.update_teachers_pswd:
                presenter.updateSchoolTeachersPswd(((School)schoolSpinner.getSelectedItem()).getId());
                return true;
            case R.id.update_teacher_pswd:
                presenter.updateTeacherPswd(((Teacher)teacherSpinner.getSelectedItem()).getId());
                return true;
            case R.id.logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Are you sure you want to logout?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferenceUtil.logout(DashboardActivity.this);
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                finish();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    private void showSnackbar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgess() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showSnackbar(message);
    }

    @Override
    public void showSchool(List<School> schoolList) {
        ArrayAdapter<School> adapter = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, schoolList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolSpinner.setAdapter(adapter);
        schoolSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void showClass(List<Clas> clasList) {
        ArrayAdapter<Clas> adapter = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clasList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(adapter);
        classSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void showSection(List<Section> sectionList) {
        ArrayAdapter<Section> adapter = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sectionList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectionSpinner.setAdapter(adapter);
        sectionSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void showStudent(List<Student> studentList) {
        ArrayAdapter<Student> adapter = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, studentList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentSpinner.setAdapter(adapter);
        studentSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void showTeacher(List<Teacher> teacherList) {
        ArrayAdapter<Teacher> adapter = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, teacherList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teacherSpinner.setAdapter(adapter);
        teacherSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        switch (parent.getId()) {
            case R.id.school_spinner:
                presenter.getClassList(((School)schoolSpinner.getSelectedItem()).getId());
                presenter.getTeacherList(((School)schoolSpinner.getSelectedItem()).getId());
                break;
            case R.id.class_spinner:
                presenter.getSectionList(((Clas)classSpinner.getSelectedItem()).getId());
                break;
            case R.id.section_spinner:
                presenter.getStudentList(((Section)sectionSpinner.getSelectedItem()).getId());
                break;
            case R.id.student_spinner:
                break;
            case R.id.teacher_spinner:
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
