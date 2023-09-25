from django.apps import AppConfig


class OrganizationappConfig(AppConfig):
    default_auto_field = 'django.db.models.BigAutoField'
    name = 'organizationapp'
    
    
    def ready(self):
        from organizationapp import signals