# Generated by Django 4.1.2 on 2023-05-05 13:11

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('organizationapp', '0003_alter_user_email'),
    ]

    operations = [
        migrations.CreateModel(
            name='Sponsor',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('description', models.CharField(max_length=500, null=True, verbose_name='description')),
                ('profile', models.OneToOneField(null=True, on_delete=django.db.models.deletion.CASCADE, to='organizationapp.profile')),
            ],
            options={
                'db_table': 'Sponsor',
            },
        ),
        migrations.AddField(
            model_name='event',
            name='if_active',
            field=models.BooleanField(default=True),
        ),
        migrations.CreateModel(
            name='SponsorForEvent',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('event', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='organizationapp.event')),
                ('sponsor', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='organizationapp.sponsor')),
            ],
            options={
                'db_table': 'SponsorForEvent',
            },
        ),
        migrations.CreateModel(
            name='Application',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('vacancy', models.CharField(max_length=50)),
                ('description', models.CharField(max_length=5000, null=True)),
                ('event', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='organizationapp.event')),
                ('profile', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='organizationapp.profile')),
            ],
            options={
                'db_table': 'Application',
            },
        ),
    ]
