from django.shortcuts import render
from django.http import JsonResponse

from rest_framework import viewsets
# from .serializers import HeroSerializer
# from .models import Hero

# Create your views here.


def login(request):
    return JsonResponse({'name': 'Aakarshit', 'msg': 'Welcome to the world'})
