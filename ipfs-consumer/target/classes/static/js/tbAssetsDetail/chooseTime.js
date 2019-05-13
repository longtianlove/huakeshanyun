/*
*  @author dp
*  @since 2019-03-12
*/
layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    layui.use('laydate', function datatime(){
	    var laydate = layui.laydate;
	    //日期时间
	    var start = laydate.render({
	        elem:'#startTime',
	        type:'datetime',
	        calendar: true,
            done: function(value, date, endDate){
            	end.config.min = {
                        year: date.year,
                        month: date.month - 1,
                        date: date.date,
                        hours: date.hours,
                        minutes: date.minutes,
                        seconds: date.seconds
                    }
            	}

    });
	    var end = laydate.render({
		  elem:'#endTime',
		  type:'datetime',
		  calendar: true,
	      done: function(value, date){
	    	  start.config.max = {
	    	            year: date.year,
	    	            month: date.month - 1,
	    	            date: date.date,
	    	            hours: date.hours,
	    	            minutes: date.minutes,
	    	            seconds: date.seconds
	    	        }
	      }
	    });
	});
  	  
    form.on("submit(export)",function(data){   
    	var end=$("#endTime").val();
    	var start=$("#startTime").val(); 
    	window.open("/tbAssetsDetail/export?endTime="+end+"&startTime="+start);
    })
})