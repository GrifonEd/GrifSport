# Generated by Django 4.1.2 on 2023-05-18 00:47

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('organizationapp', '0010_files_filesforevent'),
    ]

    operations = [
        migrations.AlterField(
            model_name='application',
            name='description',
            field=models.CharField(blank=True, max_length=5000, null=True),
        ),
        migrations.AlterUniqueTogether(
            name='application',
            unique_together={('profile', 'event')},
        ),
    ]
