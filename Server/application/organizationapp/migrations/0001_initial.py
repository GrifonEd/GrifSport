# Generated by Django 4.1.2 on 2023-04-28 13:28

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion
import organizationapp.managers


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('auth', '0012_alter_user_first_name_max_length'),
    ]

    operations = [
        migrations.CreateModel(
            name='User',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('password', models.CharField(max_length=128, verbose_name='password')),
                ('last_login', models.DateTimeField(blank=True, null=True, verbose_name='last login')),
                ('is_superuser', models.BooleanField(default=False, help_text='Designates that this user has all permissions without explicitly assigning them.', verbose_name='superuser status')),
                ('username', models.CharField(max_length=255, unique=True, verbose_name='username')),
                ('email', models.EmailField(max_length=254, verbose_name='email address')),
                ('date_joined', models.DateTimeField(auto_now_add=True, verbose_name='date joined')),
                ('is_active', models.BooleanField(default=False, verbose_name='active')),
                ('is_staff', models.BooleanField(default=False, verbose_name='staff')),
                ('is_verified', models.BooleanField(default=False, verbose_name='verified')),
                ('groups', models.ManyToManyField(blank=True, help_text='The groups this user belongs to. A user will get all permissions granted to each of their groups.', related_name='user_set', related_query_name='user', to='auth.group', verbose_name='groups')),
                ('user_permissions', models.ManyToManyField(blank=True, help_text='Specific permissions for this user.', related_name='user_set', related_query_name='user', to='auth.permission', verbose_name='user permissions')),
            ],
            options={
                'verbose_name': 'user',
                'verbose_name_plural': 'users',
                'unique_together': {('username', 'email')},
            },
            managers=[
                ('objects', organizationapp.managers.UserManager()),
            ],
        ),
        migrations.CreateModel(
            name='Discipline',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('name', models.CharField(max_length=500, verbose_name='first_name')),
            ],
            options={
                'db_table': 'Discipline',
            },
        ),
        migrations.CreateModel(
            name='Event',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('name', models.CharField(max_length=255)),
                ('description', models.CharField(blank=True, max_length=255)),
                ('adress', models.CharField(max_length=500)),
                ('date_created', models.DateTimeField(auto_now_add=True)),
                ('event_start_date', models.DateTimeField()),
                ('event_end_date', models.DateTimeField()),
                ('image', models.CharField(blank=True, max_length=255, null=True)),
                ('schedule', models.CharField(blank=True, max_length=5000)),
                ('results', models.CharField(blank=True, max_length=5000)),
                ('deadline_for_accepting_applications', models.DateTimeField()),
                ('ages', models.CharField(max_length=255)),
                ('genders', models.CharField(max_length=50, verbose_name='genders')),
                ('phone_number', models.CharField(max_length=12, verbose_name='phone_number')),
            ],
            options={
                'db_table': 'Event',
            },
        ),
        migrations.CreateModel(
            name='Organizer',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('first_name', models.CharField(max_length=500, verbose_name='first_name')),
                ('second_name', models.CharField(max_length=500, verbose_name='second_name')),
                ('sex', models.CharField(max_length=500, verbose_name='sex')),
                ('age', models.IntegerField(verbose_name='age')),
                ('phone_number', models.CharField(max_length=12, verbose_name='phone_number')),
            ],
            options={
                'db_table': 'Organizer',
            },
        ),
        migrations.CreateModel(
            name='TypeDiscipline',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('olympic', models.BooleanField(default=False)),
                ('team', models.BooleanField(default=False)),
            ],
            options={
                'db_table': 'TypeDiscipline',
            },
        ),
        migrations.CreateModel(
            name='TypeEvent',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('type', models.CharField(max_length=255, unique=True)),
            ],
            options={
                'db_table': 'TypeEvent',
            },
        ),
        migrations.CreateModel(
            name='TypeSport',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('olympic', models.BooleanField(default=False)),
                ('team', models.BooleanField(default=False)),
            ],
            options={
                'db_table': 'TypeSport',
            },
        ),
        migrations.CreateModel(
            name='Sport',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('name', models.CharField(max_length=500, verbose_name='first_name')),
                ('typeSport', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='organizationapp.typesport')),
            ],
            options={
                'db_table': 'Sport',
            },
        ),
        migrations.CreateModel(
            name='Profile',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('first_name', models.CharField(max_length=500, verbose_name='first_name')),
                ('second_name', models.CharField(max_length=500, verbose_name='second_name')),
                ('sex', models.CharField(max_length=10, verbose_name='sex')),
                ('age', models.IntegerField(verbose_name='age')),
                ('city', models.CharField(max_length=500, verbose_name='city')),
                ('phone_number', models.CharField(max_length=12, verbose_name='phone_number')),
                ('user', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
            options={
                'db_table': 'profile',
            },
        ),
        migrations.CreateModel(
            name='OrganizerForEvent',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('event', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='organizationapp.event')),
                ('organizer', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='organizationapp.organizer')),
            ],
            options={
                'db_table': 'OrganizerForEvent',
            },
        ),
        migrations.AddField(
            model_name='event',
            name='type',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.SET_NULL, to='organizationapp.typeevent'),
        ),
        migrations.CreateModel(
            name='DisciplineForEvent',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('discipline', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='organizationapp.discipline')),
                ('event', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='organizationapp.event')),
            ],
            options={
                'db_table': 'DisciplineForEvent',
            },
        ),
        migrations.AddField(
            model_name='discipline',
            name='sport',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, to='organizationapp.sport'),
        ),
        migrations.AddField(
            model_name='discipline',
            name='type',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, to='organizationapp.typediscipline'),
        ),
    ]
