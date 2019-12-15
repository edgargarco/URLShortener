#!/usr/bin/python
import zeep
import subprocess as sp
import os
from PIL import Image





url = "http://localhost:7777/soap/ShortenerWS?wsdl"
client = zeep.Client(url)
command = ""
img = ""

def pauseProgram():
    programPause = raw_input("\nPress any key to continue...")

def clearShell():
    sp.call("clear", shell = True)

def createUrl():
    clearShell()
    print("Create a shoter URL\n")
    username = raw_input("Enter the Username: ")
    url = raw_input("Enter the Url: ")
    response = client.service.createUrl(username,url)

    if 'code' in response and 'message' in response:
        checkStatus(response)
        return None
    else:
        return response

def checkStatus(data):
    code = data["code"]
    message = data["message"]
    print("Code: " + str(code) + " " + message)

    if code == 1000:
        print("\nSession expired. You need re-login...")
        pauseProgram()
    else:
        pauseProgram()

def showUrl(urlJson):
    print("\n" + "-" * 140 + "\n")
    print("Original URL: " + urlJson["url"])
    print("Shorter URL: " + urlJson["hash"])
    print("Creation Date: " + str(urlJson["creationDate"]))
    if urlJson["actualImage"] != None:
        path = "/home/garco/IdeaProjects/URLShortener/Clients/image.jpeg"
        fh = open("image.jpeg", "wb")
        fh.write(urlJson["actualImage"].decode('base64'))
        fh.close()
        global img
        img = Image.open(r"image.jpeg")
        img.show()
    print("\nEstadisticas\n")
    print("Windows Users: " + str(urlJson["statistics"]["windowsUser"]))
    print("Linux Users: " + str(urlJson["statistics"]["linuxUser"]))
    print("IOS Users: " + str(urlJson["statistics"]["iOSUser"]))
    print("Android Users: " + str(urlJson["statistics"]["androidUser"]))
    print("Total de Visitas: " + str(urlJson["statistics"]["windowsUser"] + urlJson["statistics"]["linuxUser"] +  urlJson["statistics"]["iOSUser"] + urlJson["statistics"]["androidUser"]))
    print("\n" + "-" * 140 + "\n")

def getUrls():
    clearShell()
    print("Get URL's From an User\n")
    username = raw_input("Enter the username: ")
    response = client.service.getUrls(username);
    if 'code' in response and 'message' in response:
        checkStatus(response)
        return None
    else:
        return response

def functionality(command):

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
    elif command == "4":
        print("\nStopping services...\nGoodbye...\n")
    else:
        print("\nBad selection...")
        pauseProgram()


while(command != "4"):
    clearShell()
    print("Welcome to your SoapClient for the URL Shortener...\n")
    print("Please select what you want to do...\n")
    print("1-Get URL's From an User\n2-Create an URL\n4-Exit\n")
    command = raw_input("Write your command: ")
    functionality(command)
