from typing import Optional, Iterable, List
from django.db.models import QuerySet
import matplotlib.pyplot as plt
from ..models import User,City, Invite,Profile,Event,Application,Organizer,Worker,Judge,Participant,ParticipantForEvent,JudgeForEvent,OrganizerForEvent,WorkerForEvent,Sport,TypeSport,TypeEvent,Discipline,DisciplineForEvent,Viewer,ViewerForEvent
import pandas 
from pandas import describe_option
import pandas_profiling
import numpy 
import scipy.stats 
from pandas_profiling import profile_report 
import django_pandas.io
import math






# Event
def get_event_by_id(event_id: int) -> Optional[Event]:      #Есть
   
    event = Event.objects.filter(id=event_id).first()
    return event

def get_all_event() -> Optional[Event]:      #Есть
   
    event = Event.objects.all()
    return event

def create_event(name:str,description:str,adress:str,event_start_date:str,event_end_date:str,image:str,schedule:str,results:str,type:str,deadline_for_accepting_applications:str,ages:str,genders:str,phone_number:str,city:str,sport:str) -> Optional[Event]:
    typeEvent=get_type_event_by_name(type)      #Есть но сериалайзером типа ивента
    sport1=get_sport_event_by_name(sport) 
    city1=get_city_event_by_name(city) 
    event = Event.objects.create(name=name,
                                description=description,
                                adress=adress,
                                event_start_date=event_start_date,
                                event_end_date=event_end_date,
                                image=image,
                                schedule=schedule,
                                results=results,
                                type=typeEvent,
                                deadline_for_accepting_applications=deadline_for_accepting_applications,
                                ages=ages,
                                genders=genders,
                                phone_number=phone_number,
                                sport=sport1,
                                city=city1
                                    )

    event.save()
    return event

def update_event_by_id(id:int,name:str,description:str,adress:str,event_start_date:str,event_end_date:str,image:str,schedule:str,results:str,type:str,deadline_for_accepting_applications:str,ages:str,genders:str,phone_number:str) -> None:
    typeEvent=get_type_event_by_name(type) #Есть но сериалайзером типа ивента
    event=get_event_by_id(id)
    event.name=name
    event.description=description
    event.adress=adress
    event.event_start_date=event_start_date
    event.event_end_date=event_end_date
    event.image=image
    event.schedule=schedule
    event.results=results
    event.type=typeEvent
    event.deadline_for_accepting_applications=deadline_for_accepting_applications
    event.ages=ages
    event.genders=genders
    event.phone_number=phone_number
    event.save()


def delete_event_by_id(id_event: int) -> None:      #Есть
    get_event_by_id(id_event).delete()



def create_participant_for_event(id_participant:int,id_event:int) -> None:
    participant = get_participant_by_id_profile(id_participant)
    event = get_event_by_id(id_event)
    participantForEvent = ParticipantForEvent.objects.create(participant=participant,
                                             event=event,
                                    )
    participantForEvent.save()

def create_worker_for_event(id_worker:int,id_event:int) -> None:
    worker = get_worker_by_id_profile(id_worker)
    event = get_event_by_id(id_event)
    workerForEvent = WorkerForEvent.objects.create(worker=worker,
                                             event=event,
                                    )
    workerForEvent.save()

def create_judge_for_event(id_judge:int,id_event:int) -> None:
    judge = get_judge_by_id_profile(id_judge)
    event = get_event_by_id(id_event)
    judgeForEvent = JudgeForEvent.objects.create(judge=judge,
                                             event=event,
                                    )
    judgeForEvent.save()


def create_viewer_for_event(id_viewer:int,id_event:int) -> None:
    viewer = get_viewer_by_id_profile(id_viewer)
    event = get_event_by_id(id_event)
    viewerForEvent = ViewerForEvent.objects.create(viewer=viewer,
                                             event=event,
                                    )
    viewerForEvent.save()

def create_organizer_for_event(id_organizer:int,id_event:int) -> None:
    organizer = get_organizer_by_id(id_organizer)
    event = get_event_by_id(id_event)
    organizerForEvent = OrganizerForEvent.objects.create(organizer=organizer,
                                             event=event,
                                    )
    organizerForEvent.save()

def get_participant_by_id(id_participant:int)->Optional[Participant]:
    participant=Participant.objects.filter(id=id_participant).first()
    return participant

def get_worker_by_id(id_worker:int)->Optional[Worker]:
    worker=Worker.objects.filter(id=id_worker).first()
    return worker

def get_judge_by_id(id_judge:int)->Optional[Judge]:
    judge=Judge.objects.filter(id=id_judge).first()
    return judge

def get_viewer_by_id(id_viewer:int)->Optional[Viewer]:
    viewer=Viewer.objects.filter(id=id_viewer).first()
    return viewer

def get_organizer_by_id(id_organizer:int)->Optional[Organizer]:
    organizer=Organizer.objects.filter(id=id_organizer).first()
    return organizer

def get_organizer_by_id_profile(id_profile:int)->Optional[Organizer]:
    profile = Profile.objects.filter(id=id_profile).first()
    organizer=Organizer.objects.filter(profile=profile).first()
    return organizer

def get_viewer_by_id_profile(id_profile:int)->Optional[Viewer]:
    profile = Profile.objects.filter(id=id_profile).first()
    viewer=Viewer.objects.filter(profile=profile).first()
    return viewer

def get_worker_by_id_profile(id_profile:int)->Optional[Worker]:
    profile = Profile.objects.filter(id=id_profile).first()
    worker=Worker.objects.filter(profile=profile).first()
    return worker

def get_participant_by_id_profile(id_profile:int)->Optional[Participant]:
    profile = Profile.objects.filter(id=id_profile).first()
    participant=Participant.objects.filter(profile=profile).first()
    return participant

def get_judge_by_id_profile(id_profile:int)->Optional[Judge]:
    profile = Profile.objects.filter(id=id_profile).first()
    judge=Judge.objects.filter(profile=profile).first()
    return judge


def get_organizers_for_event(id_event:int)->Optional[OrganizerForEvent]:
    event=get_event_by_id(id_event)
    eventOrgan = OrganizerForEvent.objects.filter(event=event).all()
    return eventOrgan

def get_participants_for_event(id_event:int)->Optional[ParticipantForEvent]:
    event=get_event_by_id(id_event)
    eventParticipant = ParticipantForEvent.objects.filter(event=event).all()
    return eventParticipant

def get_judge_for_event(id_event:int)->Optional[JudgeForEvent]:
    event=get_event_by_id(id_event)
    eventJudges = JudgeForEvent.objects.filter(event=event).all()
    return eventJudges

def get_viewer_for_event(id_event:int)->Optional[ViewerForEvent]:
    event=get_event_by_id(id_event)
    eventViewers = ViewerForEvent.objects.filter(event=event).all()
    return eventViewers

def get_workers_for_event(id_event:int)->Optional[WorkerForEvent]:
    event=get_event_by_id(id_event)
    eventWorker = WorkerForEvent.objects.filter(event=event).all()
    return eventWorker

def get_type_event_by_name(type:str) -> Optional[TypeEvent]:
    typeEvent=TypeEvent.objects.filter(type=type).first()
    return typeEvent

def get_city_event_by_name(city:str) -> Optional[City]:
    city=City.objects.filter(name=city).first()
    return city

def get_sport_event_by_name(sport:str) -> Optional[Sport]:
    sport=Sport.objects.filter(name=sport).first()
    return sport


def create_participant(id_user:int,description:str) -> None:
    profile=get_profile(id_user)
    participant = Participant.objects.create(profile=profile,
                                         description=description
    )
    participant.save()
    
def create_judge(id_user:int,description:str) -> None:
    profile=get_profile(id_user)
    judge = Judge.objects.create(profile=profile,
                                         description=description
    )
    judge.save()


def create_viewer(id_user:int,description:str) -> None:
    profile=get_profile(id_user)
    viewer = Viewer.objects.create(profile=profile,
                                         description=description
    )
    viewer.save()

def create_worker(id_user:int,description:str) -> None:
    profile=get_profile(id_user)
    worker = Worker.objects.create(profile=profile,
                                         description=description
    )
    worker.save()

def create_organizer(id_user:int,description:str) -> None:
    profile=get_profile(id_user)
    organizer = Organizer.objects.create(profile=profile,
                                         description=description
    )
    organizer.save()


def get_profile(id: int) -> Optional[Profile]:   
    user=User.objects.filter(id=id).first()
    profile = Profile.objects.filter(user=user).first()
    return profile

def create_application(id_user:int,id_event:int,vacancy:str,description:str) -> None:
    profile=Profile.objects.filter(id=id_user).first()
    event = get_event_by_id(id_event)
    application = Application.objects.create(profile=profile,
                                             event=event,
                                             vacancy=vacancy,
                                             description=description 
                                    )
    application.save()

def get_application_by_id(application_id: int) -> Optional[Application]:      #Есть
    application = Application.objects.filter(id=application_id).first()
    return application

def get_all_application_for_event(event_id:int) -> Optional[Application]:      #Есть
    event1 = get_event_by_id(event_id)
    event = Application.objects.filter(event=event1).all()
    return event

def get_all_application_for_profile(user_id:int) -> Optional[Application]:      #Есть
    profile = get_profile(user_id)
    appllication = Application.objects.filter(profile=profile).all()
    return appllication


def delete_application_by_id(application_id: int) -> None:      #Есть
    get_application_by_id(application_id).delete()

def get_discipline_by_id(discipline_id:int)->Optional[Discipline]:
    discipline = Discipline.objects.filter(id=discipline_id).first()
    return discipline

def get_sport_by_id(sport_id:int)->Optional[Sport]:
    sport = Sport.objects.filter(id=sport_id).first()
    return sport

def get_discipline_for_event(event_id:int)-> Optional[DisciplineForEvent]:
    event=get_event_by_id(event_id=event_id)
    disciplines=DisciplineForEvent.objects.filter(event=event).all()
    return disciplines

def get_all_profiles() -> Optional[Profile]:      #Есть
   
    profiles = Profile.objects.all()
    return profiles

def get_all_event_for_organizer(profile_id:int)-> Optional[OrganizerForEvent]:    
    organizer=get_organizer_by_id_profile(profile_id)
    organizerForEvent= OrganizerForEvent.objects.filter(organizer=organizer).all()
    return organizerForEvent

def get_all_event_for_worker(profile_id:int)-> Optional[WorkerForEvent]:    
    worker=get_worker_by_id_profile(profile_id)
    workerForEvent= WorkerForEvent.objects.filter(worker=worker).all()
    return workerForEvent

def get_all_event_for_judge(profile_id:int)-> Optional[JudgeForEvent]:    
    judge=get_judge_by_id_profile(profile_id)
    judgeForEvent= JudgeForEvent.objects.filter(judge=judge).all()
    return judgeForEvent

def get_all_event_for_viewer(profile_id:int)-> Optional[ViewerForEvent]:    
    viewer=get_viewer_by_id_profile(profile_id)
    viewerForEvent= ViewerForEvent.objects.filter(viewer=viewer).all()
    return viewerForEvent

def get_all_event_for_participant(profile_id:int)-> Optional[ParticipantForEvent]:    
    participant=get_participant_by_id_profile(profile_id)
    participantForEvent= ParticipantForEvent.objects.filter(participant=participant).all()
    return participantForEvent


def create_invite(id_profile:int,id_event:int,vacancy:str) -> None:
    profile=Profile.objects.filter(id=id_profile).first()
    event = get_event_by_id(id_event)
    invite = Invite.objects.create(profile=profile,
                                             event=event,
                                             vacancy=vacancy,
                                    )
    invite.save()

def get_invite_by_id(invite_id: int) -> Optional[Invite]:      #Есть
    invite = Invite.objects.filter(id=invite_id).first()
    return invite

def get_all_invite_for_event(event_id:int) -> Optional[Event]:      #Есть
    event1 = get_event_by_id(event_id)
    event = Invite.objects.filter(event=event1).all()
    return event

def get_all_invite_for_profile(user_id:int) -> Optional[Invite]:      #Есть
    profile = Profile.objects.filter(id=user_id).first()
    invite = Invite.objects.filter(profile=profile).all()
    return invite


def delete_invite_by_id(invite_id: int) -> None:      #Есть
    print(invite_id)
    get_invite_by_id(invite_id).delete()



Viewer