from django.db.models import*
from django.db import models
from django.contrib.auth.models import PermissionsMixin
from django.contrib.auth.base_user import AbstractBaseUser
from django.utils import timezone
from django.utils.translation import gettext_lazy as _
from django.conf import settings
from organizationapp.managers import UserManager
import pandas
#from models import PhoneNumberField

# Create your models here.


class User(AbstractBaseUser, PermissionsMixin):
    username = models.CharField(_('username'), max_length=255, unique=True)
    email = models.EmailField(_('email address'),\
        null=False, blank=False,unique=True)
    date_joined = models.DateTimeField(_('date joined'),auto_now_add=True)
    is_active = models.BooleanField(_('active'), default=False)
    is_staff = models.BooleanField(_('staff'), default=False)

    is_verified = models.BooleanField(_('verified'), default=False)

    objects = UserManager()

    USERNAME_FIELD = 'username'
    REQUIRED_FIELDS = ['email']

    class Meta:
        verbose_name = _('user')
        verbose_name_plural = _('users')
        unique_together = ('username', 'email')
    
    def __str__(self):
        return f"{self.username}, {self.email}"
    


class Profile(models.Model):
    user = models.OneToOneField(
        settings.AUTH_USER_MODEL, on_delete=models.CASCADE
    )
    id = AutoField(primary_key=True)
    first_name = models.CharField("first_name", max_length=500,null=False)
    second_name = models.CharField("second_name", max_length=500,null=False)
    sex = models.CharField("sex", max_length=10,null=False)
    age = models.IntegerField("age",null=False)
    city = models.CharField("city", max_length=500,null=False)
    phone_number =  models.CharField("phone_number", max_length=12,null=False)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'profile'

    def __str__(self):
        return f"{self.first_name} {self.second_name}, {self.user.email}, {self.id}"
    
class Organizer(models.Model):
    profile = models.OneToOneField(Profile,on_delete=models.CASCADE,null=True)
    id = AutoField(primary_key=True)
    description = models.CharField("description", max_length=500,null=True)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'Organizer'

    def __str__(self):
        return f"{self.profile.first_name} {self.profile.second_name}, {self.profile.user.email}, {self.id}"
    
    
class Worker(models.Model):
    profile = models.OneToOneField(Profile,on_delete=models.CASCADE)
    id = AutoField(primary_key=True)
    description = models.CharField("description", max_length=500,null=True)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'Worker'

    def __str__(self):
        return f"{self.profile.first_name} {self.profile.second_name}, {self.profile.user.email}, {self.id}"

class Participant(models.Model):
    profile = models.OneToOneField(Profile,on_delete=models.CASCADE)
    id = AutoField(primary_key=True)
    description = models.CharField("description", max_length=500,null=True)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'Participant'

    def __str__(self):
        return f"{self.profile.first_name} {self.profile.second_name}, {self.profile.user.email}, {self.id}"
    
    
class Judge(models.Model):
    profile = models.OneToOneField(Profile,on_delete=models.CASCADE)
    id = AutoField(primary_key=True)
    description = models.CharField("description", max_length=500,null=True)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'Judge'

    def __str__(self):
        return f"{self.profile.first_name} {self.profile.second_name}, {self.profile.user.email}, {self.id}"
    
class Viewer(models.Model):
    profile = models.OneToOneField(Profile,on_delete=models.CASCADE)
    id = AutoField(primary_key=True)
    description = models.CharField("description", max_length=500,null=True)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'Viewer'

    def __str__(self):
        return f"{self.profile.first_name} {self.profile.second_name}, {self.profile.user.email}, {self.id}"    

class Discipline(models.Model):
    id = AutoField(primary_key=True)
    name = models.CharField( max_length=500)
    sport = ForeignKey('Sport', null=True, on_delete=CASCADE)
    type = ForeignKey('TypeDiscipline', null=True, on_delete=CASCADE)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'Discipline'

    def __str__(self):
        return f"{self.name} {self.sport}, {self.type}, {self.id}"
    
class Sport(models.Model):
    id = AutoField(primary_key=True)
    name = models.CharField("first_name", max_length=500)
    typeSport = ForeignKey('TypeSport', null=False, on_delete=CASCADE)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'Sport'

    def __str__(self):
        return f"{self.name} {self.typeSport}, {self.id}"
    

class City(models.Model):
    id = AutoField(primary_key=True)
    name = models.CharField("name", max_length=500)
    
    class Meta:
        """ Установка названия таблицы """
        db_table = 'City'

    def __str__(self):
        return f"{self.name} , {self.id}"

class Event(models.Model):
    id = AutoField(primary_key=True)
    name = CharField(max_length=255,null=False)
    description = CharField(max_length=255,blank=True)
    adress =  CharField(max_length=500,null=False)
    city = ForeignKey('City', null=True,blank=True, on_delete=SET_NULL)
    date_created = models.DateTimeField(auto_now_add=True)
    event_start_date = models.DateTimeField(null=False)
    event_end_date = models.DateTimeField(null=False)
    image = CharField(max_length=255,blank=True,null=True)
    schedule = CharField(max_length=5000,blank=True)
    sport = ForeignKey('Sport', null=True,blank=True, on_delete=SET_NULL)
    results = CharField(max_length=5000,blank=True, null=True)
    type = ForeignKey('TypeEvent', null=True, on_delete=SET_NULL)
    deadline_for_accepting_applications = models.DateTimeField(null=False)
    ages=CharField(max_length=255,null=False)
    genders = models.CharField("genders", max_length=50)
    phone_number =  models.CharField("phone_number", max_length=12,null=False)
    is_active =  models.BooleanField(default=True)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'Event'

    def __str__(self):
        return f"{self.name} {self.description}, {self.event_start_date}, {self.id}"




class OrganizerForEvent(models.Model):
    id = AutoField(primary_key=True)
    event = ForeignKey('Event', null=False, on_delete=CASCADE)
    organizer = ForeignKey('Organizer', null=False, on_delete=CASCADE)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'OrganizerForEvent'
        unique_together = ('event', 'organizer')

    def __str__(self):
        return f"{self.id} {self.event.name}, {self.organizer.profile.first_name}"
    
class JudgeForEvent(models.Model):
    id = AutoField(primary_key=True)
    event = ForeignKey('Event', null=False, on_delete=CASCADE)
    judge = ForeignKey('Judge', null=False, on_delete=CASCADE)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'JudgeForEvent'
        unique_together = ('event', 'judge')

    def __str__(self):
        return f"{self.id} {self.event.name}, {self.judge.profile.first_name}"
    
class ViewerForEvent(models.Model):
    id = AutoField(primary_key=True)
    event = ForeignKey('Event', null=False, on_delete=CASCADE)
    viewer = ForeignKey('Viewer', null=False, on_delete=CASCADE)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'ViewerForEvent'
        unique_together = ('event', 'viewer')

    def __str__(self):
        return f"{self.id} {self.event.name}, {self.viewer.profile.first_name}"

class WorkerForEvent(models.Model):
    id = AutoField(primary_key=True)
    event = ForeignKey('Event', null=False, on_delete=CASCADE)
    worker = ForeignKey('Worker', null=False, on_delete=CASCADE)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'WorkerForEvent'
        unique_together = ('event', 'worker')

    def __str__(self):
        return f"{self.id} {self.event.name}, {self.worker.profile.first_name}"
    
class ParticipantForEvent(models.Model):
    id = AutoField(primary_key=True)
    event = ForeignKey('Event', null=False, on_delete=CASCADE)
    participant = ForeignKey('Participant', null=False, on_delete=CASCADE)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'ParticipantForEvent'
        unique_together = ('event', 'participant')

    def __str__(self):
        return f"{self.id} {self.event.name}, {self.participant.profile.first_name}"

class DisciplineForEvent(models.Model):
    id = AutoField(primary_key=True)
    event = ForeignKey('Event', null=False, on_delete=CASCADE)
    discipline = ForeignKey('Discipline', null=False, on_delete=CASCADE)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'DisciplineForEvent'

    def __str__(self):
        return f"{self.id} {self.event.name}, {self.discipline.name}"
    
class TypeEvent(models.Model):
    id = AutoField(primary_key=True)
    type = CharField(max_length=255, null=False, unique=True)

    class Meta:
        db_table = 'TypeEvent'

    def __str__(self):
        return str({'id': self.id, 'type': self.type})


class TypeSport(models.Model):
    id = AutoField(primary_key=True)
    olympic = BooleanField(null=False,default=False)
    team  = BooleanField(null=False,default=False)

    class Meta:
        db_table = 'TypeSport'

    def __str__(self):
        return str({'id': self.id, 'olympic': self.olympic,'team': self.team})

class TypeDiscipline(models.Model):
    id = AutoField(primary_key=True)
    olympic = BooleanField(null=False,default=False)
    team  = BooleanField(null=False,default=False)

    class Meta:
        db_table = 'TypeDiscipline'

    def __str__(self):
        return str({'id': self.id, 'olympic': self.olympic,'team': self.team})
    
class Sponsor(models.Model):
    profile = models.OneToOneField(Profile,on_delete=models.CASCADE,null=True)
    id = AutoField(primary_key=True)
    description = models.CharField("description", max_length=500,null=True)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'Sponsor'

    def __str__(self):
        return f"{self.profile.first_name} {self.profile.second_name}, {self.profile.user.email}, {self.id}"


class SponsorForEvent(models.Model):
    id = AutoField(primary_key=True)
    event = ForeignKey('Event', null=False, on_delete=CASCADE)
    sponsor = ForeignKey('Sponsor', null=False, on_delete=CASCADE)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'SponsorForEvent'

    def __str__(self):
        return f"{self.id} {self.event.name}, {self.sponsor.profile.first_name}"
    
class Application(models.Model):
    id = AutoField(primary_key=True)
    profile = ForeignKey('Profile', null=False, on_delete=CASCADE)
    event = ForeignKey('Event', null=False, on_delete=CASCADE)
    vacancy =  models.CharField(max_length=50,null=False)
    description = models.CharField(max_length=5000,null=True,blank=True)

    class Meta:
        """ Установка названия таблицы """
        db_table = 'Application'
        unique_together = ('profile', 'event')

    def __str__(self):
        return f"{self.id} {self.event.name}, {self.profile.first_name}"
    
class Invite(models.Model):
    id = AutoField(primary_key=True)
    profile = ForeignKey('Profile', null=False, on_delete=CASCADE)
    event = ForeignKey('Event', null=False, on_delete=CASCADE)
    vacancy =  models.CharField(max_length=50,null=False)
    

    class Meta:
        """ Установка названия таблицы """
        db_table = 'Invite'
        unique_together = ('profile', 'event')

    def __str__(self):
        return f"{self.id} {self.event.name}, {self.profile.first_name}"




class Files(models.Model):
    id = AutoField(primary_key=True)
    title = models.CharField(max_length = 80)
    pdf = models.FileField(upload_to='pdfs/')

    class Meta:

        db_table = 'Files'

    def __str__(self):
        return f"{self.id}"
    
class FilesForEvent(models.Model):
    id = AutoField(primary_key=True)
    event = ForeignKey('Event', null=False, on_delete=CASCADE)
    files = ForeignKey('Files', null=False, on_delete=CASCADE)

    class Meta:

        db_table = 'FilesForEvent'

    def __str__(self):
        return f"{self.id} {self.event.name} {self.files.title}"
    
