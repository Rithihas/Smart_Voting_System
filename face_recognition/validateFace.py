#final module/code

import sys
# caution: path[0] is reserved for script path (or '' in REPL)
sys.path.insert(1, 'D:/CVRnotes/year3sem2/mini project/Smart_Voting_System/face_recognition/Lib/site-packages')

from pymongo import MongoClient
import base64
from deepface import DeepFace
from flask import Flask

app = Flask(__name__)

@app.route('/facevalidate')
def validateFace(username,password,cimagepath):
            
    client = MongoClient("mongodb+srv://rithihas:votingsmart@smartvoting.xgicig5.mongodb.net/?retryWrites=true&w=majority")
 
    dbname = client["smartvoting"]

    cname = dbname["voters"]
    
    retrieved = cname.find_one({"username" : username})
    
    img = base64.b64decode((retrieved['photo']))
    with open("D:/CVRnotes/year3sem2/mini project/Smart_Voting_System/face_recognition/image_staging/dbimage.jpg", "wb") as fh:
        fh.write(img)
        
    
    result = DeepFace.verify(img1_path = "D:/CVRnotes/year3sem2/mini project/Smart_Voting_System/face_recognition/image_staging/dbimage.jpg" , img2_path =cimagepath,enforce_detection=False)




    if result['distance'] < 0.32:
        return 1
    else:
        return 0
