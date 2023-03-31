$.ajax(
    {
        type: 'POST',
        url : "D:/CVRnotes/year3sem2/mini project/Smart_Voting_System/face_recognition/validateFace,py",
        data : {param : "hey"},
        dataType : "text",
        success : function(response){
            output = response;
            console.log(response);
        }
    }
).done( function(data) {
    console.log(data);
});