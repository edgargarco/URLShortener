$(document).on('click','#select-date',function () {
    var hash = $('input.hashKEY').val();
    var date = $('input.datepicker-here').val()
    $.post('/getStatisctics',{hash:hash,date:date},function (data) {
        var visitsPerHour = JSON.parse(data);
        alert(visitsPerHour[0])
        chart(visitsPerHour);
    })

})
 var y = 0;
$(document).ready(function () {chart(y)})
function chart(info) {
    var x;
    if (typeof info === 'undefined') {
        x = 0;
    }else{
        x = info;
    }
    const chart = document.getElementById("myChart");
    console.log(chart);
    var lineChart = new Chart(chart,{
        type: 'line',
        data:{
            labels: ["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"],
            datasets: [
                {
                    label: "Visitas vs Tiempo",
                    fill:false,
                    lineTension:0.1,
                    backgroundColor:"rgba(75,192,192,0.4)",
                    borderColor:"rgba(75,192,192,1)",
                    borderCapStyle:"butt",
                    borderDash:[],
                    borderDashOffset:0.0,
                    borderJoinStyle:"miter",
                    pointBorderColor:"rgba(75,192,192,1)",
                    pointBackgroundColor:"#fff",
                    pointBorderWidth:1,
                    pointBorderRadius:5,
                    pointHoverBackgroundColor: "rgba(75,192,192,1)",
                    pointHoverBorderColor:"rgba(220,220,220,1)",
                    pointHoverBorderWidth:2,
                    pointRadius :1,
                    pointHitRadius:10,
                    data:x
                }]
        }
    })
}