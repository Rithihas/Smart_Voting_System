#final module/code

import sys
#  path[0] is reserved for script path
# this is for providing location of our packages 
sys.path.insert(1, 'D:/CVRnotes/year3sem2/mini project/Smart_Voting_System/face_recognition/virtual/Lib/site-packages')

from pymongo import MongoClient #for connecting to DB
from PIL import Image # for converting base64 decoded image to bytes array
import base64 # for base64 decoding
import io 
from deepface import DeepFace # for face-recognition models
from flask import Flask, request, jsonify # for creating server
import numpy as np  # for converting bytes array to numpy array
from flask_cors import CORS # For security reasons, browsers restrict cross-origin HTTP requests initiated from scripts. For example, XMLHttpRequest and the Fetch API follow the same-origin policy. This means that a web application using those APIs can only request resources from the same origin the application was loaded from unless the response from other origins includes the right CORS headers.
#this allows us to perform cross origin requests.

app = Flask(__name__) #initializing instance of Flask object.
cors = CORS(app) # for cross origin

@app.route('/validate', methods = ["POST"]) # url to call this method
def validateFace():

    data = request.get_json()  #extracting request data
    # data = jsonify(data)

    
    #connecting to mongo DB database  
    # mongodb+srv://rithihas:votingsmart@smartvoting.xgicig5.mongodb.net/?retryWrites=true&w=majority      
    client = MongoClient("mongodb://127.0.0.1:27017/") #connecting to local mongoDB database
 
    dbname = client["SmartVote"] 

    if data[0]["collection"] == "organizer":
        cname = dbname["Organizers"] #getting required collection
    else:
        cname = dbname["Voters"] #getting required collection
    # print(data)
    
    
    

    retrieved = cname.find_one({"_id" : data[0]["user"]}) #retrieving record from collection

    

    decode = base64.b64decode(retrieved['PhotoString'])  # decoding base64 string retrieved from database

    # converting the decode object into numpy array (deepface is compatible with numpy arrays)
    image = Image.open(io.BytesIO(decode))
    dbim = np.array(image)

    # doing the same with webcam photo
    cdecode = base64.b64decode(data[0]["photo"])

    currentimage = Image.open(io.BytesIO(cdecode))
    cim = np.array(currentimage)

      
    # verifying using deepface (model used: VGG)
    result = DeepFace.verify(dbim,cim,enforce_detection=False)

    client.close()
    
    #comparing the dissimilarity between the faces , and returning result
    if result['distance'] < 0.32:
        return {"fresult" : "true" , "distance" : result['distance']}
    else:
        return {"fresult" : "false", "distance" : result['distance']}
    


#for inserting records into the database

@app.route('/insert', methods = ["POST"])
def insertFace():

    data = request.get_json()
    # data = jsonify(data)

    
            
    client = MongoClient("mongodb+srv://rithihas:votingsmart@smartvoting.xgicig5.mongodb.net/?retryWrites=true&w=majority")
 
    dbname = client["smartvoting"]

    cname = dbname["voters"]

    photo_string = data[0]['photo']

    user = data[0]['user']

    password = data[0]['pass']

    voter = {"username" : user , "password" : password , "photo"  : photo_string}


    inserted = cname.insert_one(voter)

    return {"fresult" : "inserted"}

    
  
