# Generated by Django 4.1.2 on 2023-05-07 15:28

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('organizationapp', '0006_alter_event_results'),
    ]

    operations = [
        migrations.AlterField(
            model_name='event',
            name='results',
            field=models.CharField(blank=True, max_length=5000, null=True),
        ),
    ]
