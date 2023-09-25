from django.urls import include, path,re_path
from drf_yasg.views import get_schema_view
from drf_yasg import openapi
from rest_framework import permissions
from . import views
from django.conf import settings
from django.conf.urls.static import static



schema_view = get_schema_view(
    openapi.Info(
        title=" Organization API",
        default_version='v1',
        description="Organization API",
        terms_of_service="https://example.com",
        ontact=openapi.Contact(email="contact@mail.local"),
        license=openapi.License(name="BSD License"),
    ),
    public=True,
    permission_classes=[permissions.AllowAny],
)


urlpatterns = [
    
    path('event/<int:event_id>', views.GetDelEvent.as_view()),
    path('event/all', views.GetAllEvent.as_view()),
    path('event/create', views.PostPutEvent.as_view()),

    path('participant/<int:participant_id>', views.GetParticipant.as_view()),
    path('participant/create', views.PostParticipant.as_view()),
    path('participant-event/create/<int:event_id>/<int:participant_id>', views.PostParticipantForEvent.as_view()),

    path('worker-event/create/<int:event_id>/<int:worker_id>', views.PostWorkerForEvent.as_view()),
    path('judge-event/create/<int:event_id>/<int:judge_id>', views.PostJudgeForEvent.as_view()),
    path('viewer-event/create/<int:event_id>/<int:viewer_id>', views.PostViewerForEvent.as_view()),
    path('organizer-event/create/<int:event_id>/<int:organizer_id>', views.PostOrganizerForEvent.as_view()),
    path('participant-event/<int:event_id>', views.GetParticipantForEvent.as_view()),
    path('worker-event/<int:event_id>', views.GetWorkerForEvent.as_view()),
    path('judge-event/<int:event_id>', views.GetJudgeForEvent.as_view()),
    path('viewer-event/<int:event_id>', views.GetViewerForEvent.as_view()),
    path('organizer-event/<int:event_id>', views.GetOrganizerForEvent.as_view()),
    path('discipline-event/<int:event_id>', views.GetDisciplineForEvent.as_view()),

    path('profile/get/<int:user_id>', views.GetProfile.as_view()),

    path('organizer/profile/<int:profile_id>', views.GetOrganizerForProfile.as_view()),
   
    path('worker/<int:worker_id>', views.GetWorker.as_view()),
    path('worker/create', views.PostWorker.as_view()),

    path('judge/<int:judge_id>', views.GetJudge.as_view()),
    path('judge/create', views.PostJudge.as_view()),

    path('viewer/<int:viewer_id>', views.GetViewer.as_view()),
    path('viewer/create', views.PostViewer.as_view()),
    
    path('organizer/<int:organizer_id>', views.GetOrganizer.as_view()),
    path('organizer/create', views.PostOrganizer.as_view()),
   
    path('', views.Docs.as_view()),
    
    
    path('application/profile/<int:user_id>', views.GetApplicationForProfile.as_view()),
    path('application/event/<int:event_id>', views.GetApplicationForEvent.as_view()),
    path('application/<int:application_id>', views.GetApplication.as_view()),
    path('application/create', views.PostApplication.as_view()),

    path('invite/profile/<int:user_id>', views.GetInviteForProfile.as_view()),
    path('invite/event/<int:event_id>', views.GetInviteForEvent.as_view()),
    path('invite/<int:invite_id>', views.GetInvite.as_view()),
    path('invite/create', views.PostInvite.as_view()),


    path('file/', views.Docs.as_view()),
    path('file/create', views.PostFile.as_view()),
    path('upload', views.UploadView.as_view(), name='fileupload'),
    path('upload1',views.upload_file, name='fileupload'),

    path('profile/all', views.GetAllProfile.as_view()),

    path('organizer-event/getAll/<int:profile_id>', views.GetAllOrganizerForEvent.as_view()),
    path('judge-event/getAll/<int:profile_id>', views.GetAllJudgeForEvent.as_view()),
    path('worker-event/getAll/<int:profile_id>', views.GetAllWorkerForEvent.as_view()),
    path('participant-event/getAll/<int:profile_id>', views.GetAllParticipantForEvent.as_view()),
    path('viewer-event/getAll/<int:profile_id>', views.GetAllViewerForEvent.as_view()),


    re_path(r'^swagger(?P<format>\.json|\.yaml)$',schema_view.without_ui(cache_timeout=0), name='schema-json'),
    re_path(r'^swagger/$', schema_view.with_ui('swagger', cache_timeout=0),name='schema-swagger-ui'),
]

if settings.DEBUG:
    urlpatterns+=static(settings.MEDIA_URL,document_root=settings.MEDIA_ROOT)

