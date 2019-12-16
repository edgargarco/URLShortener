#!/usr/bin/python
import zeep
import subprocess as sp
import os
from PIL import Image

url = "https://soap.smart-la-vega.technology/soap/ShortenerWS?wsdl"
client = zeep.Client(url)
command = ""
img = ""

def pauseProgram():
    programPause = raw_input("\nPress any key to continue...")

def clearShell():
    sp.call("clear", shell = True)

def takeUsername():
    return raw_input("Enter the username: ")

def takeUrl():
    return raw_input("Enter the Url: ")

def getUrls():
    clearShell()
    print("Get URL's From an User\n")
    username = takeUsername()
    response = client.service.getUrls(username);
    if response == None or len(response) == 0:
        return None
    else:
        return response

def createUrl():
    clearShell()
    print("Create a shoter URL\n")
    username = takeUsername()
    url = takeUrl()
    response = client.service.createUrl(username, url)
    if response == None:
        return None
    else:
        return response

def showUrl(urlJson):
    print("\n" + "-" * 140 + "\n")
    print("Original URL: " + str(urlJson["url"]))
    print("Shorter URL: " + str(urlJson["hash"]))
    print("Creation Date: " + str(urlJson["creationDate"]).split("T")[0])
    if urlJson["actualImage"] != None:
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
    for browser in urlJson["statistics"]["browsersList"]:
        print(browser["browser"] + " Users: " + str(browser["cantVisit"]))
    print("Total de Visitas: " + str(urlJson["statistics"]["windowsUser"] + urlJson["statistics"]["linuxUser"] +  urlJson["statistics"]["iOSUser"] + urlJson["statistics"]["androidUser"]))
    print("\n" + "-" * 140 + "\n")

def functionality(command):
    if command == "1":
        urls = getUrls()
        if urls != None:
            clearShell()
            print("URL's Received")
            for url in urls:
                showUrl(url)
        else:
            print("\nThis user does not exist or does not have registered URLs...\n")
        pauseProgram()
    elif command == "2":
        url = createUrl()
        if url != None:
            clearShell()
            print("URL Created")
            showUrl(url)
            if os.path.exists("image.jpeg"):
                os.remove("image.jpeg")
        else:
            print("\nSomething when wrong, check the user and the url that want to create...\n")
        pauseProgram()
    elif command == "3":
        print("\nStopping services...\nGoodbye...\n")
    else:
        print("\nBad selection...")
        pauseProgram()


while(command != "3"):
    clearShell()
    print("Welcome to your SoapClient for the URL Shortener...\n")
    print("Please select what you want to do...\n")
    print("1-Get URL's From an User\n2-Create an URL\n3-Exit\n")
    command = raw_input("Write your command: ")
    functionality(command)
