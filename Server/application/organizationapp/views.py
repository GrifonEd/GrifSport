from django.shortcuts import render, redirect
from rest_framework.generics import CreateAPIView, RetrieveDestroyAPIView, GenericAPIView
from rest_framework.request import Request
from rest_framework.response import Response
from rest_framework.renderers import JSONRenderer
from rest_framework.views import APIView
from rest_framework import status
from drf_yasg.views import get_schema_view
from django.http import HttpResponse
import json
import collections
from django.views.generic.edit import CreateView
from .models  import Files
from .forms import UploadFileForm
from django.urls import reverse_lazy
from django.views.decorators.csrf import csrf_exempt
from .serializers import InviteSerializer,InvitePostSerializer,EventSerializer,EventTypeSerializer,SponsorSerializer,SponsorForEventSerializer,OrganizerSerializer,OrganizerForEventSerializer,JudgeSerializer,JudgeForEventSerializer,ParticipantSerializer,ParticipantForEventSerializer,WorkerSerializer,WorkerForEventSerializer,TypeSportSerializer,SportSerializer,DisciplineSerializer,DisciplineForEventSerializer,ApplicationSerializer,ProfileSerializer,UserSerializer,ViewerForEventSerializer,ViewerSerializer,ApplicationPostSerializer
from .services.organization_service import OrganizationService,UploadFile
from .models import Files
# Create your views here.

service=OrganizationService
upload=UploadFile
class Docs(GenericAPIView):
    renderer_classes = [JSONRenderer]

    def get(self, request: Request) -> Response:                                        
        docs = {
	"Status": 'Скоро будет'
}

        response = docs
        return Response(response)

class GetDelEvent(GenericAPIView):
    serializer_class = EventSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request, event_id: int) -> Response:                                 
        response = service.get_event(event_id=event_id)
        return Response(data=response.data)
    
    def delete(self, request: Request,event_id: int) -> Response:                                        
        service.delete_event(event_id)
        return Response(status=status.HTTP_200_OK)
    
class GetAllEvent(GenericAPIView):
    serializer_class = EventSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request) -> Response:                                 
        response = service.get_all_event()
        return Response(data=response.data)
    
class PostPutEvent(GenericAPIView):
    serializer_class = EventSerializer   
    renderer_classes = [JSONRenderer]    
    
    def post(self, request: Request, *args, **kwargs) -> Response:
        serializer = EventSerializer(data=request.data)
        if serializer.is_valid():  
            response=service.add_new_event(serializer)
            return Response(data=response.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    
    def put(self, request: Request, *args, **kwargs) -> Response:
        serializer = EventSerializer(data=request.data)
        if serializer.is_valid():
            service.put_event(serializer)
            return Response(status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    

class GetWorker(GenericAPIView):
    serializer_class = WorkerSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request, worker_id: int) -> Response:                                 
        response = service.get_worker(id=worker_id)
        return Response(data=response.data)
    
class PostWorker(GenericAPIView):
    serializer_class = WorkerSerializer   
    renderer_classes = [JSONRenderer]    
    
    def post(self, request: Request, *args, **kwargs) -> Response:
        print(request.data)
        serializer = WorkerSerializer(data=request.data)
        if serializer.is_valid():  
            service.add_new_worker(serializer)
            return Response(status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    
class GetJudge(GenericAPIView):
    serializer_class = JudgeSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request, judge_id: int) -> Response:                                 
        response = service.get_judge(id=judge_id)
        return Response(data=response.data)
    
class GetViewer(GenericAPIView):
    serializer_class = ViewerSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request, viewer_id: int) -> Response:                                 
        response = service.get_viewer(id=viewer_id)
        return Response(data=response.data)
    
class PostJudge(GenericAPIView):
    serializer_class = JudgeSerializer   
    renderer_classes = [JSONRenderer]    
    
    def post(self, request: Request, *args, **kwargs) -> Response:
        serializer = JudgeSerializer(data=request.data)
        if serializer.is_valid():  
            service.add_new_judge(serializer)
            return Response(status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    
class PostViewer(GenericAPIView):
    serializer_class = ViewerSerializer   
    renderer_classes = [JSONRenderer]    
    
    def post(self, request: Request, *args, **kwargs) -> Response:
        serializer = ViewerSerializer(data=request.data)
        if serializer.is_valid():  
            service.add_new_viewer(serializer)
            return Response(status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    
class GetOrganizer(GenericAPIView):
    serializer_class = OrganizerSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request, organizer_id: int) -> Response:                                 
        response = service.get_organizer(id=organizer_id)
        return Response(data=response.data)
    
class GetOrganizerForProfile(GenericAPIView):
    serializer_class = OrganizerSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request, profile_id: int) -> Response:                                 
        response = service.get_organizer_for_profile(id=profile_id)
        return Response(data=response.data)

    
class PostOrganizer(GenericAPIView):
    serializer_class = ParticipantSerializer   
    renderer_classes = [JSONRenderer]    
    
    def post(self, request: Request, *args, **kwargs) -> Response:
        serializer = OrganizerSerializer(data=request.data)
        if serializer.is_valid():  
            service.add_new_organizer(serializer)
            return Response(status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    
class GetParticipant(GenericAPIView):
    serializer_class = ParticipantSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request, participant_id: int) -> Response:                                 
        response = service.get_participant(id=participant_id)
        return Response(data=response.data)
    
class PostParticipant(GenericAPIView):
    serializer_class = ParticipantSerializer   
    renderer_classes = [JSONRenderer]    
    
    def post(self, request: Request, *args, **kwargs) -> Response:
        serializer = ParticipantSerializer(data=request.data)
        if serializer.is_valid():  
            service.add_new_participant(serializer)
            return Response(status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    
class PostParticipantForEvent(GenericAPIView):
    serializer_class = ParticipantForEventSerializer  
    renderer_classes = [JSONRenderer]    
    
    def post(self, request: Request, event_id:int,participant_id:int) -> Response:
        service.add_participant_for_event(event_id,participant_id)
        return Response(status=status.HTTP_201_CREATED)
    
class PostWorkerForEvent(GenericAPIView):
    serializer_class = WorkerForEventSerializer  
    renderer_classes = [JSONRenderer]    
    
    def post(self, request: Request, event_id:int,worker_id:int) -> Response:
        service.add_worker_for_event(event_id,worker_id)
        return Response(status=status.HTTP_201_CREATED)
    
class PostJudgeForEvent(GenericAPIView):
    serializer_class = JudgeForEventSerializer  
    renderer_classes = [JSONRenderer]    
    
    def post(self, request: Request, event_id:int,judge_id:int) -> Response:
        service.add_judge_for_event(event_id,judge_id)
        return Response(status=status.HTTP_201_CREATED)
    
class PostViewerForEvent(GenericAPIView):
    serializer_class = ViewerForEventSerializer  
    renderer_classes = [JSONRenderer]    
    
    def post(self, request: Request, event_id:int,viewer_id:int) -> Response:
        service.add_viewer_for_event(event_id,viewer_id)
        return Response(status=status.HTTP_201_CREATED)
    
class PostOrganizerForEvent(GenericAPIView):
    serializer_class = OrganizerForEventSerializer  
    renderer_classes = [JSONRenderer]    
    
    def post(self, request: Request, event_id:int,organizer_id:int) -> Response:
        service.add_organizer_for_event(event_id,organizer_id)
        return Response(status=status.HTTP_201_CREATED)
       

class PostApplication(GenericAPIView):
    serializer_class = ApplicationSerializer   
    renderer_classes = [JSONRenderer]    
    
    def post(self, request: Request, *args, **kwargs) -> Response:
        serializer = ApplicationPostSerializer(data=request.data)
        if serializer.is_valid():  
            service.add_new_application(serializer)
            return Response(status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    


class GetApplicationForProfile(GenericAPIView):
    serializer_class = ApplicationSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request, user_id: int) -> Response:                                 
        response = service.get_applications_for_profile(user_id=user_id)
        return Response(data=response.data)
    

class GetApplicationForEvent(GenericAPIView):
    serializer_class = ApplicationSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request, event_id: int) -> Response:                                 
        response = service.get_applications_for_event(event_id=event_id)
        return Response(data=response.data)

class GetApplication(GenericAPIView):
    serializer_class = ApplicationSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request, application_id: int) -> Response:                                 
        response = service.get_one_application(application_id=application_id)
        return Response(data=response.data)
    
    def delete(self, request: Request,application_id: int) -> Response:                                        
        service.delete_one_application(application_id)
        return Response(status=status.HTTP_200_OK)



class GetWorkerForEvent(GenericAPIView):
    serializer_class = WorkerForEventSerializer  
    renderer_classes = [JSONRenderer]    
    
    def get(self, request: Request, event_id: int) -> Response:                                 
        response = service.get_all_workers_for_event(event_id=event_id)
        return Response(data=response.data)

class GetParticipantForEvent(GenericAPIView):
    serializer_class = ParticipantForEventSerializer  
    renderer_classes = [JSONRenderer]    
    
    def get(self, request: Request, event_id: int) -> Response:                                 
        response = service.get_all_participants_for_event(event_id=event_id)
        return Response(data=response.data)
    
class GetJudgeForEvent(GenericAPIView):
    serializer_class = JudgeForEventSerializer  
    renderer_classes = [JSONRenderer]    
    
    def get(self, request: Request, event_id: int) -> Response:                                 
        response = service.get_all_judges_for_event(event_id=event_id)
        return Response(data=response.data)
    
class GetViewerForEvent(GenericAPIView):
    serializer_class = ViewerForEventSerializer  
    renderer_classes = [JSONRenderer]    
    
    def get(self, request: Request, event_id: int) -> Response:                                 
        response = service.get_all_viewers_for_event(event_id=event_id)
        return Response(data=response.data)

class GetOrganizerForEvent(GenericAPIView):
    serializer_class = OrganizerForEventSerializer  
    renderer_classes = [JSONRenderer]    
    
    def get(self, request: Request, event_id: int) -> Response:                                 
        response = service.get_all_organizers_for_event(event_id=event_id)
        return Response(data=response.data)    

class GetDisciplineForEvent(GenericAPIView):
    serializer_class = DisciplineForEventSerializer  
    renderer_classes = [JSONRenderer]    
    
    def get(self, request: Request, event_id: int) -> Response:                                 
        response = service.get_all_disciplines_for_event(event_id=event_id)
        return Response(data=response.data) 

class GetProfile(GenericAPIView):
    serializer_class = ProfileSerializer  
    renderer_classes = [JSONRenderer]    
    
    def get(self, request: Request, user_id: int) -> Response:                                 
        response = service.get_profile_by_user_id(user_id=user_id)
        return Response(data=response.data) 


class GetAllProfile(GenericAPIView):
    serializer_class = ProfileSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request) -> Response:                                 
        response = service.get_all_profile()
        return Response(data=response.data)


class GetAllOrganizerForEvent(GenericAPIView):
    serializer_class = OrganizerForEventSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request,profile_id) -> Response:                                 
        response = service.get_all_event_for_organizer(profile_id)
        return Response(data=response.data)
    
class GetAllParticipantForEvent(GenericAPIView):
    serializer_class = OrganizerForEventSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request,profile_id) -> Response:                                 
        response = service.get_all_event_for_participant(profile_id)
        return Response(data=response.data)
    
class GetAllWorkerForEvent(GenericAPIView):
    serializer_class = OrganizerForEventSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request,profile_id) -> Response:                                 
        response = service.get_all_event_for_worker(profile_id)
        return Response(data=response.data)
    
class GetAllViewerForEvent(GenericAPIView):
    serializer_class = OrganizerForEventSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request,profile_id) -> Response:                                 
        response = service.get_all_event_for_viewer(profile_id)
        return Response(data=response.data)
    
class GetAllJudgeForEvent(GenericAPIView):
    serializer_class = OrganizerForEventSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request,profile_id) -> Response:                                 
        response = service.get_all_event_for_judge(profile_id)
        return Response(data=response.data)



class PostInvite(GenericAPIView):
    serializer_class = InviteSerializer   
    renderer_classes = [JSONRenderer]    
    
    def post(self, request: Request, *args, **kwargs) -> Response:
        serializer = InvitePostSerializer(data=request.data)
        if serializer.is_valid():  
            service.add_new_invite(serializer)
            return Response(status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
    


class GetInviteForProfile(GenericAPIView):
    serializer_class = InviteSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request, user_id: int) -> Response:                                 
        response = service.get_invite_for_profile(user_id=user_id)
        return Response(data=response.data)
    

class GetInviteForEvent(GenericAPIView):
    serializer_class = InviteSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request, event_id: int) -> Response:                                 
        response = service.get_invite_for_event(event_id=event_id)
        return Response(data=response.data)

class GetInvite(GenericAPIView):
    serializer_class = InviteSerializer
    renderer_classes = [JSONRenderer]
        
    def get(self, request: Request, invite_id: int) -> Response:                                 
        response = service.get_one_invite(invite_id=invite_id)
        return Response(data=response.data)
    
    def delete(self, request: Request,invite_id: int) -> Response:                                        
        service.delete_one_invite(invite_id)
        return Response(status=status.HTTP_200_OK)








class PostFile(GenericAPIView):
   
   def post(self, request: Request) -> Response:             
        form = UploadFile(request.POST,request.FILES)
        if form.is_valid():
            form.save()
            return HttpResponse('The file is saved')
        return Response(request.errors, status=status.HTTP_400_BAD_REQUEST)
   
@csrf_exempt
def upload_file(request):
    if request.method == 'POST':
        form = UploadFileForm(request.POST,request.FILES," ")
        print(form)
        file1 = request.FILES.get('fileee')
        files=Files.objects.create(title="hm",pdf=file1)
        files.save()
        return HttpResponse("HMMMM"+str(file1))
    else:
        form = UploadFileForm()
    return Response(request.errors, status=status.HTTP_400_BAD_REQUEST)

class UploadView(CreateView):
    model = Files
    fields = ['pdf', ]
    success_url = reverse_lazy('fileupload')
    @csrf_exempt
    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['documents'] = Files.objects.all()
        return context
    
