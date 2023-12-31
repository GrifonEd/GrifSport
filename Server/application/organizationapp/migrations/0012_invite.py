# Generated by Django 4.1.2 on 2023-05-19 21:50

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('organizationapp', '0011_alter_application_description_and_more'),
    ]

    operations = [
        migrations.CreateModel(
            name='Invite',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('vacancy', models.CharField(max_length=50)),
                ('event', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='organizationapp.event')),
                ('profile', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='organizationapp.profile')),
            ],
            options={
                'db_table': 'Invite',
                'unique_together': {('profile', 'event')},
            },
        ),
    ]
