from ..serializers import InviteSerializer,InvitePostSerializer,ApplicationPostSerializer,EventSerializer,EventTypeSerializer,SponsorSerializer,SponsorForEventSerializer,OrganizerSerializer,OrganizerForEventSerializer,JudgeSerializer,JudgeForEventSerializer,ParticipantSerializer,ParticipantForEventSerializer,WorkerSerializer,WorkerForEventSerializer,TypeSportSerializer,SportSerializer,DisciplineSerializer,DisciplineForEventSerializer,ApplicationSerializer,ProfileSerializer,UserSerializer,ViewerSerializer,ViewerForEventSerializer
from .repository_service import *
from ..models import Files
import rest_framework
import json
import collections
from django import forms

class OrganizationService:
   
    def get_event(event_id:int) -> Optional[EventSerializer]:    
        result = get_event_by_id(event_id)
        if result is not None:
            return EventSerializer(result)
        return result
    
    def get_all_event() -> Optional[EventSerializer]:    
        result = get_all_event()
        if result is not None:
            return EventSerializer(result,many=True)
        return result
    
    def add_new_event(event:EventSerializer) -> Optional[EventSerializer]:            #ЕСТЬ
        event_data = event.data     # получаем валидированные с помощью сериализатора данные (метод .data  возвращает объект типа dict)
        return EventSerializer(create_event(name=event_data.get('name'),
                     description=event_data.get('description'),
                     adress=event_data.get('adress'),
                     event_start_date=event_data.get('event_start_date'),
                     event_end_date=event_data.get('event_end_date'),
                     image=event_data.get('image'),
                     schedule=event_data.get('schedule'),
                     results=event_data.get('results'),
                     type=str((event_data.get('type')).get('type')),
                     deadline_for_accepting_applications=event_data.get('deadline_for_accepting_applications'),
                     ages=event_data.get('ages'),
                     genders=event_data.get('genders'),
                     phone_number=event_data.get('phone_number'),
                     city=str((event_data.get('city')).get('name')),
                     sport=str((event_data.get('sport')).get('name')),
            
                       ))
        
        

    def put_event(event:EventSerializer)-> None:            #ЕСТЬ
        event_data = event.data
        update_event_by_id(id=event_data.get('id'),
                         name=event_data.get('name'),
                     description=event_data.get('description'),
                     adress=event_data.get('adress'),
                     event_start_date=event_data.get('event_start_date'),
                     event_end_date=event_data.get('event_end_date'),
                     image=event_data.get('image'),
                     schedule=event_data.get('schedule'),
                     results=event_data.get('results'),
                     type=str((event_data.get('type')).get('type')),
                     deadline_for_accepting_applications=event_data.get('deadline_for_accepting_applications'),
                     ages=event_data.get('ages'),
                     genders=event_data.get('genders'),
                     phone_number=event_data.get('phone_number')
        )


    def delete_event(id:int)-> None:            #ЕСТЬ
        delete_event_by_id(id)



    def add_new_participant(participant:ParticipantSerializer) -> None:            #ЕСТЬ
        participant_data = participant.data     # получаем валидированные с помощью сериализатора данные (метод .data  возвращает объект типа dict)
        create_participant(id_user=(participant_data.get('profile')).get('user').get('id'),
                           description=participant_data.get('description'),
                       )
        
    def get_participant(id:int) -> Optional[ParticipantSerializer]:    
        result = get_participant_by_id(id)
        if result is not None:
            return ParticipantSerializer(result)
        return result
    
    def add_new_worker(worker:WorkerSerializer) -> None:            #ЕСТЬ
        worker_data = worker.data     # получаем валидированные с помощью сериализатора данные (метод .data  возвращает объект типа dict)
        create_worker(id_user=(worker_data.get('profile')).get('user').get('id'),
                           description=worker_data.get('description'),
                       )
        
    def get_worker(id:int) -> Optional[WorkerSerializer]:    
        result = get_worker_by_id(id)
        if result is not None:
            return WorkerSerializer(result)
        return result
    
    def add_new_judge(judge:JudgeSerializer) -> None:            #ЕСТЬ
        judge_data = judge.data     # получаем валидированные с помощью сериализатора данные (метод .data  возвращает объект типа dict)
        create_judge(id_user=(judge_data.get('profile')).get('user').get('id'),
                           description=judge_data.get('description'),
                       )
        
    def add_new_viewer(viewer:ViewerSerializer) -> None:            #ЕСТЬ
        viewer_data = viewer.data     # получаем валидированные с помощью сериализатора данные (метод .data  возвращает объект типа dict)
        create_viewer(id_user=(viewer_data.get('profile')).get('user').get('id'),
                           description=viewer_data.get('description'),
                       )
        
    def get_judge(id:int) -> Optional[JudgeSerializer]:    
        result = get_judge_by_id(id)
        if result is not None:
            return JudgeSerializer(result)
        return result
    
    def get_viewer(id:int) -> Optional[ViewerSerializer]:    
        result = get_viewer_by_id(id)
        if result is not None:
            return ViewerSerializer(result)
        return result
    
    def add_new_organizer(organizer:OrganizerSerializer) -> None:            #ЕСТЬ
        organizer_data = organizer.data     # получаем валидированные с помощью сериализатора данные (метод .data  возвращает объект типа dict)
        create_organizer(id_user=(organizer_data.get('profile')).get('user').get('id'),
                           description=organizer_data.get('description'),
                       )
        
    def get_organizer(id:int) -> Optional[OrganizerSerializer]:    
        result = get_organizer_by_id(id)
        if result is not None:
            return OrganizerSerializer(result)
        return result
    
    def get_organizer_for_profile(id:int) -> Optional[OrganizerSerializer]:    
        result = get_organizer_by_id_profile(id)
        if result is not None:
            return OrganizerSerializer(result)
        return result
    
    def add_participant_for_event(event_id:int,id_participant:int) -> None:            #ЕСТЬ
        create_participant_for_event(id_event=event_id,
                     id_participant=id_participant,
                       )
        
    def add_worker_for_event(event_id:int,id_worker:int) -> None:            #ЕСТЬ
        create_worker_for_event(id_event=event_id,
                     id_worker=id_worker,
                       )
        
    def add_judge_for_event(event_id:int,id_judge:int) -> None:            #ЕСТЬ
        create_judge_for_event(id_event=event_id,
                     id_judge=id_judge,
                       )
        
    def add_viewer_for_event(event_id:int,id_viewer:int) -> None:            #ЕСТЬ
        create_viewer_for_event(id_event=event_id,
                     id_viewer=id_viewer,
                       )
        
    def add_organizer_for_event(event_id:int,id_organizer:int) -> None:            #ЕСТЬ
        create_organizer_for_event(id_event=event_id,
                     id_organizer=id_organizer,
                       )
        
    def add_new_application(application:ApplicationPostSerializer) -> None:            #ЕСТЬ
        application_data = application.data     # получаем валидированные с помощью сериализатора данные (метод .data  возвращает объект типа dict)
        create_application(id_user=application_data.get('profile'),
                           id_event=application_data.get('event'),
                           vacancy=application_data.get('vacancy'),
                           description=application_data.get('description')
                       )
        
    
    def get_one_application(application_id:int) -> Optional[ApplicationSerializer]:    
        result = get_application_by_id(application_id)
        if result is not None:
            return ApplicationSerializer(result)
        return result
    
    def get_applications_for_event(event_id:int) -> Optional[ApplicationSerializer]:    
        result = get_all_application_for_event(event_id)
        if result is not None:
            return ApplicationSerializer(result,many=True)
        return result
    
    def get_applications_for_profile(user_id:int) -> Optional[ApplicationSerializer]:    
        result = get_all_application_for_profile(user_id)
        if result is not None:
            return ApplicationSerializer(result,many=True)
        return result
    

    def delete_one_application(application_id:int)-> None:            #ЕСТЬ
        delete_application_by_id(application_id)
    

    def get_all_participants_for_event(event_id:int)-> Optional[ParticipantForEventSerializer]: 
        result = get_participants_for_event(event_id)
        if result is not None:
            return ParticipantForEventSerializer(result,many=True)
        return result 
    
    def get_all_workers_for_event(event_id:int)-> Optional[WorkerForEventSerializer]: 
        result = get_workers_for_event(event_id)
        if result is not None:
            return WorkerForEventSerializer(result,many=True)
        return result 
    
    def get_all_judges_for_event(event_id:int)-> Optional[JudgeForEventSerializer]: 
        result = get_judge_for_event(event_id)
        if result is not None:
            return JudgeForEventSerializer(result,many=True)
        return result 
    
    def get_all_viewers_for_event(event_id:int)-> Optional[ViewerForEventSerializer]: 
        result = get_viewer_for_event(event_id)
        if result is not None:
            return ViewerForEventSerializer(result,many=True)
        return result 
    
    def get_all_organizers_for_event(event_id:int)-> Optional[OrganizerForEventSerializer]: 
        result = get_organizers_for_event(event_id)
        if result is not None:
            return OrganizerForEventSerializer(result,many=True)
        return result 
    
    def get_all_disciplines_for_event(event_id:int)-> Optional[DisciplineForEventSerializer]: 
        result = get_discipline_for_event(event_id)
        if result is not None:
            return DisciplineForEventSerializer(result,many=True)
        return result 
    
    def get_profile_by_user_id(user_id:int)-> Optional[ProfileSerializer]: 
        result = get_profile(user_id)
        if result is not None:
            return ProfileSerializer(result)
        return result 
    
    def get_all_profile() -> Optional[ProfileSerializer]:    
        result = get_all_profiles()
        if result is not None:
            return ProfileSerializer(result,many=True)
        return result
    
    def get_all_event_for_organizer(profile_id:int) -> Optional[OrganizerForEventSerializer]:    
        result = get_all_event_for_organizer(profile_id)
        if result is not None:
            return OrganizerForEventSerializer(result,many=True)
        return result
    
    def get_all_event_for_worker(profile_id:int) -> Optional[WorkerForEventSerializer]:    
        result = get_all_event_for_worker(profile_id)
        if result is not None:
            return WorkerForEventSerializer(result,many=True)
        return result
    
    def get_all_event_for_judge(profile_id:int) -> Optional[JudgeForEventSerializer]:    
        result = get_all_event_for_judge(profile_id)
        if result is not None:
            return JudgeForEventSerializer(result,many=True)
        return result
    
    def get_all_event_for_participant(profile_id:int) -> Optional[ParticipantForEventSerializer]:    
        result = get_all_event_for_participant(profile_id)
        if result is not None:
            return ParticipantForEventSerializer(result,many=True)
        return result
    
    def get_all_event_for_viewer(profile_id:int) -> Optional[ViewerForEventSerializer]:    
        result = get_all_event_for_viewer(profile_id)
        if result is not None:
            return ViewerForEventSerializer(result,many=True)
        return result
    

    def add_new_invite(invite:InvitePostSerializer) -> None:            #ЕСТЬ
        invite_data = invite.data     # получаем валидированные с помощью сериализатора данные (метод .data  возвращает объект типа dict)
        create_invite(id_profile=invite_data.get('profile'),
                           id_event=invite_data.get('event'),
                           vacancy=invite_data.get('vacancy'),
                           
                       )
        
    
    def get_one_invite(invite_id:int) -> Optional[InviteSerializer]:    
        result = get_invite_by_id(invite_id)
        if result is not None:
            return InviteSerializer(result)
        return result
    
    def get_invite_for_event(event_id:int) -> Optional[InviteSerializer]:    
        result = get_all_invite_for_event(event_id)
        if result is not None:
            return InviteSerializer(result,many=True)
        return result
    
    def get_invite_for_profile(user_id:int) -> Optional[InviteSerializer]:    
        result = get_all_invite_for_profile(user_id)
        if result is not None:
            return InviteSerializer(result,many=True)
        return result
    

    def delete_one_invite(invite_id:int)-> None:            #ЕСТЬ
        delete_invite_by_id(invite_id)
    











    
class UploadFile(forms.ModelForm):
    class Meta:
        model = Files
        fields = ('title', 'pdf',)
    
    
    """ 
    def get_all_condition(self) -> Optional[ConditionSerializer]:             #ЕСТЬ
        result = get_all_condition()
        if result is not None:
            return ConditionSerializer(result,many=True)
        return result
    
    def get_all_condition_one_profile(self,id: int) -> Optional[ConditionSerializer]:            #ЕСТЬ
        print(id)
        result = get_all_condition_for_profile(id)
        print(result)
        if result is not None:
            print(ConditionSerializer(result))
            return ConditionSerializer(result,many=True)
        return result
    
   
    
    def delete_one_condition(self,id:int)-> None:            #ЕСТЬ
        delete_condition_by_id(id)

    def put_condition(self,condition:ConditionSerializerPost)-> None:            #ЕСТЬ
        cond=json.dumps(condition.data,ensure_ascii=False,default=ConditionSerializerPost)
        condition_data=json.loads(cond)
        update_condition_by_id(id=condition_data.get('id'),
                         assessment=condition_data.get('assessment'),
                         description=condition_data.get('description'),
                         work=condition_data.get('work'),
                         reading=condition_data.get('reading'),
                         workout=condition_data.get('workout'),
                         travel=condition_data.get('travel'),
                         TV=condition_data.get('TV'),
                         shopping=condition_data.get('shopping'),
                         sleep=condition_data.get('sleep'),
                         study=condition_data.get('study'),
                         family=condition_data.get('family'),
                         friend=condition_data.get('friend'),
                         gaming=condition_data.get('gaming'),
                         leisure=condition_data.get('leisure'),
                         cleaning=condition_data.get('cleaning'),
                         bar=condition_data.get('bar'),
                         movie=condition_data.get('movie'),
                         music=condition_data.get('music'),
                         party=condition_data.get('party'),
                         rendezvous=condition_data.get('rendezvous')
        )

    def put_condition1(self,condition:ConditionSerializerPost)-> None:            #ЕСТЬ
        cond=json.dumps(condition.data,ensure_ascii=False,default=ConditionSerializerPost)
        condition_data=json.loads(cond)
        update_condition_by_id1(id=condition_data.get('id'),
                         date=condition_data.get('date'),
                         assessment=condition_data.get('assessment'),
                         description=condition_data.get('description'),
                         work=condition_data.get('work'),
                         reading=condition_data.get('reading'),
                         workout=condition_data.get('workout'),
                         travel=condition_data.get('travel'),
                         TV=condition_data.get('TV'),
                         shopping=condition_data.get('shopping'),
                         sleep=condition_data.get('sleep'),
                         study=condition_data.get('study'),
                         family=condition_data.get('family'),
                         friend=condition_data.get('friend'),
                         gaming=condition_data.get('gaming'),
                         leisure=condition_data.get('leisure'),
                         cleaning=condition_data.get('cleaning'),
                         bar=condition_data.get('bar'),
                         movie=condition_data.get('movie'),
                         music=condition_data.get('music'),
                         party=condition_data.get('party'),
                         rendezvous=condition_data.get('rendezvous')
        )
        
        #Test

    def get_one_test(self, id: int) -> Optional[TestSerializer]:           #ЕСТЬ
        result = get_test_by_id(id)
        if result is not None:
            return TestSerializer(result)
        return result
    

    def get_all_tests(self) -> Optional[TestSerializer]:               #ЕСТЬ
        result = get_all_tests()
        if result is not None:
            return TestSerializer(result,many=True)
        return result
    

           #QUESTION

    def get_one_question(self,id_test:int,number:int)-> Optional[QuestionSerializer]: #ЕСТЬ
        result = get_question_by_test_and_number(id_test=id_test,number=number)
        if result is not None:
            return QuestionSerializer(result)
        return result
    
    #ANSWER

    def get_answers(self,id_test:int,number:int)-> Optional[AnswerSerializer]: #ЕСТЬ
        result = get_answers_by_question(id_test=id_test,number=number)
        if result is not None:
            return AnswerSerializer(result,many=True)
        return result
    
    #RESULTTEST

    def add_new_result(self,resulttest:ResultTestSerializerPost) -> None:
        resultTest=json.dumps(resulttest.data,ensure_ascii=False,default=ResultTestSerializer)
        result_data=json.loads(resultTest)
        print(result_data)
        print(type(result_data))
        create_result_test(
                         description=result_data.get('description'),
                         test_completion_time=result_data.get('test_completion_time'),
                         score=result_data.get('score'),
                         user_id=result_data.get('profile'),
                         test_id=result_data.get('test')
                         )
        
    def get_one_result(self,test_id:int,id_user:int)-> Optional[ResultTestSerializer]:
        result = get_result_test(test_id=test_id,user=id_user)
        if result is not None:
            return ResultTestSerializer(result)
        return result


    def get_all_result(self,profile:int,test_id:int)-> Optional[ResultTestSerializer]:
        result = get_all_result_test_for_profile(profile=profile,test_id=test_id)
        print(result)
        if result is not None:
            return ResultTestSerializer(result,many=True)
        return result


#PROFILEEEEEEEEEEEEEEEEEEEEE



    def get_one_profile(self,userid:int)-> Optional[ProfileSerializer]:
        result = get_profile(id=userid)
        if result is not None:
            return ProfileSerializer(result)
        return result


    def put_profile(self,user:UserSerializer1)-> None:
        profile=get_profile(user)
        update_profile(first_name=profile.get('id'),
                               second_name=profile.get('assessment'),
                               sex=profile.get('description'),
                               age=profile.get('age'),
                               user=user
                               )
        

    def get_description_result(self,id_test:int,points:int)-> Optional[ResultDescriptionSerializer]:
        result = get_one_description_result(id_test=id_test,points=points)
        if result is not None:
            return ResultDescriptionSerializer(result)
        return result
    

    def get_analysis(self,id_user:int,caseMy:str)-> QuerySet:
        result = lets_analysis(id_user=id_user,caseMy=caseMy)
        if result is not None:
            return result
        return result


"""