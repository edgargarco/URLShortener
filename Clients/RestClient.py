#!/usr/bin/python
import unirest
import subprocess as sp
import os
from PIL import Image  

DOMAIN = "https://smart-la-vega.technology"
acceptHeader = "application/json"
authPrefix = "Bearer "
jwtToken = ""
command = ""
img = ""

def pauseProgram():
    programPause = raw_input("\nPress enter to continue...")

def clearShell():
    sp.call("clear", shell = True)

def takeUsername():
    return raw_input("Enter the username: ")

def takePassword():
    return raw_input("Enter the password: ")

def takeUrl():
    return raw_input("Enter the Url: ")

def autheticate():
    global jwtToken
    jwtToken = ""
    clearShell()
    print("Authentication on the server\n")
    while jwtToken == "":
        username = takeUsername()
        password = takePassword()
        response = unirest.post(DOMAIN + "/login", headers={ "Accept": acceptHeader }, params={ "username": username, "password": password })
        if 'token' in response.body:
            jwtToken = response.body["token"]
            print("User validated...\nToken = " + jwtToken)
            pauseProgram()
            break
        elif 'code' in response.body and 'message' in response.body:
            checkStatus(response.body)
        else:
            print("Something when wrong. Try again!")

def checkStatus(data):
    code = data["code"]
    message = data["message"]
    print("Code: " + str(code) + " " + message)
    if code == 1000:
        print("\nSession expired. You need re-login...")
        pauseProgram()
        autheticate()
    else:
        pauseProgram()

def getUrls():
    clearShell()
    print("Get URL's From an User\n")
    username = takeUsername()
    response = unirest.get(DOMAIN + "/rest/url/", headers={ "Accept": acceptHeader, "Authorization": authPrefix + jwtToken}, params={ "username": username })
    if 'code' in response.body and 'message' in response.body:
        checkStatus(response.body)
        return None
    else:
        return response.body

def createUrl():
    clearShell()
    print("Create a Shorter URL\n")
    username = takeUsername()
    url = takeUrl()
    response = unirest.put(DOMAIN + "/rest/url/create", headers={ "Accept": acceptHeader, "Authorization": authPrefix + jwtToken}, params={ "username": username, "url": url })
    if 'code' in response.body and 'message' in response.body:
        checkStatus(response.body)
        return None
    else:
        return response.body

def showUrl(urlJson):
    print("\n" + "-" * 140 + "\n") 
    print("Original URL: " + str(urlJson["url"]))
    print("Shorter URL: " + str(urlJson["hash"]))
    print("Creation Date: " + str(urlJson["creationDate"]["year"]) + "-" + str(urlJson["creationDate"]["monthValue"]) + "-" + str(urlJson["creationDate"]["dayOfMonth"]))
    if urlJson["actualImage"] != None:
        fh = open("image.jpeg", "wb")
        fh.write(urlJson["actualImage"].decode('base64'))
        fh.close()
        global img                                                                              
        img = Image.open(r'image.jpeg')
        img.show()
    print("\nEstadisticas\n")
    print("Windows Users: " + str(urlJson["statistics"]["windowsUser"]))
    print("Linux Users: " + str(urlJson["statistics"]["linuxUser"]))
    print("IOS Users: " + str(urlJson["statistics"]["iOSUser"]))
    print("Android Users: " + str(urlJson["statistics"]["androidUser"]))
    for browser in urlJson["statistics"]["browsersList"]:
        print(browser["browser"] + " Users: " + str(browser["cantVisit"]))
    print("Total de Visitas: " + str(urlJson["statistics"]["windowsUser"] + urlJson["statistics"]["linuxUser"] +  urlJson["statistics"]["iOSUser"] + urlJson["statistics"]["androidUser"]))
    print("\n" + "-" * 140 + "\n") 


def functionality(command):
    if jwtToken == "" and command != "4":
        autheticate()

    if command == "1":
        urls = getUrls()
        if urls != None:
            clearShell()
            print("URL's Received")
            for url in urls:
                showUrl(url)
            pauseProgram()
    elif command == "2":
        url = createUrl()
        if url != None:
            clearShell()
            print("URL's Created")
            showUrl(url)
            pauseProgram()
            if os.path.exists("image.jpeg"):
                os.remove("image.jpeg")
    elif command == "3":
        clearShell()
        print("Token = " + jwtToken)
        pauseProgram()
    elif command == "4":
        print("\nStopping services...\nGoodbye...\n")
    else:
        print("\nBad selection...")
        pauseProgram()

#60 seconds to wait for responses
unirest.timeout(60)

while(command != "4"):
    clearShell()
    print("Welcome to your RestClient for the URL Shortener...\n")
    print("Please select what you want to do...\n")
    print("1-Get URL's From an User\n2-Create an URL\n3-Check Current Token\n4-Exit\n")
    command = raw_input("Write your command: ")
    functionality(command) 