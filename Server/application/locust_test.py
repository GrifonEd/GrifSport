import json
import time
import random
import json
from locust import HttpUser, task, tag, between




class RESTServerUser(HttpUser):
    """ Класс, эмулирующий пользователя / клиента сервера """
    wait_time = between(1.0, 5.0)       # время ожидания пользователя перед выполнением новой task

    # Адрес, к которому клиенты (предположительно) обращаются в первую очередь (это может быть индексная страница, страница авторизации и т.п.)
    def on_start(self):
        self.client.get("/")    # базовый класс HttpUser имеет встроенный HTTP-клиент для выполнения запросов (self.client)

    @tag("get_all_event")
    @task(2)
    def get_all_event(self):
        """ Тест GET-запроса """
        with self.client.get(f'/event/all',        # тест get-метода, получение данных о доступе в квартиру
                             catch_response=True,
                             name='/event/all') as response:
            # Если получаем код HTTP-код 200, то оцениваем запрос как "успешный"
            if response.status_code == 200:
                response.success()
            # Иначе обозначаем как "отказ"
            else:
                response.failure(f'Status code is {response.status_code}')

    @tag("get_one_event")
    @task(5)
    def get_one_event(self):
        """ Тест GET-запроса """
        event_id = random.randint(20, 24)      # генерируем случайный id в диапазоне [4, 6]     
        with self.client.get(f'/event/{event_id}',        # тест get-метода, получение данных о доступе в квартиру
                             catch_response=True,
                             name='/event/{event_id}') as response:
            # Если получаем код HTTP-код 200, то оцениваем запрос как "успешный"
            if response.status_code == 200:
                response.success()
            # Иначе обозначаем как "отказ"
            else:
                response.failure(f'Status code is {response.status_code}')


    @tag("post_event")
    @task(2)
    def post_event(self):
        """ Тест PosT-запроса (обновление записи о погоде) """
        test_data = {
	"id": 20,
	"name": "1",
	"description": "1",
	"adress": "1",
	"city": {
		"name": "Уфа",
		"id": 1
	},
	"date_created": "2023-05-14T22:24:59.870331",
	"event_start_date": "2023-05-14T12:00:00",
	"event_end_date": "2023-04-27T13:40:00",
	"image": "1",
	"schedule": "1",
	"results": "",
	"type": {
		"id": 1,
		"type": "Фестиваль"
	},
	"sport": {
		"name": "Скалолазание",
		"id": 1,
		"typeSport": {
			"olympic": True,
			"id": 1,
			"team": True
		}
	},
	"deadline_for_accepting_applications": "2023-05-14T10:24:00",
	"ages": "1",
	"genders": "1",
	"phone_number": "1",
	"is_active": True
}
        post_data = json.dumps(test_data)
        # отправляем PUT-запрос на адрес <SERVER>/api/weatherforecast/{city_name}
        with self.client.post('/event/create',
                             catch_response=True,
                             name='/event/create',
                             data=post_data,
                             headers={'content-type': 'application/json'}) as response:
            if response.status_code == 201 or response.status_code == 200:
                response.success()
            else:
                response.failure(f'Status code is {response.status_code}')


    
    @tag("put_event")
    @task(3)
    def put_event(self):
        """ Тест PuT-запроса (создание записи о погоде) """
        # Генерируем случайные данные в опредленном диапазоне
        chislo = random.randint(24,60)
        test_data = {
	"id": 20,
	"name": "1",
	"description": "1",
	"adress": "1",
	"city": {
		"name": "Москва",
		"id": 1
	},
	"date_created": "2023-05-14T22:24:59.870331",
	"event_start_date": "2023-05-14T12:00:00",
	"event_end_date": "2023-04-27T13:40:00",
	"image": "1",
	"schedule": "1",
	"results": "",
	"type": {
		"id": 1,
		"type": "Фестиваль"
	},
	"sport": {
		"name": "Скалолазание",
		"id": 1,
		"typeSport": {
			"olympic": True,
			"id": 1,
			"team": True
		}
	},
	"deadline_for_accepting_applications": "2023-05-14T10:24:00",
	"ages": "1",
	"genders": "1",
	"phone_number": "1",
	"is_active": True
}
        put_data = json.dumps(test_data)       # сериализуем тестовые данные в json-строку
        # отправляем POST-запрос с данными (POST_DATA) на адрес <SERVER>/api/weatherforecast
        with self.client.put('/event/create',
                              catch_response=True,
                              name='/event/create', data=put_data,
                              headers={'content-type': 'application/json'}) as response:
            # проверяем, корректность возвращаемого HTTP-кода
            if response.status_code == 201:
                response.success()
            else:
                response.failure(f'Status code is {response.status_code}')


    @tag("get_all_applications_for_event")
    @task(1)
    def get_all_applications_for_event(self):
        """ Тест GET-запроса (получение одной записи) """
        event_id = 24
        with self.client.get(f'/application/event/{event_id}',
                             catch_response=True,
                             name='/application/event/{event_id}') as response:
            # Если получаем код HTTP-код 200 или 204, то оцениваем запрос как "успешный"
            if response.status_code == 200 or response.status_code == 204:
                response.success()
            else:
                response.failure(f'Status code is {response.status_code}')

    @tag("post_application")
    @task(2)
    def post_application(self):
        """ Тест PosT-запроса (обновление записи о погоде) """
        test_data = {
	"id": 1,
	"profile": 18,
	"event": 20,
	"vacancy": "1",
	"description": ""
}
        post_data = json.dumps(test_data)
        # отправляем PUT-запрос на адрес <SERVER>/api/weatherforecast/{city_name}
        with self.client.post('/application/create',
                             catch_response=True,
                             name='/application/create',
                             data=post_data,
                             headers={'content-type': 'application/json'}) as response:
            if response.status_code == 201 or response.status_code == 200:
                response.success()
            else:
                response.failure(f'Status code is {response.status_code}')

    @tag("get_all_invites")
    @task(3)
    def get_all_invites(self):
        """ Тест GET-запроса (получение одной записи) """
        user_id = 7
        with self.client.get(f'/invite/profile/{user_id}',
                             catch_response=True,
                             name='/invite/profile/{user_id}') as response:
            # Если получаем код HTTP-код 200 или 204, то оцениваем запрос как "успешный"
            if response.status_code == 200 or response.status_code == 204:
                response.success()
            else:
                response.failure(f'Status code is {response.status_code}')

    
    @tag("post_invite")
    @task(2)
    def post_invite(self):
        """ Тест PosT-запроса (обновление записи о погоде) """
        test_data = {
	"id": 1,
	"profile": 18,
	"event": 20,
	"vacancy": "1",
}
        post_data = json.dumps(test_data)
        # отправляем PUT-запрос на адрес <SERVER>/api/weatherforecast/{city_name}
        with self.client.post('/invite/create',
                             catch_response=True,
                             name='/invite/create',
                             data=post_data,
                             headers={'content-type': 'application/json'}) as response:
            if response.status_code == 201:
                response.success()
            else:
                response.failure(f'Status code is {response.status_code}')


    @tag("get_all_event_for_organizer")
    @task(3)
    def get_all_event_for_organizer(self):
        """ Тест GET-запроса """
        profile = 18
        with self.client.get(f'/organizer-event/getAll/{profile}',        # тест get-метода, получение данных о доступе в квартиру
                             catch_response=True,
                             name='/organizer-event/getAll/{profile}') as response:
            # Если получаем код HTTP-код 200, то оцениваем запрос как "успешный"
            if response.status_code == 200:
                response.success()
            # Иначе обозначаем как "отказ"
            else:
                response.failure(f'Status code is {response.status_code}')

    @tag("get_all_participant_for_event")
    @task(2)
    def get_all_participant_for_event(self):
        """ Тест GET-запроса """
        event_id = 24
        with self.client.get(f'/participant-event/{event_id}',        # тест get-метода, получение данных о доступе в квартиру
                             catch_response=True,
                             name='/participant-event/{event_id}') as response:
            # Если получаем код HTTP-код 200, то оцениваем запрос как "успешный"
            if response.status_code == 200:
                response.success()
            # Иначе обозначаем как "отказ"
            else:
                response.failure(f'Status code is {response.status_code}')

    
