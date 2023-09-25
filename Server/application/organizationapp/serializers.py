from rest_framework import serializers

class UserSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    username = serializers.CharField()
    email = serializers.CharField()
    date_joined = serializers.DateTimeField()
    is_active = serializers.BooleanField()
    is_staff= serializers.BooleanField()
    is_verified = serializers.BooleanField()

class ProfileSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    user = UserSerializer()
    first_name = serializers.CharField()
    second_name = serializers.CharField()
    sex = serializers.CharField()
    age= serializers.IntegerField()
    city = serializers.CharField()
    phone_number = serializers.CharField()

class OrganizerSerializer(serializers.Serializer):
    profile = ProfileSerializer()
    id = serializers.IntegerField()
    description = serializers.CharField()

class WorkerSerializer(serializers.Serializer):
    profile = ProfileSerializer()
    id = serializers.IntegerField()
    description = serializers.CharField()

class ParticipantSerializer(serializers.Serializer):
    profile = ProfileSerializer()
    id = serializers.IntegerField()
    description = serializers.CharField()

class JudgeSerializer(serializers.Serializer):
    profile = ProfileSerializer()
    id = serializers.IntegerField()
    description = serializers.CharField()

class ViewerSerializer(serializers.Serializer):
    profile = ProfileSerializer()
    id = serializers.IntegerField()
    description = serializers.CharField()

class TypeSportSerializer(serializers.Serializer):
    olympic = serializers.BooleanField()
    id = serializers.IntegerField()
    team = serializers.BooleanField()

class SportSerializer(serializers.Serializer):
    name = serializers.CharField()
    id = serializers.IntegerField()
    typeSport = TypeSportSerializer()

class DisciplineSerializer(serializers.Serializer):
    name = serializers.CharField()
    id = serializers.IntegerField()
    sport = SportSerializer()

class EventTypeSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    type = serializers.CharField()

class CitySerializer(serializers.Serializer):
    name = serializers.CharField()
    id = serializers.IntegerField()
    

class EventSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    name = serializers.CharField()
    description = serializers.CharField()
    adress =  serializers.CharField()
    city = CitySerializer()
    date_created = serializers.DateTimeField()
    event_start_date =  serializers.DateTimeField()
    event_end_date =  serializers.DateTimeField()
    image = serializers.CharField()
    schedule = serializers.CharField() 
    results = serializers.CharField(allow_blank=True)
    type = EventTypeSerializer()
    sport = SportSerializer()
    deadline_for_accepting_applications =  serializers.DateTimeField()
    ages=serializers.CharField()
    genders =serializers.CharField()
    phone_number =  serializers.CharField()
    is_active =  serializers.BooleanField()

class OrganizerForEventSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    event = EventSerializer()
    organizer = OrganizerSerializer()

class JudgeForEventSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    event = EventSerializer()
    judge = JudgeSerializer()

class ViewerForEventSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    event = EventSerializer()
    viewer = ViewerSerializer()

class WorkerForEventSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    event = EventSerializer()
    worker = WorkerSerializer()

class ParticipantForEventSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    event = EventSerializer()
    participant = ParticipantSerializer()

class DisciplineForEventSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    event = EventSerializer()
    discipline = DisciplineSerializer()

class SponsorSerializer(serializers.Serializer):
    profile = ProfileSerializer()
    id = serializers.IntegerField()
    description = serializers.CharField()

class SponsorForEventSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    event = EventSerializer()
    sponsor = SponsorSerializer()

class ApplicationSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    profile = ProfileSerializer()
    event = EventSerializer()
    vacancy =   serializers.CharField()
    description = serializers.CharField(allow_blank=True)

class ApplicationPostSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    profile = serializers.IntegerField()
    event = serializers.IntegerField()
    vacancy =   serializers.CharField()
    description = serializers.CharField(allow_blank=True)

class InviteSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    profile = ProfileSerializer()
    event = EventSerializer()
    vacancy =   serializers.CharField()

class InvitePostSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    profile = serializers.IntegerField()
    event = serializers.IntegerField()
    vacancy =   serializers.CharField()


class FilesSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    files = serializers.FileField()
    event = EventSerializer()

class FilesForEventSerializer(serializers.Serializer):
    id = serializers.IntegerField()
    files = FilesSerializer()
    event = EventSerializer()