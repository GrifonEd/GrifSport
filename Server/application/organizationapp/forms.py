from django import forms

class UploadFileForm(forms.Form):
    fileee = forms.FileField()
