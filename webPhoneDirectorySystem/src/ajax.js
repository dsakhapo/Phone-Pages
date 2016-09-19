/**
 * Created by bicboi on 9/5/16.
 */


var xmlHttpReq;
var url = "http://localhost:80/PhoneDirectorySystem/rest";
function xmlHttpCreate(){
    //For modern browsers
    if(window.XMLHttpRequest){
        xmlHttpReq = new XMLHttpRequest();
    }else{
        //For IE5, IE6
        xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function xmlHttpPost(urlExt){
    xmlHttpReq.open("POST", url + urlExt, true);
    xmlHttpReq.setRequestHeader("Content-type", "application/json; charset=utf-8");
    xmlHttpReq.onreadystatechange = function(){
        if(xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200)
            alert(xmlHttpReq.responseText);
    };
    xmlHttpReq.send(getJSONData());
}

function xmlHttpDelete(urlExt){
    xmlHttpReq.open("DELETE", url + urlExt, true);
    xmlHttpReq.setRequestHeader("Content-type", "application/json; charset=utf-8");
    xmlHttpReq.onreadystatechange = function(){
        if(xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200)
            alert(xmlHttpReq.responseText);
    };
    xmlHttpReq.send(getJSONData());
}

function xmlHttpGet(urlExt){
    xmlHttpReq.open("GET", url + urlExt, true);
    xmlHttpReq.send();
    xmlHttpReq.onreadystatechange = function(){
        if(xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200) {
            var json = xmlHttpReq.responseText;
            if(json == ""){
                alert("Error: Not a valid entry.")
            }else{
                var entry = JSON.parse(json);
                document.getElementById("entry").innerHTML = "<table id=\"entry-table\"> " +
                    "<tr> " +
                    "<th>Phone Number</th> " +
                    "<th>Address</th> " +
                    "<th>Name</th>" +
                    "</tr>" +
                    "<tr>" +
                    "<td>" + entry.phoneNumber + "</td>" +
                    "<td>" + entry.address + "</td>" +
                    "<td>" + entry.name + "</td>" +
                    "</tr>" +
                    "</table>";
            }
        }
    };

}

function xmlHttpGetAll(urlExt){
    xmlHttpReq.open("GET", url + urlExt, true);
    xmlHttpReq.send();
    xmlHttpReq.onreadystatechange = function(){
        if(xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200) {
            var json = xmlHttpReq.responseText;
            var phoneDirectory = JSON.parse(json);
            var tableInfo = "";

            //In case there exists only 1 entry currently
            if(phoneDirectory.phoneEntryBean.length == undefined){
                tableInfo +=
                    "<tr>" +
                    "<td>" + phoneDirectory.phoneEntryBean.phoneNumber + "</td>" +
                    "<td>" + phoneDirectory.phoneEntryBean.address + "</td>" +
                    "<td>" + phoneDirectory.phoneEntryBean.name + "</td>" +
                    "</tr>";
                //More than 1 entry exists
            }else{
                for(var i = 0; i < phoneDirectory.phoneEntryBean.length; ++i){
                    tableInfo +=
                        "<tr>" +
                        "<td>" + phoneDirectory.phoneEntryBean[i].phoneNumber + "</td>" +
                        "<td>" + phoneDirectory.phoneEntryBean[i].address + "</td>" +
                        "<td>" + phoneDirectory.phoneEntryBean[i].name + "</td>" +
                        "</tr>";
                }
            }
           document.getElementById("display").innerHTML = "<table id=\"display-table\">" +
                    "<tr>" +
                    "<th>Phone Number</th> " +
                    "<th>Address</th> " +
                    "<th>Name</th>" +
                    "</tr>" + tableInfo + "</table>";
        }
    };
}

function xmlHttpUpdate(urlExt){
    xmlHttpReq.open("PUT", url + urlExt, true);
    xmlHttpReq.setRequestHeader("Content-type", "application/json; charset=utf-8");
    xmlHttpReq.onreadystatechange = function(){
        if(xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200)
            alert(xmlHttpReq.responseText);
    };
    xmlHttpReq.send(getJSONData());
}

function clearData(){
    var htmlElements = new Array();
    htmlElements[0] = document.getElementById("data");
    htmlElements[1] = document.getElementById("entry");
    htmlElements[2] = document.getElementById("display");

    //Clear all form values
    for(var i = 0; i < htmlElements[0].length; ++i)
        htmlElements[0].elements[i].value = "";

    //Clear all entries/tables
    htmlElements[1].innerHTML = "";
    htmlElements[2].innerHTML = "";

}

function getJSONData(){
    var form = document.forms["data"];
    var formValues = new Array();
    var formPairing = new Object();

    for(var i = 0; i < form.length; ++i)
        formValues[i] = form.elements[i].value;

    formPairing.name = formValues[0];
    formPairing.address = formValues[1];
    formPairing.phoneNumber = formValues[2];

    var phoneEntry = JSON.stringify(formPairing);
    return phoneEntry;
}

function submit(){
    xmlHttpCreate();
    xmlHttpPost("/create");
}

function deleteEntry(){
    xmlHttpCreate();
    xmlHttpDelete("/delete");
}

function getEntry(){
    xmlHttpCreate();
    xmlHttpGet("/entry?phoneNumber=" + document.forms["data"].elements[2].value);
}

function displayTable(){
    xmlHttpCreate();
    xmlHttpGetAll("/display");
}

function updateEntry(){
    xmlHttpCreate();
    xmlHttpUpdate("/update");
}
