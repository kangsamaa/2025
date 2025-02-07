import requests
import sys
import json

# ipstack API를 사용하여 IP로부터 위치 정보를 얻는 함수
def get_location_from_ip(ip_address):
    #ipstack_api_key = '81354d585b0b05680846a82412048344'
    url = f"http://api.ipstack.com/{ip_address}?access_key={ipstack_api_key}"
    
    response = requests.get(url)
    location_data = response.json()

    # 도시 이름 반환
    city = location_data.get('city', 'Tokyo')  # 기본값 'Seoul' 설정
    return city

# OpenWeatherMap API를 사용하여 날씨 정보 조회 함수
def get_weather(city):
    #api_key = 'fb14a45455d10574d641e2b74991d6de'
    url = f"http://api.openweathermap.org/data/2.5/weather?q={city}&appid={api_key}&units=metric&lang=kr"
    
    response = requests.get(url)
    weather_data = response.json()

    # 날씨 정보 처리
    if weather_data.get('cod') != 200:
        return {"error": "Could not retrieve weather data."}
    
    weather_info = {
        "city": city,
        "temperature": weather_data['main']['temp'],
        "humidity": weather_data['main']['humidity'],
        "description": weather_data['weather'][0]['description'],
        "icon": weather_data['weather'][0]['icon']
    }

    return weather_info

def main():
    ip_address = sys.argv[1]  # IP 주소를 커맨드 라인에서 전달받음
    city = get_location_from_ip(ip_address)  # IP 주소로 도시 추적
    weather_info = get_weather(city)  # 해당 도시의 날씨 정보 조회

    # 결과를 JSON 형식으로 출력
    print(json.dumps(weather_info))

if __name__ == "__main__":
    main()
