# Generated by Django 4.1.2 on 2023-05-07 20:50

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('organizationapp', '0007_alter_event_results'),
    ]

    operations = [
        migrations.AlterUniqueTogether(
            name='judgeforevent',
            unique_together={('event', 'judge')},
        ),
        migrations.AlterUniqueTogether(
            name='organizerforevent',
            unique_together={('event', 'organizer')},
        ),
        migrations.AlterUniqueTogether(
            name='participantforevent',
            unique_together={('event', 'participant')},
        ),
        migrations.AlterUniqueTogether(
            name='workerforevent',
            unique_together={('event', 'worker')},
        ),
    ]
