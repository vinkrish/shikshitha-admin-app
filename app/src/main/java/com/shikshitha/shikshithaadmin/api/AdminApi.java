package com.shikshitha.shikshithaadmin.api;

import com.shikshitha.shikshithaadmin.model.AdminCredentials;
import com.shikshitha.shikshithaadmin.model.AppVersion;
import com.shikshitha.shikshithaadmin.model.Clas;
import com.shikshitha.shikshithaadmin.model.Credentials;
import com.shikshitha.shikshithaadmin.model.School;
import com.shikshitha.shikshithaadmin.model.Section;
import com.shikshitha.shikshithaadmin.model.Student;
import com.shikshitha.shikshithaadmin.model.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Vinay on 20-07-2017.
 */

public interface AdminApi {

    @POST("login/elasticbeanstalk")
    Call<Void> keepServerUp();

    @POST("login/admin")
    Call<AdminCredentials> login(@Body Credentials credentials);

    @GET("school")
    Call<List<School>> getSchoolList();

    @GET("class/school/{schoolId}")
    Call<List<Clas>> getClassList(@Path("schoolId") long schoolId);

    @GET("section/class/{classId}")
    Call<List<Section>> getSectionList(@Path("classId") long classId);

    @GET("student/section/{sectionId}")
    Call<List<Student>> getStudents(@Path("sectionId") long sectionId);

    @GET("teacher/school/{schoolId}")
    Call<List<Teacher>> getTeachers(@Path("schoolId") long schoolId);

    @POST("password/school/{schoolId}")
    Call<Void> updateSchoolPswd(@Path("schoolId") long schoolId);

    @POST("password/class/{classId}")
    Call<Void> updateClassPswd(@Path("classId") long classId);

    @POST("password/section/{sectionId}")
    Call<Void> updateSectionPswd(@Path("sectionId") long sectionId);

    @POST("password/student/{studentId}")
    Call<Void> updateStudentPswd(@Path("studentId") long studentId);

    @POST("password/teachers/school/{schoolId}")
    Call<Void> updateTeachersPswd(@Path("schoolId") long schoolId);

    @POST("password/teacher/{teacherId}")
    Call<Void> updateTeacherPswd(@Path("teacherId") long teacherId);

    @POST("sms/school/{schoolId}")
    Call<Void> sendSchoolPswd(@Path("schoolId") long schoolId);

    @POST("sms/school/{schoolId}/unlogged")
    Call<Void> sendUnloggedStdsPswd(@Path("schoolId") long schoolId);

    @POST("sms/class/{classId}")
    Call<Void> sendClassPswd(@Path("classId") long classId);

    @POST("sms/section/{sectionId}")
    Call<Void> sendSectionPswd(@Path("sectionId") long sectionId);

    @POST("sms/student/id/{studentId}")
    Call<Void> sendStudentPswd(@Path("studentId") long studentId);

    @POST("sms/teachers/school/{schoolId}")
    Call<Void> sendTeachersPswd(@Path("schoolId") long schoolId);

    @POST("sms/teachers/school/{schoolId}/unlogged")
    Call<Void> sendUnloggedTchrsPswd(@Path("schoolId") long schoolId);

    @POST("sms/teacher/id/{teacherId}")
    Call<Void> sendTeacherPswd(@Path("teacherId") long teacherId);

    @POST("sms/{schoolId}/{homeworkDate}")
    Call<Void> sendHomeworkSMS(@Path("schoolId") long schoolId,
                               @Path("homeworkDate") String homeworkDate);

    @GET("appversion")
    Call<List<AppVersion>> geAppVersions();

    @DELETE("appversion/{id}")
    Call<Void> deleteAppVersion(@Path("id") long id);

    @POST("appversion")
    Call<Void> addAppVersion(@Body AppVersion appVersion);

    @PUT("appversion")
    Call<Void> updateAppVersion(@Body AppVersion appVersion);

}
