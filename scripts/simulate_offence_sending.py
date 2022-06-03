import requests
import json

API_ENDPOINT = "http://localhost:8081/api/detekcija-nepravilnosti"

if __name__ == "__main__":
    data = {"jmbg": "1231231231231"}

    for i in range(5):
        r = requests.post(url = API_ENDPOINT, data = json.dumps(data), headers={"Content-Type": "application/json"})
