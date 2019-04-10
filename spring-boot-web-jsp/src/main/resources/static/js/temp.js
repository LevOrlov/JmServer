var json_obj  = {
    "name":"John",
    "age":30,
    "cars": [
        { "name":"Ford", "models":[ "Fiesta", "Focus", "Mustang" ] },
        { "name":"BMW", "models":[ "320", "X3", "X5" ] },
        { "name":"Fiat", "models":[ "500", "Panda","550" ] }
    ]
}

$(document).ready(function(){
    $("button").click(function(){
        var number_of_rows = json_obj.cars.length;
        var k = 0;
        var table_body = '<table border="1" id="example"><thead><tr><th>Cars</th><th>Models</th></tr></thead><tbody>';
        for(j in json_obj.cars){
            for(i =0;i<json_obj.cars.length;i++){
                table_body+='<tr>';
                table_body +='<td>';
                table_body +=json_obj.cars[k]["name"];
                table_body +='</td>';

                table_body +='<td>';
                table_body +=json_obj.cars[k].models[i];
                table_body +='</td>';

                table_body+='</tr>';
            }
            k++;
        }
        table_body+='</tbody></table>';
        $('#tableDiv').html(table_body);
        //display data..........
    });
})