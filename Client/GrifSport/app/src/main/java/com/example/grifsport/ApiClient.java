package com.example.grifsport;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.MYURL+"/") //первичная ссылка
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create()) //преобразование json-объектов
                .build();

        return retrofit;
    }
    public  static  UserServicePost postUserService(){
        UserServicePost userService = getRetrofit().create(UserServicePost.class);
        return userService;
    }

    public  static  UserServiceLogin loginUserService(){
        UserServiceLogin userService = getRetrofit().create(UserServiceLogin.class);
        return userService;
    }

    public  static  Authorization AuthorizationUserService(){
        Authorization userService = getRetrofit().create(Authorization.class);
        return userService;
    }

    public  static  ProfileServiceGet profileServiceGet(){
        ProfileServiceGet profileServiceGet = getRetrofit().create(ProfileServiceGet.class);
        return profileServiceGet;
    }

    public  static  EventServiceGet eventServiceGet(){
        EventServiceGet eventServiceGet = getRetrofit().create(EventServiceGet.class);
        return eventServiceGet;
    }

    public  static  OneEventServiceGet oneEventServiceGet(){
        OneEventServiceGet eventServiceGet = getRetrofit().create(OneEventServiceGet.class);
        return eventServiceGet;
    }

    public  static  DisciplineForEventServiceGet disciplineForEventServiceGet(){
        DisciplineForEventServiceGet disciplineForEventServiceGet = getRetrofit().create(DisciplineForEventServiceGet.class);
        return disciplineForEventServiceGet;
    }

    public  static  EventServicePost eventServicePost(){
        EventServicePost eventServicePost = getRetrofit().create(EventServicePost.class);
        return eventServicePost;
    }

    public  static  EventServicePut eventServicePut(){
        EventServicePut eventServicePut = getRetrofit().create(EventServicePut.class);
        return eventServicePut;
    }

    public  static  FileServiceGet fileServiceGet(){
        FileServiceGet fileServiceGet = getRetrofit().create(FileServiceGet.class);
        return fileServiceGet;
    }

    public  static  OrganizerServicePost organizerServicePost(){
        OrganizerServicePost organizerServicePost = getRetrofit().create(OrganizerServicePost.class);
        return organizerServicePost;
    }

    public  static  WorkerServicePost workerServicePost(){
        WorkerServicePost workerServicePost = getRetrofit().create(WorkerServicePost.class);
        return workerServicePost;
    }

    public  static  JudgeServicePost judgeServicePost(){
        JudgeServicePost judgeServicePost = getRetrofit().create(JudgeServicePost.class);
        return judgeServicePost;
    }

    public  static  ViewerServicePost viewerServicePost(){
        ViewerServicePost viewerServicePost = getRetrofit().create(ViewerServicePost.class);
        return viewerServicePost;
    }

    public  static  ParticipantServicePost participantServicePost(){
        ParticipantServicePost participantServicePost = getRetrofit().create(ParticipantServicePost.class);
        return participantServicePost;
    }

    public  static  OrganizerServiceGet organizerServiceGet(){
        OrganizerServiceGet organizerServiceGet = getRetrofit().create(OrganizerServiceGet.class);
        return organizerServiceGet;
    }

    public  static  OrganizerForEventServicePost organizerForEventServicePostServiceGet(){
        OrganizerForEventServicePost organizerForEventServicePostServiceGet = getRetrofit().create(OrganizerForEventServicePost.class);
        return organizerForEventServicePostServiceGet;
    }

    public  static  OrganizerForAllEventServiceGet organizerForAllEventServiceGet(){
        OrganizerForAllEventServiceGet organizerForEventServicePostServiceGet = getRetrofit().create(OrganizerForAllEventServiceGet.class);
        return organizerForEventServicePostServiceGet;
    }

    public  static JudgeForAllEventServiceGet judgeForAllEventServiceGet(){
        JudgeForAllEventServiceGet judgeForAllEventServiceGet = getRetrofit().create(JudgeForAllEventServiceGet.class);
        return judgeForAllEventServiceGet;
    }

    public  static  ViewerForAllEventServiceGet viewerForAllEventServiceGet(){
        ViewerForAllEventServiceGet viewerForAllEventServiceGet = getRetrofit().create(ViewerForAllEventServiceGet.class);
        return viewerForAllEventServiceGet;
    }

    public  static  WorkerForAllEventServiceGet workerForAllEventServiceGet(){
        WorkerForAllEventServiceGet workerForAllEventServiceGet = getRetrofit().create(WorkerForAllEventServiceGet.class);
        return workerForAllEventServiceGet;
    }

    public  static  ParticipantForAllEventServiceGet participantForAllEventServiceGet(){
        ParticipantForAllEventServiceGet participantForAllEventServiceGet = getRetrofit().create(ParticipantForAllEventServiceGet.class);
        return participantForAllEventServiceGet;
    }

    public  static  ApplicationServicePost applicationServicePost(){
        ApplicationServicePost applicationServicePost = getRetrofit().create(ApplicationServicePost.class);
        return applicationServicePost;
    }

    public  static  ApplicationsForEventServiceGet applicationsForEventServiceGet(){
        ApplicationsForEventServiceGet applicationsForEventServiceGet = getRetrofit().create(ApplicationsForEventServiceGet.class);
        return applicationsForEventServiceGet;
    }

    public  static  WorkerForEventServicePost workerForEventServicePost(){
        WorkerForEventServicePost workerForEventServicePost = getRetrofit().create(WorkerForEventServicePost.class);
        return workerForEventServicePost;
    }

    public  static  ViewerForEventServicePost viewerForEventServicePost(){
        ViewerForEventServicePost viewerForEventServicePost = getRetrofit().create(ViewerForEventServicePost.class);
        return viewerForEventServicePost;
    }

    public  static  ParticipantForEventServicePost participantForEventServicePost(){
        ParticipantForEventServicePost participantForEventServicePost = getRetrofit().create(ParticipantForEventServicePost.class);
        return participantForEventServicePost;
    }

    public  static  JudgeForEventServicePost judgeForEventServicePost(){
        JudgeForEventServicePost judgeForEventServicePost = getRetrofit().create(JudgeForEventServicePost.class);
        return judgeForEventServicePost;
    }

    public  static  ApplicationServiceDelete applicationServiceDelete(){
        ApplicationServiceDelete applicationServiceDelete = getRetrofit().create(ApplicationServiceDelete.class);
        return applicationServiceDelete;
    }

    public  static  InviteServiceDelete inviteServiceDelete(){
        InviteServiceDelete inviteServiceDelete = getRetrofit().create(InviteServiceDelete.class);
        return inviteServiceDelete;
    }

    public  static  InviteServicePost inviteServicePost(){
        InviteServicePost inviteServicePost = getRetrofit().create(InviteServicePost.class);
        return inviteServicePost;
    }

    public  static  InviteForEventServiceGet inviteForEventServiceGet(){
        InviteForEventServiceGet inviteForEventServiceGet = getRetrofit().create(InviteForEventServiceGet.class);
        return inviteForEventServiceGet;
    }

    public  static  InviteForProfileServiceGet inviteForProfileServiceGet(){
        InviteForProfileServiceGet inviteForProfileServiceGet = getRetrofit().create(InviteForProfileServiceGet.class);
        return inviteForProfileServiceGet;
    }

    public  static  ProfileAllServiceGet profileAllServiceGet(){
        ProfileAllServiceGet profileAllServiceGet = getRetrofit().create(ProfileAllServiceGet.class);
        return profileAllServiceGet;
    }


}
